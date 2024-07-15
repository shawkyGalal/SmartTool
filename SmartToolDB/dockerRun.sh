#!/bin/bash

imageName=SmartTool-db
dbPassword=Redsea11
contScriptPath=/opt/oracle/SmartToolDB/
dbName=SVDB
port=1521
oracle_volume_name=oracle-volume
dumpFileName=SmartTool20170610.dmp
oracldbImageName=ghcr.io/oracle/adb-free:23.10.2.4 
# shawkyfoda/smarttooldb-image 
# gvenzl/oracle-free


REM Start the container in the background
  docker run -d -p $port:$port -e ORACLE_PASSWORD=$dbPassword --name $imageName --env=ORACLE_DATABASE=$dbName -v $oracle_volume_name:/opt/oracle/oradata $oracldbImageName

REM Wait for the container to be ready (you may need to adjust the sleep duration)
:wait_loop
docker exec $imageName echo Container is ready 2>nul && goto :container_ready
echo Waiting for the container to be ready...
timeout /nobreak /t 1 >nul
goto :wait_loop

:container_ready
echo Container is ready to accept commands.

# docker exec -it %imageName% mkdir -p %contScriptPath% 
# docker cp DB_Dump %imageName%:%contScriptPath%
# docker cp setup-scripts %imageName%:%contScriptPath%

#--- Create DB TableSpaces & Users 
docker exec -it $imageName sqlplus system/$dbPassword@//localhost:$port/$dbName @$contScriptPath/setup-scripts/createAllUsers.sql $dbName

#-----Create Roles ---
docker exec -it $imageName sqlplus system/$dbPassword@//localhost:$port/$dbName @$contScriptPath/setup-scripts/createRoles.sql


#-- Import Database Dmp File 
docker exec -it $imageName imp log=$contScriptPath/plsimp.log file=$contScriptPath/DB_Dump/$dumpFileName userid="system/%dbPassword%@localhost:$port/$dbName" buffer=30720 commit=yes full=yes grants=yes ignore=yes indexes=yes rows=yes show=no constraints=yes


##-- Drop Users ( if needed )  
drop user JC_DATA cascade ;
drop user SUPPORT cascade ; 
drop user ICDB cascade ; 
drop user PNU cascade ; 
drop user JCCS cascade ; 
drop user MOEP cascade ; 
drop user CARRENT cascade ; 
drop user GIHAZ cascade ; 
drop user ATT cascade ; 