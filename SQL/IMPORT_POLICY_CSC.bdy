create or replace package body csc.import_policy as

----------------------------------------------------------------------------------------------  
procedure import_policy_from_prod(v_policy_No number ) is 
-- Created By Shawky Foda--
-- Date 25-02-2004--
--purpose: To Export all data related to a certain policy fmo production Environment (Edge) 
--          to Testing environment (Edgedev )
 m_Qut_no number;
  m_cust_id number;
  m_branch_code number;

  begin

    --0 - getting policy key data
    select po.quotation_no, po.cust_id  , po.branch_code
    into m_Qut_no , m_cust_id , m_branch_code
    from policy@edget.world po where po.policy_no = v_policy_No;
--    savepoint bac;
 
    begin
      execute immediate 'alter table CSC.EDGE_client disable all triggers' ;
      delete from CSC.EDGE_client ec where ec.cust_id = to_char(m_cust_id) ;
      insert into CSC.EDGE_client 
             /*(cust_id, source_code, supp_id,  a_name, e_name, a_contact_person, e_contact_person, tel_1, 
                                   tel_2, a_address_1, e_address_1, a_address_2, e_address_2, fax_no, telex_no, salesman_code, credit_limit, credit_period, 
                                   control_acct, brokerage_limit, bus_in_limit, balance, e_remarks, a_remarks, blacklist_flag, n_a_trans, corr_type, branch_code, loc_int_flag, 
                                   sub_loc_code, stmt_of_acct, cust_type, trade_business, pay_appl, groub_code, id_no, supp_yn, ho_loc, title_no, status, business_ocupation, payment_method, 
                                   inserted_by, insert_date, last_updated_by, last_update_date, nationality_code, year_of_birth, vip_flag, do_renewals, email, acc_prem, stl_claims, lobs, policies, 
                                   gsb, segment_code, gender, black_list_reason_code, no_mail, mobile_no) 
               */
                         (select * from CSC.EDGE_client@edget.world ec where ec.cust_id  = to_char(m_cust_id));
      execute immediate 'alter table CSC.EDGE_client enable all triggers' ; 

          
      execute immediate 'alter table csc.prospective_renewals disable all triggers' ;
      delete from csc.prospective_renewals  pr where pr.plcy_policy_no = v_policy_No;
      insert into csc.prospective_renewals ( select * from csc.prospective_renewals@edget.world pr where pr.plcy_policy_no= v_policy_No);
      execute immediate 'alter table csc.prospective_renewals enable all triggers' ;
      
      execute immediate 'alter table csc.rnwl_rukhsa_member disable all triggers' ;           
      delete from csc.rnwl_rukhsa_member t where t.prs_renewal_no in (select p.renewal_no from CSC.PROSPECTIVE_RENEWALS@edget.world  p where p.plcy_policy_no = v_policy_no);
      insert into csc.rnwl_rukhsa_member ( select * from csc.rnwl_rukhsa_member@edget.world t where t.prs_renewal_no in (select p.renewal_no from CSC.PROSPECTIVE_RENEWALS@edget.world  p where p.plcy_policy_no = v_policy_no));
      execute immediate 'alter table csc.rnwl_rukhsa_member enable all triggers' ; 

      execute immediate 'alter table csc.policies disable all triggers' ;           
      delete from csc.policies t where t.policy_no =  v_policy_no;
      insert into csc.policies ( select * from csc.policies@edget.world t where t.policy_no  =  v_policy_no);
      execute immediate 'alter table csc.policies enable all triggers' ; 
      
      execute immediate 'alter table csc.policy_details disable all triggers' ;           
      delete from csc.policy_details t where t.plcy_policy_no =  v_policy_no;
      insert into csc.policy_details ( select * from csc.policy_details@edget.world t where t.plcy_policy_no  =  v_policy_no);
      execute immediate 'alter table csc.policy_details enable all triggers' ; 
      

      
  /*    execute immediate 'alter table csc.edge_client disable all triggers' ;
      delete from csc.edge_client  cl where cl.cust_id = m_cust_id;
      insert into csc.edge_client (cust_id, source_code, supp_id, a_name, e_name, a_contact_person, e_contact_person, 
                                tel_1, tel_2, a_address_1, e_address_1, a_address_2, e_address_2, fax_no, telex_no, salesman_code, 
                                credit_limit, credit_period, control_acct, brokerage_limit, bus_in_limit, balance, e_remarks, a_remarks, 
                                blacklist_flag, n_a_trans, corr_type, branch_code, loc_int_flag, sub_loc_code, stmt_of_acct, cust_type, 
                                trade_business, pay_appl, groub_code, id_no, supp_yn, ho_loc, title_no, status, business_ocupation, payment_method, 
                                inserted_by, insert_date, last_updated_by, last_update_date, nationality_code, year_of_birth, vip_flag, do_renewals, 
                                email, acc_prem, stl_claims, lobs, policies, gsb, segment_code, gender, black_list_reason_code, no_mail, mobile_no)  
                                ( select * from csc.edge_client@edget.world cl where  cl.cust_id = m_cust_id );
      execute immediate 'alter table csc.edge_client enable all triggers' ;*/
          
      
    end;
  end;
----------------------------Special procedue to import from the endt table where it contains a long data type---
end;
/
