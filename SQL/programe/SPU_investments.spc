CREATE OR REPLACE PACKAGE TAKAFUL.SPU_investments As

TYPE installment IS RECORD(inst number, age number); 
TYPE installments IS TABLE OF installment INDEX BY BINARY_INTEGER;

--=============================================================================
Function Get_Relative_Period(m_Period IN varchar2, Position IN number) return varchar2;
--=============================================================================
procedure Close_Fund(m_fund_Id IN number, m_Period IN varchar2, Interest_Rate IN Number);
--=============================================================================
Function Get_Rate
 ( 			
 				ALB 					IN Number, 
        m_term 				IN Number, 
        m_Class_id 		IN varchar2, 
        Sex_Indicator IN varchar2, 
        Plan_rider_id	IN Number, 
        isRider 			IN varchar2
                  
 ) Return Number ;
--=============================================================================
Procedure calcCV
(
		m_policy_no		IN	Number,
		m_fund_Period	IN	varchar2,
		m_interest_rate	IN Number
) ;
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
);
-----------------------------------------------------------------------------
FUNCTION GET_LOADED_PREMIUM
		(
		year_no 				IN number,
    v_proposal_id     in number,
    v_user_id         in number,
    v_rider_id        in number
		) RETURN NUMBER;
------------------------------------------------------------------------------
FUNCTION GET_ALL_INSTALMENTS
     (v_USER_ID NUMBER, 
      v_PROPOSAL_ID NUMBER, 
      v_RIDER_ID NUMBER
     ) RETURN installments ;
------------------------------------------------------------------------------
--- The following function is used to get the total due premium for a certain proposal at a certain year 
--- this total premium included all loadinging and all riders and WOP if exist

function get_total_premium(v_year_no number,
                           v_proposal_no number) 
          return number;
------------------------------------------------------------------------------
--- By Shawky Foda
--- Date 01-03-2003
--- Purpose : this procedure is desoigned to return user_id and proposal_no for a certain proposal_no
procedure get_user_id_and_proposal_id
          (v_proposal_no number,
           v_user_id in out number,
           v_proposal_id in out number 
           );
----------------------------------------------------------
--- By Shawky Foda
--- Date 01-03-2003
--- Purpose : this procedure is desoigned to return user_id and proposal_no for a certain policy_no
procedure get_user_proposal_id_by_Polcy
                 (v_policy number ,
                  v_user_id in out number ,
                  v_proposal_id in out number 
                  );
-----------------------------------------------------------                  
--- By Shawky Foda
--- Date 31-12-2002  "Happy New year"
--- Purpose : this function is designed to check is the installments of a certain rider including the basic plan
--- are uniformally ditributed along the policy period due to some sort of non uniform loading or not
--- Returns TRUE ---> the installment are uniformally distributed
--          False ---> the installment are nonuniformally distributed due to some sort of non uniform loadings
function is_installs_uniform
      (  v_USER_ID NUMBER, 
         v_PROPOSAL_ID NUMBER,
         v_RIDER_ID number
       ) return boolean; 
--------------------------------------------------------------------------------------------------------

END;
/
