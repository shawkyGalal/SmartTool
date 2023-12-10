CREATE OR REPLACE FUNCTION TAKAFUL.SPU_GET_LOADED_PREMIUM
		(
		year_no 				IN number,
    v_proposal_id     in number,
    v_user_id         in number,
    v_rider_id        in number
		) RETURN NUMBER
IS

BEGIN
	
  declare
  	
  return_value NUMBER :=0;
  rider_sum_inssured number:=0;
  RIDER_MATURITY NUMBER :=100;
  counter number:=0;

  after_maturity Exception;
  no_such_rider Exception;
    
  proposal_data tblproposals%rowtype;
  
	
	cursor 	cur_ld_basic (m_ALB number) is
		select 	LOAD_AMOUNT, pluse_prm
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= v_proposal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = v_user_id
  	and			loading_on = 0
  	and			load_mech = 'PRM'
   	and			m_ALB + year_no >= LOAD_AGE
    and     m_ALB + year_no < load_age +load_dur;
	
	cursor 	cur_ld_rider(m_alb number) is
		select 	loading_on, LOAD_AMOUNT, LOAD_PERMILLE, load_mech, pluse_prm
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= v_proposal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = v_user_id
    and     loading_on = v_rider_id
  	and			m_ALB + year_no >= LOAD_AGE
    and     m_ALB + year_no < load_age +load_dur;

    
  begin
  	-- getting basic premium loadings
    select * into proposal_data  from tblproposals t 
    where t.proposal_id =v_proposal_id 
    and t.prdcr_1_user_id =v_user_id;	  
    
    IF V_RIDER_ID = 0 THEN 
  	       
        if ( V_RIDER_ID = 0 OR V_RIDER_ID = 700 ) AND proposal_data.PREMIUM_PAY_YEARS < year_no then
          raise after_maturity;
     		end if;
        
        return_value:= proposal_data.ANNUALIZED_PREMIUM;
      	
      		for eachrec in cur_ld_basic(proposal_data.alb) loop
      				 	if upper(eachrec.PLUSE_PRM) = 'Y' then
      				 			return_value := return_value + nvl((eachrec.load_amount * proposal_data.SUM_INSURED / 1000),0);
      				 	elsif nvl(upper(eachrec.pluse_prm),'N') = 'N' then --- its effect will apear in the Cach Values so do nothing...
    				 				null; 
      				 	end if;
      		end loop;
    end if;
    
    If V_RIDER_ID <> 0 THEN 
  	-- Getting riders premium data
          select count(*)
          into counter
          from takaful.tblriders_per_proposals t 
          where t.props_1_proposal_id = v_proposal_id
               and t.Props_1_Prdcr_1_User_Id = v_user_id
               and t.rdrs_rider_id = v_rider_id; 
         
          if counter = 0 then
          raise no_such_rider;
          end if;     

          select t.annualized_premium,  T.MATURITY, t.sum_insured
          into return_value, RIDER_MATURITY, rider_sum_inssured  
          from takaful.tblriders_per_proposals t 
          where t.props_1_proposal_id = v_proposal_id
               and t.Props_1_Prdcr_1_User_Id = v_user_id
               and t.rdrs_rider_id = v_rider_id; 
          
          if RIDER_MATURITY < year_no then 
             raise after_maturity;
          end if;
                                                       
				 --- Apply loading to the rider premium, if any
         for r_rec in cur_ld_rider(proposal_data.alb) loop
		 				if upper(r_rec.load_mech) = 'PRM' and r_rec.pluse_prm='Y' then
			 						return_value := return_value + nvl((r_rec.load_amount * rider_sum_inssured /1000),0);
                  --- Note: Cash Value minus (r_rec.pluse_prm='N')has no meaning in case of rider loading
				 			elsif upper(r_rec.load_mech) = 'EXM' then -- extra mortaligy
                  return_value := return_value* nvl(r_rec.LOAD_PERMILLE, 1);
				 				
				 			end if;
				 end loop;
		END IF;

     
    RETURN return_value;
  			
    exception  
    when after_maturity  then 
             RETURN 0;
    when no_such_rider  then 
             RETURN 0;
        		
  end;
END;
/
