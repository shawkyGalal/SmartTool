CREATE OR REPLACE FUNCTION COMP.AUTH_DEL(AFROM VARCHAR2,ADATE DATE) return varchar2 is
a1    varchar2(15) ;
a2    varchar2(15) ;
a3    varchar2(15) ;
begin
  begin
    SELECT auth_to into a1
    FROM WFLO_APPS.AUTH_DELEGATION
    WHERE AUTH_FROM = afrom AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
    IF a1 = afrom  THEN
       RETURN a1;
    ELSE
       BEGIN
         SELECT auth_to into a2
         FROM WFLO_APPS.AUTH_DELEGATION
         WHERE AUTH_FROM = a1  AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
         If a2 = a1 or a2= afrom  THEN
            RETURN  a2;
         ELSE
            BEGIN
              SELECT auth_to into a3
              FROM WFLO_APPS.AUTH_DELEGATION
              WHERE AUTH_FROM = a2  AND TRUNC(adate) BETWEEN TRUNC(DATE_FROM) AND TRUNC(DATE_TO) AND ROWNUM = 1;
              If a3 = a2 or a3= afrom  THEN
                 return a3;
              else
                 return AUTH_DEL(a2,adate);
              end if;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
               RETURN a2;
            END;
         END IF;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
         retuRn a1;
       END;
    END IF;
  exception
  when no_data_found then
  return afrom;
  end;
end;
/
