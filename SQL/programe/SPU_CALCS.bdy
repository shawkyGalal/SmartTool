CREATE OR REPLACE PACKAGE BODY TAKAFUL.SPU_CALCS IS
   --===============================================================================================================
 --===============================================================================================================
 Procedure Premiums_Recalc
(
		m_Plan_Id			IN Number,
		ALB						IN Number,
		PolicyTerm		IN Number,
		Sum_Insured		IN Number,
		class_ID			IN Varchar2,
		MOP						IN varchar2,
		Ann_Premium		IN OUT Number ,
		Basic_Premium	IN OUT Number ,
		R_Premium			IN OUT Number 
		
) IS

		Prem_Factor 	Number := 1;
		m_rate_ID			Varchar2(5);
		m_rate_Type		Varchar2(2);
		m_rate				Number :=0;
		m_comm_rate		Number :=0;
		m_admin_rate	Number :=0;
		m_expens_rate	Number :=0;
		r_Comm				Number := 0;
		r_Admin				Number := 0;
		r_Expens			Number := 0;		
		
Begin
	-- Getting the rate id
	Select 	NCCI_RAT_RATE_ID into m_rate_id
	From		tblplans
	where		plan_id = m_plan_Id;
	
	Select rate_type into m_rate_Type
	from 		ncci_rates
	where		rate_Id = m_rate_id;
	
	if upper(m_rate_type) in ('LT', 'DT', 'OC') then
		m_rate := SPU_INVESTMENTS.Get_rate(ALB , policyTerm,  class_id, 'M', m_Plan_id , 'N');		
		Ann_premium := Sum_Insured * m_rate /1000;
		
		Select	comm_rate, Admin_rate, Expense_rate 	into	m_comm_rate, m_admin_rate, m_expens_rate
		from		tblcomm_loadings
		where		Dur = 1
		and			Pln_plan_Id = m_plan_id;	
		
		
		r_comm 		:= Ann_Premium * m_comm_rate / 100;
		r_Admin 	:= Ann_Premium * m_Admin_rate /100;
		r_Expens 	:= Ann_Premium * m_expens_rate /100;
		
		
		Ann_Premium := Ann_Premium + nvl(r_comm,0) + nvl(r_Admin,0) + nvl(r_expens,0);
		Ann_premium := Round(Ann_Premium,0);
		
		If Upper(MOP) = 'A' then
			Ann_Premium 	:= Ann_Premium;
			Basic_Premium := Ann_Premium;
		End if;
		
		If Upper(MOP) = 'S' then
			Basic_Premium		:= 1.02 * Ann_Premium /2;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 2;
		End if;
		
		If Upper(MOP) = 'M' then
			Basic_Premium		:= 1.04 * Ann_Premium /12;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 12;
		End if;
		
		If Upper(MOP) = 'Q' then
			Basic_Premium		:= 1.03 * Ann_Premium /4;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 4;
		End if;

		elsif 	Upper(m_rate_type) = 'RP' then	
			
		If Upper(MOP) = 'A' then
			Ann_Premium 	:= Ann_Premium;
			Basic_Premium := Ann_Premium;
		End if;
		
		If Upper(MOP) = 'S' then
			Basic_Premium		:= Ann_Premium /2;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 2;
		End if;
		
		If Upper(MOP) = 'M' then
			Basic_Premium		:= Ann_Premium /12;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 12;
		End if;
		
		If Upper(MOP) = 'Q' then
			Basic_Premium		:= Ann_Premium /4;
			Basic_Premium		:= Round(Basic_Premium,0);
			Ann_Premium			:= Basic_Premium * 4;
		End if;
	end if;	
Exception
		  	When Others then
		  	   Raise_application_error(-60600, 'Premiums_Recalc ... Raised .. '|| Sqlerrm);  
		  	   rollback;	            
		
end Premiums_Recalc;

--===============================================================================================================
--===============================================================================================================

