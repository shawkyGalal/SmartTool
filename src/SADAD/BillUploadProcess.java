package SADAD;
import java.sql.*;
import java.io.*;
public class BillUploadProcess extends UploadProcess
{
  
  public BillUploadProcess(Connection m_con , File m_billUploadFolder)
  {
    super(m_con, m_billUploadFolder); 
    this.tableName = "Bill_Upload_Process";
  }
}