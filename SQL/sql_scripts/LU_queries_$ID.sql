create or replace trigger LU_queries_$ID
 Before Insert ON LU_queries
  for each row
  
Declare
  lSeq Number;
begin
 if :New.ID is null then
  select LU_queries_Seq.NextVal into lSeq From Dual;
  :New.ID :=lSeq;
  end if;
end LU_queries_$ID;
/
