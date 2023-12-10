<%@ page errorPage="../errorPage.jsp" import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@page import="java.util.* , Support.Misc"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html;charset=windows-1256"%>
<%request.setCharacterEncoding("windows-1256");%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:w="urn:schemas-microsoft-com:office:word"
xmlns:m="http://schemas.microsoft.com/office/2004/12/omml"
xmlns="http://www.w3.org/TR/REC-html40">
<title>طباعة على النموذج </title>

<head>
<meta http-equiv=Content-Type content="text/html; charset=windows-1256">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 14">
<meta name=Originator content="Microsoft Word 14">
<link rel=File-List href="contract_print_files/filelist.xml">
<link rel=Edit-Time-Data href="contract_print_files/editdata.mso">

<style>
</style>
</head>

<body lang=EN-US style='tab-interval:.5in'>


<div align=center>
<%
  java.sql.Connection  con , repCon  =null;
	  con = (java.sql.Connection)session.getAttribute("con");

	  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  String loggedUser = Misc.getConnectionUserName(con) ;  
	  java.sql.Statement stmt = con.createStatement();  
	  String uniqueColumnName = "contRowIdValue";
	  String rowIdValue =  request.getParameter("contRowIdValue");
	  Vector pramNames = new Vector() ; pramNames.add("$$"+uniqueColumnName) ;  
	  Vector pramValues = new Vector() ; pramValues.add(rowIdValue) ;
	  Support.SqlReader sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  "26158", pramNames , pramValues , false);
	  String queryStatment = sqlReader.getQueryStatments()[0] ;  
	  
  
  	//-------------------------------------------
  	java.sql.ResultSet rs= null;
  	try{ rs =  stmt.executeQuery(queryStatment);  	}
  	catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
	rs.next();
 	String company = rs.getString ("COMPANY"); 	
 	String branch = rs.getString("BRANCH") ;
 	String branch_id = rs.getString("BRANCH_ID") ;
//------------Customer Info -----------	
	String custName = rs.getString("CUST_NAME") ; 	String idNo = rs.getString("ID_NO") ; 	String idType = rs.getString("ID_TYPE") ; 	
	String idDateExpire = rs.getString("ID_DATE_EXPIRE") ; String idSource = rs.getString("ID_SOURCE") ; 	
	String licNo = rs.getString("LIC_NO") ;  String licDateExpire = rs.getString("LIC_DATE_EXPIRE") ;
	String licSource = rs.getString("LIC_SOURCE") ; String custOfficeAddress = rs.getString("CUST_OFFICE_ADDRESS") ;  String office_tel = rs.getString("OFFICE_TEL") ;
	String cust_address = rs.getString("CUST_ADDRESS") ; String extraDriverName = rs.getString("EXTRA_DRIVER_NAME") ; String  extra_driver_lic_id = rs.getString("EXTRA_DRIVER_LIC_ID") ;
	String extra_driver_lic_edate = rs.getString("EXTRA_DRIVER_LIC_EDATE") ;
	String custMobile =   rs.getString("CUST_MOBILE") ; 
	String custNo = rs.getString("CUST_NO") ; 
	String custNationality = rs.getString("CUST_NATIONALITY") ; 
//----------- Car Info -----------------
	String carCode = rs.getString ("CAR_CODE"); String carType = rs.getString("CAR_TYPE"); String carYear = rs.getString("CAR_YEAR");
	String carColor = rs.getString("CAR_COLOR") ; String carDailyRate = rs.getString("CAR_DAILY_RATE") ;  String carKmDay = rs.getString("CAR_KM_DAY");
	String carExtraHourRate = rs.getString("CAR_EXTRA_HOUR_RATE") ; 
	String carExtraKmRate = rs.getString("CAR_EXTRA_KM_RATE") ;
	String carFmkmreading = rs.getString("CAR_FM_KM_READING"); 
	String carRrturnKM = rs.getString("CAR_RETURN_KM");
	String discount = rs.getString("DISCOUNT");  
	
	
	
