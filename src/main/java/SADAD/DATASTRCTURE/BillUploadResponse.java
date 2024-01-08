package SADAD.DATASTRCTURE;

import SADAD.*;
import org.w3c.dom.*;
import java.sql.*;
import java.net.*;

public class BillUploadResponse extends SADADMessage 
{ 
  public final static String rootName = "BillUploadRs";
  String statusCode="";
  String statusDesc="";
  String severity = "";
  Connection con;
  //
  

   
  public  BillUploadResponse(String XmlFilePath , Connection con,boolean validateSchema) throws Exception
  {
    super(XmlFilePath, con,validateSchema);
    setParameters();
  }
     public BillUploadResponse(URL XmlFileURL , Connection con, boolean validateSchema) throws Exception
  {
    super(XmlFileURL , con,validateSchema);
    setParameters();
  }
  protected void setParameters()
  {
  }
  protected void validate()
  {
    
  }
 
  public void save2DB(SADADService parentService) throws SADADException
  {
    try{
    Statement  stmt = con.createStatement();
    stmt.execute("update bill_upload_process set SADAD_RES_CODE = '"+this.statusCode+"',SADAD_RES_Desc = '"+this.statusDesc+ "' , dummy_col = null where dummy_col = 'Waiting'"); 
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Bill Upload Response in DB "+ e.getMessage(),e,1);
    }
  }
  public static void main(String[] args) 
  {
   try{ 
   Connection con = null;//NCCI.ConnectionFactory.createConnection("192.1.1.7","orac805","compl","compl");
   URL xmlFileURL = new URL("file:///c:/temp/renewal_Data10-04-2004 08-28.xml");
   BillUploadResponse xx= new  BillUploadResponse(xmlFileURL, con,false);
   xx.logAction("Connection created Successfully");   
  
   //xx.save2DB();
  }
   catch(Exception e)    {System.out.print(e.getMessage());}
    
  }
  
}