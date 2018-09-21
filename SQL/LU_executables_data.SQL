prompt PL/SQL Developer import file
prompt Created on Sunday, April 03, 2005 by foda_sh
set feedback off
set define off
prompt Disabling triggers for LU_EXECUTABLES...
alter table LU_EXECUTABLES disable all triggers;
prompt Disabling foreign key constraints for LU_EXECUTABLES...
alter table LU_EXECUTABLES disable constraint FK_LU_EXECUTABLES;
alter table LU_EXECUTABLES disable constraint FK_LU_EXECUTABLES_LU_ROOT;
prompt Deleting LU_EXECUTABLES...
delete from LU_EXECUTABLES;
commit;
prompt Loading LU_EXECUTABLES...
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (6, 'dbo', 0, null, null, null, 'dbo', 'dbo', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (14, 'Export Lookup', 2, null, 0, null, 'Export Lookup to Dev', 'Export Lookup to Dev', null, 'T', 'F', 0, 0, '\--------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.export_lookup(''edge.source'')' || chr(13) || '' || chr(10) || '', 'BAT_USER', 'This Command will Export all lookup Tables from Production to Development');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (43, 'MA203685', 0, null, null, null, 'MA203685', 'MA203685', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (51, 'WAHEDK', 0, null, null, null, 'WAHEDK', 'WAHEDK', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (95, 'Export All Lookup', 2, null, 0, null, 'Export All Lookup', 'Export All Lookup', null, 'T', 'F', 0, 0, '\--------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.export_lookups()' || chr(13) || '' || chr(10) || '', 'BAT_USER', 'This Command will Export all lookup Tables from Production to Development');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (2, 'General', 1, null, 0, null, 'General', 'General', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (4, 'kill_session', 2, null, 0, null, 'kill_session', 'kill_session', null, 'T', 'F', 0, 0, '\----------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.medical_support.kill_session($$session_id,  $$sn)', 'BAT_USER', 'This Command Will Kill The Given Session Number');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (0, '0', null, null, null, null, 'Users Executables', 'Users Executables', null, 'T', 'F', 0, 0, null, 'COMP', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (1, 'OS310', 0, null, null, null, 'OS310', 'OS310', null, 'T', 'F', 0, 0, null, 'OS310', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (3, 'Medical', 1, null, 0, null, 'Medical', 'Medical', null, 'T', 'F', 0, 0, '\------------------------' || chr(13) || '' || chr(10) || 'call delete_traety(''MD'', ''0607''  , 100 )', 'OS310', 'Medical cbxcfbcv');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (27, 'Return claim 2 Que', 3, null, 0, null, 'Return claim 2 Queue', 'Return claim 2 Queue', null, 'T', 'F', 0, 0, '\--------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.returnClaim2Queue ($$pno , $$cl_no)', 'OS310', 'This Command Will Return The given Claim No for the Given Policy No Back to The Queue.');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (28, 'Delete a Batch', 3, null, 0, null, 'Delete a Batch', 'Delete a Batch', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_batch(''$$batch_ref'')', 'OS310', 'This Command Will Delete all the  Given Batch Details ');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (29, 'Delete a claim', 3, null, 0, null, 'Delete a claim', 'Delete a claim', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_Claim($$cl_no,$$pno)', 'OS310', 'This Command will Delete all  claim Details for The given Policy No and Claim Number');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (30, 'Test', 3, null, 0, null, 'Test', 'test', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.Test()', 'OS310', 'test');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (31, 'OS302', 0, null, null, null, 'OS302', 'OS302', null, 'T', 'F', 0, 0, null, 'OS310', 'OS302');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (34, 'delete_adj_details', 3, null, 0, null, 'delete adj details', 'delete adj details', null, 'T', 'F', 0, 0, '\------------------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_adj_details(999999999)', 'OS310', 'This Command will Delete all  the details for the given adjustment No ');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (35, 'EDGE', 0, null, null, null, 'EDGE', 'EDGE', null, 'T', 'F', 0, 0, null, 'OS310', 'EDGE');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (36, 'BAT_USER', 0, null, null, null, 'BAT_USER', 'BAT_USER', null, 'T', 'F', 0, 0, null, 'OS310', 'BAT_USER');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (37, 'dfvsdf', 36, null, 0, null, 'dsfgsdfg', 'dsfgsdfg', null, 'T', 'F', 0, 0, '\--------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.medical_support.test()', 'OS310', 'dsfgsdfg');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (89, 'OS303', 0, null, null, null, 'OS303', 'OS303', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (103, 'Unsettel Batch', 3, null, 0, null, 'Unsettel Batch', 'Unsettel Batch', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.unsettel_Batch(''$$batch_ref'')', 'BAT_USER', 'This Command will Delete all Transactions Releted to The given Batch No. ');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (105, 'Billing System', 1, null, 0, null, 'Billing System', 'Billing System', null, 'T', 'F', 0, 0, null, 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (106, 'Create New Daily Wor', 105, null, 0, null, 'Create New Daily Work', 'Create New Daily Work', null, 'T', 'F', 0, 0, '\----------------------' || chr(13) || '' || chr(10) || 'insert into Bill.DAILY_PROG (SUB_TASK_NO , activity_code , created_by , creation_date , work_date) values ($$sub_task_no,50 , user , sysdate , sysdate)', 'BAT_USER', 'null');
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG)
values (117, 'Create New Sub Task', 105, null, 0, null, 'Create New Sub Task', 'Create New Sub Task', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'insert into bill.sub_tasks (sub_task_no , task_no , created_by , creation_date ) ' || chr(13) || '' || chr(10) || '        values ( ( select max(sub_task_no) +1 from bill.sub_tasks) , $$task_no , user , sysdate )', 'BAT_USER', 'Create a New Sub Task Under The Given Task No. ');
commit;
prompt 24 records loaded
prompt Enabling foreign key constraints for LU_EXECUTABLES...
alter table LU_EXECUTABLES enable constraint FK_LU_EXECUTABLES;
alter table LU_EXECUTABLES enable constraint FK_LU_EXECUTABLES_LU_ROOT;
prompt Enabling triggers for LU_EXECUTABLES...
alter table LU_EXECUTABLES enable all triggers;
set feedback on
set define on
prompt Done.
