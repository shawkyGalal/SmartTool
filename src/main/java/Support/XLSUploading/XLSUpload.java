package Support.XLSUploading;

//import NCCI.*;
import Support.*;
import java.util.*;
import jxl.*;
import java.io.*;
import java.sql.*;

public class XLSUpload 
{
File xlsFile ;
public Vector[] data ;
String columnNames = "";
ResultSetMetaData md = null;
Vector errors = new Vector();
Vector errorRowNumbers = new Vector();
private String sqlDateFormate = "dd-mm-yyyy" ;
private Connection con;
private boolean oracleDB;
private boolean sqlDB; 
private int ukColumnCount = 1 ; 
public String getSqlDateFormate() {
	return sqlDateFormate;
}
public void setSqlDateFormate(String sqlDateFormate) {
	this.sqlDateFormate = sqlDateFormate;
}

private int updateCounter = 0   ;
public  void updateXlsData2DB( Connection m_con, String m_tableName) throws SQLException
{
	this.setMetaData(m_con , m_tableName);
  // Vector errors = new Vector();
  Statement stmt = m_con.createStatement();
  String updateStmt ;
  updateCounter = 0 ; 
  for (int i = 1 ; i< this.data[0].size() ; i++)
  {
	updateStmt = constructUpdateStmt(m_tableName , i);
    try{
    	updateCounter += stmt.executeUpdate(updateStmt);
    	
    }
    catch (Exception e)
    {
      errors.addElement("Record No Error " + i +":Unable to Execute the Following :<br>" + updateStmt +"<br> Due to The following Error "+ e);
      errorRowNumbers.addElement(String.valueOf(i));
    }
  }
}
public int getUpdateCount()
{
	return this.updateCounter ; 
}

private int uploadCounter = 0   ;
public  void uploadXlsData2DB( Connection m_con, String m_tableName) throws SQLException
{
  this.setMetaData(m_con , m_tableName);
  // Vector errors = new Vector();
  Statement stmt = m_con.createStatement();
  String insertStmt = null;
  for (int i = 1 ; i< this.data[0].size() ; i++)
  {
    insertStmt = constructInsertStmt(m_tableName , i);
    try{
    	uploadCounter += stmt.executeUpdate(insertStmt);
    }
    catch (Exception e)
    {
      errors.addElement("Record Error No. " + i +" :Unable to Execute the Following :<br>" + insertStmt +"<br> Due to The following Error "+ e);
      errorRowNumbers.addElement(String.valueOf(i));
    }
  }
}

public int getUploadCount()
{
	return this.uploadCounter ; 
}

public Vector getErrors()
{
  return this.errors;
}

public Vector getErrorRowNumbers()
{
  return this.errorRowNumbers;
}
private void setMetaData(Connection m_con , String tableName) throws SQLException
{
  this.con = m_con ;
  String productName = this.con.getMetaData().getDatabaseProductName().toUpperCase() ; 
  this.oracleDB = productName.indexOf("ORACLE") != -1;
  this.sqlDB = productName.indexOf("MICROSOFT") != -1;
  String dumyStmt = "select " + this.columnNames  + " from " +  tableName +" where 1<>1";
  try {  ResultSet rs = m_con.createStatement().executeQuery(dumyStmt);
  md = rs.getMetaData(); }
  catch (SQLException sqle) { throw new SQLException("Unable to Execute : " + dumyStmt + "Due to :" + sqle.getMessage()) ;}
}

private String getColumnValue(int columnIndex , int rowindex) throws SQLException
{
	
	String columnValue ;
	  columnValue= "'" + Misc.replaceSingleQuteWithDouble(this.data[columnIndex].elementAt(rowindex).toString()) +"'";
	  String columnTypeName = this.md.getColumnTypeName(columnIndex + 1);
	  if (this.isOracleDB())
	  {
		  if ( columnTypeName.equals("DATE"))
		  {
			  columnValue = "to_date("+columnValue+",'"+this.getSqlDateFormate()+"')";
		  }
	   
		  if ( columnTypeName.equals("RAW"))
		  {
			  columnValue = "hextoraw("+columnValue+")";
		  }
	  }
	  if (this.isSqlDB())
	  {
		  if ( columnTypeName.equalsIgnoreCase("datetime") || columnTypeName.equalsIgnoreCase("date"))
		  {
			  columnValue = "Convert(datetime , "+columnValue+",103)"; // 103  corresponding to sql date format "dd/mm/yyyy hh:mm:ss"
		  }  
	  }
	return columnValue ; 
}

private String constructUpdateStmt(String tableName , int rowIndex) throws SQLException
{

  String columnValue;
  String columnName ;
  String settingStr = null ;
  String whereCondition = " Where 1 = 1 "; 
  
  // Construct where condition from the first column(s) 
  for ( int columnIndex = 0 ; columnIndex < this.getUkColumnCount() ; columnIndex++ ) 
  {
	  whereCondition += "\n And "  + this.data[columnIndex].elementAt(0).toString() + " = " + this.getColumnValue(columnIndex , rowIndex);
  }
  settingStr = "" ; 
  for (int columnIndex = this.getUkColumnCount() ; columnIndex< this.data.length ; columnIndex++)
  {
	  columnName = this.data[columnIndex].elementAt(0).toString() ;
	  columnValue = this.getColumnValue(columnIndex , rowIndex); 
	  settingStr += columnName + " = " +  columnValue + ( (columnIndex == this.data.length -1 )? "" : "," );
  }
  
  String updateStmt = "Update " + tableName + " Set " + settingStr +  whereCondition; 
  return updateStmt ;
}

private String constructInsertStmt(String tableName , int i) throws SQLException
{

  String columnValues = "";
  String columnValue= "";
  for (int count = 0 ; count< this.data.length ; count++)
  {
	  columnValue = this.getColumnValue(count, i);
      columnValues +=  columnValue  + ( (count == this.data.length -1 )? "" : "," );
  }
  
  String insertStmt = "insert into " + tableName + " ("+this.columnNames+") values ("+columnValues+")";
  return insertStmt ;
}
  public XLSUpload(File m_xlsFile , int sheetNo )throws Exception 
  {
   this.xlsFile = m_xlsFile;
   setXLSData(sheetNo);
  }
  
public Vector[] getXLSData () 
{
  return data;
}
  public void setXLSData (int sheetNo) throws Exception 
  {
	WorkbookSettings wbSettings = new WorkbookSettings();

	//wbSettings.setLocale(new Locale("ar", "AR"));
	//wbSettings.setEncoding("UTF-8");    
    Workbook wb =  Workbook.getWorkbook(xlsFile  , wbSettings  );
    Sheet sheet = wb.getSheet(sheetNo);
   
    int rows = sheet.getRows(); int columns = sheet.getColumns();
    data = new Vector[columns];
    Cell cell = null;
    for ( int columnCounter = 0 ; columnCounter< columns ; columnCounter++)
    {
     data[columnCounter] = new Vector();
      for (int rowsCounter = 0; rowsCounter<rows ;rowsCounter++ )
      {
        cell = sheet.getCell(columnCounter,rowsCounter);
        data[columnCounter].addElement(cell.getContents());
      }
    }

  //---------Constructing coma seperated column Names ----------
  for (int count = 0 ; count< this.data.length ; count++)
  {
   columnNames +=  this.data[count].elementAt(0).toString() + ( (count == this.data.length -1 )? "" : "," );
  }
    
  }
  
   public static void  main(String[] args)
  {
     try{
          File xlsFile = new File ("C:\\med_mem.xls"); //"c:\\temp\\ASQA - Knowledge 2005-02-07 - SGF.xls");
          XLSUpload abc = new XLSUpload(xlsFile , 0);
          String ins = abc.constructInsertStmt("abc" , 1 );
          System.out.print("");
    
    }
    catch (Exception e) {System.out.print(e.getMessage());}
  }

   public boolean isOracleDB() {
	return oracleDB;
   }
	public boolean isSqlDB() {
		return sqlDB;
	}
	public void setUkColumnCount(int ukColumnCount) {
		this.ukColumnCount = ukColumnCount;
	}
	public int getUkColumnCount() {
		return ukColumnCount;
	}
}