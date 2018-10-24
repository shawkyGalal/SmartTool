package SADAD;
import Support.*;
import java.sql.*;
import java.io.*;

public class UploadProcess
{
  Connection con;
  File billUploadFolder ;
  String tableName ;
  public UploadProcess(Connection m_con , File m_UploadFolder )
  {
    this.con = m_con;
    billUploadFolder = m_UploadFolder;
  }
  public void deletAllUnSent() throws Exception
  {
  String[] fileNames = this.billUploadFolder.list();
  java.io.File billUploadFile;
  boolean fileSentToSADAD = false;
  for (int i= 0; i< fileNames.length ; i++)
  {
    billUploadFile =  new java.io.File (billUploadFolder.getAbsolutePath() +"\\"+ fileNames[i]);
    if (!isFileSentToSADAD(billUploadFile))
    {
      billUploadFile.delete();
    }
  }
  }
  public boolean isFileSentToSADAD(File file) throws Exception 
  {
    boolean result = false;
    String queryStmt = "select * from "+tableName+" where upper(file_name) = '" + "FILE:///" + Misc.repalceAll(file.getAbsolutePath().toUpperCase(),"\\","/") +"'" ;
    try{
    Statement stmt = this.con.createStatement();
    result = stmt.executeQuery(queryStmt).next();
    }
    catch ( Exception e ) {throw new Exception("Unable to check if the File " + file.getAbsolutePath() + "Is already Sent To SADAD or Not  " +" Due to the Following Error " + e.getMessage() );}
    return result;     
  }
}