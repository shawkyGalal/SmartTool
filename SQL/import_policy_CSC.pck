create or replace package csc.import_policy as
       --procedure import_endts( v_policy_no number ); 
      -- procedure del_policy(v_policy_No number);
       procedure import_policy_from_prod(v_policy_No number , includeClaims char);
       procedure import_policy_claims_from_prod(v_policy_No number );
       PROCEDURE import_Motor_Calim_Objects (v_policy_No number );
end;
/
create or replace package body csc.import_policy as

----------------------------------------------------------------------------------------------  
procedure import_policy_from_prod(v_policy_No number , includeClaims char) is 
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
   
     if  includeClaims = 'Y' then 
       import_policy_claims_from_prod(v_policy_No);
     end if ;
          
      
    end;
  end;
----------------------------Special procedue to import from the endt table where it contains a long data type---
procedure import_policy_claims_from_prod(v_policy_No number ) is
LobCode varchar2(2);
GlobCode varchar2(2);
begin
          select lob_code into LobCode from edge.policy where policy_no = v_policy_no;
          select glob_code into GlobCode from edge.lob where lob_code = LobCode;     
      if ( LobCode in ('CD','DC' , 'CO') )  then
           import_Motor_Calim_Objects(v_policy_No);
      end if;    
end;
---------------------------------------------------------------------------------------------------------------
PROCEDURE import_Motor_Calim_Objects (v_policy_No number ) is
BEGIN
--Motor Claims Objects by AMB 21-03-2005
          
          execute immediate 'alter table csc.motor_claims disable all triggers' ;
          delete from csc.motor_claims  t where t.policy_no= v_policy_No ;
          insert into csc.motor_claims (select *   from csc.motor_claims@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table csc.motor_claims enable all triggers' ;
          
           execute immediate 'alter table csc.claim_details disable all triggers' ;
          delete from csc.claim_details  t where t.clm_policy_no= v_policy_No ;
          insert into csc.claim_details (select *   from csc.claim_details@edget.world pb where pb.clm_policy_no= v_policy_No) ;
          execute immediate 'alter table csc.claim_details enable all triggers' ;
          
          execute immediate 'alter table csc.police_reports disable all triggers' ;
          delete from csc.police_reports  t where t.clm_policy_no= v_policy_No ;
          insert into csc.police_reports (select *   from csc.police_reports@edget.world pb where pb.clm_policy_no= v_policy_No) ;
          execute immediate 'alter table csc.police_reports enable all triggers' ;

          execute immediate 'alter table csc.motor_details disable all triggers' ;
          delete from csc.motor_details  t where t.clm_dtl_policy_no= v_policy_No ;
 				  insert into csc.motor_details
				  (
             MOTOR_DETAIL_NO
             ,REGISTRATION_NO,MAKE                           
             ,MODEL                          
             ,BODY_CODE                      
             ,PURPOSE                        
             ,CREATED_BY                     
             ,CREATION_DATE                  
             ,DATE_OF_MANUFACTURE            
             ,SPEED                          
             ,INTOXICATED                    
             ,TOWING_REQUIRED                
             ,DRIVER_ADDRESS1                
             ,DRIVER_ADDRESS2                
             ,AGENCY_REPAIR                  
             ,TOWING_DESERVED                
             ,DEPRECIATION_BASE_DATE         
             ,ARABIC_DRIVER_ADDRESS1         
             ,ARABIC_DRIVER_ADDRESS2         
             ,ARABIC_DRIVER_NAME             
             ,ISTEMARA_EXPIRY_DATE           
             ,RESERVE                        
             ,RETAIN_SALVAGE_INDICATOR       
             ,RESPONSIBILITY                 
             ,RUKHSA_IMAGE_REFERENCE         
             ,DETAILED_STATUS                
             ,STATUS                         
             ,REPAIR_NEEDED                  
             ,NCCI_WORKSHOP                  
             ,TOTAL_LOSS_INDICATOR           
             ,VEHICLE_WO_CREATED             
             ,DRIVER_DATE_OF_BIRTH           
             ,DRIVER_FAX_NO                  
             ,DRIVER_LICENCE_NO              
             ,DRIVER_NAME                    
             ,DRIVER_NATIONALITY             
             ,ACCUMULATED_AMOUNT_PAID        
             ,ACCUMULATED_EXPENSES_PAID      
             ,DRIVER_TEL_NO                  
             ,EXPENSES_RESERVE               
             ,ISTEMARA_IMAGE_REFERENCE       
             ,LAST_UPDATED_BY                
             ,LAST_UPDATED_DATE              
             ,CLM_DTL_DETAIL_NO              
             ,CLM_DTL_POLICY_NO              
             ,CLM_DTL_CLAIM_NO               
             ,CLM_DTL_COVER_TYPE             
             ,CLM_DTL_BRANCH_CODE            
             ,LANGUAGE                       
             ,DOCUMENTATION_COMPLETE         
             ,IMMEDIATE_PAYMENT              
             ,PROCESS_USER                   
             ,CLM_CEN_BRANCH                 
             ,BANK_NAME                      
             ,BANK_ACCOUNT_NO                
             ,CHEQUE_OR_TRANSFER             
             ,TP_TYPE                        
             ,RUKHSA_TYPE                    
             )
             ( select
              MOTOR_DETAIL_NO
             ,REGISTRATION_NO,MAKE                           
             ,MODEL                          
             ,BODY_CODE                      
             ,PURPOSE                        
             ,CREATED_BY                     
             ,CREATION_DATE                  
             ,DATE_OF_MANUFACTURE            
             ,SPEED                          
             ,INTOXICATED                    
             ,TOWING_REQUIRED                
             ,DRIVER_ADDRESS1                
             ,DRIVER_ADDRESS2                
             ,AGENCY_REPAIR                  
             ,TOWING_DESERVED                
             ,DEPRECIATION_BASE_DATE         
             ,ARABIC_DRIVER_ADDRESS1         
             ,ARABIC_DRIVER_ADDRESS2         
             ,ARABIC_DRIVER_NAME             
             ,ISTEMARA_EXPIRY_DATE           
             ,RESERVE                        
             ,RETAIN_SALVAGE_INDICATOR       
             ,RESPONSIBILITY                 
             ,RUKHSA_IMAGE_REFERENCE         
             ,DETAILED_STATUS                
             ,STATUS                         
             ,REPAIR_NEEDED                  
             ,NCCI_WORKSHOP                  
             ,TOTAL_LOSS_INDICATOR           
             ,VEHICLE_WO_CREATED             
             ,DRIVER_DATE_OF_BIRTH           
             ,DRIVER_FAX_NO                  
             ,DRIVER_LICENCE_NO              
             ,DRIVER_NAME                    
             ,DRIVER_NATIONALITY             
             ,ACCUMULATED_AMOUNT_PAID        
             ,ACCUMULATED_EXPENSES_PAID      
             ,DRIVER_TEL_NO                  
             ,EXPENSES_RESERVE               
             ,ISTEMARA_IMAGE_REFERENCE       
             ,LAST_UPDATED_BY                
             ,LAST_UPDATED_DATE              
             ,CLM_DTL_DETAIL_NO              
             ,CLM_DTL_POLICY_NO              
             ,CLM_DTL_CLAIM_NO               
             ,CLM_DTL_COVER_TYPE             
             ,CLM_DTL_BRANCH_CODE            
             ,LANGUAGE                       
             ,DOCUMENTATION_COMPLETE         
             ,IMMEDIATE_PAYMENT              
             ,PROCESS_USER                   
             ,CLM_CEN_BRANCH                 
             ,BANK_NAME                      
             ,BANK_ACCOUNT_NO                
             ,CHEQUE_OR_TRANSFER             
             ,TP_TYPE                        
             ,RUKHSA_TYPE                    
             from csc.motor_details@edget.world pb where pb.clm_dtl_policy_no= v_policy_No
             );
             execute immediate 'alter table csc.motor_details enable all triggers' ;

          
          execute immediate 'alter table csc.towing_orders disable all triggers' ;
          delete from csc.towing_orders  t where t.clm_dtl_policy_no= v_policy_No ;
          insert into csc.towing_orders (select *   from csc.towing_orders@edget.world pb where pb.clm_dtl_policy_no= v_policy_No) ;
          execute immediate 'alter table csc.towing_orders enable all triggers' ;
          
          execute immediate 'alter table csc.supplier_invoices disable all triggers' ;
          delete from csc.supplier_invoices  t where t.tng_ordr_towing_order_no 
          				in(Select Towing_order_no from towing_orders where clm_dtl_policy_no =  v_policy_No)  ;
          delete from csc.supplier_invoices  t where t.mtr_dtl_motor_detail_no 
          				in(select motor_detail_no from motor_details where clm_dtl_policy_no =  v_policy_No)  ; 				
          insert into csc.supplier_invoices (select * from csc.supplier_invoices@edget.world pb where pb.tng_ordr_towing_order_no in
          	                                       (select towing_order_no from csc.towing_orders@edget.world where clm_dtl_policy_no = v_policy_no));
          insert into csc.supplier_invoices (select * from csc.supplier_invoices@edget.world pb where pb.mtr_dtl_motor_detail_no in
          	                                       (select motor_detail_no from csc.motor_details@edget.world where clm_dtl_policy_no = v_policy_no));	                                       
          execute immediate 'alter table csc.supplier_invoices enable all triggers' ;
          
          execute immediate 'alter table csc.property_details disable all triggers' ;
          delete from csc.property_details  t where t.clm_dtl_policy_no= v_policy_No ;
          insert into csc.property_details (select *   from csc.property_details@edget.world pb where pb.clm_dtl_policy_no= v_policy_No) ;
          execute immediate 'alter table csc.property_details enable all triggers' ;
          
          execute immediate 'alter table csc.human_details disable all triggers' ;
          delete from csc.human_details  t where t.clm_dtl_policy_no= v_policy_No ;
          insert into csc.human_details (select *   from csc.human_details@edget.world pb where pb.clm_dtl_policy_no= v_policy_No) ;
          execute immediate 'alter table csc.human_details enable all triggers' ;
         
          execute immediate 'alter table csc.settlements disable all triggers' ;
          delete from csc.settlements  t where t.policy_no= v_policy_No ;
          insert into csc.settlements (select *   from csc.settlements@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table csc.settlements enable all triggers' ;
          
          execute immediate 'alter table csc.recoveries disable all triggers' ;
          delete from csc.recoveries  t where t.policy_no= v_policy_No ;
          insert into csc.recoveries (select *   from csc.recoveries@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table csc.recoveries enable all triggers' ;
          
          execute immediate 'alter table csc.recovery_settlements disable all triggers' ;
          delete from csc.recovery_settlements  t where t.policy_no= v_policy_No ;
          insert into csc.recovery_settlements (select *   from csc.recovery_settlements@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table csc.recovery_settlements enable all triggers' ;
          
          execute immediate 'alter table csc.CREDIT_NOTES disable all triggers' ;
          delete from csc.CREDIT_NOTES  t where t.stl_settlement_no in (select SETTLEMENT_NO from csc.settlements@edget.world  where policy_no = v_policy_No) ;
          insert into csc.CREDIT_NOTES (select *   from csc.CREDIT_NOTES@edget.world pb 
          where pb.STL_SETTLEMENT_NO in(select pbb.SETTLEMENT_NO from csc.settlements@edget.world pbb where pbb.policy_no = v_policy_No)) ;
          execute immediate 'alter table csc.CREDIT_NOTES enable all triggers' ;
          
          execute immediate 'alter table csc.debit_notes disable all triggers' ;
          delete from csc.debit_notes  t where t.rcvry_stl_settlement_no in (select SETTLEMENT_NO from csc.recovery_settlements@edget.world  where policy_no = v_policy_No) ;
          insert into csc.debit_notes (select *   from csc.debit_notes@edget.world pb 
          where pb.RCVRY_STL_SETTLEMENT_NO in(select pbb.SETTLEMENT_NO from csc.recovery_settlements@edget.world pbb where pbb.policy_no = v_policy_No)) ;
          execute immediate 'alter table csc.debit_notes enable all triggers' ;
          
          execute immediate 'alter table csc.payment_request disable all triggers' ;
          delete from csc.payment_request  t where t.policy_no= v_policy_No ;
          insert into csc.payment_request (select *   from csc.payment_request@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table csc.payment_request enable all triggers' ;
 
        
          --Motor Claims Objects by AMB 21-03-2005 END- 

end ;

end;
/