Procedure RI_Distribution
(
		m_treaty_ID				IN 	Number,
		m_policy_no				IN 	Number,
		m_fund_id					IN  Number,
		m_period					IN 	varchar2,
		m_result 					OUT Boolean,
		m_result_message 	OUT varchar2
			
	
) IS
		m_plan_rider_id		Number;
		m_inst_no					Number;
		m_year_no					Number;
		
		m_QUOTA_SHARE			Number(14,2)	:=0;
		m_QUOTA_AMT_MAX		Number(14,2)	:=0;
		m_SURPLUS_AMT_MAX	Number(14,2)	:=0;
		
		max_treaty_Amt		Number(14,2)	:=0;
		
		m_SUM_AT_RISK			Number(14,2)	:=0;
		m_SI_RET					Number(14,2)	:=0;
		m_SI_QS						Number(14,2)	:=0;
		m_SI_SURPLUS			Number(14,2)	:=0;
		m_SI_FAC					Number(14,2)	:=0;
		m_RISK_PREMIUM		Number(14,2)	:=0;
		m_PRM_RET					Number(14,2)	:=0;
		m_PRM_QS					Number(14,2)	:=0;
		m_PRM_SURPLUS			Number(14,2)	:=0;
		m_PRM_FAC					Number(14,2)	:=0;
		m_OGR_ONR					Number(14,2)	:=0;
		m_RI_COMM					Number(14,2)	:=0;
		
			
	temp_counter 	Number;
	temp_string		Varchar2(500);
	
	fund_exception 	exception;
	instal_exists 	exception;
		
	Cursor riders_curs is
		Select 	b.rider_id m_rider_id, a.sum_insured m_sum_insured, a.Premium m_premium
		from		tblriders_per_proposals a, tblriders b, tblproposals c
		where 	a.RDRS_RIDER_ID = b.rider_id
		and			upper(b.bundled) = 'N'
		and			a.PROPS_1_PROPOSAL_ID = c.proposal_id
		and			a.PROPS_1_PRDCR_1_USER_ID = c.PRDCR_1_USER_ID
		and			c.policy_no = m_policy_no;
--		and			sysdate <=  add_months(c.inception_date, a.maturity * 12);
		
		
		
	Begin

	
Begin
		-- Block2  >> Check if the given policy has records in the Installment table for distribution
			
		select 	count(*) into temp_counter 
		from 		tblinstls
		where  	POLICY_POLICY_NO = m_policy_no
		and			to_char(due_date, 'MMYYYY') = m_period;
		
		if temp_counter <> 1 then
			m_result := false;
			m_result_message := 'The period ' || m_period || '  does not exist in the Installment table';
			raise instal_exists;
		end if;
		exception
			when instal_exists then
					Raise_application_error(-60900,  m_result_message || '  '|| Sqlerrm);
			
			when others then
				Raise_application_error(-60900,  m_result_message || '  '|| Sqlerrm);
end;
		
Begin
	-- Block3 >>  
	-- 1. get the required values from tblinstls , tblinvsts & tblriders_per_proposals
		-- Getting first the Plan type (ie. saving or not saving)
		
			select rate_type into temp_string
			from ncci_rates a, tblplans b, tblproposals c
			where a.rate_id = b.NCCI_RAT_RATE_ID
			and		b.plan_id = c.PLN_PLAN_ID
			and		c.POLICY_NO = m_policy_no;
			
		-- Get the instlment and the year number
			select 	inst_no, YEAR_NO into m_inst_no, m_year_no
			from 		tblinstls
			where 	policy_policy_no = m_policy_no
			and			to_char(due_date, 'MMYYYY') = m_period;
			
		-- Get the Treaty attributes
		
			select 	QUOTA_SHARE, QUOTA_AMT_MAX, SURPLUS_AMT_MAX
			into		m_QUOTA_SHARE, m_QUOTA_AMT_MAX, m_SURPLUS_AMT_MAX
			from		tbltreaties
			where		treaty_id = m_treaty_id;

