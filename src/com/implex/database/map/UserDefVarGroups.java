package com.implex.database.map;


import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DataSet;
import com.implex.database.DbForignKey;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._UserDefVarGroups;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;

public class UserDefVarGroups extends _UserDefVarGroups {
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	private static final String HR_USER_DEF_VAR = "USR_DEF_VAR";
	public 	ArrayList<SelectItem> grpUserDefienedVariables;
	
	private static DbForignKeyArray ForignKeyForUserDefVarDta = 
		new DbForignKeyArray(  new DbForignKey[] {
				new DbForignKey(UserDefVarGroups.UDVG_CODE, UsrDefVar.UDVG_CODE , UsrDefVar.DB_TABLE_NAME , UsrDefVar.DB_TABLE_OWNER  ),
				new DbForignKey(UserDefVarGroups.TABLE_NAME, UsrDefVar.TABLE_NAME , UsrDefVar.DB_TABLE_NAME , UsrDefVar.DB_TABLE_OWNER),
				new DbForignKey(UserDefVarGroups.TABLE_OWNER, UsrDefVar.TABLE_OWNER , UsrDefVar.DB_TABLE_NAME , UsrDefVar.DB_TABLE_OWNER)} , false );
	
	
	public DataSet getUserDefVarDS()  {
		DataSet result = null ; 
		try {
			result=  getChildrenDataSet(HR_USER_DEF_VAR, UsrDefVar.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null) 
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(HR_USER_DEF_VAR, UserDefVarGroups.ForignKeyForUserDefVarDta);
		}
		return childrenForignKeys;
	}
	
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return null;
	}
 
	 private static TriggerHandler triggerHandler = null; 
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		 boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); 
		 if (triggerHandler == null && auditable ) 
			{ 
				triggerHandler = new AuditInDbTriggerHandler(); 
			} 
		 return triggerHandler; 
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		return null; 
	}
	
	/**
	 * This method adds a new user def var for this group 
	 */
	public void addUsrDefVar(){
		try 
		{
			DataSet udvDs = this.getUserDefVarDS();
			udvDs.addNew();
			((UsrDefVar)udvDs.getCurrentItem()).setUdvVarValue("newVar");
			((UsrDefVar)udvDs.getCurrentItem()).getUdvVarField().setValue("select 0 Result from dual");
						
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 
	 * @return an Array of {@link SelectItem} used in the JSF Layer 
	 * @throws Exception
	 */
	public ArrayList<SelectItem> getUserDefienedVariablesByGrpCodeName(String pm_udvGrpCodeName) throws Exception
	{
		if (grpUserDefienedVariables == null) 
		{
			String delimiter = PersistantObject.USER_DEFINED_DELIMTER; 
			String query = "select '"+delimiter+"'|| "+UsrDefVar.UDV_VAR+", "+UsrDefVar.UDV_VAR+" , "+UsrDefVar.UDV_VAR+"  from "+UsrDefVar.DB_TABLE_NAME   +
					" where "+UsrDefVar.TABLE_OWNER+" = '"+this.getTableOwnerValue()+"' and "+UsrDefVar.TABLE_NAME+" = '"  + this.getTableNameValue() +"'" + " and " + UsrDefVar.UDVG_CODE + " = '" +  pm_udvGrpCodeName +"'" ; 
			grpUserDefienedVariables = this.getDbService().getSelectItems(query,false);
		}
		return grpUserDefienedVariables;
	}

	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
