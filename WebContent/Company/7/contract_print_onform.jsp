<%@ page errorPage="/errorPage.jsp" import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@page import="java.util.* , Support.Misc"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html;charset=windows-1256"%>
<%request.setCharacterEncoding("windows-1256");%>
<html>
<title>ÿ»«⁄… ⁄·Ï «·‰„Ê–Ã </title>

<head>
<% double cellHight = 1 ;
double firstLeft = 1.2 ;
double firstColumnWidth = 3; 
double secondColumnWidth = 3;
%>
<style>

	div.general {
		/* border:1px solid red ; */
		height:0.7cm; 
		display: table-cell;
		vertical-align: middle;
		
	}
	div.firstColumn {
	    /* border:1px solid red ; */
		height:0.7cm; 
		width :2.8cm ; 
		left  :1cm ;
		display: table-cell;
		vertical-align: middle;
		
		}
	div.secondColumn {
		/* border:1px solid red ; */
		height:0.7cm; 
		width :2.8cm ; 
		left  :6cm
		}
	div.thirdColumn {
		/* border:1px solid red ; */
		height:0.7cm; 
		left  :11cm
		}
		
</style>

</head>

<body lang=EN-US style='tab-interval:.5in; height: 846px'>


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
	String idDateExpire = rs.getString("ID_DATE_EXPIRE") ; 
	idDateExpire = (idDateExpire== null)? "---" : idDateExpire ; 
	String idSource = rs.getString("ID_SOURCE") ; 	
	String licNo = rs.getString("LIC_NO") ;  String licDateExpire = rs.getString("LIC_DATE_EXPIRE") ;
	licDateExpire = (licDateExpire==null)? "---" : licDateExpire ; 
	String licSource = rs.getString("LIC_SOURCE") ; String custOfficeAddress = rs.getString("CUST_OFFICE_ADDRESS") ;  String office_tel = rs.getString("OFFICE_TEL") ;
	office_tel = (office_tel==null) ? "---" : office_tel ; 
	String cust_address = rs.getString("CUST_ADDRESS") ; String extraDriverName = rs.getString("EXTRA_DRIVER_NAME") ; String  extra_driver_lic_id = rs.getString("EXTRA_DRIVER_LIC_ID") ;
	String extra_driver_lic_edate = rs.getString("EXTRA_DRIVER_LIC_EDATE") ;
	extra_driver_lic_edate = (extra_driver_lic_edate == null)? "---" : extra_driver_lic_edate ; 
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
	String logged_user = rs.getString("logged_user") ;
	String geo_boundary = rs.getString("GEO_BOUNDARY") ;
		 
    String queryStatment1 = sqlReader.getQueryStatments()[1] ;
    try{ rs =  stmt.executeQuery(queryStatment1);  	}
  	catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}

 	String payDate = "";
 	String spellAmount = ""; 	
 	String amount = "";
 	String recepitNo = "";
 	String drcr = "";
 	String notes = "";
 	String issuedBy = "";
 	
	try{
	rs.next();
 	payDate = rs.getString ("pay_date");
 	spellAmount = rs.getString ("SPELLAMOUNT"); 	
 	amount = rs.getString ("AMOUNT");
 	recepitNo = rs.getString ("recepitNo");
 	drcr = rs.getString ("DRCR");
 	notes = rs.getString ("NOTES");
 	issuedBy = rs.getString ("CREATED_BY");
 	} catch ( Exception e) {}
 	
    
    rs.close();
    stmt.close();
%>

