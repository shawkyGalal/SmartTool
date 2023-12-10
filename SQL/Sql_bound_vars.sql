prompt PL/SQL Developer import file
prompt Created on Thursday, July 07, 2005 by shawky.foda
set feedback off
set define off
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
  DATA_TYPE      VARCHAR2(20),
  QUERY_FOR_LIST VARCHAR2(400)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SQL_BOUND_VARS
  add constraint OWNER_BOUND_VAR_UK unique (OWNER,BOUND_VAR_NAME)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS disable all triggers;
prompt Deleting SQL_BOUND_VARS...
delete from SQL_BOUND_VARS;
commit;
prompt Loading SQL_BOUND_VARS...
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Account ID', '$$accId', 'Y', null, to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Stmt ID', '$$stmtId', 'Y', null, to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'From Date', '$$from_date', 'Y', '07-07-2005', to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'To Date', '$$to_date', 'Y', '07-07-2005', to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'From Date', '$$from_date', 'Y', '07-07-2005', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 5, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Stmt ID', '$$stmtId', 'Y', null, to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 4, 'stmtId', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'SPTN', '$$SPTN', 'Y', null, to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 7, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'To Date', '$$to_date', 'Y', '07-07-2005', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 6, 'Date', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Biller ID', '$$billerId', 'Y', '005', to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('DEFAULT', 'Account ID', '$$accId', 'Y', null, to_date('07-07-2005 17:29:14', 'dd-mm-yyyy hh24:mi:ss'), 3, 'Text', null);
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Biller ID', '$$billerId', 'Y', '005', to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 1, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B65'' and 1=1 ');
insert into SQL_BOUND_VARS (OWNER, TITLE, BOUND_VAR_NAME, ACTIVE, DEFAULT_VAL, CREATION_DATE, SN, DATA_TYPE, QUERY_FOR_LIST)
values ('CRYSTAL', 'Bank ID', '$$BankId', 'Y', 'SAMBSARI', to_date('07-07-2005 18:04:52', 'dd-mm-yyyy hh24:mi:ss'), 2, 'List', 'select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = ''B64'' and 1=1 ');
commit;
prompt 13 records loaded
prompt Enabling triggers for SQL_BOUND_VARS...
alter table SQL_BOUND_VARS enable all triggers;
set feedback on
set define on
prompt Done.
