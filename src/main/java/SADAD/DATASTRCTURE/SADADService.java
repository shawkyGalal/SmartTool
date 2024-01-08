package SADAD.DATASTRCTURE;

import java.io.*;
import java.text.*;
import java.util.*;
import Support.*;
import SADAD.*;
import java.net.*;
import org.w3c.dom.*;
import java.sql.*;

public class SADADService extends SADADMessage
{
  public SignonRq signonRq;
  private final static String SignonRqNodeName = "SIGNONRQ";
  
  public SignonRs signonRs;
  private final static String SignonRsNodeName = "SIGNONRS";

  
  //---------------all of the following should be null except only one ---------------------
  public PresSvcRq presSvcRq;
  private final static String PresSvcRqNodeName = "PRESSVCRQ";
  
  public PresSvcRs presSvcRs;
  private final static String PresSvcRsNodeName = "PRESSVCRS";

    
  public BillerSvcRq billerSvcRq;
  private final static String billerSvcRqNodeName = "BILLERSVCRQ";
  
  public BillerSvcRs billerSvcRs;
  private final static String billerSvcRsNodeName = "BILLERSVCRS" ;
  
  public BankSvcRq bankSvcRq;
  private final static String bankSvcRqNodeName = "BANKSVCRQ";

  public SARIERq sarieRq;
  private final static String sarieRqName = "SARIERQ";

  
  //public XxxxSvcRq xxxxSvcRq;
  private final static String xxxxSvcRqNodeName = "XXXXSVCRQ";
  
  //----------------------------------------------------------------
  public Vector signatures = new Vector();
  private final static String SignatureNodeName = "SIGNATURE";
  
  public String fileName;
  public String serviceType;
  
  public SADADService(URL theURL  , Connection m_con, boolean validateSchema) throws Exception
  {
    super(theURL, m_con,validateSchema);
    setParameters(docRootNode );
    this.fileName=theURL.toString();
  }
  /*
   *  Used in case of reading parameters from XML Node and writing to DB or vice vesa
   */
  public SADADService (Node messageNode, Connection m_con) throws Exception
  {
   super(messageNode,m_con);
   setParameters(docRootNode);
  }
  /*
   *  Used in case of reading parameters from XML Node
   */
  public SADADService (Node messageNode) throws Exception
  {
   super(messageNode);
   setParameters(docRootNode );
  }

