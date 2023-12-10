Prompt ***************** Creating notes **************************
drop TABLE NOTES cascade constraints
/

PROMPT Creating Table 'NOTES'
CREATE TABLE NOTES
 (E_DSC VARCHAR2(300)
 ,A_DSC VARCHAR2(300)
 ,ACTIVE VARCHAR2(1) DEFAULT 'T' NOT NULL
 ,NOTES_ID NUMBER(30) NOT NULL
 ,DELETED VARCHAR2(1) DEFAULT 'F' NOT NULL
 ,UPDATE_STATUS NUMBER DEFAULT 0
 ,MODIFY_SEQ NUMBER DEFAULT 0
 ,CODE VARCHAR2(30)
 ,KEYWORDS VARCHAR2(500)
 ,NOTE VARCHAR2(4000)
 ,PAGEURL VARCHAR2(300)
 )
/

COMMENT ON COLUMN NOTES.ACTIVE IS 'Record Status (Active=A, Not Active=I) '
/

COMMENT ON COLUMN NOTES.NOTES_ID IS 'Primary Key generated from Oracle Sequence'
/

COMMENT ON COLUMN NOTES.DELETED IS 'Refere to current recored be deleted Logicaly (true) or not deleted (false)(default)'
/

COMMENT ON COLUMN NOTES.UPDATE_STATUS IS 'Determin update or delete constraints on record'
/

COMMENT ON COLUMN NOTES.MODIFY_SEQ IS 'Filled by database trigger'
/

COMMENT ON COLUMN NOTES.CODE IS 'User Defined Code'
/

COMMENT ON COLUMN NOTES.KEYWORDS IS 'Keywords to Search in Notes Field'
/

PROMPT Creating Primary Key on 'NOTES'
ALTER TABLE NOTES
 ADD (CONSTRAINT NOTE_PK PRIMARY KEY 
  (NOTES_ID))
/


 
PROMPT Creating Check Constraint on 'NOTES'
ALTER TABLE NOTES
 ADD (CONSTRAINT AVCON_996668572_ACTIV_000 CHECK (ACTIVE IN ('T', 'F')))
/

PROMPT Creating Check Constraint on 'NOTES'
ALTER TABLE NOTES
 ADD (CONSTRAINT AVCON_996668572_DELET_000 CHECK (DELETED IN ('T', 'F')))
/



