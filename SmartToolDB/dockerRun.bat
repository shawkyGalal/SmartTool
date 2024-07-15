@echo off
set port=1521
set containerName=smarttooldb-container-latest
set oracle_volume_name=oracle-volume-new-02

set dbPassword=Redsea11
set contScriptPath=/opt/oracle/SmartToolDB/
set dumpFileName=SmartTool.dmp
set oracldbImageName=container-registry.oracle.com/database/free:latest  


REM Start the container in the background
REM docker run -d -p 1521:1521 -p 1522:1522 -p 8443:8443 -p 27017:27017  -e WORKLOAD_TYPE='ATP'  -e WALLET_PASSWORD=%dbPassword% -e SYS_PASSWORD=%dbPassword% -e ADMIN_PASSWORD=%dbPassword%  --cap-add SYS_ADMIN  --device /dev/fuse -v %oracle_volume_name%:/opt/oracle/oradata  --name %containerName%   %oracldbImageName%
docker run -d -p 1521:1521 -p 1522:1522 -p 8443:8443 -p 27017:27017  -e WORKLOAD_TYPE='ATP'  -e WALLET_PASSWORD=%dbPassword% -e SYS_PASSWORD=%dbPassword%  --cap-add SYS_ADMIN  --device /dev/fuse -v %oracle_volume_name%:/opt/oracle/oradata  --name %containerName%   shawkyfoda/smarttooldb-image:latest

docker run -d -p 1521:1521 -p 1522:1522 -p 8443:8443 -p 27017:27017  -e WORKLOAD_TYPE='ATP'  -e WALLET_PASSWORD=%dbPassword% -e SYS_PASSWORD=%dbPassword%  --cap-add SYS_ADMIN  --device /dev/fuse --name %containerName% shawkyfoda/smarttooldb-image:latest
  

		REM Wait for the container to be ready (you may need to adjust the sleep duration)
		:wait_loop
		docker exec %containerName% echo Container is ready 2>nul && goto :container_ready
		echo Waiting for the container to be ready...
		timeout /nobreak /t 1 >nul
		goto :wait_loop
		
		:container_ready
		echo Container is ready to accept commands.

docker exec -it %containerName% /opt/oracle/setPassword.sh %dbPassword%

REM =========== Clone or pull local repository ==============
cd G:\My Drive\SmartValue\Sources\app\
@ECHO OFF
REM Define the repository URL
SET repo_dir=SmartToolDB
SET "repo_url=https://github.com/shawkyGalal/%repo_dir%.git"
REM Check if the directory already exists
IF EXIST "%repo_dir%" (
  ECHO "Directory '%repo_dir%' found. Pulling latest changes..."
  cd "%repo_dir%"
  git pull 
) ELSE (
  ECHO "Directory '%repo_dir%' not found. Cloning repository..."
  git clone "%repo_url%"
)
ECHO Done!

:post_git_operations
docker cp "G:\My Drive\SmartValue\Sources\app\SmartToolDB" %containerName%:%contScriptPath%


REM --- Create DB TableSpaces & Users 
docker exec -it %containerName% sqlplus system/%dbPassword%@//localhost:%port%/FREEPDB1 @%contScriptPath%/setup-scripts/createAllUsers.sql 

REM -----Create Roles ---
docker exec -it %containerName% sqlplus system/%dbPassword%@//localhost:%port%/FREEPDB1 @%contScriptPath%/setup-scripts/createRoles.sql  

REM --- Execute Before Import Script 
docker exec -it %containerName% sqlplus sys/%dbPassword% as sysdba@//localhost:%port%/FREEPDB1 @%contScriptPath%/setup-scripts/beforeImport.sql
 
REM -- Import Database Dmp File 
docker exec -it %containerName% imp  file=%contScriptPath%/DB_Dump/%dumpFileName% userid="system/%dbPassword%@localhost:%port%/FREEPDB1" buffer=30720 commit=yes full=yes grants=yes ignore=yes indexes=yes rows=yes show=no constraints=yes

REM --- Execute After Import Script 
docker exec -it %containerName% sqlplus sys/%dbPassword%//localhost:%port%/FREEPDB1" @%contScriptPath%/setup-scripts/afterImport.sql



REM ------ EXPORTING DB & Download to Host ------------------------
docker exec -it %containerName% /opt/oracle/product/23c/dbhomeFree/bin/exp FILE=/tmp/SmartTool.dmp userid="system/%dbPassword%@FREEPDB1" OWNER=SUPPORT,ICDB,JCCS,MOEP,PNU,CARRENT,GIHAZ,MOEP,CARRENT statistics=none
docker cp %containerName%:/tmp/SmartTool.dmp "G:\My Drive\SmartValue\Sources\app\SmartToolDB\DB_Dump"



REM -- Drop Users ( if needed )  
drop user JC_DATA cascade ;
drop user SUPPORT cascade ; 
drop user ICDB cascade ; 
drop user PNU cascade ; 
drop user JCCS cascade ; 
drop user MOEP cascade ; 
drop user CARRENT cascade ; 
drop user GIHAZ cascade ; 
drop user ATT cascade ; 