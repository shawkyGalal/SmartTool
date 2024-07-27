package com.smartValue.database;

import java.io.Serializable;

import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.services.TableMaintMasterServices;

public class DbForignKeyArray implements Serializable{
	
	private DbForignKey[]   dbForignKeyArray ; 
	private boolean onCascadeDelete = false ;
	//private String childOwner ;
	private String name ;
	private String tableName ;
	
	private boolean generateDsTabInMaintForm ;

	private TableMaintMaster childTable ;
	private DbServices dbService ;
	
	public DbForignKeyArray(DbForignKey[]   pm_dbForignKeyArray  , boolean pm_onCascadeDelete)
	{
		this.dbForignKeyArray = pm_dbForignKeyArray ;
		this.onCascadeDelete = pm_onCascadeDelete ;
		this.tableName = pm_dbForignKeyArray[0].getChildTableName();
	}
	
	public String getTableName()
	{
		return this.tableName;
	}
	public DbForignKey[] getDbForignKeyArray() {
		return dbForignKeyArray;
	}

	public void setOnCascadeDelete(boolean onCascadeDelete) {
		this.onCascadeDelete = onCascadeDelete;
	}

	public boolean isOnCascadeDelete() {
		return onCascadeDelete;
	}
//	public void setChildOwner(String childOwner) {
//		this.childOwner = childOwner;
//	}
//	public String getChildOwner() {
//		return childOwner;
//	}
	
	public String getChildFullTableName()
	{
		String result = null ;
		String owner = this.getDbForignKeyArray()[0].getChildTableOwner() ; //this.getChildOwner() ;
		result=   (owner != null) ? owner+"."+ tableName : tableName ;
		return result ;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public TableMaintMaster getChildTable() {
		if (childTable == null)
		{
			TableMaintMasterServices tmms = new TableMaintMasterServices(this.getDbService()); 
			tmms.setTableOwner(this.getDbForignKeyArray()[0].getChildTableOwner());
			tmms.setTableName(this.getDbForignKeyArray()[0].getChildTableName());
			childTable = tmms.getCurrentTable();
		}
		return childTable;
	}
	public void setGenerateDsTabInMaintForm(boolean generateDsTabInMaintForm) {
		this.generateDsTabInMaintForm = generateDsTabInMaintForm;
	}
	public boolean isGenerateDsTabInMaintForm() {
		return generateDsTabInMaintForm;
	}
	public void setGenerateMapForChildTable(boolean generateMapForChildTable) {
		this.generateMapForChildTable = generateMapForChildTable;
	}
	public boolean isGenerateMapForChildTable() {
		return generateMapForChildTable;
	}
	public DbServices getDbService() {
		return dbService;
	}

	public void setDbService(DbServices dbService) {
		this.dbService = dbService;
	}
	private boolean generateMapForChildTable ;
	

}
