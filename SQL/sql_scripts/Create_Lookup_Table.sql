SET NEWPAGE 0
SET SPACE 0
SET LINESIZE 120
SET PAGESIZE 0
SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET MARKUP HTML OFF SPOOL OFF

@@Tables\tBLSys_Script.tbl
clear variables;
Delete from tBLSys_Script;
INPUT
PROMPT 'This tBLSys_Script will create a tBLSys_Script to create your lookup table '
PROMPT 'with its constraints'

ACCEPT TableName VARCHAR2 PROMPT 'Table Name:  '
ACCEPT TableDesc VARCHAR2 PROMPT 'English Table DetBLSys_Scription:  '
ACCEPT ATableDesc VARCHAR2 PROMPT 'Arabic Table DetBLSys_Scription:  '
ACCEPT HeaderName VARCHAR2 PROMPT 'Header Table Name(if none enter LU_ROOT):  '

INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Drop table ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'drop table &&TableName cascade constraints;');
INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Create table ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'create table &&TableName ');
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
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint PK_&&TableName primary key (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint UQ_&&TableName._CODE unique (CODE);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint FK_&&TableName foreign key (PARENT_ID)');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  references &&TableName (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
' ');
--- Cancel Notes Forign Key--- By Shawky Foda
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'alter table &&TableName ');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'  add constraint FK_&&TableName._Notes foreign key (Note_Notes_id)');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--'  references Notes (Notes_ID);');
--INSERT INTO tBLSys_Script (Line ) VALUES (
--' ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_&&TableName._Parent CHECK(parent_id <>ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');


INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint FK_&&TableName._&&HeaderName foreign key (Header_ID)');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  references &&HeaderName (ID);');
INSERT INTO tBLSys_Script (Line ) VALUES (
' ');
INSERT INTO tBLSys_Script (Line ) VALUES ( 'WHENEVER SQLERROR NONE;');


INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Create/Recreate check constraints ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_&&TableName._Active');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  check (Active=''F''or Active=''T'');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');
INSERT INTO tBLSys_Script (Line ) VALUES (
'alter table &&TableName ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  add constraint CHK_&&TableName._Deleted');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  check (Deleted=''F''or Deleted=''T'');');
INSERT INTO tBLSys_Script (Line ) VALUES (
'');

---------
INSERT INTO tBLSys_Script (Line ) VALUES (
'-- Add comments to the columns ');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..ID');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Primary Key'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..CODE');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''User Code'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..Parent_id');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Parent Record'';');
INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..Header_id');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''&&HeaderName'';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..E_DSC');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''English Desc'';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..A_DSC');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''Arabic Desc'';');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..Active');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''For User Activation'';  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on column &&TableName..Deleted');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  is ''For Logical Deletion'';  ');

INSERT INTO tBLSys_Script (Line ) VALUES (
'comment on Table &&TableName');
INSERT INTO tBLSys_Script (Line ) VALUES (
' is '' &&TableDesc (&&ATableDesc)''; ');

Commit;
INSERT INTO tBLSys_Script (Line ) VALUES (
'INSERT INTO &&TableName');
INSERT INTO tBLSys_Script (Line ) VALUES (
'  (ID, code,a_dsc, e_dsc )');
INSERT INTO tBLSys_Script (Line ) VALUES (  
'   VALUES ');
INSERT INTO tBLSys_Script (Line ) VALUES (  
'  (0, ''0'', ''&&ATableDesc''||'' '' ,''&&TableDesc''||'' '');');
INSERT INTO tBLSys_Script (Line ) VALUES (    
'Commit;');
INSERT INTO tBLSys_Script (Line ) VALUES (    
'/');

Spool c:\t.sql
Select Line from tBLSys_Script ORDER BY ID;
Spool off
@@c:\T.sql
/

