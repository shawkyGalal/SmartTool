<%@page import="java.math.BigDecimal"%>
<%@page import="com.implex.database.map.SecRole"%>
<%@page import="com.implex.database.map.MasCompanyData"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ; 
SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session ) ; //(SecUsrDta) session.getAttribute("loggedUser") ;
SecRole userDefaultSecRole = loggedUser.getDefaultSecRole();
boolean noDefaultRole = (userDefaultSecRole == null) ; 
Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
%>

<head>
    <title><%=loggedUser.getUserCompany().getCmpDescValue()%> </title>
</head>

	<div class="static-content">
    	<div class="page-content">
    	<div style = "font-size: 18">
    	<% MasCompanyData company =  loggedUser.getUserCompany() ;   	%>
    	<%= company.getMissionVisionStmt() %>
	</div>
        	<div class="page-tabs">
            </div>
            <div class="container-fluid">
            <% String queryId = "" ;
            	if (! noDefaultRole)
            	{
            %>
				<div class="row">
				
				<% queryId = "34441" ;  
				boolean nodeAccessable = false ; 
				try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;" 
				        	class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-sitemap"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
					<%} %>
					
				<%  queryId = "66561" ; 
					 nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
    				<div class="col-md-3">
						<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId%>', 'contentDiv' ) ; " class="info-tile tile-info has-footer">
	            			<div class="tile-heading">
	                			<div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
	                
	            			</div>
	            			<div class="tile-body">
		                		<div class="text-center"><div class="iconhome"><i class="ion-android-done-all"></i></div></div>
	            			</div>
	            			<div class="tile-footer">
	           				</div>
	            			
        				</a>
    				</div>
    				<% } %>
    				<%  queryId = "66757" ; 
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
    				<div class="col-md-3">
        				<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId%>' , 'contentDiv'  ) ;" class="info-tile tile-indigo has-footer">
            				<div class="tile-heading">
                				<div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %> </div>
            				</div>
			 				<div class="tile-body">
								<div class="text-center"><div class="iconhome"><i class="fa fa-cubes"></i></div></div>
            				</div>
            				<div class="tile-footer">
	           				</div>
        				</a>
    				</div>
    				<% } %>
    				<%  queryId = "64930" ; 
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId%>&printable=true', 'contentDiv' ) ; " class="info-tile tile-success has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">التقييم الإجمالى لأداء البرامج </div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-dashboard"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
					<%  queryId = "66792" ; 
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="#" class="info-tile tile-danger has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">التقارير- <%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-ban"></i></div></div>
				            </div>
				            <div class="tile-footer">    </div>
				        </a>
				    </div>
					<%} %>
				<%  queryId = "66805" ; 
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
					<div class="col-md-3">
				            <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId %>&query_id=66792' , 'contentDiv' ) ;" class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">التنبيهات- المؤشرات الغير محققة للأهداف</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-umbrella"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
				    <% queryId = "67212" ;  
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;" class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">التحليلات- أداء المراكز خلال سنة سابقة</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-cogs"></i></div></div>
				            </div>
				            <div class="tile-footer">
				                       </div>
				        </a>
				    </div>
				    <%} %>
				    
				    
				    <%
					    BigDecimal systemId = loggedUser.getUserCompany().getSystemIdValue(); 
					    if (systemId.equals(new BigDecimal("3"))) 
					    { 
						    %>
						    <!-- Include Menu For PNU System  -->
						    <jsp:include page="mainScreenPNU.jsp"></jsp:include>
						    
						    <!-- Include Menu For Training Centers System  -->
						    <jsp:include page="mainScreenOrigin.jsp"></jsp:include>
						    
						    <%
					    }
					    else if (systemId.equals(new BigDecimal("7"))) 
					    { 
						    %>
						    <!-- Include Menu For Tiba School System  -->
						    <jsp:include page="../70/mainScreenTiba.jsp"></jsp:include>
						    <%
					    }
				    %>
				    
				    <% queryId = "67423" ; 
				    
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">إدارة المستخدمين</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-group"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
				</div>
				
				<%}%>
			</div> <!-- .container-fluid -->
		</div> <!-- #page-content -->
	</div>
