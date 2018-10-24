<%@ page errorPage="/errorPage.jsp"%>
<%@page import="Support.Misc"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
	<%@page  contentType="text/html;charset=UTF-8"%>	
	<%request.setCharacterEncoding("UTF-8");%>
	<link rel="stylesheet" type="text/css" href="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker.css">
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/jQueryAssets/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
	<script type="text/javascript" src="http://hulool.epm.smart-value.com:8080/SmartTool/includes/HijriCalender/hijriCalender.js"></script>
	<!--  Jquery Validation Engine  -->
	<link rel="stylesheet" href="http://hulool.epm.smart-value.com:8080/SmartTool/jQuery-Validation-Engine/css/validationEngine.jquery.css" type="text/css"/>
	<link rel="stylesheet" href="http://hulool.epm.smart-value.com:8080/SmartTool/jQuery-Validation-Engine/css/template.css" type="text/css"/>
	<script src="http://hulool.epm.smart-value.com:8080/SmartTool/jQuery-Validation-Engine/js/jquery-1.8.2.min.js" type="text/javascript">	</script>
	<script src="http://hulool.epm.smart-value.com:8080/SmartTool/jQuery-Validation-Engine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8">	</script>
	<script src="http://hulool.epm.smart-value.com:8080/SmartTool/jQuery-Validation-Engine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8">	</script>
	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID").validationEngine();
		});
	</script>

	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<title>طلب حساب تجريبي  Request EPM Demo Account </title>
</head>
<body>
<div id = "sysMessage" > </div>
	<br>
	<p align="center"><strong><font size="5">Enterprise Performance Management System - Smart EPM : Request for Free Trail Account</p > 
	<p align="center">نظام قياس و إدارة الأداء للمؤسسات - سمارت إي بي ام : طلب تجربة مجاني </font></strong>
</p>
	<div align="center">
  	 	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<script type="text/javascript" src="/SmartTool/includes/AJAX_new.js"></script>
<script type="text/javascript" src="/SmartTool/includes/smartTool.js"></script>
<!--
<script type="text/javascript" src="/SmartTool/jQueryAssets/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/SmartTool/jQueryAssets/jquery-1.10.2.js"></script>
-->
<script type="text/javascript" src="/SmartTool/jQueryAssets/jquery-ui.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<link href="/SmartTool/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/SmartTool/jQueryAssets/jquery-ui.css">
<link href="/SmartTool/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function performAction(action)
{
	var v_url = '/SmartTool/includes/dbTransc.jsp?'+ action+'=Y' ; 
	sendAjaxRequestToJsp( v_url , 'sysMessage' ) ; 
	$( "#sysMessage" ).dialog({modal: true}, { position: { my: "center center", at: "center center", of: window  } });
}
</script>
</div>
<table style="width:100%" border = 0 > 
<tr> 
<td width="65%">
<a target="attach_details" href="/SmartTool/EPM_DOCS/EPM Marketing Prochure.pdf">
	<object data="/SmartTool/EPM_DOCS/EPM Marketing Prochure.pdf#toolbar=1&amp;navpanes=0&amp;scrollbar=1&amp;page=1&amp;view=FitH" type="application/pdf" width="100%" height="400%" internalinstanceid="3">  
		<p>It appears you dont have a PDF plugin for this browser. No biggie... you can <a href="/SmartTool/EPM_DOCS/EPM Marketing Prochure.pdf">click here to download the PDF file.</a></p> 
	</object>
