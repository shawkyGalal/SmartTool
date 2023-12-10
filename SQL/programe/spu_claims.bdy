create or replace package body spu_claims AS

  -- Created by shawky Foda
  -- Date 15-02-2002
  -- Purpose: implementaion of the Claim calculation Module

------Claim Types----------	--------Benefit Categories------
  Maturity_claim NUMBER:=0;	Basic_benefit 	   number :=0;
  PPD_Claim NUMBER:=1;		BPPD_benefit number :=1;
  TPD_Claim NUMBER:=2;		ADB_benefit 	   number :=2;
  ADP_Claim NUMBER:=3;		PPD_Benefit	   number :=3;
  Death_claim NUMBER:=4;	TPD_Benefit	   number :=4;
  Side_fund_claim NUMBER:=5;	FIB_Benefit	   number :=5;
				DTH_Benefit	   number :=6;
-----------------------------------------------------------------------------------------------------
function get_Claim_due_amount(
                                v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                v_claim_id NUMBER,
                                v_claim_date DATE,
                                v_ppd_ratio NUMBER
                                )
                             return number is

    claim_due_amount NUMBER := 0;

 BEGIN

        ----------Start calculating DA-------------
        IF v_claim_id =Maturity_claim THEN
           claim_due_amount := spu_claims.get_maturity_claim_amount(v_policy_no,v_rider_id);
        END IF;

        IF v_claim_id =PPD_CLAIM THEN
           claim_due_amount := spu_claims.get_PPD_claim_amount(v_policy_no,v_rider_id,v_claim_date,v_ppd_ratio);
        END IF;

        IF v_claim_id =TPD_CLAIM THEN
           claim_due_amount := spu_claims.get_TPD_claim_amount(v_policy_no,v_rider_id,v_claim_date);
        END IF;

        IF v_claim_id =ADP_CLAIM THEN
           claim_due_amount := spu_claims.get_ADP_claim_amount(v_policy_no,v_rider_id,v_claim_date);
        END IF;

        IF v_claim_id =Death_CLAIM THEN
           claim_due_amount := spu_claims.get_Death_claim_amount(v_policy_no,v_rider_id,v_claim_date);
        END IF;

        IF v_claim_id =side_fund_claim THEN
           claim_due_amount := spu_claims.get_side_fund_claim_amount(v_policy_no,v_claim_date);
        END IF;


      return claim_due_amount;
 end;
--------------------------------------------------------------------------------
FUNCTION get_Death_claim_amount(v_policy_no NUMBER
                               ,v_rider_id NUMBER
                               ,v_claim_date DATE
                               ) RETURN NUMBER IS
  -- This function is designed to return the claim amount due to policy maturity
  return_value NUMBER:=0;
  l_sum_inssured NUMBER:=0;
  l_cash_value NUMBER :=0;
  l_user_id NUMBER;
  l_proposal_id NUMBER;
  last_invest_seq NUMBER;
  l_maturity NUMBER;
  l_elapsed_period NUMBER;
  l_inception_date DATE;
  rider_maturity_date DATE;
  rider_maturity_in_days NUMBER;
  last_due_date_before_claim DATE;
  period NUMBER;
  l_interest_rate NUMBER:=0;
  L_benefit_category number;

