CREATE OR REPLACE PACKAGE BODY TAKAFUL.SPU_investments As
--=============================================================================
  Function Get_Relative_Period 
  (
  		m_Period IN varchar2, 
  		Position IN number 
  )  return varchar2 is
  
  temp_date date;
	previous_period varchar2(6);

	begin
   
  temp_date := to_char(to_date('01-' || substr(m_period,1,2) || '-' || substr(m_period,3,4),'dd-mm-yyyy'));
  temp_date := add_months(temp_date, Position);
  
  previous_period := to_char(temp_date,'MMYYYY');
  
  return(previous_period);
   Exception
		  	When Others then
		  	   Raise_application_error(-60100, 'Get_Relative_Period ... Raised .. '|| Sqlerrm);	
  end Get_Relative_Period;
 
 --=============================================================================
 --=============================================================================
  procedure Close_Fund(m_fund_Id IN number, m_Period IN varchar2, Interest_rate IN Number) is
 
  Cursor cur_policies is
  	Select 	Policy_no 
  	from		vw_polfunds
  	where		fu_fund_id = m_fund_id
  	order by policy_no;
	
  begin
  	 
  	-- we need to get the policies that depends on this fund

 
 			for each_row in cur_policies loop
 				 calcCV(to_number(each_row.policy_no), m_period, to_number(Interest_Rate));
  		end loop;
  
  		
  	 Exception
		  	When Others then null;
		  	   Raise_application_error(-60200, 'Close_Fund ... Raised .. '|| Sqlerrm);
		  	   rollback;
  end Close_Fund;
 
 
 --=============================================================================
 --=============================================================================
 Function Get_Rate
 (
 									ALB IN Number, 
                  m_term IN Number, 
                  m_Class_id IN varchar2, 
                  Sex_Indicator IN varchar2, 
                  Plan_rider_id IN Number, 
                  isRider IN varchar2
                  
 ) Return Number IS

m_rate_id varchar2(5);
m_rate_type	varchar2(2);
m_FEMALE_DED_PER number(7,3) :=1;
m_bundled_rate number(7,3) := 0;
final_rate number(7,3) := 0;

CURSOR cur_bundled_riders IS 
			SELECT 	a.bundled_rate 
			FROM 		tblriders a, tblplan_riders b
			where 	a.rider_id = b.RDRS_RIDER_ID
			and			b.PLN_PLAN_ID = plan_rider_id
			and			upper(bundled) = 'Y';

begin
--For the Rating & CV calculations
	if upper(isRider) = 'N' then
		select upper(NCCI_RAT_RATE_ID) into m_rate_id
		from tblplans
		where plan_id = Plan_rider_id;
	else
		select upper(NCCI_RAT_RATE_ID) into m_rate_id
		from tblriders
		where rider_id = Plan_rider_id;
	end if;
	
		Select 	rate_type into m_rate_type
		from 		ncci_rates
		where 	upper(rate_id) = upper(m_rate_id); 
	
    if upper(m_rate_type) = 'RP' then
    	select 	rate into final_rate
    	from		rp_rates
    	where		upper(NCCI_RAT_RATE_ID) = upper(m_rate_id)
    	and			age = alb;
   
    elsif upper(m_rate_type) = 'LT' then
			select 	rate into final_rate
			from		lt_rates
			where		upper(NCCI_RAT_RATE_ID) = upper(m_rate_id)
			and			age = ALB
			and			term = m_term;
			
		elsif upper(m_rate_type) = 'DT' then
			select 	rate into final_rate
			from		dt_rates
			where		upper(NCCI_RAT_RATE_ID) = upper(m_rate_id)
			and			age = ALB
			and			term = m_term
			and			sex = sex_indicator
			and			tpd_included = 'N';
			
		elsif upper(m_rate_type) = 'CR' then
			select 	rate, female_ded_per into final_rate, m_FEMALE_DED_PER
			from		occupation_class_rates
			where		upper(NCCI_RAT_RATE_ID) = upper(m_rate_id)
			and			class_id = decode(m_class_id,0,1, m_class_id);
			
				if sex_indicator = 'F' then
					final_rate := final_rate * nvl(m_FEMALE_DED_PER,1);
				end if;
				
		end if;
    
    -- ********** Get the Bundled Rider's Rate (if any) for basic plans only  ***
    
    if upper(isRider) = 'N' then null;
    	For each_row IN cur_bundled_riders loop
    		m_bundled_rate := m_bundled_rate + each_row.bundled_rate;
    	end loop;
    end if;
    
	final_rate := final_rate + nvl(m_bundled_rate,0);
	
	return(final_rate/1000);

