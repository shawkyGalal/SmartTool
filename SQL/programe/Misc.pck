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
 
FUNCTION GET_NO_WeekDays (
-- to get how many dd (fridays ) between two dates 
SDATE DATE,
EDATE DATE,
dd varchar) 
RETURN NUMBER;


end Misc;
/
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
FUNCTION GET_NO_WeekDays (SDATE DATE,EDATE DATE, dd varchar) RETURN NUMBER IS
  VCNT NUMBER := 0;
	SDA DATE := SDATE ;
BEGIN
 LOOP
   IF Upper(TO_CHAR(SDA,'DY')) = Upper(dd) THEN
      VCNT := VCNT + 1;
   END IF;
   EXIT WHEN SDA = EDATE;
    SDA := SDA+1;
 END LOOP;

RETURN(VCNT);
END;


begin
  -- Initialization
  NULL;
end Misc;
--==============================
/