//-----------Contract Info -------------
	String serialNo = rs.getString("SERIALNO") ; String status = rs.getString("STATUS"); 
	String usedKM = rs.getString("USED_KM") ; BigDecimal totalPayments = rs.getBigDecimal("total_Payments"); BigDecimal total_penalty = rs.getBigDecimal("total_penalty");
	String extra_hours = rs.getString("extra_hours") ; String extraHoursVal = rs.getString("EXTRA_HOURS_VAL") ;	
	String extraKm = rs.getString("EXTRA_KM"); String extra_km_val = rs.getString("EXTRA_KM_VAL");
	String interval = rs.getString("INTERVAL") ; String damgeValue = rs.getString("DAMGEVALUE") ;   
	String carincome = rs.getString("CARINCOME") ; 
	String conOutDate = rs.getString("out_date") ; String conOutTime = rs.getString("out_time") ;
	String conInDate = rs.getString("in_date") ; String conInTime = rs.getString("in_time") ;
	String conDaysValue = rs.getString("con_days_value");	 
	String contExpiryDate = rs.getString("cont_expiry_date");
	String contNeedToCllect = rs.getString("need_to_cllect") ;
	String contNeedToRefund = rs.getString("need_to_refund") ; 
 

	stmt.close();
    rs.close();
%>
<table style="height: 20px ; width:18cm" dir = rtl border = 0>
<tr style="height: 30px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:1cm "></td> 
	<td align = left style=" width:4cm "><%=serialNo%>/<%=branch %></td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "></td>
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "></td>
</tr>


<tr style="height: 30px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:1cm "></td> 
	<td colspan = 3 align = left style=" width:4cm "><%=custNo %></td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:1cm "></td> 
	<td colspan = 3 align = right style=" width:6cm " nowrap="nowrap" ><%=custName%></td> 
	<td colspan = 2 align = center style=" width:6cm "><%=contExpiryDate %></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:1cm "></td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "><%=idType%></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "><%=carType%></td>
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "><%=carDailyRate %> ريال</td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "><%=idNo%></td> 
	<td align = center style=" width:2cm "><%=idDateExpire %></td> 
	<td align = left style=" width:1cm "><%=idSource%></td>
	<td align = right style=" width:2cm "><%=custNationality%></td> 
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "><%=carCode%></td>
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:4cm "><%=carKmDay %> كم </td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carColor %></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carExtraHourRate %> ريال</td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "><%=licNo %></td> 
	<td align = center style=" width:2cm "><%=licDateExpire %></td> 
	<td align = left style=" width:1cm "><%=licSource %></td>
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carYear %></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carExtraKmRate %> هلله</td>
</tr>

<tr style="height: 40px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=extraDriverName %></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=conOutDate %></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=conInDate %></td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "><%=extra_driver_lic_id %></td> 
	<td align = center style=" width:2cm "><%=extra_driver_lic_edate %></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=conOutTime %></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=conInTime %></td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:1cm "></td> 
	<td colspan = 3  align = center style=" width:6cm " nowrap="nowrap"><%=custOfficeAddress %></td> 
	<td align = center style=" width:1cm "></td> 
	<td align = center style=" width:2cm "><%=carFmkmreading %> كم </td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carRrturnKM %> كم </td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "><%=office_tel%></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=usedKM %> كم</td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=damgeValue %> ريال </td>
</tr>

<tr style="height: 20px" >
	 
	<td colspan = 3 align = center style=" width:2cm " nowrap="nowrap" ><%=cust_address%></td> 
	<td align = right style=" width:2cm "><%=custMobile %></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=interval%></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=conDaysValue %> ريال </td>
</tr>
<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=extra_hours %> ساعة</td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=extraHoursVal %> ريال</td>
</tr>
<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=extraKm %> كم </td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=extra_km_val %> ريال</td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=total_penalty %> ريال</td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=discount %> ريال </td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=carincome %> ريال </td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=totalPayments %> ريال </td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=contNeedToCllect %> ريال </td>
</tr>

<tr style="height: 20px" >
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "></td>
	<td align = center style=" width:2cm "></td> 
	<td align = center style=" width:2cm "><%=contNeedToRefund %>  ريال </td>
</tr>

</table>

</div>

<div height=20cm > 

</div>

<div align="center" dir = RTL >
<table border = 0>
	<tr height= 280px> 
		<td> </td>
	</tr>
	<tr> 
		<td> 
			<jsp:include page="../renderQueryResult.jsp?id=26158&lookupTableName=LU_QUERIES">
				<jsp:param value="1" name="queryIndex"/>
				<jsp:param value="<%=rowIdValue%>" name="rowId"/>
				<jsp:param value="true" name="printable"/>
				
			</jsp:include>
			<a href = "../editAndExecute.jsp?id=26158&lookupTableName=LU_QUERIES&ContRowIdValue=<%=rowIdValue%>" title ="Click To Maintain">Maintain Report</a>
		</td> 
	</tr>
	
</table>
</div>
	
</body>

</html>
