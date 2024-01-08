package com.implex.database.map.security.PersisObjectSecurityController;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.audit.PersistentObjectAuditor;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysTablesSecurityControler;
import com.implex.database.map.TableMaintMaster;

public class PoSecurityContollerUserGroupImpl implements IPersisObjectController {

	
	public boolean isCreatable(PersistantObject pm_po) {
		return this.isAllowd(pm_po, PersistentObjectAuditor.INSERT_TRANSACTION);
	}

	
	public boolean isDeletable(PersistantObject pm_po) 
	{
		return this.isAllowd(pm_po, PersistentObjectAuditor.DELETE_TRANSACTION);
	}
	
	
	public boolean isUpdatable(PersistantObject pm_po) {
		return this.isAllowd(pm_po, PersistentObjectAuditor.UPDATE_TRANSACTION);
	
	}
	private boolean isAllowd(PersistantObject pm_po , String pm_operation )
	{
		boolean result = false;
		SecUsrDta loggedUser = pm_po.getDbService().getLoggedUser();
		TableMaintMaster tmm = pm_po.getTableMaintMaster();
		DataSet ds = null ; 
		if (pm_operation.equals(PersistentObjectAuditor.INSERT_TRANSACTION))
		{ds = tmm.getSysTablesSecurityControlerForCreateDS() ;} //calcSysTablesSecurityControlerDS(pm_operation);
		else if (pm_operation.equals(PersistentObjectAuditor.UPDATE_TRANSACTION))
		{ds = tmm.getSysTablesSecurityControlerForUpdateDS() ;}
		else if (pm_operation.equals(PersistentObjectAuditor.DELETE_TRANSACTION))
		{ds = tmm.getSysTablesSecurityControlerForDeleteDS() ;}
		
		boolean loogedUserInDs = this.isUserInDs(loggedUser , ds );
		
		
		if (tmm.getUpdateListForAllowed().getBooleanValue())
		{
			result = loogedUserInDs ;
		}
		else 
		{
			result = !loogedUserInDs ;
		}
		return  result;	
	}

	private boolean isUserInDs(SecUsrDta pm_secUser , DataSet pm_ds) {
		boolean result = false ;
		ArrayList<PersistantObject> sus = pm_ds.getPersistantObjectList();
		String userName = pm_secUser.getUsrNameValue() ; 
		for(PersistantObject po : sus)
		{
			if (po!= null && po instanceof SysTablesSecurityControler )
			{
				SysTablesSecurityControler stsc = (SysTablesSecurityControler)po ; 
				if (userName.equals(stsc.getExecludedUsersValue()))
				{
					result = true ; 
					break;
				}
			}
		}
		return result ; 
	
	}

	

	
	public String getMaintFormUrl() {
		return "/templates/include/securityControllersMaintForms/usersSecurityControllerMaintForm.xhtml";

	}

}

