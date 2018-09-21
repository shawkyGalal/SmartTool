create or replace procedure takaful.Get_All_oracle_Error_Messages  is

   err_msg VARCHAR2(100);
BEGIN
   /* Get all Oracle error messages. */
   FOR err_num IN 1..9999 LOOP
      err_msg := SQLERRM(-1 * err_num); 
      insert into oracle_errors (error_code,error_message) values (-1 * err_num,err_msg);
      raise_application_error(-20020,'sfSDcv');
   END LOOP;
   commit;
END Get_All_oracle_Error_Messages;
/
