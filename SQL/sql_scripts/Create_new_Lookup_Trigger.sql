SET NEWPAGE 0
SET SPACE 0
SET LINESIZE 120
SET PAGESIZE 0
SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET MARKUP HTML OFF SPOOL OFF

Delete Sys_Script;
clear variables;
COMMIT;

INPUT
PROMPT 'This Script will create trigger for lookup tables to update ID from a sequence'
PROMPT 'and if Root is LU_Root it will update the header id by 0 all time.'
ACCEPT TableName VARCHAR2 PROMPT 'Table Name :  '
ACCEPT PKY       VARCHAR2 PROMPT 'Primary Key:  '

INSERT INTO Sys_Script(Line) VALUES(
'-- Drop sequence ');
INSERT INTO Sys_Script(Line) VALUES(
'Drop SEQUENCE  &&TableName._Seq; ');

INSERT INTO Sys_Script(Line) VALUES(
'-- Create sequence ');
INSERT INTO Sys_Script(Line) VALUES(
'Create SEQUENCE  &&TableName._Seq; ');


INSERT INTO Sys_Script(Line) VALUES(
'-- Create Trigger ');
INSERT INTO Sys_Script(Line) VALUES(
' CREATE OR REPLACE TRIGGER &&TableName._bi ');
INSERT INTO Sys_Script(Line) VALUES(
' BEFORE INSERT ON &&TableName ');
INSERT INTO Sys_Script(Line) VALUES(
' FOR EACH ROW ');
INSERT INTO Sys_Script(Line) VALUES(
'DECLARE');
INSERT INTO Sys_Script(Line) VALUES(
'lhdr VARCHAR2(200);');
INSERT INTO Sys_Script(Line) VALUES(
'BEGIN');
INSERT INTO Sys_Script(Line) VALUES(
'  SELECT &&TableName._Seq.NEXTVAL INTO :NEW.&&PKy FROM DUAL;'  );
INSERT INTO Sys_Script(Line) VALUES(
' ');
INSERT INTO Sys_Script(Line) VALUES(
'  SELECT comments');
INSERT INTO Sys_Script(Line) VALUES(
'    INTO lHdr');
INSERT INTO Sys_Script(Line) VALUES(
'    FROM user_col_comments  ');
INSERT INTO Sys_Script(Line) VALUES(
'    WHERE table_name=UPPER(''&&TableName'') ');
INSERT INTO Sys_Script(Line) VALUES(
'    AND Column_name = ''HEADER_ID'';');

INSERT INTO Sys_Script(Line) VALUES(
' ');
INSERT INTO Sys_Script(Line) VALUES( 
'   IF Instr(UPPER(lHdr),''LU_ROOT'')>0 THEN');
INSERT INTO Sys_Script(Line) VALUES(
'     :New.Header_id:=0;');
INSERT INTO Sys_Script(Line) VALUES(
'   END IF;');
INSERT INTO Sys_Script(Line) VALUES(
'END &&TableName._bi;');
INSERT INTO Sys_Script(Line) VALUES(
'/');

Commit;
Spool c:\t.sql
Select Line from Sys_Script;
Spool off
@c:\t.sql

SET ECHO ON
SET FEEDBACK ON
SET HEADING ON


