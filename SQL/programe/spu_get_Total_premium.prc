-- By Shawky Foda
-- Created 29/12/2002
--Purpose : this function returns the annualized loaded premium for a certain year along the policy period
--- This premium may be for basic plan if v_rider_id= 0 or any rider else

CREATE OR REPLACE FUNCTION TAKAFUL.SPU_GET_LOADED_PREMIUM  
		(
		year_no 				IN number,
--		sum_premium 		IN OUT number, 
    v_proposal_id     in number,
    v_user_id         in number,
    v_rider_id        in number
		) RETURN NUMBER
IS

BEGIN
	
  declare
  	
  ld_basic 	Number	:=0;
	ld_rider 	Number	:=0;
  sum_premium NUMBER :=0;
	ld_tot		Number	:=0;
	sum_insurred			number :=0;
  after_policy_maturity Exception;
  
  proposal_data tblproposals%rowtype;
  
	
	cursor 	cur_ld_basic (m_ALB number) is
		select 	LOAD_AMOUNT, pluse_prm
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= v_proposal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = v_user_id
  	and			loading_on = 0
 -- 	and			upper(PLUSE_PRM) = 'Y' 
  	and			load_mech = 'PRM'
   	and			m_ALB + year_no >= LOAD_AGE
  	and			LOAD_AGE - M_ALB + LOAD_DUR > year_no ; 
	
	cursor 	cur_ld_rider(m_alb number) is
		select 	loading_on, LOAD_AMOUNT, LOAD_PERMILLE, load_mech, pluse_prm
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= v_proposal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = v_user_id
  	and			loading_on > 0
--  	and			upper(PLUSE_PRM) = 'Y' 
  	and			m_ALB + year_no >= LOAD_AGE
  	and			LOAD_AGE - M_ALB + LOAD_DUR > year_no   
    and     loading_on = v_rider_id; 
    
  begin
  		  
    select * into proposal_data  from tblproposals t 
    where t.proposal_id =v_proposal_id 
    and t.prdcr_1_user_id =v_user_id;
    
    if proposal_data.PREMIUM_PAY_YEARS < year_no then
      RAISE after_policy_maturity;
  		end if;

    
    ld_basic:= proposal_data.ANNUALIZED_PREMIUM;
  	IF V_RIDER_ID = 0 THEN 
  	-- getting basic premium loadings
  		for eachrec in cur_ld_basic(proposal_data.alb) loop
  			exit when sql%notfound;
  				 	if upper(eachrec.PLUSE_PRM) = 'Y' then
  				 			ld_basic := ld_basic + nvl((eachrec.load_amount * proposal_data.SUM_INSURED / 1000),0);
  				 	elsif nvl(upper(eachrec.pluse_prm),'N') = 'N' then --- its effect will apear in the Cach Values so do nothing...
				 				null; 
  				 	end if;
  		end loop;
    
    ELSE  
  	-- getting riders premium loadings			
            select t.annualized_premium , t.sum_insured into ld_rider, sum_insurred 
            from takaful.tblriders_per_proposals t 
                      where t.props_1_proposal_id = v_proposal_id
                      and t.props_prdcr_1_user_id = v_user_id
                      and t.rdrs_rider_id = v_rider_id; 
                      
  				 for r_rec in cur_ld_rider(proposal_data.alb) loop
  				 		exit when sql%notfound;
      				 				if upper(r_rec.load_mech) = 'PRM' and r_rec.pluse_prm='Y' then
     				 						ld_rider := ld_rider + nvl((r_rec.load_amount * sum_insurred /1000),0);
                        --- Note Cash Value minus (r_rec.pluse_prm='N')has no meaning in case or rider loading
      				 				elsif upper(r_rec.load_mech) = 'EXM' then -- extra mortaligy
                          ld_rider := ld_rider* nvl(r_rec.LOAD_PERMILLE, 1);
      				 				
      				 				end if;
  				 end loop;
		END IF;

     if v_rider_id= 0 then
      	sum_premium := nvl(ld_basic,0) ; 
      else
      	sum_premium := round(ld_rider, 0);
      end if;
      
      RETURN sum_premium;
  			
  exception
    WHEN after_policy_maturity THEN 
         sum_premium:=0;
     when others then 
          null;
  		
  end;
END;
/
