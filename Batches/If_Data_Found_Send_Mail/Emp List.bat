@echo off

set _CP=..\..\WebContent\WEB-INF\classes;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\BC4J;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\bc4jhtml.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\bc4juixtags.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\bc4j_jclient_common.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\Bluetooth;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\cayenne.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CheckFree;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CHeckFree_LogReader_old;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\clrt.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\common;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\commons-collections-3.1.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\commons-configuration-1.2.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\commons-lang-2.1.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\commons-logging.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\Concurrent.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalContentModels.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalDatabaseConnectors.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalExporters.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalExportingBase.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalFormulas.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalQueryEngine.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalReportEngine.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\CrystalReportingCommon.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\datatags.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\dsn.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\fileaccess.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\FTP;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\icu4j.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\imap.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\inetfactory.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\J2EE;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jas.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\JDBC_for_SQL_Server;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jrcadapter.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jrcerom.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\JSP_RunTime;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jtds-1.2.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jug.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\junit.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\jxl;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\keycodeDecoder.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\log4j.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\mailapi.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\MetafileRenderer.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\side-sas.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\nls_charset12.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\oc4j.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\ojc.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\ojdbc14.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\ojsp.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\ojsputil.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\Oracle_JDBC;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\Oracle_XML;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\pop3.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\rasapp.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\rascore.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\ReportPrinter.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\rpoifs.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\serialization.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\sftp.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\smtp.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\sqljdbc.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\sqljdbc1.2.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\sqltaglib.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\struts.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\Telnet;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\utiltaglib.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\WMParsers;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\xercesImpl.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\xml-apis.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\xmlcomp.jar;
set _CP=%_CP%..\..\WebContent\WEB-INF\lib\xmlparserv2.jar

set _OPT=-Djbo.maxpoolsize=2 -Djbo.poolrequesttimeout=10000 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
set _APP= Support.SqlReader ifDataFoundSendQueryResultByMail 16490 0 "MyLabtop" scott tiger 

@echo on

java.exe -classpath %_CP% %_OPT% %_APP% >>Absence.log

@echo off

