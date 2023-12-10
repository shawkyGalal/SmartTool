package com.implex.database;

import java.io.Serializable;

public class DbForignKey implements Serializable
{

	private String parentColumnName ;
	private String childColumnName ; 
	private String childTableName ;
	private String childTableOwner ; 
	
	public DbForignKey(String pm_parentColumnName , String pm_childColumnName  ,String pm_childTableName , String pm_childTableOwner)
	{
		 this.parentColumnName = pm_parentColumnName ; 
		 this.childColumnName = pm_childColumnName ; 
		 this.setChildTableOwner(pm_childTableOwner) ; 
		 this.setChildTableName(pm_childTableName);
	}
	
	/**
	 * Create a new Forign Key with the given keys
	 * @param pm_parentColumnName represents Parent(Master Table ) Column Name 
	 * @param pm_childColumnName  represents Child (Detail Table ) Column Name
	 */
	@Deprecated
	public DbForignKey(String pm_parentColumnName , String pm_childColumnName )
	{
		  this.parentColumnName = pm_parentColumnName ; 
		  this.childColumnName = pm_childColumnName ; 
	}


	public String getParentColumnName()
	{
		return parentColumnName;
	}



	public String getChildColumnName()
	{
		return childColumnName;
	}

	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}

	public String getChildTableName() {
		return childTableName;
	}

	public void setChildTableOwner(String childTableOwner) {
		this.childTableOwner = childTableOwner;
	}

	public String getChildTableOwner() {
		return childTableOwner;
	}
	
	
}
