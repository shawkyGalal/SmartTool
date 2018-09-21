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
