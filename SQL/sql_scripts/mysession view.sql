
create or replace view mysessions as 
-- This View is created only to enable my support system to access the  v$session  
-- Where my support system does not allow $ in any query...
SELECT  *   FROM v$session t   