--  Distribute the basic plan first
--		
			select pln_plan_id into m_plan_rider_id 
			from	 tblproposals
			where	 policy_no = m_policy_no;
			
			
			if upper(temp_string) = 'RP' then -- Saving Plan we will need to consider the investment table
				-- Calculate for the basic plan 
				
					select 	SUMATRISK, T_RISK_AMT into m_sum_at_risk, m_risk_premium
					from 		tblinvsts
					where		policy_no = m_policy_no
					and			invest_period = m_period;
		
			else
					select 	a.sum_insured, b.due_amount into m_sum_at_risk, m_risk_premium
					from 		tblproposals a, tblinstls b
					where		a.policy_no = b.POLICY_POLICY_NO
					and			a.Policy_no = m_policy_no
					and 		to_char(b.due_date, 'MMYYYY') = m_period;
			end if;
		
					
					max_treaty_Amt := m_Quota_Amt_Max * 100/m_Quota_share;
					
					if m_SUM_AT_RISK >  max_treaty_Amt then
						m_SI_RET			:= max_treaty_Amt - m_QUOTA_AMT_MAX;
						m_SI_QS				:= m_QUOTA_AMT_MAX; 
						m_SI_Surplus 	:= m_SUM_AT_RISK - max_treaty_Amt;
						if m_SI_Surplus > m_SURPLUS_AMT_MAX then
							m_SI_FAC		:= m_SI_Surplus - m_SURPLUS_AMT_MAX;
							m_SI_Surplus	:= m_SURPLUS_AMT_MAX;
						end if;
					else
						m_SI_QS				:= m_SUM_AT_RISK * m_Quota_share/100;
						m_SI_Ret			:= m_SUM_AT_RISK * (100 - m_Quota_share)/100;
					end if;
					
					if m_sum_at_risk > 0 then
						
						m_PRM_RET			:= round(m_Risk_Premium * m_SI_RET /m_sum_at_risk,2);
						m_PRM_QS			:= round(m_Risk_Premium * m_SI_QS / m_sum_at_risk, 2);
						m_PRM_Surplus	:= round(m_risk_Premium * m_si_surplus/m_sum_at_risk,2);
						m_PRM_FAC			:= round(m_Risk_Premium * m_SI_FAC/m_sum_at_risk,2);
						
						-- calculating the commission ..
						
					begin
							select count(*) into temp_counter
							from tty_comms
							where TTY_TREATY_NO = m_treaty_ID
							and POLY_DUR = m_year_no
							and PLN_PLAN_ID = m_plan_rider_id;
							
							if temp_counter > 0 then
								select nvl(comm_per, 0) * m_prm_qs /100 into m_ri_comm
								from tty_comms
								where TTY_TREATY_NO = m_treaty_ID
								and POLY_DUR = m_year_no
								and PLN_PLAN_ID = m_plan_rider_id;
							else
								m_ri_comm := 0;
							end if;
							
							exception
								when others then m_ri_comm := 0;							
						
					end;
					
						
					else
						m_sum_at_risk	:= 0;
						m_PRM_RET			:= 0;
						m_PRM_QS			:= 0;
						m_PRM_Surplus	:= 0;
						m_PRM_FAC			:= 0;
						m_ri_comm			:= 0;
						
					end if;
						
-- Start inserting in the ri dist

					Insert into tblridists 
					(
					TREATY_ID, 										-- Value 1
					POLICY_NO,										-- Value 2
					INST_NO,											-- Value 3
					DIST_DATE, 										-- Value 4
					DIST_PERIOD,									-- Value 5
					PLAN_RIDER_ID,								-- Value 6
					SUM_AT_RISK,									-- Value 7
					SI_RET,												-- Value 8
					SI_QS, 												-- Value 9
					SI_SURPLUS,										-- Value 10
					SI_FAC,												-- Value 11
					RISK_PREMIUM, 								-- Value 12
					PRM_RET, 											-- Value 13
					PRM_QS,												-- Value 14
					PRM_SURPLUS, 									-- Value 15
					PRM_FAC,											-- Value 16
					OGR_ONR,											-- Value 17
					RI_COMM,											-- Value 18
					B_R														-- Value 19
					)
					Values
					(
					m_TREATY_ID, 									-- Value 1
					m_POLICY_NO,									-- Value 2
					m_INST_NO,										-- Value 3
					sysdate, 											-- Value 4
					m_PERIOD,											-- Value 5
					m_PLAN_RIDER_ID,							-- Value 6
					m_SUM_AT_RISK,								-- Value 7
					m_SI_RET,											-- Value 8
					m_SI_QS, 											-- Value 9
					m_SI_SURPLUS,									-- Value 10
					m_SI_FAC,											-- Value 11
					m_RISK_PREMIUM, 							-- Value 12
					m_PRM_RET, 										-- Value 13
					m_PRM_QS,											-- Value 14
					m_PRM_SURPLUS, 								-- Value 15
					m_PRM_FAC,										-- Value 16
					m_OGR_ONR,										-- Value 17
					m_RI_COMM,										-- Value 18
					'B'														-- Value 19
					);
					

