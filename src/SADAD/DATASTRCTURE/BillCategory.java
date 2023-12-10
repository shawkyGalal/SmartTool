package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
import java.sql.*;


public class BillCategory //extends SADADMessage 
{
  static final String MedicalIns ="MedicalIns";
  static final String RukhsaIns = "RukhsaIns";
  
  
  public  BillCategory( Node billCatXMLNode  )throws Exception
  {
    //super( billCatXMLNode );
    setParameters(billCatXMLNode);
  }
  protected void setParameters(Node billCatXMLNode) 
  {
    //--------Need To Be Implemented---------
  }
  public String toXMLTag()
  {
    String xMLTag ="/n"+"<BillCategory>";
    //--------Under Investigation---- 
    xMLTag ="/n"+"</BillCategory>";
    return xMLTag;
  }
  protected void validate()
  {
   
  }
   protected void save2DB()
  {
   
  }
}