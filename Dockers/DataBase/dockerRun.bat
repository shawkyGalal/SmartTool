@echo off

set imageName=smartValueDb
set dbPassword=Redsea11
set contScriptPath=/opt/oracle/SmartValue/SmartTool/Database
set dbName=SVDB
set port=1521
set oracle_volume_name=oracle-volume


REM Start the container in the background
docker run -d -p %port%:%port% -e ORACLE_PASSWORD=%dbPassword% --name %imageName% --env=ORACLE_DATABASE=%dbName% -v %oracle_volume_name%:/opt/oracle/oradata gvenzl/oracle-free 

REM Wait for the container to be ready (you may need to adjust the sleep duration)
:wait_loop
docker exec %imageName% echo Container is ready 2>nul && goto :container_ready
echo Waiting for the container to be ready...
timeout /nobreak /t 1 >nul
goto :wait_loop

:container_ready
echo Container is ready to accept commands.

docker exec -it %imageName% mkdir -p %contScriptPath% 
docker cp DB_Dump %imageName%:%contScriptPath%
docker cp setup-scripts %imageName%:%contScriptPath%

-- docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createAllUsers.sql %dbName%  

docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% SUPPORT
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% ICDB
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% PNU
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% JCCS
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% MOEP
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% CARRENT
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% GIHAZ
docker exec -it %imageName% sqlplus system/%dbPassword%@//localhost:%port%/%dbName% @%contScriptPath%/setup-scripts/createUser.sql %dbName% ATT

#-----Create Roles ---
CREATE ROLE "PMP_ROLE";
GRANT "CONNECT" TO "PMP_ROLE" ;
GRANT "DBA" TO "PMP_ROLE" ;

CREATE ROLE "CARRENT_USER_ROLE"
GRANT "CONNECT" TO "CARRENT_USER_ROLE" ;
GRANT "DBA" TO "CARRENT_USER_ROLE" ;

CREATE ROLE "PNU_SUBJECT_OWNER" ; 
GRANT "CONNECT" TO "PNU_SUBJECT_OWNER" ;
GRANT "DBA" TO "PNU_SUBJECT_OWNER" ;


#-- Import Database Dmp File 
#-- username/password@hostname:port/service_name
#-- docker exec -it %imageName% sqlplus sys/%dbPassword%@//localhost:%port%/%dbName% as sysdba 
docker exec -it %imageName% imp log=%contScriptPath%/plsimp.log file=%contScriptPath%/DB_Dump/PNU_Dump/SmartTool20170610.dmp userid="system/%dbPassword%@localhost:%port%/%dbName%" buffer=30720 commit=yes full=yes grants=yes ignore=yes indexes=yes rows=yes show=no constraints=yes



##-- Drop Users 
drop user JC_DATA cascade ;
drop user SUPPORT cascade ; 
drop user ICDB cascade ; 
drop user PNU cascade ; 
drop user JCCS cascade ; 
drop user MOEP cascade ; 
drop user CARRENT cascade ; 
drop user GIHAZ cascade ; 
drop user ATT cascade ; 