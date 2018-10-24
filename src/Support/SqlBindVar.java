package Support;


import java.sql.*;
import org.w3c.dom.*;
import java.util.Vector;
public class SqlBindVar 
{
  public String bindVarName = "";
  private static final String bindVarNameNode = "BINDVARNAME";

  public String bindVarValue = "";
  private static final String bindVarValueNode = "BINDVARVALUE";




  public SqlBindVar(Node conneParmNode) throws Exception
  {
    NodeList childNodes = conneParmNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean bindVarNameFound= false;
    boolean bindVarValueFound = false;
 
    
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
     
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
                      
                      if ( ! bindVarNameFound  &&  nodeName1.toUpperCase().equals(bindVarNameNode))
                      {
                        this.bindVarName = nodeValue2;
                        bindVarNameFound = true;
                        continue;
                      }

                      if ( ! bindVarValueFound  &&  nodeName1.toUpperCase().equals(bindVarValueNode))
                      {
                        this.bindVarValue = nodeValue2;
                        bindVarValueFound = true;
                        continue;
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
}



