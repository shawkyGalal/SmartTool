call D:\SmartValue\Sources\SMARTVALUE_CONFIG_HOME\smartTool\Batches\If_Data_Found_Send_Mail\Mailesender_init.bat

set _APP= Support.SqlReader ifDataFoundSendQueryResultByMail 22744 -1 "PNU_PROD" JCCS abc123 

@echo on

java.exe -classpath "%_CP%" %_OPT% %_APP% >> 22744_0.log

@echo off
