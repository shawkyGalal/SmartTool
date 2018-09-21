create or replace trigger LU_executables_bIUTrns
  before Insert OR Update OF
  E_DSC,A_DSC ON LU_executables
  for each row
-- Save Other Language Columns
begin
 if :new.E_DSC is null then
  :New.E_DSC:=:New.A_DSC;
 End if;
 if :new.A_DSC is null then
  :New.A_DSC:=:New.E_DSC;
 End if;
 
  if :New.msg is null then 
  :New.msg := :New.e_dsc;
  end if;
----------------
end LU_QUERIES_bIUTrns;
/
