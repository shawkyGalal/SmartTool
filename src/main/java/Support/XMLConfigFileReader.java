package Support;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.util.*;
//import javax.xml.parsers.*;
//import oracle.jdbc.pool.OracleConnectionCacheImpl;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.w3c.dom.*;

import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.configuration.ReadEnvironment;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.web.swaf.SWAF;
import com.smartValue.web.listners.SmartToolContextListener;

import connectionPoolManager.SmartValueConnectionCashImpl;


public class XMLConfigFileReader
{
public Vector<ConnParms> connParms = new Vector<ConnParms>();
private String connParmNodeName= "CONNECTION";
public Vector<SqlBindVar>  sqlBoundVars = new Vector<SqlBindVar>();

public ConnParms reposatoryConn ;
private String reposatoryConnName= "REPOSATORYCONN";

//private String sqlBoundVarNodeName= "SQLBOUNDVAR"; 
private URL configURL = null;
protected Node docRootNode ;

private static	SmartValueConnectionCashImpl repConnPool = null;

@SuppressWarnings("deprecation")
public static SmartValueConnectionCashImpl getRepostoryConnectionPool() throws Exception
{
	if (repConnPool == null)
	{
		OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
		
	    Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ; 
		ocpds.setURL(supportConfig.reposatoryConn.url ); 
		ocpds.setUser(supportConfig.reposatoryConn.userName);
		ocpds.setPassword(supportConfig.reposatoryConn.password);
		ocpds.setImplicitCachingEnabled(true);
		repConnPool = new SmartValueConnectionCashImpl(ocpds);
		//repConnPool.setMaxLimit(supportConfig.reposatoryConn.getMaxPoolSize());
		//repConnPool.setMinLimit(supportConfig.reposatoryConn.getMinPoolSize());
		//repConnPool.setCacheScheme(OracleConnectionCacheImpl.FIXED_WAIT_SCHEME);
		//repConnPool.setCacheFixedWaitTimeout(supportConfig.reposatoryConn.getWaitTimeOut());
		
		//repConnPool.setCacheInactivityTimeout((long) 0.5);
		//repConnPool.setCacheFixedWaitIdleTime((long) 0.01);
		
		
	}
	return repConnPool;
}

public ConnParms getConnParmsByName(String m_name)
{
  ConnParms x= null;;
  for (int i = 0 ; i<this.connParms.size() ; i ++)
  {
    x = (ConnParms) connParms.elementAt(i);
    if (x.name.trim().toUpperCase().equals(m_name.toUpperCase()))
    {
      break;
    }
  }
 return x;
}
  
    /*
   * Used in case of reading parameters from URL
   */
  public XMLConfigFileReader(URL theURL,boolean validateSchema) throws Exception
  {
    Support.Validator1.checkExpiry();
    configURL = theURL ;//.forName("Support.XMLSupportConfig").getClassLoader().getResource("Support/config_support.xml");
    setRootNode(validateSchema);
    setParameters();
  }
 
  /*
   * uses the xml file located with this Class File (in the same folder) 
   */
  public XMLConfigFileReader( boolean validateSchema ) throws Exception
  { 
    Validator1.checkExpiry();
    try{
    	
    //URL x= getConnectionsFile() ; //Class.forName("Support.XMLConfigFileReader").getClassLoader().getResource("");
    configURL            = getConnectionsFile() ; // Class.forName("Support.XMLConfigFileReader").getClassLoader().getResource("Support/Connections_config.xml");
    System.out.println(" ========DB Connection  File : " + this.configURL);    
    }
    catch (Exception e ) 
    {
    	e.printStackTrace();
    	throw new Exception("Unable to Find the Connection Configuration file Support/Connections_config.xml");
    
    }
    setRootNode(validateSchema);
    setParameters();
  }

