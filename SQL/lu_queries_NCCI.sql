prompt PL/SQL Developer import file
prompt Created on 19 „«—”, 2005 by foda_sh
set feedback off
set define off
prompt Creating LU_QUERIES...
create table LU_QUERIES
(
  ID             NUMBER not null,
  CODE           VARCHAR2(200) not null,
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
  UPDATE_STATUS  NUMBER default 0,
  QUERY_BODY     VARCHAR2(4000) default '\--------------',
  OWNER          VARCHAR2(100) default user
) ;

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
alter table LU_QUERIES   add constraint PK_LU_QUERIES primary key (ID) ;
alter table LU_QUERIES   add constraint UQ_LU_QUERIES_CODE unique (CODE,PARENT_ID);
alter table LU_QUERIES   add constraint FK_LU_QUERIES foreign key (PARENT_ID)   references LU_QUERIES (ID) on delete cascade;
alter table LU_QUERIES   add constraint FK_LU_QUERIES_LU_ROOT foreign key (HEADER_ID)   references LU_ROOT (ID);
alter table LU_QUERIES   add constraint CHK_LU_QUERIES_ACTIVE   check (Active='F'or Active='T');
alter table LU_QUERIES   add constraint CHK_LU_QUERIES_DELETED   check (Deleted='F'or Deleted='T');
alter table LU_QUERIES   add constraint CHK_LU_QUERIES_PARENT   check (parent_id <>ID);

set feedback on
set define on
prompt Done.
