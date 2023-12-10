package SADAD;
import java.sql.*;
import java.io.*;
public class PmtUploadProcess extends UploadProcess
{
  
  public PmtUploadProcess(Connection m_con , File m_billUploadFolder)
  {
    super(m_con, m_billUploadFolder); 
    this.tableName = "Pmt_Upload_Process";
  }
}

