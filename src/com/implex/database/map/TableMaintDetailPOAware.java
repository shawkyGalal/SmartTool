package com.implex.database.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

import Support.Misc;
import Support.QueryParamValueNotFoundException;

import com.implex.database.PersistantObject;

public class TableMaintDetailPOAware extends TableMaintDetails {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> queryParams ; 
	
	public ArrayList<String> getQueryParams() {
		//if (queryParams == null)
		{
			queryParams = this.extractParameters((String) this.getSelectListQueryValue());
		}
		return queryParams;
	}
	
	protected ArrayList<SelectItem> calcQuerySelectItems(int pm_userLang , java.sql.Connection m_con , String callerObjectRowId)
	{
		ArrayList<SelectItem> v_result = null ; 
		String query = (String) this.getSelectListQueryValue();
		 
		if (isSimpleQuery()) // The Query is simple and has no parameter
		{
			v_result = super.calcQuerySelectItems(pm_userLang, m_con , callerObjectRowId) ; 
		}
		
		else // the query is complex and/or has parameters 
		{
			
			if (query.indexOf(boundVarInit) != -1  && callerObjectRowId == null ) 
			{	
				v_result = new ArrayList<SelectItem>(); 
				v_result.add(new SelectItem(0 , "Parameters Values Not defined in Parameterized Query "));
				return v_result ;
			}
			
			if (query.indexOf(boundVarInit) != -1 ) 
			{	
				// Substitute Variables Values From PO
				ArrayList<String> params = this.getQueryParams() ; 
				String paramsComaSeperated = "" ;
				if (params.size() >0 )
				{
						for (String param : params )
						{
							paramsComaSeperated += param.substring(2) +", "; 
						}
						int lastCommaIndex = paramsComaSeperated.lastIndexOf(", ") ; 
						paramsComaSeperated = (lastCommaIndex != -1)? paramsComaSeperated.substring(0, lastCommaIndex) : "";
						String callerObjectInfoQuerStr= "Select " + paramsComaSeperated +" from " + this.getOwnerValue() + "." + this.getTableNameValue() + " t Where t.rowid = '" + callerObjectRowId +"'";
						Statement stmt = null ; 
						ResultSet rs = null ;
						
						try 
						{
							 stmt = m_con.createStatement(); 
							 rs = stmt.executeQuery(callerObjectInfoQuerStr);
							 rs.next(); 
							for (String param : params)
							{
								query = Misc.repalceAll(query, param, rs.getString(param.substring(2).toUpperCase())) ;
							}
						}
						catch(SQLException sqle ) 
						{ 	sqle.printStackTrace(); 
							releaseResources(stmt , rs) ;
						}
						finally {releaseResources(stmt , rs) ; }
				}
			}
			if (query.indexOf(refQueryInit) != -1 ) 
			{
				// get the referenced Query 
			}
			
			boolean mandatory = ! this.getNullable().getBooleanValue(); //&&  isRadioButton ; //  this.getHtmlTypeValue().equals(HtmlTypesOld.Radio_Button_Group);
			if (query == null || query.equals("") || this.isListExccedsLimit())
			{
				v_result = new ArrayList<SelectItem>();
			}
			else
			{
				v_result = this.getDbService().getSelectItems(query , ! mandatory , pm_userLang , m_con);
			}		
		}
		
		return v_result  ; 
	}
	
	private static void releaseResources(Statement stmt, ResultSet rs) {
		try { if (rs!= null) {rs.close();} if (stmt!= null) {stmt.close(); } } catch (Exception e) {} 
		
	}

