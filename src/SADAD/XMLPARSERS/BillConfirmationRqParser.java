package SADAD.XMLPARSERS;
import SADAD.*;
import java.sql.*;
import org.w3c.dom.*;

public class BillConfirmationRqParser extends BasicXMLParser 
{
  public final static String rootName = "BillConfirmationRq";
  
  public BillConfirmationRqParser(String XmlFilePath , Connection con) throws Exception
  {
    super(XmlFilePath, rootName , con);
    
    NodeList childNodes = docRootNode.getChildNodes();
    //---------Start Parsing an XML Doc-------
    //-------
  }
  
    public void save2DB() throws SADADException
  {
    try{
    Statement  stmt = con.createStatement();
    stmt.execute(""); // to be calculated 
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Bill Confirmation Rq "+ e.getMessage(),e,1);
    }
  }
}