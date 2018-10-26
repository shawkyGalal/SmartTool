<%@page import="java.math.BigDecimal"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ; 
SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session ) ;
int usrInterfaceLang = loggedUser.getUserLangIntValue() ; 
String displayDirection =  loggedUser.getDisplayDirection() ; 
//BigDecimal systemId = loggedUser.getUserCompany().getSystemIdValue(); 
//String mainScreenPage = (systemId.equals(new BigDecimal("3"))) ? "mainScreenPNU.jsp" : "mainScreen.jsp" ; 
%>

<html lang="en">
<head>
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/smartTool.js"></script>

    <meta charset="utf-8">
    <title><%=loggedUser.getUserCompany().getCmpDescValue() %></title>
    <%@ include file = "jsScripts.html" %>

</head>

<body class="infobar-offcanvas <%=displayDirection%> ">
<div id = "sysMessage" ></div>        


	<!-- Togglable navigation -->
        <!-- ================================================ -->
             	<jsp:include page="header.jsp"></jsp:include>
        <!-- ================================================ -->

	<!-- /.navbar-collapse -->

        <div id="wrapper">
            <div id="layout-static">
            	<!-- ================================================ -->
                	<jsp:include page="navBarNew.jsp"></jsp:include>
             	<!-- ================================================ -->
                <div class="static-content-wrapper">
                	<div id = "contentDiv" > 
            	<!-- ================================================ -->
 						<jsp:include page="mainScreen.jsp"></jsp:include>  
             	<!-- ================================================ -->
					</div>
            	   	<jsp:include page="footer.jsp"></jsp:include>
             	<!-- ================================================ -->

                </div>
            </div>
        </div>


        <div class="infobar-wrapper scroll-pane">
            <div class="infobar scroll-content">
                <div id="widgetarea">
                    <div class="widget">
                        <div class="widget-heading">
                            <a href="javascript:;" data-toggle="collapse" data-target="#options"><h4>اختر الملف</h4></a>
                        </div>
                        <div class="panel-body">
                            <div id="tree-default">
                                <ul>
                                    <li>
                                        Root node 1
                                        <ul>
                                            <li data-jstree='{ "selected" : true }'>
                                                <a href="#">
                                                    Initially selected
                                                </a>
                                            </li>
                                            <li data-jstree='{ "icon" : "ion-briefcase text-success " }'>
                                                custom icon URL
                                            </li>
                                            <li data-jstree='{ "opened" : true }'>
                                                initially open
                                                <ul>
                                                    <li data-jstree='{ "disabled" : true }'>
                                                        Disabled Node
                                                    </li>
                                                    <li data-jstree='{ "type" : "file" }'>
                                                        Another node
                                                    </li>
                                                </ul>
                                            </li>
                                            <li data-jstree='{ "icon" : "ion-android-warning text-danger" }'>
                                                Custom icon class (bootstrap)
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-jstree='{ "type" : "file" }'>
                                        <a href="http://www.jstree.com">
                                            Clickanle link node
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                    </div>

                </div>
                
</div>
        </div>


    </body>
</html>