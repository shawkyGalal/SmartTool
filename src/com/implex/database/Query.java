package com.implex.database;

import java.io.Serializable;

public class Query  implements Serializable{
	private String queryStr ;
	private int firstFromIndex  ;
	private int firstWhereIndex ;
	public static final String SELECT = "SELECT"; 
	public static final String FROM = "FROM ";
	public static final String WHERE = "WHERE ";
	public static final String DUAL = "DUAL"; 
	public static final String AND = "AND"; 
	private String betweenSelectAndFirstFrom ;
	private String estimatedTableName ;
	private String estimatedTableOwner ; 
	private Query subQuery ;// Not yet Used 
	
	public Query(String pm_query) {
		if (pm_query  != null )
		{
		setQueryStr(pm_query); 
		String upperCaseVersion = pm_query.toUpperCase() ; 
		this.firstFromIndex = upperCaseVersion.indexOf(FROM);
		this.firstWhereIndex = upperCaseVersion.indexOf(WHERE);
		try{
			this.betweenSelectAndFirstFrom = this.queryStr.substring(SELECT.length(), firstFromIndex ).trim();
		}
		catch(Exception e ) {}
		this.setEstimatedTableName(this.estimateTableName());
		}
		
	}
	
	private   String estimateTableName()
	{
		String queryStr = this.getQueryStr() ;
		if(queryStr == null || ! isSingleTableQuery()) return null;
		queryStr = queryStr.toUpperCase();
		String temp = queryStr.split(FROM)[1];
		temp = temp.split(" ")[0];
		int dotIndex = temp.indexOf(".");
		if (dotIndex != -1)
		{
			this.setEstimatedTableOwner(temp.substring(0 , dotIndex));
		}
		return (dotIndex != -1 )? temp.substring(dotIndex+1) : temp;
	}
	
	public  boolean isSingleTableQuery()
	{
		String queryStr = this.getQueryStr() ;
		
		String betweenFromAndWhere = (firstWhereIndex ==-1)? queryStr.substring(firstFromIndex) : queryStr.substring(firstFromIndex , firstWhereIndex);
		return ! betweenFromAndWhere.contains(",");
	}

	private void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getQueryStr() {
		return queryStr;
	}
	Object argsParameter[];
	public Object[] getArgsParameter() {
		return argsParameter;
	}

	public void setArgsParameter(Object[] argsParameter) {
		this.argsParameter = argsParameter;
	}

	public boolean isAliasProvided()
	{
		boolean result = false;
		String[] cs = this.getColumns();
		for(String column : cs)
		{
			if (column.trim().contains(" "))
				result = true;
			break;
		}
		return result;
	}
	private String[] columns ;
	public String[] getColumns()
	{
		if (columns == null)
		{
			columns = calcColumns() ; 
		}
		return columns ;
	}
	public String[] calcColumns()
	{
		String[] result = null; 
		if (betweenSelectAndFirstFrom != null)
		{
			result=  betweenSelectAndFirstFrom.toUpperCase().split(",");
			for ( int i = 0 ; i< result.length ; i++ )
			{	
				result[i] = result[i].trim() ;
				  
				
				int spaceIndex = result[i].indexOf(" ") ;
				if ( spaceIndex!= -1 )
				{
					result[i] = result[i].substring(spaceIndex+1).trim() ;
					
				}
				
				int dotIndex = result[i].indexOf(".");
				if (dotIndex!= -1 )
				{
					result[i] = result[i].substring(dotIndex +1 ).trim() ;
				}
				
			}
		}
		return result ; 
		
	}
	
	public boolean isFromDual()
	{
		return this.getEstimatedTableName().contains(DUAL);
	}

	public void setEstimatedTableName(String pm_estimatedTableName) {
		if (pm_estimatedTableName!= null)
		this.estimatedTableName = pm_estimatedTableName.trim();
	}

	public String getEstimatedTableName() {
		return estimatedTableName;
	}
	
	public int getFirstWhereIndex()
	{
		return this.firstWhereIndex ;
	}
	
	public void appendAndFilter(String filter)
	{
		appendAndOr(filter , Query.AND); 
	}
	
	private void appendAndOr(String filter , String andOr)
	{
		if (filter != null && !filter.equals(""))
		{
			String newQueryStr = this.getQueryStr() + " " + andOr + " " + filter ; 
			setQueryStr(newQueryStr) ; 
		}
	}

	public void setEstimatedTableOwner(String estimatedTableOwner) {
		this.estimatedTableOwner = estimatedTableOwner;
	}

	public String getEstimatedTableOwner() {
		return estimatedTableOwner;
	}
	

}