exception
	when no_data_found then final_rate := 0;
		return(final_rate);
	When Others then
			   raise_application_error ( -60300, ' SPU_Investments.Get_Rate ..'|| sqlerrm);
end Get_Rate;

 
--=============================================================================
--=============================================================================
Procedure Get_Loadings 
(
		Plan_id IN Number, 
		Rider_ID IN Number, 
		Year IN Number, 
		Premium In Number,
		m_Comm Out Number,
		m_Admin Out Number,
		m_expen Out Number
) IS

	max_Comm Number(6,3);
	max_Admin Number(6,3);
	max_expens Number(6,3);
	max_dur		number(3);
Begin
-- it's agreed that the last record in the table will be applied throughout the whole period ...
		
	If Plan_id != 0 and Rider_id != 0 then
		null;--	raise application_trigger_failure; -- ( -60001, 'Either Plan or Rider to be specified');
	
	elsif Plan_id != 0 then
		select max(dur) into max_dur 
		from tblcomm_loadings 
		where pln_plan_id = plan_id;
		
		Select 	COMM_RATE, 	ADMIN_RATE, EXPENSE_RATE 
		into 		max_Comm,		max_Admin,	max_expens
		from		tblcomm_loadings
		where 	pln_plan_id = Plan_id
		and			dur = max_dur;
		
		if Year >= max_dur then
			m_comm := max_comm;
			m_Admin := max_admin;
			m_Expen := max_expens;
		else
			Select 	COMM_RATE, 	ADMIN_RATE, EXPENSE_RATE 
			into 		m_Comm,		m_Admin,	m_expen
			from		tblcomm_loadings
			where 	pln_plan_id = Plan_id
			and			dur = year;	
		end if;
		
	elsif Rider_id != 0 then
		select max(dur) into max_dur 
		from tblcomm_loadings 
		where RDRS_RIDER_ID = rider_id;
		
		Select 	COMM_RATE, 	ADMIN_RATE, EXPENSE_RATE 
		into 		max_Comm,		max_Admin,	max_expens
		from		tblcomm_loadings
		where 	RDRS_RIDER_ID = rider_id
		and			dur = max_dur;
		
		if Year >= max_dur then
			m_comm := max_comm;
			m_Admin := max_admin;
			m_Expen := max_expens;
		else
			Select 	COMM_RATE, 	ADMIN_RATE, EXPENSE_RATE 
			into 		m_Comm,		m_Admin,	m_expen
			from		tblcomm_loadings
			where 	RDRS_RIDER_ID = rider_id
			and			dur = year;	
		end if;
	end if;	
	
	m_comm := round(nvl((Premium * m_comm / 100),0),2);
	m_Admin := round(nvl((Premium * m_Admin / 100),0),2);
	m_Expen := round(nvl((Premium * m_Expen / 100),0),2);	
	 Exception
		  	When Others then
		  	   Raise_application_error(-60400, 'Get_Loadings ... Raised .. '|| Sqlerrm);
End Get_Loadings; 

--=============================================================================
--=============================================================================
Procedure calcCV

-- IMPORTANT:  This is to remind everyone that the invesment module is based on
-- the Monthly rate of funds, so that all calculations will be converted to monthly figures ...

