call D:\SmartValue\Sources\SMARTVALUE_CONFIG_HOME\smartTool\Batches\If_Data_Found_Send_Mail\Mailesender_init.bat
set _APP= Support.SqlReader ifDataFoundSendQueryResultByMail <QUERY_ID> -1 "<DB CONFIGURATION FILE CONNECTION NAME>" <USERNAME> <PASSWORD> 

@echo on

java.exe -classpath "%_CP%" %_OPT% %_APP% >> <OUT_LOG_FILE>

@echo off