-- Distributions for the Riders (if any)
	-- Check if there is any non-bundled riders
		
		Select 	count(*) into temp_counter
		from		tblriders_per_proposals a, tblriders b, tblproposals c
		where 	a.RDRS_RIDER_ID = b.rider_id
		and			upper(b.bundled) = 'N'
		and			a.PROPS_1_PROPOSAL_ID = c.proposal_id
		and			a.PROPS_1_PRDCR_1_USER_ID = c.PRDCR_1_USER_ID
		and			c.policy_no = m_policy_no;
				
						
		if temp_counter > 0 then
	--		open riders_curs;
			for eachrec in riders_curs loop
				Exit when riders_curs%notfound;
				m_plan_rider_id := eachrec.m_rider_id;
				m_sum_at_risk := eachrec.m_Sum_insured;
				m_risk_premium := eachrec.m_Premium;
				
				if m_SUM_AT_RISK >  max_treaty_Amt then
						m_SI_RET			:= max_treaty_Amt - m_QUOTA_AMT_MAX;
						m_SI_QS				:= m_QUOTA_AMT_MAX; 
						m_SI_Surplus 	:= m_SUM_AT_RISK - max_treaty_Amt;
						if m_SI_Surplus > m_SURPLUS_AMT_MAX then
							m_SI_FAC		:= m_SI_Surplus - m_SURPLUS_AMT_MAX;
							m_SI_Surplus	:= m_SURPLUS_AMT_MAX;
						end if;
					else
						m_SI_QS				:= m_SUM_AT_RISK * m_Quota_share/100;
						m_SI_Ret			:= m_SUM_AT_RISK * (100 - m_Quota_share)/100;
					end if;
					
					if m_sum_at_risk > 0 then
						
						m_PRM_RET			:= round(m_Risk_Premium * m_SI_RET /m_sum_at_risk,2);
						m_PRM_QS			:= round(m_Risk_Premium * m_SI_QS / m_sum_at_risk, 2);
						m_PRM_Surplus	:= round(m_risk_Premium * m_si_surplus/m_sum_at_risk,2);
						m_PRM_FAC			:= round(m_Risk_Premium * m_SI_FAC/m_sum_at_risk,2);
						
								-- calculating the commission ..
						
					begin
							select count(*) into temp_counter
							from tty_comms
							where TTY_TREATY_NO = m_treaty_ID
							and POLY_DUR = m_year_no
							and RDRS_RIDER_ID = m_plan_rider_id;
							
							if temp_counter > 0 then
								select nvl(comm_per, 0) * m_prm_qs /100 into m_ri_comm
								from tty_comms
								where TTY_TREATY_NO = m_treaty_ID
								and POLY_DUR = m_year_no
								and PLN_PLAN_ID = m_plan_rider_id;
							else
								m_ri_comm := 0;
							end if;
							
							exception
								when others then m_ri_comm := 0;							
						
					end;
						
					else
						m_sum_at_risk	:= 0;
						m_PRM_RET			:= 0;
						m_PRM_QS			:= 0;
						m_PRM_Surplus	:= 0;
						m_PRM_FAC			:= 0;
						
					end if;
						
