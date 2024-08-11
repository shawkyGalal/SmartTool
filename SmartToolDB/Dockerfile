# Use the base image
FROM shawkyfoda/smarttooldb-image:01 

RUN cp "./setup-scripts" %contScriptPath%
RUN sqlplus system/%dbPassword%@//localhost:1521/FREEPDB1 @%contScriptPath%/setup-scripts/createAllUsers.sql 

	

