package Support.XLS;
import java.io.*;
import java.util.*;

public class XlsToXmlConverter 
{
 Vector[] xlsData;
 Vector[] tagNames;
 int noOfRoInHeader; 
 
  public XlsToXmlConverter(String xlsFilePath, int m_noOfRoInHeader) throws Exception
  {
     this.noOfRoInHeader =  m_noOfRoInHeader;
       //----------------- Reading The XLS File  -----------
     File xlsFile = new File (xlsFilePath);
     Support.XLSUploading.XLSUpload  xLSUpload = new Support.XLSUploading.XLSUpload(xlsFile, 4);
     this.xlsData = xLSUpload.getXLSData();

  //--------Getting TagNames from The firt Ro----
  //int noOfRoInHeader = 1;
  this.tagNames = new Vector[noOfRoInHeader];
  for (int i =0 ; i< noOfRoInHeader ; i++)
  { tagNames[i] = new Vector();}
  
    for (int rowCounter = 0 ; rowCounter<noOfRoInHeader ; rowCounter++)
    {
  
      for  (int columnCounter = 0 ; columnCounter<xlsData.length ; columnCounter++)
      {
      tagNames[rowCounter].addElement(xlsData[columnCounter].elementAt(rowCounter)); 
      
      }
    }  
        

  }

 
 public int getNoOfSons(int rowNo, int ColumnNo)
 {
  int noOfSons = 0;
  if (rowNo == this.noOfRoInHeader)
   { return 0; }
  
  for (int i =ColumnNo ; i< this.tagNames.length-1 ; i++)
  {
    if (this.getTagName(rowNo, i) == this.getTagName(rowNo, i+1) 
        || this.getTagName(rowNo, i+1) == ""
        || this.getTagName(rowNo, i+1) == null
        )
    {
      noOfSons++;
    }
  }
  return noOfSons;
 }

 private String getTagName(int rowNo, int columnNo)
 {
     String tagName = this.tagNames[columnNo].elementAt(rowNo).toString();
     return tagName;
 }
 
 public StringBuffer getXmlString(int rowNo ,  int columnNo , int dataRowNo)
 {
   StringBuffer xmlSB = new StringBuffer("");
   int noOfSons = this.getNoOfSons( rowNo , columnNo);
   if ( noOfSons !=0)
   {
     for (int j = 0 ; j <= noOfSons ; j++)
     {
       String sonTagName = this.getTagName(rowNo+1 , columnNo+j);
       xmlSB.append("<"+sonTagName+">");
       xmlSB.append(getXmlString(rowNo+1 , columnNo+j, dataRowNo));
       xmlSB.append("</"+sonTagName+">");
     }
   }
   
   else
   {
     String tagName = this.getTagName(rowNo, columnNo);
     
     String dataValue = this.xlsData[columnNo].elementAt(this.noOfRoInHeader + dataRowNo).toString();
     //xmlSB.append("\n<"+tagName+">");
      xmlSB.append("\t" +dataValue);
    
      //xmlSB.append("</"+tagName+">");
   }
   
   return xmlSB;
 }
 
  public static void main(String[] args) throws Exception
  {
    XlsToXmlConverter xlsToXmlConverter = new XlsToXmlConverter("D:\\MyWork\\Support\\public_html\\xls\\BillUploadTemp2.xls", 4);
    System.out.print( xlsToXmlConverter.getXmlString(0,0,0));
  }
}