package com.smartValue.database.map.services;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SysCode;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.UsrDefVar;

public class UsrDefVarServices extends ModuleServices{
	
	public UsrDefVarServices(DbServices pmDbServices) {
		super(pmDbServices);
	}

	private UsrDefVar toBeModifiedUsrDefVar;
	private String selectedTableName = "USR_DEF_VAR";
	private String selectedTableOwner = TableMaintMaster.CDB_SCHEMA_OWNER;
	private boolean validFormula ;
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	@Override 
	public String getOrginalQuery()	
	{
		return  TableMaintMaster.getAllItemsQuery(UsrDefVar.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER) ;
	}
	@Override
	public Class getPersistentClass()
	{
		return UsrDefVar.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}

	 public UsrDefVar getUserDefinedVariable(String userDefinedvariableName)  {
			String q = TableMaintMaster.getAllItemsQuery( UsrDefVar.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) +" Where "+ UsrDefVar.UDV_VAR + " = '" + userDefinedvariableName + "'"; 
			UsrDefVar result = null;
			try {
				result = (UsrDefVar) this.getDbServices().queryForList(q, UsrDefVar.class).get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	 public UsrDefVar getUserDefinedVariable(String userDefinedvariableName , String pm_TableName)  {
			String q = TableMaintMaster.getAllItemsQuery( UsrDefVar.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) +" Where "+ UsrDefVar.UDV_VAR + " = '" + userDefinedvariableName + "' AND t.TABLE_NAME = '"+pm_TableName+"'"; 
			UsrDefVar result = null;
			try {
				result = (UsrDefVar) this.getDbServices().queryForList(q, UsrDefVar.class).get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	 /**
	  * This method to get UserDefVar PO
	  * @param pm_udvCode the UsrDefVar's code
	  * @return Object of UsrDefVar class
	  */
	 public UsrDefVar getUserDefinedVariableByUdvCode(BigDecimal pm_udvCode)  {
		 UsrDefVar result = null;
		 if(pm_udvCode != null)
		{
			String q = TableMaintMaster.getAllItemsQuery( UsrDefVar.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) +" Where "+ UsrDefVar.UDV_CODE + " = " + pm_udvCode ; 
			
			try {
				result = (UsrDefVar) this.getDbServices().queryForList(q, UsrDefVar.class).get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	 }
	public void setToBeModifiedUsrDefVar(UsrDefVar selectedUsrDefVar) {
		this.toBeModifiedUsrDefVar = selectedUsrDefVar;
	}
	public UsrDefVar getToBeModifiedUsrDefVar() {
		return toBeModifiedUsrDefVar;
	}

	public void checkFomulaSyntax(String query){
			ArrayList<String> vars= PersistantObject.getListOfVariables(query, PersistantObject.SYS_DEFINED_DELIMTER);
			if(vars != null)
				for(String param : vars)
				{
					query = query.replace(PersistantObject.SYS_DEFINED_DELIMTER+param, " null ");
				}
			
			vars= PersistantObject.getListOfVariables(query, PersistantObject.USER_DEFINED_DELIMTER);
			if(vars != null)
				for(String param : vars)
				{
					query = query.replace(PersistantObject.USER_DEFINED_DELIMTER+param, " null ");
				}
			DataSet ds = this.getDbServices().queryForDataSet(query , null);
			try{
				if(ds.getCurrentItem() != null )
			{
				this.setValidFormula(true);
			}else 		
			this.setValidFormula(false);
			}catch (Exception e) {
				setValidFormula(false);
			}
		}
	
//	public void testQuery (Object po)
//	{
//		if (po instanceof UsrDefVar) {
//			UsrDefVar usrDefVar = (UsrDefVar) po;
//			this.checkFomulaSyntax(usrDefVar.getUdvVarFieldValue());
//			usrDefVar.setValidityValue(validFormula ? "Y" : "N");
//		}else if (po instanceof HrMasBenData) {
//			HrMasBenData hrMasBenData = (HrMasBenData) po;
//			hrMasBenData.getValidFormulaSyntax();
//		}else if (po instanceof TableMaintDetails) {
//			TableMaintDetails tmd = (TableMaintDetails) po;
//			checkFomulaSyntax(tmd.getSecurityQueryValue());
//		}
//	}
	public String getSelectedTableName() {
		return selectedTableName;
	}

	public void setSelectedTableName(String pm_tableName) {
		this.selectedTableName = pm_tableName;
	}
	
	public List<SelectItem> getListOfColumnsNames()
	{
		ModuleServicesContainer msc = this.getDbServices().getModuleServiceContainer(); 
		TableMaintMaster tmm = msc.getListOfUsedTables().get(this.getSelectedTableName());
		return tmm == null? new ArrayList<SelectItem>() : tmm.getListOfColumnsNames();
	}
	

	public List<SelectItem> getListOfColumnsDisplayNames()
	{
		TableMaintMaster tmm ; 
		DbTable dbt = new DbTable(selectedTableOwner , this.getSelectedTableName() , this.getDbServices()) ;
		tmm = this.getTableMaintMasterServices().getTableMaintMaster(dbt) ;
		return  tmm.getListOfColumnsDescAutoLang();
	}
	
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , UsrDefVar.DB_TABLE_NAME , this.getDbServices());
	}
	
	private String selectedSysVar = null;
	private String selectedLoggedUserProperty = null;
	private String selectedUsrDefVar = null;
	private String selectedUserDefVarsExceptMe = null;
	private boolean disabledTablesNamesList = false;
	
	public String getSelectedSysVar() {
		return selectedSysVar;
	}
	public void setSelectedSysVar(String selectedSysVar) {
		this.selectedSysVar = selectedSysVar;
	}
	public String getSelectedLoggedUserProperty() {
		return selectedLoggedUserProperty;
	}
	public void setSelectedLoggedUserProperty(String selectedLoggedUserProperty) {
		this.selectedLoggedUserProperty = selectedLoggedUserProperty;
	}
	public String getSelectedUsrDefVar() {
		return selectedUsrDefVar;
	}
	public void setSelectedUsrDefVar(String selectedUsrDefVar) {
		this.selectedUsrDefVar = selectedUsrDefVar;
	}
	public String getSelectedUserDefVarsExceptMe() {
		return selectedUserDefVarsExceptMe;
	}
	public void setSelectedUserDefVarsExceptMe(String selectedUserDefVarsExceptMe) {
		this.selectedUserDefVarsExceptMe = selectedUserDefVarsExceptMe;
	}
	public void setDisabledTablesNamesList(boolean disabledTablesNamesList) {
		this.disabledTablesNamesList = disabledTablesNamesList;
	}
	public boolean isDisabledTablesNamesList() {
		return disabledTablesNamesList;
	}
	
	public boolean isValidFormula() {
		return validFormula;
	}
	public void setValidFormula(boolean validFormula) {
		this.validFormula = validFormula;
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