-- Start inserting in the ri dist

					Insert into tblridists 
					(
					TREATY_ID, 										-- Value 1
					POLICY_NO,										-- Value 2
					INST_NO,											-- Value 3
					DIST_DATE, 										-- Value 4
					DIST_PERIOD,									-- Value 5
					PLAN_RIDER_ID,								-- Value 6
					SUM_AT_RISK,									-- Value 7
					SI_RET,												-- Value 8
					SI_QS, 												-- Value 9
					SI_SURPLUS,										-- Value 10
					SI_FAC,												-- Value 11
					RISK_PREMIUM, 								-- Value 12
					PRM_RET, 											-- Value 13
					PRM_QS,												-- Value 14
					PRM_SURPLUS, 									-- Value 15
					PRM_FAC,											-- Value 16
					OGR_ONR,											-- Value 17
					RI_COMM,											-- Value 18
					B_R														-- Value 19
					)
					Values
					(
					m_TREATY_ID, 									-- Value 1
					m_POLICY_NO,									-- Value 2
					m_INST_NO,										-- Value 3
					sysdate, 											-- Value 4
					m_PERIOD,											-- Value 5
					m_PLAN_RIDER_ID,							-- Value 6
					m_SUM_AT_RISK,								-- Value 7
					m_SI_RET,											-- Value 8
					m_SI_QS, 											-- Value 9
					m_SI_SURPLUS,									-- Value 10
					m_SI_FAC,											-- Value 11
					m_RISK_PREMIUM, 							-- Value 12
					m_PRM_RET, 										-- Value 13
					m_PRM_QS,											-- Value 14
					m_PRM_SURPLUS, 								-- Value 15
					m_PRM_FAC,										-- Value 16
					m_OGR_ONR,										-- Value 17
					m_RI_COMM,										-- Value 18
					'R'														-- Value 19
					);
		
			
			end loop;
		--	Close riders_curs;
				
		end if;
		
		

	commit;
	
		
	exception
		when others then
		m_result := false;
		m_result_message := 'Error 909809 ' || ' Policy ' || m_policy_No 
												|| ' Period ' || m_period	|| '  '														
																|| sqlerrm;
	end;


	exception
	
	
		when others then
		m_result := false;
		m_result_message := 'Error 93210 - ri_distribution Server Procedure says: ' 
													|| 'Policy ' || m_policy_no || ' Period ' || m_period || ' ' 
													|| M_RESULT_MESSAGE || sqlerrm; 
		Raise_application_error(-70100, m_result_message || '  '|| Sqlerrm);
			 								
	End;
	
	--==============================================================================================================
	--==============================================================================================================
	PROCEDURE Total_premium
		(
		propsal_id         IN number,
		user_id         IN number,
		year_no 				IN number,
		sum_premium 		IN OUT number, 
		minus_premium 	IN OUT number, 
		prem_pay_year 	IN NUMBER,
		temp_B_PREMIUM 	IN NUMBER, 
		m_MOP 					IN varchar2, 
		m_ALB 					IN NUMBER, 
		m_sum_insured 	IN NUMBER
		) 
IS

