@echo off
set _app_path=C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\SmartTool

set _CP=%_app_path%\WEB-INF\classes;
set _CP=%_CP%%_app_path%\WEB-INF\lib\implex-core.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\side-sas.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\side-swaf.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\servlet-api.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\BC4J;
set _CP=%_CP%%_app_path%\WEB-INF\lib\bc4jhtml.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\bc4juixtags.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\bc4j_jclient_common.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\cayenne.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\clrt.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\common;
set _CP=%_CP%%_app_path%\WEB-INF\lib\commons-collections-3.1.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\commons-configuration-1.2.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\commons-lang-2.1.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\commons-logging.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\Concurrent.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalContentModels.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalDatabaseConnectors.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalExporters.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalExportingBase.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalFormulas.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalQueryEngine.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalReportEngine.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\CrystalReportingCommon.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\datatags.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\dsn.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\fileaccess.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\FTP;
set _CP=%_CP%%_app_path%\WEB-INF\lib\icu4j.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\imap.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\inetfactory.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\J2EE;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jas.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jrcadapter.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jrcerom.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\JSP_RunTime;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jtds-1.2.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jug.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jxl;
set _CP=%_CP%%_app_path%\WEB-INF\lib\keycodeDecoder.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\log4j.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\mailapi.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\MetafileRenderer.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\nls_charset12.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\oc4j.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\ojc.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\ojdbc6.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\ojsp.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\ojsputil.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\Oracle_JDBC;
set _CP=%_CP%%_app_path%\WEB-INF\lib\Oracle_XML;
set _CP=%_CP%%_app_path%\WEB-INF\lib\pop3.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\rasapp.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\rascore.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\ReportPrinter.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\rpoifs.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\serialization.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\sftp.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\smtp.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\sqljdbc.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\sqljdbc1.2.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\sqltaglib.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\struts.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\Telnet;
set _CP=%_CP%%_app_path%\WEB-INF\lib\utiltaglib.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\WMParsers;
set _CP=%_CP%%_app_path%\WEB-INF\lib\xercesImpl.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\xml-apis.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\xmlcomp.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\xmlparserv2.jar;
set _CP=%_CP%%_app_path%\WEB-INF\lib\jsf-api-1.2_12.jar

set _OPT=-Djbo.maxpoolsize=2 -Djbo.poolrequesttimeout=10000 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dfile.encoding=Windows-1256

set _APP= Support.SqlReader ifDataFoundSendQueryResultByMail 37078 0 "PNU_PROD" JCCS redsea11 

@echo on

java.exe -classpath "%_CP%" %_OPT% %_APP% >> 37078_0.log

@echo off
