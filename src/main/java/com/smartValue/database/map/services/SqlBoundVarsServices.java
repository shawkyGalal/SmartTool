package com.smartValue.database.map.services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SqlBoundVars;
import com.smartValue.database.map.TableMaintMaster;
public class SqlBoundVarsServices extends ModuleServices{
	
	public SqlBoundVarsServices(DbServices pmDbServices) {
		super(pmDbServices);
	}
	private SqlBoundVars sqlBoundVarTObeModified = null; 
	//private SelectItem[] allSqlBoundVars = null;
	private String selectedSqlBoundVar = null;
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	@Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from SQL_BOUND_VARS t " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return SqlBoundVars.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	
	public ArrayList<SelectItem> getLoggedUserSqlBoundVars() throws Exception {
		ArrayList<SelectItem> allSqlBoundVars = null;
		
			String query = "select T.BOUND_VAR_NAME, t.title, t.title from SQL_BOUND_VARS t where T.OWNER='" +
					this.getDbServices().getLoggedUser().getUsrNameValue().toUpperCase()+"'";
			allSqlBoundVars = this.getDbServices().getSelectItems(query);
		
		return allSqlBoundVars;
	}
	
	public void appendToQuery()
	{
		ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getModuleServiceContatiner();
		msc.getCurrentActiveModule().getLinkedSysMnuItem().setMnuSqlValue(
				msc.getCurrentActiveModule().getLinkedSysMnuItem().getMnuSqlValue() + this.getSelectedSqlBoundVar());
	}
	
	public List<PersistantObject> getSqlBoundVarByNames(ArrayList<String> names)
	{
		List<PersistantObject> result = new ArrayList<PersistantObject>();
		if (names.isEmpty()) return result;
		String comaSeperated = "";
		boolean first = true ; 
		for (String str : names)
		{
			comaSeperated += ((first)? "":", ")  +  "'"  + SqlBoundVars.VAR_DELIMITER +str.toUpperCase() + "'";
			first = false;
		}
		
		
		String q= TableMaintMaster.getAllItemsQuery (SqlBoundVars.DB_TABLE_NAME , this.getDbTable().getTableOwner()) 
		+ " Where upper(" + SqlBoundVars.OWNER  + ") = '" + this.getDbServices().getLoggedUser().getUsrNameValue().toUpperCase() + "'"  
		+ " And upper( " + SqlBoundVars.BOUND_VAR_NAME + ") in ( " + comaSeperated + " )"
		+ " And " + SqlBoundVars.ACTIVE + " = 'Y'" ; 
		DataSet ds = null;
		try {
				ds = this.getDbServices().queryForDataSet(q, SqlBoundVars.class);
				result = ds.getPersistantObjectList();
		} catch (Exception e) {
			this.getMessageCommnunicatorService().sendMessageToUser(e.getMessage());
			e.printStackTrace();
		}
		 
		  return result ; 
	}
	public void setSqlBoundVarTObeModified(SqlBoundVars sqlBoundVarTObeModified) {
		this.sqlBoundVarTObeModified = sqlBoundVarTObeModified;
	}
	public SqlBoundVars getSqlBoundVarTObeModified() {
		return sqlBoundVarTObeModified;
	}
	public void setSelectedSqlBoundVar(String selectedSqlBoundVar) {
		this.selectedSqlBoundVar = selectedSqlBoundVar;
	}
	public String getSelectedSqlBoundVar() {
		return selectedSqlBoundVar;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SqlBoundVars.DB_TABLE_NAME , this.getDbServices());
	}
	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
}