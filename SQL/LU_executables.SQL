-- Create table
create table LU_EXECUTABLES
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
  EXEC_BODY      VARCHAR2(4000),
  OWNER          VARCHAR2(100) default user,
  MSG            VARCHAR2(400)
);
-- Add comments to the table 
comment on table LU_EXECUTABLES
  is 'User Executables';
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table LU_EXECUTABLES   add constraint PK_LU_EXECUTABLES primary key (ID);
alter table LU_EXECUTABLES   add constraint UQ_LU_EXECUTABLES_CODE unique (CODE,PARENT_ID);
alter table LU_EXECUTABLES   add constraint FK_LU_EXECUTABLES foreign key (PARENT_ID) references LU_EXECUTABLES (ID) on delete cascade;
alter table LU_EXECUTABLES   add constraint FK_LU_EXECUTABLES_LU_ROOT foreign key (HEADER_ID) references LU_ROOT (ID);
-- Create/Recreate check constraints 
alter table LU_EXECUTABLES   add constraint CHK_LU_EXECUTABLES_ACTIVE   check (Active='F'or Active='T');
alter table LU_EXECUTABLES   add constraint CHK_LU_EXECUTABLES_DELETED  check (Deleted='F'or Deleted='T');
alter table LU_EXECUTABLES   add constraint CHK_LU_EXECUTABLES_PARENT   check (parent_id <>ID);
