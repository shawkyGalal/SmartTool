------------------------------------------------------
-- Export file for user SUPPORT                     --
-- Created by shawky.foda on 9/10/2006, 11:05:29 AM --
------------------------------------------------------

spool All_Support_objects.log

prompt
prompt Creating table COMPANIES_INFO
prompt =============================
prompt
create table COMPANIES_INFO
(
  SYMBOLE      NUMBER,
  COMPANY_NAME VARCHAR2(100)
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

prompt
prompt Creating table EXEC_STAT
prompt ========================
prompt
create table EXEC_STAT
(
  QUERYID     NUMBER,
  EXECUTED_BY VARCHAR2(30),
  STARTTIME   DATE,
  ENDTIME     DATE,
  BOUNDVARS   VARCHAR2(1000),
  SEQUANCE    NUMBER,
  DB_URL      VARCHAR2(100)
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
create index QUERY_ID_IX on EXEC_STAT (QUERYID)
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

prompt
prompt Creating table LU_ROOT
prompt ======================
prompt
create table LU_ROOT
(
  ID             NUMBER not null,
  CODE           VARCHAR2(20) not null,
  PARENT_ID      NUMBER,
  PREV_PARENT_ID NUMBER,
  HEADER_ID      NUMBER,
  PREV_HEADER_ID NUMBER,
  A_DSC          VARCHAR2(200) not null,
  E_DSC          VARCHAR2(200) not null,
  NOTE_NOTES_ID  NUMBER,
  ACTIVE         CHAR(1) default 'T',
  DELETED        CHAR(1) default 'F',
  MODIFY_SEQ     NUMBER default 0,
  UPDATE_STATUS  NUMBER default 0
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 32K
    minextents 1
    maxextents unlimited
  );
comment on table LU_ROOT
  is ' ROOT  («·ﬁ«⁄œ… )';
comment on column LU_ROOT.ID
  is 'Primary Key';
comment on column LU_ROOT.CODE
  is 'User Code';
comment on column LU_ROOT.PARENT_ID
  is 'Parent Record';
comment on column LU_ROOT.A_DSC
  is 'Arabic Desc';
comment on column LU_ROOT.E_DSC
  is 'English Desc';
comment on column LU_ROOT.ACTIVE
  is 'For User Activation';
comment on column LU_ROOT.DELETED
  is 'For Logical Deletion';
alter table LU_ROOT
  add constraint PK_LU_ROOT primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 32K
    minextents 1
    maxextents unlimited
  );
alter table LU_ROOT
  add constraint UQ_LU_ROOT_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 32K
    minextents 1
    maxextents unlimited
  );
alter table LU_ROOT
  add constraint FK_LU_ROOT foreign key (PARENT_ID)
  references LU_ROOT (ID);
alter table LU_ROOT
  add constraint CHK_LU_ROOT_ACTIVE
  check (Active='F'or Active='T');
alter table LU_ROOT
  add constraint CHK_LU_ROOT_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_ROOT
  add constraint CHK_LU_ROOT_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_EXECUTABLES
