Rem : ===================1- Installig Oracle Express Edition==========================
D:\SW_Lib\OracleXE112_Win32\DISK1\OracleXE.exe /s /f1"D:\SW_Lib\OracleXE112_Win32\DISK1\response\OracleXE-Install.iss" /f2"D:\SW_Lib\OracleXE112_Win32\DISK1\setup.log"


Rem : ===================2- Creating TableSapces and usres ============================

md D:\oraclexe\app\oracle\oradata\XE\SUPPORT
md D:\oraclexe\app\oracle\oradata\XE\ICDB
md D:\oraclexe\app\oracle\oradata\XE\JCCS
md D:\oraclexe\app\oracle\oradata\XE\MOEP


sqlplus -s "sys/redsea11@xe as sysdba" @createUsers.sql 

Rem : ===================3- Import data ================================================

imp userid=system/redsea11@xe rows=yes Full=Y file=SmartTool.dmp 

Rem

Rem : ====================4- Install Apatch Tomcat Server ===============================


Rem : ====================5- Deply SmartTool War file To Tomcat Server ==================






