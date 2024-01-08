package Support;

import java.io.*;
import java.util.*;

public class DataExtractor 
{ //--------Createed By : Shawky Foda
  //-----Date : 15-02-2004-----------
  //-----purpose-This Class is used To Extract a tab delimited data from a text file and return it as an array of Vector Object---
  //---- last Status  : Completed & Tested 
  
  FileReader fr ;
  Vector[] data = null;
  public DataExtractor(String fileName) throws Exception
  {
    fr = new FileReader(fileName);
  }
  public Vector[] getData() throws Exception
  {
    return this.data;
  }
  public void setData() throws Exception
  {
    //Vector[] data = null;
    if( fr == null )
    { throw new Exception("") ;}
    BufferedReader br = new BufferedReader(fr);
    String s = "";
    int rowNo = 0;
    int columnNumber = 0 ; 
    while ((s= br.readLine()) != null  && s.length() != 0 ) 
    {
     StringTokenizer st = new StringTokenizer(s, "\t");
     columnNumber = 0; 
     while (st.hasMoreElements())
     {
      if (rowNo == 0)
      {
        if (columnNumber == 0 )
        {
        int coumnCount = st.countTokens();
        data = new Vector[coumnCount];
        }
        data[columnNumber] = new Vector();
      }
      String elm = st.nextElement().toString();
       data[columnNumber].addElement( elm );
       columnNumber++;
     }
    
     rowNo ++; 
    }
    br.close();
        
  }

      public  static void main(String[] ergs)
    {
      try{
       new DataExtractor("C:\\Edge_Work\\SABIC\\public_html\\SabicData\\marine.txt").setData();
      System.out.print("aaa");
      }
      catch (Exception e) {System.out.print(e.getMessage());}
    }
  
}