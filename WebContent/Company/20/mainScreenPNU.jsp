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
				<% queryId = "71807" ; // الجهات التدريبية  
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
					
				<%  queryId = "71826" ; // البرامج التدريبية
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
    				<%  queryId = "71813" ; // إدارة  و إعدادات النظام
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
    				<%  queryId = "71835" ;  // تقارير و إحصائيات
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId%>&printable=true', 'contentDiv' ) ; " class="info-tile tile-success has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"> <%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %> </div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-dashboard"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
					<%  queryId = "71822" ; // تسجيل الطالبات على برنامج تدريبي  
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="#" class="info-tile tile-danger has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-ban"></i></div></div>
				            </div>
				            <div class="tile-footer">    </div>
				        </a>
				    </div>
					<%} %>
				
				    <% queryId = "71824" ;  // عرض تقييم الطالبات من قبل المستشفى
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;" class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-cogs"></i></div></div>
				            </div>
				            <div class="tile-footer">
				                       </div>
				        </a>
				    </div>
				    <%} %>
				    
					
				    <% queryId = "71823" ;  // عرض حضور و غياب الطالبات
				    
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-group"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
				    
				    <%  queryId = "71833" ; //  عرض بيانات الجهات التدريبية
					nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
					<div class="col-md-3">
				            <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=<%=queryId %>&query_id=66792' , 'contentDiv' ) ;" class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-umbrella"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
				    
				    <!------------------------------------ Trainers Functions  ------------------------------------>
				    <!------------------------------------ Trainers Functions  ------------------------------------>
				    
				    <% queryId = "71816" ;  // 01-تسجيل حضور الطالبات
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
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

				    <% queryId = "71817" ;  // 02- تقييم الطالبات
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
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
				    <%} %>
				    <% queryId = "71832" ;  // عرض البيانات الاكاديمية لطالبة  
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-cubes"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>

				    <% queryId = "71905" ;  // التنبيهات و الانذارات 
				    nodeAccessable = false ; try {mainQueriesTree.getindex2("LU_QUERIES"+queryId) ; nodeAccessable = true ;	}catch(Exception e ){}
					if (nodeAccessable)  {%>
				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ; " class="info-tile tile-primary has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white"><%=mainQueriesTree.getDesc(mainQueriesTree.getindex2("LU_QUERIES"+queryId)) %></div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-cubes"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				    <%} %>
				
				
				<!---------------------------------- Students Functions  -------------------------------------------->
				<!---------------------------------- Students Functions  -------------------------------------------->
					
				<% queryId = "71834" ; //تقييم جهة التدريب   
				nodeAccessable = false ; 
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
					
				<%  queryId = "71818" ; // عرض  و تعديل بياناتى الشخصية
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
    				<%  queryId = "71825" ; // الاطلاع على تقييم برنامج تدريبي
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
				    
				    <%  queryId = "71902" ; // التنبيهات و الانذارات
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
    				
    				<%  queryId = "71900" ; // طلبات الاجازة
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
    				
    				<%  queryId = "71901" ; // طلبات التدريب
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
