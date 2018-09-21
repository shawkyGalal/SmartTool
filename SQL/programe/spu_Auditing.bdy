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
