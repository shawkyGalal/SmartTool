create or replace package edge.import_policy as
       procedure import_endts( v_policy_no number ); 
       procedure import_policy_from_prod(v_policy_No number ,  includeClaims char);
       procedure import_policy_claims_from_prod(v_policy_No number );
       procedure import_Quotation_from_prod(m_Qut_no number , m_branch_code number );
       procedure import_med_calim_batch(m_batch_ref varchar );
       procedure import_shop_owner_objects(v_policy_No number ); 
       procedure import_medical_objects(v_policy_No number) ;
       procedure import_Motor_objects(v_policy_No number) ;

       procedure import_csc_objects(v_policy_No number , includeClaims char );
       procedure import_csc_claim_objects(v_policy_No number );
       PROCEDURE import_CSC_Motor_Calim_Objects (v_policy_No number );
       procedure import_comp_objects(v_policy_No number , includeClaims char );

       procedure import_CMS_APP_objects(v_policy_No number , includeClaims char ) ;  
       procedure import_CMS_APP_claim_objects(v_policy_No number );
end;
/
create or replace package body edge.import_policy as

----------------------------------------------------------------------------------------------  
procedure import_policy_from_prod(v_policy_No number , includeClaims char ) is 
-- Created By Shawky Foda--
-- Date 25-02-2004--
--purpose: To Export all data related to a certain policy from production Environment (Edge) 
--          to Testing environment (Edgedev )

  m_Qut_no number;
  m_cust_id number;
  m_branch_code number;
  m_lob_code varchar2(3);
  
  begin

    --0 - Getting policy key data
    select po.quotation_no, po.cust_id  , po.branch_code, po.lob_code
    into m_Qut_no , m_cust_id , m_branch_code , m_lob_code
    from policy@edget.world po where po.policy_no = v_policy_No;

         /**********************************************
          *------Importing Common Objects--------------*
          **********************************************/

          -- Client Table under maintenance, remove the next comment after setling the client table----
          execute immediate 'alter table client disable all triggers' ;
          delete from edge.client c where c.cust_id = to_char(m_cust_id) ;
          insert into client (select * from client@edget.world c where c.cust_id =to_char(m_cust_id) );
          execute immediate 'alter table client enable all triggers' ;         
          
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table Quotation disable all triggers' ;
          delete from Quotation qu where qu.policy_no = v_policy_no or ( qu.quotation_no = m_Qut_no and qu.branch_code = m_branch_code) ;
          insert into Quotation (select * from Quotation@edget.world qu where qu.policy_no = v_policy_no or ( qu.quotation_no = m_Qut_no and qu.branch_code = m_branch_code) );
          execute immediate 'alter table Quotation enable all triggers' ; 
          --------------------------------------------------------------------------------------------------------------          
          execute immediate 'alter table edge.p_a_benf disable all triggers' ;
          delete from edge.p_a_benf lqd where  lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code;
          insert into edge.p_a_benf ae ( select * from p_a_benf@edget.world ae1 where ae1.quotation_no = m_Qut_no and ae1.branch_code = m_branch_code );
          execute immediate 'alter table edge.p_a_benf enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.a_endt disable all triggers' ;
          delete from edge.a_endt ae where ae.policy_no = v_policy_no ;
          insert into edge.a_endt ae ( select * from a_endt@edget.world ae1 where ae1.policy_no = v_policy_no );
          execute immediate 'alter table edge.a_endt enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------          
          execute immediate 'alter table lob_qtn_det disable all triggers' ;
          delete from lob_qtn_det lqd where lqd.policy_no = v_policy_no or ( lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code);
          insert into edge.lob_qtn_det ( select * from lob_qtn_det@edget.world lqd where lqd.policy_no = v_policy_no or ( lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code));
          execute immediate 'alter table lob_qtn_det enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.Risk disable all triggers' ;          
          delete from edge.Risk r where r.policy_no = v_policy_no or ( r.quotation_no = m_Qut_no and r.branch_code = m_branch_code);
          insert into edge.Risk ( select * from risk@edget.world r where r.policy_no = v_policy_no or ( r.quotation_no = m_Qut_no and r.branch_code = m_branch_code));
          execute immediate 'alter table edge.Risk enable all triggers' ;          
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.Risk_dist disable all triggers' ;          
          delete from Risk_dist rd where rd.policy_no = v_policy_no  ;          
          insert into edge.Risk_dist ( select * from risk_dist@edget.world rd where rd.policy_no = v_policy_no   );
          execute immediate 'alter table edge.Risk_dist enable all triggers' ;          
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.mar_certif disable all triggers' ;                              
          delete from edge.mar_certif mc where mc.policy_no = v_policy_No  ;
          insert into edge.mar_certif ( select * from mar_certif@edget.world mc where mc.policy_no = v_policy_No  );
          execute immediate 'alter table edge.mar_certif enable all triggers' ;  
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table EDGE.CERT_INT_DET disable all triggers' ;                              
          delete from EDGE.CERT_INT_DET mc where mc.policy_no = v_policy_No  ;
          insert into EDGE.CERT_INT_DET ( select * from EDGE.CERT_INT_DET@edget.world mc where mc.policy_no = v_policy_No  );
          execute immediate 'alter table EDGE.CERT_INT_DET enable all triggers' ;        
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.int_det disable all triggers' ; 
          delete from edge.int_det  id where id.branch_code = m_branch_code and id.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
          insert into edge.int_det ( select * from int_det@edget.world id where id.branch_code = m_branch_code and id.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) );
          execute immediate 'alter table edge.int_det enable all triggers' ;           
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.Cover_Det disable all triggers' ;
          delete from edge.Cover_Det cd where cd.branch_code = m_branch_code and cd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.Cover_Det ( select * from edge.Cover_Det@edget.world cd where cd.branch_code = m_branch_code and cd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.Cover_Det enable all triggers' ; 
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.peril_det disable all triggers' ;          
          delete from edge.peril_det pd where pd.branch_code = m_branch_code and pd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.peril_det ( select * from edge.peril_det@edget.world pd where pd.branch_code = m_branch_code and pd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.peril_det enable all triggers' ;          
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.source_det disable all triggers' ;                    
          delete from edge.source_det sd where sd.branch_code = m_branch_code and sd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
          insert into edge.source_det ( select * from edge.source_det@edget.world sd where sd.branch_code = m_branch_code and sd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) );
          execute immediate 'alter table edge.source_det enable all triggers' ;                    
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.cond_det disable all triggers' ;                    
          delete from edge.cond_det cd where cd.branch_code = m_branch_code and cd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.cond_det ( select * from edge.cond_det@edget.world cd where cd.branch_code = m_branch_code and cd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual) );
          execute immediate 'alter table edge.cond_det enable all triggers' ;                              
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.co_ins disable all triggers' ;                    
          delete from edge.co_ins  ci where ci.branch_code = m_branch_code and ci.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.co_ins ( select * from edge.co_ins@edget.world ci where ci.branch_code = m_branch_code and ci.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.co_ins enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.exc_det disable all triggers' ;
          delete from edge.exc_det ed where ed.branch_code = m_branch_code and ed.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.exc_det ( select * from edge.exc_det@edget.world ed where ed.branch_code = m_branch_code and ed.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.exc_det enable all triggers' ;          
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.ded_det disable all triggers' ;
          delete from edge.ded_det dd where dd.branch_code = m_branch_code and dd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.ded_det ( select * from edge.ded_det@edget.world dd where dd.branch_code = m_branch_code and dd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.ded_det enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.form_det disable all triggers' ;
          delete from edge.form_det fd where fd.branch_code = m_branch_code and fd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual );
          insert into edge.form_det ( select * from edge.form_det@edget.world fd where fd.branch_code = m_branch_code and fd.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ));
          execute immediate 'alter table edge.form_det enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.policy disable all triggers' ;
          delete from edge.policy  plcy where plcy.policy_no= v_policy_No ;
          insert into edge.policy ( select * from edge.policy@edget.world plcy where plcy.policy_no= v_policy_No );
          execute immediate 'alter table edge.policy enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.instl disable all triggers' ;
          delete from edge.instl  ins where ins.policy_no= v_policy_No ;
          insert into edge.instl ( select * from edge.instl@edget.world ins where ins.policy_no= v_policy_No );
          execute immediate 'alter table edge.instl enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.araptrx disable all triggers' ;
          delete from edge.araptrx  ins where ins.policy_no= v_policy_No ;
          insert into edge.araptrx ( select * from edge.araptrx@edget.world ax where ax.policy_no= v_policy_No );
          execute immediate 'alter table edge.araptrx enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.b_araptrx disable all triggers' ;
          delete from edge.b_araptrx  ins where ins.policy_no= v_policy_No ;
          insert into edge.b_araptrx ( select * from edge.b_araptrx@edget.world ax where ax.policy_no= v_policy_No );
          execute immediate 'alter table edge.b_araptrx enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.prem_register disable all triggers' ;
          delete from edge.prem_register  pr where pr.policy_no= v_policy_No ;
          insert into edge.prem_register ( select * from edge.prem_register@edget.world pr where pr.policy_no= v_policy_No );
          execute immediate 'alter table edge.prem_register enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.endt_register disable all triggers' ;
          delete from edge.endt_register  er where er.policy_no= v_policy_No ;
          insert into edge.endt_register ( select * from edge.endt_register@edget.world er where er.policy_no= v_policy_No );
          execute immediate 'alter table edge.endt_register enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.cert_register disable all triggers' ;
          delete from edge.cert_register  cr where cr.policy_no= v_policy_No ;
          insert into edge.cert_register ( select * from edge.cert_register@edget.world cr where cr.policy_no= v_policy_No );
          execute immediate 'alter table edge.cert_register enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          execute immediate 'alter table edge.prem_bordereau disable all triggers' ;
          delete from edge.prem_bordereau  pb where pb.policy_no= v_policy_No ;
          insert into edge.prem_bordereau ( select * from edge.prem_bordereau@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.prem_bordereau enable all triggers' ;
          --------------------------------------------------------------------------------------------------------------
          -- Upon AbdelMajed Butt Help Desk Request No. 8953
          execute immediate 'alter table edge.pol_endt disable all triggers' ;
          delete from edge.pol_endt  pb where pb.policy_no= v_policy_No ;
          insert into edge.pol_endt ( select * from edge.pol_endt@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.pol_endt enable all triggers' ;
         
          import_endts(v_policy_No);     

          /**************************************************
          *         Importing LOB Specific Objects          *
          ***************************************************/
          if m_lob_code = 'SO' then                                        ----- Importing Shop Owner Objects 
             import_shop_owner_objects(v_policy_No);
          end if;
          if m_lob_code = 'MD' then                                        ----- Importing Medical Objects 
             import_medical_objects(v_policy_No);
          end if;
          if m_lob_code in ( 'CD' , 'DC' , 'CO' , 'TP' , 'DV' , 'OD') then ----- Importing Motor Objects 
             import_Motor_objects(v_policy_no);
          end if;

          /****************************************************
          ----------Importing Claims if required-----         *
          ****************************************************/
          if  includeClaims = 'Y' then 
            import_policy_claims_from_prod( v_policy_No ) ;              
          end if;

          /***************************************************
          ----------Importing other Schemas Objects----------*
          ****************************************************/
          -- taj.import_policy.import_policy_from_prod(v_policy_no);
          csc.import_policy.import_policy_from_prod(v_policy_no , includeClaims);
          comp.import_policy.import_policy_from_prod(v_policy_no);
          cms_app.import_policy.import_policy_from_prod(v_policy_no , includeClaims);
          taj.import_policy.import_policy_from_prod(v_policy_no );
          
          --import_csc_objects(v_policy_no , includeClaims);
          --import_comp_objects(v_policy_no , includeClaims);
          --import_CMS_APP_objects(v_policy_no , includeClaims);

end;
-------------------------------------------------------------------------------------------
procedure import_CMS_APP_objects(v_policy_No number , includeClaims char ) is 
begin
  if  includeClaims = 'Y' then 
     import_CMS_APP_claim_objects( v_policy_No ) ;              
  end if;
end;
-------------------------------------------------------------------------------------------
procedure import_CMS_APP_claim_objects(v_policy_No number ) is
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
-------------------------------------------------------------------------------------------
procedure import_comp_objects(v_policy_No number , includeClaims char ) is 
begin
          
          execute immediate 'alter table comp.cd_rukhsa disable all triggers' ;
          delete from comp.cd_rukhsa rukh where rukh.policy_no= v_policy_No;
          insert into comp.cd_rukhsa ( select * from comp.cd_rukhsa@edget.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.cd_rukhsa enable all triggers' ;
          
          execute immediate 'alter table comp.rukhsa_pros_rnwl disable all triggers' ;
          delete from comp.rukhsa_pros_rnwl rukh where rukh.plcy_policy_no= v_policy_No ;
          insert into comp.rukhsa_pros_rnwl ( select * from comp.rukhsa_pros_rnwl@edget.world rukh where rukh.plcy_policy_no= v_policy_No );
          execute immediate 'alter table comp.rukhsa_pros_rnwl enable all triggers' ;

          execute immediate 'alter table comp.rukhsa_req_rnwl  disable all triggers' ;
          delete from comp.rukhsa_req_rnwl  rukh where rukh.policy_no= v_policy_No;
          insert into comp.rukhsa_req_rnwl  ( select * from comp.rukhsa_req_rnwl @edget.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.rukhsa_req_rnwl  enable all triggers' ;

          execute immediate 'alter table comp.ruk_renewal_notices  disable all triggers' ;
          delete from comp.ruk_renewal_notices  notes  where notes.policy_no= v_policy_No;
          insert into comp.ruk_renewal_notices  ( select * from comp.ruk_renewal_notices @edget.world rukh where rukh.policy_no= v_policy_No);
          execute immediate 'alter table comp.ruk_renewal_notices  enable all triggers' ;          
          

end;
-------------------------------------------------------------------------------------------
procedure import_csc_objects(v_policy_No number , includeClaims char ) is 
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
      insert into CSC.EDGE_client (select * from CSC.EDGE_client@edget.world ec where ec.cust_id  = to_char(m_cust_id));
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
       import_csc_claim_objects(v_policy_No);
     end if ;
   end;

end;
---------------------------------------------------------------------------------
procedure import_csc_claim_objects(v_policy_No number ) is
LobCode varchar2(2);
GlobCode varchar2(2);
begin
          select lob_code into LobCode from edge.policy where policy_no = v_policy_no;
          select glob_code into GlobCode from edge.lob where lob_code = LobCode;     
      if ( LobCode in ( 'CD' , 'DC' , 'CO' , 'TP' , 'DV' , 'OD') )  then
           import_CSC_Motor_Calim_Objects(v_policy_No);
      end if;    
end;
---------------------------------------------------------------------------------
PROCEDURE import_CSC_Motor_Calim_Objects (v_policy_No number ) is
BEGIN
--Motor Claims Objects by AMB 21-03-2005
          
          execute immediate 'alter table csc.motor_claims disable all triggers' ;
          delete from csc.motor_claims  t where t.policy_no= v_policy_No ;
          insert into csc.motor_claims (select * from csc.motor_claims@edget.world pb where pb.policy_no= v_policy_No) ;
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
				      ( MOTOR_DETAIL_NO ,REGISTRATION_NO,MAKE ,MODEL ,BODY_CODE ,PURPOSE ,CREATED_BY 
               ,CREATION_DATE ,DATE_OF_MANUFACTURE  ,SPEED  ,INTOXICATED ,TOWING_REQUIRED  ,DRIVER_ADDRESS1
               ,DRIVER_ADDRESS2 ,AGENCY_REPAIR ,TOWING_DESERVED ,DEPRECIATION_BASE_DATE ,ARABIC_DRIVER_ADDRESS1 ,ARABIC_DRIVER_ADDRESS2
               ,ARABIC_DRIVER_NAME ,ISTEMARA_EXPIRY_DATE ,RESERVE ,RETAIN_SALVAGE_INDICATOR ,RESPONSIBILITY ,RUKHSA_IMAGE_REFERENCE
               ,DETAILED_STATUS ,STATUS,REPAIR_NEEDED ,NCCI_WORKSHOP ,TOTAL_LOSS_INDICATOR ,VEHICLE_WO_CREATED ,DRIVER_DATE_OF_BIRTH
               ,DRIVER_FAX_NO ,DRIVER_LICENCE_NO ,DRIVER_NAME ,DRIVER_NATIONALITY ,ACCUMULATED_AMOUNT_PAID ,ACCUMULATED_EXPENSES_PAID
               ,DRIVER_TEL_NO ,EXPENSES_RESERVE ,ISTEMARA_IMAGE_REFERENCE ,LAST_UPDATED_BY ,LAST_UPDATED_DATE ,CLM_DTL_DETAIL_NO ,CLM_DTL_POLICY_NO              
               ,CLM_DTL_CLAIM_NO ,CLM_DTL_COVER_TYPE ,CLM_DTL_BRANCH_CODE ,LANGUAGE ,DOCUMENTATION_COMPLETE ,IMMEDIATE_PAYMENT ,PROCESS_USER ,CLM_CEN_BRANCH                 
               ,BANK_NAME ,BANK_ACCOUNT_NO  ,CHEQUE_OR_TRANSFER  ,TP_TYPE ,RUKHSA_TYPE )
           (select  MOTOR_DETAIL_NO ,REGISTRATION_NO,MAKE ,MODEL ,BODY_CODE ,PURPOSE ,CREATED_BY 
               ,CREATION_DATE ,DATE_OF_MANUFACTURE  ,SPEED  ,INTOXICATED ,TOWING_REQUIRED  ,DRIVER_ADDRESS1
               ,DRIVER_ADDRESS2 ,AGENCY_REPAIR ,TOWING_DESERVED ,DEPRECIATION_BASE_DATE ,ARABIC_DRIVER_ADDRESS1 ,ARABIC_DRIVER_ADDRESS2
               ,ARABIC_DRIVER_NAME ,ISTEMARA_EXPIRY_DATE ,RESERVE ,RETAIN_SALVAGE_INDICATOR ,RESPONSIBILITY ,RUKHSA_IMAGE_REFERENCE
               ,DETAILED_STATUS ,STATUS,REPAIR_NEEDED ,NCCI_WORKSHOP ,TOTAL_LOSS_INDICATOR ,VEHICLE_WO_CREATED ,DRIVER_DATE_OF_BIRTH
               ,DRIVER_FAX_NO ,DRIVER_LICENCE_NO ,DRIVER_NAME ,DRIVER_NATIONALITY ,ACCUMULATED_AMOUNT_PAID ,ACCUMULATED_EXPENSES_PAID
               ,DRIVER_TEL_NO ,EXPENSES_RESERVE ,ISTEMARA_IMAGE_REFERENCE ,LAST_UPDATED_BY ,LAST_UPDATED_DATE ,CLM_DTL_DETAIL_NO ,CLM_DTL_POLICY_NO              
               ,CLM_DTL_CLAIM_NO ,CLM_DTL_COVER_TYPE ,CLM_DTL_BRANCH_CODE ,LANGUAGE ,DOCUMENTATION_COMPLETE ,IMMEDIATE_PAYMENT ,PROCESS_USER ,CLM_CEN_BRANCH                 
               ,BANK_NAME ,BANK_ACCOUNT_NO  ,CHEQUE_OR_TRANSFER  ,TP_TYPE ,RUKHSA_TYPE             from csc.motor_details@edget.world pb where pb.clm_dtl_policy_no= v_policy_No
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

----------------------------Special procedue to import from the endt table where it contains a long data type---
procedure import_endts( v_policy_no number ) is
m_endt_desc varchar2(1000);
cursor policy_endt_cur is 
     select e.endt_no, e.endt_desc from edge.endt@edget.world e where e.policy_no = v_policy_no ;
begin

    execute immediate 'alter table edge.endt disable all triggers' ;
    delete from  edge.endt e where  e.Policy_No= v_policy_No ;
    insert into edge.endt(branch_code, policy_no, cert_no, endt_no, endt_desc , endt_date, final_flag, inst_update_flag, 
                          endt_type, ttl_si, ttl_cont, dist_status, quotation_no, tabc, toc, twc, approved, approval_user, 
                          rnwl_qtn, elapsed_period, charge_period, ext_period, entry_date, a_endt_desc, a_endt_desc2, a_endt_desc3, a_endt_desc4, 
                          policy_fee_refund, real_entry_date, upd_date, user_id, last_updated_by, last_update_date, created_by, creation_date) 
           ( select branch_code, policy_no, cert_no, endt_no, 'temp_value',endt_date, final_flag, inst_update_flag, 
                          endt_type, ttl_si, ttl_cont, dist_status, quotation_no, tabc, toc, twc, approved, approval_user, 
                          rnwl_qtn, elapsed_period, charge_period, ext_period, entry_date, a_endt_desc, a_endt_desc2, a_endt_desc3, a_endt_desc4, 
                          policy_fee_refund, real_entry_date, upd_date, user_id, last_updated_by, last_update_date, created_by, creation_date 
             from edge.endt@edget.world e where e.Policy_No= v_policy_No );
   
   for abc in policy_endt_cur loop
       begin
         update edge.endt e set e.endt_desc = abc.endt_desc  where e.policy_no = v_policy_no and e.endt_no = abc.endt_no ; 
         exception when others then
         null;
       end;
   end loop;
   execute immediate 'alter table edge.endt enable all triggers' ;          
end;
-----------------------------------------------------------------------------------------------------
PROCEDURE import_Medical_Calim_Objects (v_policy_No number ) is
BEGIN
         execute immediate 'alter table edge.claim_batch disable all triggers' ;
          delete from edge.claim_batch  t where t.policy_no= v_policy_No ;
          insert into edge.claim_batch (batch_ref, branch_code, policy_no, provider_id, entry_date, receive_date, given_date, loss_date, comment1, provider_ref_no, store_location, 
                                                gross_amount, user_com_id, stl_date, stl_entry_date, stl_user_com_id, max_pay_amount, adjuster, last_updated_by, last_update_date, created_by, creation_date, in_out_patient, 
                                                plan_id, auto_flag, txt_file_name, returned_claim, bills_not_received, transfered_claims, rec_amount, returned_claims_net, diff_comp_amt, req_net_amt, 
                                                segment_code, date_of_credit_send, diff_free_txt, reserve_amount  , average_billing  , transfer_batch_ref , max_claim_count  -- , batch_closed 
                                                )
                                              
                              ( select * 
                                from edge.claim_batch@edget.world pb where pb.policy_no= v_policy_No 
                               );
          execute immediate 'alter table edge.claim_batch enable all triggers' ;
          
          execute immediate 'alter table edge.aso_claim disable all triggers' ;
          delete from edge.aso_claim  t where t.policy_no= v_policy_No ;
          insert into edge.aso_claim ( select * from edge.aso_claim@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.aso_claim enable all triggers' ;
          

          execute immediate 'alter table edge.med_cl_break_1 disable all triggers' ;
          delete from edge.med_cl_break_1  t where t.policy_no= v_policy_No ;
          insert into edge.med_cl_break_1 ( select * from edge.med_cl_break_1@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.med_cl_break_1 enable all triggers' ;
          
          execute immediate 'alter table edge.aso_med_cl_break_1 disable all triggers' ;
          delete from edge.aso_med_cl_break_1  t where t.policy_no= v_policy_No ;
          insert into edge.aso_med_cl_break_1 ( select * from edge.aso_med_cl_break_1@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.aso_med_cl_break_1 enable all triggers' ;
END;
---------------------------------------------------------------------------------------------
procedure import_policy_claims_from_prod(v_policy_No number ) is
LobCode varchar2(2);
GlobCode varchar2(2);
begin
      select lob_code into LobCode from edge.policy where policy_no = v_policy_no;
      select glob_code into GlobCode from edge.lob where lob_code = LobCode;
      --Common Objects
          execute immediate 'alter table edge.claim disable all triggers' ;
          delete from edge.claim  t where t.policy_no= v_policy_No ;
          insert into edge.claim    /*       (branch_code, policy_no, claim_no, risk_no, lob_code, cust_id, cert_no, sal_rec_no, int_date, loss_date, si, est_amt, status, acc_amt_paid, acc_sal, 
                                              acc_rec, excess, no_stl, reg_no, tp_reg_no, deprc, deprc_amt, sub_loc_code, loc_code, loss_desc, uw_year, cl_pr_brn, treaty_no, currency_code, ex_rate, perm_pay_flag, t_claim_note_flag, cash_cool_flag, dist_before, last_renew_date, prem_pay_flag, 
                                              user_com_id, e_claimant, loss_code, lst_ren_endt_no, max_endt_no, insured_hospital_pay_flag, in_out_kingdom, in_out_patient, pre_exist_cond, repatriation, business_vacation, days_out_kingdom, days_in_hospital, paid_ded_at_hospital, paid_coins_at_hospital, 
                                              mem_code, payable_amt, coins, ded, refund_disc, med_not_necessary, not_covered, exceeds_policy_limit, admission_date, nurse_code, approver_code, doctor_code, provider_id, provider_name, mnn_pay_flag, nc_pay_flag, epl_pay_flag, np_debit_to_hospital, np_debit_to_customer, 
                                              event_no, r_dist_date, m_dist_date, od_claim_no, batch_ref, fee_amt, cov_discount, not_cov_discount, not_pay_tot, ded_coins_pymnt_flag, epl_discount, u_id, quotation_no, ded_per, coins_per, not_approved_amt, entry_date, upd_date, med_stld_entry_date, close_flag, hand_branch_code, 
                                              occ_code, age, interest_code, rec_status, rec_code, a_loss_date, drvl_no, anti_stl_date, coins_pymnt_flag, last_updated_by, last_update_date, created_by, creation_date, approved, approval_user, approval_date, claim_reserve, adj_reserve, la_appt, oth_exp, claim_type, add_adj_fee_paid, 
                                              acc_oth_exp_paid, acc_clm_amt_paid, acc_adj_fee_paid, claim_blocked, complete_doc, e_fmemo, a_fmemo, date_received, non_paid_ded_at_hospital, non_paid_coins_at_hospital, new_born_baby, delivery_complications, fraud_comment, fraud_check) */
                                              
                                              ( select *
                                              /*branch_code, policy_no, claim_no, risk_no, lob_code, cust_id, cert_no, sal_rec_no, int_date, loss_date, si, est_amt, status, acc_amt_paid, acc_sal, 
                                              acc_rec, excess, no_stl, reg_no, tp_reg_no, deprc, deprc_amt, sub_loc_code, loc_code, loss_desc, uw_year, cl_pr_brn, treaty_no, currency_code, ex_rate, perm_pay_flag, t_claim_note_flag, cash_cool_flag, dist_before, last_renew_date, prem_pay_flag, 
                                              user_com_id, e_claimant, loss_code, lst_ren_endt_no, max_endt_no, insured_hospital_pay_flag, in_out_kingdom, in_out_patient, pre_exist_cond, repatriation, business_vacation, days_out_kingdom, days_in_hospital, paid_ded_at_hospital, paid_coins_at_hospital, 
                                              mem_code, payable_amt, coins, ded, refund_disc, med_not_necessary, not_covered, exceeds_policy_limit, admission_date, nurse_code, approver_code, doctor_code, provider_id, provider_name, mnn_pay_flag, nc_pay_flag, epl_pay_flag, np_debit_to_hospital, np_debit_to_customer, 
                                              event_no, r_dist_date, m_dist_date, od_claim_no, batch_ref, fee_amt, cov_discount, not_cov_discount, not_pay_tot, ded_coins_pymnt_flag, epl_discount, u_id, quotation_no, ded_per, coins_per, not_approved_amt, entry_date, upd_date, med_stld_entry_date, close_flag, hand_branch_code, 
                                              occ_code, age, interest_code, rec_status, rec_code, a_loss_date, drvl_no, anti_stl_date, coins_pymnt_flag, last_updated_by, last_update_date, created_by, creation_date, approved, approval_user, approval_date, claim_reserve, adj_reserve, la_appt, oth_exp, claim_type, add_adj_fee_paid, 
                                              acc_oth_exp_paid, acc_clm_amt_paid, acc_adj_fee_paid, claim_blocked, complete_doc, e_fmemo, a_fmemo, date_received, non_paid_ded_at_hospital, non_paid_coins_at_hospital, new_born_baby, delivery_complications, fraud_comment, fraud_check  */
                                               from edge.claim@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.claim enable all triggers' ;
          
          execute immediate 'alter table edge.cl_treaty_no disable all triggers' ;
          delete from edge.cl_treaty_no  t where t.policy_no= v_policy_No ;
          insert into edge.cl_treaty_no ( select * from edge.cl_treaty_no@edget.world pb where pb.policy_no= v_policy_No );
          execute immediate 'alter table edge.cl_treaty_no enable all triggers' ;

      --Common Objects
        
      --Medical 
          if (LobCode ='MD') then
          import_Medical_Calim_Objects(v_policy_No);
          end if;
           
      --Non Motor Claims Objects by AMB 21-03-2005
        if GlobCode <> 'MR' and LobCode <> 'MD' then  
          execute immediate 'alter table edge.adjuster disable all triggers' ;
          delete from edge.adjuster  t where t.policy_no= v_policy_No ;
          insert into edge.adjuster (select *   from edge.adjuster@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.adjuster enable all triggers' ;
          
          execute immediate 'alter table edge.claim_document disable all triggers' ;
          delete from edge.claim_document  t where t.policy_no= v_policy_No ;
          insert into edge.claim_document (select *   from edge.claim_document@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.claim_document enable all triggers' ;
          
          execute immediate 'alter table edge.claims_perils disable all triggers' ;
          delete from edge.claims_perils  t where t.policy_no= v_policy_No ;
          insert into edge.claims_perils (select *   from edge.claims_perils@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.claims_perils enable all triggers' ;
          
          execute immediate 'alter table edge.claim_desc disable all triggers' ;
          delete from edge.claim_desc  t where t.policy_no= v_policy_No ;
          insert into edge.claim_desc (BRANCH_CODE,POLICY_NO,CLAIM_NO,CREATED_BY,
                           CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE,DESC_TYPE )
                           ( select  BRANCH_CODE,POLICY_NO,CLAIM_NO,CREATED_BY, CREATION_DATE,
                                   LAST_UPDATED_BY,LAST_UPDATE_DATE,DESC_TYPE from edge.claim_desc@edget.world pb 
                              where pb.policy_no= v_policy_No);
          execute immediate 'alter table edge.claim_desc enable all triggers' ;
          
          execute immediate 'alter table edge.stld_claim disable all triggers' ;
          delete from edge.stld_claim  t where t.policy_no= v_policy_No ;
          insert into edge.stld_claim (select *   from edge.stld_claim@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.stld_claim enable all triggers' ;
          
          execute immediate 'alter table edge.os_treaty_dist disable all triggers' ;
          delete from edge.os_treaty_dist  t where t.policy_no= v_policy_No ;
          insert into edge.os_treaty_dist (select *   from edge.os_treaty_dist@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.os_treaty_dist enable all triggers' ;
          
          execute immediate 'alter table edge.stl_treaty_dist disable all triggers' ;
          delete from edge.stl_treaty_dist  t where t.policy_no= v_policy_No ;
          insert into edge.stl_treaty_dist (select *   from edge.stl_treaty_dist@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.stl_treaty_dist enable all triggers' ;
          
          execute immediate 'alter table edge.stld_claim_tty disable all triggers' ;
          delete from edge.stld_claim_tty  t where t.policy_no= v_policy_No ;
          insert into edge.stld_claim_tty (select *   from edge.stld_claim_tty@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.stld_claim_tty enable all triggers' ;
          
          execute immediate 'alter table edge.stl_fac_dist disable all triggers' ;
          delete from edge.stl_fac_dist  t where t.policy_no= v_policy_No ;
          insert into edge.stl_fac_dist (select *   from edge.stl_fac_dist@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.stl_fac_dist enable all triggers' ;
          
          execute immediate 'alter table edge.sal_rec disable all triggers' ;
          delete from edge.sal_rec  t where t.policy_no= v_policy_No ;
          insert into edge.sal_rec (select *   from edge.sal_rec@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.sal_rec enable all triggers' ;
          
          execute immediate 'alter table edge.sal_rec_tty disable all triggers' ;
          delete from edge.sal_rec_tty  t where t.policy_no= v_policy_No ;
          insert into edge.sal_rec_tty (select *   from edge.sal_rec_tty@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.sal_rec_tty enable all triggers' ;
          
          execute immediate 'alter table edge.fac_risk_dist disable all triggers' ;
          delete from edge.fac_risk_dist  t where t.policy_no= v_policy_No ;
          insert into edge.fac_risk_dist(branch_code, policy_no, risk_no, cert_no, lob_code, uw_year, treaty_no, endt_no, dist_no, 
                                         supp_id, dist_date, cont_share, si_share, comm, final_flag, fac_oblig_flag, fac_slip_no, lst_alt_no, alt_no, ri_ref, last_updated_by, 
                                         last_update_date, created_by, creation_date
                                         --, share_per, comm_per ,  tax_per,  tax_agreed
                                         ) 
                      (select *   from edge.fac_risk_dist@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.fac_risk_dist enable all triggers' ;
          
          execute immediate 'alter table edge.cms_fac_ref disable all triggers' ;
          delete from edge.cms_fac_ref  t where t.policy_no= v_policy_No ;
          insert into edge.cms_fac_ref (select *   from edge.cms_fac_ref@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.cms_fac_ref enable all triggers' ;
          
          execute immediate 'alter table edge.claim_approval_history disable all triggers' ;
          delete from edge.claim_approval_history  t where t.policy_no= v_policy_No ;
          insert into edge.claim_approval_history (select *   from edge.claim_approval_history@edget.world pb where pb.policy_no= v_policy_No) ;
          execute immediate 'alter table edge.claim_approval_history enable all triggers' ;
          
        --Non Motor Claims Objects by AMB 21-03-2005
      end if;    
end;
--------------------------------------------------------------------------------------------------
procedure import_Quotation_from_prod(m_Qut_no number , m_branch_code number ) is 
-- Created By Shawky Foda--
-- Date 01-03-2005--
--purpose: To Export all data related to a certain Quotation fmo production Environment (Edge) 
--          to Testing environment (Edgedev )

  m_cust_id number;

  begin

    --0 - getting Quotation key data
    select cust_id 
    into  m_cust_id 
    from Quotation@edget.world q where q.quotation_no = m_Qut_no and q.branch_code = m_branch_code ;

    begin
  
    --2--Copy Policy info to EdgeDev
          --2.0--copying client info
          -- Client Table under maintenance, remove the next comment after setling the client table----
         execute immediate 'alter table client disable all triggers' ;
          delete from edge.client c where c.cust_id = to_char(m_cust_id) ;
          insert into client
                      (select * from client@edget.world c where c.cust_id =to_char(m_cust_id) );
          execute immediate 'alter table client enable all triggers' ;         
          
         
          --2.1--copying sales requests-------
          -- insert into SALES_REQUESTS (select * from SALES_REQUESTS@edget.world sr where sr.policy_policy_no= v_policy_No);
          execute immediate 'alter table Quotation disable all triggers' ;
          delete from Quotation qu where ( qu.quotation_no = m_Qut_no and qu.branch_code = m_branch_code) ;
          insert into Quotation (select * from Quotation@edget.world qu where ( qu.quotation_no = m_Qut_no and qu.branch_code = m_branch_code) );
          execute immediate 'alter table Quotation enable all triggers' ; 
                              
          --2.3--copying quotation Details info ----
          execute immediate 'alter table edge.p_a_benf disable all triggers' ;
          delete from edge.p_a_benf lqd where  lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code;
          insert into edge.p_a_benf ae ( select * from p_a_benf@edget.world ae1 where ae1.quotation_no = m_Qut_no and ae1.branch_code = m_branch_code );
          execute immediate 'alter table edge.p_a_benf enable all triggers' ;
          
             
          execute immediate 'alter table lob_qtn_det disable all triggers' ;
          delete from lob_qtn_det lqd where ( lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code);
          insert into edge.lob_qtn_det ( select * from lob_qtn_det@edget.world lqd where ( lqd.quotation_no = m_Qut_no and lqd.branch_code = m_branch_code));
          execute immediate 'alter table lob_qtn_det enable all triggers' ;

          execute immediate 'alter table edge.Risk disable all triggers' ;          
          delete from edge.Risk r where ( r.quotation_no = m_Qut_no and r.branch_code = m_branch_code);
          insert into edge.Risk ( select * from risk@edget.world r where ( r.quotation_no = m_Qut_no and r.branch_code = m_branch_code));
          execute immediate 'alter table edge.Risk enable all triggers' ;          
          
             
          execute immediate 'alter table edge.fleet disable all triggers' ; 
          delete from edge.fleet f where  f.branch_code = m_branch_code and f.quotation_no  = m_Qut_no  ;
          insert into edge.fleet ( select * from edge.fleet@edget.world f where f.branch_code = m_branch_code and f.quotation_no = m_Qut_no ) ;
          execute immediate 'alter table edge.fleet enable all triggers' ; 
          
          execute immediate 'alter table edge.int_det disable all triggers' ; 
          delete from edge.int_det  id where id.branch_code = m_branch_code and id.quotation_no = m_Qut_no   ;
          insert into edge.int_det ( select * from int_det@edget.world id where id.branch_code = m_branch_code and id.quotation_no  =  m_Qut_no );
          execute immediate 'alter table edge.int_det enable all triggers' ;           


          --   Temproery Disabled 
          execute immediate 'alter table edge.med_mem disable all triggers' ; 
          delete from edge.med_mem mm where mm.branch_code = m_branch_code and mm.quotation_no =  m_Qut_no  ;
          insert into edge.med_mem ( select * from edge.med_mem@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no =  m_Qut_no  ) ;
          execute immediate 'alter table edge.med_mem enable all triggers' ; 
          
          execute immediate 'alter table edge.med_mrb_coins_det disable all triggers' ; 
          delete from edge.med_mrb_coins_det mm where mm.branch_code = m_branch_code and mm.quotation_no  = m_Qut_no  ;
          insert into edge.med_mrb_coins_det ( select * from edge.med_mrb_coins_det@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no = m_Qut_no   ) ;
          execute immediate 'alter table edge.med_mrb_coins_det enable all triggers' ; 

          execute immediate 'alter table edge.med_mrb_ded_det disable all triggers' ; 
          delete from edge.med_mrb_ded_det mm where mm.branch_code = m_branch_code and mm.quotation_no = m_Qut_no ;
          insert into edge.med_mrb_ded_det ( select * from edge.med_mrb_ded_det@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no = m_Qut_no  ) ;
          execute immediate 'alter table edge.med_mrb_ded_det enable all triggers' ; 
          
          execute immediate 'alter table edge.Med_Class_Qtn disable all triggers' ; 
          delete from edge.Med_Class_Qtn  mcq where mcq.branch_code = m_branch_code and mcq.quotation_no = m_Qut_no ; 
          insert into edge.Med_Class_Qtn ( select * from edge.Med_Class_Qtn@edget.world mcq where mcq.branch_code = m_branch_code and mcq.quotation_no = m_Qut_no ); 
          execute immediate 'alter table edge.Med_Class_Qtn enable all triggers' ; 
           
          execute immediate 'alter table edge.Cover_Det disable all triggers' ;
          delete from edge.Cover_Det cd where cd.branch_code = m_branch_code and cd.quotation_no = m_Qut_no ;
          insert into edge.Cover_Det ( select * from edge.Cover_Det@edget.world cd where cd.branch_code = m_branch_code and cd.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.Cover_Det enable all triggers' ; 

          execute immediate 'alter table edge.peril_det disable all triggers' ;          
          delete from edge.peril_det pd where pd.branch_code = m_branch_code and pd.quotation_no = m_Qut_no ;
          insert into edge.peril_det ( select * from edge.peril_det@edget.world pd where pd.branch_code = m_branch_code and pd.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.peril_det enable all triggers' ;          
          
          execute immediate 'alter table edge.source_det disable all triggers' ;                    
          delete from edge.source_det sd where sd.branch_code = m_branch_code and sd.quotation_no = m_Qut_no  ;
          insert into edge.source_det ( select * from edge.source_det@edget.world sd where sd.branch_code = m_branch_code and sd.quotation_no = m_Qut_no  );
          execute immediate 'alter table edge.source_det enable all triggers' ;                    
                    
          execute immediate 'alter table edge.cond_det disable all triggers' ;                    
          delete from edge.cond_det cd where cd.branch_code = m_branch_code and cd.quotation_no = m_Qut_no ;
          insert into edge.cond_det ( select * from edge.cond_det@edget.world cd where cd.branch_code = m_branch_code and cd.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.cond_det enable all triggers' ;                              
          
          execute immediate 'alter table edge.co_ins disable all triggers' ;                    
          delete from edge.co_ins  ci where ci.branch_code = m_branch_code and ci.quotation_no = m_Qut_no ;
          insert into edge.co_ins ( select * from edge.co_ins@edget.world ci where ci.branch_code = m_branch_code and ci.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.co_ins enable all triggers' ;
                                        
          execute immediate 'alter table edge.exc_det disable all triggers' ;
          delete from edge.exc_det ed where ed.branch_code = m_branch_code and ed.quotation_no = m_Qut_no ;
          insert into edge.exc_det ( select * from edge.exc_det@edget.world ed where ed.branch_code = m_branch_code and ed.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.exc_det enable all triggers' ;          
          
          execute immediate 'alter table edge.ded_det disable all triggers' ;
          delete from edge.ded_det dd where dd.branch_code = m_branch_code and dd.quotation_no = m_Qut_no ;
          insert into edge.ded_det ( select * from edge.ded_det@edget.world dd where dd.branch_code = m_branch_code and dd.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.ded_det enable all triggers' ;

          execute immediate 'alter table edge.form_det disable all triggers' ;
          delete from edge.form_det fd where fd.branch_code = m_branch_code and fd.quotation_no = m_Qut_no ;
          insert into edge.form_det ( select * from edge.form_det@edget.world fd where fd.branch_code = m_branch_code and fd.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.form_det enable all triggers' ;

             
          
          execute immediate 'alter table edge.rukhsa_member disable all triggers' ;           
          delete from edge.rukhsa_member rm where rm.branch_code = m_branch_code and rm.quotation_no = m_Qut_no  ;
          insert into edge.rukhsa_member ( select * from edge.rukhsa_member@edget.world rm where rm.branch_code = m_branch_code and rm.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.rukhsa_member enable all triggers' ; 
 
          execute immediate 'alter table edge.contr_quot disable all triggers' ;           
          delete from edge.contr_quot rm where rm.branch_code = m_branch_code and rm.quotation_no = m_Qut_no  ;
          insert into edge.contr_quot ( select * from edge.contr_quot@edget.world rm where rm.branch_code = m_branch_code and rm.quotation_no = m_Qut_no );
          execute immediate 'alter table edge.contr_quot enable all triggers' ; 
 
 
    end;

end;
----Created By Shawky Foda 15-03-2005-----
---Purpose : importing all information related to medical claim batch no. 
procedure import_med_calim_batch(m_batch_ref varchar ) is
begin
          --1-- Migrate Claims under the Batch--------
          execute immediate 'alter table edge.claim disable all triggers' ;
          delete from edge.claim  t where t.batch_ref= m_batch_ref ;
          insert into edge.claim(branch_code, policy_no, claim_no, risk_no, lob_code, cust_id, cert_no, sal_rec_no, int_date, loss_date, si, est_amt, status, acc_amt_paid, acc_sal, 
                                              acc_rec, excess, no_stl, reg_no, tp_reg_no, deprc, deprc_amt, sub_loc_code, loc_code, loss_desc, uw_year, cl_pr_brn, treaty_no, currency_code, ex_rate, perm_pay_flag, t_claim_note_flag, cash_cool_flag, dist_before, last_renew_date, prem_pay_flag, 
                                              user_com_id, e_claimant, loss_code, lst_ren_endt_no, max_endt_no, insured_hospital_pay_flag, in_out_kingdom, in_out_patient, pre_exist_cond, repatriation, business_vacation, days_out_kingdom, days_in_hospital, paid_ded_at_hospital, paid_coins_at_hospital, 
                                              mem_code, payable_amt, coins, ded, refund_disc, med_not_necessary, not_covered, exceeds_policy_limit, admission_date, nurse_code, approver_code, doctor_code, provider_id, provider_name, mnn_pay_flag, nc_pay_flag, epl_pay_flag, np_debit_to_hospital, np_debit_to_customer, 
                                              event_no, r_dist_date, m_dist_date, od_claim_no, batch_ref, fee_amt, cov_discount, not_cov_discount, not_pay_tot, ded_coins_pymnt_flag, epl_discount, u_id, quotation_no, ded_per, coins_per, not_approved_amt, entry_date, upd_date, med_stld_entry_date, close_flag, hand_branch_code, 
                                              occ_code, age, interest_code, rec_status, rec_code, a_loss_date, drvl_no, anti_stl_date, coins_pymnt_flag, last_updated_by, last_update_date, created_by, creation_date, approved, approval_user, approval_date, claim_reserve, adj_reserve, la_appt, oth_exp, claim_type, add_adj_fee_paid, 
                                              acc_oth_exp_paid, acc_clm_amt_paid, acc_adj_fee_paid, claim_blocked, complete_doc, e_fmemo, a_fmemo, date_received, non_paid_ded_at_hospital, non_paid_coins_at_hospital, new_born_baby, delivery_complications, fraud_comment, fraud_check) 
                                              
                                              ( select branch_code, policy_no, claim_no, risk_no, lob_code, cust_id, cert_no, sal_rec_no, int_date, loss_date, si, est_amt, status, acc_amt_paid, acc_sal, 
                                              acc_rec, excess, no_stl, reg_no, tp_reg_no, deprc, deprc_amt, sub_loc_code, loc_code, loss_desc, uw_year, cl_pr_brn, treaty_no, currency_code, ex_rate, perm_pay_flag, t_claim_note_flag, cash_cool_flag, dist_before, last_renew_date, prem_pay_flag, 
                                              user_com_id, e_claimant, loss_code, lst_ren_endt_no, max_endt_no, insured_hospital_pay_flag, in_out_kingdom, in_out_patient, pre_exist_cond, repatriation, business_vacation, days_out_kingdom, days_in_hospital, paid_ded_at_hospital, paid_coins_at_hospital, 
                                              mem_code, payable_amt, coins, ded, refund_disc, med_not_necessary, not_covered, exceeds_policy_limit, admission_date, nurse_code, approver_code, doctor_code, provider_id, provider_name, mnn_pay_flag, nc_pay_flag, epl_pay_flag, np_debit_to_hospital, np_debit_to_customer, 
                                              event_no, r_dist_date, m_dist_date, od_claim_no, batch_ref, fee_amt, cov_discount, not_cov_discount, not_pay_tot, ded_coins_pymnt_flag, epl_discount, u_id, quotation_no, ded_per, coins_per, not_approved_amt, entry_date, upd_date, med_stld_entry_date, close_flag, hand_branch_code, 
                                              occ_code, age, interest_code, rec_status, rec_code, a_loss_date, drvl_no, anti_stl_date, coins_pymnt_flag, last_updated_by, last_update_date, created_by, creation_date, approved, approval_user, approval_date, claim_reserve, adj_reserve, la_appt, oth_exp, claim_type, add_adj_fee_paid, 
                                              acc_oth_exp_paid, acc_clm_amt_paid, acc_adj_fee_paid, claim_blocked, complete_doc, e_fmemo, a_fmemo, date_received, non_paid_ded_at_hospital, non_paid_coins_at_hospital, new_born_baby, delivery_complications, fraud_comment, fraud_check  
                                               from edge.claim@edget.world pb where pb.batch_ref= m_batch_ref );
          execute immediate 'alter table edge.claim enable all triggers' ;
          
          --1-- Migrate Claim_submission under the Batch--------
          -- Note: Disabling Triggers is temproery commented (for insufficient Authority problem), so TaJ.Claim_Sub Trigger will be fired i.e Audting INformation ( created by , creation_date , ....) will be lost 
          -- execute immediate 'alter table taj.claims_submission disable all triggers' ;
          delete from taj.claims_submission  t where t.batch_ref= m_batch_ref ;
          insert into taj.claims_submission(portal_trans_id, payer_claim_no, prov_org_claim_no, prov_cur_claim_no, portal_batch_no, sender_id, sender_name, deducted_amt, paid_amt, currency, payment_date, portal_login_id, portal_user_name, 
                               claim_type, receiver_id, member_code, provider_id, policy_no, portal_date, visit_type, account_code, claim_date, patient_file_no, batch_ref, birth_date, nationality, gender, clinic_code, elig_ref_no, reservation_no, 
                               admission_date, discharge_date, length_of_stay, room_number, bed_number, lmpd, r_eye_sd, r_eye_cd, r_eye_sn, r_eye_cn, l_eye_sd, l_eye_cd, l_eye_sn, l_eye_cn, msg_code, msg_desc, chronic, congenital, rta, 
                               work_related, vaccination, check_up, psychiatric, infertility, pregnancy, orthodontics_aesthetics, sports_related, cleaning, clm_p_sub_others, physician_id, physician_name, main_symptom, unit_of_duration, duration_of_illness, 
                               temperature, blood_pressure, pulse, respiratory_rate, weight, other_conditions, significant_signs, radiology_report, comments_and_reports, tot_clm_gross_amt, tot_clm_discount, tot_clm_patient_share, tot_clm_net_amt, deleivery_type, 
                               status, claim_status, posted, rejection_reasons, plan_type, plan_id, physician_category, physician_notes, bus_type, submission_type, created_by, creation_date, last_updated_by, last_update_date, member_name, member_age, unit_of_age )  
                      ( select portal_trans_id, payer_claim_no, prov_org_claim_no, prov_cur_claim_no, portal_batch_no, sender_id, sender_name, deducted_amt, paid_amt, currency, payment_date, portal_login_id, portal_user_name, 
                               claim_type, receiver_id, member_code, provider_id, policy_no, portal_date, visit_type, account_code, claim_date, patient_file_no, batch_ref, birth_date, nationality, gender, clinic_code, elig_ref_no, reservation_no, 
                               admission_date, discharge_date, length_of_stay, room_number, bed_number, lmpd, r_eye_sd, r_eye_cd, r_eye_sn, r_eye_cn, l_eye_sd, l_eye_cd, l_eye_sn, l_eye_cn, msg_code, msg_desc, chronic, congenital, rta, 
                               work_related, vaccination, check_up, psychiatric, infertility, pregnancy, orthodontics_aesthetics, sports_related, cleaning, clm_p_sub_others, physician_id, physician_name, main_symptom, unit_of_duration, duration_of_illness, 
                               temperature, blood_pressure, pulse, respiratory_rate, weight, other_conditions, significant_signs, radiology_report, comments_and_reports, tot_clm_gross_amt, tot_clm_discount, tot_clm_patient_share, tot_clm_net_amt, deleivery_type, 
                               status, claim_status, posted, rejection_reasons, plan_type, plan_id, physician_category, physician_notes, bus_type, submission_type, created_by, creation_date, last_updated_by, last_update_date, member_name, member_age, unit_of_age 
                         from taj.claims_submission@edget.world pb where pb.batch_ref= m_batch_ref );
          -- execute immediate 'alter table taj.claims_submission enable all triggers' ;
          

          --2-- Migrate the Batch it self --------
          execute immediate 'alter table edge.claim_batch disable all triggers' ;
          delete from edge.claim_batch  t where t.batch_ref= m_batch_ref ;
          insert into edge.claim_batch (batch_ref, branch_code, policy_no, provider_id, entry_date, receive_date, given_date, loss_date, comment1, provider_ref_no, store_location, 
                                                gross_amount, user_com_id, stl_date, stl_entry_date, stl_user_com_id, max_pay_amount, adjuster, last_updated_by, last_update_date, created_by, creation_date, in_out_patient, 
                                                plan_id, auto_flag, txt_file_name, returned_claim, bills_not_received, transfered_claims, rec_amount, returned_claims_net, diff_comp_amt, req_net_amt, 
                                                segment_code, date_of_credit_send, diff_free_txt, reserve_amount  , average_billing  , transfer_batch_ref , max_claim_count  -- , batch_closed 
                                                )
                                              
                              ( select * 
                                from edge.claim_batch@edget.world pb where pb.batch_ref= m_batch_ref 
                               );
          execute immediate 'alter table edge.claim_batch enable all triggers' ;
          
          -----------------------------------------------------------------------------
          execute immediate 'alter table edge.aso_claim disable all triggers' ;
          delete from edge.aso_claim  t where t.batch_ref= m_batch_ref ;
          insert into edge.aso_claim ( select * from edge.aso_claim@edget.world pb where pb.batch_ref= m_batch_ref );
          execute immediate 'alter table edge.aso_claim enable all triggers' ;
          
          -----------------------------------------------------------------------------
          execute immediate 'alter table edge.med_cl_break_1 disable all triggers' ;
          delete from edge.med_cl_break_1  t  where (t.claim_no , t.policy_no) in (select claim_no, policy_no from claim@edget.world c where c.batch_ref= m_batch_ref) ;
          insert into edge.med_cl_break_1  ( select * from edge.med_cl_break_1@edget.world pb  where (pb.claim_no , pb.policy_no) in (select claim_no , policy_no from claim@edget.world c where c.batch_ref= m_batch_ref));
          execute immediate 'alter table edge.med_cl_break_1 enable all triggers' ;
          -----------------------------------------------------------------------------          
          execute immediate 'alter table edge.aso_med_cl_break_1 disable all triggers' ;
          delete from edge.aso_med_cl_break_1  t where (t.claim_no , t.policy_no) in (select claim_no, policy_no from claim@edget.world c where c.batch_ref= m_batch_ref) ;
          insert into edge.aso_med_cl_break_1 ( select * from edge.aso_med_cl_break_1@edget.world pb  where (pb.claim_no , pb.policy_no) in (select claim_no , policy_no from claim@edget.world c where c.batch_ref= m_batch_ref));
          execute immediate 'alter table edge.aso_med_cl_break_1 enable all triggers' ;
          
          -----------------------------------------------------------------------------          
          execute immediate 'alter table edge.cl_treaty_no disable all triggers' ;
          delete from edge.cl_treaty_no  t where (t.claim_no , t.policy_no) in (select claim_no, policy_no from claim@edget.world c where c.batch_ref= m_batch_ref) ;
          insert into edge.cl_treaty_no ( select * from edge.cl_treaty_no@edget.world pb  where (pb.claim_no , pb.policy_no) in (select claim_no , policy_no from claim@edget.world c where c.batch_ref= m_batch_ref));
          execute immediate 'alter table edge.cl_treaty_no enable all triggers' ;
          -----------------------------------------------------------------------------          
          execute immediate 'alter table edge.araptrx disable all triggers' ;
          delete from edge.araptrx  t where t.batch_ref = m_batch_ref ;
          insert into edge.araptrx ( select * from edge.araptrx@edget.world x  where x.batch_ref = m_batch_ref ); 
          execute immediate 'alter table edge.araptrx enable all triggers' ;                    
          
          
          
end;

-----------------------------------------------------------------------------------------------------------------
procedure import_Motor_objects(v_policy_No number) is
  m_Qut_no number;
  m_cust_id number;
  m_branch_code number;
  m_lob_code varchar2(3);
begin
    select po.quotation_no, po.cust_id  , po.branch_code, po.lob_code
    into m_Qut_no , m_cust_id , m_branch_code , m_lob_code
    from policy@edget.world po where po.policy_no = v_policy_No;
       
    execute immediate 'alter table edge.rukhsa disable all triggers' ;
    delete from edge.rukhsa rukh where rukh.policy_no= v_policy_No ;
    insert into edge.rukhsa ( select * from edge.rukhsa@edget.world rukh where rukh.policy_no= v_policy_No );
    execute immediate 'alter table edge.rukhsa enable all triggers' ;
    
    execute immediate 'alter table edge.rukhsa_member disable all triggers' ;           
    delete from edge.rukhsa_member rm where rm.branch_code = m_branch_code and rm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no) ;
    insert into edge.rukhsa_member ( select * from edge.rukhsa_member@edget.world rm where rm.branch_code = m_branch_code and rm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no) );
    execute immediate 'alter table edge.rukhsa_member enable all triggers' ; 
    -- Upon Darwish Help Desk Request No. 8953
     execute immediate 'alter table edge.RUKHSA_POL disable all triggers' ;
     delete from edge.RUKHSA_POL  pb where pb.policy_no= v_policy_No ;
     insert into edge.RUKHSA_POL ( select * from edge.RUKHSA_POL@edget.world pb where pb.policy_no= v_policy_No );
     execute immediate 'alter table edge.RUKHSA_POL enable all triggers' ;
     -- End of Upon Darwish Help Desk Request No. 8953
     execute immediate 'alter table edge.fleet disable all triggers' ; 
     delete from edge.fleet f where  f.branch_code = m_branch_code and f.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
     insert into edge.fleet ( select * from edge.fleet@edget.world f where f.branch_code = m_branch_code and f.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no) );
     execute immediate 'alter table edge.fleet enable all triggers' ; 

end;
       
-----------------------------------------------------------------------------------------------------------------
procedure import_medical_objects(v_policy_No number) is
  m_Qut_no number;
  m_cust_id number;
  m_branch_code number;
  m_lob_code varchar2(3);
begin

    select po.quotation_no, po.cust_id  , po.branch_code, po.lob_code
    into m_Qut_no , m_cust_id , m_branch_code , m_lob_code
    from policy@edget.world po where po.policy_no = v_policy_No;
          
    execute immediate 'alter table edge.med_mem disable all triggers' ; 
    delete from edge.med_mem mm where mm.branch_code = m_branch_code and mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
    insert into edge.med_mem ( select * from edge.med_mem@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ) ;
    execute immediate 'alter table edge.med_mem enable all triggers' ; 
          
    execute immediate 'alter table edge.med_mrb_coins_det disable all triggers' ; 
    delete from edge.med_mrb_coins_det mm where mm.branch_code = m_branch_code and mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
    insert into edge.med_mrb_coins_det ( select * from edge.med_mrb_coins_det@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ) ;
    execute immediate 'alter table edge.med_mrb_coins_det enable all triggers' ; 

    execute immediate 'alter table edge.med_mrb_ded_det disable all triggers' ; 
    delete from edge.med_mrb_ded_det mm where mm.branch_code = m_branch_code and mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ;
    insert into edge.med_mrb_ded_det ( select * from edge.med_mrb_ded_det@edget.world mm  where mm.branch_code = m_branch_code and  mm.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ) ;
    execute immediate 'alter table edge.med_mrb_ded_det enable all triggers' ; 
    
    execute immediate 'alter table edge.Med_Class_Qtn disable all triggers' ; 
    delete from edge.Med_Class_Qtn  mcq where mcq.branch_code = m_branch_code and mcq.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual ) ; 
    insert into edge.Med_Class_Qtn ( select * from edge.Med_Class_Qtn@edget.world mcq where mcq.branch_code = m_branch_code and mcq.quotation_no in (select qu.quotation_no from edge.quotation qu where qu.policy_no = v_policy_no union select m_Qut_no from dual )); 
    execute immediate 'alter table edge.Med_Class_Qtn enable all triggers' ; 

end;

-----------------------------------------------------------------------------------------------------------------
procedure import_shop_owner_objects(v_policy_No number) is
m_SOP_REQ_NO number;
begin
    select SOP_REQ_NO into m_SOP_REQ_NO from edge.SO_POLICY@edget.world t where t.policy_no = v_policy_No;

   -----------------------------------------------------------------------------              
    execute immediate 'alter table edge.SO_POLICY  disable all triggers' ;
   delete from edge.SO_POLICY  t where t.policy_no = v_policy_No ;
   insert into edge.SO_POLICY ( select * from edge.SO_POLICY@edget.world x  where x.policy_no = v_policy_No ); 
    execute immediate 'alter table edge.SO_POLICY enable all triggers' ; 
   ----------------------------------------------------------------------------              
   execute immediate 'alter table edge.sop_risk  disable all triggers' ;
   delete from edge.sop_risk  t where t.sop_req_no= m_SOP_REQ_NO ;
   insert into edge.sop_risk ( select * from edge.sop_risk@edget.world x  where x.sop_req_no = m_SOP_REQ_NO ); 
   execute immediate 'alter table edge.sop_risk enable all triggers' ; 
   -----------------------------------------------------------------------------          
   execute immediate 'alter table edge.SOP_COVER  disable all triggers' ;
   delete from edge.SOP_COVER  t where t.sop_req_no = m_SOP_REQ_NO ; 
   insert into edge.SOP_COVER ( select * from edge.SOP_COVER@edget.world x  where x.sop_req_no = m_SOP_REQ_NO ); 
   execute immediate 'alter table edge.SOP_COVER enable all triggers' ; 
   -----------------------------------------------------------------------------          
   execute immediate 'alter table edge.SOP_COVER_EMP disable all triggers' ;
   delete from edge.SOP_COVER_EMP   t where t.cover_req_no in (select sc.cover_req_no from edge.SOP_COVER sc where sc.sop_req_no = m_SOP_REQ_NO) ;
   insert into edge.SOP_COVER_EMP  ( select * from edge.SOP_COVER_EMP@edget.world x  where x.cover_req_no in (select sc.cover_req_no from edge.SOP_COVER sc where sc.sop_req_no = m_SOP_REQ_NO) );
   execute immediate 'alter table edge.SOP_COVER_EMP enable all triggers' ; 
   -----------------------------------------------------------------------------          
   execute immediate 'alter table edge.SOP_COVER_Risk disable all triggers' ;
   delete from edge.SOP_COVER_Risk   t where t.cover_item_req_no in 
        (
         select scr.cover_item_req_no from edge.SOP_COVER_Risk@edget.world scr where scr.cover_req_no in 
            (select cover_req_no from sop_cover@edget.world sc where sc.sop_req_no = m_SOP_REQ_NO) 
        );
   insert into edge.SOP_COVER_Risk  
          ( select * from edge.SOP_COVER_Risk@edget.world x  where x.cover_req_no in 
             (select sc.cover_req_no from edge.SOP_COVER@edget.world sc where sc.sop_req_no = m_SOP_REQ_NO) 
          );
   execute immediate 'alter table edge.SOP_COVER_Risk enable all triggers' ; 
   -----------------------------------------------------------------------------          
   execute immediate 'alter table edge.SOP_COVER_VEHICLES disable all triggers' ;
   delete from edge.SOP_COVER_VEHICLES   t where t.cover_req_no in 
               (select sc.cover_req_no from edge.SOP_COVER@edget.world sc where sc.sop_req_no = m_SOP_REQ_NO) ;
   insert into edge.SOP_COVER_VEHICLES  
               (select * from edge.SOP_COVER_VEHICLES@edget.world x  where x.cover_req_no in 
                       (select sc.cover_req_no from edge.SOP_COVER@edget.world sc where sc.sop_req_no = m_SOP_REQ_NO) 
               );
   execute immediate 'alter table edge.SOP_COVER_VEHICLES enable all triggers' ; 
   -----------------------------------------------------------------------------        
 

end;       


end;
/