BEGIN
	
	
  declare
  	
  ld_basic 	Number	:=0;
	ld_rider 	Number	:=0;
	ld_tot		Number	:=0;
	r_SI			number :=0; 
	
	cursor 	cur_ld_basic is
		select 	LOAD_AMOUNT, pluse_prm
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= propsal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = user_id
  	and			loading_on = 0
 -- 	and			upper(PLUSE_PRM) = 'Y' 
  	and			load_mech = 'PRM'
   	and			m_ALB + year_no >= LOAD_AGE
  	and			LOAD_AGE - M_ALB + LOAD_DUR > year_no ; 
	
	cursor 	cur_ld_rider is
		select 	loading_on, LOAD_AMOUNT, LOAD_PERMILLE, load_mech
		from 		tblproposal_loadings
		where		PROPS_1_PROPOSAL_ID 		= propsal_id
  	and 		PROPS_1_PRDCR_1_USER_ID = user_id
  	and			loading_on > 0
 -- 	and			upper(PLUSE_PRM) = 'Y' 
  	and			m_ALB + year_no >= LOAD_AGE
  	and			LOAD_AGE - M_ALB + LOAD_DUR > year_no ;  
  	
  	
  	cursor cur_r is
  	 	select MATURITY,PREMIUM, sum_insured, rdrs_rider_id, annualized_premium
      from TBLRIDERS_PER_PROPOSALS
      where PROPS_1_PROPOSAL_ID = propsal_id
      and PROPS_1_PRDCR_1_USER_ID = user_id ; 
      
      
  begin
  	minus_premium := 0;
  	
  	-- getting basic premium loadings
  		for eachrec in cur_ld_basic loop
  			exit when sql%notfound;
  				 	if upper(eachrec.PLUSE_PRM) = 'Y' then
  				 			ld_basic := ld_basic + nvl((eachrec.load_amount * m_sum_insured / 1000),0);
  				 	elsif nvl(upper(eachrec.pluse_prm),'N') = 'N' then
  				 				minus_premium := minus_premium + nvl((eachrec.load_amount * m_sum_insured / 1000),0);
  				 	end if;
  		end loop;
  
  	-- getting riders premium loadings			
  		for eachrec in cur_r loop
  			exit when sql%notfound;	
  				 for r_rec in cur_ld_rider loop
  				 		exit when sql%notfound;
  				 			if eachrec.rdrs_rider_id = r_rec.loading_on then
  				 				if upper(r_rec.load_mech) = 'PRM' then
  				 					
  				 						ld_rider := ld_rider + nvl((r_rec.load_amount * eachrec.sum_insured /1000),0);
  				 				
  				 				elsif upper(r_rec.load_mech) = 'EXM' then -- extra mortaligy
  				 						ld_rider := ld_rider + nvl((eachrec.annualized_premium * (1 + r_rec.LOAD_PERMILLE) - eachrec.annualized_premium),0);
  				 				else
  				 						ld_rider := 0;
  				 				end if;
  				 				
  				 					
  				 						
  				 			end if;
  				 end loop;
  		end loop;
  				 		
  				 	ld_tot := nvl(ld_basic,0) + nvl(ld_rider,0);
  				 	
  				 	if upper(m_MOP) = 'A' then
  				 		ld_tot := ld_tot;
  				 		minus_premium := minus_premium;
  				 	elsif upper(m_MOP) = 'S' then
  				 		ld_tot := ld_tot * 1.02 /2;
  				 		minus_premium := minus_premium * 1.02 /2;
  				 	elsif upper(m_MOP) = 'Q' then
  				 		ld_tot := ld_tot * 1.03 / 4;
  				 		minus_premium := minus_premium * 1.03 / 4;
  				 	elsif upper(m_mop) = 'M' then
  				 		ld_tot := ld_tot * 1.04 /12;
  				 			minus_premium := minus_premium * 1.04 /12;
  				 	end if;
  				 	
  					minus_premium := round(minus_premium,0);
  					ld_tot := round(ld_tot, 0);
  	
  	
  		for eachrec in cur_r loop
  			exit when sql%notfound;
  			if eachrec.MATURITY >= year_no then
  				 sum_premium := sum_premium + eachrec.PREMIUM;
  			end if;
  		end loop;
  		
  		if prem_pay_year >= year_no then
  			sum_premium := sum_premium + temp_B_PREMIUM + ld_tot;
  		else
  			sum_premium := 0;
  		end if;
  		exception
  			when others then
		  	   Raise_application_error(-60603, 'Error1 in Total Premium calculations ... Raised .. '|| Sqlerrm);  
  end;
  exception
  			when others then
		  	   Raise_application_error(-60604, 'Error2 in Total Premium calculations ... Raised .. '|| Sqlerrm);  
END;
------------------------------------------------------------------------------
---- CREATED bY SHAWKY FODA
---- DATE : 11/1/2003
---- fUNCTION : THIS PROCEDURE IS USED TO TRASFEAR ACCOUNTS FROM ONE USER TO ANOTHER ONE 
---- IT RETURNS THE FOLLWING
-----   1= TRANSFEAR IS ok 
-----  -1 NO SUCH Proposal_no 
-----  -2 NO SUCH TO_USER_ID or the to_user_id is not active
-----  -3 unable to audit the transfer action
------ -4 unique constrain violation
-----   0 IS NOT ok for other unknown reason

FUNCTION TRANSFEAR_PROPOSAL
         (
           v_proposal_no number,
           V_TO_USER_ID NUMBER,
           v_comments varchar2
           --, PROPOSALS_TRANSFERED IN OUT NUMBER
          ) RETURN  NUMBER IS

V_FROM_USER_ID NUMBER;
v_proposal_id number;
MY_RESULT NUMBER:=0;
COUNTER NUMBER:=0;
USER_STATUS CHARACTER;
NO_SUCH_Proposal_no exception;
NO_SUCH_TO_USER_ID exception;
--- The follwoing exception is used to detect any unique constrain violation 
--  during changing the user_id in any table
unique_Constrain_violated exception;
pragma exception_init (unique_Constrain_violated, -0001);
audit_failuer exception;

