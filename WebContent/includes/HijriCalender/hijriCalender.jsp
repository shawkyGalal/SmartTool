<%@page  contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%String appUrl = Support.Misc.getAppURL(request) ;%>
<link rel="stylesheet" type="text/css" href="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.css">

	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/hijriCalender.js"></script>
	
<%@page import="Support.Misc"%><meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<%
		String columnName = request.getParameter("columnName") ;
		String columnValue = request.getParameter("columnValue") ;  
		String hijriDateFieldId = columnName +"_hijriDate" ;
		String jdDateFieldId = columnName ;	
		String dateFormat  = (request.getParameter("dateFormat") != null)? request.getParameter("dateFormat") : "dd/mm/yyyy" ;
		String onCloseJsCode = request.getParameter("onCloseJsCode");
		String validator = request.getParameter("validator"); 
		String divIdWithoutQuot = request.getParameter("divIdWithoutQuot");
		hijriDateFieldId += "_"+divIdWithoutQuot ; 
		jdDateFieldId += "_"+divIdWithoutQuot ;
		
	%>
		<script type="text/javascript">

			var selectedDateFormat  = '<%=dateFormat%>'; 

			function hijriCalOnClose_<%=hijriDateFieldId%>(date) {
				convertHijriToJd('<%=hijriDateFieldId%>' , '<%=jdDateFieldId%>' ) ;
				try
				{
					<%=Support.Misc.repalceAll(onCloseJsCode , "this.value" , jdDateFieldId+".value")%>
				}
				catch (e)
				{
					alert (e) ; 
				} 
			}

			function jdCalOnClose_<%=jdDateFieldId%>(date) {
				convertJdToHijri( '<%=jdDateFieldId%>' , '<%=hijriDateFieldId%>') ;
				try
				{
					<%=onCloseJsCode%>
				}
				catch (e)
				{
					alert (e) ; 
				} 
			}


			jQuery(document).ready(function ($)
			{
			 try{
 			
				var arabicCalendar = $.calendars.instance('ummalqura', 'ar'); 
				var englishCalendar = $.calendars.instance(); 

				$('#<%=hijriDateFieldId%>').calendarsPicker({calendar: arabicCalendar , onClose: hijriCalOnClose_<%=hijriDateFieldId%> });
				$('#<%=jdDateFieldId%>').calendarsPicker({calendar: englishCalendar , onClose: jdCalOnClose_<%=jdDateFieldId%> });
				
				$('#<%=jdDateFieldId%>').calendarsPicker('option', {dateFormat: selectedDateFormat});
				$('#<%=hijriDateFieldId%>').calendarsPicker('option', {dateFormat: selectedDateFormat});
				
				$('#<%=jdDateFieldId%>').calendarsPicker({showTrigger: '#calImg'});				
				
				// --------- Setting intial Jd Value and convertig to Hijri				
				if ('<%=columnValue%>' != '')
				{
					$('#<%=jdDateFieldId%>').val('<%=columnValue%>') ;
					var dates = $('#<%=jdDateFieldId%>').val().split(',');
					//alert (dates[0]); 
					$('#<%=jdDateFieldId%>').calendarsPicker('setDate', dates);
				
					convertJdToHijri('<%=jdDateFieldId%>' , '<%=hijriDateFieldId%>') ;
					}
				}
				catch (e) 
				{
					alert (e) ;
				}
			
			});
			
			
		</script>
		
		<p>هجرى <input <%=validator %>  type="text" name="<%=hijriDateFieldId%>" id= "<%=hijriDateFieldId%>"  size="10"  >
			<img src="<%=Support.Misc.getAppURL(request)%>/includes/HijriCalender/lib/calendar.gif" alt="Popup" class="trigger calendars-trigger">
		</p>
			
		<p>ميلادى <input <%=validator %> type="text" name="<%=jdDateFieldId%>" id= "<%=jdDateFieldId%>"   size="10"  >
			<img src="<%=Support.Misc.getAppURL(request)%>/includes/HijriCalender/lib/calendar.gif" id="calImg" alt="Popup" class="trigger calendars-trigger">
		</p>
		