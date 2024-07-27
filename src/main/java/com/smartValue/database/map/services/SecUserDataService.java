package com.smartValue.database.map.services;


import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.PersistentObjectAuditor;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintMaster;




public class SecUserDataService extends ModuleServices{
	
	public SecUserDataService(DbServices pmDbServices) {
		super(pmDbServices);
	}
	private DataSet users ; 
	private DataSet groups ; 
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	@Override
	public Class getPersistentClass()
	{
		return  SecUsrDta.class;	
	}
	

	@Override
	public String getOrginalQuery()
	{
		// I will not use the default DataSet
		return  "SELECT t.rowid, t.* FROM "+TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t where rownum = 1"  ;
	}
	
	public SecUsrDta getUserByUserNameAndEmail (String userName , String userEmail ) throws Exception
	{	
		SecUsrDta result = null ; 
		result =  getUserByUiqueKey(SecUsrDta.USR_NAME , userName) ;
		if (result.getUsrEmailValue().equalsIgnoreCase(userEmail)) 
		{
			return result ; 
		}
		else throw new Exception ("Email Address Does Not Match     الايميل غير متطابق مع اسم المستخدم بالنظام") ; 
	}
	
	public SecUsrDta getUserByUserName (String userName ) throws Exception
	{
		return getUserByUiqueKey(SecUsrDta.USR_NAME , userName) ; 
	}
	
	public SecUsrDta getUserByEmail (String m_userEmail ) throws Exception
	{
		return getUserByUiqueKey(SecUsrDta.USR_EMAIL , m_userEmail) ; 
	}
	public SecUsrDta getUserByUiqueKey (String m_ukName , String m_ukValue) throws Exception
	{
		SecUsrDta result = null ;
		String q = "SELECT t.rowid, t.* FROM "+TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t  where upper(t." + m_ukName + ") = upper('" + m_ukValue +"')"  ;
		try
		{
			result = (SecUsrDta) this.getDbServices().queryForDataSet(q, SecUsrDta.class ).getPersistantObjectList().get(0);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception ( "Unable to find Details of User " + m_ukValue.toUpperCase() + "  Due to : " + e.getMessage()); 
		}
		
		return result;
	}

	//......................................................................................
	private DataSet getUsersOfType(Integer pm_userType) throws Exception
	{
		String q = "SELECT t.rowid, t.* FROM "+TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t" + ((pm_userType==null)? "" : "  where t."+SecUsrDta.USR_TYPE + " = '" + pm_userType +"'" ) ;
		return this.getDbServices().queryForDataSet(q, SecUsrDta.class);
	}
	
	ArrayList<SelectItem> allAvailableUsers ; 
	public ArrayList<SelectItem> getAllAvailableUsers() throws Exception {
		if (allAvailableUsers == null)
		{	
			allAvailableUsers = new ArrayList<SelectItem>() ;
			List<PersistantObject> ListOfAllAvailableUsers= new ArrayList<PersistantObject>(); 
		
			ListOfAllAvailableUsers= this.getUsersOfType(null).getPersistantObjectList();
			for (PersistantObject po : ListOfAllAvailableUsers)
			{
				SecUsrDta   userData = (SecUsrDta)  po;
				allAvailableUsers.add(new SelectItem(userData , userData.getUsrNameValue()) );
			}
		}
		return allAvailableUsers ; 
	}
	//..................................................................................................
	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	
	public  SelectItem[] getAllAvailableOperations()
	{
		SelectItem[] allAvailable = new SelectItem[3];
		allAvailable[0] = new SelectItem(PersistentObjectAuditor.UPDATE_TRANSACTION  , "Update Item");
		allAvailable[1] = new SelectItem( PersistentObjectAuditor.INSERT_TRANSACTION , "Create New Item");
		allAvailable[2] = new SelectItem(PersistentObjectAuditor.DELETE_TRANSACTION , "Delete Item");
		
		return allAvailable;
	}

	public DataSet getUsers()
	{	if (users == null)
		{
			try
			{
				users = this.getUsersOfType(SecUsrDta.USER);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return users;
	}
	
	public DataSet getGroups()
	{	
		if (groups == null)
		{
			try
			{
				groups = this.getUsersOfType(SecUsrDta.GROUP);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return groups;
	}
	private SelectItem[] availableGroups = null;

	public SelectItem[] getAvailableGroups() {
		if(availableGroups == null){
			List<PersistantObject> result = this.getGroups().getPersistantObjectList();
							
			availableGroups = new SelectItem[result.size()];
			for (int i = 0; i < result.size(); i++) {
				SecUsrDta secGroup = (SecUsrDta) result.get(i);
				availableGroups[i] = new SelectItem(secGroup, secGroup.getUsrNameValue());
			}
		}
		return availableGroups;
	}
	
	public ArrayList<SelectItem> getAllSecUsersNames()
	{   String loginName= this.getDbServices().getLoggedUser().getUsrNameValue();// ApplicationContext.getLoggedUserSession().getLoginName();
		return this.getDbServices().getSelectItems("select USR_NAME,USR_NAME,USR_NAME from  "+TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA  where USR_NAME !='"+loginName+"'",true);
	}

	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SecUsrDta.DB_TABLE_NAME , this.getDbServices());
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

