package com.implex.database.map.services;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.map.SysFrm;
import com.implex.database.map.SysObjText;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.sysObjTextProtectionType.SysObjTextPropertyController;


public class SysFrmServices extends ModuleServices{
	
	public SysFrmServices(DbServices pmDbServices) {
		super(pmDbServices);
	}

	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}

	private SysObjText sysObjTextTObeModified ;

	
	

	public static final String GLOBAL_PAGE_PATH = "/SWAF/jsp/globalPage.xhtml";
	public static final String SQL_EXECUTER_PAGE = "/SWAF/jsp/sqlExecuter.xhtml";
	private String editObjectTxtLabl = null;
	private boolean editObjectTxt = false;
	
	
		
	
 @Override 
	public String getOrginalQuery()	
	{
		return  TableMaintMaster.getAllItemsQuery(SysFrm.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER) + " Where T.FRM_NAME like '%.xhtml' " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return SysFrm.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}


	
	
	public void setSysObjTextTObeModified(SysObjText sysObjTextTObeModified) {
		this.sysObjTextTObeModified = sysObjTextTObeModified;
	}
	public SysObjText getSysObjTextTObeModified() {
		return sysObjTextTObeModified;
	}
	public String getFrmObjTxt(String pm_frmName, String pm_objName)
	 {
		SysObjText sysObjText = getSysObjTxt(pm_frmName, pm_objName);	
		return  (sysObjText != null) ? sysObjText.getFrmObjTxtValue() : pm_objName; 
	 } 
	
	public SysObjText getSysObjTxt(String pm_frmName, String pm_objName)
	 {
		SysFrm sysFrm = getSysFrm(pm_frmName); 
		return (sysFrm != null)? sysFrm.getSysObjTxt(pm_objName) : null;
		 
	 } 
	
	private SysFrm getSysFrm(String pm_frmName)
	{
		int userlang = this.getDbServices().getLoggedUserLang() ; //this.getDbService().getLoggedUserLang() ;
		String sysFrmKey = pm_frmName+userlang;
		SysFrm result = null; 
		if (ApplicationContext.getListOfUsedSysFrms().containsKey(sysFrmKey))
		{
			return ApplicationContext.getListOfUsedSysFrms().get(sysFrmKey);
		}
		else
		{
			try {
				DbServices dbs =  this.getDbServices();
				result =   (SysFrm)(dbs.queryForList("select f.* from sys_frm  f " +
						" where F.FRM_NAME = '" +pm_frmName+ "' and F.FRM_LNG_ID = "+ userlang,
						SysFrm.class)).get(0);
			} catch (Exception e) {
				e.printStackTrace();
				this.getMessageCommnunicatorService().sendExceptionToUser(e);
			}
			if (result != null)
			ApplicationContext.getListOfUsedSysFrms().put(sysFrmKey, result);
			return result;
		}
	}


	public String getEditObjectTxtLabl() {
		if (editObjectTxt == true)
			editObjectTxtLabl = getFrmObjTxt("/templates/include/toolBar.xhtml","btnDisableMaintainObj" );
		else
			editObjectTxtLabl = getFrmObjTxt("/templates/include/toolBar.xhtml","btnEnableMaintainObj");
	
		return editObjectTxtLabl;
	}

	public void setEditObjectTxt(boolean editObjectTxt) {
		this.editObjectTxt = editObjectTxt;
	}

	public boolean isEditObjectTxt() {
		return editObjectTxt;
	}


	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SysFrm.DB_TABLE_NAME , this.getDbServices());
	}
	ArrayList<SelectItem> allAvailableProtectionTypes ;
	public ArrayList<SelectItem> getAllAvailableProtectionTypes()
	{
		if (allAvailableProtectionTypes==null)
		{
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbServices());
		allAvailableProtectionTypes =  ims.getAllAvailableImpls(SysObjTextPropertyController.INTERFACE_NAME);
		}
		return  allAvailableProtectionTypes ;
	}

	private String sysObjTextToBeRendered;

	public void setSysObjTextToBeRendered(String sysObjTextToBeRendered) {
		this.sysObjTextToBeRendered = sysObjTextToBeRendered;
	}
	public String getSysObjTextToBeRendered() {
		return sysObjTextToBeRendered;
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