<div class = "general"  dir ="rtl" style="position:absolute top:0.1cm; left:0.1cm; height: 22cm; width: 20.0cm">

	<div id = "row1"  align= "center" class = "firstColumn" style="position:absolute ; top : 0.8cm ; width: 94px" ><%=serialNo%>/<%=branch %></div>
	<div id = "row1"  align= "center" class = "firstColumn" style="position:absolute ; top : 2.9cm ; width: 130px ; left: 0.6cm" ><%=geo_boundary%></div>
	<div id = "row2"  align= "center" class = "firstColumn" style="position:absolute ; top : 1.2cm ; width: 2.5cm"></div>
	<div id = "row3"  align= "center" class = "firstColumn" style="position:absolute ; top : 1.9cm ; width: 2.5cm"><%=custNo %></div>
	<div id = "row4"  align= "center" class = "firstColumn" style="position:absolute ; top : 2.6cm "></div>
	<div id = "row5"  align= "center" class = "firstColumn" style="position:absolute ; top : 3.5cm "><%=carDailyRate %> —Ì«·</div>
	<div id = "row6"  align= "center" class = "firstColumn" style="position:absolute ; top : 4.2cm "><%=carKmDay %> ﬂ„ </div>
	<div id = "row7"  align= "center" class = "firstColumn" style="position:absolute ; top : 4.7cm "><%=carExtraHourRate %> —Ì«·</div>
	<div id = "row8"  align= "center" class = "firstColumn" style="position:absolute ; top : 5.4cm "><%=carExtraKmRate %> Â··Â</div>
	<div id = "row9"  align= "center" class = "firstColumn" style="position:absolute ; top : 6.1cm; height: 1.4cm "><%=conInDate %></div>
	<div id = "row10" align= "center" class = "firstColumn" style="position:absolute ; top : 7.5cm "><%=conInTime %></div>
	<div id = "row11" align= "center" class = "firstColumn" style="position:absolute ; top : 8.2cm "><%=carRrturnKM %> ﬂ„ </div>
	<div id = "row12" align= "center" class = "firstColumn" style="position:absolute ; top : 8.9cm "><%=damgeValue %> —Ì«· </div>
	<div id = "row13" align= "center" class = "firstColumn" style="position:absolute ; top : 9.6cm "><%=conDaysValue %> —Ì«· </div>
	<div id = "row14" align= "center" class = "firstColumn" style="position:absolute ; top : 10.3cm "><%=extraHoursVal %> —Ì«·</div>
	<div id = "row15" align= "center" class = "firstColumn" style="position:absolute ; top : 11cm "><%=extra_km_val %> —Ì«·</div>				
	<div id = "row16" align= "center" class = "firstColumn" style="position:absolute ; top : 11.6cm "><%=total_penalty %> —Ì«·</div>
	<div id = "row17" align= "center" class = "firstColumn" style="position:absolute ; top : 12.2cm "><%=carincome %> —Ì«· </div>
	<div id = "row18" align= "center" class = "firstColumn" style="position:absolute ; top : 12.8cm "><%=totalPayments %> —Ì«·</div>
	<div id = "row19" align= "center" class = "firstColumn" style="position:absolute ; top : 13.4cm "><%=contNeedToCllect %> —Ì«· </div>
	<div id = "row20" align= "center" class = "firstColumn" style="position:absolute ; top : 14.0cm "><%=contNeedToRefund %>  —Ì«· </div>
	
	
	
	<div id = "row4"  align= "center" class = "secondColumn" style="position:absolute ; top : 2.8cm; width:3.3cm"><%=contExpiryDate %></div>
	<div id = "row5"  align= "center" class = "secondColumn" style="position:absolute ; top : 3.5cm "><%=carType%></div>
	<div id = "row6"  align= "center" class = "secondColumn" style="position:absolute ; top : 4.1cm "><%=carCode%></div>
	<div id = "row7"  align= "center" class = "secondColumn" style="position:absolute ; top : 4.7cm "><%=carColor %></div>
	<div id = "row8"  align= "center" class = "secondColumn" style="position:absolute ; top : 5.4cm "><%=carYear %></div>
	<div id = "row9"  align= "center" class = "secondColumn" style="position:absolute ; top : 6.1cm; height:1.4cm "><%=conOutDate %></div>
	<div id = "row10" align= "center" class = "secondColumn" style="position:absolute ; top : 7.5cm "><%=conOutTime %></div>
	<div id = "row11" align= "center" class = "secondColumn" style="position:absolute ; top : 8.2cm "><%=carFmkmreading %> ﬂ„ </div>
	<div id = "row12" align= "center" class = "secondColumn" style="position:absolute ; top : 8.9cm "><%=usedKM %> ﬂ„</div>
	<div id = "row13" align= "center" class = "secondColumn" style="position:absolute ; top : 9.6cm "><%=interval%></div>
	<div id = "row14" align= "center" class = "secondColumn" style="position:absolute ; top : 10.3cm "><%=extra_hours %> ”«⁄…</div>
	<div id = "row15" align= "center" class = "secondColumn" style="position:absolute ; top : 10.9cm "><%=extraKm %> ﬂ„</div>
	<div id = "row16" align= "center" class = "secondColumn" style="position:absolute ; top : 11.5cm "></div>				
	<div id = "row16" align= "center" class = "secondColumn" style="position:absolute ; top : 12.1cm "><%=discount %> —Ì«·</div>
	
	
	<div id="row4" align="center" class="thirdColumn" style="position:absolute ;  top : 2.8cm; width: 6.5cm"><%=custName%></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ;  top : 6.1cm; width: 6.5cm"><%=extraDriverName %></div>
	<div id="row6" align="center" class="thirdColumn" style="position:absolute ; top : 4cm; width: 1.5cm"/><%=custNationality%></div>
	<div id="row6" align="center" class="thirdColumn" style="position:absolute ;  top : 5.4cm; width: 1.5cm"/></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ; top : 7.5cm; width: 1.5cm"/></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ;  top : 8.2cm; width: 8cm"/><%=custOfficeAddress %></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ;  top : 8.9cm; width: 3.5cm"/></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ;  top : 9.6cm; width: 3.5cm"/></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ; top : 11cm; width: 315px; height: 2cm"/></div>
	
	<div id="row9" align="center" class="general" style="position:absolute ; left :15cm; top : 8.9cm; width: 4.0cm"/></div>
	<div id="row9" align="center" class="general" style="position:absolute ; left :15cm; top : 9.6cm; width: 4.0cm"/></div>
	<div id="row5" align="center" class="general" style="position:absolute ; left :16.5cm; top : 3.5cm; width: 2.5cm"><%=idType%></div>
	<div id="row6" align="center" class="general" style="position:absolute ; left :12.2cm; top : 4cm; width: 1.5cm"/><%=idSource%></div>
	<div id="row6" align="center" class="general" style="position:absolute ; left :12.2cm; top : 5.4cm; width: 1.5cm"/><%=licSource %></div>
	<div id="row6" align="center" class="general" style="position:absolute ; left :14cm; top : 4.2cm; width: 2.3cm"/><%=idDateExpire %></div>
	<div id="row6" align="center" class="general" style="position:absolute ; left :14cm; top : 5.4cm; width: 2.3cm"/><%=licDateExpire %></div>
	<div id="row6" align="center" class="general" style="position:absolute ; left :16.5cm; top : 4.2cm; width: 2.5cm"/><%=idNo%></div>
	<div id="row7" align="center" class="general" style="position:absolute ; left :16.5cm; top : 5.4cm; width: 2.5cm"/><%=licNo %></div>
	<div id="row7" align="center" class="general" style="position:absolute ; left :16.5cm; top : 7.5cm; width: 2.5cm"/><%=extra_driver_lic_id %></div>
	<div id="row9" align="center" class="general" style="position:absolute ; left :14cm; top : 7.5cm; width: 84px"/><%=extra_driver_lic_edate %></div>
	<div id="row9" align="center" class="general" style="position:absolute ; left :12.2cm; top : 7.5cm; width: 1.5cm"/></div>
	<div id="row9" align="center" class="general" style="position:absolute ; left :15.2cm; top : 8.8cm; width: 3cm"/><%=office_tel%></div>
	<div id="row9" align="center" class="general" style="position:absolute ; left :15.2cm; top : 9.6cm; width: 3cm"/><%=cust_address%></div>
	<div id="row9" align="center" class="thirdColumn" style="position:absolute ;  top : 9.6cm; width: 3cm"/><%=custMobile%></div>
	
	
	<div id="row9" align="center" class="general" style="position:absolute ; left :11.0cm; top : 20.5cm; width: 5.5cm"/><%=logged_user %></div>
		
	</div>
	
	<div align="right" dir = RTL style="position:absolute ; left :1cm; top : 21cm; width: 651px ; height:5cm" >
		<font size="4" style="BOLD">
		<div dir = "LTR" align="center" class="general" style="position:absolute ; left :6.5cm; top : 0.7cm; width: 5.5cm"/><%=payDate.toUpperCase()%></div>
		<div align="center" class="general" style="position:absolute ; left :2.0cm; top : 0.7cm; width: 5.5cm"/>—ﬁ„ «·«Ì’«· : <%=recepitNo%></div>
		<div align="center" class="general" style="position:absolute ; left :12.5cm; top : 1.8cm; width: 5.5cm"/>##<%=amount%>##</div>
		<div align="center" class="general" style="position:absolute ; left :6.0cm; top : 1.8cm; width: 5.5cm"/>‰Ê⁄ «·≈Ì’«· : <%=drcr%></div>
		<div align="center" class="general" style="position:absolute ; left :3.0cm; top : 3.1cm; width: 11cm"/>###Only <%=spellAmount%>####</div>
		<div align="center" class="general" style="position:absolute ; left :8.0cm; top : 4.0cm; width: 5.5cm"/><%=notes%></div>
		<div align="center" class="general" style="position:absolute ; left :12.0cm; top : 5.0cm; width: 5.5cm"/><%=issuedBy%></div>
		</font>		
		<div align="center" class="general" style="position:absolute ; left :1.0cm; top : 5.5cm; width: 5.5cm"/>	
			<a href = "../../editAndExecute.jsp?id=26158&lookupTableName=LU_QUERIES&ContRowIdValue=<%=rowIdValue%>" title ="Click To Maintain"><font size="1">Maintain Report</font></a>
		</div>

	</div>
	
	
	
	
	
</body>

</html>
