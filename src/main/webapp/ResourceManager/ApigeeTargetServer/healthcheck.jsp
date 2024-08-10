<%@page import="java.util.Date"%>
<%
Date startDate = new Date()  ;
int delayInsec = 3 ; //Integer.parseInt(request.getParameter("delayInsec")) ; 
int statusCode = Integer.parseInt(request.getParameter("statusCode")) ; 
String healthcheckId = request.getHeader("X-Apigee-Healthcheck-Id"); 

response.setStatus(statusCode); // Set to the desired status code
response.setHeader("ImOK", "true");
java.util.concurrent.TimeUnit.SECONDS.sleep(delayInsec);
Date endDate = new Date()  ;
System.out.println( startDate  + " Health Check request to healthcheck.jsp is issued : Statuscode =  " + statusCode  + " With a delay = " + delayInsec + " sec" + ", endDate = " + endDate + ", healthcheckId = " + healthcheckId) ;



%>
{
	"statusCode": "<%=statusCode%>", 
	"delayInsec" : "<%=delayInsec %>"
}