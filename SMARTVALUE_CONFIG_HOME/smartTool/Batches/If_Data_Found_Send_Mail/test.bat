
setClassPath.bat 

@echo on

set _APP= Support.SqlReader ifDataFoundSendQueryResultByMail 25675 0 "Muraishid" carrent abcd1234 

@echo on

java.exe -classpath %_CP% %_OPT% %_APP% >> 25675_0.log

@echo off
