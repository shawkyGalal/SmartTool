prompt PL/SQL Developer import file
prompt Created on Thursday, April 13, 2006 by shawky.foda
set feedback off
set define off
prompt Disabling triggers for LU_EXECUTABLES...
alter table LU_EXECUTABLES disable all triggers;
prompt Disabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS disable all triggers;
prompt Disabling triggers for SYS_PARAMS...
alter table SYS_PARAMS disable all triggers;
prompt Disabling triggers for TBLSYS_SCRIPT...
alter table TBLSYS_SCRIPT disable all triggers;
prompt Disabling foreign key constraints for LU_EXECUTABLES...
alter table LU_EXECUTABLES disable constraint FK_LU_EXECUTABLES;
alter table LU_EXECUTABLES disable constraint FK_LU_EXECUTABLES_LU_ROOT;
prompt Deleting TBLSYS_SCRIPT...
delete from TBLSYS_SCRIPT;
commit;
prompt Deleting SYS_PARAMS...
delete from SYS_PARAMS;
commit;
prompt Deleting SQL_BOUND_VARS...
delete from SQL_BOUND_VARS;
commit;
prompt Deleting LU_EXECUTABLES...
delete from LU_EXECUTABLES;
commit;
prompt Loading LU_EXECUTABLES...
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (6, 'dbo', 0, null, null, null, 'dbo', 'dbo', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (14, 'Export Lookup', 2, null, 0, null, 'Export Lookup to Dev', 'Export Lookup to Dev', null, 'T', 'F', 0, 0, '\--------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.export_lookup(''edge.source'')' || chr(13) || '' || chr(10) || '', 'BAT_USER', 'This Command will Export all lookup Tables from Production to Development', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (43, 'MA203685', 0, null, null, null, 'MA203685', 'MA203685', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (51, 'WAHEDK', 0, null, null, null, 'WAHEDK', 'WAHEDK', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (95, 'Export All Lookup', 2, null, 0, null, 'Export All Lookup', 'Export All Lookup', null, 'T', 'F', 0, 0, '\--------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.export_lookups()' || chr(13) || '' || chr(10) || '', 'BAT_USER', 'This Command will Export all lookup Tables from Production to Development', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (2, 'General', 1, null, 0, null, 'General', 'General', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (4, 'kill_session', 2, null, 0, null, 'kill_session', 'kill_session', null, 'T', 'F', 0, 0, '\----------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.medical_support.kill_session($$session_id,  $$sn)', 'BAT_USER', 'This Command Will Kill The Given Session Number', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (0, '0', null, null, null, null, 'Users Executables', 'Users Executables', null, 'T', 'F', 0, 0, null, 'COMP', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (1, 'OS310', 0, null, null, null, 'OS310', 'OS310', null, 'T', 'F', 0, 0, null, 'OS310', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3, 'Medical', 1, null, 0, null, 'Medical', 'Medical', null, 'T', 'F', 0, 0, '\------------------------' || chr(13) || '' || chr(10) || 'call delete_traety(''MD'', ''0607''  , 100 )', 'OS310', 'Medical cbxcfbcv', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (27, 'Return claim 2 Que', 3, null, 0, null, 'Return claim 2 Queue', 'Return claim 2 Queue', null, 'T', 'F', 0, 0, '\--------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.returnClaim2Queue ($$pno , $$cl_no)', 'OS310', 'This Command Will Return The given Claim No for the Given Policy No Back to The Queue.', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (28, 'Delete a Batch', 3, null, 0, null, 'Delete a Batch', 'Delete a Batch', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_batch(''$$batch_ref'')', 'OS310', 'This Command Will Delete all the  Given Batch Details ', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (29, 'Delete a claim', 3, null, 0, null, 'Delete a claim', 'Delete a claim', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_Claim($$cl_no,$$pno)', 'OS310', 'This Command will Delete all  claim Details for The given Policy No and Claim Number', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (30, 'Test', 3, null, 0, null, 'Test', 'test', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.Test()', 'OS310', 'test', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (31, 'OS302', 0, null, null, null, 'OS302', 'OS302', null, 'T', 'F', 0, 0, null, 'OS310', 'OS302', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (34, 'delete_adj_details', 3, null, 0, null, 'delete adj details', 'delete adj details', null, 'T', 'F', 0, 0, '\------------------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.delete_adj_details(999999999)', 'OS310', 'This Command will Delete all  the details for the given adjustment No ', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (35, 'EDGE', 0, null, null, null, 'EDGE', 'EDGE', null, 'T', 'F', 0, 0, null, 'OS310', 'EDGE', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (36, 'BAT_USER', 0, null, null, null, 'BAT_USER', 'BAT_USER', null, 'T', 'F', 0, 0, null, 'OS310', 'BAT_USER', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (37, 'dfvsdf', 36, null, 0, null, 'dsfgsdfg', 'dsfgsdfg', null, 'T', 'F', 0, 0, '\--------------------------------' || chr(13) || '' || chr(10) || 'call bat_user.medical_support.test()', 'OS310', 'dsfgsdfg', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (89, 'OS303', 0, null, null, null, 'OS303', 'OS303', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (103, 'Unsettel Batch', 3, null, 0, null, 'Unsettel Batch', 'Unsettel Batch', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'call bat_user.Medical_support.unsettel_Batch(''$$batch_ref'')', 'BAT_USER', 'This Command will Delete all Transactions Releted to The given Batch No. ', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (105, 'Billing System', 1, null, 0, null, 'Billing System', 'Billing System', null, 'T', 'F', 0, 0, null, 'BAT_USER', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (106, 'Create New Daily Wor', 105, null, 0, null, 'Create New Daily Work', 'Create New Daily Work', null, 'T', 'F', 0, 0, '\----------------------' || chr(13) || '' || chr(10) || 'insert into Bill.DAILY_PROG (SUB_TASK_NO , activity_code , created_by , creation_date , work_date) values ($$sub_task_no,50 , user , sysdate , sysdate)', 'BAT_USER', 'null', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (117, 'Create New Sub Task', 105, null, 0, null, 'Create New Sub Task', 'Create New Sub Task', null, 'T', 'F', 0, 0, '\-----------------------' || chr(13) || '' || chr(10) || 'insert into bill.sub_tasks (sub_task_no , task_no , created_by , creation_date ) ' || chr(13) || '' || chr(10) || '        values ( ( select max(sub_task_no) +1 from bill.sub_tasks) , $$task_no , user , sysdate )', 'BAT_USER', 'Create a New Sub Task Under The Given Task No. ', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (5, 'CRYSTAL', 0, null, null, null, 'CRYSTAL', 'CRYSTAL', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (46, 'BGADMIN', 0, null, null, null, 'BGADMIN', 'BGADMIN', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (187, 'SYSTEM', 0, null, null, null, 'SYSTEM', 'SYSTEM', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (301, 'CRYSTAL ', 0, null, null, null, 'CRYSTAL ', 'CRYSTAL ', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (343, 'SHAWKY', 0, null, null, null, 'SHAWKY', 'SHAWKY', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (348, 'IDI', 0, null, null, null, 'IDI', 'IDI', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (356, 'SUHAILA', 0, null, null, null, 'SUHAILA', 'SUHAILA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (362, 'HODA', 0, null, null, null, 'HODA', 'HODA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (377, 'EMAN', 0, null, null, null, 'EMAN', 'EMAN', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (482, 'MAULIK', 0, null, null, null, 'MAULIK', 'MAULIK', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (577, 'NADA', 0, null, null, null, 'NADA', 'NADA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (645, 'LINA', 0, null, null, null, 'LINA', 'LINA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (663, ' SHAWKY', 0, null, null, null, ' SHAWKY', ' SHAWKY', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (712, 'SUPPORT', 0, null, null, null, 'SUPPORT', 'SUPPORT', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (734, 'MARYAM', 0, null, null, null, 'MARYAM', 'MARYAM', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (830, 'HANAGHAMIN', 0, null, null, null, 'HANAGHAMIN', 'HANAGHAMIN', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (914, 'ESRAA', 0, null, null, null, 'ESRAA', 'ESRAA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (989, 'ROULA', 0, null, null, null, 'ROULA', 'ROULA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (1051, 'ROUK', 0, null, null, null, 'ROUK', 'ROUK', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (2154, 'MNSDWM', 0, null, null, null, 'MNSDWM', 'MNSDWM', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (2904, 'HANA', 0, null, null, null, 'HANA', 'HANA', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3108, 'REEM', 0, null, null, null, 'REEM', 'REEM', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3788, 'RollBack ', 46, null, 0, null, 'RollBack Master Reconciliation', 'RollBack Master Reconciliation', null, 'T', 'F', 0, 0, '\---dfsdfd' || chr(13) || '' || chr(10) || 'Delete from BGADMIN.AuditLog where to_date(createdate,''j'') = to_date(''08-10-2005'',''dd-mm-YYYY'') and ' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || 'Application in (''BANK_RECONCILIATION'',''BILLER_FUNDS'',''BILLER_RECONCILIATION'',''INTRABANK_SETTLEMENT'',''SARIE_SETTLEMENT'');' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || '/---------' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDRELATION ' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || 'where rolebid = (Select id from bgadmin.SDPAYMENTRUN where to_date(createdate,''j'') = to_date(''08-10-2005'',''dd-mm-YYYY'') and to_char(RUNTYPE) = ''100'');' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || '/---- ' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDPAYMENTLOG;' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || ' ' || chr(13) || '' || chr(10) || '/---------' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDPaymentRun where to_date(createdate,''j'') = to_date(''08-10-2005'',''dd-mm-YYYY'');' || chr(13) || '' || chr(10) || '', 'SUPPORT', 'This Process Will RollBack Master Reconciliation for the given $$from_date', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3789, 'Step 1', 3788, null, 0, null, 'Step 1', 'Step 1', null, 'T', 'F', 0, 0, '\--------' || chr(13) || '' || chr(10) || 'Delete from BGADMIN.AuditLog ' || chr(13) || '' || chr(10) || 'where to_date(createdate,''j'') = to_date(''$$from_date'',''dd-mm-YYYY'') ' || chr(13) || '' || chr(10) || 'and Application in (''BANK_RECONCILIATION'',''BILLER_FUNDS'',''BILLER_RECONCILIATION'',''INTRABANK_SETTLEMENT'',''SARIE_SETTLEMENT'')', 'SUPPORT', 'This Process Will RollBack Master Reconciliation for the given $$from_date', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3790, 'Step 2', 3788, null, 0, null, 'Step 2', 'Step 2', null, 'T', 'F', 0, 0, '\--------' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDRELATION ' || chr(13) || '' || chr(10) || 'where rolebid = (Select id from bgadmin.SDPAYMENTRUN ' || chr(13) || '' || chr(10) || '                 where to_date(createdate,''j'') = to_date(''$$from_date'',''dd-mm-YYYY'') ' || chr(13) || '' || chr(10) || '                 and to_char(RUNTYPE) = ''100'')' || chr(13) || '' || chr(10) || ' ' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || '', 'SUPPORT', 'This Process Will RollBack Master Reconciliation for the given $$from_date', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3791, 'Step 3', 3788, null, 0, null, 'Step 3', 'Step 3', null, 'T', 'F', 0, 0, '\---' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDPAYMENTLOG' || chr(13) || '' || chr(10) || '' || chr(13) || '' || chr(10) || ' ' || chr(13) || '' || chr(10) || '', 'SUPPORT', 'This Process Will RollBack Master Reconciliation for the given $$from_date', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3792, 'Step 4', 3788, null, 0, null, 'Step 4', 'Step 4', null, 'T', 'F', 0, 0, '\----' || chr(13) || '' || chr(10) || 'Delete  from BGADMIN.SDPaymentRun ' || chr(13) || '' || chr(10) || 'where to_date(createdate,''j'') = to_date(''$$from_date'',''dd-mm-YYYY'')' || chr(13) || '' || chr(10) || '', 'SUPPORT', 'This Process Will RollBack Master Reconciliation for the given $$from_date', null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (3878, 'ODS01SMART', 0, null, null, null, 'ODS01SMART', 'ODS01SMART', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (4031, 'BILLER', 0, null, null, null, 'BILLER', 'BILLER', null, 'T', 'F', 0, 0, null, 'SUPPORT', null, null);
insert into LU_EXECUTABLES (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS, EXEC_BODY, OWNER, MSG, HYPERLINK_TITLE)
values (4054, 'msg_codes ', 4031, null, 0, null, 'msg_codes ', 'msg_codes ', null, 'T', 'F', 0, 0, '\' || chr(13) || '' || chr(10) || 'insert into msg_codes values (''PUPLRQ'')', 'SUPPORT', null, null);
commit;
prompt 54 records loaded
prompt Loading SQL_BOUND_VARS...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'Account ID', '$$accId', 'Y', null, to_date('20-09-2005 11:02:19', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'Stmt ID', '$$stmtId', 'Y', null, to_date('20-09-2005 11:02:19', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('20-09-2005 11:02:19', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('20-09-2005 11:02:19', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'SPTN', '$$sptn', 'Y', null, to_date('20-09-2005 11:02:19', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Account ID', '$$accId', 'Y', null, to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'Account ID', '$$accId', 'Y', '5555555', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Stmt ID', '$$stmtId', 'Y', null, to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'SPTN', '$$sptn', 'Y', null, to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'Stmt ID', '$$stmtId', 'N', null, to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'Account ID', '$$accId', 'Y', null, to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'Stmt ID', '$$stmtId', 'Y', null, to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'SPTN', '$$sptn', 'Y', null, to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Object Name', '$$obj_name', 'Y', 'abc', to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 8, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'Biller ID', '$$billerId', 'Y', '005', to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'Biller ID', '$$billerId', 'Y', '005', to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANAGHAMIN', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('17-08-2005 09:15:27', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'SPTN', '$$sptn', 'Y', null, to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'DB Obj Name', '$$obj_name', 'Y', 'abc', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 8, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'Biller ID', '$$billerId', 'Y', '005', to_date('28-11-2005 16:43:31', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'Account ID', '$$accId', 'Y', null, to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('28-11-2005 16:43:31', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'loginId', '$$loginId', 'N', 'foda_sh', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 9, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'SPTN', '$$sptn', 'Y', null, to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'Account ID', '$$accId', 'Y', null, to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'Biller ID', '$$billerId', 'Y', '005', to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ESRAA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('22-08-2005 15:52:56', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'SPTN', '$$sptn', 'Y', null, to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'Biller ID', '$$billerId', 'Y', '005', to_date('20-09-2005 11:02:18', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MARYAM', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('20-09-2005 11:02:18', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'Biller ID', '$$billerId', 'Y', '005', to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('ROULA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('05-09-2005 12:39:11', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Biller ID', '$$billerId', 'Y', '001', to_date('25-01-2006 12:05:16', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'Account ID', '$$accId', 'Y', null, to_date('28-11-2005 16:43:31', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'Stmt ID', '$$stmtId', 'Y', null, to_date('28-11-2005 16:43:31', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('28-11-2005 16:43:32', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('28-11-2005 16:43:32', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MNSDWM', 'SPTN', '$$sptn', 'Y', null, to_date('28-11-2005 16:43:32', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'DateConv', '$$Date', 'N', null, to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 10, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'Account ID', '$$accId', 'Y', null, to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HANA', 'SPTN', '$$sptn', 'Y', null, to_date('31-01-2006 15:13:48', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'Biller ID', '$$billerId', 'Y', '005', to_date('31-01-2006 15:23:39', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('31-01-2006 15:23:39', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'Account ID', '$$accId', 'Y', null, to_date('31-01-2006 15:23:39', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'Stmt ID', '$$stmtId', 'Y', null, to_date('31-01-2006 15:23:39', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('31-01-2006 15:23:39', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('31-01-2006 15:23:40', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SYSTEM', 'SPTN', '$$sptn', 'Y', null, to_date('31-01-2006 15:23:40', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'Biller ID', '$$billerId', 'Y', '005', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'Account ID', '$$accId', 'Y', null, to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'Stmt ID', '$$stmtId', 'Y', null, to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'SPTN', '$$sptn', 'Y', '47221', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BGADMIN', 'Object', '$$obj_name', 'Y', 'abc', to_date('03-04-2006 11:09:15', 'dd-mm-yyyy hh24:mi:ss'), 8, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'Account ID', '$$accId', 'Y', null, to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'Stmt ID', '$$stmtId', 'Y', null, to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'SPTN', '$$sptn', 'Y', null, to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'objectname', '$$obj_name', 'Y', 'abc', to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 8, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'Biller ID', '$$billerId', 'Y', '005', to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('IDI', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('03-04-2006 11:18:19', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SHAWKY', 'Biller ID', '$$billerId', 'Y', '005', to_date('04-04-2006 10:29:45', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Stmt ID', '$$stmtId', 'Y', null, to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'SPTN', '$$sptn', 'Y', null, to_date('16-07-2005 15:10:38', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Biller ID', '$$billerId', 'Y', '005', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Account ID', '$$accId', 'Y', null, to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'Account ID', '$$accId', 'Y', null, to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'Biller ID', '$$billerId', 'Y', '005', to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'Stmt ID', '$$stmtId', 'Y', null, to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'Account ID', '$$accId', 'Y', null, to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'Account ID', '$$accId', 'Y', null, to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
commit;
prompt 100 records committed...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUHAILA', 'SPTN', '$$sptn', 'Y', null, to_date('18-07-2005 09:45:09', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'Account ID', '$$accId', 'Y', null, to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'SPTN', '$$sptn', 'Y', null, to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'Biller ID', '$$billerId', 'Y', '005', to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('HODA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('18-07-2005 10:24:02', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'Account ID', '$$accId', 'Y', null, to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'SPTN', '$$sptn', 'Y', null, to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'Biller ID', '$$billerId', 'Y', '005', to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('EMAN', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('19-07-2005 10:59:14', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'Stmt ID', '$$stmtId', 'Y', null, to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'SPTN', '$$sptn', 'Y', null, to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'Biller ID', '$$billerId', 'Y', '005', to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'Biller ID', '$$billerId', 'Y', '005', to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('MAULIK', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('25-07-2005 14:48:16', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'Account ID', '$$accId', 'Y', null, to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('NADA', 'SPTN', '$$sptn', 'Y', null, to_date('31-07-2005 16:32:25', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'Biller ID', '$$billerId', 'Y', '005', to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'Stmt ID', '$$stmtId', 'Y', null, to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'SPTN', '$$sptn', 'Y', null, to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'Biller ID', '$$billerId', 'Y', '005', to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , uname || ''-'' || EXTERNALKEY from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('LINA', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('02-08-2005 13:51:52', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'Account ID', '$$accId', 'Y', null, to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'Stmt ID', '$$stmtId', 'Y', null, to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'SPTN', '$$sptn', 'Y', null, to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('SUPPORT', 'Object Name', '$$obj_name', 'Y', 'abc', to_date('08-08-2005 12:32:23', 'dd-mm-yyyy hh24:mi:ss'), 8, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BILLER', 'Msg Code', '$$msg_code', 'Y', null, to_date('06-04-2006 17:37:09', 'dd-mm-yyyy hh24:mi:ss'), 3, 'List', 'select msg_code , msg_code  from msg_codes ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BILLER', 'From Date', '$$from_date', 'Y', 'sysdate', to_date('06-04-2006 17:37:09', 'dd-mm-yyyy hh24:mi:ss'), 1, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('BILLER', 'To Date', '$$to_date', 'Y', 'sysdate', to_date('06-04-2006 17:37:09', 'dd-mm-yyyy hh24:mi:ss'), 2, 'Date', null);
commit;
prompt 143 records loaded
prompt Loading SYS_PARAMS...
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('Support', 'General', '1', 2, 'T', null, 'SMTP_SERVER', 'SMTP_SERVER', '10.16.18.12', 'IP Address of SMTP Mail Server ', 'IP Address of SMTP Mail Server ', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('Support', 'General', '1', 3, 'T', null, 'Support User Email', 'Smart Report User Email', 'smart_reports@sadad.com', 'Smart Report User Email', 'Smart Report User Email', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('SADAD', 'FTP', '1', 4, 'T', null, 'SFS_Server_IP_Prod1', 'SFS_Server_IP_Prod1', '10.16.17.9', 'SFS_Server_IP_Prod1', 'SFS_Server_IP_Prod1', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('SADAD', 'FTP', '1', 5, 'T', null, 'SFS_Server_IP_Prod2', 'SFS_Server_IP_Prod2', '10.16.17.13', 'SFS_Server_IP_Prod2', 'SFS_Server_IP_Prod2', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('SADAD', 'FTP', '1', 6, 'T', null, 'SFS_Server_IP_UserName1', 'SFS_Server_IP_UserName1', 'wasadm', 'SFS_Server_IP_UserName1', 'SFS_Server_IP_UserName1', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('Support', 'General', '1', 7, 'T', null, 'Admin_Notify_Mail_Address_Receiver', 'Admin_Notify_Mail_Address_Receiver', 'sfoda@sadad.com', 'Admin_Notify_Mail_Address_Receiver', 'Admin_Notify_Mail_Address_Receiver', 0, 0);
commit;
prompt 6 records loaded
prompt Loading TBLSYS_SCRIPT...
prompt Table is empty
prompt Enabling foreign key constraints for LU_EXECUTABLES...
alter table LU_EXECUTABLES enable constraint FK_LU_EXECUTABLES;
alter table LU_EXECUTABLES enable constraint FK_LU_EXECUTABLES_LU_ROOT;
prompt Enabling triggers for LU_EXECUTABLES...
alter table LU_EXECUTABLES enable all triggers;
prompt Enabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS enable all triggers;
prompt Enabling triggers for SYS_PARAMS...
alter table SYS_PARAMS enable all triggers;
prompt Enabling triggers for TBLSYS_SCRIPT...
alter table TBLSYS_SCRIPT enable all triggers;
set feedback on
set define on
prompt Done.
