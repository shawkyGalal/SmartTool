-----Droping Users --------------
DROP USER PNU CASCADE;
DROP USER CAROLD CASCADE;
DROP USER CARRENT CASCADE;
DROP USER ICDB CASCADE;
DROP USER SUPPORT CASCADE;
DROP USER JCCS CASCADE;
DROP USER MOEP CASCADE;
DROP USER GIHAZ CASCADE;
DROP USER ATT CASCADE;

-----Droping Table Spaces --------------
DROP TABLESPACE PNU_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE PNU_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE CAROLD_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE CAROLD_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE CARRENT_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE CARRENT_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE ICDB_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE ICDB_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE SUPPORT_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE SUPPORT_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE JCCS_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE JCCS_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE MOEP_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE MOEP_TMP INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE GIHAZ_DATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE GIHAZ_TMP INCLUDING CONTENTS AND DATAFILES;

-- Creating TableSpaces 
CREATE TABLESPACE PNU_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\PNU\PNU_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT 
AUTO FLASHBACK ON;

CREATE TEMPORARY TABLESPACE PNU_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\PNU\PNU_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED 
  TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE CAROLD_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\CAROLD\CAROLD_DATA' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT 
AUTO FLASHBACK ON;
CREATE TEMPORARY TABLESPACE CAROLD_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\CAROLD\CAROLD_TMP' SIZE 128M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE CARRENT_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\CARRENT\CARRENT_DATA' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE CARRENT_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\CARRENT\CARRENT_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE ICDB_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\ICDB\ICDB_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE ICDB_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\ICDB\ICDB_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE SUPPORT_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\SUPPORT\SUPPORT_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE SUPPORT_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\SUPPORT\SUPPORT_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE JCCS_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\JCCS\JCCS_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE JCCS_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\JCCS\JCCS_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE MOEP_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\MOEP\MOEP_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE MOEP_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\MOEP\MOEP_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

CREATE TABLESPACE GIHAZ_DATA DATAFILE 
  'D:\app\oracle\oradata\svdb\GIHAZ\GIHAZ_DATA' SIZE 512M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING ONLINE PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE BLOCKSIZE 8K SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
CREATE TEMPORARY TABLESPACE GIHAZ_TMP TEMPFILE 
  'D:\app\oracle\oradata\svdb\GIHAZ\GIHAZ_TMP' SIZE 256M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;

-- Dropping Roles 
 drop role pmp_role ; 
 Drop role PNU_SUBJECT_OWNER ; 
 drop role CARRENT_USER_ROLE ; 
 drop role NGO_ADMIN ; 

 -- Creating Roles    
 Create role PMP_ROLE ;
 create role PNU_SUBJECT_OWNER ;
 Create role CARRENT_USER_ROLE;
 Create role NGO_ADMIN ; 
 
 -- EXECUTE THE IMPORT COMMAND  TO CREATE USERS FROM THE IMPORT FILE 
 ===== UNDER dEVELOPMENT ===
 -- POST IMPORT COMMAND -- 
 	grant select on user$ to ICDB ; 
	grant execute on sys.DBMS_CRYPTO to ICDB ; 
