prompt PL/SQL Developer import file
prompt Created on Sunday, May 01, 2005 by foda_sh
set feedback off
set define off
prompt Disabling triggers for SYS_PARAMS...
alter table SYS_PARAMS disable all triggers;
prompt Deleting SYS_PARAMS...
delete from SYS_PARAMS;
commit;
prompt Loading SYS_PARAMS...
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('Support', 'General', 1, 2, 'T', null, 'SMTP_SERVER', 'SMTP_SERVER', '130.1.2.36', 'IP Address of SMTP Mail Server ', 'IP Address of SMTP Mail Server ', 0, 0);
insert into SYS_PARAMS (SYS_CODE, MOD_CODE, USR_ID, ID, TYP, SECT, E_NAME, A_NAME, VAL, E_DSC, A_DSC, MODIFY_SEQ, UPDATE_STATUS)
values ('Support', 'General', 1, 3, 'T', null, 'Admin_Notify_Mail_Address', 'Admin_Notify_Mail_Address', 'foda_sh@ncci.com.sa', 'Admin_Notify_Mail_Address', 'Admin_Notify_Mail_Address', 0, 0);
commit;
prompt 2 records loaded
prompt Enabling triggers for SYS_PARAMS...
alter table SYS_PARAMS enable all triggers;
set feedback on
set define on
prompt Done.
