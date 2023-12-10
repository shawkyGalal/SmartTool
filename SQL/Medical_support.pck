CREATE OR REPLACE PACKAGE Medical_support IS

/* Be Carful the same Package found in CSC DB*/

procedure unsettel_Claim ( v_batch_ref varchar2 , v_claim_no number);
procedure unsettel_Batch ( v_batch_ref varchar2 );
procedure unsettel_Adj ( v_adj_no number ) ;
procedure returnBatch2Queue ( v_batch_ref varchar2);
procedure returnClaim2Queue (v_claim_no number , v_policy_no number) ;
procedure delete_batch(v_batch_ref varchar2 ) ;
procedure delete_Claim(v_cl_no number , v_policy_no number );
procedure validate_Claim_Status(v_cl_no number , v_policy_no number);
procedure validate_batch_status(v_batch_ref varchar2);
procedure delete_adj_details (v_adj_no number);
procedure kill_session (session_id number , serialNo number);
procedure export_lookup(tableName varchar2);
procedure export_lookups;
procedure check_user_in_role(v_granted_role varchar2);
procedure Test;
procedure restore_aso_claims;
END;
/
CREATE OR REPLACE PACKAGE BODY Medical_support IS

-- Created By Shawky Foda----
-- Purpose : To Perform General General Support Processes related to Medical System---

procedure returnBatch2Queue ( v_batch_ref varchar2) as
BEGIN
    -- Check User Authority-------------
    check_user_in_role('MED_MGR') ;
    validate_batch_status(v_batch_ref);

    begin
        savepoint A ;

          update taj.claims_submission t set posted = 'N',claim_status = 'O' 
          where t.batch_ref = v_batch_ref
          and 1=1 ; 
          
          update Taj.med_res_portal t set t.approval_status = 'A' 
          where t.batch_ref = v_batch_ref;
          
          delete from edge.aso_med_cl_break_1 t 
          where (t.claim_no , t.policy_no) 
                in ( select cs.payer_claim_no , cs.policy_no  from taj.claims_submission cs where cs.batch_ref = v_batch_ref );
          
          delete from edge.cl_treaty_no t
          where (t.claim_no , t.policy_no) 
                in ( select cs.payer_claim_no , cs.policy_no  from taj.claims_submission cs where cs.batch_ref = v_batch_ref );
  
          
          delete from edge.aso_claim t
          where t.batch_ref = v_batch_ref;
          
          delete from edge.med_cl_break_1 t
          where (t.claim_no , t.policy_no) 
                in ( select cs.payer_claim_no , cs.policy_no  from taj.claims_submission cs where cs.batch_ref = v_batch_ref );
          
          delete from edge.claim t
          where t.batch_ref = v_batch_ref;
        
    Exception when others then
      rollback ;
      raise_application_error(-20001 , 'Unable to returm Batch  '||v_batch_ref || ' to Queue Due to The Following Error: ' ||sqlerrm );
    end;

    
END;
------------------------------------------------------------------------------
procedure returnClaim2Queue ( v_claim_no number, v_policy_no number) as
counter number;
begin
-- Created By Shawky Foda----
-- Purpose--- To Return a Medical Calim to The Queue--
-- Date : 26-04-3-2005----------

     -- Check User Authority-------------
    check_user_in_role('MED_MGR') ;
    validate_Claim_Status(v_claim_no , v_policy_no);

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
m_amount number;
begin

  -- Check User Authority-------------
  check_user_in_role('MED_MGR') ;

  ----Check batch exitance & Status -----
  begin
    select t.amount  
    into m_amount
    from edge.araptrx t 
    where t.batch_ref = v_batch_ref;
    
    if m_amount > 0 then 
       raise_application_error(-20001 , 'Batch Already Settled with amount ('||m_amount||'),  Please Unsettle it First' ) ;
    end if;
    
    Exception when no_data_found then
       raise_application_error(-20001 , 'Batch '||v_batch_ref||' Not Found in The System ' ) ;
  end; 
  
  begin
     
    savepoint A;
    delete from edge.aso_med_cl_break_1
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = v_batch_ref);
    
    delete from edge.cl_treaty_no
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = v_batch_ref);
    
    delete from edge.aso_claim
    where batch_ref = v_batch_ref;
    
    delete from edge.med_cl_break_1
    where (policy_no,claim_no) in (select policy_no,claim_no from edge.claim
    where batch_ref = v_batch_ref);
    
    delete from edge.claim
    where batch_ref = v_batch_ref;
    
    update edge.claim_batch t 
    set t.stl_date = null , t.stl_entry_date = null
    where t.batch_ref = v_batch_ref;
    
    Exception when others then
      rollback  ;
      raise_application_error(-20001 , 'Unable to Delete Batch  '|| v_batch_ref||' Due to The Following : ' ||sqlerrm );
   end;

end;
--------------------------------------------------------------------------------------------
procedure validate_batch_status(v_batch_ref varchar2) is 
m_stl_user_com_id number;
m_amount number;
begin
     ---- Validate Batch status----
     select sum(t.amount)
     into m_amount
     from edge.araptrx t
     where t.batch_ref = v_batch_ref;

     if m_amount >0  then 
       raise_application_error (-20001 , 'Batch Ref. No ' ||v_batch_ref|| '  Have been settled with amount = '||m_amount||', Please Unsettle first');          
     end if;
     
     select t.stl_user_com_id  into m_stl_user_com_id from edge.claim_batch t 
     where t.batch_ref = v_batch_ref;
     
     Exception when no_data_found then
       raise_application_error (-20001 , 'Batch Ref. No  ' ||v_batch_ref|| ' not Found  in The System' );     
     
