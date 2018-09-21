SET echo OFF;
Set Define off
set feedback OFF
SET HEADING OFF
set line 800;

Prompt -- Created By Shawky Foda
Prompt -- Date 23-02-2003-----
Prompt -- Purpose: To Create all the claim module database objects

prompt ***************************
prompt * creating Calim TABLES; *
prompt ***************************

drop table tblClaim_settlement;
DROP TABLE tblsys_script;
DROP TABLE tblclaim_details;
DROP TABLE tblclaims;
DROP TABLE tblclaim_status_allow_trans;
DROP TABLE tblclaim_status;


@..\command_files\tblclaim_status.pdc
@..\command_files\tblclaim_status_allow_trans.pdc
@..\command_files\tblclaims.pdc;
@..\command_files\tblclaim_details.pdc;
@..\command_files\tblsys_script.pdc;
@..\command_files\tblClaim_settlement.pdc;

prompt ***************************
prompt * Inserting basic DATA *
prompt ***************************
@..\basic_data\CLAIM_STATUS_DATA.SQL;
@..\basic_data\CLAIM_STATUS_ALLOW_TRAN_DATA.SQL


prompt ***************************
prompt * Inserting test DATA *
prompt ***************************
@..\test_data\CLAIMs_DATA.SQL;
@..\test_data\CLAIM_SETTLEMENT_DATA.SQL;



prompt ***************************
prompt * creating Calim triggers *
prompt ***************************
@..\programe\claim_status_change_validate.trg

--- Creating Claim Package----
prompt ***************************
prompt * creating SPU_MISC PACKAGE; *
prompt ***************************
@..\programe\spu_Misc.spc;
@..\programe\spu_Misc.bdy;

prompt ***************************
prompt * creating SPU_Calim PACKAGE; *
prompt ***************************
@..\programe\spu_claims.spc;
@..\programe\spu_claims.bdy;


prompt ***************************
prompt * creating Audinting PACKAGE; *
prompt ***************************
@..\programe\spu_Auditing.spc;
@..\programe\spu_Auditing.bdy;
DROP SEQUENCE AUD$_Seq;
CREATE SEQUENCE AUD$_Seq;

prompt ***************************
prompt * creating Audinting TABLE & TRIGGER FOR TABLES *
prompt ***************************

DELETE FROM tblSys_Script;
EXECUTE spu_auditing.create_audit_table('tblclaims');
EXECUTE spu_Auditing.Create_Audit_Trigger('tblclaims');

--EXECUTE spu_auditing.create_audit_table('tblclaim_details');
--EXECUTE spu_Auditing.Create_Audit_Trigger('tblclaim_details');

EXECUTE spu_auditing.create_audit_table('tblClaim_settlement');
EXECUTE spu_Auditing.Create_Audit_Trigger('tblClaim_settlement');



Spool c:\t.SQL;
Select Line from tblSys_Script ORDER BY ID;
Spool OFF;
@@c:\t.SQL;