BEGIN

    -- Getting user_id , proposal_id of the proposal_no
    Takaful.spu_investments.get_user_id_and_proposal_id(v_proposal_no, V_FROM_USER_ID, v_proposal_id);
       
    --- CHECK EXISTANCE AND ACTIVE TO_USER_ID
            SELECT COUNT(*) INTO COUNTER FROM TBLPRODUCERS T WHERE T.USER_ID = V_TO_USER_ID;
    IF COUNTER = 1 THEN
      SELECT T.BRO_ACTIVE INTO USER_STATUS FROM TBLPRODUCERS T WHERE T.USER_ID = V_TO_USER_ID;
      IF NOT UPPER(USER_STATUS) = 'Y' THEN 
         RAISE NO_SUCH_TO_USER_ID;
      END IF;
    END if;
    if counter = 0 then     RAISE NO_SUCH_TO_USER_ID; end if;
    
    ----------------------Starting Transfer-------------------------------
    SAVEPOINT ABC;
          
      --1- TBLPROPOSALS---
      UPDATE TAKAFUL.TBLPROPOSALS T 
      SET T.CLNT_1_PRDCR_1_USER_ID = V_TO_USER_ID,
          T.PRDCR_1_USER_ID        = V_TO_USER_ID,
          t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.CLNT_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and t.proposal_no = v_proposal_no;
    
      --2- TBLCLIENTS---
      UPDATE TAKAFUL.TBLCLIENTS T 
      SET T.PRDCR_1_USER_ID     = V_TO_USER_ID, 
           t.prev_user_id       = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PRDCR_1_USER_ID = V_FROM_USER_ID;
    
      --4- RISK_DISTS---
      UPDATE TAKAFUL.RISK_DISTS T 
      SET T.PRDCR_USER_ID = V_TO_USER_ID, 
           t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PRDCR_USER_ID = V_FROM_USER_ID ;
    
      --5- TBLAVACATIONS---
      UPDATE TAKAFUL.TBLAVOCATIONS T 
      SET T.PROPS_1_PRDCR_1_USER_ID = V_TO_USER_ID, 
           t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID 
      and t.props_1_proposal_id = v_proposal_id;
      
    
      --6- TBLDESIONS---
      UPDATE TAKAFUL.TBLDECISIONS T 
      SET T.PROPS_1_PRDCR_1_USER_ID = V_TO_USER_ID,
          T.PROPS_PRDCR_1_USER_ID = V_TO_USER_ID, 
          t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and ( t.props_1_proposal_id = v_proposal_id or t.props_prdcr_1_user_id =v_proposal_id) ;
      

      --7- TBLFAMILY_HISTORY---
      UPDATE TAKAFUL.TBLFAMILY_HISTORIES T 
      SET T.PROPS_1_PRDCR_1_USER_ID = V_TO_USER_ID, 
           t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID 
      and (t.props_1_proposal_id = v_proposal_id); 
    
      --8- TBLIMPAIRMENTS---
      UPDATE TAKAFUL.TBLIMPAIRMENTS T 
      SET T.PROPS_1_PRDCR_1_USER_ID = V_TO_USER_ID,
          T.PROPS_PRDCR_1_USER_ID = V_TO_USER_ID,
          t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and (t.props_1_proposal_id = v_proposal_id or t.props_proposal_id = v_proposal_id );

      ---9- TBLOCCUPATION- 
      UPDATE TAKAFUL.TBLOCCUPATIONS T 
      SET T.PROPS_1_PRDCR_1_USER_ID   = V_TO_USER_ID,
          t.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and t.props_1_proposal_id       = v_proposal_id ;
      
      ---10- TBLPERSONAL_DETAILS- 
      UPDATE TAKAFUL.TBLPERSONAL_DETAILS T 
      SET T.PROPS_1_PRDCR_1_USER_ID   = V_TO_USER_ID,
          T.PROPS_PRDCR_1_USER_ID     = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID 
      and (t.props_1_proposal_id = v_proposal_id or t.props_proposal_id =v_proposal_id) ;
      
      ---11- TBLPROPOSAL_BROKER_COMMISSIONS- 
      UPDATE TAKAFUL.TBLPROPOSAL_BROKER_COMMISSIONS T 
      SET T.PROPS_1_PRDCR_1_USER_ID   = V_TO_USER_ID,
          T.PROPS_PRDCR_1_USER_ID     = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and (t.props_1_proposal_id = v_proposal_no or t.props_proposal_id = v_proposal_no);

      ---12- TBLPROPOSAL_LOADINGS- 
      UPDATE TAKAFUL.TBLPROPOSAL_LOADINGS T 
      SET T.PROPS_1_PRDCR_1_USER_ID = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers      
      WHERE T.PROPS_1_PRDCR_1_USER_ID = V_FROM_USER_ID
      and t.props_1_proposal_id= v_proposal_id;
      
      ---13- Tblproposal_Rider_Commissions- 
      UPDATE TAKAFUL.Tblproposal_Rider_Commissions T 
      SET T.Props_1_Prdcr_1_User_Id = V_TO_USER_ID,
          T.Props_Prdcr_1_User_Id   = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers                      
      WHERE T.Props_1_Prdcr_1_User_Id = V_FROM_USER_ID
      and (t.props_1_proposal_id = v_proposal_id or t.props_proposal_id = v_proposal_id);

      ---14- Tblriders_Per_Proposals- 
      UPDATE TAKAFUL.Tblriders_Per_Proposals T 
      SET T.Props_1_Prdcr_1_User_Id = V_TO_USER_ID,
          T.Props_Prdcr_1_User_Id   = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers          
      WHERE T.Props_1_Prdcr_1_User_Id = V_FROM_USER_ID
      and (t.props_1_proposal_id = v_proposal_id or t.props_proposal_id = v_proposal_id);

      ---15- Tblbenefeciaries- 
      UPDATE TAKAFUL.Tblbenefeciaries T 
      SET T.Props_Prdcr_User_Id = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers      
      WHERE T.Props_Prdcr_User_Id = V_FROM_USER_ID
      and t.props_proposal_id = v_proposal_id;
      
      ---16- Tblpolicies----- 
      UPDATE TAKAFUL.Tblpolicies T 
      SET T.Props_Prdcr_User_Id = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers      
      WHERE T.Props_Prdcr_User_Id = V_FROM_USER_ID
      and t.props_proposal_id = v_proposal_id;
 
      ---17- Tblpersonal_Details_Bnk- 
      UPDATE TAKAFUL.Tblpersonal_Details_Bnk T 
      SET T.User_Id = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers      
      WHERE T.User_Id = V_FROM_USER_ID
      and t.proposal_id = v_proposal_id;
       
      ---18- Tblprojections- 
      UPDATE TAKAFUL.Tblprojections T 
      SET T.User_Id = V_TO_USER_ID,
          T.prev_user_id           = v_from_user_id   ----- This is to Keep track of the proposal transfers      
      WHERE T.User_Id = V_FROM_USER_ID
      and t.proposal_id= v_proposal_id;
       
       ---- Auditing the transfer action------
      -- select takaful.account_trans_seq.nextval into counter from dual;
      insert into takaful.tblproposal_transfers
              (proposal_no, commited_user, transfer_date, from_user,to_user,comments) 
       values (v_proposal_no, user, sysdate, V_FROM_USER_ID , V_TO_USER_ID,v_comments);
      if not sql%found then
            raise audit_failuer;
      end if;
      -----End of auditing the transfer action--
      
      commit;
      MY_RESULT:=1;
      RETURN MY_RESULT;
  
  ------------------Rxception handeler Section------------------
  EXCEPTION 

  
  when NO_SUCH_Proposal_no then
  MY_RESULT:=-1;
  RETURN MY_RESULT;
  
  when no_such_to_user_id then
  MY_RESULT:=-2;
  RETURN MY_RESULT;

  when audit_failuer then
  ROLLBACK TO SAVEPOINT ABC;
  MY_RESULT:=-3;
  RETURN MY_RESULT;
  
  when unique_Constrain_violated then
  ROLLBACK TO SAVEPOINT ABC;
  my_result:=-4;
  RETURN MY_RESULT;
  
  WHEN OTHERS THEN
  ROLLBACK TO SAVEPOINT ABC;
  RETURN MY_RESULT;

END;
	
END SPU_CALCS;
/