prompt =============================
prompt
create table LU_EXECUTABLES
(
  ID              NUMBER not null,
  CODE            VARCHAR2(200) not null,
  PARENT_ID       NUMBER,
  PREV_PARENT_ID  NUMBER,
  HEADER_ID       NUMBER,
  PREV_HEADER_ID  NUMBER,
  A_DSC           VARCHAR2(200) not null,
  E_DSC           VARCHAR2(200) not null,
  NOTE_NOTES_ID   NUMBER,
  ACTIVE          CHAR(1) default 'T',
  DELETED         CHAR(1) default 'F',
  MODIFY_SEQ      NUMBER default 0,
  UPDATE_STATUS   NUMBER default 0,
  EXEC_BODY       VARCHAR2(4000),
  OWNER           VARCHAR2(100) default user,
  MSG             VARCHAR2(400),
  HYPERLINK_TITLE VARCHAR2(400)
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
comment on table LU_EXECUTABLES
  is 'User Executables';
comment on column LU_EXECUTABLES.ID
  is 'Primary Key';
comment on column LU_EXECUTABLES.CODE
  is 'User Code';
comment on column LU_EXECUTABLES.PARENT_ID
  is 'Parent Record';
comment on column LU_EXECUTABLES.HEADER_ID
  is 'LU_ROOT';
comment on column LU_EXECUTABLES.A_DSC
  is 'Arabic Desc';
comment on column LU_EXECUTABLES.E_DSC
  is 'English Desc';
comment on column LU_EXECUTABLES.ACTIVE
  is 'For User Activation';
comment on column LU_EXECUTABLES.DELETED
  is 'For Logical Deletion';
comment on column LU_EXECUTABLES.MSG
  is 'Message displayed when User tries to Execute This Executable';
alter table LU_EXECUTABLES
  add constraint PK_LU_EXECUTABLES primary key (ID)
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
alter table LU_EXECUTABLES
  add constraint UQ_LU_EXECUTABLES_CODE unique (CODE,PARENT_ID)
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
alter table LU_EXECUTABLES
  add constraint FK_LU_EXECUTABLES foreign key (PARENT_ID)
  references LU_EXECUTABLES (ID) on delete cascade;
alter table LU_EXECUTABLES
  add constraint FK_LU_EXECUTABLES_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_EXECUTABLES
  add constraint CHK_LU_EXECUTABLES_ACTIVE
  check (Active='F'or Active='T');
alter table LU_EXECUTABLES
  add constraint CHK_LU_EXECUTABLES_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_EXECUTABLES
  add constraint CHK_LU_EXECUTABLES_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_QUERIES
prompt =========================
prompt
create table LU_QUERIES
(
  ID              NUMBER not null,
  CODE            VARCHAR2(200) not null,
  PARENT_ID       NUMBER,
  PREV_PARENT_ID  NUMBER,
  HEADER_ID       NUMBER,
  PREV_HEADER_ID  NUMBER,
  A_DSC           VARCHAR2(200) not null,
  E_DSC           VARCHAR2(200) not null,
  NOTE_NOTES_ID   NUMBER,
  ACTIVE          CHAR(1) default 'T',
  DELETED         CHAR(1) default 'F',
  MODIFY_SEQ      NUMBER default 0,
  UPDATE_STATUS   NUMBER default 0,
  OWNER           VARCHAR2(100) default user,
  QUERY_BODY_1    VARCHAR2(4000),
  QUERY_BODY_CLOB CLOB,
  QUERY_BODY      CLOB,
  HYPERLINK_TITLE VARCHAR2(300)
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
comment on table LU_QUERIES
  is 'User Queries  («·«” ⁄·«„«  )';
comment on column LU_QUERIES.ID
  is 'Primary Key';
comment on column LU_QUERIES.CODE
  is 'User Code';
comment on column LU_QUERIES.PARENT_ID
  is 'Parent Record';
comment on column LU_QUERIES.HEADER_ID
  is 'LU_ROOT';
comment on column LU_QUERIES.A_DSC
  is 'Arabic Desc';
comment on column LU_QUERIES.E_DSC
  is 'English Desc';
comment on column LU_QUERIES.ACTIVE
  is 'For User Activation';
comment on column LU_QUERIES.DELETED
  is 'For Logical Deletion';
alter table LU_QUERIES
  add constraint PK_LU_QUERIES primary key (ID)
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
alter table LU_QUERIES
  add constraint UQ_LU_QUERIES_CODE unique (CODE,PARENT_ID)
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
alter table LU_QUERIES
  add constraint FK_LU_QUERIES foreign key (PARENT_ID)
  references LU_QUERIES (ID) on delete cascade;
alter table LU_QUERIES
  add constraint FK_LU_QUERIES_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_QUERIES
  add constraint CHK_LU_QUERIES_ACTIVE
  check (Active='F'or Active='T');
alter table LU_QUERIES
  add constraint CHK_LU_QUERIES_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_QUERIES
  add constraint CHK_LU_QUERIES_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_QUERIES_BKUP
prompt ==============================
prompt
create table LU_QUERIES_BKUP
(
  ID              NUMBER not null,
  CODE            VARCHAR2(200) not null,
  PARENT_ID       NUMBER,
  PREV_PARENT_ID  NUMBER,
  HEADER_ID       NUMBER,
  PREV_HEADER_ID  NUMBER,
  A_DSC           VARCHAR2(200) not null,
  E_DSC           VARCHAR2(200) not null,
  NOTE_NOTES_ID   NUMBER,
  ACTIVE          CHAR(1) default 'T',
  DELETED         CHAR(1) default 'F',
  MODIFY_SEQ      NUMBER default 0,
  UPDATE_STATUS   NUMBER default 0,
  OWNER           VARCHAR2(100) default user,
  QUERY_BODY_1    VARCHAR2(4000),
  QUERY_BODY_CLOB CLOB,
  QUERY_BODY      CLOB
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
comment on table LU_QUERIES_BKUP
  is 'User Queries  («·«” ⁄·«„«  )';
comment on column LU_QUERIES_BKUP.ID
  is 'Primary Key';
comment on column LU_QUERIES_BKUP.CODE
  is 'User Code';
comment on column LU_QUERIES_BKUP.PARENT_ID
  is 'Parent Record';
comment on column LU_QUERIES_BKUP.HEADER_ID
  is 'LU_ROOT';
comment on column LU_QUERIES_BKUP.A_DSC
  is 'Arabic Desc';
comment on column LU_QUERIES_BKUP.E_DSC
  is 'English Desc';
comment on column LU_QUERIES_BKUP.ACTIVE
  is 'For User Activation';
comment on column LU_QUERIES_BKUP.DELETED
  is 'For Logical Deletion';
alter table LU_QUERIES_BKUP
  add constraint PK_LU_QUERIES_BKUP primary key (ID)
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
alter table LU_QUERIES_BKUP
  add constraint UQ_LU_QUERIES_BKUP_CODE unique (CODE,PARENT_ID)
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
alter table LU_QUERIES_BKUP
  add constraint FK_LU_QUERIES_BKUP foreign key (PARENT_ID)
  references LU_QUERIES (ID) on delete cascade;
alter table LU_QUERIES_BKUP
  add constraint FK_LU_QUERIES__BKUP_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_QUERIES_BKUP
  add constraint CHK_LU_QUERIES_BKUP_ACTIVE
  check (Active='F'or Active='T');
alter table LU_QUERIES_BKUP
  add constraint CHK_LU_QUERIES_BKUP_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_QUERIES_BKUP
  add constraint CHK_LU_QUERIES_BKUP_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table PS_TXN
prompt =====================
prompt
create table PS_TXN
(
  ID            NUMBER(20) not null,
  PARENTID      NUMBER(20),
  COLLID        NUMBER(10) not null,
  CONTENT       BLOB,
  CREATION_DATE DATE default sysdate
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
alter table PS_TXN
  add constraint PS_TXN_PK primary key (COLLID,ID)
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

prompt
prompt Creating table SINGLE_SIGN_IN
prompt =============================
prompt
create table SINGLE_SIGN_IN
(
  SERVER        VARCHAR2(40) not null,
  ACCESS_TYPE   VARCHAR2(50),
  PORT_NO       NUMBER,
  USERNAME      VARCHAR2(30) not null,
  PASSWORD      VARCHAR2(30) not null,
  SERVER_NAME   VARCHAR2(10),
  SERVER_ALT_IP VARCHAR2(40)
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

prompt
prompt Creating table SQL_BOUND_VARS
prompt =============================
prompt
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

prompt
prompt Creating table STOCK_INFO
prompt =========================
prompt
create table STOCK_INFO
(
  SYMBOL            VARCHAR2(5),
  PRICE_TIME        DATE default sysdate,
  NO_OF_TRADES      NUMBER,
  VOLUME_TRADED     NUMBER,
  BEST_BID_PRICE    NUMBER,
  BEST_BID_VOLUME   NUMBER,
  BEST_OFFER_PRICE  NUMBER,
  BEST_OFFER_VOLUMN NUMBER,
  LAST_TRADE_VOLS   NUMBER,
  PRICE             NUMBER
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
create index PRICE_TIME_IX on STOCK_INFO (PRICE_TIME)
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
create index SYMBOL_IX on STOCK_INFO (SYMBOL)
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

prompt
prompt Creating table SYS_PARAMS
prompt =========================
prompt
create table SYS_PARAMS
(
  SYS_CODE      VARCHAR2(50) not null,
  MOD_CODE      VARCHAR2(50) not null,
  USR_ID        VARCHAR2(20),
  ID            NUMBER not null,
  TYP           CHAR(1) default 'T' not null,
  SECT          VARCHAR2(50),
  E_NAME        VARCHAR2(50) not null,
  A_NAME        VARCHAR2(50) not null,
  VAL           VARCHAR2(50),
  E_DSC         VARCHAR2(200) not null,
  A_DSC         VARCHAR2(200) not null,
  MODIFY_SEQ    NUMBER default 0,
  UPDATE_STATUS NUMBER default 0
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
comment on table SYS_PARAMS
  is 'System Parameters';
comment on column SYS_PARAMS.TYP
  is 'Interface type T text,N Number, S Select, C Check Box, D Date';
alter table SYS_PARAMS
  add constraint PK_SPARAM primary key (SYS_CODE,ID)
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
alter table SYS_PARAMS
  add constraint UQ_SAPARAM_NAME unique (A_NAME)
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
alter table SYS_PARAMS
  add constraint UQ_SEPARAM_NAME unique (E_NAME)
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

prompt
prompt Creating table TBLSYS_SCRIPT
prompt ============================
prompt
create table TBLSYS_SCRIPT
(
  LINE VARCHAR2(2000),
  ID   NUMBER not null
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
alter table TBLSYS_SCRIPT
  add constraint PK_SYS_SCRIPT primary key (ID)
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

prompt
prompt Creating sequence AUD$_SEQ
prompt ==========================
prompt
create sequence AUD$_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence LU_EXECUTABLES_SEQ
prompt ====================================
prompt
create sequence LU_EXECUTABLES_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 8066
increment by 1
cache 20;

prompt
prompt Creating sequence LU_QUERIES_SEQ
prompt ================================
prompt
create sequence LU_QUERIES_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 11523
increment by 1
cache 20;

prompt
prompt Creating sequence PS_TXN_SEQ
prompt ============================
prompt
create sequence PS_TXN_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SYS_PARAMS_SEQ
prompt ================================
prompt
create sequence SYS_PARAMS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SYS_SCRIPT_SEQ
prompt ================================
prompt
create sequence SYS_SCRIPT_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 81
increment by 1
cache 20;

prompt
prompt Creating function GET_YESTERDAY_CLOSE_VALUE
prompt ===========================================
prompt
create or replace function GET_YESTERDAY_CLOSE_VALUE(m_symbol number ,OPEN_Date date) return number is
  Result number;
  
begin
  
  
  
  SELECT PRICE INTO RESULT FROM STOCK_INFO T WHERE 
  T.PRICE_TIME = 
               (SELECT MAX(S.PRICE_TIME) 
                 FROM STOCK_INFO S 
                 WHERE S.PRICE_TIME between  TRUNC(OPEN_Date)-decode ( to_char(sysdate , 'DAY') , 'SATURDAY ' , 3 , 1 ) and TRUNC(OPEN_Date)
                 AND S.SYMBOL = M_SYMBOL 
                 AND S.PRICE <> 0
                 AND S.SYMBOL = T.SYMBOL
                )
  AND T.SYMBOL = m_symbol;
  return(Result);
end GET_YESTERDAY_CLOSE_VALUE;
/

prompt
prompt Creating view CLOSING_PRICES
prompt ============================
prompt
create or replace view closing_prices as
select symbole, working_day-1  working_Day , get_yesterday_close_value(c.symbole, working_day ) close_price  
  from 
  ( select distinct trunc(price_time)  working_day from stock_info t ) -- 
  , companies_info c
   where symbole <> '0000'
    and symbole not like '1%'
    and working_day is not null
/

prompt
prompt Creating view MAX_CLOSE_PRICES
prompt ==============================
prompt
create or replace view max_close_prices as
select symbole ,  max(close_price)  max_close_price 
 from closing_prices cp  
 group by symbole
/

prompt
prompt Creating package MISC
prompt =====================
prompt
create or replace package Misc is

  -- Author  : AMR.FADLY
  -- Modified bY Shawky Foda
  -- Created : 3/22/2001 10:50:54 AM
  -- Purpose : Functions to be included in Select Statments
Type TItem_Acc  is VARRAY(100) of VARCHAR2(10);

function get_tree_path(
/*  This Function return a full tree path
    form any table that has parent_id = id
    the path could be constructed from two parts
    1- a series of col1
    2- the last item as Col2
-----------------------------------------------*/
   pTableName IN VARCHAR2,
   pID        IN NUMBER,
   pCol1      IN VARCHAR2,
   pCol2      IN CHAR )
return varchar2;
--==============================

function get_tree_Element_Level(
/*  This Function return a full tree path
    form any table that has parent_id = id
    the path could be constructed from two parts
    1- a series of col1   -- no Need
    2- the last item as Col2 -- no Need
-----------------------------------------------*/
   pTableName IN VARCHAR2,
   pID        IN NUMBER,
   pCol1      IN VARCHAR2,
   pCol2      IN CHAR )
return varchar2;
--==============================
procedure Restore_Rec
/*
This function Restore a logically deleted record
it returns: -1 can not restore (Parent logically deleted);
0 No record ; 1 Restored
*/
(  pTable  in   varchar2,
   pKey    IN   varchar2,
   pid     in   number,
   pOut    OUT  number
);
--==============================
Procedure Delete_All_Logical;
/*  Delete all Logically deleted record
in all tables owned by current user.
*/
--==============================
Function Check_Ref
/*
This function Checks if a Record has reference in other or same tables
It Returns False for no and True for yes.
*/
(  pTable    in   varchar2,
   pKey      IN   varchar2,
   pid       in   number
)Return NUmber;
--==============================
procedure calc_date(
/* Construct Date from
SysDate + Months,
*/
num_of_mon in number,
num_of_days in number,
date_calculated out date  );
--==============================
Function Check_Num_Exist(
/* Checks if a number in the form 2^n
Exists in another number
*/
pChk in number,
pNum in number)
return char;
--==============================


function get_overlapping_period(
/* get overlappeing period between two intervals*/
start1 in date,
end1 in date,
start2 date,
end2 date)
 return number;

FUNCTION GET_NO_WeekDays (
-- to get how many dd (fridays ) between two dates
SDATE DATE,
EDATE DATE,
dd varchar)
RETURN NUMBER;


end Misc;
/

prompt
prompt Creating package SPU_AUDITING
prompt =============================
prompt
create or replace package spu_Auditing is

  -- Author  : AMRO.FADLY
  -- Created : 2/27/2001 11:27:37 AM
  -- Purpose : Create Audit Tables and Triggers

  procedure Create_Audit_Table (Table_Name in VARCHAR2);
  procedure Create_Audit_Tables ;
  procedure Create_Audit_Trigger(Table_Name in VARCHAR2);

  procedure Create_Translate_Trigger(Table_Name in VARCHAR2);
  procedure Create_Translate_Triggers ;

  procedure Create_Modify_Trigger(Table_Name in VARCHAR2);
  procedure Create_Modify_Triggers ;

  procedure Create_ID_Trigger( Table_Name in VARCHAR2,
                               IDFlag     IN boolean default true);
  procedure Create_ID_Trigger( Table_Name in VARCHAR2,
                             keyName     IN VARCHAR2);
  procedure Create_ID_Triggers;

  procedure Create_View( pTable_Name in VARCHAR2);
  procedure Create_Views;

  procedure Create_DelNotes_Trigger(Table_Name in VARCHAR2);
  procedure Create_DelNotes_Triggers ;

  procedure Create_InsChk_Trigger(View_Name in VARCHAR2);
  procedure Create_InsChk_Triggers ;
  PROCEDURE create_lookup_table(TableName IN VARCHAR2 , TableDesc IN VARCHAR2 , ATableDesc IN VARCHAR2 , HeaderName IN VARCHAR2 );
  procedure shift_Sequences;

  AuditFlag       Boolean;
  user_is_db_user BOOLEAN;
end spu_Auditing;
/

prompt
prompt Creating function GET_LAST_VALUE
prompt ================================
prompt
create or replace function GET_LAST_VALUE(m_symbol number ,LAST_Date date) return number is
  Result number;
begin
  select  price INTO RESULT
   from support.stock_info t  
   where t.price_time =  (select max(si.price_time) 
                          from support.stock_info si 
                          WHERE SI.PRICE_TIME BETWEEN TRUNC(LAST_DATE) AND TRUNC(LAST_DATE)+1
                          AND SI.SYMBOL = m_symbol
                          and si.price <> 0
                          )
    AND T.SYMBOL = m_symbol
    ;


  return(Result);
end GET_LAST_VALUE;
/

prompt
prompt Creating function GET_MAX_LAST_CLOSE
prompt ====================================
prompt
create or replace function get_max_last_close(m_symbole number , fromDate date) return table  is
  Result number;
begin
  for c in (select * from closing_Prices where symbole = m_symbole)
  loop
    null;
  end loop;
  
  return(Result);
end get_max_last_close;
/

prompt
prompt Creating function GET_MAX_VALUE
prompt ===============================
prompt
create or replace function get_max_value(m_symbol number , fromDate date , toDate date) return number is
  Result number;
   opendate date;
begin

   -- Getting the open date for the given symbol 
   select min(price_time) into opendate from stock_info s 
   where s.symbol = m_symbol
   and s.price <> 0 
   and s.price_time  >= fromDate;
   
   
   select max(price) into result 
   from support.stock_info t
   where t.symbol = m_symbol
   and t.price_time between  opendate and trunc(toDate)+1;
   
  return(Result);
end get_Max_Value;
/

prompt
prompt Creating function GET_MIN_VALUE
prompt ===============================
prompt
create or replace function get_min_value(m_symbol number , fromDate date , toDate date) return number is
  Result number;
  opendate date;
begin


  -- Getting the open date for the given symbol 
   select min(price_time) into opendate from stock_info s 
   where s.symbol = m_symbol
   and s.price <> 0 
   and s.price_time  >= fromDate;

   select nvl(min(price),0) into result 
   from support.stock_info t
   where t.symbol = m_symbol
   AND PRICE <> 0
   and t.price_time between opendate and trunc(toDate)+1;
   
  return(Result);
end get_min_Value;
/

prompt
prompt Creating function GET_OPEN_VALUE
prompt ================================
prompt
create or replace function GET_OPEN_VALUE(m_symbol number ,OPEN_Date date) return number is
  Result number;
begin
  SELECT PRICE INTO RESULT FROM STOCK_INFO T WHERE 
  T.PRICE_TIME = 
               (SELECT MIN(S.PRICE_TIME) 
                 FROM STOCK_INFO S 
                 WHERE S.PRICE_TIME between  TRUNC(OPEN_Date) and TRUNC(OPEN_Date)+1
                 AND S.SYMBOL = M_SYMBOL 
                 AND S.PRICE <> 0
                 AND S.SYMBOL = T.SYMBOL
                )
  AND T.SYMBOL = m_symbol;
  return(Result);
end GET_OPEN_VALUE;
/

prompt
prompt Creating package body MISC
prompt ==========================
prompt
create or replace package body Misc is

function get_tree_Element_Level(
/*  This Function return a full tree path
    form any table that has parent_id = id
    the path could be constructed from two parts
    1- a series of col1
    2- the last item as Col2
Modified bY shawky to return the itelm level no.
-----------------------------------------------*/
   pTableName IN VARCHAR2,
   pID        IN NUMBER,
   pCol1      IN VARCHAR2,
   pCol2      IN CHAR )
return varchar2 is

  cur_hdl        NUMBER;
  rows_processed NUMBER;
  OutCol1        varchar2(2000);
  OutCol2        varchar2(2000);
  St             varchar2(2000);
  Result         varchar2(2000);
  Cntr           INTEGER;
begin
  St:='Select '||pCol1||','||pCol2 ||' from '||pTableName||' START WITH ID= ';
  St:=St||To_Char(pID)||' Connect by id = prior parent_id ORDER BY Level DESC';

  cur_hdl := DBMS_SQL.OPEN_CURSOR;

  DBMS_SQL.PARSE(cur_hdl, St, DBMS_SQL.NATIVE);
  dbms_sql.define_column(cur_hdl, 1, OutCol1, 200);
  dbms_sql.define_column(cur_hdl, 2, OutCol2, 200);
  rows_processed := dbms_sql.execute(cur_hdl);

  Result:=' ';
  Cntr:=0;
  LOOP
  -- fetch a row
    IF dbms_sql.fetch_rows(cur_hdl) > 0 then
    -- fetch columns from the row
      Cntr:=Cntr+1;
      dbms_sql.column_value(cur_hdl, 1, OutCol1);
      dbms_sql.column_value(cur_hdl, 2, OutCol2);
      Result:=Result||OutCol1||'/';
    ELSE
        EXIT;
    END IF;
  END LOOP;
  Result:=SUBstr(Result,1,Length(Result)-Length(OutCol1)-1)||OutCol2;
  DBMS_SQL.CLOSE_CURSOR(cur_hdl);
  return(cntr-1);
end get_tree_Element_Level;
--==============================

function get_tree_path(
/*  This Function return a full tree path
    form any table that has parent_id = id
    the path could be constructed from two parts
    1- a series of col1
    2- the last item as Col2
-----------------------------------------------*/
   pTableName IN VARCHAR2,
   pID        IN NUMBER,
   pCol1      IN VARCHAR2,
   pCol2      IN CHAR )
return varchar2 is

  cur_hdl        NUMBER;
  rows_processed NUMBER;
  OutCol1        varchar2(2000);
  OutCol2        varchar2(2000);
  St             varchar2(2000);
  Result         varchar2(2000);
  Cntr           INTEGER;
begin
  St:='Select '||pCol1||','||pCol2 ||' from '||pTableName||' START WITH ID= ';
  St:=St||To_Char(pID)||' Connect by id = prior parent_id ORDER BY Level DESC';

  cur_hdl := DBMS_SQL.OPEN_CURSOR;

  DBMS_SQL.PARSE(cur_hdl, St, DBMS_SQL.NATIVE);
  dbms_sql.define_column(cur_hdl, 1, OutCol1, 200);
  dbms_sql.define_column(cur_hdl, 2, OutCol2, 200);
  rows_processed := dbms_sql.execute(cur_hdl);

  Result:=' ';
  Cntr:=0;
  LOOP
  -- fetch a row
    IF dbms_sql.fetch_rows(cur_hdl) > 0 then
    -- fetch columns from the row
      Cntr:=Cntr+1;
      dbms_sql.column_value(cur_hdl, 1, OutCol1);
      dbms_sql.column_value(cur_hdl, 2, OutCol2);
      Result:=Result||OutCol1||'/';
    ELSE
        EXIT;
    END IF;
  END LOOP;
  Result:=SUBstr(Result,1,Length(Result)-Length(OutCol1)-1)||OutCol2;
  DBMS_SQL.CLOSE_CURSOR(cur_hdl);
  return(Result);
end get_tree_path;
--==============================
procedure Restore_Rec
/*
This function Restore a logically deleted record
it returns: -1 can not restore (Parent logically deleted);
0 No record ; 1 Restored
*/
(  pTable  in   varchar2,
   pKey    IN   varchar2,
   pid     in   number,
   pOut    OUT  number
)
is
Stmnt              Varchar2(2000);
References_Exist   Exception;
begin
  begin
    Stmnt:='Select 1 from '||pTable||' t1,'||pTable|| ' t2 ';
    Stmnt:=Stmnt||'  Where t1.id = t2.Parent_id AND t1.Deleted=''T'' ';
    Execute Immediate Stmnt;
    Raise References_Exist;
  Exception
    When others then
      Null;
  end;

  Stmnt:='Update '||pTable||' Set Deleted=''F'' '||
  ', Parent_id= prev_parent_id, Header_id = Prev_Header_id '||
  ', prev_parent_id= null, Prev_Header_id = Null '||
  ' Where ';
  Stmnt:=Stmnt||pKey||'='||to_Char(pid);

  Execute Immediate Stmnt;
  commit;
  pOut:=1;
exception
  When References_Exist then
    pOut:=-1;
  When No_Data_Found then
    pOut:=0;

end Restore_Rec;

Procedure Delete_All_Logical
/*  Delete all Logically deleted record
in all tables owned by current user.
*/
is
  Cursor call_tables is
    Select table_name from user_tables;
  Stmnt    Varchar2(2000);
begin

  For Tbl in call_tables Loop
   Begin
     Stmnt:='Delete from '|| Tbl.Table_Name ||' Where Deleted = ''T'' ';
     Execute Immediate Stmnt;
   Exception
     When Others Then
       Null;
   End;
  End Loop;
  Commit;
end Delete_All_Logical;
--==============================
Function Check_Ref
/*
This function Checks if a Record has reference in other or same tables
It Returns False for no and True for yes.
*/
(  pTable    in   varchar2,
   pKey      IN   varchar2,
   pid       in   number
)Return NUmber
is
Begin
  Begin
    Savepoint x;
    SPU_Auditing.AuditFlag:=false;
    Execute immediate 'Delete from '||pTable||
     '  where '||pKey||'='||pid;
    SPU_Auditing.AuditFlag:=true;
    Rollback to x;
    Return 0;
  Exception
    When Others Then
      SPU_Auditing.AuditFlag:=true;
      Rollback to x;
      Return 1;
  End;
End Check_Ref;
--==============================
procedure calc_date(
/* Construct Date from
SysDate + Months,
*/
num_of_mon in number,
num_of_days in number,
date_calculated out date  )
is
expire_date date;
begin
  expire_date := (add_months(sysdate , num_of_mon)+num_of_days);
  date_calculated:= expire_date;
end calc_date;
--==============================
Function Check_Num_Exist(
/* Checks if a number in the form 2^n
Exists in another number

select distinct      it.item_code
from mt_trans_const_items it
where Misc.Check_Num_Exist(it.const_code,34) = 'T'
  and it.element_code                        = 'MyElement'

*/
pChk in number,
pNum in number)
return char
is
ldif   number;
Begin
  lDif:=pNum;
  While  lDif > 0
  Loop
    lDif:=lDif-Power(2,trunc(log(2,lDif)));
    if lDif=pChk Then
      Return 'T';
    End if;
  End Loop;

  Return 'F';
End Check_Num_Exist;
--------------------------------------------
function get_overlapping_period(start1 in date, end1 in date,start2 date, end2 date) return number is
  Result number;
begin

--period1 out of period2
     if end2<start1  or start2>end1 then
     Result:=0;
     end if ;
-- start1 before the second period
     if start2<= start1 and end2<=end1 and end2> start1   then
     Result:=end2-start1+1;
     end if ;

     if start2<=start1 and end2>end1 then
     Result:=end1-start1+1;
     end if;

-- start2    between the second period
     if start2>start1 and start2< end1 and end2<=end1 then
     Result:=end2-start2+1;
     end if;

     if start2>start1 and start2<end1 and end2>end1 then
     Result:=end1-start2+1;
     end if;

  return(Result);
end get_overlapping_period;

---------------------------------------------------------------------------------
FUNCTION GET_NO_WeekDays (SDATE DATE,EDATE DATE, dd varchar) RETURN NUMBER IS
  VCNT NUMBER := 0;
	SDA DATE := SDATE ;
BEGIN
 LOOP
   IF Upper(TO_CHAR(SDA,'DY')) = Upper(dd) THEN
      VCNT := VCNT + 1;
   END IF;
   EXIT WHEN SDA = EDATE;
    SDA := SDA+1;
 END LOOP;

RETURN(VCNT);
END;


begin
  -- Initialization
  NULL;
end Misc;
--==============================
/

prompt
prompt Creating package body SPU_AUDITING
prompt ==================================
prompt
create or replace package body spu_Auditing is
-------------------------------------------------------------------------
procedure set_user_type(v_user_is_db_user boolean)
is
begin
spu_auditing.user_is_db_user:= v_user_is_db_user;
end;
--------
procedure Create_Audit_Table
(Table_Name in VARCHAR2)
is
Line VARCHAR2(2000);
begin
  Line:='DROP TABLE AUD$_'||Table_Name||' ;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='CREATE TABLE AUD$_'||Table_Name||' as (select * from '||Table_Name||' where 1<>1);';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='ALTER TABLE AUD$_'||Table_Name||' ADD (Aud_Op_ID NUMBER, Old_New CHAR(1), Aud_Time_stamp DATE, Aud_User_id VARCHAR2(20));';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='ALTER TABLE AUD$_'||Table_Name||' ADD CONSTRAINT PK_AUD$_'||Table_Name||' PRIMARY KEY(Aud_OP_ID);';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  COMMIT;
end Create_Audit_Table;
-------------------------------------------------------------------------
procedure Create_Audit_Trigger
(Table_Name in VARCHAR2)
Is
 Time_now DATE;
 Terminal CHAR(10);
 MyLine VARCHAR2(2000);
 CURSOR Column_Cur(TN IN VARCHAR2)  IS
 SELECT * FROM USER_TAB_COLUMNS
 WHERE Table_name = UPPER(TN);
BEGIN
  MyLine:='CREATE OR REPLACE TRIGGER AUD_'||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='AFTER INSERT OR UPDATE OR DELETE ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='FOR EACH ROW';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='DECLARE';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:=' Time_now DATE;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:=' MySession NUMBER;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:=' MyUserID  VARCHAR2(40) := USER;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='BEGIN';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);

  MyLine:='  -- Skip Auditing Flag';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  if (spu_Auditing.AuditFlag=false) and (Deleting) Then';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    Return;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  End if;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);

  MyLine:='  -- get current time, and user''s session:';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  Time_now := SYSDATE;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  MySession := USERENV(''SESSIONID'');';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  BEGIN';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    NULL; --select UserID into MyUserID';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='     --from Sys_User_Sessions';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    --where Session_id = MySession;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  Exception';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    WHEN NO_DATA_FOUND THEN';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='      MyUserID :=USER;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='      Return;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  END;';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);

  MyLine:='  IF DELETING OR UPDATING THEN';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    INSERT INTO AUD$_'||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='     (Aud_OP_ID,OLD_NEW,Aud_TIME_STAMP, Aud_User_id';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  FOR Col_Rec IN  Column_Cur(Table_Name) LOOP
     MyLine:='      ,'||Col_Rec.Column_Name;
     Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  END LOOP;

  MyLine:='      ) VALUES ';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='      (Aud$_Seq.Nextval,''O'', Time_Now, MyUserID ';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  FOR Col_Rec IN  Column_Cur(Table_Name) LOOP
    MyLine:='      , :OLD.'||Col_Rec.Column_Name;
    Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  END LOOP;
  MyLine:='      );';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  END IF;   ';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  IF INSERTING OR UPDATING THEN';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='    INSERT INTO AUD$_'||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='     (Aud_OP_ID,OLD_NEW,Aud_TIME_STAMP, Aud_User_id';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  FOR Col_Rec IN  Column_Cur(Table_Name) LOOP
     MyLine:='      ,'||Col_Rec.Column_Name;
     Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  END LOOP;

  MyLine:='      ) VALUES ';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='      (Aud$_Seq.Nextval,''N'', Time_Now, MyUserID ';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  FOR Col_Rec IN  Column_Cur(Table_Name) LOOP
    MyLine:='      , :NEW.'||Col_Rec.Column_Name;
    Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  END LOOP;
  MyLine:='      );';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='  END IF;   ';

  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='END AUD_'||Table_Name||';';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);
  MyLine:='/';
  Insert into TBLSys_Script (Line)  VALUES    (MyLine);

  COMMIT;
end Create_Audit_Trigger;
-------------------------------------------------------------------------
procedure Create_Audit_Tables
is
CURSOR Tables_Cur IS
  SELECT table_name
   FROM user_tables
   where table_name not like 'AUD$_%'
     and Table_name not like 'SYS%'
     and Table_name not like 'V$%'
     and Table_name not like 'V_%'
     and table_name not like 'MT_%';

begin
  DELETE TBLSys_Script;
  COMMIT;
  FOR Table_Rec IN Tables_Cur loop
    Create_Audit_Table(Table_Rec.Table_Name);
    Create_Audit_Trigger(Table_Rec.Table_Name);
  end loop;
end Create_Audit_Tables;
------------------------------------
PROCEDURE create_lookup_table(TableName IN VARCHAR2 , TableDesc IN VARCHAR2 , ATableDesc IN VARCHAR2 , HeaderName IN VARCHAR2 )
IS
BEGIN


--INSERT INTO tBLSys_Script (Line ) VALUES (
--'-- Drop table ');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'drop table '||TableName ||' cascade constraints;');
INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Create table ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'create table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'(');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  ID         NUMBER not null,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  CODE       VARCHAR2(20) not null,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Parent_id  NUMBER,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Prev_Parent_id  NUMBER,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Header_id  NUMBER,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Prev_Header_id  NUMBER,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  A_DSC      VARCHAR2(200) not null,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  E_DSC      VARCHAR2(200) not null,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Note_Notes_ID      Number,');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Active     CHAR(1) DEFAULT ''T'',');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  deleted    CHAR(1) DEFAULT ''F'', ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Modify_Seq    NUMBER Default 0, ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  Update_Status   Number DEFAULT 0 ');
INSERT INTO tBLSys_Script (Line ) VALUES (
');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');
INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Create/Recreate primary, unique and foreign key constraints ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint PK_'|| TableName || '  primary key (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint UQ_'|| TableName || '_CODE unique (CODE);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint FK_'|| TableName || '  foreign key (PARENT_ID)');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  references '|| TableName || '  (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
' ');
--- Cancel Notes Forign Key--- By Shawky Foda
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'alter table '|| TableName || '  ');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'  add constraint FK_'|| TableName || ' ._Notes foreign key (Note_Notes_id)');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'  references Notes (Notes_ID);');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--' ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_'|| TableName || '_Parent CHECK(parent_id <>ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');


INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint FK_'|| TableName || '_'|| HeaderName || '  foreign key (Header_ID)');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  references '|| HeaderName || '  (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
' ');
INSERT INTO tBLSys_Script (Line ) VALUES ( 'WHENEVER SQLERROR NONE;');


INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Create/Recreate check constraints ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_'|| TableName || '_Active');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  check (Active=''F''or Active=''T'');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');
INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table '|| TableName || '  ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_'|| TableName || '_Deleted');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  check (Deleted=''F''or Deleted=''T'');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');

---------
INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Add comments to the columns ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.ID');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Primary Key'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.CODE');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''User Code'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.Parent_id');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Parent Record'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.Header_id');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is '''|| HeaderName ||''';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.E_DSC');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''English Desc'';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.A_DSC');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Arabic Desc'';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.Active');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''For User Activation'';  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column '|| TableName || '.Deleted');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''For Logical Deletion'';  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on Table '|| TableName || ' ');
INSERT INTO tBLSys_Script (Line ) VALUES (
' is '' '|| TableDesc || '  ('|| ATableDesc || ' )''; ');

Commit;
INSERT INTO tBLSys_Script (Line ) VALUES (
'INSERT INTO '|| TableName || ' ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  (ID, code,a_dsc, e_dsc )');
INSERT INTO tBLSys_Script (Line ) VALUES (
'   VALUES ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  (0, ''0'', '''|| ATableDesc || ' ''||'' '' ,'''|| TableDesc || ' ''||'' '');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'Commit;');
INSERT INTO tBLSys_Script (Line ) VALUES (
'/');
Commit;

