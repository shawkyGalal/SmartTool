package SADAD.SAMA_SIMULATOR;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;
/*
 * This Class Simulates reciving Bills at the SADAD Server
 */
public class BillReciver extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    InputStream is = request.getInputStream();
    String requestStr = "";
    int c = 0;
    while( (c= is.read()) != -1)
    {
    requestStr += (char)c;
    }
      
    PrintWriter out = response.getWriter();
    out.println("<?xml version=\"1.0\"?>");
    out.println("<SADAD>");
    out.println("  <SignonRs>");
    out.println("    <ClientDt>2004-10-04T06:00:51</ClientDt>");
    out.println("    <ServerDt>2004-10-04T06:00:16</ServerDt>");
    out.println("    <LanguagePref>en-gb</LanguagePref>");
    out.println("    <SignonProfile>");
    out.println("      <Sender>SADAD-001</Sender>");
    out.println("      <Receiver>003</Receiver>");
    out.println("      <MsgCode>BUPLRS</MsgCode>");
    out.println("    </SignonProfile>");
    out.println("  </SignonRs>");
    out.println("  <PresSvcRs>");
    out.println("    <Status>");
    out.println("      <StatusCode>0</StatusCode>");
    out.println("      <ShortDesc>Success</ShortDesc>");
    out.println("      <Severity>Info</Severity>");
    out.println("    </Status>");
    out.println("    <RqUID>99a82b54-15b1-11d9-940f-000bcdb526f0</RqUID>");
    out.println("    <BillUploadRs>BUPLRS</BillUploadRs>");
    out.println("  </PresSvcRs>");
    out.println("</SADAD>");
    out.close();
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>ReciveBills</title></head>");
    out.println("<body>");
    out.println("<p>The servlet has received a Get. This is the reply.</p>");
    out.println("</body></html>");
    out.close();
    
  }
  
}