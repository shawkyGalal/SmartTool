CREATE OR REPLACE PROCEDURE Copy_hirarchy (table_Name varchar2,  from_id number, to_id number)IS

cursor c1 is
select * from lu_queries
where   PARENT_ID=from_id ;

r1  c1%rowtype ;
v_LU_QUERIES_SEQ   number ;

v_count  number :=0;

BEGIN
------------For LU_Queries Table---------------
if upper(table_Name) = 'LU_QUERIES' then 
  open c1;
  loop
  	fetch c1 into r1 ;
  	exit when c1%notfound ;
  	
  	select LU_QUERIES_SEQ.nextval into v_LU_QUERIES_SEQ from dual ;
  	
  	insert into compl.lu_queries(id,parent_id,code,e_dsc,a_dsc,query_body)
  	values (v_LU_QUERIES_SEQ,to_id,r1.code||v_LU_QUERIES_SEQ,r1.e_dsc,r1.a_dsc,r1.query_body)  ;
  	
  	select count(*) into v_count
  	from   lu_queries
  	where  parent_id = r1.id and parent_id <> 0;
  	
  	if v_count <> 0 then
  		 Copy_hirarchy (table_Name , r1.id, v_LU_QUERIES_SEQ) ;
  	end  if;
  end loop;
  close c1;
else raise_application_error(-21000,'Shawky : PL/SQL Copy Procedure Does not implemented for Db Table table_Name');  
end if;  
  commit;
END;
/
