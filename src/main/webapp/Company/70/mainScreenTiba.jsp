<%@page import="com.implex.database.map.SecRole"%>
<%@page import="com.implex.database.map.MasCompanyData"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% 	 
	Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
	String queryId = "" ;
%>			
				<% queryId = "72424" ; // Attendance & Homework  
				boolean nodeAccessable = false ; 
				try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId %>' , 'contentDiv' ) ;" 
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
					
				<%  queryId = "72423" ; // Exams
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
    				<%  queryId = "72432" ; //Settings
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
    				
    				<%  queryId = "72422" ; //Settings
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