/*
--********** Creating Users *************
CREATE USER PNU
  IDENTIFIED BY 123
  DEFAULT TABLESPACE PNU_DATA
  TEMPORARY TABLESPACE PNU_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
 -- Grant/Revoke role privileges 
grant connect to PNU with admin option;
grant dba to PNU with admin option;
grant resource to PNU with admin option;
-- Grant/Revoke system privileges 
grant alter tablespace to PNU with admin option;
grant create materialized view to PNU;
grant create session to PNU;
grant create user to PNU;
grant create view to PNU;
grant drop user to PNU;
grant unlimited tablespace to PNU with admin option;


CREATE USER CAROLD
  IDENTIFIED BY 123
  DEFAULT TABLESPACE CAROLD_DATA
  TEMPORARY TABLESPACE CAROLD_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
 -- Grant/Revoke role privileges 
grant connect to CAROLD with admin option;
grant dba to CAROLD with admin option;
grant resource to CAROLD with admin option;
-- Grant/Revoke system privileges 
grant alter tablespace to CAROLD with admin option;
grant create materialized view to CAROLD;
grant create session to CAROLD;
grant create user to CAROLD;
grant create view to CAROLD;
grant drop user to CAROLD;
grant unlimited tablespace to CAROLD with admin option;


CREATE USER CARRENT
  IDENTIFIED BY 123
  DEFAULT TABLESPACE CARRENT_DATA
  TEMPORARY TABLESPACE CARRENT_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
 -- Grant/Revoke role privileges 
grant connect to CARRENT with admin option;
grant dba to CARRENT with admin option;
grant resource to CARRENT with admin option;
-- Grant/Revoke system privileges 
grant alter tablespace to CARRENT with admin option;
grant create materialized view to CARRENT;
grant create session to CARRENT;
grant create user to CARRENT;
grant create view to CARRENT;
grant drop user to CARRENT;
grant select any table to CARRENT;
grant unlimited tablespace to CARRENT with admin option;

CREATE USER ICDB
  IDENTIFIED BY 123
  DEFAULT TABLESPACE ICDB_DATA
  TEMPORARY TABLESPACE ICDB_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO ICDB WITH ADMIN OPTION;
  GRANT RESOURCE TO ICDB WITH ADMIN OPTION;
  GRANT DBA TO ICDB WITH ADMIN OPTION;
  ALTER USER ICDB DEFAULT ROLE ALL;
  -- 2 System Privileges  
  GRANT ALTER TABLESPACE TO ICDB WITH ADMIN OPTION;
  GRANT UNLIMITED TABLESPACE TO ICDB WITH ADMIN OPTION;

CREATE USER SUPPORT
  IDENTIFIED BY 123
  DEFAULT TABLESPACE SUPPORT_DATA
  TEMPORARY TABLESPACE SUPPORT_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO SUPPORT WITH ADMIN OPTION;
  GRANT RESOURCE TO SUPPORT WITH ADMIN OPTION;
  GRANT DBA TO SUPPORT WITH ADMIN OPTION;
  ALTER USER SUPPORT DEFAULT ROLE ALL;
  -- 2 System Privileges  
  GRANT ALTER TABLESPACE TO SUPPORT WITH ADMIN OPTION;
  GRANT UNLIMITED TABLESPACE TO SUPPORT WITH ADMIN OPTION;

CREATE USER JCCS
  IDENTIFIED BY 123
  DEFAULT TABLESPACE JCCS_DATA
  TEMPORARY TABLESPACE JCCS_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO JCCS WITH ADMIN OPTION;
  GRANT RESOURCE TO JCCS WITH ADMIN OPTION;
  GRANT DBA TO JCCS WITH ADMIN OPTION;
  ALTER USER JCCS DEFAULT ROLE ALL;
  -- 2 System Privileges  
  GRANT ALTER TABLESPACE TO JCCS WITH ADMIN OPTION;
  GRANT UNLIMITED TABLESPACE TO JCCS WITH ADMIN OPTION;
  
 -------------Creating MOEP User ----------------
CREATE USER MOEP
  IDENTIFIED BY 123
  DEFAULT TABLESPACE MOEP_DATA
  TEMPORARY TABLESPACE MOEP_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO MOEP WITH ADMIN OPTION;
  GRANT RESOURCE TO MOEP WITH ADMIN OPTION;
  GRANT DBA TO MOEP WITH ADMIN OPTION;
  ALTER USER MOEP DEFAULT ROLE ALL;
  -- 2 System Privileges  
  GRANT ALTER TABLESPACE TO MOEP WITH ADMIN OPTION;
  GRANT UNLIMITED TABLESPACE TO MOEP WITH ADMIN OPTION;
  
 -------------Creating GIHAZ User ----------------

CREATE USER GIHAZ
  IDENTIFIED BY 123
  DEFAULT TABLESPACE GIHAZ_DATA
  TEMPORARY TABLESPACE GIHAZ_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO GIHAZ WITH ADMIN OPTION;
  GRANT RESOURCE TO GIHAZ WITH ADMIN OPTION;
  GRANT DBA TO GIHAZ WITH ADMIN OPTION;
  ALTER USER GIHAZ DEFAULT ROLE ALL;
  -- 2 System Privileges  
  GRANT ALTER TABLESPACE TO GIHAZ WITH ADMIN OPTION;
  GRANT UNLIMITED TABLESPACE TO GIHAZ WITH ADMIN OPTION;
 -------------Creating ATT User ----------------

CREATE USER ATT
  IDENTIFIED BY 123
  DEFAULT TABLESPACE GIHAZ_DATA
  TEMPORARY TABLESPACE GIHAZ_TMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles  
  GRANT CONNECT TO ATT WITH ADMIN OPTION;
  GRANT RESOURCE TO ATT WITH ADMIN OPTION;
  GRANT DBA TO ATT WITH ADMIN OPTION;
  ALTER USER ATT DEFAULT ROLE ALL;
 */
 
   