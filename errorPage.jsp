<%@ page isErrorPage="true" %>
<%@ page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<% request.setAttribute("exception" , exception ); %>
<jsp:include page="errorPageHandling.jsp"></jsp:include>
