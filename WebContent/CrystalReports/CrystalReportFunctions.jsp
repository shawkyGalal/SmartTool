<%@ page contentType="text/html;charset=windows-1252" 
errorPage = "../errorPage.jsp"
import = "com.sideinternational.clrt.*
,java.util.*
,com.sideinternational.sas.service.impl.scdb.entity.*
,com.sideinternational.sas.service.impl.scdb.* "%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title> Hello World </title>
</head>
<body>
<h2>Testing Side Command Line Reporting Tool</h2>

<%


String repName = request.getParameter("repName");
String outFileName = repName.substring( 0, repName.indexOf("."))+"_result";

InputArgs ia = new InputArgs();
ia.setListAllReports(false);
ia.setDestination("FILE");
ia.setReportId("AllEventsSimple.rpt");
ia.setOutPath(null);
ia.setOutputFileName(outFileName+"%F%T");
ia.setOutputFormat("RTF");
ia.setSQLServer("localhost");
ia.setSQLUserName("spp");
ia.setSQLPassword("tutorial");
ia.setSchemaName("scdb");

ia.setReportId("eventsWithParam.rpt");
System.setProperty("user.dir", "C:\\Sources\\Reporting\\SideReportingCMD");
ClrtServices clrtService = ClrtServiceFactory.createClrtService();
clrtService.initialize(ia);

if (request.getParameter("ExportToFile") != null) 
{
	int repId = Integer.parseInt( request.getParameter("repId"));	
	//ia.setReportId("#"+repId);
	ia.setReportId(repName);
	ia.setDestination("FILE");
	ia.setOutputFileName(outFileName );
	clrtService.initialize(ia);
	clrtService.exportToFile();
	out.print("<h3>File "+outFileName +" Successfuly Generated</h3>");
}

else if (request.getParameter("ExportToMail") != null) 
{
	int repId = Integer.parseInt( request.getParameter("repId"));	
	ia.setReportId(repName);
	ia.setDestination("EMAIL");
	ia.setEmailSubject("Testing CLRT From Smart Tool");
	ia.setEmailBody("xxx");
	String emailTo = "sfoda@eastnets.com" ; 
	ia.setEmailTo(emailTo );
	clrtService.initialize(ia);
	clrtService.exportToMail();
	out.print("<h3>Email Sent Suuccessfully to "+emailTo+ " </h3>");
}

else if (request.getParameter("ExportToPrinter") != null) 
{
	ia.setDestination("PRINTER");
	clrtService.initialize(ia);
	clrtService.exportToPrinter();
}


else if (request.getParameter("ListParams") != null) 
{
	out.print("<h3>Listing report Parameters</h3>");
	int repId = Integer.parseInt( request.getParameter("repId"));
	List params = clrtService.getParams(repId , "xx");
	Iterator it = params.iterator();
	while (it.hasNext())
	{
		ReportParameter rp = (ReportParameter)it.next();
		out.print("<br>"+rp.toString());
	}
	
}

else if (request.getParameter("List") != null)
{	
	List reports = clrtService.getReports();
	Iterator it = reports.iterator();
	while (it.hasNext())
	{
		Report r = (Report)it.next();
		out.print("<br>"+r.toString());
	}
}

%>



</body>
</html>
