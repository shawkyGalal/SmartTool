CREATE OR REPLACE VIEW v_user_Queries AS
select * from lu_queries t 
start with  t.code = user
connect by prior t.id = t.parent_id
union 
select * from lu_queries t 
start with  upper(t.code) = upper('Standard')
connect by prior t.id = t.parent_id
union 
select * from lu_queries t 
where t.id=0


