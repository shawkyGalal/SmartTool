package com.smartValue.database.map.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.SysCode;
import com.smartValue.database.map.SysCodeMaster;
import com.smartValue.database.map.TableMaintMaster;

public class MainCodesService extends ModuleServices {

	public MainCodesService(DbServices pmDbServices) {
		super(pmDbServices);
	}

	@Override
	public void afterModuleRemoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SysCodeMaster.DB_TABLE_NAME , this.getDbServices());
	}

	@Override
	public String getOrginalQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	/****** This section for global page *****
    private TableMaintMaster currentTable = null ;
	
	public TableMaintMaster getCurrentTable()
	{
		if(currentTable == null)
		{
			DbTable dbTable = new DbTable("SYS_CODE");
			dbTable.setTableOwner("ICDB");
			currentTable = this.getTableMaintMasterServices().getTableMaintMaster(dbTable);
		}
		return currentTable;
	}
	
	*************************************/

	
//**************** Select Items *******
	
	private ArrayList<SelectItem> mainCodesItems = null;
	public ArrayList<SelectItem> getMainCodesItems()
	{
		if(mainCodesItems == null)
		{
			String query = "Select T.CODE_ID, T.CODE_NAME_, T.CODE_NAME from "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SYS_CODE t where T.TBL_ID = 'NCA_MAIN_CODES'";
			mainCodesItems = this.getDbServices().getSelectItems(query, true);
		}
		return mainCodesItems;
	}
	
	private String selectedMainCode = null;
	public void setSelectedMainCode(String selectedMainCode) {
		this.selectedMainCode = selectedMainCode;
		this.calcMainCodeDs();
	}

	public String getSelectedMainCode() {
		return selectedMainCode;
	}
	
	private void calcMainCodeDs()
	{
		try{
			String query = "Select T.ROWID, t.* from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SYS_CODE t where T.TBL_ID = '" + this.selectedMainCode + "'";
			selectedMainCodeDataSet = this.getDbServices().queryForDataSet(query, SysCode.class);
		}catch (Exception e) {
			
		}
	}
//***************************************
	private DataSet selectedMainCodeDataSet = null;
	public void setSelectedMainCodeDataSet(DataSet selectedMainCodeDataSet) {
		this.selectedMainCodeDataSet = selectedMainCodeDataSet;
	}

	public DataSet getSelectedMainCodeDataSet() {
		if(selectedMainCodeDataSet == null)
		{
			selectedMainCodeDataSet = this.getDbServices().queryForDataSet(TableMaintMaster.getAllItemsQuery(SysCode.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) +" Where 1<>1", SysCode.class );
		}
		return selectedMainCodeDataSet;
	}
	
	public void addNewLockupValue()
	{
		try {
			selectedMainCodeDataSet.addNew();
			
			if(nextSequence.intValue() == 0)
			{
				nextSequence = this.getNextCodeIdValue();
			}
			
			SysCode currentItem = (SysCode)selectedMainCodeDataSet.getCurrentItem();
			currentItem.setTblIdValue(this.selectedMainCode);
			currentItem.setCodeIdValue(String.valueOf(nextSequence));
			nextSequence = new BigDecimal(nextSequence.toString()).add(new BigDecimal(1));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	BigDecimal nextSequence = new BigDecimal(0);
	public void saveAllCodes() throws Exception
	{
		selectedMainCodeDataSet.saveAll();
	}
	
	public void revertChanges()
	{
		this.nextSequence = new BigDecimal(0);
		selectedMainCodeDataSet.revertAll();
	}
	
	private BigDecimal getNextCodeIdValue()
	{
		String query = "Select MAX(t.CODE_ID) as CODE_ID from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SYS_CODE t where T.TBL_ID = '" + this.selectedMainCode + "'";
		
		SysCode sysCode = (SysCode)this.getDbServices().queryForList(query, SysCode.class).get(0);
		String value = sysCode.getAttributeValue(SysCode.CODE_ID) == null ? null : String.valueOf(sysCode.getAttributeValue(SysCode.CODE_ID));
		BigDecimal nextSequence = value != null? new BigDecimal(value).add(new BigDecimal(1)) : new BigDecimal(1);
		return nextSequence;
	}
}
