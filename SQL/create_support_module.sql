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
@command_files\tblsys_script.pdc;


prompt ***************************
prompt * creating ...... PACKAGES; *
prompt ***************************

prompt ***************************
prompt * 1- creating Auditing PACKAGE; *
prompt ***************************
@programe\spu_Auditing.spc;
@programe\spu_Auditing.bdy;
DROP SEQUENCE AUD$_Seq;
CREATE SEQUENCE AUD$_Seq;


prompt ***************************
prompt * 3- creating Misc PACKAGE; *
prompt ***************************
@programe\Misc.pck;


prompt ***************************
prompt * creating Complaints TABLES; *
prompt ***************************

DROP TABLE sql_bound_vars; 
DROP TABLE lu_queries; 
DROP TABLE LU_executables; 
DROP TABLE LU_ROOT;
drop table sys_params;

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
@LU_Root.sql;
@LU_queries.sql;

@LU_queries_$ID.sql;

@LU_executables.sql;
@LU_executables_$ID.trg;

@sys_params.sql;


@sql_bound_vars.sql;


DELETE FROM tblSys_Script;
--- insert here all tables names need to have a sequnce number with a trigger to fill it
set feedback OFF;
execute spu_auditing.create_id_trigger('LU_queries','ID');
execute spu_auditing.create_id_trigger('LU_executables','ID');
execute spu_auditing.create_id_trigger('sys_params','ID');
execute spu_auditing.create_translate_trigger('LU_queries');
execute spu_auditing.create_translate_trigger('LU_executables');

Spool c:\t.SQL;
Select Line from tblSys_Script ORDER BY ID;
Spool OFF;
@@c:\t.SQL;
@alter_LU_Queries_Seq.sql;
DELETE FROM tblSys_Script;
-- @LU_queries_data.sql;
-- @LU_executables_data.sql;
-- @sys_params_data.sql
prompt ***********************************************************************************************
prompt * You Should Use plsql developer import tool to import file D:\MyWork\Support\SQL\all_data.pde*
prompt ***********************************************************************************************

commit;