  /*
   * Used in case of reading parameters from xml file
   */
  public  SADADService(InputStream is ,boolean validateSchema , Connection m_con) throws Exception
  {
  super(is,validateSchema,m_con);
  setParameters(docRootNode);
  }
  public  SADADService(String xmlString ,boolean validateSchema , Connection m_con) throws Exception
  {
  
  super(xmlString,validateSchema,m_con);
  this.con= m_con;
  setParameters(docRootNode);
 
  }
  public SADADService(String xmlFilePath  , Connection m_con, boolean validateSchema) throws Exception
  {
    super(xmlFilePath,m_con,validateSchema);
    setParameters(docRootNode);
    this.fileName= xmlFilePath;
  }
    
     
public void setParameters(Node serviceNode) throws Exception
{
    NodeList childNodes = serviceNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
        
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(SignonRqNodeName)  )
              {
                this.signonRq = new SignonRq(firstLevelNode);
                continue;
              }
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(SignonRsNodeName)  )
              {
                this.signonRs = new SignonRs(firstLevelNode);
                continue;
              }
     
          else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PresSvcRqNodeName)  )
              {
                this.presSvcRq= new PresSvcRq(firstLevelNode , this.con );
                serviceType = PresSvcRqNodeName;
                continue;
              }
          else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PresSvcRsNodeName)  )
              {
                presSvcRs= new PresSvcRs(firstLevelNode , this.con );
                serviceType = PresSvcRsNodeName;
                continue;
              }
              

           else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billerSvcRqNodeName)  )
              {
                this.billerSvcRq= new BillerSvcRq(firstLevelNode , this.con );
                serviceType = billerSvcRqNodeName;
                continue;
              }

           else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(this.bankSvcRqNodeName)  )
              {
                this.bankSvcRq= new BankSvcRq(firstLevelNode , this.con );
                serviceType = bankSvcRqNodeName;
                continue;
              }
           else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(this.billerSvcRsNodeName)  )
              {
                this.billerSvcRs= new BillerSvcRs(firstLevelNode , this.con );
                serviceType = billerSvcRsNodeName;
                continue;
              }
                            
              
           else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(SignatureNodeName)  )
              {
                this.signatures.addElement(new Signature(firstLevelNode));
                continue;
              }
           else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(sarieRqName)  )
              {
                this.sarieRq = new SARIERq(firstLevelNode);
                continue;
              }
             
              
         }
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }

  public SADADService sendToSADAD(String url) throws Exception
  {

    boolean sendResult = true;
    String billUpoladURL = "";
    String response= "";
    SADADService responseMessage;
    //---------2--Send To SADAD-------
    try
    {
     //---------------2.2-----Send ----------
     HttpPoster httpPoster = new  HttpPoster(url,"", "userName", "pass");
     String messageAsString =  this.toXMLString();
     httpPoster.postRequest(messageAsString);
     response = httpPoster.readResponse();
    }
    catch (Exception e)
    {
    throw new Exception ("Unable to send message to SADAD ("+billUpoladURL+") Due to the following Exception " + e.getMessage());
    }
    
    try{
       // Parseing the response Mesage as a SADAD Service---
       responseMessage = new SADADService(response,true,con);
        }
    catch(Exception e) { throw new Exception("Unable to Parse the Response Message Received From SADAD : \nResponse Message : \n" + response +"\n Due to the following Exception\n " + e.getMessage());}
    
     
   return responseMessage;
  }
  public void saveInSFS(String applicationPath  ) throws Exception
  {
    String fileName = applicationPath +"\\SADAD_Messages\\";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    String timeStamp = sdf.format(new java.util.Date());
    if (this.signonRq != null)
    {
     if (this.signonRq.getSignonProfile().getSender().toUpperCase().indexOf("SADAD") != -1)
      { fileName  += "FromSADAD\\" + this.signonRq.getSignonProfile().getMsgCode();}
    else
      { fileName  += "ToSADAD\\" + this.signonRq.getSignonProfile().getMsgCode();}
      
      fileName += "\\" + this.signonRq.getSignonProfile().getMsgCode() + timeStamp + ".xml" ;
    }
    
    if (this.signonRs != null)
    {
     if (this.signonRs.signonProfile.getSender().toUpperCase().indexOf("SADAD") != -1)
      { fileName  += "FromSADAD\\" + this.signonRs.signonProfile.getMsgCode() ;}
    else
      { fileName  += "ToSADAD\\" + this.signonRs.signonProfile.getMsgCode();}
      
      fileName += "\\" + this.signonRs.signonProfile.getMsgCode() + timeStamp + ".xml" ;
    }

    try { 
    new Misc().saveStringToFile(fileName  ,this.toXMLString());
    }
    catch (Exception e ) { throw new SADADException("Unable to Save the message in File System due to " + e.getMessage(),e,0);}
  
  }
  public void saveInDb() throws Exception
  
  {
    if (this.presSvcRq != null)
    {
        for (int i=0; i< this.presSvcRq.messageRequests.size() ; i++)
        {
          if (this.presSvcRq.messageRequests.elementAt(i) instanceof BillUploadRq)
          {
            BillUploadRq burq = (BillUploadRq) this.presSvcRq.messageRequests.elementAt(i);
            burq.saveInDB(this);
          }
          else if (this.presSvcRq.messageRequests.elementAt(i) instanceof BillConfirmationRq) 
          {
           BillConfirmationRq bcrq  = (BillConfirmationRq) this.presSvcRq.messageRequests.elementAt(i);
           bcrq.save2DB(this);
          }
          else {throw new Exception("Shawky: Message Type is not defined or Uploading into DB is not Yet implemented for this Message Type ");}     
        }
    }
    else if (this.billerSvcRq != null)
    {
        for (int i=0; i< this.billerSvcRq.messageRequests.size() ; i++)
        {
         if (this.billerSvcRq.messageRequests.elementAt(i) instanceof PmtUploadRq)
          {
            PmtUploadRq pur = (PmtUploadRq) this.billerSvcRq.messageRequests.elementAt(i);
            pur.save2DB(this);
          }
         else if (this.billerSvcRq.messageRequests.elementAt(i) instanceof BillerReconUploadRq)
          {
            BillerReconUploadRq brur = (BillerReconUploadRq) this.billerSvcRq.messageRequests.elementAt(i);
            brur.save2DB(this);
          }
         else if (this.billerSvcRq.messageRequests.elementAt(i) instanceof PmtNotifyRq)
          {
            PmtNotifyRq pnr = (PmtNotifyRq) this.billerSvcRq.messageRequests.elementAt(i);
            pnr.save2DB(this);
          }
          
         else {throw new Exception("Shawky: Message Type is not defined or Uploading into DB is not Yet implemented for this Message Type ");}     
          
        }
    }
    else if (this.presSvcRs != null)
    {
      if (this.presSvcRs.messageRequests.elementAt(0) instanceof BillUploadResponse )
      {
        BillUploadResponse billUploadResponse = (BillUploadResponse) this.presSvcRs.messageRequests.elementAt(0);
        billUploadResponse.save2DB(this);
      }
      //throw new Exception("Saving PresSvcRs in DB not yet implemented");
    }
    else 
    {throw new Exception("No Action yet implemented");}
    
  }
 


  public static String toSqlDate(String xmlDate)
   {
     String sqlDate= " to_date('"+xmlDate.substring(0,10) +" "
                                 + xmlDate.substring(11) 
                                 + "','YYYY-MM-DD HH24:MI:SS') ";
     return sqlDate;
   }
 public String toXMLString() throws Exception
 {
   
   String xmlString = "<?xml version='1.0' encoding='UTF-8'?>";
   xmlString += "\n " + "<SADAD>";
   if (signonRq != null )
   { xmlString += "\n\n " + this.signonRq.toXMLString();}
   if (signonRs != null )
   { xmlString += "\n\n " + this.signonRs.toXMLString();}
   
   if( billerSvcRq != null ) 
   { xmlString += "\n\n " + this.billerSvcRq.toXMLString(); 
   }
  
   if (this.presSvcRq != null  )
   {
   xmlString += "\n " + this.presSvcRq.toXMLString();
   }

  if (this.presSvcRs != null  )
   {
   xmlString += "\n " + this.presSvcRs.toXMLString();
   }

  if (this.billerSvcRs != null  )
   {
   xmlString += "\n " + this.billerSvcRs.toXMLString();
   }

   xmlString += "\n </SADAD>";
   return xmlString;
 
 }
 public String getRUID()
 {
  String ruid= null;
      if (this.presSvcRq != null)
      {ruid = this.presSvcRq.RqUID;}
      else if (this.presSvcRs != null)
      {ruid = this.presSvcRs.RqUID;}
      else if (this.bankSvcRq != null)
      {ruid = this.bankSvcRq.RqUID;}
      else if (this.billerSvcRq!= null)
      {ruid = this.billerSvcRq.RqUID;}
      else if (this.billerSvcRs !=null)
      {ruid = this.billerSvcRs.RqUID;}
      return ruid;
 }
 
   public  static void main(String[] args)
   {
    
    try{
     
      // Connection con = ConnectionFactory.createConnection("130.1.2.2","edgedev","SADAD","dev2004");
      String   userName = "utadminxx";
      String   password = "riyadh123";
       
      Support.DownLoad.MyAuthenticator auth = new Support.DownLoad.MyAuthenticator(userName,password);
      java.net.Authenticator.setDefault(auth);  
      
     SADADService ss = new SADADService("file:\\\\10.16.17.51\\sfs\\bank\\SAMBSARI\\download\\SAMBSARI-BKRCRQ-20060402-1.xml", null, false);
     //ss.saveInSFS("D:\\MyWork\\SADAD\\public_html");
     System.out.print("asdasd");
    // billerReconUploadRq.saveInDb();
     }
     catch (Exception e ){ e.printStackTrace();  }
     //----------------2.1----Getting The Bill Upload URL From DB-------
    /*
     String billUpoladURL = SADADSettings.getSystemParmValue("billUploadEndPoint", con);
    
    try{
     SADADService billUploadRs =   billerReconUploadRq.sendToSADAD(billUpoladURL);
     if (billUploadRs.presSvcRs.status.statusCode.equals("0"))
     {
        System.out.print(billUploadRs.toXMLString());  
        //con.commit();
     }
     System.out.print("");
     
     }
     catch (Exception e ){e.printStackTrace(); throw e;}
   */
     System.out.print("");
   
 
     
   }
  

  
}
  