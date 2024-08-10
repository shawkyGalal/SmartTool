 <%@page import="com.smartvalue.moj.najiz.NajizService"%>
 <%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
 <%@page import="com.google.gson.Gson"%>
 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.mashape.unirest.http.*"%>
 <%@page import ="com.google.gson.internal.LinkedTreeMap"%>
 <%@page import ="com.smartvalue.moj.najiz.mapping.appointments.AppointmentServices"%>
 <%@page import ="com.mashape.unirest.http.exceptions.UnirestException"%>
 
<!DOCTYPE html>
<html>
<head>

<title>Build Appointment Request /title>
</head>
<body>
<%@include file="../intialize.jsp" %>
<form action="CreateAction.jsp" method="post">

	<%
	Gson gson = new Gson();
	HttpResponse<String> serviceResponse = null ; 
	mojEnv.refreshAccessToken(); 
	%>
		 
	<h1> Add a New Appointment Request </h1>
	== Not Yet Completed ==  
	
	<h2>Flow Name : GetRegions </h2>
	<%
	try {
	AppointmentServices  appointmentServices = mojEnv.getAppointmentService() ;  
	serviceResponse = appointmentServices.getRegions();
	ArrayList<Object>  responseBody = gson.fromJson(serviceResponse.getBody(), ArrayList.class); 
	%>
	Subject : <input type="text" name="subject" id="subject" value="APPLY FOR A COMPLAIN">
	<br><select id="region" name = "region">
	<% 
	for (Object region:  responseBody )
		{
			LinkedTreeMap<String , Object> regionMap = (LinkedTreeMap<String , Object>)region ;  
			String regionName = (String)regionMap.get("name") ;
			Double regionId = (Double)regionMap.get("id") ;
			out.print("<option id ="+regionId+">" + regionName + "</option>" ) ; 
		}
	%>
	</select>
	<h2>Flow Name : GetSites  </h2>
	<%
		serviceResponse = mojEnv.getAppointmentService().getSites(12);
		responseBody = gson.fromJson(serviceResponse.getBody(), ArrayList.class); 
	%>
	<select id="siteName" name = "siteName">
	<% 
	for (Object site:  responseBody )
		{
			LinkedTreeMap<String , Object> sitesMap = (LinkedTreeMap<String , Object>)site ;  
			String siteName = (String)sitesMap.get("name") ;
			Double siteId = (Double)sitesMap.get("id") ;
			out.print("<option id = "+siteId+">" + siteName + "</option>" ) ; 
		}
	%>
	</select>
		
	<h2>Flow Name : Departments  </h2><%
		 
	serviceResponse = mojEnv.getAppointmentService().getDepartments(245);
	responseBody = gson.fromJson(serviceResponse.getBody(), ArrayList.class); 
	%>
	<select id="departmentName" name = "departmentName" >
	<% 
		for (Object dept:  responseBody )
			{
				LinkedTreeMap<String , Object> deptMap = (LinkedTreeMap<String , Object>)dept ;  
				String deptName = (String)deptMap.get("name") ;
				Integer deptId = ((Double) deptMap.get("id")).intValue();
				out.print("<option id = "+deptId+" value = "+deptId+">" + deptName + "</option>" ) ;  
			}
	%>
	</select>
		

	<h2>Flow Name : GetServices  </h2><%
		serviceResponse = mojEnv.getAppointmentService().getServices(349);
		responseBody = gson.fromJson(serviceResponse.getBody(), ArrayList.class); 
	%>
	<select id = "serviceId" name = "serviceId">
	<% 
		for (Object service:  responseBody )
			{
				LinkedTreeMap<String , Object> serviceMap = (LinkedTreeMap<String , Object>)service ;  
				String serviceName = (String)serviceMap.get("name") ;
				Integer serviceId = ((Double) serviceMap.get("id")).intValue();
				out.print("<option  id = "+serviceId+" value = "+serviceId+" >" + serviceName + "</option>" ) ; 
			}
	%>
	</select>
		
	<h2>Flow Name : Get Available Appointments Times </h2>
	<%
		serviceResponse = mojEnv.getAppointmentService().getAvailableAppointments(322, 36);
		try{ responseBody = gson.fromJson(serviceResponse.getBody(), ArrayList.class);
		out.print( Renderer.generateArrayHtmlTable(responseBody)) ;
		}
		catch(Exception e ){ 
		com.google.gson.internal.LinkedTreeMap hashMapResponseBody = gson.fromJson(serviceResponse.getBody(), com.google.gson.internal.LinkedTreeMap.class);
		out.print( hashMapResponseBody.toString()) ;
		}
		 
	
	} 
	catch ( AccessTokenNotFound ex) {response.sendRedirect("/ResourceManagerWeb/NajizLikeSampleApp/index.jsp") ; return ;  }
	catch (UnirestException t) {mojEnv.refreshAccessToken() ; }
	%>
	<br><br><br>Selected Day : <input type="text" name = "date"  value = "2023-10-29">	
	<br><br><br>Selected TimeSlot : <input type="text" name = "slotId" id = "slotId" value = "5">
	<br> <br> <input type="submit"  name="Ø­Ø¬Ø² ÙÙØ¹Ø¯" id="submit" />
	</form>

</body>
</html>