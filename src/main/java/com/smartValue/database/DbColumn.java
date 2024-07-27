package com.smartValue.database;

import java.io.Serializable;
import java.util.Date;



public class DbColumn implements Serializable
{
	private String columnName ;
	private String columnTypeName ;
	private String columnClassName;
	private Facet facet = new Facet(this.getColumnName(), null); 
	public DbColumn(String pm_columnName, String pm_columnTypeName, String pm_columnClassName)
	{
		this.setColumnName(pm_columnName);
		this.setColumnTypeName(pm_columnTypeName);
		this.columnClassName = pm_columnClassName;
		
	}
	public void setColumnTypeName(String columnTypeName)
	{
		this.columnTypeName = columnTypeName;
	}
	public Object getEmptyInstanceOfColumnType() throws Exception
	{
		
		if (this.getColumnClassName().equals("java.math.BigDecimal"))
			return null;// new BigDecimal(0);
		if (this.getColumnClassName().equals("java.sql.Timestamp"))
			return new java.sql.Timestamp(new Date().getTime());
		return Class.forName(this.getColumnClassName()).newInstance(); //Util.instantiateClass(this.getColumnClassName());
	}
	public String getColumnTypeName()
	{
		return columnTypeName;
	}
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	public String getColumnName()
	{
		return columnName;
	}
	public void setFacet(Facet facet)
	{
		this.facet = facet;
	}
	public Facet getFacet()
	{
		return facet;
	}

	public String getColumnClassName()
	{
		return columnClassName;
	}
	
	public boolean equals(Object another_dbColumn)
	{
		if (another_dbColumn ==null || ! (another_dbColumn instanceof DbColumn))
		{
			return false ;
		}
		else
		{
			return this.getColumnName().equals( ( (DbColumn)another_dbColumn).getColumnName()) ;
		}
	}


}
