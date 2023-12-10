prompt PL/SQL Developer import file
prompt Created on Friday, June 30, 2006 by shawky.foda
set feedback off
set define off
prompt Creating SINGLE_SIGN_IN...
create table SINGLE_SIGN_IN
(
  SERVER      VARCHAR2(40) not null,
  ACCESS_TYPE VARCHAR2(50),
  PORT_NO     NUMBER,
  USERNAME    VARCHAR2(30) not null,
  PASSWORD    VARCHAR2(30) not null,
  SERVER_NAME VARCHAR2(10)
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

prompt Disabling triggers for SINGLE_SIGN_IN...
alter table SINGLE_SIGN_IN disable all triggers;
prompt Deleting SINGLE_SIGN_IN...
delete from SINGLE_SIGN_IN;
commit;
prompt Loading SINGLE_SIGN_IN...
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.51', 'AIX', null, 'wmin0101', 'sdwm3160', 'DRCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.51', 'AIX', null, 'wmin0201', 'sdwm3160', 'DRCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.51', 'AIX', null, 'wmin0301', 'sdwm3160', 'DRCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.51', 'AIX', null, 'wmin0401', 'sdwm3160', 'DRCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.52', 'AIX', null, 'wmin0301', 'sdwm3160', 'DRCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.49' || chr(10) || '', 'AIX', null, 'wmex0101', 'at0s0rigin' || chr(10) || '', 'DRCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.51', 'AIX', null, 'wmin0101', 'wmadmin02', 'MNCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.49' || chr(10) || '', 'AIX', null, 'wmex0201' || chr(10) || '', 'at0s0rigin' || chr(10) || '', 'DRCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.50', 'AIX', null, 'wmex0101' || chr(10) || '', 'at0s0rigin' || chr(10) || '', 'DRCFWD2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.50', 'AIX', null, 'wmex0201' || chr(10) || '', 'at0s0rigin' || chr(10) || '', 'DRCFWD2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.52', 'AIX', null, 'wmin0201', 'sdwm3160', 'DRCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.52', 'AIX', null, 'wmin0101', 'sdwm3160', 'DRCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.51', 'AIX', null, 'wmin0201', 'wmadmin02', 'MNCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.51', 'AIX', null, 'wmin0301', 'wmadmin02', 'MNCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.51', 'AIX', null, 'wmin0401', 'wmadmin02', 'MNCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.52', 'AIX', null, 'wmin0101', 'wmadmin02', 'MNCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.52', 'AIX', null, 'wmin0201', 'wmadmin02', 'MNCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.52', 'AIX', null, 'wmin0301', 'wmadmin02', 'MNCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.49', 'AIX', null, 'wmex0101', 'wmadmin02', 'MNCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.49', 'AIX', null, 'wmex0201', 'wmadmin02', 'MNCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.50', 'AIX', null, 'wmex0101', 'wmadmin02', 'MNCFWD2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.50', 'AIX', null, 'wmex0201', 'wmadmin02', 'MNCFWD2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.51', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'DRCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.24.52', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'DRCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.49' || chr(10) || '', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'DRCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.25.50', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'DRCFWD2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.51', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'MNCFDX1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.16.52', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'MNCFDX2');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.49', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'MNCFWD1');
insert into SINGLE_SIGN_IN (SERVER, ACCESS_TYPE, PORT_NO, USERNAME, PASSWORD, SERVER_NAME)
values ('10.16.17.50', 'CheckFreeOwner', null, 'bgadmin', 'bgadmin', 'MNCFWD2');
commit;
prompt 30 records loaded
prompt Enabling triggers for SINGLE_SIGN_IN...
alter table SINGLE_SIGN_IN enable all triggers;
set feedback on
set define on
prompt Done.