end;

--------------------------------------------------------------------------------------------
--Purpose : Check if The claim not Yet Setteled..
procedure validate_Claim_status(v_cl_no number , v_policy_no number) is 

m_counter number;
m_status varchar(1);
begin
     ---- Validate claim status----
     select t.status 
     into m_status
     from edge.claim t 
     where t.policy_no = v_policy_no
     and t.claim_no = v_cl_no;

     if m_status ='S' then 
       raise_application_error (-20001 , 'Claim No ' ||v_cl_no|| ' for policy no ' || v_policy_no || ' Have been settled, Please Unsettle first');          
     end if;
     
     Exception when no_data_found then
       raise_application_error (-20001 , 'Claim No ' ||v_cl_no|| ' not Found  for policy no ' || v_policy_no );     
     
end;
--------------------------------------------------------------------------------------------
--purpose : Delete a medical claim from the system
procedure delete_Claim(v_cl_no number , v_policy_no number ) is
m_counter number;
m_status number;
begin
     
     -- Check User Authority-------------
     check_user_in_role('MED_MGR') ; -- only MED_MGR or ISD could 

     validate_Claim_Status(v_cl_no , v_policy_no);
 
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
procedure unsettel_Claim ( v_batch_ref varchar2 , v_claim_no number ) is
begin

	raise_application_error(-20001 , 'This Process Not Yet Implemented Please contact ISD');
  
  ------Check Authorty--------
  check_user_in_role('MED_MGR') ;

	null;	 --- Implementation Starts Here-----
	--delete_adj_details(v_adj_no)
end;

--------------------------------------------------------------------------------------------
procedure unsettel_Batch ( v_batch_ref  varchar2 ) is
begin

	raise_application_error(-20001 , 'This Process Not Yet Implemented Please contact ISD');
  
  ------Check Authorty--------
  check_user_in_role('MED_MGR') ;

	null;	 --- Implementation Starts Here-----
	--delete_adj_details(v_adj_no)
end;

--------------------------------------------------------------------------------------------
procedure unsettel_Adj ( v_adj_no number ) is
begin

	raise_application_error(-20001 , 'This Process Not Yet Implemented Please contact ISD');
  
  ------Check Authorty--------
  check_user_in_role('MED_MGR') ;

	null;	 --- Implementation Starts Here-----
	--delete_adj_details(v_adj_no)
end;

--------------------------------------------------------------------------------------------
procedure delete_adj_details (v_adj_no number) is 
-- Purpose : To Delete ajustment details for a given adjustment No.----
--           Some time Adjustment system get stuck , so use this procedure.
m_counter number;
begin
   ------Check Authorty--------
   check_user_in_role('MED_MGR') ; -- only MED_MGR or ISD could 

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
procedure kill_session (session_id number , serialNo number) is
begin
  ------Check Authorty--------
   check_user_in_role('ISD') ;  -- only ISD should 
   --ALTER SYSTEM KILL SESSION '||session_id||', '||serialNo||'
   execute immediate 'ALTER SYSTEM KILL SESSION '' '||session_id||', '||serialNo||' '' ' ;
end;

--------------------------------------------------------------------------------------------
-- Purpose : Export certain  Lookup table from production to Development
procedure export_lookup(tableName varchar2) is
begin
     ------Check Authorty--------
     check_user_in_role('ISD') ; -- only ISD Could  
     execute immediate  'delete from '||tableName||'@edgedev.world' ;
     execute immediate  'insert into '||tableName||'@edgedev.world (select * from '|| tableName ||')';

end;
--------------------------------------------------------------------------------------------
-- Purpose : Export all Lookup tables from production to Development
procedure export_lookups is
begin
   
     export_lookup ('edge.source');
     export_lookup ('edge.GLOB');
     export_lookup ('edge.lob');
     export_lookup ('edge.supplier');
end;

--------------------------------------------------------------------------------------------
-- Purpose : To Check if the Logged user is a member in the given Role 
procedure check_user_in_role(v_granted_role varchar2) is 
counter number ;
begin
     select count(*)
     into  counter 
     from user_role_privs t
     where t.granted_role = upper(v_granted_role) 
           or t.granted_role = 'ISD'
     ;
     
     if counter = 0 then
       raise_application_error(-20001 , user || ' Is not a Member of DB Role ' ||v_granted_role || ' Please Contact Your Manager/ISD to Add You to This Role ');
     end if;
end;
--------------------------------------------------------------------------------------------
procedure restore_aso_claims is
cursor shaf is select * from shaffat.aso_claim;
inserted number:=0;
failed number := 0;
begin
 for abc in shaf loop
     begin
     insert into edge.aso_claim values abc;
     inserted := inserted +1;
     Exception when others then
     failed := failed +1;
     end;
     
 end loop;
 failed := failed;
end;
--------------------------------------------------------------------------------------------
procedure Test is

begin
     check_user_in_role('MMMMM') ; -- only MED_MGR Could  
 
end;

		  
END;
/
