create or replace trigger "CLAIM_STATUS_CHANGE_RECORD" 
       AFTER UPDATE OF STATUS ON SHAWKY.TBLCLAIMS 
       REFERENCING NEW AS NEW OLD AS OLD 
       FOR EACH ROW  
declare
  invalid_change EXCEPTION;
BEGIN
--     IF :new.status <>:old.status THEN
      INSERT INTO tblclaim_status_changes(claim_id, change_from , change_to, change_comment)
                                  VALUES(1 ,2 , 3, 'test');
                                 --VALUES(:old.claim_id , :old.status , :new.status, :new.last_status_change_comment);
--     END IF;
NULL;
end record_status_change;
/