(		m_policy_no			IN	Number,
		m_fund_Period		IN	varchar2,
		m_interest_rate	IN Number
		
) IS

		cv_rate_id 	varchar2(5); 
		age 				Number(3);
		duration 		Number(3); 
		pay_years 	Number(3); 
		cv_class 		varchar2(2);
		cv_Sex 			varchar2(1);
		SI 					Number(18) 			:= 0; 
		m_currency	varchar2(3);
		m_plan 			Number(2);
		premium 		Number(14,2) 		:=0;
		m_Comm			Number(14,2) 		:=0;
		m_Admin			Number(14,2) 		:=0;
		m_expen			Number(14,2) 		:=0;
		m_Risk_Amt	Number(14,2)		:=0;
		tl_Risk_Amt	Number(14,2)		:=0;
		m_Remain		Number(14,2)		:=0;
		m_CV				Number(14,2) 		:=0;
		m_SF				Number(14,2)		:=0;
		m_SumAtRisk	Number(14,2)		:=0;
		temp_number	Number(14,2) 		:=0;
		m_portion 	Number(6,3) 		:=1;
		invest_year	Number(3) 			:=0;
		mort_rate Number(6,3) 			:=0;
		ex_mort		Number (4,2)			:=1;
		
		Cursor cur_invsts IS
			Select * from tblinvsts 
			where policy_no = m_policy_no
			and upper(status) = 'N'
			and invest_period = m_fund_period
			order by invest_seq;
			
		cursor 	cur_exm is
		select 	LOAD_AGE, LOAD_DUR, LOAD_PERMILLE
		from 		tblproposal_loadings a, tblproposals b
		where		a.PROPS_1_PROPOSAL_ID 		= b.PROPOSAL_ID
  	and 		a.PROPS_1_PRDCR_1_USER_ID = b.PRDCR_1_USER_ID 
  	and			b.policy_no = m_policy_no
  	and			loading_on = 0
   	and			load_mech = 'EXM'
   	and			age  >= LOAD_AGE
  	and			age <= load_age + load_dur; 
	
