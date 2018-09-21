create or replace package Complaints_Misc_SPU is

  -- Author  : FODA_SH
  -- Created : 24-May-03 2:03:40 PM
  -- Purpose : General Purpose package for the complaints system

 FUNCTION AUTH_DEL(AFROM VARCHAR2,ADATE DATE) return VARCHAR2;
 function get_Compl_level(comp_id number ) return number; 
end Complaints_Misc_SPU;
/
