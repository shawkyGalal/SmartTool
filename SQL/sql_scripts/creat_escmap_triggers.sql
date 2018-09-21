-------- creating a single sequence for two tables
DROP sequence lu_esc_maps_seq; 
create sequence lu_esc_maps_seq; 

create or replace trigger LU_LOB_ESC_MAPs_$ID
 Before Insert ON LU_LOB_ESC_MAPs
  for each row
  
Declare
  lSeq Number;
begin
 if :New.Id is null then
  select LU_ESC_MAPs_Seq.NextVal into lSeq From Dual;
  :New.Id :=lSeq;
  end if;
end LU_LOB_ESC_MAPs_$ID;
/

create or replace trigger LU_BP_ESC_MAPs_$ID
 Before Insert ON LU_BP_ESC_MAPs
  for each row
  
Declare
  lSeq Number;
begin
 if :New.Id is null then
  select LU_ESC_MAPs_Seq.NextVal into lSeq From Dual;
  :New.Id :=lSeq;
  end if;
end LU_BP_ESC_MAPs_$ID;
/
create or replace trigger LU_SEC_ESC_MAP_$ID
 Before Insert ON LU_SEC_ESC_MAP
  for each row
Declare
  lSeq Number;
begin
 if :New.Id is null then
  select LU_ESC_MAPs_Seq.NextVal into lSeq From Dual;
  :New.Id :=lSeq;
  end if;
end LU_BP_ESC_MAPs_$ID;
/







