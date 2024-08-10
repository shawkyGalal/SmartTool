<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfigFactory"%>
<%@ page language="java" contentType="application/json; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="java.io.InputStream"%>
<%@page import ="com.google.gson.Gson"%>
<%
	AppConfig ac = AppContext.getAppConfig(application);
	String partner = (String) request.getAttribute("partner");
	String customer = (String) request.getAttribute("customer");
	String infra = (String) request.getAttribute("infra");
	String org = (String) request.getAttribute("org");
	String env = (String) request.getAttribute("env");
	
	Partner partnerObj =  ac.getPartnerByName(partner) ; 
	Customer customerObj = partnerObj.getCustomerByName(customer) ; 
	Infra infraObj = customerObj.getInfraByName(infra) ; //.getInfra("MasterWorks" , "MOJ" , infraName) ;
	Region region0= infraObj.getRegions().get(0);  
	ManagementServer ms = infraObj.getManagementServer(region0.getName())  ;
	if (org== null && env == null )
	{	
		String[] orgs = ms.getAllOrgNames() ;
		Gson gson = new Gson() ;
		out.print(gson.toJson(orgs).trim()) ;
	}
	
	if (org != null &&  env == null )
	{
		// -- Display Org Details 
	}
	
	if (org != null &&  env != null )
	{
		// -- Display environments list for the given org 
	}
%>
