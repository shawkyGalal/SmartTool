package com.smartValue.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Support.SqlReader;

public class JsonServices {

	public static String getDigramJsonFromDB(HttpServletRequest request , HttpSession session  ) throws Exception
	{
		java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");  	
		StringBuffer objectsInfo = new StringBuffer("") ;
		StringBuffer relationInfo = new StringBuffer("") ;
	
		String queryId = request.getParameter("query_id") ; 
		String listQueryIndex = request.getParameter("listQueryIndex") ;
		String relationQueryIndex = request.getParameter("relationQueryIndex") ;
		
		SqlReader sqlReader = new SqlReader(con , "LU_QUERIES" , "Query_BODY" , queryId , session , request) ; 
		String objListQueryStr = sqlReader.getQueryStatments()[Integer.parseInt(listQueryIndex)] ;
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		try{
		  rs = stmt.executeQuery( objListQueryStr );
		  }
		  catch (SQLException sqle)
		  {  stmt.close(); 
		      throw new SQLException("Unable to Execute the following Query <Br>" + objListQueryStr+ "\n" + " <Br> Due To the Following SQL Error " + sqle.getMessage());
		  }
		
		int counter = 0 ; 
		
		while (rs.next())
		{
			String objectId =  rs.getString("OBJECT_ID"); 
			String objectName =  rs.getString("OBJECT_Name");
			String color =  rs.getString("COLOR");
			objectsInfo.append("\n" + ( (counter==0)? "":"," )  + "{\"key\":" + objectId+ " ,\"c\": \""+color+"\"" + ",  \"text\": " + "\""+objectName+"\" }" ) ;
			counter++; 
		}
		
		String relationQueryStr = sqlReader.getQueryStatments()[Integer.parseInt(relationQueryIndex)] ;
			try{
		  rs = stmt.executeQuery( relationQueryStr );
		  }
		  catch (SQLException sqle)
		  {  stmt.close(); 
		      throw new SQLException("Unable to Execute the following Query <Br>" + relationQueryStr+ "\n" + " <Br> Due To the Following SQL Error " + sqle.getMessage());
		  }
		
		counter = 0 ;
		String result = "" ; 
		while (rs.next())
		{
			String fromId = rs.getString("FROM_OBJECT_ID") ; 
			String toId = rs.getString("To_OBJECT_ID") ;
			String description = rs.getString("DESCRIPTION") ;
			
			relationInfo.append("\n" + ( (counter==0)? "":"," )  + "{ \"from\": "+fromId+", \"to\": "+toId+", \"text\": \""+description+"\", \"curviness\": -20 }" ) ;
			counter++; 
		}
		result = "{ \"nodeKeyProperty\": \"key\",  \"nodeDataArray\": " 
		    			+"[" 
		  				+	objectsInfo.toString()
		  				+"], "
		  				+" \"linkDataArray\": " 
		  				+"[ "
		  					+ relationInfo.toString() 
		  				+"] "
					+"}" ; 
					
		if (rs != null) {rs.close(); }
		if (stmt != null) {stmt.close(); }
		
		return result ; 

	}

	public static String getDigramJsonFromFileSystem(HttpServletRequest request , HttpSession session , String filesPath)
	{
		String result  = "" ;
		String fileName = request.getParameter("fileName") ; 

		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(filesPath + fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) 
				{
				result +="\n"+ sCurrentLine;
				}
			} 
			catch (IOException e) {	e.printStackTrace();} 
			finally 
			{ try 
					{
						if (br != null)	br.close();
						if (fr != null)	fr.close();
					} catch (IOException ex) 
						{ex.printStackTrace();}
			}

		return result ; 
	}
	// This method read an sqlReader and return all the query results in a single JSON object considering the first query return a single row as a master data
	// and all the subsequent query as a detailed data. 
	public static String getObjectDataModel(HttpServletRequest request , HttpSession session ,String queryParams , String queryIDForObjectDetails) throws Exception
	{
		StringBuffer result = new StringBuffer(""); 
		java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");  	
		SqlReader sqlReader = new SqlReader(con , "LU_QUERIES" , "Query_BODY" , queryIDForObjectDetails , session , request) ;
		String resultAsJson = sqlReader.getQueryResultAsJSON(0, con) ;
		result.append("\n\t"+resultAsJson.substring(1, resultAsJson.length()-2)) ;  // Exclude first (]) and last 2 (}]) characters 
		for (int i = 1 ; i<sqlReader.getQueryStatments().length ; i++)
		{
			if (sqlReader.getQueryStatments()[i] != null )
			{
				try {
					resultAsJson = sqlReader.getQueryResultAsJSON(i, con) ;
				}
				catch (Exception e ) {resultAsJson = "Error Extracting the JSON of query id = "+queryIDForObjectDetails+" Index = ["+i+"] Due to : " + e.getMessage() ; }
				result.append( "\n\t\t , \"" + sqlReader.getQueryTitles()[i].trim() + "\" : " ) ;
				result.append( "\n\t\t" + resultAsJson ) ;
			}
		}
		//result.append("\n\t]") ;
		result.append("\n\t}") ; 
		
		return result.toString() ; 
	}
	

}
