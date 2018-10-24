<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  
   SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;
%>
<html>
<body>
<div class="static-leftbar-wrapper leftbar-default">
	<div class="static-leftbar">
    	<div class="leftbar">
		<div class="widget hide-on-horizontal hide-on-small">
			<div class="widget-body">
				<div class="userinfo clearfix">
					<div class="avatar">
						<img class="img-responsive img-circle" src="assets/demo/avatar/avatar_06.png">
						<!-- <span class="status online"></span> -->
					</div>
					<div class="info">
						<span class="name"><%=loggedUser.getUsrNameValue()%></span>
						<span class="email"><%=loggedUser.getEmail() %></span>
					</div>
				</div>
			</div>
		</div>
		<div class="widget stay-on-collapse" id="widget-leftbar">
			<nav role="navigation" class="widget-body">
				<% String queryId = "" ;  %>
				<ul class="acc-menu">
					
					<li>
							<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/mainScreen.jsp' , 'contentDiv'  ) ;	"> 
								<span class="icon"><i class="ion-android-home"></i></span>
								<span class="text" data-localize="الرئيسية">الرئيسية</span>
							</a>
							
					</li>
					
					
					<li><a href= "javascript:;" > 
							<span class="icon"><i class="ion-android-done-all"></i></span>
							<span class="text" data-localize="الخارطة الاستراتيجية">الخارطة الاستراتيجية</span>
						</a>
						<ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=66446', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="عرض الخطة الاستراتيجية">عرض الخارطة الاستراتيجية</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=33885', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="بناء الخطة الاستراتيجية">إنشاء/تعديل الخارطة الاستراتيجية</span></a></li>
			            	<li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=64724' ,'contentDiv' ) ;"><span class="icon"><i class="fa fa-cubes"></i></span><span class="text"  data-localize="المحاور الاستراتيجية">المحاور الاستراتيجية</span>
						</a>
					</li>
			            </ul>
					</li>
					
					<li><a href= "javascript:;" > 
							<span class="icon"><i class="ion-android-done-all"></i></span>
							<span class="text" data-localize="مؤشرات الأداء">مؤشرات الأداء</span>
						</a>
						<ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=xxxxx', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="عرض المؤشرات">عرض المؤشرات</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=33952','contentDiv' ) ; "><span class="text"><span class="text" data-localize="إدخال قيم المؤشرات لبرنامج"> إدخال قيم المؤشرات لبرنامج</span></a></li>
			            </ul>
					</li>
					
					
			        <li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="لوحات التقييم">لوحات التقييم</span></a>
			            <ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=65530&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="أداء جميع المراكز/ الإدارات ">أداء جميع المراكز/ الإدارات</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=64930&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="التقييم الإجمالي لأداء برامج مراكز الأحياء">التقييم الإجمالي لأداء برامج مراكز الأحياء</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=65076&printable=true' ,'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تقييم المؤشرات التفصيلية لأداء برامج مراكز الأحياء">تقييم المؤشرات التفصيلية لأداء برامج مراكز الأحياء</span></a></li>
			            </ul>
			        </li>
			
					<li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التقارير">التقارير</span></a>
			            <ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=xxxxx&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="المؤشرات الغير محققة للاهداف">المؤشرات الغير محققة للاهداف</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=yyyyy&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="قيم المؤشرات الغير مدخلة">قيم المؤشرات الغير مدخلة</span></a></li>
			            </ul>
			        </li>
					
					<li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التنبيهات">التنبيهات</span></a>
			            <ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=xxxxx&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تنبية المؤشرات الغير محققة للأهداف">تنبية المؤشرات الغير محققة للأهداف</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=yyyyy&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تنبية قيم المؤشرات الغير مدخلة">تنبية قيم المؤشرات الغير مدخلة</span></a></li>
			            </ul>
			        </li>
			        
			        <li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التحليلات">التحليلات</span></a>
			            <ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=xxxxx&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تحليل أداء المراكز خلال سنة سابقة">تحليل أداء المراكز خلال سنة سابقة</span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=yyyyy&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تحليل أداء البرامج خلال سنة سابقة">تحليل أداء البرامج خلال سنة سابقة</span></a></li>
			            </ul>
			        </li>
					<li>
			        	<% queryId = "34441" ;  %>
			        	<a href = "javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;" >
			        		<span class="icon"><i class="fa fa-sitemap"></i></span>
			        		<span class="text" data-localize="الهيكل التنظيمي للمؤسسة">الهيكل التنظيمي للمؤسسة</span></a>
			        </li>
			        
			        <li>
			        	<a href="javascript:;">
			        		<span class="icon"><i class="fa fa-group"></i></span>
			        		<span class="text" data-localize="إدارة المستخدمين">إدارة المستخدمين </span>
			        	</a>
			        	<ul class="acc-menu">
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=33847&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="قائمة المستخدمين ">قائمة المستخدمين </span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=25048&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="صلاحيات المستخدمين ">صلاحيات المستخدمين </span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=24968&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تأمين النظام-1 ">تأمين النظام-1 </span></a></li>
			                <li><a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/editAndExecute.jsp?id=33895&printable=true', 'contentDiv' ) ; "><span class="text"><span class="text" data-localize="تأمين النظام-2 ">تأمين النظام-2 </span></a></li>
			            </ul>
			        	
			        	
			        </li>
			
			        <li>
			        <%queryId = "36724" ;  %>
			        	<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/executeQuery.jsp?printable=true&id=<%=queryId %>' , 'contentDiv' ) ;"">
			        		<span class="icon"><i class="fa fa-group"></i></span>
			        		<span class="text" data-localize="التقارير">التقارير</span>
			        	</a>
			        </li>
			        <li>
			        	<a href="#">
			        		<span class="icon"><i class="fa fa-group"></i></span>
			        		<span class="text" data-localize="التنبيهات">التنبيهات</span>
			        	</a>
			        </li>
			
					
					<li>
						<a href="javascript:;" target = "System Maintenance">
							<span class="icon"><i class="fa fa-cogs"></i></span>
							<span class="text" data-localize="إدارة النظام">إدارة النظام</span>
						</a>
						<ul class="acc-menu">
			               	<li>
			               		<a href="/SmartTool/index.jsp?reportTopOpen=33884" target = "Sys Admin" >
			               			<span class="text"></span>
			               			<span class="text" data-localize="System Admin">System Admin </span>
			               		</a>
			               	</li>
			               	<li>
			               		<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/executeQuery.jsp?id=33951' , 'contentDiv' ) ;"  >
			               			<span class="text"></span>
			               			<span class="text" data-localize="إعدادات">إعدادات النظام </span>
			               		</a>
			               	</li>
			
			           	</ul>		
					</li>
					
			        <li>
			            <a href="loginScreen.jsp" onclick="return confirm('This Will Discard All Unsaved Changes, Are You Sure ? سوف يتم إلغاء جميع التعديلات التى لم يتم حفظها .. هل انت متأكد ؟')"><span class="icon"><i class="fa fa-sign-out"></i></span><span class="text" data-localize="الخروج من النظام">الخروج من النظام</span></a>
			
			        </li>
					 
				</ul>
			</nav>
		</div>
	</div>
</div>
</div>
</body>
</html>