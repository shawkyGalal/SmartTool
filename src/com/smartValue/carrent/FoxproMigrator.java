package com.smartValue.carrent;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Support.Misc;

public class FoxproMigrator {

	private Connection fromCon ;
	private Connection toCon ;
	private ArrayList<Exception> migrateErrors  = new ArrayList<Exception>();
	
	public FoxproMigrator(Connection m_fromCon , Connection m_toCon) 
	{
		this.fromCon = m_fromCon ; 
		
		this.toCon = m_toCon ;;
	}
	
	 
	public int  migrateTable( String m_tableName , boolean m_override) throws Exception
	{
		this.migrateErrors  = new ArrayList<Exception>();
		int processedRecordCount = 0 ;
		if (m_override)
		{
			try { this.dropTable(m_tableName ) ;}
			catch (Exception e) {}
			this.createTable(m_tableName) ;
		}
		processedRecordCount = this.copyData(m_tableName) ;
		
		return processedRecordCount ; 
	}
	
	private int copyData(String mTableName) throws Exception {
	    //-------Constructing first part of the insert Statment---
	    //---------Getting Data to be migrated-------
	    String queryStmt = "select * from " + mTableName ;
	    ResultSet rs = fromCon.createStatement().executeQuery(queryStmt);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	    
		String insertStmt = "insert into " + Misc.getConnectionUserName(this.toCon) + "." + mTableName + " values (" ;
	    for (int i = 1 ; i<=columnCount ;i++ )
	    {
	      insertStmt += "?" +  ( (i==( columnCount))? ")" :",")  ;
	    }
	    //-----End of Constructing first part of the insert Statment---    
	    PreparedStatement prepstmt = toCon.prepareStatement(insertStmt);
	    //------setting bind variables from resultset ---------
	    int processedRecordCount = 0;
	    while ( rs.next())
	    {
		  	processedRecordCount++;
		  	System.out.println(processedRecordCount);
	    	try{
		    	for (int i = 1 ; i<=columnCount ;i++ )
		    	{
		         String columnType = rsmd.getColumnTypeName(i);
		         
		         if (columnType.equals("C") || columnType.equals("M") || columnType.equals("G") ) // Char field Or M= Memo or G = General 
		         { 
		        	 String value =  rs.getString(i) ;
		        	 prepstmt.setString(i, value); 	 
		         }
	         
		         else if (columnType.equals("N")|| columnType.equals("I") || columnType.equals("B") ) // N = Numeic or I = Integer or B = Double  
		         { 
		        	 // Failure to read a number from Foxpro files, use -99999 instead
		        	 BigDecimal value = new BigDecimal(-99999); 
		        	 try{
		        	  value = rs.getBigDecimal(i) ;
		        	 }
		        	 catch( Exception e ){ System.out.print(e.getMessage()); }
		        	 prepstmt.setBigDecimal(i, value); 
		         }
		         
		         else if (columnType.equals("D"))
		         { prepstmt.setDate(i, rs.getDate(i)); }
		         
		         else if (columnType.equals("T"))
		         { prepstmt.setTimestamp(i, rs.getTimestamp(i)); }

		         else if (columnType.equals("L"))
		         {
		        	 String value =  ""; 
		        	 try{
			        	  value = rs.getString(i) ;
			        	 }
			        	 catch( Exception e ){
			        		 System.out.print(e.getMessage());
			        	 }
			        	 prepstmt.setString(i, value); 
		        	 
		         }
		    	}
		    	prepstmt.execute();
		  }
		  	catch (Exception e) 
		  	{ this.getMigrateErrors().add(new Exception ("Unable to Migrate Rowno " +  processedRecordCount + " Into Table " + mTableName + " Due To " + e.getMessage())); }

	    }
	    
	    prepstmt.close();
	    rs.close();
	    System.out.print ( "( " + processedRecordCount + ") "  + " Records Copied From Table " + mTableName ) ;
	    return processedRecordCount; 
	}
	 
	private void dropTable(String m_TableName) throws SQLException {
		CallableStatement  cs = null;
		try{ 
		 cs = toCon.prepareCall(" Drop table " + Misc.getConnectionUserName(this.toCon)+ "." +  m_TableName );
		 cs.execute();
		}
		catch (SQLException e)
		{
			cs.close();
			throw e ;
		}
		finally 
		{
			cs.close();	
		}
		 
	}

	private void createTable(String m_TableName) throws SQLException 
	{

	     Statement stmt = null;
	     ResultSetMetaData rsmd = null;
		 String quryStr = " Select * from " + m_TableName + " " ;
		 CallableStatement  cs = null ;
    	 ResultSet rs  = null ;
	     try {
    	 stmt = this.fromCon.createStatement(); 
	    	 rs = stmt.executeQuery(quryStr);
	    	 rsmd = rs.getMetaData();
	    	 int columnCount = rsmd.getColumnCount();
	    	 String[] columnNames = new String[columnCount];
	    	 String[] columnTypes = new String[columnCount];
	    	 int[] columnDisplaySizes = new int[columnCount];
	    	 StringBuffer createTableStmt = new StringBuffer (" Create Table "+this.toCon.getMetaData().getUserName()+"." +m_TableName.toUpperCase() )   ;  
			 createTableStmt.append( "	( " ) ; 

	    	 for (int i =1 ; i<= columnCount ; i++ )
	    	 {
	    		columnNames[i-1] = rsmd.getColumnName(i); 
	    		columnTypes[i-1] = rsmd.getColumnTypeName(i);
	    		columnDisplaySizes[i-1] = rsmd.getColumnDisplaySize(i)*2 ; // Multiply by 2 for Arabic characters 
	    		String oracleType = FoxproMigrator.mapFoxproToOracleDataType(columnTypes[i-1] , columnDisplaySizes[i-1]) ; 
	    		createTableStmt.append("\n" + ((i==1 )? "" :"," ) + columnNames[i-1] + "     " + oracleType  ) ;   	

	    	 }
			 createTableStmt.append( "	) " ) ; 
			 createTableStmt.append ( "\n" + "tablespace CAROLD_DATA "
										  +"\n" + " pctfree 10 "
										  +"\n" + " initrans 1 "
										  +"\n" + " maxtrans 255 "
										  +"\n" + " storage "
										  +"\n" + " ( initial 64K "
										  +"\n" + "   next 1M "
										  +"\n" + "   minextents 1 "
										  +"\n" + "   maxextents unlimited " 
										  +"\n" + "  ) " ) ;
			 
			 cs = toCon.prepareCall(createTableStmt.toString() );
			 cs.execute();
	     }
	     catch (SQLException e)
	     {
	    	 rs.close();
			 stmt.close();
			 cs.close();
	    	 throw e ; 
	     }
		 finally {  
			 rs.close();
			 stmt.close();
			 cs.close();
			 }
		
	}

	public static String mapFoxproToOracleDataType(String m_foxProDataTypeName , int m_foxProDisplaysize)
	{
		String result = "varchar2(2000)" ;  
		if (m_foxProDataTypeName.equalsIgnoreCase("N"))
		{
			result = "number" ; 
		}
		else if (m_foxProDataTypeName.equalsIgnoreCase("C"))
		{
			result = "varchar2(" + m_foxProDisplaysize + ")" ;
		}
		else if (m_foxProDataTypeName.equalsIgnoreCase("D"))
		{
			result = "Date" ;
		}
		
		return result ; 
	}


	public ArrayList<Exception> getMigrateErrors() {
		return migrateErrors;
	}

}
