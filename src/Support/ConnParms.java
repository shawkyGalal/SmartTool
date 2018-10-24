package Support;

import java.sql.*;
import org.w3c.dom.*;
import java.util.Vector;
public class ConnParms 
{
  public String type = "";
  private static final String typeNode = "TYPE";
  
  public String dbType = "ORACLE";
  private static final String dbTypeNode = "DBTYPE";
  

  public String odbcDataSource = "";
  private static final String odbcDataSourceNode = "ODBCDATASOURCE";

  public String name = "";
  private static final String nameNode = "NAME";

  public String server = "";
  private static final String serverNode = "SERVER";

  public String serviceName = "";
  private static final String serviceNameNode = "SERVICENAME";
  
  public String wdServer = "";
  private static final String WDserverNode = "WDSERVER";
  

  public String active = "";
  private static final String activeNode  = "ACTIVE";

  public String userName = "";
  private static final String userNameNode  = "USERNAME";

  public String password = "";
  private static final String passwordNode  = "PASSWORD";
  
  private Vector<SqlBindVar> sqlBindVars  = new Vector<SqlBindVar>();
  public Vector<SqlBindVar> getSqlBindVars() {
	return sqlBindVars;
}

private static final String sqlBindVarNode  = "SQLBINDVAR";
  
  public String url = "";
  private static final String urlNode  = "URL";
  
  public String driver = "";
  private static final String driverNode  = "DRIVER";
  

  public String maxPoolSize = "0";
  private static final String maxPoolSizeNode  = "MAX_POOL_SIZE";

  public String minPoolSize = "0";
  private static final String minPoolSizeNode  = "MIN_POOL_SIZE";
  
  public String waitTimeOut = "5000";
  private static final String waitTimeOutNode  = "WAIT_TIMEOUT";
  

  
  public Connection generateOciConnection(String m_userName , String m_password ) throws Exception
  {
    Connection con = null;
    con = ConnectionFactory.createOciConnection(this.serviceName ,m_userName,m_password);
    return con;
   
  }
  
  public Connection generateConnection(String m_userName , String m_password , String connectAs ) throws Exception
  {
    Connection con = null;
    
    
       if (this.type.toUpperCase().equals("ODBC"))
        {
          con = Support.ConnectionFactory.createODBCConnection(this.odbcDataSource, m_userName,m_password);
        }
       else if (this.type.toUpperCase().equals("JDBC"))
        {
          if (connectAs.equals("SYSDBA"))
          {
          con = ConnectionFactory.createConnectionAsSysDBA(this.url, this.driver ,m_userName,m_password);
          }
          else 
          {
            con = Support.ConnectionFactory.createConnectionByUrl(this.url, this.driver ,m_userName,m_password);
            
          }
        }
        
        return con;
   
  }
  public Connection generateConnection( ) throws Exception
  {
	  Connection con = null ;
	  if (this.type.toUpperCase().equals("ODBC"))
      {
        con = Support.ConnectionFactory.createODBCConnection(this.odbcDataSource, this.userName,this.password);
      }
	  else if (this.type.toUpperCase().equals("JDBC"))
	  {
		  con = ConnectionFactory.createConnectionByUrl(this.url,this.driver,this.userName, this.password );
	  }
    return con ; 
  }
  public ConnParms(Node conneParmNode) throws Exception
  {
    NodeList childNodes = conneParmNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean nameFound= false;
    boolean serverFound = false;
    boolean serviceNameFound = false;
    boolean WDserverFound = false;
    boolean activeFound = false;
    boolean userNameFound = false;
    boolean passwordFound = false;
    boolean typeFound = false;
    boolean odbcDataSourceFound = false;
    boolean dbTypeFound = false;
    boolean urlFound = false;
    boolean driverFound = false;
    boolean maxPoolSizeFound = false; 
    boolean minPoolSizeFound = false;
    boolean waitTimeOutFound = false;
    
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(sqlBindVarNode)  )
              {
                sqlBindVars.addElement(new Support.SqlBindVar(firstLevelNode ));
              }
     
