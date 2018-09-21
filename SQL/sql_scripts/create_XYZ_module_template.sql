SET echo OFF;
Set Define off
set feedback OFF
SET HEADING OFF
set line 800;

Prompt -- Created By Shawky Foda
Prompt -- Date 19-05-2003-----
Prompt -- Purpose: To Create all the Complaints module database objects

prompt ***************************
prompt * creating Complaints TABLES; *
prompt ***************************

Drop table 
DROP TABLE 
DROP TABLE 
DROP TABLE 
DROP TABLE 
DROP TABLE 


@..\command_files\
@..\command_files\
@..\command_files\
@..\command_files\
@..\command_files\
@..\command_files\

prompt ***************************
prompt * Inserting basic DATA *
prompt ***************************
@..\basic_data\
@..\basic_data\


prompt ***************************
prompt * Inserting test DATA *
prompt ***************************
@..\test_data\
@..\test_data\



prompt ***************************
prompt * creating Complaints triggers *
prompt ***************************
@..\programe\

--- Creating Complaints Package----
prompt ***************************
prompt * creating ...... PACKAGE; *
prompt ***************************
@..\programe\
@..\programe\

prompt ***************************
prompt * creating ...... PACKAGE; *
prompt ***************************
@..\programe\
@..\programe\


prompt ***************************
prompt * creating Auditing PACKAGE; *
prompt ***************************
@..\programe\spu_Auditing.spc;
@..\programe\spu_Auditing.bdy;
DROP SEQUENCE AUD$_Seq;
CREATE SEQUENCE AUD$_Seq;

prompt ***************************
prompt * creating Auditing TABLE & TRIGGER FOR TABLES *
prompt ***************************

DELETE FROM tblSys_Script;
EXECUTE spu_auditing.create_audit_table('.....');
EXECUTE spu_Auditing.Create_Audit_Trigger('....');

--EXECUTE spu_auditing.create_audit_table('tblclaim_details');
--EXECUTE spu_Auditing.Create_Audit_Trigger('tblclaim_details');

EXECUTE spu_auditing.create_audit_table('....');
EXECUTE spu_Auditing.Create_Audit_Trigger('....');



Spool c:\t.SQL;
Select Line from tblSys_Script ORDER BY ID;
Spool OFF;
@@c:\t.SQL;



