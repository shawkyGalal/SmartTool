<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<link rel="stylesheet" type="text/css" href="/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker.css">
		<script type="text/javascript" src="/SmartTool/jQueryAssets/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
		<script type="text/javascript" src="/SmartTool/includes/HijriCalender/hijriCalender.js"></script>

<title>Insert title here</title>
</head>
<body>
	<%
       String AAAA = request.getParameter("AAAAAA") ;
        String BBBBBB = request.getParameter("BBBBBB") ;
        out.println( "You have submitted AAAA= "  + AAAA + " and BBBB = " +  BBBBBB) ;
     %>
       <form action="" method="post">
       
        		        		<jsp:include page="hijriCalender.jsp">
        		        			<jsp:param value="01/01/2008" name="columnValue"/>
        		        			<jsp:param value="AAAAAA" name="columnName"/>
        		        			<jsp:param value="dd/mm/yyyy" name="dateFormat"/>
        		        			<jsp:param value="alert(abc1)" name="onCloseJsCode"/>
        		        			<jsp:param value="1" name="divIdWithoutQuot"/>
        		        		</jsp:include>

        		        		<jsp:include page="hijriCalender.jsp">
        		        			<jsp:param value="01/01/2002" name="columnValue"/>
        		        			<jsp:param value="AAAAAA" name="columnName"/>
        		        			<jsp:param value="dd/mm/yyyy" name="dateFormat"/>
        		        			<jsp:param value="alert(abc2)" name="onCloseJsCode"/>
        		        			<jsp:param value="2" name="divIdWithoutQuot"/>
        		        		</jsp:include>
        		        		
        		        		<input type="submit" name="Apply" value="Apply">
		</form>
</body>
</html>