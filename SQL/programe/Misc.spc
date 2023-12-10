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

FUNCTION GET_NO_OffDays (SDATE timestamp,EDATE timestamp, weekEndDay varchar , include_Vacation boolean ) RETURN NUMBER;

FUNCTION GET_New_End_Date (SDATE timestamp,EDATE timestamp, WeekEnd1 varchar , WeekEnd2 varchar) RETURN date;

end Misc;
/
