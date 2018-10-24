<%@page import="com.sideinternational.sas.license.entity.Application"%>
<%@page import="com.implex.database.map.SecUsrDta" %>
<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10, com.sideinternational.sas.license.entity.*" %>
<html>
	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Certificate Expiry </title>
	<body>
	<% 
		SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session) ; 
    	com.sideinternational.sas.license.entity.Certificate cert = com.sideinternational.web.swaf.SWAF.getCertificate();
    	boolean licGranted = cert.isGranted("SmartTool"); 
        if (! licGranted) 
        {
        	out.println ("<br> عفوا لقد تم انتهاء فترة صلاحية عمل النظام لتجديد رخصة النظام برجاء الاتصال على شركة سمارت فاليو : 0564627751") ;
        }
        
        Application app = (Application) cert.getApplications().get("SmartTool") ; 
    	out.print( "<br>Expiry Date is : " + app.getExpirationDate() + "    For Renewal please Call SmartValue Company : 0564627751" ) ;
    	out.println ("<br> Certificate Issued to  : " + cert.getBuiltFor()) ;
        out.println ("<br> No Of Licensed Users : " + cert.getNumberOfUsers() + " ( Actual Users = "+loggedUser.getUserCompany().getUsers(true).getPersistantObjectList().size()+")" ) ;
    	out.println ("<br> No Of Licensed Orgaznization Units : " + cert.getLicensedOrgUnitsCount() + "( Actual Org Units Count = "+loggedUser.getUserCompany().getOrgUnits(true).getPersistantObjectList().size()+" )" ) ;
    	

	%>
	</body>
</html>