BEGIN
     check_claim_validaty(v_policy_no,v_rider_id,v_claim_date);

     -- getting rider benefit categry----
     if v_rider_id <> 0 then 
     	select benefit_category into L_benefit_category from tblriders where rider_id = v_rider_id;
     else
     	L_benefit_category:= 0;
     end if; 

     ----- if the request for basic plan--------------------
     	if L_benefit_category = Basic_benefit then 
	--IF v_rider_id = 0 THEN
         --------Getting the sum inssured  for the Basic Plan-------------------------------
         SELECT pro.sum_insured INTO l_sum_inssured FROM tblproposals pro WHERE pro.policy_no= v_policy_no;

         ---- Getting the just latest investment seq number before the claim date
         SELECT t.Invest_Seq, t.due_date INTO last_invest_seq, last_due_date_before_claim FROM tblinvsts t
         WHERE t.policy_no =v_policy_no
         AND t.due_date <= v_claim_date
         AND t.due_date > add_months( v_claim_date,-1);

         ---Getting the cash value at the due date  just before (or equal) the claim date
         SELECT inv.cashvalue INTO l_cash_value FROM tblinvsts inv
         WHERE inv.policy_no =v_policy_no
         AND inv.Invest_Seq = last_invest_seq;
         --- period between claim date and due date
         period := v_claim_date - last_due_date_before_claim;

         --- getting investment ratio for the month just before the claim date
         BEGIN
             select t.interest_rate INTO l_interest_rate
             from tblfunhistories t
             WHERE to_date( ('01/'||substr(t.period,1,2)||'/'||substr(t.period,3,6) ),'dd/mm/yyyy') <= v_claim_date
             AND to_date( ('01/'||substr(t.period,1,2)||'/'||substr(t.period,3,6) ),'dd/mm/yyyy') > add_months( v_claim_date,-1);
             EXCEPTION
             WHEN no_data_found THEN
             l_interest_rate := 0;
         END;
         ---adjusting the Cash value for the period between claim date and last due date
         l_cash_value := l_cash_value* (1+period/30)*(1+l_interest_rate/(1200));

         IF l_cash_value > l_sum_inssured THEN
              return_value := l_cash_value;
         ELSE
              return_value := l_sum_inssured;
         END IF;


     END IF;
     if L_benefit_category in (BPPD_benefit ,ADB_benefit ,PPD_Benefit,TPD_Benefit) then 
     --IF v_rider_id IN (700,801,802,803,804) THEN
          RETURN 0;--- all THESE riders have no claim amount upon DEATH
     END IF;

     -----if the request for Decreasing Term riders---------------------
     if L_benefit_category =FIB_Benefit then 	
     --IF v_rider_id IN (805,806) THEN

         ---- getting the proposal_id and user_id of the policy under claim------------
         spu_misc.get_user_proposal_id_by_polcy(v_policy_no, l_user_id,l_proposal_id );
         ---- End of getting the proposal_id and user_id of the policy under claim-----

       ----Getting the sum insured and maturity for the required rider---
       SELECT t.sum_insured, t.maturity  INTO l_sum_inssured, l_maturity
              FROM tblriders_per_proposals t
              WHERE (t.props_1_proposal_id =l_proposal_id OR t.props_proposal_id = l_proposal_id )
                AND (t.props_1_prdcr_1_user_id =l_user_id OR t.props_prdcr_1_user_id = l_user_id )
                AND t.rdrs_rider_id =v_rider_id;

       ---Getting elapsed period of the rider (period from inception date up to claim date)---
       SELECT v_claim_date - t.inception_date , t.inception_date INTO l_elapsed_period, l_inception_date
              FROM tblpolicies t WHERE t.policy_no = v_policy_no;

         --IF l_elapsed_period  < 0 THEN
         --   return_value := 0;
         --   RETURN return_value;
         --END IF;
       ---- getting maturity date of the
       rider_maturity_date :=add_months(l_inception_date , l_maturity*12);
       rider_maturity_in_days := rider_maturity_date - l_inception_date ;

       ---THE CLAIM aMOUNT SHOULD BE THE RIDER SUM INSURED MULIBLIED BY A RATION OF REMAINING PERIOD OF THE POLICY TO THE TOTAL POLICY PERIOD
       return_value := l_sum_inssured * (rider_maturity_in_days - l_elapsed_period) /(rider_maturity_in_days);

     END IF;
     ------End of  if the request for Decreasing Term riders-------------
     --------------------------------------------------------------------------------
       if L_benefit_category = DTH_Benefit then 
       --IF v_rider_id IN (807,808) THEN
       ---- getting the proposal_id and user_id of the policy under claim------------
         spu_misc.get_user_proposal_id_by_polcy(v_policy_no, l_user_id,l_proposal_id );
         ---- End of getting the proposal_id and user_id of the policy under claim-----

       ----Getting the sum insured and maturity for the required rider---
       SELECT t.sum_insured, t.maturity INTO l_sum_inssured, l_maturity
              FROM tblriders_per_proposals t
              WHERE (t.props_1_proposal_id =l_proposal_id OR t.props_proposal_id = l_proposal_id )
                AND (t.props_1_prdcr_1_user_id =l_user_id OR t.props_prdcr_1_user_id = l_user_id )
                AND t.rdrs_rider_id =v_rider_id;
       return_value := l_sum_inssured ;
     END IF;

   RETURN return_value;

   --EXCEPTION
   --WHEN OTHERS THEN
   --raise_application_error(-20100,'Error in the spu_claims.get_death_claim_amount('||v_policy_no||','||v_rider_id||','||v_claim_date||').. Please Contact Eng. Shawky Foda(ISD)' );