	private static ArrayList<String> extractParameters(String queryStr) {
		ArrayList<String> v_result = new ArrayList<String>(); 
		
		if ( queryStr.indexOf(boundVarInit) != -1 )
	    { 
	      //--Check if it is sql Commented or not -------------
	      int boundVarStartIndex = -1 ;
	      StringTokenizer st2 = new StringTokenizer(queryStr,"\n");
	      String queryLine= "";
	      while(st2.hasMoreTokens())
	      {
	        queryLine = st2.nextToken();
	        boundVarStartIndex = queryLine.indexOf(boundVarInit);
	        int commentIndex  =queryLine.indexOf("--");
	        if (boundVarStartIndex >= 0 && commentIndex >= 0 && commentIndex < boundVarStartIndex )
	        {
	           break;
	        }
	        else 
	        {   
	        	while (boundVarStartIndex != -1 )
	        	{
		        	int boundVarEndIndex = queryLine.indexOf(" ", boundVarStartIndex) ; 
		        	int commaIndex = queryLine.indexOf("'", boundVarStartIndex); 
		        	int amberIndex = queryLine.indexOf("&", boundVarStartIndex);
		        	//int newLineIndex = queryLine.indexOf("\n", boundVarStartIndex);
		        	//if (boundVarEndIndex == -1){boundVarEndIndex = queryLine.indexOf("\n", boundVarStartIndex);} 
		        	if (boundVarEndIndex == -1 || ( commaIndex != -1 && commaIndex < boundVarEndIndex)) {boundVarEndIndex = commaIndex;} 
		        	if (boundVarEndIndex == -1 || ( amberIndex != -1 && amberIndex < boundVarEndIndex)) {boundVarEndIndex = amberIndex;}
		        	//if (boundVarEndIndex == -1 || newLineIndex < boundVarEndIndex){boundVarEndIndex = newLineIndex;}
		        	
		        	int lineLength = queryLine.length()  ; 
		        	if (boundVarEndIndex == -1) {boundVarEndIndex = lineLength ;}
		        	String boundVarName = queryLine.substring(boundVarStartIndex , boundVarEndIndex);
		        	v_result.add(boundVarName);
		        	boundVarStartIndex = queryLine.indexOf(boundVarInit , boundVarEndIndex);
	        	}
	        }
	      }
	     	      
	    }
		return v_result;
	}
	
	private String substituteParams ( String m_query  , java.sql.Connection m_con , String callerObjectRowId)
	{
		String v_result = m_query ; 
		
		String paramsComaSeperated = "" ;
		ArrayList<String> params = extractParameters(m_query) ; 
		if (params.size() >0 )
		{
				for (String param : params )
				{
					paramsComaSeperated += param.substring(2) +", "; 
				}
				int lastCommaIndex = paramsComaSeperated.lastIndexOf(", ") ; 
				paramsComaSeperated = (lastCommaIndex != -1)? paramsComaSeperated.substring(0, lastCommaIndex) : "";
				String callerObjectInfoQuerStr= "Select " + paramsComaSeperated +" from " + this.getOwnerValue() + "." + this.getTableNameValue() + " t Where t.rowid = '" + callerObjectRowId +"'";
				Statement stmt = null ; 
				ResultSet rs = null ;
				
				try 
				{
					 stmt = m_con.createStatement(); 
					 rs = stmt.executeQuery(callerObjectInfoQuerStr);
					 rs.next(); 
					for (String param : params)
					{
						v_result = Misc.repalceAll(v_result, param, rs.getString(param.substring(2).toUpperCase())) ;
					}
				}
				catch(SQLException sqle ) 
				{ 	
					releaseResources(stmt , rs) ;
				}
				finally {releaseResources(stmt , rs) ; }
		}
		return v_result ; 
	}
	public String getHtmlTypeValue(java.sql.Connection m_con , String callerObjectRowId )
	{
		String v_result = "" ;
		String displayTypeQuery = getDisplayTypeQueryValue() ; 
		if (displayTypeQuery== null )
		{
		 v_result = this.getHtmlTypeValue(); 
		}
		else
		{
			displayTypeQuery = this.substituteParams(displayTypeQuery, m_con, callerObjectRowId) ;
			Statement stmt = null ; 
			ResultSet rs = null ;
			try{
				 stmt = m_con.createStatement(); 
				 rs = stmt.executeQuery(displayTypeQuery);
				 rs.next(); 
				 v_result = rs.getString(1) ; 
			}
			catch (SQLException sqle)
			{releaseResources(stmt , rs) ;
			}
			finally {releaseResources(stmt , rs) ; }

		}
		return v_result; 
	}
	
	public java.lang.String getJavaScriptValidationValue(java.sql.Connection m_con , String callerObjectRowId){
		 
		String v_result = super.getJavaScriptValidationValue() ; 
		
		if ( v_result != null && v_result.indexOf(boundVarInit)!= -1 )
		{
			v_result = substituteParams( v_result, m_con , callerObjectRowId);
		}
		return v_result;
	}
	
	public java.lang.String getAttributeValue( java.sql.Connection m_con , String callerObjectRowId){
		String v_result = super.getAttributeValue() ; 
		
		if ( v_result != null && v_result.indexOf(boundVarInit)!= -1 )
		{
			v_result = substituteParams( v_result, m_con , callerObjectRowId);
		}
		return v_result;	}
}
