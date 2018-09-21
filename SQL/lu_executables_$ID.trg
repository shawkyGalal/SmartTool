create or replace trigger lu_executables_$ID
 Before Insert ON lu_executables
  for each row
  Declare
  lSeq Number;
begin
 if :New.ID is null then
  select lu_executables_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
  if :New.msg is null then 
  :New.msg := :New.e_dsc;
  end if;
end lu_executables_$ID;
/
