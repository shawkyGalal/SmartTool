CREATE OR REPLACE PACKAGE TAKAFUL.SPU_CALCS AS

--======================================================================================
--======================================================================================
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
		
);


--======================================================================================
--======================================================================================
/*
Function Get_PPY
(			rate_id 			IN Varchar2, 
			ALB 					IN Number, 
			term 					IN Number,
			sum_Insured 	IN Number, 
			Class_id 			IN Number,
			Sex_Indicator	IN Varchar2
) 
Return Number ;

*/

--======================================================================================
--======================================================================================
Procedure RI_Distribution
(
		m_treaty_ID				IN 	Number,
		m_policy_no				IN 	Number,
		m_fund_id					IN	Number,
		m_period					IN 	varchar2,
		m_result 					OUT Boolean,
		m_result_message 	OUT varchar2
				
);

--======================================================================================
--======================================================================================
PROCEDURE Total_premium
(
		propsal_id      IN NUMBER,
		user_id         IN NUMBER,
		year_no 				IN number,
		sum_premium 		IN OUT number, 
		minus_premium 	IN OUT number, 
		prem_pay_year 	IN NUMBER,
		temp_B_PREMIUM 	IN NUMBER, 
		m_MOP 					IN varchar2, 
		m_ALB 					IN NUMBER, 
		m_sum_insured 	IN NUMBER
); 

FUNCTION TRANSFEAR_ACCOUNTS
(
    V_FROM_USER_ID NUMBER,
    V_TO_USER_ID NUMBER,
    v_comments varchar2,
    PROPOSALS_TRANSFERED IN OUT NUMBER
) 
RETURN  NUMBER ;

END SPU_Calcs;
/
