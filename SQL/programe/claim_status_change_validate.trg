create or replace trigger claim_status_change_validate
  BEFORE update on  tblclaims
  for each row
declare
  invalid_status_change EXCEPTION;
BEGIN
     IF :new.status <>:old.status THEN
       SPU_CLAIMS.CHECK_CLAIM_TRANSATION(:old.status, :new.status);
     END IF;

end record_status_change;
/