</a>
</td>
<% Connection con = (Connection) session.getAttribute("con") ;  
if ( request.getParameter("Save" )!= null && con != null)
{
	String name = request.getParameter("NAME") ; 
	String email = request.getParameter("EMAIL") ;
	String reveiveUpdateByEmail = request.getParameter("RECIEVE_UPDATE_BY_EMAIL") ;
	String mobile = request.getParameter("MOBILE") ;
	String subject = Misc.replaceSingleQuteWithDouble(request.getParameter("SUBJECT")) ;
	String message = Misc.replaceSingleQuteWithDouble(request.getParameter("MESSAGE")) ;
	String orgName = Misc.replaceSingleQuteWithDouble(request.getParameter("ORG_NAME")) ;
	String orgSize = Misc.replaceSingleQuteWithDouble(request.getParameter("ORG_SIZE")) ;
	
	String insertStmt = "Insert into Support.PROSP_CUSTOMER (NAME , EMAIL ,RECIEVE_UPDATE_BY_EMAIL  , MOBILE , SUBJECT , MESSAGE , ORG_NAME , ORG_SIZE ) " 
		+ "values ('"+name+"' , '"+email+"' , '"+reveiveUpdateByEmail+"' , '"+mobile+"' , '"+subject+"' , '"+message+"' , '"+orgName+"' , '"+orgSize+"'  )" ; 
	System.out.print(insertStmt) ; 
	java.sql.Statement stmt = con.createStatement() ;  
	try { stmt.execute(insertStmt) ; }
	catch( Exception e) { stmt.close() ;  throw e ; }
	finally { stmt.close() ; } 
	
	%> 
	<td>
		<div>
			<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;text-align:center'><span style='font-size:16.0pt;color:Green;background:
			white;mso-shading-themecolor:background1'>Your request successfully sent <br> تم ارسال الطلب بنجاح </span><span
			style='font-size:20.0pt;color:black;background:#ECECEC'> <o:p></o:p></span></p>
			
			<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: auto;text-align:center'><span style='font-size:12.0pt;color:black;background:
			white;mso-shading-themecolor:background1'>an email will be sent to Your email address (<%=email%>) with Your new free account details,</span><span style='font-size:20.0pt; color:black;background:#ECECEC'>&nbsp;</span><span style='font-size:20.0pt;
			color:black'><br style='font-variant-ligatures: normal;font-variant-caps: normal; orphans: 2;text-align:-webkit-center;widows: 2;-webkit-text-stroke-width: 0px;
			text-decoration-style: initial;text-decoration-color: initial;word-spacing: 0px'>
			</span><span style='color:black;background:white;mso-shading-themecolor:background1'><small>Please	check your email and ensure that an email from customer_care@smart-value.com will not send to you junk mail box</small></span><span style='font-family:"Calibri",sans-serif;
			mso-ascii-theme-font:minor-latin;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:	"Times New Roman";mso-bidi-theme-font:minor-bidi'><o:p></o:p></span></p>
			
			
			
			<p class=MsoNormal align=center dir=RTL style='mso-margin-top-alt:auto;	mso-margin-bottom-alt:auto;text-align:center;direction:rtl;unicode-bidi:embed'><span
			lang=AR-SA style='font-size:14.0pt;color:black'>سوف يتم ارسال ايميل لك بتفاصيل الحساب الخاص بكم على بريدك الالكترونى<span style='mso-spacerun:yes'>  </span></span><span
			dir=LTR></span><span dir=LTR style='font-size:18.0pt;color:black'><span dir=LTR></span>&nbsp;</span><span lang=AR-SA><%=email%><br>
			</span><span lang=AR-SA style='font-size:10.0pt;color:black;background:white; mso-shading-themecolor:background1'>برجاء مراجعة بريدك الالكترونى و التأكد من
			ان اى رسالة واردة من <a href="mailto:منcustomer_care@smart-value.com"><span lang=EN-US dir=LTR style='font-size:10.0pt'> customer_care@smart-value.com</span></a></span><span
			dir=LTR style='font-size:14.0pt;color:black;background:white;mso-shading-themecolor:background1'><span style='mso-spacerun:yes'>  </span></span><span dir=RTL></span><span
			lang=AR-SA style='font-size:10.0pt;color:black;background:white;mso-shading-themecolor:	background1'><span dir=RTL></span><span style='mso-spacerun:yes'>  </span>لم
			يتم ارسالها الى صندوق البريد الغير هام</span><span dir=LTR style='font-size:14.0pt;color:black;background:white;mso-shading-themecolor:background1'><o:p></o:p></span></p>
			
			<p class=MsoNormal align=center dir=RTL style='mso-margin-top-alt:auto;	mso-margin-bottom-alt:auto;text-align:center;direction:rtl;unicode-bidi:embed'><span
			dir=LTR style='font-size:18.0pt;font-family:"Calibri",sans-serif;mso-ascii-theme-font:minor-latin;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
			mso-bidi-theme-font:minor-bidi;mso-no-proof:yes'>
			
			</span><span lang=AR-SA style='font-size:18.0pt;mso-ascii-font-family:Calibri;mso-ascii-theme-font:	minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;
			mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'><o:p></o:p></span></p>

		</div>
		</td>
	<% 
}
else 
{
 %>
 <td>
  <form name="PROSP_CUSTOMER_InsertForm" id="formID" action="" method="post">
	  <input name="tableName" type="hidden" value="PROSP_CUSTOMER">
	  <input name="owner" type="hidden" value="SUPPORT">
  	
  <table width="100%" border="0" dir="rtl" >
  	  
		<tr>
			<td width="10%"></td>
			<td>  برجاء تعبئة النموذج التالى لانشاء حساب تجريبى لمؤسستك على نظام سمارت إي بى إم   
				<br> Please complete and submit the below form to Create a "Smart EPM" Demo Account for your organization  
			<br><strong> الاسم    Name</strong>
			<br>
				<input id = "NAME" data-validation-engine ="validate[required]"    name="NAME" type="text" Value = """  style="width: 218px; "> 
				<div  style = "color:red; width: 301px" id="validDivNAME"></div>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td width="10%"></td>		
			<td>
				<strong>البريد الالكتروني Email </strong>
				<br>
				 <input id = "EMAIL" data-validation-engine ="validate[required] ,  validate[custom[email]]"    name="EMAIL" type="text" Value = """  style="width: 221px; "><br>
				 <div  style = "color:red; width: 301px" id="validDivEMAIL"></div> 
				هل ترغب فى استلام التحديثات بالبريد الالكتروني
				 <input type="checkbox" Value="N" onClick=" if (this.checked ) {RECIEVE_UPDATE_BY_EMAIL.value='Y';} else {RECIEVE_UPDATE_BY_EMAIL.value='N';}; ">Receive  Updates By Email?	
		        		        	     	 <input type="hidden"  name="RECIEVE_UPDATE_BY_EMAIL"  value="" > 
				<div  style = "color:red; width: 308px" id=validDivRECIEVE_UPDATE_BY_EMAIL></div>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td width="10%"></td>		
			<td>
				<strong>الهاتف Mobile</strong><br><input id = "MOBILE" data-validation-engine ="validate[required]"    name="MOBILE" type="text" Value = """  >
				<div  style = "color:red; width: 274px" id="validDivMOBILE"></div>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td width="10%"></td>		
			<td> 
				<strong>Company الشركة   </strong><br>
			 	<input id = "ORG_NAME" data-validation-engine ="validate[required]"    name="ORG_NAME" type="text" Value = """  style="width: 382px; "> 
				<div  style = "color:red; width: 195px" id="validDivORG_NAME"></div>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td width="10%"></td>		
			<td> 
				<strong>Company Size  حجم الشركة</strong><br>
			 	<select id = "ORG_SIZE" data-validation-engine ="validate[required]"    name="ORG_SIZE" type="text" Value = """  style="width: 160px; ">
			 		<option value="1">20 - 100 Employees </option>			 		
			 		<option value="2">100 - 500 Employees </option>
			 		<option value="3">500 - 1000 Employees </option>
			 		<option value="4">More Than 1000 Employees </option>
			 	</select> 
				<div  style = "color:red; width: 195px" id="validDivORG_SIZE"></div>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td width="10%"></td>		
			<td> 
				<strong>الموضوع Subject  </strong><br>
			 	<input id = "SUBJECT" data-validation-engine ="validate[required]"    name="SUBJECT" type="text" Value = "طلب حساب تجريبي - Request For a Demo Account"  readonly style="width: 382px; "> 
				<div  style = "color:red; width: 195px" id="validDivSUBJECT"></div>
			</td>
		</tr>
		<tr></tr>	
		<tr>
			<td width="10%"></td>		
			<td>
				<strong>الرسالة Message </strong><br>
				 <textArea data-validation-engine ="validate[required]"   name="MESSAGE" style = "width: 382px; height : 154px"></textArea> 
				<div  style = "color:red; width: 258px" id="validDivMESSAGE"></div>
			</td>
		</tr>
	
</table>

<div align="center">

	<p>
	<script>
	var msg= "Invalid Name  " ; 
	var sts= false ; 

	function formValidate()
	{
		var a1 = checkName (document.getElementById('NAME')) ;
		var a2 = checkEmail(document.getElementById('EMAIL'))  ;
		var a3 = checkMobile(document.getElementById('MOBILE'))  ;
		var result =  a1.status & a2.status & a3.status ;
		return (result === 0 )? false : true ; 
		
	}
	function checkName(object )
		{
			if(true)
			{
			  msg = ''  ;  
			  sts = true ; 
			  vr = {message :msg , status : sts} ;
			  document.getElementById('validDivNAME').innerHTML = vr.message ;
			  return vr ; 
			} 
		}
		function checkEmail(object)
		{
			  msg = ''  ; 
			  sts = true ; 
			  vr = {message :msg , status : sts} ;
			  document.getElementById('validDivEMAIL').innerHTML = vr.message ;
			  return vr ; 
		}
		function checkMobile(object)
		{
			  msg = ''  ; 
			  sts = true ; 
			  if (isNaN(object.value)) 
			  {
			  msg = 'Should be a Number Only'  ; 
			  sts = false ;
			  }
			  vr = {message :msg , status : sts} ;
			  document.getElementById('validDivMOBILE').innerHTML = vr.message ;
			  return vr ; 
		}
	</script>
	
	<input class="submit" type="submit" name="Save" value=" Send إرسال" onclick="return ( formValidate() ) ; "></p>
	
</div>
</form>
</td>
<tr>
</table>
<% } %>
</body>
</html>