END;
-------------------------------------------------------------------------------

--------------------------------------------------------------------------------
FUNCTION get_PPD_claim_amount(   v_policy_no NUMBER,
                                 v_rider_id NUMBER,
                                 v_claim_date DATE,
                                 v_PPD_Ratio NUMBER
                              ) RETURN NUMBER IS
  -- This function is designed to return the claim amount due to PPD (Permanent Partial Disability)
  l_sum_insured NUMBER :=0;
  return_value NUMBER:=0;
  prev_sum_ppd_ratio NUMBER:=0;
  PPD_Exceed_100 EXCEPTION;
  L_benefit_category number;
BEGIN
     check_claim_validaty(v_policy_no,v_rider_id,v_claim_date);

     -- getting rider benefit categry----
     if v_rider_id <> 0 then 
     	select benefit_category into L_benefit_category from tblriders where rider_id = v_rider_id;
     else
     	L_benefit_category:= 0;
     end if;

     if   L_benefit_category  in (Basic_benefit,ADB_benefit ,TPD_Benefit,FIB_Benefit,DTH_Benefit) then 
     --IF v_rider_id IN (0,801,803,804,805,806,807,808) THEN
        return_value:=0;
     END IF;


     if   L_benefit_category = BPPD_benefit then 
     --IF v_rider_id =700 THEN
        ---Rule: DA should be equal to SI with a max. value of 1,500,000 SR if and only if R=100%

        IF v_PPD_Ratio <> 100 THEN
         return_value:=0;
         RETURN return_value;
        END IF;

        IF v_PPD_Ratio = 100 THEN
          SELECT t.sum_insured INTO l_sum_insured FROM tblproposals t WHERE t.policy_no= v_policy_no;
          IF l_sum_insured > 1500000 THEN -- this value should be get from the prefrence table instead of hardcoding
             return_value := 1500000;
          ELSE
              return_value :=l_sum_insured;
          END IF;

        END IF;

     END IF;

     if   L_benefit_category = PPD_Benefit then 
     --IF v_rider_id =802 THEN
        ---Rule: DA should be equal to sum_insured * R
        ---implementation not yet done----
        --1- check previous calims of the same type to the same policy and rider
        SELECT decode(sum(c.ppd_ratio),'',0) INTO prev_sum_ppd_ratio FROM tblclaims c , tblclaim_details cd
               WHERE c.claim_type = PPD_Claim
               AND c.claim_id = cd.claim_id
               AND cd.policy_no= v_policy_no
               AND cd.rider_id= 802;

        prev_sum_ppd_ratio :=prev_sum_ppd_ratio;

        IF v_ppd_ratio>100-prev_sum_ppd_ratio THEN
           --RAISE PPD_Exceed_100;
           raise_application_error(-20200,'PPD Ratio Exceed 100%');
        END IF;

        SELECT r.sum_insured INTO l_sum_insured
          FROM tblriders_per_proposals r , tblproposals pr , tblpolicies po
        	WHERE po.policy_no = v_policy_no
          AND r.rdrs_rider_id=v_rider_id
	        AND po.policy_no =pr.policy_no
          AND pr.proposal_id = r.props_1_proposal_id               -- linking both tables
	        AND pr.prdcr_1_user_id = r.props_1_prdcr_1_user_id;

        return_value:=l_sum_insured * v_ppd_ratio/100;
     END IF;

    RETURN return_value;
END;

--------------------------------------------------------------------------------
FUNCTION get_TPD_claim_amount(   v_policy_no NUMBER,
                                 v_rider_id NUMBER,
                                 v_claim_date DATE
                              ) RETURN NUMBER IS
  return_value NUMBER:=0;
  l_sum_insured NUMBER:=0;
  L_benefit_category number;
