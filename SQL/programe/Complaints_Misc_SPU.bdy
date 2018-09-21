create or replace package body Complaints_Misc_SPU is

  -- Private type declarations
  --type <TypeName> is <Datatype>;

  -- Private constant declarations
  --<ConstantName> constant <Datatype> := <Value>;

  -- Private variable declarations
 --  <VariableName> <Datatype>;

  -- Function and procedure implementations
  FUNCTION AUTH_DEL(AFROM VARCHAR2,ADATE DATE) return varchar2 is
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
-------------
function get_Compl_level(comp_id number ) return number is
result number;
begin

select nvl(sum(abc),0) into result from
(
    ( select count(t1.response_type) abc from complaint_responses_tbl t1
      where t1.response_date >
            (
            select max(t.response_date)
            from complaint_responses_tbl t
            where (
                    (t.response_type='Reprocess'
                     Or upper(t.response_type)='REACTIVATE'
                     or upper(t.response_type)= 'CREATED')
                     and t.complaint_id = comp_id 
                     and t.deleted = 'N'                 )
            )
            and UPPER(t1.response_type) = 'ESCALATE'
            and t1.complaint_id = comp_id
            and t1.deleted = 'N'
    )
    union
    ( select count(t2.response_type)* -1 abc from complaint_responses_tbl t2
      where t2.response_date >
            (
            select max(t.response_date)
            from complaint_responses_tbl t
            where (
                   (t.response_type='Reprocess'
                     Or upper(t.response_type)='REACTIVATE'
                     or upper(t.response_type)= 'CREATED')
                     and t.complaint_id = comp_id
                     and t.deleted = 'N'
                   )
            )
            and UPPER(t2.response_type) = 'DESCALATE'
            and t2.complaint_id = comp_id
            and t2.deleted = 'N'
    )
);

if result <0 then result := 0; end if;
return result ;
end;
---------------------------
end Complaints_Misc_SPU;
/
