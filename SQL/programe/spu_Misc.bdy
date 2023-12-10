create or replace package body spu_Misc AS

  -- Created by shawky Foda
  -- Date 01-03-2003
  -- Purpose: Gerneral Purpose funxtions and procedure

procedure get_user_proposal_id_by_Polcy(v_policy number , v_user_id in out number , v_proposal_id in out number ) is
  l_proposal_no NUMBER;

BEGIN
  SELECT t.proposal_no INTO l_proposal_no FROM tblproposals t
         WHERE t.policy_no = v_policy;
  get_user_id_and_proposal_id (l_proposal_no, v_user_id,v_proposal_id);

END;
----------------------------------------------------------------------------------------------------
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
-----------------------------------------------------------------------------------------------------

PROCEDURE write_to_file IS
abc    utl_file.file_type;
BEGIN

	abc  :=  utl_file.fopen('c:\temp','abc.txt','as');
  /*EXCEPTION 
  WHEN utl_file.invalid_path THEN
  dbms_output.put_line('file not fpouns');
  WHEN utl_file.invalid_mode THEN
  dbms_output.put_line('invalid Mode');*/
  
END;
--------------------------------------------------------------------------------------------------
function get_side_fund_premium( period number, 			-- period of the sf investment
																invest number, 			-- investment ratio during the sf period in % ,
																gross_amount number -- the gross amount of the sf after the period
															) return number is
--- return_value = gross_amount/sum(1+invest)**n
return_value number := 0;
sum1 number:=0; 
L_sf_percent number :=0;
L_sf_amount number :=0;

begin
	-- getting admin fee % for side fund ( deducted only from the first installment ) 
	select sf_percent , sf_amount into L_sf_percent, L_sf_amount from TBL_REFERENCES; 
	if period = 0 then
		raise_application_error(-20500,'Value Should Not be Zero')	;
	end if;
	
	for i in 1..period-1 loop
		sum1:=sum1 + power ( (1+invest) , i );
	end loop;
	-- the first installment, which will stay invested whole the period, will be deducted by L_sf_percent
	sum1:=sum1 + (1-L_sf_percent/100)* power( (1+invest), period );
	
	return_value := gross_amount/sum1;
	
		if return_value * L_sf_percent/100 > L_sf_amount then
		
			--recalculate based on fixed deducted value = L_sf_amount
			sum1:=0;
			for i in 1..period loop
			 	sum1:=sum1 + power ( (1+invest) , i );
			end loop;
			return_value := (gross_amount + L_sf_amount*power( (1+invest), period ) )/sum1;
		
		end if;
	return return_value;
end;



end spu_Misc;


/