BEGIN
  check_claim_validaty(v_policy_no,v_rider_id,v_claim_date);

   -- getting rider benefit categry----
     if v_rider_id <> 0 then 
     	select benefit_category into L_benefit_category from tblriders where rider_id = v_rider_id;
     else
     	L_benefit_category:= 0;
     end if; 


  if L_benefit_category = TPD_Benefit then 
  --IF v_rider_id IN (803,804) THEN

  SELECT r.sum_insured INTO l_sum_insured
          FROM tblriders_per_proposals r , tblproposals pr , tblpolicies po
        	WHERE po.policy_no = v_policy_no
          AND r.rdrs_rider_id=v_rider_id
	        AND po.policy_no =pr.policy_no
          AND pr.proposal_id = r.props_1_proposal_id               -- linking both tables
	        AND pr.prdcr_1_user_id = r.props_1_prdcr_1_user_id;

  return_value:=l_sum_insured;
  END IF;

  RETURN return_value;
END;
-------------------------------------------------------------------
FUNCTION get_ADP_claim_amount( v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                v_claim_date DATE
                             ) RETURN NUMBER IS
  -- This function is designed to return the claim amount due to ADP (Accidental Death Benefit)
  return_value NUMBER:=0;
  l_sum_insured NUMBER ;
  L_benefit_category number;
BEGIN
     --- ACCEDNTAL DEATH PENIFIT IS EXACTLY LIKE DEATH BENIFIT IN ADDITION TO THE BENIFIT OF 801 RIDER
     return_value := GET_DEATH_CLAIM_AMOUNT(V_POLICY_NO , V_RIDER_ID ,V_CLAIM_DATE );

   -- getting rider benefit categry----
     if v_rider_id <> 0 then 
     	select benefit_category into L_benefit_category from tblriders where rider_id = v_rider_id;
     else
     	L_benefit_category:= 0;
     end if; 
     

      if L_benefit_category = ADB_benefit  then 
      --IF v_rider_id =801 THEN
      check_claim_validaty(v_policy_no,v_rider_id,v_claim_date);
      ---Rule : DA = SI
      ---implementation not yet done----
        SELECT r.sum_insured INTO l_sum_insured
          FROM tblriders_per_proposals r , tblproposals pr , tblpolicies po
        	WHERE po.policy_no = v_policy_no
          AND r.rdrs_rider_id=v_rider_id
	        AND po.policy_no =pr.policy_no
          AND pr.proposal_id = r.props_1_proposal_id               -- linking both tables
	        AND pr.prdcr_1_user_id = r.props_1_prdcr_1_user_id;

          return_value := l_sum_insured;
      END IF;

    RETURN return_value;
END;
-------------------------------------------------------------------------------

--------------------------------------------------------------------------------
FUNCTION get_Maturity_claim_amount(
                                  v_policy_no NUMBER,
                                  v_rider_id NUMBER
                                  --,v_claim_date DATE
                                ) RETURN NUMBER IS
  -- This function is designed to return the claim amount due to Death
  return_value NUMBER:=0;
  last_invest_seq NUMBER;
  l_cash_value NUMBER;
  L_benefit_category number;
BEGIN
     -- getting rider benefit categry----
     if v_rider_id <> 0 then 
     	select benefit_category into L_benefit_category from tblriders where rider_id = v_rider_id;
     else
     	L_benefit_category:= 0;
     end if; 
	
     if L_benefit_category <>Basic_benefit  then 
     --IF v_rider_id<>0 THEN
     return_value :=0;-- all riders have no claim amount due to maturity
     END IF;

     IF L_benefit_category = Basic_benefit  THEN 
     --IF v_rider_id=0 THEN
     ---The due amount for this case is the last Cash value
     --- Getting the last cash value-------
     SELECT Max(inv.Invest_Seq) INTO last_invest_seq FROM tblinvsts inv
         WHERE inv.policy_no =v_policy_no  ;
         ---Getting the cash value at the last investment seq number
         SELECT inv.cashvalue INTO l_cash_value FROM tblinvsts inv
         WHERE inv.policy_no =v_policy_no
         AND inv.Invest_Seq = last_invest_seq;
         return_value := l_cash_value;

     END IF;
    RETURN return_value;
END;
-------------------------------------------------------------------------------
FUNCTION get_Side_fund_claim_amount(
                                     v_policy_no NUMBER,
                                     v_claim_date DATE
                                   ) RETURN NUMBER IS
  -- This function is designed to return the side fund claim amount
  return_value NUMBER:=0;
BEGIN
     SELECT max(inv.sf_total) INTO return_value FROM tblinvsts inv
     WHERE inv.policy_no= v_policy_no;

    RETURN return_value;
END;

