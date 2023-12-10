-- Create table
create table SYS_PARAMS
(
  SYS_CODE      VARCHAR2(50) not null,
  MOD_CODE      VARCHAR2(50) not null,
  USR_ID        NUMBER not null,
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
);
-- Add comments to the table 
comment on table SYS_PARAMS
  is 'System Parameters';
-- Add comments to the columns 
comment on column SYS_PARAMS.TYP
  is 'Interface type T text,N Number, S Select, C Check Box, D Date';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_PARAMS   add constraint PK_SPARAM primary key (SYS_CODE,ID) ;
alter table SYS_PARAMS   add constraint UQ_SAPARAM_NAME unique (A_NAME);
alter table SYS_PARAMS   add constraint UQ_SEPARAM_NAME unique (E_NAME);
