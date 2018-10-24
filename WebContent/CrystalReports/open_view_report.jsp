<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.crystaldecisions.reports.sdk.*"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.*"%>
<%@page import="com.crystaldecisions.report.web.viewer.*"%>
<%@page
	import="com.sideinternational.sas.service.impl.CrystalReportService"%>
<%@page import="com.sideinternational.sas.service.report.*"%>
<%@page import="com.sideinternational.clrt.XmlRepParamDoc"%>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.sideinternational.sas.util.xml.XmlNode"%>
<%@page import="com.sideinternational.sas.event.logging.Logger"%>
<%@page import="com.sideinternational.sas.event.EventDefinitionResource"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page
	import="com.sideinternational.sas.service.impl.scdb.entity.Report"%>
<%@page
	import="com.sideinternational.clrt.config.impl.ClrtConfigXmlImpl"%>
<%@page import="com.sideinternational.clrt.impl.ClrtServicesXmlImpl"%>
<%@page
	import="com.sideinternational.sas.event.resource.debug.EventDbug9998"%>
<%@ page errorPage="../errorPage.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%------------Beginning of 'Open and View Report' snippet----------------%>
<%
	//Use the relative path to the report; the physical or full qualified URL cannot be used.
	String reportName ="CrystalReports"+File.separator+request.getParameter("repName") ; //"Sample Reports/eventsWithParam.rpt"; //  request.getParameter("reportName");//
	String repFullPath =  session.getServletContext().getRealPath(reportName); //request.getRealPath("");
	
	
	
	//Check if the Report Source Session Variable already exists
	Object reportSource = session.getAttribute(reportName);
	
	//if the report source has not been opened
	//if (reportSource == null)
	{	
		
	    //---------- Set the path to the location of the report source -------------
	
	    //Modify Report Connection and Partameters
	    EventDbug9998 x = new EventDbug9998("Testing Crystal Reports");
	    XmlRepParamDoc xx= new XmlRepParamDoc(request.getRealPath("CrystalReports"+File.separator+"XmlParameters")+File.separator+"Report-Params_v2.xml");
	    List parameters  = xx.getParameters(reportName, "Param Set 1");
	    
	    
	    DataSource ds = new DataSource();
	    String DB_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";//"net.sourceforge.jtds.jdbc.Driver" ; //con.getMetaData().getDriverName();
	    String url ="jdbc:oracle:thin:@//localhost:1521/orcl";// "jdbc:jtds:sqlserver://localhost:1433;databasename=scdb";//con.getMetaData().getURL();
	    String userName = "scott"; //con.getMetaData().getUserName();
	    String password = "tiger";
	    
	    ds.putValue(DataSource.DB_CONNECTION_URL,url );
		ds.putValue(DataSource.USER_NAME, userName);
		ds.putValue(DataSource.USER_PASSWORD,password );
		ds.putValue(DataSource.DB_DRIVER_CLASS, DB_DRIVER_CLASS);
		
		CrystalReportService crs = new CrystalReportService();
	   	//---------- Create a ReportClientDocument -------------
	    ReportClientDocument reportClientDocument = crs.getReportClientDocument(reportName , parameters , ds);
	
	    //Get report source
	    reportSource = reportClientDocument.getReportSource();
	
	    //Cache report source session variable
	    //This will be used by the viewer to display the desired report.
	    session.setAttribute(reportName, reportSource);
	}    
	    
	//---------- Create the viewer and render the report -------------
		
	//Create the CrystalReportViewer object
	com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
	
	//Set the reportsource property of the viewer
	crystalReportViewer.setReportSource(reportSource);
	
	//Set viewer attributes
	crystalReportViewer.setOwnPage(true);
	crystalReportViewer.setOwnForm(true);
	  
	//Set the CrystalReportViewer print mode
	crystalReportViewer.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
	
	//Process the report
	crystalReportViewer.processHttpRequest(request, response, getServletConfig().getServletContext(), null); 
%><%------------End of 'Open and View Report' snippet----------------%>

</body>
</html>