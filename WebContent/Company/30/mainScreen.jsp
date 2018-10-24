<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>

<head>
    <title>جمعية مراكز الأحياء بمنطقة مكة المكرمة</title>
</head>

	<div class="static-content">
    	<div class="page-content">
        	<div class="page-heading mb-n">
            	<h1>الرئيسية</h1>
                	<div class="options">
                	</div>
            </div>
            <div class="page-tabs">
            </div>
            <div class="container-fluid">
            <% String queryId = "" ;  %>
				<div class="row">
    				<div class="col-md-3">
						<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=66446', 'contentDiv' ) ; " class="info-tile tile-info has-footer">
	            			<div class="tile-heading">
	                			<div class="text-center text-white">الهيكل الاستراتيجى  </div>
	                
	            			</div>
	            			<div class="tile-body">
		                		<div class="text-center"><div class="iconhome"><i class="ion-android-done-all"></i></div></div>
	            			</div>
	            			<div class="tile-footer">
	           				</div>
	            			
        				</a>
    				</div>
    				<div class="col-md-3">
        				<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=64724' , 'contentDiv'  ) ;" class="info-tile tile-indigo has-footer">
            				<div class="tile-heading">
                				<div class="text-center text-white">المحاور الاستراتيجية</div>
            				</div>
			 				<div class="tile-body">
								<div class="text-center"><div class="iconhome"><i class="fa fa-cubes"></i></div></div>
            				</div>
            				<div class="tile-footer">
	           				</div>
        				</a>
    				</div>

				    <div class="col-md-3">
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=64930&printable=true', 'contentDiv' ) ; " class="info-tile tile-success has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">شاشات المتابعة</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-dashboard"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>

				    <div class="col-md-3">
				        <a href="#" class="info-tile tile-danger has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">صيانة المستخدمين</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-group"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>

				    <div class="col-md-3">
				    <% queryId = "34441" ;  %>
				        <a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;" class="info-tile tile-sky has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">الهيكل التنظيمي للمؤسسة</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-sitemap"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>

				    <div class="col-md-3">
				        <a href="#" class="info-tile tile-primary has-footer">
				            <div class="tile-heading">
				                <div class="text-center text-white">إدارة النظام</div>
				            </div>
				            <div class="tile-body">
				                <div class="text-center"><div class="iconhome"><i class="fa fa-cogs"></i></div></div>
				            </div>
				            <div class="tile-footer">
				            </div>
				        </a>
				    </div>
				</div>
			</div> <!-- .container-fluid -->
		</div> <!-- #page-content -->
	</div>
