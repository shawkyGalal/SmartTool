-------------------------------------------------
-- Export file for user COMPL                  --
-- Created by FODA_SH on 05-Apr-04, 1:06:42 PM --
-------------------------------------------------

spool Compl_exported_objects.log

prompt
prompt Creating table AUD$_LU_ESC_CHART
prompt ================================
prompt
create table AUD$_LU_ESC_CHART
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
  ACTIVE         CHAR(1),
  DELETED        CHAR(1),
  MODIFY_SEQ     NUMBER,
  UPDATE_STATUS  NUMBER,
  CURRENT_EMP    VARCHAR2(15),
  AUD_OP_ID      NUMBER not null,
  OLD_NEW        CHAR(1),
  AUD_TIME_STAMP DATE,
  AUD_USER_ID    VARCHAR2(20)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 36K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table AUD$_LU_ESC_CHART
  add constraint PK_AUD$_LU_ESC_CHART primary key (AUD_OP_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table COMPLAINTANTS_TBL
prompt ================================
prompt
create table COMPLAINTANTS_TBL
(
  COMPLAINTANT_TYPE      VARCHAR2(4) default 0 not null,
  COMPLAINTANT_ID        NUMBER not null,
  A_NAME                 VARCHAR2(40),
  E_NAME                 VARCHAR2(40),
  A_ADDRESS1             VARCHAR2(200),
  A_ADDRESS2             VARCHAR2(200),
  E_ADDRESS1             VARCHAR2(200),
  E_ADDRESS2             VARCHAR2(200),
  TEL1                   VARCHAR2(16),
  TEL2                   VARCHAR2(16),
  JOB                    VARCHAR2(50),
  CUST_ID                VARCHAR2(12),
  SUPP_ID                VARCHAR2(12),
  FAX_NO                 VARCHAR2(17),
  MOBILE_NO              VARCHAR2(16),
  NATIONALITY_CODE       NUMBER(4),
  EMAIL                  VARCHAR2(50),
  R_A_ADDRESS1           VARCHAR2(200),
  R_A_ADDRESS2           VARCHAR2(200),
  R_E_ADDRESS1           VARCHAR2(200),
  R_E_ADDRESS2           VARCHAR2(200),
  R_FAX_NO               VARCHAR2(22),
  R_MOBILE_NO            VARCHAR2(22),
  R_EMAIL                VARCHAR2(50),
  R_TEL1                 VARCHAR2(16),
  R_TEL2                 VARCHAR2(16),
  R_SEGMENT_CODE         VARCHAR2(2),
  R_A_NAME               VARCHAR2(40),
  R_E_NAME               VARCHAR2(40),
  R_BSG_CODE             VARCHAR2(10),
  R_A_POLICY_HOLDER_NAME VARCHAR2(40),
  R_E_POLICY_HOLDER_NAME VARCHAR2(40),
  R_NATIONALITY_CODE     NUMBER(4),
  TITLE                  VARCHAR2(80),
  POSITION               VARCHAR2(80)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 24K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on column COMPLAINTANTS_TBL.COMPLAINTANT_TYPE
  is '0 = NCCI customer, 1= existing member of coroperate policy, 2 = individual , 3 = supplier/service provider / other';
comment on column COMPLAINTANTS_TBL.CUST_ID
  is 'In case of complaintant type is NCCI Customer, get his information from v_clients view';
comment on column COMPLAINTANTS_TBL.SUPP_ID
  is 'in case of complaintant type is NCCI supplier, get his information from suppliers view';
comment on column COMPLAINTANTS_TBL.MOBILE_NO
  is 'input mobile number';
comment on column COMPLAINTANTS_TBL.R_A_ADDRESS1
  is 'Retireved arabic address 1';
comment on column COMPLAINTANTS_TBL.R_A_ADDRESS2
  is 'retrieved arabic address 2';
comment on column COMPLAINTANTS_TBL.R_E_ADDRESS1
  is 'Retrieved english address 1';
comment on column COMPLAINTANTS_TBL.R_E_ADDRESS2
  is 'Retrieved english address 2';
comment on column COMPLAINTANTS_TBL.R_FAX_NO
  is 'retrieved fax number';
comment on column COMPLAINTANTS_TBL.R_EMAIL
  is 'retrieved email';
comment on column COMPLAINTANTS_TBL.R_TEL1
  is 'retrieved tel number 1';
comment on column COMPLAINTANTS_TBL.R_TEL2
  is 'retrieved tel number 2';
comment on column COMPLAINTANTS_TBL.R_SEGMENT_CODE
  is 'retrieved segment code';
comment on column COMPLAINTANTS_TBL.R_A_NAME
  is 'retrieved arabic name';
comment on column COMPLAINTANTS_TBL.R_E_NAME
  is 'retrieved english name';
comment on column COMPLAINTANTS_TBL.R_BSG_CODE
  is 'retrieved BSG code';
comment on column COMPLAINTANTS_TBL.R_A_POLICY_HOLDER_NAME
  is 'retrieved arbic name for policy holder';
comment on column COMPLAINTANTS_TBL.R_E_POLICY_HOLDER_NAME
  is 'retrieved english name for policy holder';
alter table COMPLAINTANTS_TBL
  add constraint COMPLAINTANT_ID_PK primary key (COMPLAINTANT_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMPLAINTANTS_TBL
  add constraint COMPLAINTANT_TYPE_CHK
  check (complaintant_type in (0,1,2,3,4));

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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
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

prompt
prompt Creating table LU_BUS_PRCS
prompt ==========================
prompt
create table LU_BUS_PRCS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_BUS_PRCS
  is ' Business Processes  („—«Õ· «·⁄„· )';
comment on column LU_BUS_PRCS.ID
  is 'Primary Key';
comment on column LU_BUS_PRCS.CODE
  is 'User Code';
comment on column LU_BUS_PRCS.PARENT_ID
  is 'Parent Record';
comment on column LU_BUS_PRCS.HEADER_ID
  is 'LU_ROOT';
comment on column LU_BUS_PRCS.A_DSC
  is 'Arabic Desc';
comment on column LU_BUS_PRCS.E_DSC
  is 'English Desc';
comment on column LU_BUS_PRCS.ACTIVE
  is 'For User Activation';
comment on column LU_BUS_PRCS.DELETED
  is 'For Logical Deletion';
alter table LU_BUS_PRCS
  add constraint PK_LU_BUS_PRCS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BUS_PRCS
  add constraint UQ_LU_BUS_PRCS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BUS_PRCS
  add constraint FK_LU_BUS_PRCS foreign key (PARENT_ID)
  references LU_BUS_PRCS (ID);
alter table LU_BUS_PRCS
  add constraint FK_LU_BUS_PRCS_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_BUS_PRCS
  add constraint CHK_LU_BUS_PRCS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_BUS_PRCS
  add constraint CHK_LU_BUS_PRCS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_BUS_PRCS
  add constraint CHK_LU_BUS_PRCS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_COMM_MODE
prompt ===========================
prompt
create table LU_COMM_MODE
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_COMM_MODE
  is 'Complaint Communication Mode';
comment on column LU_COMM_MODE.ID
  is 'Primary Key';
comment on column LU_COMM_MODE.CODE
  is 'User Code';
comment on column LU_COMM_MODE.PARENT_ID
  is 'Parent Record';
comment on column LU_COMM_MODE.HEADER_ID
  is 'LU_ROOT';
comment on column LU_COMM_MODE.A_DSC
  is 'Arabic Desc';
comment on column LU_COMM_MODE.E_DSC
  is 'English Desc';
comment on column LU_COMM_MODE.ACTIVE
  is 'For User Activation';
comment on column LU_COMM_MODE.DELETED
  is 'For Logical Deletion';
alter table LU_COMM_MODE
  add constraint PK_LU_TYPES primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMM_MODE
  add constraint UQ_LU_TYPES_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMM_MODE
  add constraint FK_LU_TYPES foreign key (PARENT_ID)
  references LU_COMM_MODE (ID);
alter table LU_COMM_MODE
  add constraint FK_LU_TYPES_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_COMM_MODE
  add constraint CHK_LU_TYPES_ACTIVE
  check (Active='F'or Active='T');
alter table LU_COMM_MODE
  add constraint CHK_LU_TYPES_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_COMM_MODE
  add constraint CHK_LU_TYPES_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_LOB
prompt =====================
prompt
create table LU_LOB
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
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_LOB
  is ' LINE Of Business  (LINE Of Business )';
comment on column LU_LOB.ID
  is 'Primary Key';
comment on column LU_LOB.CODE
  is 'User Code';
comment on column LU_LOB.PARENT_ID
  is 'Parent Record';
comment on column LU_LOB.HEADER_ID
  is 'LU_ROOT';
comment on column LU_LOB.A_DSC
  is 'Arabic Desc';
comment on column LU_LOB.E_DSC
  is 'English Desc';
comment on column LU_LOB.ACTIVE
  is 'For User Activation';
comment on column LU_LOB.DELETED
  is 'For Logical Deletion';
alter table LU_LOB
  add constraint PK_LU_LOB primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB
  add constraint UQ_LU_LOB_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB
  add constraint FK_LU_LOB foreign key (PARENT_ID)
  references LU_LOB (ID);
alter table LU_LOB
  add constraint FK_LU_LOB_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_LOB
  add constraint CHK_LU_LOB_ACTIVE
  check (Active='F'or Active='T');
alter table LU_LOB
  add constraint CHK_LU_LOB_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_LOB
  add constraint CHK_LU_LOB_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table COMPLAINTS_TBL
prompt =============================
prompt
create table COMPLAINTS_TBL
(
  COMPLAINT_ID       NUMBER not null,
  COMPLAINTANT_ID    NUMBER not null,
  COMPLAINT_DATE     DATE default sysdate not null,
  RECEIVED_BY        VARCHAR2(10) default user not null,
  COMPLAINT_SUBJECT  VARCHAR2(300) not null,
  COMPLAINT_BODY     VARCHAR2(600) not null,
  LOB_ID             NUMBER default 1 not null,
  SECTION_ID         NUMBER(5),
  ESCALATION_MAP_ID  NUMBER,
  CURRENT_RESOLVER   VARCHAR2(20),
  TYPE_ID            NUMBER default 1,
  COMPLAINT_SEVERITY NUMBER,
  AGAINIST_CODE      VARCHAR2(5),
  AGAINIST_DET_CODE  VARCHAR2(12),
  ACK_MODE_CODE      NUMBER(5),
  BUS_PRCS_ID        NUMBER(5),
  REASON_ID          NUMBER(5),
  POLICY_NO          NUMBER(9),
  BRANCH_CODE        NUMBER(2),
  LIC_NO             VARCHAR2(10),
  MEM_CODE           VARCHAR2(15),
  AUTOESCALATE       CHAR(1) default 'Y',
  WORK_FLOW_NUMBER   VARCHAR2(40),
  STATUS             CHAR(2) default 'A',
  STEP_EXPIRE_DATE   DATE,
  OTHER_AGAINIST_DET VARCHAR2(200)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on column COMPLAINTS_TBL.COMPLAINT_ID
  is 'a unique ID for the complaint';
comment on column COMPLAINTS_TBL.COMPLAINTANT_ID
  is 'a frefrence to the complaintant';
comment on column COMPLAINTS_TBL.ESCALATION_MAP_ID
  is 'a refrence to esclation map that complaint will follow';
comment on column COMPLAINTS_TBL.COMPLAINT_SEVERITY
  is 'a scale from 0 to 5';
comment on column COMPLAINTS_TBL.AGAINIST_CODE
  is 'refrence to general againist area';
comment on column COMPLAINTS_TBL.AGAINIST_DET_CODE
  is 'refrence to specified againist area';
comment on column COMPLAINTS_TBL.ACK_MODE_CODE
  is 'refrence to complainant acknowledge mode';
comment on column COMPLAINTS_TBL.REASON_ID
  is 'refrence to the reason';
comment on column COMPLAINTS_TBL.LIC_NO
  is 'A REFRENCE TO EDGE.RUKHSA_MEMEBER LIC_NO';
comment on column COMPLAINTS_TBL.MEM_CODE
  is 'A REFRENCE TO EDGE.MED_MEM.MEM_CODE';
comment on column COMPLAINTS_TBL.STATUS
  is 'A = Active,  R = Resolved , T = Trash Can';
comment on column COMPLAINTS_TBL.OTHER_AGAINIST_DET
  is 'if User need to define other against details';
alter table COMPLAINTS_TBL
  add constraint COMPLAINT_ID_PK primary key (COMPLAINT_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMPLAINTS_TBL
  add constraint COMPLAINTANT_ID_FK foreign key (COMPLAINTANT_ID)
  references COMPLAINTANTS_TBL (COMPLAINTANT_ID);
alter table COMPLAINTS_TBL
  add constraint COMPLAINT_CATEGORY_FK foreign key (BUS_PRCS_ID)
  references LU_BUS_PRCS (ID);
alter table COMPLAINTS_TBL
  add constraint COMPLAINT_LOB_FK foreign key (LOB_ID)
  references LU_LOB (ID);
alter table COMPLAINTS_TBL
  add constraint COMPLAIN_TYPE_ID_FK foreign key (TYPE_ID)
  references LU_COMM_MODE (ID);

prompt
prompt Creating table COMPLAINT_RESPONSES_TBL
prompt ======================================
prompt
create table COMPLAINT_RESPONSES_TBL
(
  RESPONSE_NO       NUMBER(8) not null,
  RESPONSE_TYPE     VARCHAR2(25),
  RECOMENDATION     VARCHAR2(2000),
  RESPONSE_DATE     DATE default sysdate,
  RESPONSE_BY       VARCHAR2(12),
  COMPLAINT_ID      NUMBER not null,
  PREV_PARTCIPANT   VARCHAR2(20),
  NEW_PARTCIPANT    VARCHAR2(20),
  IS_WF_PARTICIPANT CHAR(1) default 'Y',
  DELETED           CHAR(1) default 'N' not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on column COMPLAINT_RESPONSES_TBL.RESPONSE_TYPE
  is 'ignore,  escalate,  resolved';
comment on column COMPLAINT_RESPONSES_TBL.RESPONSE_BY
  is 'a refrense to NCCI employee invistigating the complaint';
comment on column COMPLAINT_RESPONSES_TBL.COMPLAINT_ID
  is 'a refrence to a Complaint';
comment on column COMPLAINT_RESPONSES_TBL.IS_WF_PARTICIPANT
  is 'indicates Whether the New_participant is Work flow participant or a reassigned resolver';
comment on column COMPLAINT_RESPONSES_TBL.DELETED
  is 'Y indicates That This Response belongs to a WF which have been canceled and a new one have been restarted';
alter table COMPLAINT_RESPONSES_TBL
  add constraint RESPONSE_NO_PK primary key (RESPONSE_NO)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMPLAINT_RESPONSES_TBL
  add constraint RESPONSE_COMPLAINT_ID_FK foreign key (COMPLAINT_ID)
  references COMPLAINTS_TBL (COMPLAINT_ID);

prompt
prompt Creating table COMPLAINT_SOLUTIONS_TBL
prompt ======================================
prompt
create table COMPLAINT_SOLUTIONS_TBL
(
  ID                     NUMBER not null,
  SOLUTION_ID            NUMBER not null,
  SOLUTION_RECOMENDATION VARCHAR2(2000),
  SOLUTION_DATE          DATE default SYSDATE,
  SOLVED_BY              VARCHAR2(12),
  COMPLAINT_ID           NUMBER not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on column COMPLAINT_SOLUTIONS_TBL.ID
  is 'a refrence to solution id';
comment on column COMPLAINT_SOLUTIONS_TBL.SOLUTION_RECOMENDATION
  is 'an explanation for the suggested solution';
comment on column COMPLAINT_SOLUTIONS_TBL.SOLVED_BY
  is 'a refrence to the person suggested that solution';
alter table COMPLAINT_SOLUTIONS_TBL
  add constraint SOLUTION_ID_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMPLAINT_SOLUTIONS_TBL
  add constraint SOLUTION_COMPLAINT_ID_FK foreign key (COMPLAINT_ID)
  references COMPLAINTS_TBL (COMPLAINT_ID);

prompt
prompt Creating table COMP_ATTACHMENTS
prompt ===============================
prompt
create table COMP_ATTACHMENTS
(
  ATTACH_ID     NUMBER not null,
  E_DSC         VARCHAR2(200),
  A_DSC         VARCHAR2(200),
  FILENETID     NUMBER,
  FILENETLIB    VARCHAR2(50),
  COMPLAINT_ID  NUMBER not null,
  ATTACHMENT_BY VARCHAR2(30),
  DATE_ATTACHED DATE default sysdate,
  FILENAME      VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMP_ATTACHMENTS
  add constraint ATTACH_PK primary key (ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMP_ATTACHMENTS
  add constraint ATTACH_COMP_FK foreign key (COMPLAINT_ID)
  references COMPLAINTS_TBL (COMPLAINT_ID) on delete cascade
  disable;

prompt
prompt Creating table COMP_USER_ROLES
prompt ==============================
prompt
create table COMP_USER_ROLES
(
  ROLE_ID NUMBER not null,
  USER_ID VARCHAR2(20) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table COMP_USER_ROLES
  add constraint USER_ROLES_UK unique (USER_ID,ROLE_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table EMP_POSITIONS
prompt ============================
prompt
create table EMP_POSITIONS
(
  USER_COMP_ID    VARCHAR2(12) not null,
  POSITION_ID     NUMBER not null,
  FROM_DATE       DATE not null,
  TO_DATE         DATE,
  CREATED_BY      VARCHAR2(12),
  CREATION_DATE   DATE,
  LASTUPDATEDBY   VARCHAR2(12) default user not null,
  LAST_UDATE_DATE DATE default sysdate not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table LU_ESC_CHART
prompt ===========================
prompt
create table LU_ESC_CHART
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
  UPDATE_STATUS  NUMBER default 0,
  CURRENT_EMP    VARCHAR2(15)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_ESC_CHART
  is 'POSTION HIRARCHY IN NCCI (??????? ???????? )';
comment on column LU_ESC_CHART.ID
  is 'Primary Key';
comment on column LU_ESC_CHART.CODE
  is 'User Code';
comment on column LU_ESC_CHART.PARENT_ID
  is 'Parent Record';
comment on column LU_ESC_CHART.HEADER_ID
  is 'LU_ROOT';
comment on column LU_ESC_CHART.A_DSC
  is 'Arabic Desc';
comment on column LU_ESC_CHART.E_DSC
  is 'English Desc';
comment on column LU_ESC_CHART.ACTIVE
  is 'For User Activation';
comment on column LU_ESC_CHART.DELETED
  is 'For Logical Deletion';
comment on column LU_ESC_CHART.CURRENT_EMP
  is 'a forign key to user_info.user_comp_id';
alter table LU_ESC_CHART
  add constraint PK_LU_ESC_CHART primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_ESC_CHART
  add constraint UQ_LU_ESC_CHART_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_ESC_CHART
  add constraint FK_LU_ESC_CHART foreign key (PARENT_ID)
  references LU_ESC_CHART (ID);
alter table LU_ESC_CHART
  add constraint FK_LU_ESC_CHART_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_ESC_CHART
  add constraint CHK_LU_ESC_CHART_ACTIVE
  check (Active='F'or Active='T');
alter table LU_ESC_CHART
  add constraint CHK_LU_ESC_CHART_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_ESC_CHART
  add constraint CHK_LU_ESC_CHART_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table ESCAL_MAP_DETAILS_TBL
prompt ====================================
prompt
create table ESCAL_MAP_DETAILS_TBL
(
  DETAIL_ID     NUMBER not null,
  MAP_ID        NUMBER,
  DETAIL_SEQ    NUMBER,
  RESOLVER_ID   VARCHAR2(12),
  DURATION      NUMBER,
  NOTIFY_BEFORE NUMBER,
  POSITION_ID   NUMBER
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on column ESCAL_MAP_DETAILS_TBL.MAP_ID
  is 'a Refrence to the Escalation Map';
comment on column ESCAL_MAP_DETAILS_TBL.DETAIL_SEQ
  is 'sequance number for the esclation step no.';
comment on column ESCAL_MAP_DETAILS_TBL.RESOLVER_ID
  is 'If the Escalation Map is based on Persons, NCCI Employee Responsable for trying to resove the refrenced complaint';
comment on column ESCAL_MAP_DETAILS_TBL.DURATION
  is 'max. period that the complaint will reside on the resolver in hours';
comment on column ESCAL_MAP_DETAILS_TBL.POSITION_ID
  is 'If the Escalation Map is based on Postions  ( From LU_ESC_CHART)';
alter table ESCAL_MAP_DETAILS_TBL
  add constraint DETAIL_ID_PK primary key (DETAIL_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table ESCAL_MAP_DETAILS_TBL
  add constraint POSTION_ID_FK foreign key (POSITION_ID)
  references LU_ESC_CHART (ID);

prompt
prompt Creating table LU_BP_ESC_MAPS
prompt =============================
prompt
create table LU_BP_ESC_MAPS
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
  UPDATE_STATUS  NUMBER default 0,
  ESC_MAP_TYPE   VARCHAR2(6) default 'PER'
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_BP_ESC_MAPS
  is 'BP ESCALATION MAPS  (Œ—«∆ÿ «· ’⁄Ìœ )';
comment on column LU_BP_ESC_MAPS.ID
  is 'Primary Key';
comment on column LU_BP_ESC_MAPS.CODE
  is 'User Code';
comment on column LU_BP_ESC_MAPS.PARENT_ID
  is 'Parent Record';
comment on column LU_BP_ESC_MAPS.HEADER_ID
  is 'LU_BUS_PRCS';
comment on column LU_BP_ESC_MAPS.A_DSC
  is 'Arabic Desc';
comment on column LU_BP_ESC_MAPS.E_DSC
  is 'English Desc';
comment on column LU_BP_ESC_MAPS.ACTIVE
  is 'For User Activation';
comment on column LU_BP_ESC_MAPS.DELETED
  is 'For Logical Deletion';
comment on column LU_BP_ESC_MAPS.ESC_MAP_TYPE
  is 'PER = The Escalation Map is based On Persons , ''POS'' = The Escalation Map is Based on Positions';
alter table LU_BP_ESC_MAPS
  add constraint PK_LU_BP_ESC_MAPS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_ESC_MAPS
  add constraint UQ_LU_BP_ESC_MAPS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_ESC_MAPS
  add constraint FK_LU_BP_ESC_MAPS foreign key (PARENT_ID)
  references LU_BP_ESC_MAPS (ID);
alter table LU_BP_ESC_MAPS
  add constraint FK_LU_BP_ESC_MAPS_LU_BUS_PRCS foreign key (HEADER_ID)
  references LU_BUS_PRCS (ID)
  disable;
alter table LU_BP_ESC_MAPS
  add constraint CHK_LU_BP_ESC_MAPS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_BP_ESC_MAPS
  add constraint CHK_LU_BP_ESC_MAPS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_BP_ESC_MAPS
  add constraint CHK_LU_BP_ESC_MAPS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_BP_REASONS
prompt ============================
prompt
create table LU_BP_REASONS
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
    initial 10K
    next 16K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_BP_REASONS
  is ' COMPLAINT BP REASONS  («”»«» «·‘ﬂ«ÊÏ )';
comment on column LU_BP_REASONS.ID
  is 'Primary Key';
comment on column LU_BP_REASONS.CODE
  is 'User Code';
comment on column LU_BP_REASONS.PARENT_ID
  is 'Parent Record';
comment on column LU_BP_REASONS.HEADER_ID
  is 'LU_Bus_Prcs';
comment on column LU_BP_REASONS.A_DSC
  is 'Arabic Desc';
comment on column LU_BP_REASONS.E_DSC
  is 'English Desc';
comment on column LU_BP_REASONS.ACTIVE
  is 'For User Activation';
comment on column LU_BP_REASONS.DELETED
  is 'For Logical Deletion';
alter table LU_BP_REASONS
  add constraint PK_LU_BP_REASONS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_REASONS
  add constraint UQ_LU_BP_REASONS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_REASONS
  add constraint FK_LU_BP_REASONS foreign key (PARENT_ID)
  references LU_BP_REASONS (ID);
alter table LU_BP_REASONS
  add constraint FK_LU_BP_REASONS_LU_BUS_PRCS foreign key (HEADER_ID)
  references LU_BUS_PRCS (ID);
alter table LU_BP_REASONS
  add constraint CHK_LU_BP_REASONS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_BP_REASONS
  add constraint CHK_LU_BP_REASONS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_BP_REASONS
  add constraint CHK_LU_BP_REASONS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_BP_SOLS
prompt =========================
prompt
create table LU_BP_SOLS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_BP_SOLS
  is ' COMPLAINT BP SOLUTIONS  (Õ·Ê· «·‘ﬂ«ÊÏ )';
comment on column LU_BP_SOLS.ID
  is 'Primary Key';
comment on column LU_BP_SOLS.CODE
  is 'User Code';
comment on column LU_BP_SOLS.PARENT_ID
  is 'Parent Record';
comment on column LU_BP_SOLS.HEADER_ID
  is 'LU_BP_REASONS';
comment on column LU_BP_SOLS.A_DSC
  is 'Arabic Desc';
comment on column LU_BP_SOLS.E_DSC
  is 'English Desc';
comment on column LU_BP_SOLS.ACTIVE
  is 'For User Activation';
comment on column LU_BP_SOLS.DELETED
  is 'For Logical Deletion';
alter table LU_BP_SOLS
  add constraint PK_LU_BP_SOLS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_SOLS
  add constraint UQ_LU_BP_SOLS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_BP_SOLS
  add constraint FK_LU_BP_SOLS foreign key (PARENT_ID)
  references LU_BP_SOLS (ID);
alter table LU_BP_SOLS
  add constraint FK_LU_BP_SOLS_LU_BP_REASONS foreign key (HEADER_ID)
  references LU_BP_REASONS (ID);
alter table LU_BP_SOLS
  add constraint CHK_LU_BP_SOLS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_BP_SOLS
  add constraint CHK_LU_BP_SOLS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_BP_SOLS
  add constraint CHK_LU_BP_SOLS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_COMP_AGAINST
prompt ==============================
prompt
create table LU_COMP_AGAINST
(
  ID                NUMBER not null,
  CODE              VARCHAR2(20) not null,
  PARENT_ID         NUMBER,
  PREV_PARENT_ID    NUMBER,
  HEADER_ID         NUMBER,
  PREV_HEADER_ID    NUMBER,
  A_DSC             VARCHAR2(200) not null,
  E_DSC             VARCHAR2(200) not null,
  NOTE_NOTES_ID     NUMBER,
  ACTIVE            CHAR(1) default 'T',
  DELETED           CHAR(1) default 'F',
  MODIFY_SEQ        NUMBER default 0,
  UPDATE_STATUS     NUMBER default 0,
  AGAINST_EDGE_TYPE VARCHAR2(4)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_COMP_AGAINST
  is 'Complaint Against  («·‘ﬂÊÏ Û÷œ )';
comment on column LU_COMP_AGAINST.ID
  is 'Primary Key';
comment on column LU_COMP_AGAINST.CODE
  is 'User Code';
comment on column LU_COMP_AGAINST.PARENT_ID
  is 'Parent Record';
comment on column LU_COMP_AGAINST.HEADER_ID
  is 'LU_ROOT';
comment on column LU_COMP_AGAINST.A_DSC
  is 'Arabic Desc';
comment on column LU_COMP_AGAINST.E_DSC
  is 'English Desc';
comment on column LU_COMP_AGAINST.ACTIVE
  is 'For User Activation';
comment on column LU_COMP_AGAINST.DELETED
  is 'For Logical Deletion';
alter table LU_COMP_AGAINST
  add constraint PK_LU_COMP_AGAINST primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMP_AGAINST
  add constraint UQ_LU_COMP_AGAINST_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMP_AGAINST
  add constraint FK_LU_COMP_AGAINST foreign key (PARENT_ID)
  references LU_COMP_AGAINST (ID);
alter table LU_COMP_AGAINST
  add constraint FK_LU_COMP_AGAINST_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_COMP_AGAINST
  add constraint CHK_LU_COMP_AGAINST_ACTIVE
  check (Active='F'or Active='T');
alter table LU_COMP_AGAINST
  add constraint CHK_LU_COMP_AGAINST_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_COMP_AGAINST
  add constraint CHK_LU_COMP_AGAINST_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_COMP_ROLES
prompt ============================
prompt
create table LU_COMP_ROLES
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_COMP_ROLES
  is ' Complaint Roles  (ÊŸ«∆› „” Œœ„Ï «·‰Ÿ«„ )';
comment on column LU_COMP_ROLES.ID
  is 'Primary Key';
comment on column LU_COMP_ROLES.CODE
  is 'User Code';
comment on column LU_COMP_ROLES.PARENT_ID
  is 'Parent Record';
comment on column LU_COMP_ROLES.HEADER_ID
  is 'LU_ROOT';
comment on column LU_COMP_ROLES.A_DSC
  is 'Arabic Desc';
comment on column LU_COMP_ROLES.E_DSC
  is 'English Desc';
comment on column LU_COMP_ROLES.ACTIVE
  is 'For User Activation';
comment on column LU_COMP_ROLES.DELETED
  is 'For Logical Deletion';
alter table LU_COMP_ROLES
  add constraint PK_LU_COMP_ROLES primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMP_ROLES
  add constraint UQ_LU_COMP_ROLES_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_COMP_ROLES
  add constraint FK_LU_COMP_ROLES foreign key (PARENT_ID)
  references LU_COMP_ROLES (ID);
alter table LU_COMP_ROLES
  add constraint FK_LU_COMP_ROLES_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_COMP_ROLES
  add constraint CHK_LU_COMP_ROLES_ACTIVE
  check (Active='F'or Active='T');
alter table LU_COMP_ROLES
  add constraint CHK_LU_COMP_ROLES_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_COMP_ROLES
  add constraint CHK_LU_COMP_ROLES_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_DEPTS
prompt =======================
prompt
create table LU_DEPTS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_DEPTS
  is ' NCCI Departments  (Departments )';
comment on column LU_DEPTS.ID
  is 'Primary Key';
comment on column LU_DEPTS.CODE
  is 'User Code';
comment on column LU_DEPTS.PARENT_ID
  is 'Parent Record';
comment on column LU_DEPTS.HEADER_ID
  is 'LU_ROOT';
comment on column LU_DEPTS.A_DSC
  is 'Arabic Desc';
comment on column LU_DEPTS.E_DSC
  is 'English Desc';
comment on column LU_DEPTS.ACTIVE
  is 'For User Activation';
comment on column LU_DEPTS.DELETED
  is 'For Logical Deletion';
alter table LU_DEPTS
  add constraint PK_LU_DEPTS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_DEPTS
  add constraint UQ_LU_DEPTS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_DEPTS
  add constraint FK_LU_DEPTS foreign key (PARENT_ID)
  references LU_DEPTS (ID);
alter table LU_DEPTS
  add constraint FK_LU_DEPTS_LU_ROOT foreign key (HEADER_ID)
  references LU_ROOT (ID);
alter table LU_DEPTS
  add constraint CHK_LU_DEPTS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_DEPTS
  add constraint CHK_LU_DEPTS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_DEPTS
  add constraint CHK_LU_DEPTS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_LOB_ESC_MAPS
prompt ==============================
prompt
create table LU_LOB_ESC_MAPS
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
  UPDATE_STATUS  NUMBER default 0,
  ESC_MAP_TYPE   VARCHAR2(6) default 'PER'
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_LOB_ESC_MAPS
  is 'LOB ESCALATION MAPS  (Œ—«∆ÿ «· ’⁄Ìœ )';
comment on column LU_LOB_ESC_MAPS.ID
  is 'Primary Key';
comment on column LU_LOB_ESC_MAPS.CODE
  is 'User Code';
comment on column LU_LOB_ESC_MAPS.PARENT_ID
  is 'Parent Record';
comment on column LU_LOB_ESC_MAPS.HEADER_ID
  is 'LU_LOB';
comment on column LU_LOB_ESC_MAPS.A_DSC
  is 'Arabic Desc';
comment on column LU_LOB_ESC_MAPS.E_DSC
  is 'English Desc';
comment on column LU_LOB_ESC_MAPS.ACTIVE
  is 'For User Activation';
comment on column LU_LOB_ESC_MAPS.DELETED
  is 'For Logical Deletion';
comment on column LU_LOB_ESC_MAPS.ESC_MAP_TYPE
  is 'PER = The Escalation Map is based On Persons , ''POS'' = The Escalation Map is Based on Positions';
alter table LU_LOB_ESC_MAPS
  add constraint PK_LU_LOB_ESC_MAPS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_ESC_MAPS
  add constraint UQ_LU_LOB_ESC_MAPS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_ESC_MAPS
  add constraint FK_LU_LOB_ESC_MAPS foreign key (PARENT_ID)
  references LU_LOB_ESC_MAPS (ID);
alter table LU_LOB_ESC_MAPS
  add constraint FK_LU_LOB_ESC_MAPS_LU_LOB foreign key (HEADER_ID)
  references LU_LOB (ID);
alter table LU_LOB_ESC_MAPS
  add constraint CHK_LU_LOB_ESC_MAPS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_LOB_ESC_MAPS
  add constraint CHK_LU_LOB_ESC_MAPS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_LOB_ESC_MAPS
  add constraint CHK_LU_LOB_ESC_MAPS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_LOB_REASONS
prompt =============================
prompt
create table LU_LOB_REASONS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_LOB_REASONS
  is ' COMPLAINT LOB REASONS  («”»«» «·‘ﬂ«ÊÏ )';
comment on column LU_LOB_REASONS.ID
  is 'Primary Key';
comment on column LU_LOB_REASONS.CODE
  is 'User Code';
comment on column LU_LOB_REASONS.PARENT_ID
  is 'Parent Record';
comment on column LU_LOB_REASONS.HEADER_ID
  is 'LU_LOB';
comment on column LU_LOB_REASONS.A_DSC
  is 'Arabic Desc';
comment on column LU_LOB_REASONS.E_DSC
  is 'English Desc';
comment on column LU_LOB_REASONS.ACTIVE
  is 'For User Activation';
comment on column LU_LOB_REASONS.DELETED
  is 'For Logical Deletion';
alter table LU_LOB_REASONS
  add constraint PK_LU_LOB_REASONS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_REASONS
  add constraint UQ_LU_LOB_REASONS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_REASONS
  add constraint FK_LU_LOB_REASONS foreign key (PARENT_ID)
  references LU_LOB_REASONS (ID);
alter table LU_LOB_REASONS
  add constraint FK_LU_LOB_REASONS_LU_LOB foreign key (HEADER_ID)
  references LU_LOB (ID);
alter table LU_LOB_REASONS
  add constraint CHK_LU_LOB_REASONS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_LOB_REASONS
  add constraint CHK_LU_LOB_REASONS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_LOB_REASONS
  add constraint CHK_LU_LOB_REASONS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_LOB_SOLS
prompt ==========================
prompt
create table LU_LOB_SOLS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_LOB_SOLS
  is ' COMPLAINT LOB SOLUTIONS  (Õ·Ê· «·‘ﬂ«ÊÏ )';
comment on column LU_LOB_SOLS.ID
  is 'Primary Key';
comment on column LU_LOB_SOLS.CODE
  is 'User Code';
comment on column LU_LOB_SOLS.PARENT_ID
  is 'Parent Record';
comment on column LU_LOB_SOLS.HEADER_ID
  is 'LU_LOB_REASONS';
comment on column LU_LOB_SOLS.A_DSC
  is 'Arabic Desc';
comment on column LU_LOB_SOLS.E_DSC
  is 'English Desc';
comment on column LU_LOB_SOLS.ACTIVE
  is 'For User Activation';
comment on column LU_LOB_SOLS.DELETED
  is 'For Logical Deletion';
alter table LU_LOB_SOLS
  add constraint PK_LU_LOB_SOLS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_SOLS
  add constraint UQ_LU_LOB_SOLS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_LOB_SOLS
  add constraint FK_LU_LOB_SOLS foreign key (PARENT_ID)
  references LU_LOB_SOLS (ID);
alter table LU_LOB_SOLS
  add constraint FK_LU_LOB_SOLS_LU_LOB_REASONS foreign key (HEADER_ID)
  references LU_LOB_REASONS (ID);
alter table LU_LOB_SOLS
  add constraint CHK_LU_LOB_SOLS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_LOB_SOLS
  add constraint CHK_LU_LOB_SOLS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_LOB_SOLS
  add constraint CHK_LU_LOB_SOLS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_SECTIONS
prompt ==========================
prompt
create table LU_SECTIONS
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_SECTIONS
  is ' NCCI SECTIONS  (SECTIONS )';
comment on column LU_SECTIONS.ID
  is 'Primary Key';
comment on column LU_SECTIONS.CODE
  is 'User Code';
comment on column LU_SECTIONS.PARENT_ID
  is 'Parent Record';
comment on column LU_SECTIONS.HEADER_ID
  is 'LU_DEPTS';
comment on column LU_SECTIONS.A_DSC
  is 'Arabic Desc';
comment on column LU_SECTIONS.E_DSC
  is 'English Desc';
comment on column LU_SECTIONS.ACTIVE
  is 'For User Activation';
comment on column LU_SECTIONS.DELETED
  is 'For Logical Deletion';
alter table LU_SECTIONS
  add constraint PK_LU_SECTIONS primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_SECTIONS
  add constraint UQ_LU_SECTIONS_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_SECTIONS
  add constraint FK_LU_SECTIONS foreign key (PARENT_ID)
  references LU_SECTIONS (ID);
alter table LU_SECTIONS
  add constraint FK_LU_SECTIONS_LU_DEPTS foreign key (HEADER_ID)
  references LU_DEPTS (ID);
alter table LU_SECTIONS
  add constraint CHK_LU_SECTIONS_ACTIVE
  check (Active='F'or Active='T');
alter table LU_SECTIONS
  add constraint CHK_LU_SECTIONS_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_SECTIONS
  add constraint CHK_LU_SECTIONS_PARENT
  check (parent_id <>ID);

prompt
prompt Creating table LU_SEC_ESC_MAP
prompt =============================
prompt
create table LU_SEC_ESC_MAP
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
  UPDATE_STATUS  NUMBER default 0,
  ESC_MAP_TYPE   VARCHAR2(6) default 'PER'
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
comment on table LU_SEC_ESC_MAP
  is 'SEC ESCALATION MAPS  (Œ—«∆ÿ «· ’⁄Ìœ )';
comment on column LU_SEC_ESC_MAP.ID
  is 'Primary Key';
comment on column LU_SEC_ESC_MAP.CODE
  is 'User Code';
comment on column LU_SEC_ESC_MAP.PARENT_ID
  is 'Parent Record';
comment on column LU_SEC_ESC_MAP.HEADER_ID
  is 'LU_SECTIONS';
comment on column LU_SEC_ESC_MAP.A_DSC
  is 'Arabic Desc';
comment on column LU_SEC_ESC_MAP.E_DSC
  is 'English Desc';
comment on column LU_SEC_ESC_MAP.ACTIVE
  is 'For User Activation';
comment on column LU_SEC_ESC_MAP.DELETED
  is 'For Logical Deletion';
comment on column LU_SEC_ESC_MAP.ESC_MAP_TYPE
  is 'PER = The Escalation Map is based On Persons , ''POS'' = The Escalation Map is Based on Positions';
alter table LU_SEC_ESC_MAP
  add constraint PK_LU_SEC_ESC_MAP primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_SEC_ESC_MAP
  add constraint UQ_LU_SEC_ESC_MAP_CODE unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table LU_SEC_ESC_MAP
  add constraint FK_LU_SEC_ESC_MAP foreign key (PARENT_ID)
  references LU_SEC_ESC_MAP (ID);
alter table LU_SEC_ESC_MAP
  add constraint FK_LU_SEC_ESC_MAP_LU_SECTIONS foreign key (HEADER_ID)
  references LU_SECTIONS (ID);
alter table LU_SEC_ESC_MAP
  add constraint CHK_LU_SEC_ESC_MAP_ACTIVE
  check (Active='F'or Active='T');
alter table LU_SEC_ESC_MAP
  add constraint CHK_LU_SEC_ESC_MAP_DELETED
  check (Deleted='F'or Deleted='T');
alter table LU_SEC_ESC_MAP
  add constraint CHK_LU_SEC_ESC_MAP_PARENT
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
    initial 10K
    next 10K
    minextents 1
    maxextents unlimited
    pctincrease 50
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table ROLE_PERMISSIONS
prompt ===============================
prompt
create table ROLE_PERMISSIONS
(
  ROLE_ID       NUMBER,
  PERMISSION_ID NUMBER
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );
alter table ROLE_PERMISSIONS
  add constraint ROLE_PERM_UK unique (ROLE_ID,PERMISSION_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table SYS_PARAMS
prompt =========================
prompt
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
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
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
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
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
    initial 10K
    next 36K
    minextents 1
    maxextents 121
    pctincrease 50
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
    initial 10K
    next 24K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating table VACATION_DATES
prompt =============================
prompt
create table VACATION_DATES
(
  START_DATE DATE default sysdate,
  END_DATE   DATE default sysdate
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 10K
    next 10K
    minextents 1
    maxextents 121
    pctincrease 50
  );

prompt
prompt Creating sequence AUD$_SEQ
prompt ==========================
prompt
create sequence AUD$_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1437
increment by 1
cache 20;

prompt
prompt Creating sequence COMPLAINTANTS_TBL_SEQ
prompt =======================================
prompt
create sequence COMPLAINTANTS_TBL_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1241
increment by 1
cache 20;

prompt
prompt Creating sequence COMPLAINTS_TBL_SEQ
prompt ====================================
prompt
create sequence COMPLAINTS_TBL_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1433
increment by 1
cache 20;

prompt
prompt Creating sequence COMPLAINT_RESPONSES_TBL_SEQ
prompt =============================================
prompt
create sequence COMPLAINT_RESPONSES_TBL_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1630
increment by 1
cache 20;

prompt
prompt Creating sequence COMPLAINT_SOLUTIONS_TBL_SEQ
prompt =============================================
prompt
create sequence COMPLAINT_SOLUTIONS_TBL_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence COMP_ATTACHMENTS_SEQ
prompt ======================================
prompt
create sequence COMP_ATTACHMENTS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1157
increment by 1
cache 20;

prompt
prompt Creating sequence ESCAL_MAP_DETAILS_TBL_SEQ
prompt ===========================================
prompt
create sequence ESCAL_MAP_DETAILS_TBL_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1348
increment by 1
cache 20;

prompt
prompt Creating sequence LU_BP_SOLS_SEQ
prompt ================================
prompt
create sequence LU_BP_SOLS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence LU_BUS_PRCS_SEQ
prompt =================================
prompt
create sequence LU_BUS_PRCS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1023
increment by 1
cache 20;

prompt
prompt Creating sequence LU_COMP_AGAINST_SEQ
prompt =====================================
prompt
create sequence LU_COMP_AGAINST_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1055
increment by 1
cache 20;

prompt
prompt Creating sequence LU_COMP_ROLES_SEQ
prompt ===================================
prompt
create sequence LU_COMP_ROLES_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence LU_DEPTS_SEQ
prompt ==============================
prompt
create sequence LU_DEPTS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1042
increment by 1
cache 20;

prompt
prompt Creating sequence LU_ESC_CHART_SEQ
prompt ==================================
prompt
create sequence LU_ESC_CHART_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1061
increment by 1
cache 20;

prompt
prompt Creating sequence LU_ESC_MAPS_SEQ
prompt =================================
prompt
create sequence LU_ESC_MAPS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1068
increment by 1
cache 20;

prompt
prompt Creating sequence LU_LOB_SEQ
prompt ============================
prompt
create sequence LU_LOB_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1079
increment by 1
cache 20;

prompt
prompt Creating sequence LU_LOB_SOLS_SEQ
prompt =================================
prompt
create sequence LU_LOB_SOLS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence LU_REASONS_SEQ
prompt ================================
prompt
create sequence LU_REASONS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1201
increment by 1
cache 20;

prompt
prompt Creating sequence LU_SECTIONS_SEQ
prompt =================================
prompt
create sequence LU_SECTIONS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1080
increment by 1
cache 20;

prompt
prompt Creating sequence LU_SEC_ESC_MAP_SEQ
prompt ====================================
prompt
create sequence LU_SEC_ESC_MAP_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence LU_TYPES_SEQ
prompt ==============================
prompt
create sequence LU_TYPES_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1023
increment by 1
cache 20;

prompt
prompt Creating sequence PS_TXN_SEQ
prompt ============================
prompt
create sequence PS_TXN_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 23
increment by 1
cache 20;

prompt
prompt Creating sequence SYS_SCRIPT_SEQ
prompt ================================
prompt
create sequence SYS_SCRIPT_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1901
increment by 1
cache 20;

prompt
prompt Creating view V$ALL_LOOKUP_HIERARCHY
prompt ====================================
prompt
create or replace view v$all_lookup_hierarchy as
select '_'||xx.id as id , 'LU_'||xx.dtable as tableName, decode (yy.id,null,null,'_'||yy.id) as Parent_Id, '1' as linked,
(select comments
  from sys.all_tab_comments
  where owner = 'COMPL'  and table_name = 'LU_'||xx.dtable ) as A_DSC
, xx.dtable as E_DSC, '_'||xx.id as idinTable, '0' as header_Id
from
(select decode(a.table_name,'LU_ROOT',0,RowNum) as Id, substr(a.table_Name,4,30) as DTable,substr(b.table_Name,4,30) as MTable, a.constraint_name
        from all_constraints a, all_constraints b
        where a.owner = 'COMPL'
        and b.owner (+)= a.owner
        and a.Table_Name like 'LU%'
        and a.r_constraint_name = b.constraint_name (+)
        and a.table_name <>b.table_name (+)
        and a.constraint_type = 'R'
        and ((b.table_name Is not null ) or (a.table_name='LU_ROOT'))) xx,
(select decode(a.table_name,'LU_ROOT',0,RowNum) as Id, substr(a.table_Name,4,30) as DTable, substr(b.table_Name,4,30) as MTable, a.constraint_name
        from all_constraints a, all_constraints b
        where a.owner = 'COMPL'
        and b.owner (+)= a.owner
        and a.Table_Name like 'LU%'
        and a.r_constraint_name = b.constraint_name (+)
        and a.table_name <>b.table_name (+)
        and a.constraint_type = 'R'
        and ((b.table_name Is not null ) or (a.table_name='LU_ROOT'))) yy
where xx.mtable  = yy.dtable (+)
order by xx.id
/

prompt
prompt Creating view V_CLIENTS
prompt =======================
prompt
CREATE OR REPLACE VIEW V_CLIENTS AS
SELECT  "CUST_ID","SOURCE_CODE","SUPP_ID","A_NAME","E_NAME","A_CONTACT_PERSON","E_CONTACT_PERSON","TEL_1","TEL_2","A_ADDRESS_1","E_ADDRESS_1","A_ADDRESS_2","E_ADDRESS_2","FAX_NO","TELEX_NO","SALESMAN_CODE","CREDIT_LIMIT","CREDIT_PERIOD","CONTROL_ACCT","BROKERAGE_LIMIT","BUS_IN_LIMIT","BALANCE","E_REMARKS","A_REMARKS","BLACKLIST_FLAG","N_A_TRANS","CORR_TYPE","BRANCH_CODE","LOC_INT_FLAG","SUB_LOC_CODE","STMT_OF_ACCT","CUST_TYPE","TRADE_BUSINESS","PAY_APPL","GROUB_CODE","ID_NO","SUPP_YN","HO_LOC","TITLE_NO","STATUS","BUSINESS_OCUPATION","PAYMENT_METHOD","INSERTED_BY","INSERT_DATE","LAST_UPDATED_BY","LAST_UPDATE_DATE","NATIONALITY_CODE","YEAR_OF_BIRTH","VIP_FLAG","DO_RENEWALS","EMAIL","ACC_PREM","STL_CLAIMS","LOBS","POLICIES","GSB","SEGMENT_CODE","GENDER","BLACK_LIST_REASON_CODE","NO_MAIL","MOBILE_NO" FROM edge.client
/

prompt
prompt Creating view V_OSUPPLIER
prompt =========================
prompt
create or replace view v_osupplier as
select  OSUPPLIER.SUPP_ID                                  SUPPLIER_CODE
       ,substr(SevenToEight( OSUPPLIER.A_NAME),1,40)       ARABIC_NAME
       ,OSUPPLIER.E_NAME                                   ENGLISH_NAME
       ,substr(SevenToEight( OSUPPLIER.A_CONTACT_PERSON),1,40)
                 ARABIC_CONTACT_PERSON
       ,OSUPPLIER.E_CONTACT_PERSON                         ENGLISH_CONTACT_PERSON
       ,OSUPPLIER.TEL_1                                    TEL_1
       ,OSUPPLIER.TEL_2                                    TEL_2
       ,substr(SevenToEight( OSUPPLIER.A_ADDRESS_1),1,40)  ARABIC_ADDRESS_1
       ,OSUPPLIER.E_ADDRESS_1                              ENGLISH_ADDRESS_1
       ,substr(SevenToEight( OSUPPLIER.A_ADDRESS_2),1,40)  ARABIC_ADDRESS_2
       ,OSUPPLIER.E_ADDRESS_2                              ENGLISH_ADDRESS_2
       ,OSUPPLIER.FAX_NO                                   FAX_NO
       ,OSUPPLIER.TELEX_NO                                 TELEX_NO
       ,OSUPPLIER.CONTROL_ACCT                             CONTROL_ACCT
       ,OSUPPLIER.BALANCE                                  BALANCE
       ,OSUPPLIER.E_REMARKS                                E_REMARKS
       ,substr(SevenToEight( OSUPPLIER.A_REMARKS),1,40)     A_REMARKS
       ,OSUPPLIER.N_A_TRANS                                N_A_TRANS
       ,OSUPPLIER.STMT_OF_ACCT                             STATEMENT_OF_ACCOUNT
       ,OSUPPLIER.ARORAPOP                                 ARORAPOP
       ,OSUPPLIER.ID_NO                                    ID_NO
       ,Decode (OSUPPLIER.SUPP_TYPE,'TW',
	   'GA',osupplier.supp_type )                      SUPPLIER_TYPE
       ,OSUPPLIER.HO_LOC                                   HO_LOC
       ,OSUPPLIER.BRANCH_CODE                              BRANCH_CODE
       ,OSUPPLIER.CORR_TYPE                                CORRESPONDANCE_TYPE
       ,OSUPPLIER.LOC_INT_FLAG		                   LOCAL_INTERNATIONAL_FLAG
       ,OSUPPLIER.PRIMARY_CONTACT_NO                       PRIMARY_CONTACT_NO
       ,OSUPPLIER.SECONDERY_CONTACT_NO                     SECONDARY_CONTACT_NO
       ,OSUPPLIER.REPAIR_ID                                REPAIR_ID
       ,OSUPPLIER.SPARES_ID                                SPARES_ID
       ,OSUPPLIER.CUSTOMER_ID                              CUSTOMER_ID
       ,OSUPPLIER.LOCATION_CODE                            LOCATION_CODE
       ,OSUPPLIER.DEALER                                   DEALER
       ,OSUPPLIER.TOWING                                   TOWING
       ,OSUPPLIER.NO_OF_VEHICLES                           NO_OF_VEHICLES
                FROM  edge.osupplier
                where supp_type in ('AD','GA','SV','TP','TW','HO')
                with read only
/

prompt
prompt Creating view V_SOURCE
prompt ======================
prompt
create or replace view v_source as
select "SOURCE_CODE","SOURCE_NAME","SOURCE_TYPE","LDG_TYPE","BRANCH_CODE","COMM_PERC","SOURCE_NO","DO_RENEWALS","A_NAME","ON_DUTY","M_ROW$$"
    from edge.v_source
/

prompt
prompt Creating view V_COMP_AGAINST_CAT
prompt ================================
prompt
CREATE OR REPLACE VIEW V_COMP_AGAINST_CAT AS
SELECT DISTINCT  t.supplier_type against_cat, 'supplier' against_cat_desc FROM v_osupplier t
UNION
SELECT DISTINCT t2.source_type against_cat,'souce' against_cat_desc FROM v_source t2
UNION
SELECT DISTINCT 'CSE' against_cat_desc , 'User_inf'  against_cat_desc FROM dual
/

prompt
prompt Creating view V_POLICY
prompt ======================
prompt
create or replace view v_policy as
select "BRANCH_CODE","POLICY_NO","QUOTATION_NO","SECURITY_LEVEL","E_NAME","A_NAME","DATE_ISS","DATE_FROM","DATE_TO","POL_FEE","GEO_AREA","TTL_CLAIMS","TTL_ENDT","CURRENCY_CODE","EX_RATE","RNWL_PREM","INV_FLAG","PROF_SHAR_FLG","NO_CLM_BNS_FLG","CUST_ID","COMMENT1","N_F_CONSIDER","N_C_POL","LONG_TERM","FINAL_FLAG","INST_UPDATE_FLAG","OLD_POLICY_NO","NEW_POLICY_NO","FIXED_EX_RATE","INS_TYPE","CO_INS_SHARE","BUS_TYPE","TTL_CERTIFS","LOB_CODE","NN","NCA_PERIOD","CNTRL_PERC","SPCL_ACCPTNCE_FLAG","A_GEO_AREA","A_COMMENT1","A_COMMENT2","A_COMMENT3","A_COMMENT4","ENTRY_DATE","UPD_DATE","USER_ID","M_YEARS","M_MONTHS","M_DAYS","SCHEME_NO","MC_DEPOSIT_CONTR","DRV_LIC_EXP_DATE","LAST_UPDATED_BY","LAST_UPDATE_DATE","CREATED_BY","CREATION_DATE","FRONTING_FEE_FLAG","FRONTING_FEE","OUT_PATIENT_ADM_FEE","EXPIRY_DATE"
    from EDGE.POLICY
/

prompt
prompt Creating view V_PRODUCTS
prompt ========================
prompt
CREATE OR REPLACE VIEW V_PRODUCTS AS
SELECT  "PRODUCT_CODE","LOB_LOB_CODE","NEED_SURVEY","NAME","DESCRIPTION","FORM_NAME","CREATED_BY","CREATION_DATE","LAST_UPDATED_BY","LAST_UPDATE_DATE","FLAT_PRICE","PROPOSAL_FORM_REPORT_NAME","AGENCY_REPAIR_LOADING","BELOW_AGE_LOADING","LEASED_VEHICLE_LOADING","NON_EXPERIENCED_DRIVER_LOADING","BAHRAIN_COVER_LOADING","AGENCY_REPAIR_AGE_LIMIT","PL_DRIVER_AGE_LIMIT","CL_DRIVER_AGE_LIMIT","EXTENSION_AGE_LIMIT","BAHRAIN_EXTENSION_AGE_LIMIT","PL_SEATING_CAPACITY_LIMIT","PRODUCT_AGE_LIMIT_FOR_CL","PRODUCT_AGE_LIMIT_FOR_PL","MAX_DISC_PER","MAX_COMM_PER","MAX_COST","RATE1","RATE2","RATE3","MIN_CONT_PER_RISK","PRODUCT_PERIOD","DED_CODE","STATUS","INT_CODE1","INT_CODE2","INT_CODE3" FROM csc.ncci_products
/

prompt
prompt Creating view V_RUKHSA_MEMBERS
prompt ==============================
prompt
create or replace view v_rukhsa_members as
select "BRANCH_CODE","POLICY_NO","QUOTATION_NO","LOB_CODE","ENDT_NO","ENDT_TYPE","DATE_FROM","DATE_TO","LIC_NO","E_MEM_NAME","A_MEM_NAME","DOB","LIC_ISS_DATE","LIC_EXP_DATE","LAST_UPDATED_BY","LAST_UPDATE_DATE","CREATED_BY","CREATION_DATE","RISK_NO"
    from EDGE.RUKHSA_MEMBER
/

prompt
prompt Creating view V_USER_INF
prompt ========================
prompt
create or replace view v_user_inf as
select "ACC_AUTH","BRANCH_CODE","USER_CODE","USER_NAME","USER_AUTHORITY","USER_COM_ID","SEC_CODE","GRADE","SOURCE_CODE","SUB_BRANCH_CODE","M_ROW$$"
    from edge.v_user_inf
/

prompt
prompt Creating package COMPLAINTS_MISC_SPU
prompt ====================================
prompt
create or replace package Complaints_Misc_SPU is

  -- Author  : FODA_SH
  -- Created : 24-May-03 2:03:40 PM
  -- Purpose : General Purpose package for the complaints system

 FUNCTION AUTH_DEL(AFROM VARCHAR2,ADATE DATE) return VARCHAR2;
 function get_Compl_level(comp_id number ) return number;
end Complaints_Misc_SPU;
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

FUNCTION GET_NO_OffDays (SDATE timestamp,EDATE timestamp, weekEndDay varchar , include_Vacation boolean ) RETURN NUMBER;

FUNCTION GET_New_End_Date (SDATE timestamp,EDATE timestamp, WeekEnd1 varchar , WeekEnd2 varchar) RETURN date;

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
prompt Creating function TEST
prompt ======================
prompt
CREATE OR REPLACE Function TEST  return varchar2 is
BEGIN
return 'abc';
END;
/

prompt
prompt Creating package body COMPLAINTS_MISC_SPU
prompt =========================================
prompt
create or replace package body Complaints_Misc_SPU is

  -- Private type declarations
  --type <TypeName> is <Datatype>;

  -- Private constant declarations
  --<ConstantName> constant <Datatype> := <Value>;

  -- Private variable declarations
 --  <VariableName> <Datatype>;

  -- Function and procedure implementations
  FUNCTION AUTH_DEL(AFROM VARCHAR2,ADATE DATE) return varchar2 is
a1    varchar2(15) ;
a2    varchar2(15) ;
a3    varchar2(15) ;
begin
  begin
    SELECT auth_to into a1
    FROM WFLO_APPS.AUTH_DELEGATION
    WHERE AUTH_FROM = afrom AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
    IF a1 = afrom  THEN
       RETURN a1;
    ELSE
       BEGIN
         SELECT auth_to into a2
         FROM WFLO_APPS.AUTH_DELEGATION
         WHERE AUTH_FROM = a1  AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
         If a2 = a1 or a2= afrom  THEN
            RETURN  a2;
         ELSE
            BEGIN
              SELECT auth_to into a3
              FROM WFLO_APPS.AUTH_DELEGATION
              WHERE AUTH_FROM = a2  AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
              If a3 = a2 or a3= afrom  THEN
                 return a3;
              else
                 return AUTH_DEL(a2,adate);
              end if;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
               RETURN a2;
            END;
         END IF;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
         retuRn a1;
       END;
    END IF;
  exception
  when no_data_found then
  return afrom;
  end;
end;
-------------
function get_Compl_level(comp_id number ) return number is
result number;
begin

select nvl(sum(abc),0) into result from
(
    ( select count(t1.response_type) abc from complaint_responses_tbl t1
      where t1.response_date >
            (
            select max(t.response_date)
            from complaint_responses_tbl t
            where (
                    (t.response_type='Reprocess'
                     Or upper(t.response_type)='REACTIVATE'
                     or upper(t.response_type)= 'CREATED')
                     and t.complaint_id = comp_id 
                     and t.deleted = 'N'                 )
            )
            and UPPER(t1.response_type) = 'ESCALATE'
            and t1.complaint_id = comp_id
            and t1.deleted = 'N'
    )
    union
    ( select count(t2.response_type)* -1 abc from complaint_responses_tbl t2
      where t2.response_date >
            (
            select max(t.response_date)
            from complaint_responses_tbl t
            where (
                   (t.response_type='Reprocess'
                     Or upper(t.response_type)='REACTIVATE'
                     or upper(t.response_type)= 'CREATED')
                     and t.complaint_id = comp_id
                     and t.deleted = 'N'
                   )
            )
            and UPPER(t2.response_type) = 'DESCALATE'
            and t2.complaint_id = comp_id
            and t2.deleted = 'N'
    )
);

if result <0 then result := 0; end if;
return result ;
end;
---------------------------
end Complaints_Misc_SPU;
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
FUNCTION GET_NO_OffDays (SDATE timestamp,EDATE timestamp, weekEndDay varchar , include_Vacation boolean ) RETURN NUMBER IS
  off_days NUMBER := 0;
	SDA DATE := SDATE ;
  x varchar(20) := '';
  counter number := 0;
BEGIN
 LOOP
   x  :=   trim(Upper(TO_CHAR(SDA,'DAY')));
   IF  x= Upper(weekEndDay) THEN
      off_days := off_days + 1;
   else 
       if include_Vacation then
           ----check if this day is a vacation day-----
           select count(*) into counter from vacation_dates t 
           where SDA >= start_date 
           and SDA <= end_date+1;
           if counter >= 1 then 
              off_days := off_days + 1;
           end if;
           -----End of check if this day is a vacation day
       end if;
   END IF;
   SDA := SDA+1;
   EXIT WHEN SDA > EDATE;
 END LOOP;

RETURN(off_days);
END;

----------------------
FUNCTION GET_New_End_Date (SDATE timestamp,EDATE timestamp, WeekEnd1 varchar , WeekEnd2 varchar) RETURN date IS
-- This function returns new end date after taking into accounts that WeekEnd1 and WeekEnd2 are weekend days 
   --1--- Taking into account week ends------
newEndDate date := EDATE;
in_period  number :=0;
total_Off_Days number :=0;
NoOfWeekEnds1 number :=0;
NoOfWeekEnds2 number :=0;
begin
 in_period := to_date(to_char(EDATE,'dd-mm-yyyy'),'dd-mm-yyyy') - to_date(to_char(Sdate,'dd-mm-yyyy'),'dd-mm-yyyy') +1 ;
 NoOfWeekEnds1 := GET_NO_OffDays(SDATE , EDATE, WeekEnd1, true);
 NoOfWeekEnds2 := GET_NO_OffDays(SDATE , EDATE, WeekEnd2, false);
 total_Off_Days := NoOfWeekEnds1 + NoOfWeekEnds2;

 --- NoOfWeekEnds1 + NoOfWeekEnds2 should not exceeds inperiod---
 if total_Off_Days > in_period then
 total_Off_Days := in_period;
 end if;
 
 newEndDate := EDATE + total_Off_Days;
  
 if newEndDate > EDATE  then 
  newEndDate := GET_New_End_Date(EDATE+1, newEndDate ,WeekEnd1,WeekEnd2);
 end if;
 
return newEndDate;
end;
----------------------------
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
prompt Creating trigger AUD_LU_ESC_CHART
prompt =================================
prompt
create or replace trigger AUD_LU_ESC_CHART
AFTER INSERT OR UPDATE OR DELETE ON LU_ESC_CHART
FOR EACH ROW
DECLARE
 Time_now DATE;
 MySession NUMBER;
 MyUserID  VARCHAR2(40) := USER;
BEGIN
  -- Skip Auditing Flag
  if (spu_Auditing.AuditFlag=false) and (Deleting) Then
    Return;
  End if;
  -- get current time, and user's session:
  Time_now := SYSDATE;
  MySession := USERENV('SESSIONID');
  BEGIN
    NULL; --select UserID into MyUserID
     --from Sys_User_Sessions
    --where Session_id = MySession;
  Exception
    WHEN NO_DATA_FOUND THEN
      MyUserID :=USER;
      Return;
  END;
  IF DELETING OR UPDATING THEN
    INSERT INTO AUD$_LU_ESC_CHART
     (Aud_OP_ID,OLD_NEW,Aud_TIME_STAMP, Aud_User_id
      ,ACTIVE
      ,DELETED
      ,MODIFY_SEQ
      ,UPDATE_STATUS
      ,CURRENT_EMP
      ,ID
      ,CODE
      ,PARENT_ID
      ,PREV_PARENT_ID
      ,HEADER_ID
      ,PREV_HEADER_ID
      ,A_DSC
      ,E_DSC
      ,NOTE_NOTES_ID
      ) VALUES
      (Aud$_Seq.Nextval,'O', Time_Now, MyUserID
      , :OLD.ACTIVE
      , :OLD.DELETED
      , :OLD.MODIFY_SEQ
      , :OLD.UPDATE_STATUS
      , :OLD.CURRENT_EMP
      , :OLD.ID
      , :OLD.CODE
      , :OLD.PARENT_ID
      , :OLD.PREV_PARENT_ID
      , :OLD.HEADER_ID
      , :OLD.PREV_HEADER_ID
      , :OLD.A_DSC
      , :OLD.E_DSC
      , :OLD.NOTE_NOTES_ID
      );
  END IF;
  IF INSERTING OR UPDATING THEN
    INSERT INTO AUD$_LU_ESC_CHART
     (Aud_OP_ID,OLD_NEW,Aud_TIME_STAMP, Aud_User_id
      ,ACTIVE
      ,DELETED
      ,MODIFY_SEQ
      ,UPDATE_STATUS
      ,CURRENT_EMP
      ,ID
      ,CODE
      ,PARENT_ID
      ,PREV_PARENT_ID
      ,HEADER_ID
      ,PREV_HEADER_ID
      ,A_DSC
      ,E_DSC
      ,NOTE_NOTES_ID
      ) VALUES
      (Aud$_Seq.Nextval,'N', Time_Now, MyUserID
      , :NEW.ACTIVE
      , :NEW.DELETED
      , :NEW.MODIFY_SEQ
      , :NEW.UPDATE_STATUS
      , :NEW.CURRENT_EMP
      , :NEW.ID
      , :NEW.CODE
      , :NEW.PARENT_ID
      , :NEW.PREV_PARENT_ID
      , :NEW.HEADER_ID
      , :NEW.PREV_HEADER_ID
      , :NEW.A_DSC
      , :NEW.E_DSC
      , :NEW.NOTE_NOTES_ID
      );
  END IF;
END AUD_LU_ESC_CHART;
/

prompt
prompt Creating trigger COMPLAINTANTS_TBL_$ID
prompt ======================================
prompt
create or replace trigger complaintants_tbl_$ID
 Before Insert ON complaintants_tbl
  for each row
  
Declare
  lSeq Number;
begin
 if :New.COMPLAINTANT_ID is null then
  select complaintants_tbl_Seq.NextVal into lSeq From Dual;
  :New.COMPLAINTANT_ID :=lSeq;
  end if;
end complaintants_tbl_$ID;
/

prompt
prompt Creating trigger COMPLAINTANTS_TBL_BIUTRNS
prompt ==========================================
prompt
create or replace trigger COMPLAINTANTS_TBL_bIUTrns
  before Insert OR Update OF
  E_NAME,A_NAME,E_ADDRESS1,A_ADDRESS1,E_ADDRESS2,A_ADDRESS2 ON COMPLAINTANTS_TBL
  for each row
-- Save Other Language Columns
begin
 if :new.E_NAME is null then
  :New.E_NAME:=:New.A_NAME;
 End if;
 if :new.A_NAME is null then
  :New.A_NAME:=:New.E_NAME;
 End if;
----------------
 if :new.E_ADDRESS1 is null then
  :New.E_ADDRESS1:=:New.A_ADDRESS1;
 End if;
 if :new.A_ADDRESS1 is null then
  :New.A_ADDRESS1:=:New.E_ADDRESS1;
 End if;
----------------
 if :new.E_ADDRESS2 is null then
  :New.E_ADDRESS2:=:New.A_ADDRESS2;
 End if;
 if :new.A_ADDRESS2 is null then
  :New.A_ADDRESS2:=:New.E_ADDRESS2;
 End if;
----------------
end COMPLAINTANTS_TBL_bIUTrns;
/

prompt
prompt Creating trigger COMPLAINTS_TBL_$ID
prompt ===================================
prompt
create or replace trigger complaints_tbl_$ID
 Before Insert ON complaints_tbl
  for each row
  
Declare
  lSeq Number;
begin
 if :New.COMPLAINT_ID is null then
  select complaints_tbl_Seq.NextVal into lSeq From Dual;
  :New.COMPLAINT_ID :=lSeq;
  end if;
end complaints_tbl_$ID;
/

prompt
prompt Creating trigger COMPLAINT_RESPONSES_TBL_$ID
prompt ============================================
prompt
create or replace trigger complaint_responses_tbl_$ID
 Before Insert ON complaint_responses_tbl
  for each row
  
Declare
  lSeq Number;
begin
 if :New.response_no is null then
  select complaint_responses_tbl_Seq.NextVal into lSeq From Dual;
  :New.response_no :=lSeq;
  end if;
end complaint_responses_tbl_$ID;
/

prompt
prompt Creating trigger COMPLAINT_SOLUTIONS_TBL_$ID
prompt ============================================
prompt
create or replace trigger complaint_solutions_tbl_$ID
 Before Insert ON complaint_solutions_tbl
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select complaint_solutions_tbl_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end complaint_solutions_tbl_$ID;
/

prompt
prompt Creating trigger COMP_ATTACHMENTS_$ID
prompt =====================================
prompt
create or replace trigger comp_attachments_$ID
 Before Insert ON comp_attachments
  for each row
  
Declare
  lSeq Number;
begin
 if :New.attach_id is null then
  select comp_attachments_Seq.NextVal into lSeq From Dual;
  :New.attach_id :=lSeq;
  end if;
end comp_attachments_$ID;
/

prompt
prompt Creating trigger COMP_ATTACHMENTS_BIUTRNS
prompt =========================================
prompt
create or replace trigger COMP_ATTACHMENTS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON COMP_ATTACHMENTS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end COMP_ATTACHMENTS_bIUTrns;
/

prompt
prompt Creating trigger ESCAL_MAP_DETAILS_TBL_$ID
prompt ==========================================
prompt
create or replace trigger escal_map_details_tbl_$ID
 Before Insert ON escal_map_details_tbl
  for each row
  
Declare
  lSeq Number;
begin
 if :New.detail_Id is null then
  select escal_map_details_tbl_Seq.NextVal into lSeq From Dual;
  :New.detail_Id :=lSeq;
  end if;
end escal_map_details_tbl_$ID;
/

prompt
prompt Creating trigger LU_BP_ESC_MAPS_$ID
prompt ===================================
prompt
create or replace trigger LU_BP_ESC_MAPs_$ID
 Before Insert ON LU_BP_ESC_MAPs
  for each row


Declare
  lSeq Number;
begin
 if :New.Id is null then
  select LU_ESC_MAPs_Seq.NextVal into lSeq From Dual;
  :New.Id :=lSeq;
  end if;
end LU_BP_ESC_MAPs_$ID;
/

prompt
prompt Creating trigger LU_BP_ESC_MAPS_BIUTRNS
prompt =======================================
prompt
create or replace trigger LU_BP_ESC_MAPS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_BP_ESC_MAPS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_BP_ESC_MAPS_bIUTrns;
/

prompt
prompt Creating trigger LU_BP_REASONS_$ID
prompt ==================================
prompt
create or replace trigger LU_BP_REASONS_$ID
 Before Insert ON LU_BP_REASONS
  for each row

Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_REASONS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_BP_REASONS_$ID;
/

prompt
prompt Creating trigger LU_BP_REASONS_BIUTRNS
prompt ======================================
prompt
create or replace trigger LU_BP_REASONS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_BP_REASONS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_BP_REASONS_bIUTrns;
/

prompt
prompt Creating trigger LU_BP_SOLS_$ID
prompt ===============================
prompt
create or replace trigger LU_BP_SOLS_$ID
 Before Insert ON LU_BP_SOLS
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_BP_SOLS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_BP_SOLS_$ID;
/

prompt
prompt Creating trigger LU_BP_SOLS_BIUTRNS
prompt ===================================
prompt
create or replace trigger LU_BP_SOLS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_BP_SOLS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_BP_SOLS_bIUTrns;
/

prompt
prompt Creating trigger LU_BUS_PRCS_$ID
prompt ================================
prompt
create or replace trigger LU_Bus_Prcs_$ID
 Before Insert ON LU_Bus_Prcs
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_Bus_Prcs_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_Bus_Prcs_$ID;
/

prompt
prompt Creating trigger LU_BUS_PRCS_BIUTRNS
prompt ====================================
prompt
create or replace trigger LU_BUS_PRCS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_BUS_PRCS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_BUS_PRCS_bIUTrns;
/

prompt
prompt Creating trigger LU_COMP_AGAINST_$ID
prompt ====================================
prompt
create or replace trigger LU_Comp_Against_$ID
 Before Insert ON LU_Comp_Against
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_Comp_Against_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_Comp_Against_$ID;
/

prompt
prompt Creating trigger LU_COMP_AGAINST_BIUTRNS
prompt ========================================
prompt
create or replace trigger LU_COMP_AGAINST_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_COMP_AGAINST
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_COMP_AGAINST_bIUTrns;
/

prompt
prompt Creating trigger LU_COMP_ROLES_$ID
prompt ==================================
prompt
create or replace trigger LU_Comp_Roles_$ID
 Before Insert ON LU_Comp_Roles
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_Comp_Roles_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_Comp_Roles_$ID;
/

prompt
prompt Creating trigger LU_COMP_ROLES_BIUTRNS
prompt ======================================
prompt
create or replace trigger LU_COMP_ROLES_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_COMP_ROLES
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_COMP_ROLES_bIUTrns;
/

prompt
prompt Creating trigger LU_DEPTS_$ID
prompt =============================
prompt
create or replace trigger LU_DEPTS_$ID
 Before Insert ON LU_DEPTS
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_DEPTS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_DEPTS_$ID;
/

prompt
prompt Creating trigger LU_DEPTS_BIUTRNS
prompt =================================
prompt
create or replace trigger LU_DEPTS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_DEPTS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_DEPTS_bIUTrns;
/

prompt
prompt Creating trigger LU_ESC_CHART_$ID
prompt =================================
prompt
create or replace trigger LU_ESC_Chart_$ID
 Before Insert ON LU_ESC_Chart
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_ESC_Chart_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_ESC_Chart_$ID;
/

prompt
prompt Creating trigger LU_ESC_CHART_BIUTRNS
prompt =====================================
prompt
create or replace trigger LU_ESC_CHART_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_ESC_CHART
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_ESC_CHART_bIUTrns;
/

prompt
prompt Creating trigger LU_LOB_$ID
prompt ===========================
prompt
create or replace trigger LU_LOB_$ID
 Before Insert ON LU_LOB
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_LOB_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_LOB_$ID;
/

prompt
prompt Creating trigger LU_LOB_BIUTRNS
prompt ===============================
prompt
create or replace trigger LU_LOB_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_LOB
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_LOB_bIUTrns;
/

prompt
prompt Creating trigger LU_LOB_ESC_MAPS_$ID
prompt ====================================
prompt
create or replace trigger LU_LOB_ESC_MAPs_$ID
 Before Insert ON LU_LOB_ESC_MAPs
  for each row

Declare
  lSeq Number;
begin
 if :New.Id is null then
  select LU_ESC_MAPs_Seq.NextVal into lSeq From Dual;
  :New.Id :=lSeq;
  end if;
end LU_LOB_ESC_MAPs_$ID;
/

prompt
prompt Creating trigger LU_LOB_ESC_MAPS_BIUTRNS
prompt ========================================
prompt
create or replace trigger LU_LOB_ESC_MAPS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_LOB_ESC_MAPS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_LOB_ESC_MAPS_bIUTrns;
/

prompt
prompt Creating trigger LU_LOB_REASONS_$ID
prompt ===================================
prompt
create or replace trigger LU_LOB_REASONS_$ID
 Before Insert ON LU_LOB_REASONS
  for each row

Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_REASONS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_LOB_REASONS_$ID;
/

prompt
prompt Creating trigger LU_LOB_REASONS_BIUTRNS
prompt =======================================
prompt
create or replace trigger LU_LOB_REASONS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_LOB_REASONS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_LOB_REASONS_bIUTrns;
/

prompt
prompt Creating trigger LU_LOB_SOLS_$ID
prompt ================================
prompt
create or replace trigger LU_LOB_SOLS_$ID
 Before Insert ON LU_LOB_SOLS
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_LOB_SOLS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_LOB_SOLS_$ID;
/

prompt
prompt Creating trigger LU_LOB_SOLS_BIUTRNS
prompt ====================================
prompt
create or replace trigger LU_LOB_SOLS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_LOB_SOLS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_LOB_SOLS_bIUTrns;
/

prompt
prompt Creating trigger LU_ROOT_BIUTRNS
prompt ================================
prompt
create or replace trigger LU_ROOT_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_ROOT
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_ROOT_bIUTrns;
/

prompt
prompt Creating trigger LU_SECTIONS_$ID
prompt ================================
prompt
create or replace trigger LU_SECTIONS_$ID
 Before Insert ON LU_SECTIONS
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_SECTIONS_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_SECTIONS_$ID;
/

prompt
prompt Creating trigger LU_SECTIONS_BIUTRNS
prompt ====================================
prompt
create or replace trigger LU_SECTIONS_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_SECTIONS
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_SECTIONS_bIUTrns;
/

prompt
prompt Creating trigger LU_SEC_ESC_MAP_$ID
prompt ===================================
prompt
create or replace trigger LU_SEC_ESC_MAP_$ID
 Before Insert ON LU_SEC_ESC_MAP
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_SEC_ESC_MAP_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_SEC_ESC_MAP_$ID;
/

prompt
prompt Creating trigger LU_SEC_ESC_MAP_BIUTRNS
prompt =======================================
prompt
create or replace trigger LU_SEC_ESC_MAP_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_SEC_ESC_MAP
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_SEC_ESC_MAP_bIUTrns;
/

prompt
prompt Creating trigger LU_TYPES_$ID
prompt =============================
prompt
create or replace trigger LU_TYPES_$ID
 Before Insert ON LU_COMM_MODE
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_TYPES_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_TYPES_$ID;
/

prompt
prompt Creating trigger LU_TYPES_BIUTRNS
prompt =================================
prompt
create or replace trigger LU_TYPES_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_COMM_MODE
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
----------------
end LU_TYPES_bIUTrns;
/

prompt
prompt Creating trigger SYS_SCRIPT_BI
prompt ==============================
prompt
create or replace trigger Sys_script_Bi
BEFORE INSERT On tblSys_Script
FOR EACH ROW
BEGIN
  SELECT Sys_Script_seq.nextval INTO :New.Id
   FROM DUAL;
END Sys_script_Bi;
/


spool off