          {
                NodeList secondLeveNodes = firstLevelNode.getChildNodes();
                int size2 = secondLeveNodes.getLength();
                for (int j=0 ; j< size2; j++)
                {
                  Node secondLevelNode = secondLeveNodes.item(j);
                  nodeName2  = secondLevelNode.getNodeName();
                  nodeType2  = secondLevelNode.getNodeType();
                  nodeValue2 = secondLevelNode.getNodeValue();
                  
                  if ( nodeType2 ==Node.TEXT_NODE )
                  {
                      if ( ! typeFound  &&  nodeName1.toUpperCase().equals(typeNode))
                      {
                        this.type = nodeValue2;
                        typeFound = true;
                        continue;
                      }

                      if ( ! odbcDataSourceFound  &&  nodeName1.toUpperCase().equals(odbcDataSourceNode))
                      {
                        this.odbcDataSource = nodeValue2;
                        odbcDataSourceFound = true;
                        continue;
                      }
                      
                      
                      if ( ! nameFound  &&  nodeName1.toUpperCase().equals(nameNode))
                      {
                        this.name = nodeValue2;
                        nameFound = true;
                        continue;
                      }
                      
                      if (! serverFound &&  nodeName1.toUpperCase().equals(serverNode))
                      {
                        this.server = nodeValue2;
                        serverFound = true;
                      }
                      if (! serviceNameFound &&  nodeName1.toUpperCase().equals(serviceNameNode))
                      {
                        this.serviceName = nodeValue2;
                        serviceNameFound = true;
                      }
                      if (! WDserverFound &&  nodeName1.toUpperCase().equals(WDserverNode))
                      {
                        this.wdServer = nodeValue2;
                        WDserverFound = true;
                      }
                      if (! activeFound &&  nodeName1.toUpperCase().equals(activeNode))
                      {
                        this.active = nodeValue2;
                        activeFound = true;
                      }
//----------------------------------------------
                     if (! userNameFound &&  nodeName1.toUpperCase().equals(userNameNode))
                      {
                        this.userName = nodeValue2;
                        userNameFound = true;
                      }
                      if (! passwordFound &&  nodeName1.toUpperCase().equals(passwordNode))
                      {
                        this.password = nodeValue2;
                        passwordFound = true;
                      }
                      if (! dbTypeFound &&  nodeName1.toUpperCase().equals(dbTypeNode))
                      {
                        this.dbType = nodeValue2;
                        dbTypeFound = true;
                      }

                      if (! urlFound &&  nodeName1.toUpperCase().equals(urlNode))
                      {
                        this.url = nodeValue2;
                        urlFound = true;
                      }
                      if (! driverFound &&  nodeName1.toUpperCase().equals(driverNode))
                      {
                        this.driver = nodeValue2;
                        driverFound = true;
                      }

                      if (! maxPoolSizeFound &&  nodeName1.toUpperCase().equals(maxPoolSizeNode))
                      {
                        this.maxPoolSize = nodeValue2;
                        maxPoolSizeFound = true;
                      }

                      if (! minPoolSizeFound &&  nodeName1.toUpperCase().equals(minPoolSizeNode))
                      {
                        this.minPoolSize = nodeValue2;
                        minPoolSizeFound = true;
                      }

                      if (! waitTimeOutFound &&  nodeName1.toUpperCase().equals(waitTimeOutNode))
                      {
                        this.waitTimeOut = nodeValue2;
                        waitTimeOutFound = true;
                      }
                      
                                      
                  }
                  
                 }
    
           }
         }
    //validate();
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}

  }
  public static void main(String[] args ) throws Exception
  {
  try{
  Support.XMLConfigFileReader supportConfig = Misc.getXMLConfigFileReader(false ) ;  
  java.util.Vector conParms  = supportConfig.connParms ;
  System.out.print("sss");
  }
  catch (Exception e ){System.out.print(e.getMessage());}
  
  }

public int getMaxPoolSize() {
	return Integer.parseInt(this.maxPoolSize);
}

public int getMinPoolSize() {
	return Integer.parseInt(this.minPoolSize);
}

public long getWaitTimeOut() {
	return Integer.parseInt(this.waitTimeOut);
}
}