END;
-------------------------------------------------------------------------
procedure Create_Translate_Trigger(Table_Name in VARCHAR2)
IS
/* This Procedure Creates Triggers on all tables in the User Schema
That has E_ and A_ Columns
The Trigger will copy data from one column to the other if the 2nd is null.
*/
-------------------------------------------------------------------------
 Cursor C (CTable_Name IN Varchar2) IS
 Select Column_Name from user_tab_Columns
 Where Table_Name = CTable_Name
   AND  (column_name like 'E\_%' ESCAPE '\');
 Col1       Varchar2(200);
 Col2       Varchar2(200);
 ColumnLst  Varchar2(2000);
 Line       Varchar2(2000);
Begin

  Line:='create or replace trigger '||Table_Name||'_bIUTrns';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  before Insert OR Update OF';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  ColumnLst:='';
  For MyCol IN C(Table_Name) LOOP
    Col1:=MyCol.Column_Name;
    Col2:='A_'||Substr(Col1,3,Length(Col1)-2);
    ColumnLst:=ColumnLst||Col1||','||Col2||',';
  End Loop;
  ColumnLst:=Substr(ColumnLst,1,Length(ColumnLst)-1);

  Line:='  '||ColumnLst||' ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='-- Save Other Language Columns';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  For MyCol IN C(Table_Name) LOOP
    Col1:=MyCol.Column_Name;
    Col2:='A_'||Substr(Col1,3,Length(Col1)-2);

    Line:=' if :new.'||Col1||' is null then';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:='  :New.'||Col1||':=:New.'||Col2||';';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:=' End if;';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:=' if :new.'||Col2||' is null then';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:='  :New.'||Col2||':=:New.'||Col1||';';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:=' End if;';
    Insert into TBLSys_Script (Line)  VALUES (Line);
    Line:='---------------- ';
    Insert into TBLSys_Script (Line)  VALUES (Line);
  End Loop;

  Line:='';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||Table_Name||'_bIUTrns;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_Translate_Trigger;
-------------------------------------------------------------------------
procedure Create_Translate_Triggers
IS
/* This Procedure Creates Triggers on all tables in the User Schema
That has E_ and A_ Columns
The Trigger will copy data from one column to the other if the 2nd is null.
*/
-------------------------------------------------------------------------
Cursor C IS
Select  table_name, Column_name
From user_tab_Columns
Where Table_Name in  ( Select table_name from user_tab_Columns
                       where column_name    like 'E\_%' ESCAPE '\')
                         and table_name not like 'AUD$_%'
                         and Table_name not like 'SYS%'
                         and Table_name not like 'V_%'
                         and Table_name not like 'V$%'
AND  (column_name like 'E\_%' ESCAPE '\')
Order by Table_Name;
Begin
  For MyTab IN C LOOP
    Create_Translate_Trigger(MyTab.Table_Name);
  End LOOP;
End Create_Translate_Triggers;
-------------------------------------------------------------------------
procedure Create_Modify_Trigger(Table_Name in VARCHAR2)
IS
/* This Procedure Creates Triggers on a table
That has Modify_Seq
The Trigger will insert the seq value after update.
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
Begin

  Line:='create or replace trigger '||Table_Name||'_MODSeq';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:=' before Update ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  Declare';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  lSeq Number;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  select Modify_Seq.NextVal into lSeq From Dual;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  :New.Modify_Seq:=lSeq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||Table_Name||'_MODSeq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_Modify_Trigger;
-------------------------------------------------------------------------
procedure Create_Modify_Triggers
IS
/* This Procedure Creates Triggers on all tables in the User Schema
*/
-------------------------------------------------------------------------
Cursor C IS
Select table_name from user_tab_Columns
 where column_name ='MODIFY_SEQ'
     and table_name not like 'AUD$_%'
     and Table_name not like 'SYS%'
     and Table_name not like 'V_%'
     and Table_name not like 'V$%'
     and table_name not like 'MT_%'
Order by Table_Name;

Begin
  For MyTab IN C LOOP
    Create_Modify_Trigger(MyTab.Table_Name);
  End LOOP;
End Create_Modify_Triggers;
-------------------------------------------------------------------------
procedure Create_ID_Trigger( Table_Name in VARCHAR2,
                             IDFlag     IN boolean default true)
IS
/* This Procedure Creates a Trigger on a table
to update ID with Sequence.
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
 KeyName    Varchar2(100);
 pos        integer;
Begin
  if IDflag Then
    KeyName:='ID';
  else
    Pos:=Instr(Table_Name,'_');
    KeyName:=Substr(Table_Name,Pos+1)||'_ID';
  end if;

  Line:='Drop Sequence '||Table_Name||'_Seq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='create Sequence '||Table_Name||'_Seq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='create or replace trigger '||Table_Name||'_$ID';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:=' Before Insert ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  Declare';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  lSeq Number;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  select '||Table_Name||'_Seq.NextVal into lSeq From Dual;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:=' if :New.'||KeyName||' is null then ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  :New.'||KeyName||' :=lSeq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  end if;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||Table_Name||'_$ID;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_ID_Trigger;
-------------------------------------------------------------------------

procedure Create_ID_Trigger( Table_Name in VARCHAR2,
                             keyName     IN VARCHAR2)
IS
/* This Procedure Creates a Trigger on a table
to update ID with Sequence.
another version with input paramter table_Name and keyname
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
 pos        integer;
Begin
/*  if IDflag Then
    KeyName:='ID';
  else
    Pos:=Instr(Table_Name,'_');
    KeyName:=Substr(Table_Name,Pos+1)||'_ID';
  end if;
  */

  Line:='Drop Sequence '||Table_Name||'_Seq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='create Sequence '||Table_Name||'_Seq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='create or replace trigger '||Table_Name||'_$ID';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:=' Before Insert ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  Declare';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  lSeq Number;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:=' if :New.'||KeyName||' is null then ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  select '||Table_Name||'_Seq.NextVal into lSeq From Dual;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  :New.'||KeyName||' :=lSeq;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  end if;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||Table_Name||'_$ID;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_ID_Trigger;


procedure Create_ID_Triggers
IS
/* This Procedure Creates Triggers to Updat ID
with a sequence
*/
-------------------------------------------------------------------------
 KeyName    Varchar2(100);
 pos        integer;

Cursor C1 IS
Select Distinct table_name
  from user_tab_Columns
 where column_name = 'ID'
   and table_name not like 'AUD$_%'
   and Table_name not like 'SYS%'
   and Table_name not like 'V$%'
   and Table_name not like 'V_%'
   and table_name not like 'MT_%'
   and Table_name not like 'LU_%'
Order by Table_Name;

Cursor C2 IS
Select Distinct table_name,  column_name
  from user_tab_Columns
 where column_name Like '%\_ID' ESCAPE '\'
     and table_name not like 'AUD$\_%' ESCAPE '\'
     and Table_name not like 'SYS%' ESCAPE '\'
     and Table_name not like 'V$%' ESCAPE '\'
     and Table_name not like 'V\_%' ESCAPE '\'
     and table_name not like 'MT\_%' ESCAPE '\'
     and Table_name not like 'LU\_%' ESCAPE '\'
Order by Table_Name;

Begin
  For MyTab1 IN C1 LOOP
    Create_ID_Trigger(MyTab1.Table_Name,true);
  End LOOP;

  For MyTab2 IN C2 LOOP
    Pos:=Instr(MyTab2.Table_Name,'_');
    KeyName:=Substr(MyTab2.Table_Name,Pos+1)||'_ID';
    if KeyName = MyTab2.column_name then
      Create_ID_Trigger(MyTab2.Table_Name,false);
    end if;
  End LOOP;

End Create_ID_Triggers;
-------------------------------------------------------------------------
procedure Create_View( pTable_Name in VARCHAR2)
IS
/* This Procedure Creates a view on a table
and exclude deleted , not active
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
Begin
  Line:='Drop View V$'||pTable_Name||';';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='Create View  V$'||pTable_Name||' AS ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='SELECT t.* ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='FROM '||pTable_Name||' t ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='WHERE t.Deleted = ''F'' AND t.Active = ''T'';';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_View;
-------------------------------------------------------------------------
procedure Create_Views
IS
/* This Procedure Creates Views
 on all tables
*/
-------------------------------------------------------------------------
Cursor C1 IS
Select Distinct table_name
  From user_tables
 Where Table_name not like 'AUD%'
   and Table_name not like 'MT%'
   and Table_name not like 'V$%'
   and Table_name not like 'V_%'
   and Table_name not like 'SYS%'
   and Table_name not like '%NOTE%'
Order by Table_Name;

Begin
  For MyTab IN C1 LOOP
    Create_View(MyTab.Table_Name);
  End LOOP;

End Create_Views;
-------------------------------------------------------------------------
procedure Create_DelNotes_Trigger(Table_Name in VARCHAR2)
IS
/* This Procedure Creates Triggers on a table That has Notes
The Trigger will delete Note when data is deleted.
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
Begin

  Line:='create or replace trigger '||Table_Name||'_$DelNotes';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  after delete ON '||Table_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  Delete from Notes ';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='    Where Notes_ID = :Old.Note_Notes_ID;';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  ';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||Table_Name||'_$DelNotes;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_DelNotes_Trigger;
-------------------------------------------------------------------------
procedure Create_DelNotes_Triggers
IS
/* This Procedure Creates Triggers on all tables in the User Schema
That has  Notes to delete notes on delete
*/
-------------------------------------------------------------------------
Cursor C IS
Select  DISTINCT table_name
From user_tab_Columns
Where Table_Name in  ( Select table_name from user_tab_Columns
                       where column_name   = 'NOTE_NOTES_ID')
                         and table_name not like 'AUD$_%'
                         and Table_name not like 'SYS%'
                         and Table_name not like 'V_%'
                         and Table_name not like 'V$%'
Order by Table_Name;
Begin
  For MyTab IN C LOOP
    Create_DelNotes_Trigger(MyTab.Table_Name);
  End LOOP;
End Create_DelNotes_Triggers;
-------------------------------------------------------------------------
procedure Create_InsChk_Trigger(View_Name in VARCHAR2)
IS
/* This Procedure Creates Triggers on Element Views
to check for security on insert
if insert is not allowed it raises an Exception
InsSecurityException
*/
-------------------------------------------------------------------------
 Line       Varchar2(2000);
Begin

  Line:='create or replace trigger '||View_Name||'_$InsChk';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  instead of insert On  '||View_Name;
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='  for each row';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  declare';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='    InsSecurityException exception;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='begin';
  Insert into TBLSys_Script (Line)  VALUES (Line);

  Line:='    if true Then ';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='    raise InsSecurityException;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  end if;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='  ';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='end '||View_Name||'_$InsChk;';
  Insert into TBLSys_Script (Line)  VALUES (Line);
  Line:='/ ';
  Insert into TBLSys_Script (Line)  VALUES (Line);

End Create_InsChk_Trigger;
-------------------------------------------------------------------------
procedure Create_InsChk_Triggers
IS
/* This Procedure Creates Triggers on all Viesw in the User Schema
to check for security

-------------------------------------------------------------------------
Cursor C IS
Select  DISTINCT View_Name
From MT_Elements
Where View_Name like 'V$%'
Order by View_Name;
Begin
  For MyView IN C LOOP
    Create_InsChk_Trigger(MyView.View_Name);
  End LOOP;
  */
begin
null;

End Create_InsChk_Triggers;
-------------------------------------------------------------------------
procedure shift_Sequences
is
    Line       Varchar2(2000);
    cursor c is
    select rownum, ' Drop sequence '||t.object_name || ';'  lineValue from user_objects  t
           where  t.object_type = 'SEQUENCE'
           and t.object_name <> 'SYS_SCRIPT_SEQ'
    union
    select rownum , ' Create sequence ' ||t.object_name || ' start with 1000;' lineValue from user_objects  t
           where  t.object_type = 'SEQUENCE'
           and t.object_name <> 'SYS_SCRIPT_SEQ'
     order by lineValue desc;

begin
for a in c loop
    line := a. lineValue;
    insert into TBLSys_Script (Line)  VALUES (Line);
end loop;
commit;

end shift_Sequences;
------------------------------------------
begin
  -- Initialization
  NULL;
end spu_Auditing;
/

prompt
prompt Creating trigger LU_EXECUTABLES_$ID
prompt ===================================
prompt
CREATE OR REPLACE TRIGGER "SUPPORT".LU_executables_$ID
 Before Insert ON LU_executables
  for each row
  Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_executables_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_executables_$ID;
/

prompt
prompt Creating trigger LU_QUERIES_$ID
prompt ===============================
prompt
CREATE OR REPLACE TRIGGER "SUPPORT".LU_queries_$ID
 Before Insert ON LU_queries
  for each row
  Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_queries_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_queries_$ID;
/

prompt
prompt Creating trigger SYS_PARAMS_$ID
prompt ===============================
prompt
CREATE OR REPLACE TRIGGER "SUPPORT".sys_params_$ID
 Before Insert ON sys_params
  for each row
  Declare
  lSeq Number;
begin
 if :New.ID is null then
  select sys_params_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end sys_params_$ID;
/

prompt
prompt Creating trigger SYS_SCRIPT_BI
prompt ==============================
prompt
CREATE OR REPLACE TRIGGER "SUPPORT".Sys_script_Bi
BEFORE INSERT On tblSys_Script
FOR EACH ROW
BEGIN
  SELECT Sys_Script_seq.nextval INTO :New.Id
   FROM DUAL;
END Sys_script_Bi;
/


spool off