----------------------------------------------------------------
PROCEDURE check_claim_validaty(
                                v_policy_no NUMBER,
                                v_rider_id NUMBER,
                                --v_claim_id NUMBER, --calim type
                                v_claim_date DATE
                                ) IS

counter NUMBER;
BEGIN
     ---- Check policy existance---
      SELECT COUNT(*) INTO counter
               FROM tblpolicies po
               WHERE po.policy_no = v_policy_no;
      IF counter=0 THEN --- No Such Policy No.

        RAISE no_such_policy;
      END IF;
      -- Check if the rider is included in the policy
      IF v_rider_id <>0 THEN

      	select COUNT(*) INTO counter
          FROM tblriders_per_proposals r , tblproposals pr , tblpolicies po
        	WHERE po.policy_no = v_policy_no
          AND r.rdrs_rider_id=v_rider_id
	        AND po.policy_no =pr.policy_no
          AND pr.proposal_id = r.props_1_proposal_id               -- linking both tables
	        AND pr.prdcr_1_user_id = r.props_1_prdcr_1_user_id;      -- riders and proposals

        IF counter=0 THEN --- Rider is not included in the policy
           RAISE rider_not_included;
        END IF;

         -- Check if the rider is included in the policy and claim date is inside the rider validity period
        select COUNT(*) INTO counter
           FROM tblriders_per_proposals r , tblproposals pr , tblpolicies po
           WHERE po.policy_no = v_policy_no
           AND r.rdrs_rider_id=v_rider_id
    	     AND po.policy_no =pr.policy_no
           AND pr.proposal_id = r.props_1_proposal_id               -- linking both tables
    	     AND pr.prdcr_1_user_id = r.props_1_prdcr_1_user_id
           AND v_claim_date > po.inception_date                -- calim date is between
           AND v_claim_date <= add_months(po.inception_date, r.maturity*12);
        IF counter=0 THEN
           raise_application_error(-20100,'Date Out of Range');
           --RAISE spu_claims.date_out_of_range;
        END IF;

      END IF;

      IF v_rider_id =0 THEN
         SELECT COUNT(po.policy_no) INTO counter
             FROM tblpolicies po , tblproposals pr
             WHERE po.policy_no = v_policy_no
             AND pr.policy_no=po.policy_no
             AND po.inception_date < v_claim_date
             AND pr.maturity_date > v_claim_date;
         IF counter=0 THEN
         raise_application_error(-20100,'Date Out of Range');
         --RAISE spu_claims.date_out_of_range;
         END IF;

      END IF;
END;
-----------------------------------------------------------------
PROCEDURE CHECK_CLAIM_TRANSATION (
                                 V_FROM_STATE NUMBER,
                                 V_TO_STATE NUMBER )
                                 IS
--BY SHAWKY FODA
-- DATE : 11-03-2003
--PURPOSE : TO CHECK A CLAIM STATUS TRANSATION AGAINST THE ALLOWED TRANSATIONS FROM THE TBLCLAIM_STATUS_ALLOW_TRANS TABLE

COUNTER NUMBER;
BEGIN
     SELECT COUNT(*) INTO COUNTER FROM TBLCLAIM_STATUS_ALLOW_TRANS T
              WHERE T.FROM_STATE= V_FROM_STATE
              AND T.TO_STATE = V_TO_STATE;
      IF COUNTER=0 THEN
      --dbms_alert.signal(
         --RAISE INVALID_CLAIM_STATUS_TRANS;
         RAISE_APPLICATION_ERROR(-20200,'INVALID CLAIM STATUS TRANSATION ');
      END IF;

END;

FUNCTION IS_TERMINAL_STATUS(V_STATUS NUMBER) RETURN BOOLEAN IS
COUNTER NUMBER;
TERMINAL_STATUS BOOLEAN ;
BEGIN
    --- THE FOLLOWING SELECT STMT RETURNS COUNT OF POSSIBLE TRANSATIONS FROM THE GIVEN STATUS
    SELECT COUNT(*) INTO COUNTER FROM TBLCLAIM_STATUS_ALLOW_TRANS T
        WHERE T.FROM_STATE=V_STATUS;

    IF COUNTER=0 THEN
        TERMINAL_STATUS := TRUE;
    ELSE
        TERMINAL_STATUS := FALSE;
    END IF;

    RETURN TERMINAL_STATUS;


END;

end spu_claims;
/
