create or replace package cms_app.import_policy as
       --procedure import_endts( v_policy_no number ); 
       --procedure del_policy(v_policy_No number);
       procedure import_policy_from_prod(v_policy_No number ,  includeClaims char);
       procedure import_policy_claims_from_prod(v_policy_No number );
       --procedure import_Quotation_from_prod(m_Qut_no number , m_branch_code number );
       --procedure import_med_calim_batch(m_batch_ref varchar );
end;
/
CREATE OR REPLACE PACKAGE BODY CMS_APP.import_policy IS
 
procedure import_policy_from_prod(v_policy_No number , includeClaims char ) is 

begin

  if  includeClaims = 'Y' then 
     import_policy_claims_from_prod( v_policy_No ) ;              
  end if;
END; --Import Policy From Prod
  

procedure import_policy_claims_from_prod(v_policy_No number ) is
    LobCode varchar2(2);
    GlobCode varchar2(2);
begin
    select lob_code into LobCode from edge.policy where policy_no = v_policy_no;
    select glob_code into GlobCode from edge.lob where lob_code = LobCode;
    
    if GlobCode <> 'MR' and LobCode <> 'MD' then
          execute immediate 'alter table cms_app.cms_wc_pa_emp disable all triggers' ;
          delete from cms_app.cms_wc_pa_emp  t where t.policy_no= v_policy_No ;
          insert into cms_app.cms_wc_pa_emp (select *   from cms_app.cms_wc_pa_emp@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table cms_app.cms_wc_pa_emp enable all triggers' ;
          
          execute immediate 'alter table cms_app.pending_claims disable all triggers' ;
          delete from cms_app.pending_claims  t where t.policy_no= v_policy_No ;
          insert into cms_app.pending_claims (select *   from cms_app.pending_claims@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table cms_app.pending_claims enable all triggers' ;
     end if;
END;
  
END;
/
