prompt PL/SQL Developer import file
prompt Created on Monday, March 14, 2005 by foda_sh
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
  SN             NUMBER,
  DATA_TYPE      VARCHAR2(20)
)
;
alter table SQL_BOUND_VARS
  add constraint OWNER_BOUND_VAR_UK unique (OWNER,BOUND_VAR_NAME);

prompt Disabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS disable all triggers;
prompt Loading SQL_BOUND_VARS...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Quotation', '$$q', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Endt no', '$$endt_no', 'Y', '0', to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Cert No', '$$cert_no', 'Y', '0', to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Claim No', '$$cl_no', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Cust ID', '$$cust_id', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Table Name', '$$tab_name', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Policy No', '$$pno', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Branch No', '$$br', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS302', 'Period', '$$per', 'Y', null, to_date('02-10-2004 11:11:19', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Quotation', '$$q', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Endt no', '$$endt_no', 'Y', '0', to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Cert No', '$$cert_no', 'Y', '0', to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Claim No', '$$cl_no', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Cust ID', '$$cust_id', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Table Name', '$$tab_name', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Request No', '$Req_no', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Period', '$$per', 'Y', null, to_date('26-10-2004 09:53:44', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Quotation', '$$q', 'Y', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Endt no', '$$endt_no', 'Y', '0', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Cert No', '$$cert_no', 'Y', '0', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Policy No', '$$pno', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('EDGE', 'Branch No', '$$br', 'Y', null, to_date('26-10-2004 09:53:43', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Claim No', '$$cl_no', 'N', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Cust ID', '$$cust_id', 'Y', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Table Name', '$$tab_name', 'N', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Object Name', '$$obj_name', 'Y', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Risk No', '$$r_no', 'N', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Edge User', '$$User', 'Y', 'OS310', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 12, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Period(Days)', '$$per', 'N', '1', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 13, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'from_date', '$$from_date', 'Y', '01-Mar-2005', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 14, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'to_date', '$$to_date', 'Y', '31-Mar-2005', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 15, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Policy No', '$$pno', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Branch No', '$$br', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Quotation', '$$q', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Endt no', '$$endt_no', 'Y', '0', to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Cert No', '$$cert_no', 'Y', '0', to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Claim No', '$$cl_no', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Cust ID', '$$cust_id', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('SADAD', 'Table Name', '$$tab_name', 'Y', null, to_date('16-08-2004 08:53:52', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'batch_ref', '$$batch_ref', 'Y', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 16, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Quotation', '$$q', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Endt no', '$$endt_no', 'N', '0', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Policy No', '$$pno', 'Y', '2960613', to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS310', 'Branch No', '$$br', 'Y', null, to_date('05-03-2005 14:45:39', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Cert No', '$$cert_no', 'N', '0', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Claim No', '$$cl_no', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Cust ID', '$$cust_id', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Quotation', '$$q', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Endt no', '$$endt_no', 'N', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Cert No', '$$cert_no', 'Y', '0', to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Claim No', '$$cl_no', 'N', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Cust ID', '$$cust_id', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Table Name', '$$tab_name', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Policy No', '$$pno', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CSC', 'Branch No', '$$br', 'Y', null, to_date('04-08-2004 11:42:52', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Policy No', '$$pno', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Branch No', '$$br', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Quotation', '$$q', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Endt no', '$$endt_no', 'Y', '0', to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Cert No', '$$cert_no', 'Y', '0', to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Claim No', '$$cl_no', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Cust ID', '$$cust_id', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('OS303', 'Table Name', '$$tab_name', 'Y', null, to_date('11-12-2004 16:17:42', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Table Name', '$$tab_name', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Object Name', '$$obj_name', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Policy No', '$$pno', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Branch No', '$$br', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Quotation', '$$q', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Endt no', '$$endt_no', 'Y', '0', to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Cert No', '$$cert_no', 'Y', '0', to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Claim No', '$$cl_no', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Cust ID', '$$cust_id', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DM204576', 'Table Name', '$$tab_name', 'Y', null, to_date('10-01-2005 16:31:36', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Quotation', '$$q', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Endt no', '$$endt_no', 'Y', '0', to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Cert No', '$$cert_no', 'Y', '0', to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Claim No', '$$cl_no', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Cust ID', '$$cust_id', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Table Name', '$$tab_name', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Policy No', '$$pno', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MA203685', 'Branch No', '$$br', 'Y', null, to_date('17-01-2005 08:35:22', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Risk No', '$$r_no', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Quotation', '$$q', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Endt no', '$$endt_no', 'Y', '0', to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Cert No', '$$cert_no', 'Y', '0', to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Claim No', '$$cl_no', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Cust ID', '$$cust_id', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Table Name', '$$ab_name', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Policy No', '$$pno', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('TAJ', 'Branch No', '$$br', 'Y', null, to_date('22-02-2005 16:35:23', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Quotation', '$$q', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Endt no', '$$endt_no', 'Y', '0', to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Cert No', '$$cert_no', 'Y', '0', to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Claim No', '$$cl_no', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Cust ID', '$$cust_id', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Table Name', '$$tab_name', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Quotation', '$$q', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Endt no', '$$endt_no', 'Y', '0', to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Policy No', '$$pno', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
commit;
prompt 100 records committed...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WAHEDK', 'Branch No', '$$br', 'Y', null, to_date('27-02-2005 15:26:37', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Cert No', '$$cert_no', 'Y', '0', to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Claim No', '$$cl_no', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Cust ID', '$$cust_id', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Table Name', '$$tab_name', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'to_date', '$$to_date', 'Y', '28-02-2005', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 15, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Endt no', '$$endt_no', 'Y', '0', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Policy No', '$$pno', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('BAT_USER', 'Branch No', '$$br', 'Y', null, to_date('28-02-2005 19:23:50', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Quotation', '$$q', 'Y', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'from_date', '$$from_date', 'Y', '01-02-2005', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 14, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Edge User', '$$User', 'Y', 'OS310', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 12, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Branch No', '$$br', 'Y', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Period(Days)', '$$per', 'N', '1', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 13, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Cert No', '$$cert_no', 'Y', '0', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Risk No', '$$r_no', 'N', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Policy No', '$$pno', 'Y', '2960613', to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'batch_ref', '$$batch_ref', 'Y', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 16, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Object Name', '$$obj_name', 'Y', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Claim No', '$$cl_no', 'N', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Cust ID', '$$cust_id', 'Y', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('DEFAULT', 'Table Name', '$$tab_name', 'N', null, to_date('19-02-2005 12:42:46', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Edge User', '$$User', 'N', 'OS310', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 12, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Period(Days)', '$$per', 'N', '1', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 13, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'from_date', '$$from_date', 'Y', '01-02-2005', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 14, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'to_date', '$$to_date', 'Y', '28-02-2005', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 15, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'batch_ref', '$$batch_ref', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 16, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Quotation', '$$q', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Endt no', '$$endt_no', 'N', '0', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Policy No', '$$pno', 'Y', '366482', to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('MK100801', 'Branch No', '$$br', 'N', null, to_date('07-03-2005 16:02:42', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Cert No', '$$cert_no', 'N', '0', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Claim No', '$$cl_no', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Cust ID', '$$cust_id', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Table Name', '$$tab_name', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Object Name', '$$obj_name', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Risk No', '$$r_no', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Edge User', '$$User', 'N', 'OS310', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 12, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Period(Days)', '$$per', 'N', '1', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 13, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'from_date', '$$from_date', 'Y', '01-02-2005', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 14, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'to_date', '$$to_date', 'Y', '28-02-2005', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 15, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'batch_ref', '$$batch_ref', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 16, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Policy No', '$$pno', 'Y', '2960613', to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('CE204615', 'Branch No', '$$br', 'N', null, to_date('08-03-2005 11:22:27', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Quotation', '$$q', 'Y', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 3, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Endt no', '$$endt_no', 'Y', '0', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 4, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Cert No', '$$cert_no', 'Y', '0', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 6, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Claim No', '$$cl_no', 'N', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 7, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Cust ID', '$$cust_id', 'Y', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 8, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Table Name', '$$tab_name', 'N', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 9, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Object Name', '$$obj_name', 'Y', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 10, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Risk No', '$$r_no', 'N', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 11, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Edge User', '$$User', 'Y', 'OS310', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 12, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Period(Days)', '$$per', 'N', '1', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 13, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'from_date', '$$from_date', 'Y', '01-02-2005', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 14, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'to_date', '$$to_date', 'Y', '28-02-2005', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 15, 'date');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'batch_ref', '$$batch_ref', 'N', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 16, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Policy No', '$$pno', 'Y', '2960613', to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 1, 'text');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE)
values ('WA1001454', 'Branch No', '$$br', 'Y', null, to_date('13-03-2005 10:26:15', 'dd-mm-yyyy hh24:mi:ss'), 2, 'text');
commit;
prompt 158 records loaded
prompt Enabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS enable all triggers;
set feedback on
set define on
prompt Done.
