<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.smartValue.database.map.services.*,com.smartValue.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ; 
SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session ) ;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
</head>
<body>
 <div class="footerfix">
         <a href = "http://www.smart-value.com" > <img src="assets/img/smart-value.bmp" width="20" /> </a>
                 Copyright © 1998,2017
   
         <img src="images/HuloolLogo.png" width="20" "<%=loggedUser.getUserCompany().getAttributeValue("LOGO_LINK") %>"  />
 </div>                    
</body>
</html>