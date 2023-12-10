package com.implex.database;

import com.implex.database.map.TableMaintMaster;

public class DbTable {

	private String m_tableName;
	private String tableOwner ; 
	private TableMaintMaster tableMaintMaster ;
	private DbServices dbServices ; 

	public DbTable(String pm_owner , String pm_tableName , DbServices pm_dbServices) {
		this.m_tableName = pm_tableName;
		this.tableOwner = pm_owner ; 
		this.dbServices = pm_dbServices ; 
	}

	public String getTableName() {
		return m_tableName;
	}

	public void setTableName(String tableName) {
		this.m_tableName = tableName;
	}
	
	public  TableMaintMaster  calcTableMaintMaster( )  {
		                                 
		return  this.getDbServices().getModuleServiceContainer().getTableMaintServices().getTableMaintMaster(this); 
	
	}

	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
	}

	public String getTableOwner() {
		return tableOwner;
	}

	public TableMaintMaster getTableMaintMaster() {
		if (tableMaintMaster == null)
		{
			if (   this.getTableOwner() == null 
				||  this.getTableOwner().toUpperCase().equals("SYS") 
				|| this.getTableOwner().toUpperCase().equals("SYSTEM") )
			{
				tableMaintMaster = null ; 
			}
			else
			{
				tableMaintMaster = calcTableMaintMaster () ;
			}
		}
		return tableMaintMaster;
	}
	
	public boolean equals(Object m_dbTable)
	{
		boolean result = false ;
		if ( m_dbTable != null && m_dbTable instanceof DbTable )
		{
			DbTable dbt = (DbTable)m_dbTable ;
			if (dbt.getTableOwner() != null)
			{
			result = dbt.getTableOwner().equalsIgnoreCase(this.getTableOwner())  
					&& dbt.getTableName().equalsIgnoreCase(this.getTableName())  ;
			}
			else
			{
				result = dbt.getTableName().equalsIgnoreCase(this.getTableName()) ;
			}
		}
		return result ;
	}
	public int hashCode()
	{
		return this.toString().hashCode() ; 
	}
	
	public String toString()
	{
		return  ( (this.getTableOwner() != null)? (this.getTableOwner()+".") : "" )  + this.getTableName() ; 
	}

	public DbServices getDbServices() {
		return dbServices;
	}

	public void setDbServices(DbServices dbServices) {
		this.dbServices = dbServices;
	}
}
