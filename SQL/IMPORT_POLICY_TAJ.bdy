create or replace package taj.import_policy as

procedure import_med_calim_batch(m_batch_ref varchar );
procedure import_policy_from_prod(v_policy_no number) ; 
end;
/
create or replace package body taj.import_policy as

   procedure import_med_calim_batch(m_batch_ref varchar ) is 
   begin
          execute immediate 'alter table taj.claims_submission disable all triggers' ;
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
          execute immediate 'alter table taj.claims_submission enable all triggers' ;
   end;
   procedure import_policy_from_prod(v_policy_no number) is 
   begin
      null;
   end ;
end;
/
