prompt PL/SQL Developer import file
prompt Created on 08 Ì‰«Ì—, 2005 by foda_sh
set feedback off
set define off
prompt Dropping LU_ROOT...
drop table LU_ROOT cascade constraints;
prompt Creating LU_ROOT...
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
    initial 16K
    next 16K
    minextents 1
    maxextents 505
    pctincrease 50
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
    initial 16K
    next 16K
    minextents 1
    maxextents 505
    pctincrease 50
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
    initial 16K
    next 16K
    minextents 1
    maxextents 505
    pctincrease 50
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

prompt Disabling triggers for LU_ROOT...
alter table LU_ROOT disable all triggers;
prompt Disabling foreign key constraints for LU_ROOT...
alter table LU_ROOT disable constraint FK_LU_ROOT;
prompt Loading LU_ROOT...
insert into LU_ROOT (ID, CODE, PARENT_ID, PREV_PARENT_ID, HEADER_ID, PREV_HEADER_ID, A_DSC, E_DSC, NOTE_NOTES_ID, ACTIVE, DELETED, MODIFY_SEQ, UPDATE_STATUS)
values (0, '0', null, null, null, null, '«·ﬁ«⁄œ…  ', 'ROOT  ', null, 'T', 'F', 0, 0);
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for LU_ROOT...
alter table LU_ROOT enable constraint FK_LU_ROOT;
prompt Enabling triggers for LU_ROOT...
alter table LU_ROOT enable all triggers;
set feedback on
set define on
prompt Done.
