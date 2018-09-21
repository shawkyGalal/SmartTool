CREATE OR REPLACE PACKAGE BODY Medical_support IS

-- Created By Shawky Foda----
-- Purpose : To Perform General General Support Processes related to Medical System---

procedure returnClaim2Queue (v_policy_no number, v_claim_no number) as
counter number;
begin
-- Created By Shawky Foda----
-- Purpose--- To Return a Medical Calim to The Queue--
-- Date : 26-04-3-2005----------
   -- Check User Authority-------------
   if user not in ('OS310' ,'OS302' ,'EDGE' ,'OS303' ) then 
        raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
   end if;
   

    --- Checking input Parms -----
    select count(*) into counter
    from taj.claims_submission c
    where c.policy_no = v_policy_no
    and c.payer_claim_no = v_claim_no;
    if counter = 0  then
     raise_application_error(-20001, 'Claim No ' || v_claim_no || ' Not Found for Policy No ' || v_policy_no);
    end if;

    begin
        savepoint;
        update taj.claims_submission t set posted = 'N',claim_status = 'O' 
        where payer_claim_no= v_claim_no 
        and t.policy_no = v_policy_no
        and 1=1 ; 
        
        update Taj.med_res_portal t set t.approval_status = 'A' 
        where  t.claim_no = v_claim_no 
        and t.policy_no = v_policy_no;
        
        
        ----- Delete The Claim from Edge System---- 
        delete from edge.aso_med_cl_break_1 
        where claim_no = v_claim_no 
        and policy_no = v_policy_no; 
        
        delete from edge.cl_treaty_no 
        where claim_no = v_claim_no 
        and policy_no = v_policy_no; 
        
        delete from edge.aso_claim 
        where claim_no = v_claim_no 
        and policy_no = v_policy_no; 
        
        delete from edge.med_cl_break_1 
        where claim_no = v_claim_no 
        and policy_no = v_policy_no; 
        
        delete from edge.claim 
        where claim_no = v_claim_no 
        and policy_no = v_policy_no;
    Exception when others then
      rollback  ;
      raise_application_error(-20001 , 'Unable to returm Claim '||v_claim_no || ' for policy No '|| v_policy_no ||' to Queue Due to The Following Error: ' ||sqlerrm );
    end;
    
end;
------------------------------------------------
procedure delete_batch(v_batch_ref varchar2 ) is 
m_counter number;
m_stl_date date;
begin

   -- Check User Authority-------------
   if user not in ('OS310' ,'OS302' ,'EDGE' ,'OS303' ) then 
        raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
   end if;

  ----Check batch exitance-----
  select count(*) 
  into m_counter
  from edge.claim_batch t 
  where t.batch_ref = v_batch_ref;
  
  if m_counter = 0 then
  raise_application_error(-20001 , 'Batch Not Found' ) ;
  end if;
  ------------------------------------
  select t.stl_date  
  into m_stl_date
  from edge.claim_batch t 
  where t.batch_ref = v_batch_ref;
  
  if m_stl_date is not null then
  raise_application_error(-20001 , 'Batch Already Settled,  Please Unsettle it First' ) ;
  end if;

  begin
     
    savepoint A;
    delete from edge.aso_med_cl_break_1
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = batch_ref);
    
    delete from edge.cl_treaty_no
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = batch_ref);
    
    delete from edge.aso_claim
    where batch_ref = batch_ref;
    
    delete from edge.med_cl_break_1
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = batch_ref);
    
    delete from edge.claim
    where batch_ref = batch_ref;
    Exception when others then
      rollback  ;
      raise_application_error(-20001 , 'Unable to Delete Batch  '|| v_batch_ref||' Due to The Following : ' ||sqlerrm );
  end;

end;


--------------------------------------------------------------------------------------------

procedure delete_Claim(v_cl_no number , v_policy_no number ) is
m_counter number;
begin
     
     -- Check User Authority-------------
     if user not in ('OS310' ,'OS302' ,'EDGE' ,'OS303' ) then 
        raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
     end if;

     ------ Validate---
     select count(*) into m_counter
     from edge.claim t 
     where t.policy_no = v_policy_no
     and t.claim_no = v_cl_no;
     
     if m_counter = 0 then
      raise_application_error (-20001 , 'Claim No ' ||v_cl_no|| ' not Found  for policy no ' || v_policy_no );
     end if;


     begin
     
        savepoint A;
        delete from edge.aso_med_cl_break_1
        where claim_no = v_cl_no  and policy_no = v_policy_no;
    
        delete from edge.cl_treaty_no
        where claim_no = v_cl_no  and policy_no = v_policy_no;
    
        delete from edge.aso_claim
        where claim_no = v_cl_no  and policy_no = v_policy_no;
        
        delete from edge.med_cl_break_1
        where claim_no = v_cl_no  and policy_no = v_policy_no;
        
        delete from edge.claim
        where claim_no = v_cl_no  and policy_no = v_policy_no;
        
        Exception when others then
          rollback  ;
          raise_application_error(-20001 , 'Unable to Delete Claim '|| v_cl_no||' for policy no '|| v_policy_no||' Due to The Following : ' ||sqlerrm );
     end;

end;
--------------------------------------------------------------------------------------------

procedure unsettel_Adj ( v_adj_no number ) is
begin

	raise_application_error(-20001 , 'This Process Not Yet Implemented Please contact ISD');
  -- Check User Authority-------------
  if user not in ('OS310' ,'OS302' ,'EDGE' ,'OS303' ) then 
      raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
  end if;
	null;	 --- Implementation Starts Here-----
	--delete_adj_details(v_adj_no)
end;

--------------------------------------------------------------------------------------------
procedure delete_adj_details (v_adj_no number) is 
-- Purpose : To Delete ajustment details for a given adjustment No.----
--           Some time Adjustment system get stuck , so use this procedure.
m_counter number;
begin

 -- Check User Authority-------------
  if user not in ('OS310' ,'OS302' ,'EDGE' ,'OS303' ) then 
      raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
  end if;
  
   select count(*)
   into m_counter
   from taj.adj_frames t 
   where t.adj_no = v_adj_no;
   if m_counter = 0 then 
      raise_application_error(-20001 , 'Adjustment No '|| v_adj_no ||' Not Found in The System');
   end if;
   ---------Check Records in Arptrx----------
   select count(*)
   into m_counter
   from edge.araptrx x  , taj.adj_credit_notes j
   where j.adj_no = v_adj_no
   and x.trx_no  = j .adj_cr_not_id 
   and x.batch_ref = j.batch_ref 
   and x.doc_type = 'CR'
   and x.trx_date = j.trx_date
   and 1=1;
   if m_counter > 0 then 
      raise_application_error(-20001 , 'Adjustment have been setteled please unsettle first');
   end if;
   
   begin 
      savepoint A;
      delete from taj.adj_clm_dets t 
      where t.adj_no =  v_adj_no;
      
      delete from taj.adj_clms t 
      where t.adj_no = v_adj_no;
   
      delete from taj.adj_credit_notes t 
      where t.adj_no = v_adj_no;  
   
   Exception when others then
      rollback;
      raise_application_error(-20001 , 'Unable to Delete Adjustment Details Due to The Following : ' ||sqlerrm );
   end;
end;
--------------------------------------------------------------------------------------------
procedure Test is

begin
  if user not in ('OS302' ,'EDGE' ,'OS303' ) then 
      raise_application_error(-20001, 'You Are Not Authorized to Perform This Function');
  end if;
  
end;
		  
END;
/
