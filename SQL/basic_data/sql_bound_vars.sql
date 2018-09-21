prompt PL/SQL Developer import file
prompt Created on 07 August 2004 by FODA_SH
set feedback off
set define off
prompt Disabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS disable all triggers;
prompt Deleting SQL_BOUND_VARS...
delete from SQL_BOUND_VARS;
commit;
prompt Loading SQL_BOUND_VARS...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Quotation', '&q', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Endt no', '&endt_no', 'Y', '0', to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Cert No', '&cert_no', 'Y', '0', to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Claim No', '&cl_no', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Cust ID', '&cust_id', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Table Name', '&tab_name', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Policy No', '&pno', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Branch No', '&br', 'Y', null, to_date('04-08-2004 14:34:10', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Quotation', '&q', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Endt no', '&endt_no', 'Y', '0', to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Cert No', '&cert_no', 'Y', '0', to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Claim No', '&cl_no', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Cust ID', '&cust_id', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Table Name', '&tab_name', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Policy No', '&pno', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Branch No', '&br', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Quotation', '&q', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Endt no', '&endt_no', 'N', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Cert No', '&cert_no', 'Y', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Claim No', '&cl_no', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Cust ID', '&cust_id', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Table Name', '&tab_name', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Policy No', '&pno', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Branch No', '&br', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Quotation', '&q', 'Y', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Endt no', '&endt_no', 'Y', '0', to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Cert No', '&cert_no', 'Y', '0', to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Claim No', '&cl_no', 'Y', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Cust ID', '&cust_id', 'N', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Table Name', '&tab_name', 'N', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Policy No', '&pno', 'Y', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Branch No', '&br', 'Y', null, to_date('04-08-2004 10:26:59', 'dd-mm-yyyy hh24:mi:ss'), 2);
commit;
prompt 32 records loaded
prompt Enabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS enable all triggers;
set feedback on
set define on
prompt Done.
