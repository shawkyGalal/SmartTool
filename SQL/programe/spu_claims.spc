create or replace package spu_claims is

  -- Author  : FODA_SH
  -- Created : 11/15/2002 3:12:34 PM
  -- Purpose :
    TYPE C_policy IS RECORD (C_policy_no NUMBER);
  TYPE table_of_numbers IS TABLE OF C_policy;
  date_out_of_range EXCEPTION;
  rider_not_included EXCEPTION;
  no_such_policy EXCEPTION;
  INVALID_CLAIM_STATUS_TRANS EXCEPTION;
  CLIENT_HAVE_NON_TERMINAL_CLAIM EXCEPTION;
-----------------------------------------------------------------------------------------------------

 function get_claim_due_amount(
                                v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                v_claim_id NUMBER,
                                v_claim_date DATE,
                                v_ppd_ratio NUMBER
                                )
                             return NUMBER;
------------------------------------------------------------------------
FUNCTION get_Maturity_claim_amount(v_policy_no NUMBER,v_rider_id NUMBER) RETURN NUMBER;
------------------------------------------------------------------------
FUNCTION get_PPD_claim_amount(   v_policy_no NUMBER,
                                 v_rider_id NUMBER,
                                 v_claim_date DATE,
                                 v_PPD_Ratio NUMBER
                              ) RETURN NUMBER;

-----------------------------------------------------------------------
FUNCTION get_TPD_claim_amount(   v_policy_no NUMBER,
                                 v_rider_id NUMBER,
                                 v_claim_date DATE
                              ) RETURN NUMBER;
----------------------------------------------------------------------
FUNCTION get_ADP_claim_amount( v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                v_claim_date DATE
                             ) RETURN NUMBER;

------------------------------------------------------------------------
FUNCTION get_Death_claim_amount(
                                  v_policy_no NUMBER,
                                  v_rider_id NUMBER,
                                  v_claim_date DATE
                                ) RETURN NUMBER;

------------------------------------------------------------------------
FUNCTION  get_Side_fund_claim_amount( v_policy_no NUMBER,
                             v_claim_date DATE
                           ) RETURN NUMBER;

--------------------------------------------------------------------------
PROCEDURE check_claim_validaty(
                                v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                --v_claim_id NUMBER, --calim type
                                v_claim_date DATE
                                );
-------------------------------------------------------------------------
PROCEDURE CHECK_CLAIM_TRANSATION (
                                 V_FROM_STATE NUMBER,
                                 V_TO_STATE NUMBER );
-----------------------------------------------------------------------------
FUNCTION IS_TERMINAL_STATUS(V_STATUS NUMBER) RETURN BOOLEAN;
end spu_claims;
/