  public static String getConfigurationHome() throws MalformedURLException {
		
	  String configSubDir = null  ; 
		try{
			configSubDir = SWAF.getConfigurationSubDirectory() ; 
		}
		catch (Exception e){
			configSubDir ="smartTool" ;
		}
	  return    Configuration.getConfigurationHome() + File.separator + configSubDir ; 
	}
	private static URL getConnectionsFile() throws MalformedURLException {
		return SmartToolContextListener.getConnectionsFile(); 
		//return  new URL ("file://" +((isWindowsOS())?"/":"") +getConfigurationHome() +File.separator + "Connections_config.xml") ; 
	}

private static boolean isWindowsOS()
{
	boolean result = false ; 
	String osName = System.getProperty("os.name").toLowerCase();
	//Console.log("Operating System Name :" +osName , ReadEnvironment.class);
	if (osName.indexOf("windows") > -1) result = true;
	else result = false;
	return result ; 
}
  private void setRootNode( boolean validateSchema)  throws Exception
 {    
     
      javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(false);
      dbf.setValidating(false);
     
      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
      //Logger log = new Logger("C:\\temp");
      //log.logMessage("ConfigURL= " + configURL.toString());
      InputStream is = configURL.openStream();
      try{
      Document doc = db.parse(is);
      is.close();
      this.docRootNode = doc.getFirstChild();
        try 
        {
        Node docRootNodex = docRootNode.getNextSibling();
        docRootNodex.getChildNodes() ;
        docRootNode  = docRootNodex;
        }
        catch (Exception e){}
        
      }
      catch (Exception e )
      {
       is.close();
       throw e;
      }
      
 }
 private void setParameters() throws Exception
 {
    NodeList childNodes = this.docRootNode.getChildNodes();
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
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(connParmNodeName)  )
              {
                this.connParms.addElement(new ConnParms(firstLevelNode));
                continue;
              }
 
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(reposatoryConnName)  )
              {
                this.reposatoryConn = new ConnParms(firstLevelNode);
                continue;
              }

              
         }
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}

   
 }
 private StringBuffer getXML()
 {
  StringBuffer sb = new StringBuffer();
  sb.append("<?xml version='1.0' encoding='UTF-8'?>");
  sb.append("\n<Configuration>");
  ConnParms thisParms = null; 
   
   for(int i = 0 ; i< this.connParms.size(); i++)
   {
   thisParms = (ConnParms) this.connParms.elementAt(i);
      sb.append("\n\t<Connection>");
      sb.append("\n\t\t<TYPE>"+thisParms.type+"</TYPE>");
      sb.append("\n\t\t<ODBCDATASOURCE>"+thisParms.odbcDataSource+"</ODBCDATASOURCE>");
      sb.append("\n\t\t<Name>"+thisParms.name+"</Name>");
      sb.append("\n\t\t<Server>"+thisParms.server+"</Server>");
      sb.append("\n\t\t<ServiceName>"+thisParms.serviceName+"</ServiceName>");
      sb.append("\n\t\t<Active>"+thisParms.active+"</Active>");
     sb.append("\n\t</Connection>");   
   }
   sb.append("\n\t<REPOSATORYCONN>");
      sb.append("\n\t\t<TYPE>"+reposatoryConn.type+"</TYPE>");
      sb.append("\n\t\t<Name>"+this.reposatoryConn.odbcDataSource+"</Name>");
      sb.append("\n\t\t<Name>"+this.reposatoryConn.name+"</Name>");
      sb.append("\n\t\t<Server>"+reposatoryConn.server+"</Server>");
      sb.append("\n\t\t<ServiceName>"+reposatoryConn.serviceName+"</ServiceName>");
      sb.append("\n\t\t<Active>"+reposatoryConn.active+"</Active>");
      sb.append("\n\t\t<userName>"+reposatoryConn.userName+"</userName>");
      sb.append("\n\t\t<password>"+reposatoryConn.password+"</password>");
   sb.append("\n\t</REPOSATORYCONN>");
  
  sb.append("\n</Configuration>");
  
  return sb;
 }

 public void save() throws Exception
 {
  StringBuffer sb = this.getXML();
  FileWriter fw = null;
  try{
  fw = new FileWriter(this.configURL.getFile());
  fw.write(sb.toString());
  }
  catch ( Exception e) {throw new Exception ("Unable to Save To File " + this.configURL.getFile() + "Due to the following Exception : " +e.getMessage());}
  fw.close();
 }  
   public  static void main(String[] args)
   {
    
    try{
    
     SmartValueConnectionCashImpl repConPool = XMLConfigFileReader.getRepostoryConnectionPool();
     Connection con1 = repConPool.getConnection();
     repConPool.printInfor();
     Connection con2 = repConPool.getConnection();
     repConPool.printInfor();     
     Connection con3 = repConPool.getConnection();
     repConPool.printInfor();  
     //con3.close();
 	 
     Connection con4 = repConPool.getConnection();
     repConPool.printInfor();     

     Connection con5 = repConPool.getConnection();
     repConPool.printInfor();     
     
    }
    catch(Exception e){e.printStackTrace();}
     
   }
 

   

}
