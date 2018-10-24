package SADAD.DATASTRCTURE;

import org.w3c.dom.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import SADAD.*;

public class Msg 
{ 
  public String language;
  private static final String languageNodeName = "LANGUAGE";

  public  String text;
  private static final String textNodeName = "TEXT";
  
  
  public Msg(Node custIdXMLNode  )throws Exception
  {
    setParameters(custIdXMLNode);
  }
  protected void setParameters(Node custIdXMLNode ) throws Exception 
  {
    NodeList childNodes = custIdXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean languageFound = false;
    boolean textFound = false;
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
                  //--------Setting the PrcDate-----
                  if ( nodeType2 ==Node.TEXT_NODE )
                  {
                      if ( ! languageFound  &&  nodeName1.toUpperCase().equals(languageNodeName))
                      {
                        this.language = nodeValue2;
                        languageFound = true;
                        continue;
                      }
                      
                      if (! textFound &&  nodeName1.toUpperCase().equals(this.textNodeName))
                      {
                        this.text = nodeValue2;
                        textFound = true;
                      }
                                        
                  }
                  
                 }
    
           }
         }
    validate();
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }
 
 
 protected void validate()
 {
   
 }
 protected void save2DB()
 {
   
 }
 
  public String toXMLString()
   {
    String xmlTag="\n"+"<Msg>";
    xmlTag+= "\n\t"+ "<Language>" + language + "</Language>";
    xmlTag+= "\n\t"+ "<Text>" + text + "</Text>";
    xmlTag+= "\n"+ "</Msg>";
    return xmlTag;
   }
   
}