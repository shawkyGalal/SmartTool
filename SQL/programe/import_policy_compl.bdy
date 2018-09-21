create or replace package body import_policy as

----------------------------------------------------------------------------------------------  
procedure import_policy_from_prod(v_policy_No number ) is 
-- Created By Shawky Foda--
-- Date 25-02-2004--
--purpose: To Export all data related to a certain policy fmo production Environment (Edge) 
--          to Testing environment (Edgedev )

 
  begin
          
          execute immediate 'alter table comp.cd_rukhsa disable all triggers' ;
          delete from comp.cd_rukhsa rukh where rukh.policy_no= v_policy_No;
          insert into comp.cd_rukhsa ( select * from comp.cd_rukhsa@edge.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.cd_rukhsa enable all triggers' ;
          
          execute immediate 'alter table comp.rukhsa_pros_rnwl disable all triggers' ;
          delete from comp.rukhsa_pros_rnwl rukh where rukh.plcy_policy_no= v_policy_No ;
          insert into comp.rukhsa_pros_rnwl

/*          (renewal_no, plcy_branch_code, plcy_policy_no, expiry_date, renewal_endorsement_no, last_rnwl_contribution, last_rnwl_duration, 
                                          sugg_cont,sugg_duration, agreed_cont, agreed_duration, total_contribution, last_year_reserve, total_reserve, 
                                          last_year_accumulated_salvage, total_accumulated_salvage, last_year_accumulated_recovery, total_accumulated_recovery, 
                                          last_year_accumulated_amount_p, total_accumulated_amount_paid, last_rnwl_loss_ratio, total_loss_ratio, last_year_no_of_claims, 
                                          total_no_of_claims, last_year_outstanding_amount, total_outstanding_amount, frequency_of_claims, lic_type, rnwl_ex_date1, 
                                          rnwl_ex_date2, rnwl_cust_id, rnwl_source_code, renewal_by, wo_cereated, renewal_terms_defined, renewal_terms_agreed, rnwl_qtn, 
                                          lob_code, x1, x2, x3, x4, x5, created_by, creation_date, last_updated_by, last_update_date, sur_amt)  
*/                                          
                        ( select * from comp.rukhsa_pros_rnwl@edge.world rukh where rukh.plcy_policy_no= v_policy_No );
          execute immediate 'alter table comp.rukhsa_pros_rnwl enable all triggers' ;

          execute immediate 'alter table comp.rukhsa_req_rnwl  disable all triggers' ;
          delete from comp.rukhsa_req_rnwl  rukh where rukh.policy_no= v_policy_No;
          insert into comp.rukhsa_req_rnwl  ( select * from comp.rukhsa_req_rnwl @edge.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.rukhsa_req_rnwl  enable all triggers' ;

          execute immediate 'alter table comp.ruk_renewal_notices  disable all triggers' ;
          delete from comp.ruk_renewal_notices  notes  where notes.policy_no= v_policy_No;
          insert into comp.ruk_renewal_notices  ( select * from comp.ruk_renewal_notices @edge.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.ruk_renewal_notices  enable all triggers' ;          
          
 
  end;
----------------------------Special procedue to import from the endt table where it contains a long data type---
end;
/
