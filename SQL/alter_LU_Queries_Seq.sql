-- Modify the last number 
alter sequence LU_QUERIES_SEQ increment by 279 nocache;
select LU_QUERIES_SEQ.nextval from dual;
alter sequence LU_QUERIES_SEQ increment by 1 nocache;
declare
  LastValue integer;
begin
  loop
    select LU_QUERIES_SEQ.currval into LastValue from dual;
    exit when LastValue >= 300 - 1;
    select LU_QUERIES_SEQ.nextval into LastValue from dual;
  end loop;
end;
/
alter sequence LU_QUERIES_SEQ increment by 1 cache 20;
