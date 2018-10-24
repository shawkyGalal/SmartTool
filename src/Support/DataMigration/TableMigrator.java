package Support.DataMigration;
//import NCCI.*;
import java.sql.*;
import java.util.Vector;

public class TableMigrator
{
  Connection fromCon ;
   Connection toCon ;
  public TableMigrator(Connection m_fromCon , Connection m_toCon)
  {
    this.fromCon = m_fromCon;
    this.toCon = m_toCon;
  }
  public int migrateTable(String tableName , String owner ,  String whereClause ) throws Exception 
  {
    //----------Comparing table in both environments ----
    Vector[] compResult = compareTables(  tableName, owner );
    
    if (compResult[0].size() > 0 )
    {
      throw new Exception("Tables are not identical");
    }
    //--------End of Comparing table in both environments ----    
    
    //-------Disabling all Triggers---
    CallableStatement  cs = 
    toCon.prepareCall("alter table " + owner + "." + tableName + " disable all triggers" );
    cs.execute();
    cs.close();
    //--------Delete similar records if exist-----

    //---------Getting Data to be migrated-------
    String queryStmt = "select * from " + owner + "." + tableName + " where " + whereClause;
    ResultSet rs = fromCon.createStatement().executeQuery(queryStmt);
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    //------End of Getting Data to be migrated------- 
    
    if (true)
    {String deleteStmt = "delete from " + owner + "." + tableName + " where  " + whereClause ; 
    toCon.createStatement().execute(deleteStmt);
    }
    //-------End of Delete similar records if exist-----
    
    //-------Constructing first part of the insert Statment---
    String insertStmt = "insert into " + owner + "." + tableName + " values (" ;
    for (int i = 1 ; i<=columnCount ;i++ )
    {
      insertStmt += "?" +  ( (i==( columnCount))? ")" :",")  ;
    }
    //-----End of Constructing first part of the insert Statment---    
    PreparedStatement prepstmt = toCon.prepareStatement(insertStmt);
    //------setting bind variables from resultset ---------
    int migratedRecordCount = 0;
    while ( rs.next())
    {
      for (int i = 1 ; i<=columnCount ;i++ )
      {
         String columnType = rsmd.getColumnTypeName(i);
         
         if (columnType.equals("RAW"))
         { prepstmt.setString(i, rs.getString(i)); }

         else if (columnType.equals("CHAR"))
         { prepstmt.setString(i, rs.getString(i)); }
         
         else if (columnType.equals("VARCHAR") || columnType.equals("VARCHAR2"))
         { prepstmt.setString(i, rs.getString(i)); }
         
         else if (columnType.equals("DATE"))
         { prepstmt.setDate(i, rs.getDate(i)); }
         
         else if (columnType.equals("NUMBER"))
         { prepstmt.setBigDecimal(i, rs.getBigDecimal(i)); }
         
         else if (columnType.equals("CLOB"))
         { prepstmt.setClob(i, rs.getClob(i));}
         
         else if (columnType.equals("BLOB"))
         { prepstmt.setBlob(i, rs.getBlob(i));}
      }
     
      prepstmt.execute();
      
      migratedRecordCount++;
    }
    //------setting bind variables from resultset ---------
    
    //-------Enabling all Triggers---
    cs = toCon.prepareCall("alter table " + owner + "." + tableName + " enable all triggers" );
    cs.execute();
    cs.close();
    //--------Delete similar records if exist-----
    prepstmt.close();
    
    return migratedRecordCount;
    
  }

public static void main(String[] args ) throws Exception
{
  Connection fromCon = Support.ConnectionFactory.createConnection_old("10.16.16.9","SPDB1","shawky","redsea11");
  Connection toCon = Support.ConnectionFactory.createConnection_old("10.16.17.51","testsvr","bgadmin","bgadmin");
  try{
    new TableMigrator(fromCon, toCon).migrateTable("abc" ,"bgadmin", " 1 <> 1 " );
  
   //new TableMigrator().migrateTable( "Account", "bgadmin", "id = '92000FDB00001B59'" , fromCon, toCon);
   fromCon.close();
   toCon.close();
  }
  
  catch (java.sql.SQLException e)
  {fromCon.close();
  toCon.close();
  throw e;}
  

  
}
public Vector[] compareTables( String tableName , String owner ) throws Exception
{
  Connection con1 = this.fromCon;
  Connection con2  = this.toCon;
  Vector[] compareResult = new Vector[6];
  compareResult[0] = new Vector();  compareResult[1] = new Vector();  compareResult[2] = new Vector();
  compareResult[3] = new Vector();  compareResult[4] = new Vector();  compareResult[5] = new Vector();
  
  String queryStmt = "select t.COLUMN_NAME ,  t.DATA_TYPE  , t.DATA_LENGTH  "
                    +"\n"+"from sys.DBA_TAB_COLUMNS t  , sys.all_col_comments  c"
                    +"\n"+"where t.table_name = upper('"+tableName+"') "
                    +"\n"+"and t.owner = Upper('"+owner+"') "
                    +"\n"+"and t.table_name  = c.table_name "
                    +"\n"+"and t.owner = c.owner "
                    +"\n"+"and t.COLUMN_NAME  = c.COLUMN_NAME "
                    +"\n"+"-- and column_id <3"
                    +"\n"+"order by t.COLUMN_id  "; 
  ResultSet rs1 = con1.createStatement().executeQuery(queryStmt);
  
  ResultSet rs2 = con2.createStatement().executeQuery(queryStmt);
  boolean rs1HasMore = rs1.next();
  boolean rs2HasMore = rs2.next();
  
  while ( rs1HasMore ||rs2HasMore )
  {
    String rs1Value1 ="", rs1Value2 ="", rs1Value3 ="", rs2Value1 ="", rs2Value2="" , rs2Value3 = "";
    if ( rs1HasMore )
    {
      rs1Value1 = rs1.getString(1);
      rs1Value2 = rs1.getString(2);
      rs1Value3 = rs1.getString(3);
    }
    if ( rs2HasMore )
    {
      rs2Value1 = rs2.getString(1);
      rs2Value2 = rs2.getString(2);
      rs2Value3 = rs2.getString(3);
    }
    
      if (  ! rs1Value1.equals(rs2Value1)  ||! rs1Value2.equals(rs2Value2)  ||! rs1Value3.equals(rs2Value3)  )
      {
        compareResult[0].addElement(rs1Value1);
        compareResult[1].addElement(rs1Value2);
        compareResult[2].addElement(rs1Value3);
        
        compareResult[3].addElement(rs2Value1);
        compareResult[4].addElement(rs2Value2);
        compareResult[5].addElement(rs2Value3);
      }
      
   rs1HasMore = rs1.next();
   rs2HasMore = rs2.next();
  }
  
return compareResult;
}
}