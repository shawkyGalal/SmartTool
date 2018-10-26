<%@ page errorPage="errorPage.jsp" %>
<%@page import="org.apache.cayenne.access.DataContext"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<html>
<title>SmartTool- Smart Value Integrated Solutions</title>
<BODY TEXT="blue" LINK="red" bgcolor="#FFEEFF">

   
   <% 
    String remoteAddr = request.getRemoteAddr();
    String info ;
	Support.NetworkMaint.ComputerInfo compInfo = new Support.NetworkMaint.ComputerInfo(); 
	compInfo.setIpAddress(remoteAddr) ;
	info =  compInfo.getAllHostINfo();
	//out.print(info);
   %>
   Client Information بيانات جهاز العميل :
   <br></br>
	<form action="">
	   <table border="1" align="center">
	   	<TR>
	   		<td>Host</td>
	   		<td>Class</td>
	   		<td>IP</td>
	   		<td>cHost</td>
	   		<td>siteLocal?</td>
	   		<td>Mac Address</td>   	
	   	</TR>
	   	<tr>
	   		<td><%=compInfo.getInetAddress().getHostName() %></td>
	   		<td><%=compInfo.getInetAddress().getClass() %></td>
	   		<td><%=compInfo.getInetAddress().getHostAddress() %></td>
	   		<td><%=compInfo.getInetAddress().getCanonicalHostName() %></td>
	   		<td><%=compInfo.getInetAddress().isSiteLocalAddress() %></td>
	   		<td><%=compInfo.getMacAddress() %></td>
	   	</tr>
	   </table>
-----
	   <table border="1" align="center">
	   	<TR>
			<td>المستخدم </td>
	   		<td>الادارة </td>
	   		<td>الموقع </td>
	   		<td>الموديل/الماركة </td>
	   		<td>الرقم المسلسل  </td>
	   		<td>نظام التشغيل  </td>
	   		
		</TR>
		<tr>
	   		<td><input type="text" name="userName"></input></td>
	   		<td><input type="text" name="deptName"></input></td>
	   		<td><input type="text" name="location"></input></td>
	   		<td><input type="text" name="Model"></input></td>
	   		<td><input type="text" name="serialNo"></input></td>
	   		<td><select name="serialNo"> 
	   				<option id= "Please Select" >Please Select</option>
	   				<option id= "Windows 7" >Windows 7 </option>
	   				<option id= "Windows XP" >Windows XP</option>
	   			</select>
	   		</td>

		
		</tr>
</table>	   
	   <input type="submit" value="حفظ البيانات" id="ddd"/> 
	</form>
<p align="center"><a href="Smart_Docs/Smart_Tech_Guide.zip"
	target="Doc">Tech.User Guide </a> - <a
	href="Smart_Docs/Smart_Tool_Demos/Smart_Tool_Demos.zip"
	target="Simple_Query">Demo</a></p>

<jsp:include page="/footer.jsp" />
</BODY>

</HTML>
