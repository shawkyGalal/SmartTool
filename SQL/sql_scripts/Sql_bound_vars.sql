prompt PL/SQL Developer import file
prompt Created on 08 Ì‰«Ì—, 2005 by foda_sh
set feedback off
set define off
prompt Dropping SQL_BOUND_VARS...
-- drop table SQL_BOUND_VARS cascade constraints;
prompt Creating SQL_BOUND_VARS...
create table SQL_BOUND_VARS
(
  OWNER          VARCHAR2(12) not null,
  TITLE          VARCHAR2(50),
  BOUND_VAR_NAME VARCHAR2(12),
  ACTIVE         VARCHAR2(1),
  DEFAULT_VAL    VARCHAR2(20),
  CREATION_DATE  DATE default sysdate,
  SN             NUMBER
);
alter table SQL_BOUND_VARS add constraint OWNER_BOUND_VAR_UK unique (OWNER,BOUND_VAR_NAME);

prompt Disabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS disable all triggers;
prompt Loading SQL_BOUND_VARS...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Quotation', '$q', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Endt no', '$endt_no', 'Y', '0', to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Cert No', '$cert_no', 'Y', '0', to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Claim No', '$cl_no', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Cust ID', '$cust_id', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Table Name', '$tab_name', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Policy No', '$pno', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Branch No', '$br', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS302', 'Period', '$per', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 10);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Quotation', '$q', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Endt no', '$endt_no', 'Y', '0', to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Cert No', '$cert_no', 'Y', '0', to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Claim No', '$cl_no', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Cust ID', '$cust_id', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Table Name', '$tab_name', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Request No', '$Req_no', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 10);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Period', '$per', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 11);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Quotation', '$q', 'Y', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Endt no', '$endt_no', 'Y', '0', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Cert No', '$cert_no', 'Y', '0', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Policy No', '$pno', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('EDGE', 'Branch No', '$br', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Claim No', '$cl_no', 'N', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Cust ID', '$cust_id', 'Y', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Table Name', '$tab_name', 'N', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Object Name', '$obj_name', 'Y', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 10);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Risk No', '$r_no', 'N', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 11);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Edge User', '$User', 'Y', 'OS310', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 12);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Period(Days)', '$per', 'Y', '1', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 13);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'from_date', '$from_date', 'Y', '01-01-2005', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 14);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'to_date', '$to_date', 'Y', '02-01-2005', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 15);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Policy No', '$pno', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Branch No', '$br', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Quotation', '$q', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Endt no', '$endt_no', 'Y', '0', to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Cert No', '$cert_no', 'Y', '0', to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Claim No', '$cl_no', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Cust ID', '$cust_id', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('SADAD', 'Table Name', '$tab_name', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Quotation', '$q', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Endt no', '$endt_no', 'Y', '0', to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Cert No', '$cert_no', 'Y', '0', to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Claim No', '$cl_no', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Cust ID', '$cust_id', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Table Name', '$tab_name', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Policy No', '$pno', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('DEFAULT', 'Branch No', '$br', 'Y', null, to_date('03-08-2004 19:06:49', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Quotation', '$q', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Endt no', '$endt_no', 'N', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Cert No', '$cert_no', 'Y', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Claim No', '$cl_no', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Cust ID', '$cust_id', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Table Name', '$tab_name', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Policy No', '$pno', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('CSC', 'Branch No', '$br', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Policy No', '$pno', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Branch No', '$br', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Quotation', '$q', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Endt no', '$endt_no', 'Y', '0', to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Cert No', '$cert_no', 'Y', '0', to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 6);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Claim No', '$cl_no', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 7);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Cust ID', '$cust_id', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 8);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS303', 'Table Name', '$tab_name', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 9);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Policy No', '$pno', 'Y', '2960613', to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN)
values ('OS310', 'Branch No', '$br', 'Y', null, to_date('08-01-2005 10:21:21', 'dd-mm-yyyy hh24:mi:ss'), 2);
commit;
prompt 65 records loaded
prompt Enabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS enable all triggers;
set feedback on
set define on
prompt Done.
