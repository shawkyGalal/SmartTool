<%@ page  errorPage= "errorPage.jsp"  contentType="text/xml;charset=UTF-8" import = "java.io.*, java.util.*, java.text.*, SADAD.* , Support.Security.*"%>
<% String userdir = "XLSUploading\\Data"; %> 
<jsp:useBean id="upbean" class="oracle.jsp.webutil.fileaccess.HttpUploadBean" >
<jsp:setProperty name="upbean" property="destination" value="<%= userdir %>" />
</jsp:useBean>
<%
  
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
 {
  String dbURL = request.getParameter("dbURL");
 // con = Support.ConnectionFactory.createConnection(dbURL ,"BILLER","BILLER");
 }

      //-----------Saving The client File at the Server--
     upbean.setBaseDir(application, request);
     upbean.upload(request);
     java.util.Enumeration fileNames = upbean.getFileNames();
     //String s = upbean.getParameter("sheetNo");
     String fileName = "";
     while (fileNames.hasMoreElements())
     {
      fileName = fileNames.nextElement().toString();
     }
     //----------------- Reading The XLS File  -----------
     int sheetNo = Integer.parseInt(upbean.getParameter("sheetNo"));
     File xlsFile = new File (session.getServletContext().getRealPath("")+"\\"+fileName);
     Support.XLSUploading.XLSUpload  xLSUpload = new Support.XLSUploading.XLSUpload(xlsFile ,sheetNo );
     Vector[] xlsData = xLSUpload.getXLSData();
     int headerRowsNo = 6;
     String Sender = xlsData[0].elementAt(1).toString();
     String ServiceType = xlsData[1].elementAt(1).toString();
     String Timestamp = xlsData[2].elementAt(1).toString();
     
     
  String RqUID = SADAD.DATASTRCTURE.SADADMessage.getUUID();
  int billRecCount = 0;
  SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String stringToBeDigitallySigned="";
  int counter = 1;
%>
<?xml version='1.0' encoding='UTF-8'?>
<SADAD>
  <SignonRq>
    <ClientDt><%=df.format(new java.util.Date()).replace(' ','T')%></ClientDt>
    <SignonProfile>
      <Sender><%=Sender%></Sender> 
      <Receiver>SADAD</Receiver>
      <MsgCode>BUPLRQ</MsgCode>
    </SignonProfile>
  </SignonRq>
  <PresSvcRq>
    <RqUID><%=RqUID%></RqUID>
    <BillUploadRq>
        <ServiceType><%=ServiceType%></ServiceType>
        <Timestamp><%=df.format(new java.util.Date()).replace(' ','T')%></Timestamp>
        <%
      for (int i= headerRowsNo; i<xlsData[0].size() ; i++)  
      { 
       String BillStatusCode = xlsData[0].elementAt(i).toString();
       String OfficialId = xlsData[1].elementAt(i).toString();
       String OfficialIdType = xlsData[2].elementAt(i).toString();
       String LanguagePref = xlsData[3].elementAt(i).toString();
       String Text = xlsData[4].elementAt(i).toString();
       String BillCategory = xlsData[5].elementAt(i).toString();
       String ServiceType2 = xlsData[6].elementAt(i).toString();
       String BillCycle = xlsData[7].elementAt(i).toString();
       String BillNumber = xlsData[8].elementAt(i).toString();
       String BillingAcct = xlsData[9].elementAt(i).toString();       
       String BillerId = xlsData[10].elementAt(i).toString();       
       String AmountDue = xlsData[11].elementAt(i).toString();
       String DueDt = xlsData[12].elementAt(i).toString();      
       String ExpDt = xlsData[13].elementAt(i).toString();      
       String BillRefInfo = xlsData[14].elementAt(i).toString();
       String MinAdvancePmt = xlsData[15].elementAt(i).toString();
       String MaxAdvancePmt = xlsData[16].elementAt(i).toString();
       String MinPartialPmt = xlsData[17].elementAt(i).toString();
     %>  
        <BillRec>
          <BillStatusCode><%=BillStatusCode%></BillStatusCode> 
          <%if (! OfficialId.equals("")) { %>
          <CustId>
            <OfficialId><%=OfficialId%></OfficialId>
            <%if (! OfficialId.equals("")) { %> <OfficialIdType><%=OfficialIdType%></OfficialIdType> <%}%>
          </CustId>
         <%}%> 
         <DisplayLabel>
          <LanguagePref><%=LanguagePref%></LanguagePref> 
          <Text><%=Text%></Text> 
        </DisplayLabel>
          <BillInfo>
            <BillCategory><%=BillCategory%></BillCategory> 
            <ServiceType><%=ServiceType2%></ServiceType>
            <%if (! BillCycle.equals("")) { %><BillCycle><%=BillCycle%></BillCycle> <%}%>
            <%if (! BillNumber.equals("")) { %><BillNumber><%=BillNumber%></BillNumber> <%}%>
            <BillingAcct><%=BillingAcct%></BillingAcct> 
            <BillerId><%=BillerId%></BillerId> 
            <AmountDue><%=AmountDue%></AmountDue> 
            <DueDt><%=DueDt%></DueDt> 
            <%if (! ExpDt.equals("")) { %><ExpDt><%=ExpDt%></ExpDt>  <%}%>
            <BillRefInfo><%=BillRefInfo%></BillRefInfo>
            <%
            if (! MinAdvancePmt.equals(""))
            {
            %>
            <BillSummAmt>
                <CurAmt><%=MinAdvancePmt%></CurAmt> 
                <BillSummAmtCode>MinAdvancePmt</BillSummAmtCode> 
                <BillSummAmtType>Supplemental</BillSummAmtType> 
            </BillSummAmt>
            <%
            }
            if (! MaxAdvancePmt.equals(""))
            {
            %>
            <BillSummAmt>
                <CurAmt><%=MaxAdvancePmt%></CurAmt> 
                <BillSummAmtCode>MaxAdvancePmt</BillSummAmtCode> 
                <BillSummAmtType>Supplemental</BillSummAmtType> 
            </BillSummAmt>
            <%
            }
            if (! MinPartialPmt.equals(""))
            {
            %>
            <BillSummAmt>
                <CurAmt><%=MinPartialPmt%></CurAmt> 
                <BillSummAmtCode>MinPartialPmt</BillSummAmtCode> 
                <BillSummAmtType>Supplemental</BillSummAmtType> 
            </BillSummAmt>
            <%
            }%>
          </BillInfo>
          
        </BillRec>
      <% 
      }
  %>   
  </BillUploadRq>
 </PresSvcRq>
</SADAD>