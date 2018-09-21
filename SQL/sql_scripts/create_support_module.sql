--to demonstrate
SET echo OFF;
Set Define off
set feedback OFF
SET HEADING OFF
set line 800;

Prompt -- Created By Shawky Foda
Prompt -- Date 01-07-2004-----
Prompt -- Purpose: To Create all the Support module database objects

prompt ****************************
prompt * General Objects          *
prompt ****************************
DROP TABLE tblSys_SCRIPT;
DROP TABLE Sys_Params;
@..\command_files\tblsys_script.pdc;
@..\command_files\Sys_Params.pdc

prompt ***************************
prompt * creating ...... PACKAGES; *
prompt ***************************

prompt ***************************
prompt * 1- creating Auditing PACKAGE; *
prompt ***************************
@..\programe\spu_Auditing.spc;
@..\programe\spu_Auditing.bdy;
DROP SEQUENCE AUD$_Seq;
CREATE SEQUENCE AUD$_Seq;


prompt ***************************
prompt * 3- creating Misc PACKAGE; *
prompt ***************************
@..\programe\Misc.pck;


prompt ***************************
prompt * creating Complaints TABLES; *
prompt ***************************

DROP TABLE sql_bound_vars; 
DROP TABLE lu_queries; 
DROP TABLE LU_ROOT;

COMMIT;

prompt ***************************
prompt * creating Lookup TABLES  *
prompt ***************************
--DELETE FROM tblsys_script;
-- EXECUTE spu_Auditing.Create_Lookup_Table('LU_ROOT','ROOT','«·ﬁ«⁄œ…','');
-- EXECUTE spu_Auditing.Create_Lookup_Table('lu_queries','User Queries','«·«” ⁄·«„« ','LU_ROOT');



--Spool c:\t.SQL;
--Select Line from tblSys_Script ORDER BY ID;
--Spool OFF;
--@@c:\t.SQL;

prompt ***************************
prompt * creating Basic Support TABLES  *
prompt ***************************
@..\command_files\LU_Root.sql;
@..\command_files\LU_queries.pdc;
@..\command_files\LU_queries_$ID.trg
@..\command_files\sql_bound_vars.pdc;


DELETE FROM tblSys_Script;
--- insert here all tables names need to have a sequnce number with a trigger to fill it
execute spu_auditing.create_id_trigger('LU_queries','ID');
execute spu_auditing.create_translate_triggers();

Spool c:\t.SQL;
Select Line from tblSys_Script ORDER BY ID;
Spool OFF;
@@c:\t.SQL;
DELETE FROM tblSys_Script;