Begin
	select 	ALB, MATURITY_AGE , CURRENCY,  		SUM_INSURED, 	PREMIUM_PAY_YEARS, 	PLN_PLAN_ID, 	CLASS_ID, NCCI_RAT_RATE_ID
	into		age, duration,  		m_currency,  	si, 					pay_years,					m_plan, 			cv_class,	cv_rate_id
	
	from tblproposals, tblplans
	where tblproposals.pln_plan_id = tblplans.plan_id
	and policy_no = m_policy_no;	 
	
		
 
  	for each_row in cur_invsts loop
		
    	invest_year := ceil(each_row.invest_seq/12);
 			age := age + invest_Year - 1;
 			mort_rate := Get_rate(age,duration,cv_class, 'M', m_plan,'N');
 			
 			-- get the extra mortality loading if any
				for exm_row in cur_exm loop
					exit when sql%notfound;
						ex_mort := ex_mort + exm_row.LOAD_PERMILLE;
				end loop;
     	
     	mort_rate := mort_rate * (1+ nvl(ex_mort,0));
     		
  -- Calculate Commissions, Admin fees & Expenses ..
  --================================================
  
    	Get_Loadings (m_plan, 0, invest_year,	each_row.premium_part, m_Comm , m_Admin ,m_expen );
    	
  
  -- Calculate m_portion (the portion of the interest rate to applied
  -- due to the different the first and last due date and end of month investment
  --=============================================================================
  
  
  	select 	max(invest_seq) into temp_number
  	from 		tblinvsts 
  	where  	policy_no = m_policy_no;
  	
		if each_row.invest_seq in( 1, temp_number) then
			 m_portion := months_between(last_day(each_row.due_date), each_row.due_date);
		else
			m_portion := 1;
		end if;
		
    	
  -- Calculate Risk Amount, remain, cash value  ..
  --=================================
  
  	-- Getting the Previous figure of Cash Values if any ..	

  		Begin
  			select cashvalue into m_cv 
  			from tblinvsts
  			where policy_no = m_policy_no
  			and		invest_seq = each_row.invest_seq - 1;
  		exception
  			when no_data_found then
  				m_CV := 0;
  		end;
  		
   -- Getting the Previous figure of side fund if any ...
   	
  		Begin
  			select sf_total into m_SF
  			from tblinvsts
  			where policy_no = m_policy_no
  			and		invest_seq = each_row.invest_seq - 1;
  		exception
  			when no_data_found then
  				m_SF := 0;
  		end;
  		
  		
    		m_SumAtRisk := nvl(si/12,0) - nvl(m_CV,0);
    		m_Risk_Amt := nvl(mort_rate,0) * nvl(m_sumAtRisk,0);
    		
    		
    		m_Remain :=  nvl(each_row.Premium_part,0) 
    								- nvl(m_comm,0) - nvl(m_Admin,0) 
    								- nvl(m_expen,0) - nvl(m_risk_Amt,0);
    		
    		m_CV := (m_CV + m_remain) *(1+ m_portion * m_interest_rate/1200);
				m_SF := (m_SF) *(1+ m_portion * m_interest_rate/1200);
    	
    		m_SumAtRisk := nvl(si,0) - nvl(m_CV,0); 
    		tl_Risk_Amt := nvl(mort_rate,0) * nvl(m_sumAtRisk,0);
    	
 -- Put all in the invsts table
 --==============================   	
    	
    	Update tblinvsts 
    	set 	comm 			= m_comm,
    				Admin 		=  m_Admin,
    				Loadings 	= m_expen,
    				Risk_Amt 	= m_Risk_Amt,
    				Remain 		= m_remain,
    				SumAtRisk = m_SumAtRisk,
    				T_Risk_Amt= tl_Risk_Amt,
    				CashValue = m_CV,
    				SF_total	= m_SF,
    				Status 		= 'I'
    				
    				
    	where	policy_no = each_row.policy_no
    	and		invest_seq = each_row.invest_seq;
		
			

  		
  		
    end loop;	
    	Commit;
    
 Exception
		  	When Others then
		  	   Raise_application_error(-60500, 'calcCV ... Raised .. '|| Sqlerrm);  
		  	   rollback;	            
end calcCV;  
 
 --===============================================================================================================
 --===============================================================================================================
-- CREATED BY SHAWKY FODA
---DATE 30-12-2002
-- THIS FUNCTION will return the loaded premium for a cetain year number 
-- and a certain plan Id 
--  ( for basic plan plan_id = 0) 
FUNCTION GET_LOADED_PREMIUM
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
  	       
        if  proposal_data.PREMIUM_PAY_YEARS < year_no+1 then
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
          
          if RIDER_MATURITY < year_no+1 then 
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
END GET_LOADED_PREMIUM ;
-----------------------------------------------------------------------
FUNCTION GET_ALL_INSTALMENTS(v_USER_ID NUMBER, v_PROPOSAL_ID NUMBER, v_RIDER_ID NUMBER) RETURN installments IS 
		-- CREATED BY SHAWKY FODA
		---DATE 30-12-2002
		-- THIS FUNCTION WILL RETURN ALL INSTALLMETS "including loadings" DUE A CERTAIN RIDER DURING ALL THE POLICY PERIOD.
		-- Note: It was intended to return all the riders installments then I failed 
		-- 			so now it returns the istallments for the input rider_id
	
		M_MATURITY_AGE NUMBER;
		v_alb number;
		ALL_INSTALLMENTS installments; 
		
BEGIN
		SELECT MATURITY_AGE, alb 
		INTO M_MATURITY_AGE, v_alb
		FROM TBLPROPOSALS T
		WHERE T.PROPOSAL_ID = v_PROPOSAL_ID
		AND T.PRDCR_1_USER_ID =v_USER_ID ;

			FOR YEAR_NO IN 0..M_MATURITY_AGE-1 LOOP
				ALL_INSTALLMENTS(YEAR_NO).inst:=TAKAFUL.SPU_investments.GET_LOADED_PREMIUM(YEAR_NO,v_PROPOSAL_ID,v_USER_ID,v_RIDER_ID);
				ALL_INSTALLMENTS(YEAR_NO).age :=YEAR_NO + v_alb;
			END LOOP;
			
		RETURN ALL_INSTALLMENTS;
			
			--------Exception Handler Section-------------------------
			Exception
		
			When others  then 
        dbms_output.put_line('Error in "GET_ALL_INSTALMENTS" Function');
			----------------------------------------------------------
				
END GET_ALL_INSTALMENTS;
------------------------------------
function is_installs_uniform(v_USER_ID NUMBER, v_PROPOSAL_ID NUMBER, v_RIDER_ID number) return boolean is
--- By Shawky Foda
--- Date 31-12-2002  "Happy New year"
--- Purpose : this function is designed to check is the installments of a certain rider including the basic plan
--- are uniformally ditributed along the policy period due to some sort of non uniform loading or not
--- Returns TRUE ---> the installment are uniformally distributed
--          False ---> the installment are nonuniformally distributed due to some sort of non uniform loadings
this_installment installments;
myreturn boolean:= false;
sum_installments number:=0;
counter number:=0;

begin
    this_installment:=get_all_instalments(v_USER_ID , v_PROPOSAL_ID , v_RIDER_ID);
    
    for i in 0..this_installment.count-1 loop
        if this_installment(i).inst <> 0 then 
            sum_installments := sum_installments+ round(this_installment(i).inst);
            counter:=counter+1;
        end if;
    end loop;
    
    if sum_installments = round(this_installment(0).inst) * counter then
        myreturn := true;
    end if;
    
    return myreturn;
end;
procedure get_user_proposal_id_by_Polcy(v_policy number , v_user_id in out number , v_proposal_id in out number ) is
l_proposal_no NUMBER;

BEGIN
SELECT t.proposal_no INTO l_proposal_no FROM tblproposals t 
       WHERE t.policy_no = v_policy;
get_user_id_and_proposal_id (l_proposal_no, v_user_id,v_proposal_id);

END;
--the following procedure is to get the corresponding user_id and proposal_id for a certain proposal_no
procedure get_user_id_and_proposal_id(v_proposal_no number , v_user_id in out number , v_proposal_id in out number ) is
counter number:=0;

begin
     select count(*) into counter from takaful.tblproposals t 
     where t.proposal_no = v_proposal_no;    
    
     IF COUNTER = 1 THEN
       select t.prdcr_1_user_id, t.proposal_id into V_USER_ID, v_proposal_id  
       from takaful.tblproposals t where t.proposal_no = v_proposal_no;
     end if;
     if counter = 0 then
     raise_application_error(-20001 , 'Shawky:- No Such Proposal Number' );
     end if;
     
end;

--- The following function is used to get the total due premium for a certain proposal at a certain year 
--- this total premium included all loadinging and all riders and WOP if exist
function get_total_premium(v_year_no number, v_proposal_no number) return number is
user_id number; 
proposal_id  number;
my_return number:=0;
wop number:=0;
begin

get_user_id_and_proposal_id(v_proposal_no  , user_id  , proposal_id );
my_return:=     get_loaded_premium(v_year_no, proposal_id,user_id,0);
    --- getting all riders loaded premiums asscoiated with this proposal No.
    declare
    cursor assoc_riders_cur is 
       select t.rdrs_rider_id from takaful.tblriders_per_proposals t
       where (t.props_1_proposal_id = proposal_id or t.props_proposal_id = proposal_id )
       and (t.props_1_prdcr_1_user_id = user_id or t.props_prdcr_1_user_id = user_id);

    begin
        for assoc_riders in assoc_riders_cur loop
            my_return := my_return + get_loaded_premium(v_year_no, proposal_id,user_id,assoc_riders.rdrs_rider_id);
        end loop;
    
    end;
    ----------adding WOP premuim-------------
    select t.wop_premium into wop from tblproposals t where t.proposal_no = v_proposal_no;
    my_return:= my_return+wop;
    
return my_return;

end;



END SPU_investments;
/
