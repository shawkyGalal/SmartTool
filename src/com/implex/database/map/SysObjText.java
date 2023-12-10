package com.implex.database.map;
import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DataSet;
import com.implex.database.DbForignKey;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.map.auto._SysObjText;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.security.SysObjTextSecurityControlHandlerImpl;
import com.implex.database.map.services.InterfaceImplServices;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.sysObjTextProtectionType.SysObjTextBooleanSimpleImpl;
import com.implex.database.sysObjTextProtectionType.SysObjTextBooleanUsingUserDefVar;
import com.implex.database.sysObjTextProtectionType.SysObjTextBooleanUsingUsersAndGroups;
import com.implex.database.sysObjTextProtectionType.SysObjTextPropertyController;
import com.implex.database.trigger.TriggerHandler;
import com.sideinternational.web.swaf.SWAF;

public class SysObjText extends _SysObjText {
	private static HashMap<String, DbForignKeyArray> childrenForignKeys ;
	private ModuleServicesContainer msc =  (ModuleServicesContainer) SWAF.getModuleServiceContatiner();
	private static final String EXCLUDED_ROLES_TABLE_NAME = "EXCLUDED_ROLES_DETAILS";
	private static final String SYS_OBJ_TEXT_TABLE_NAME= "SYS_OBJ_TEXT";
	private SysObjTextSecurityControlHandlerImpl securityControlHandlerImpl = new SysObjTextSecurityControlHandlerImpl();
	
	private static DbForignKeyArray getForignKeyForExcludedRoles()
	{
		return new DbForignKeyArray (new DbForignKey[]{new DbForignKey(SysObjText.ID, ExcludedRolesDetails.OBJECT_ID)} , false );
	}
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null) {
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(EXCLUDED_ROLES_TABLE_NAME, SysObjText.getForignKeyForExcludedRoles());
		}
		return childrenForignKeys;
	}
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return securityControlHandlerImpl; 
	}
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}

//	public String constractQueryForChildren(String childTableName , DbForignKey[] forignKeys )
//	{
//		String query = super.constractQueryForChildren(childTableName, forignKeys , null);
//		query += " and "+ ExcludedRolesDetails.TABLE_NAME +" = '"+SYS_OBJ_TEXT_TABLE_NAME+"'";
//		return query;
//	}

	public DataSet getExcludedRolesDS() throws Exception
	{
		String extraFilter = " and "+ ExcludedRolesDetails.TABLE_NAME +" = '"+SYS_OBJ_TEXT_TABLE_NAME+"'";
		return getChildrenDataSet(EXCLUDED_ROLES_TABLE_NAME , ExcludedRolesDetails.class , false , extraFilter);
	}
	
	public void setExcludedGroupsIds(String[] pm_selectedGroups) {
		try {
			getExcludedRolesDS().markAllToBeDeleted();
			
			for (int i = 0; i < pm_selectedGroups.length; i++) {
				if (pm_selectedGroups[i] != null) {
					this.getExcludedRolesDS().addNew();
					((ExcludedRolesDetails) this.getExcludedRolesDS().getCurrentItem()).setTableNameValue(SYS_OBJ_TEXT_TABLE_NAME);
					((ExcludedRolesDetails) this.getExcludedRolesDS().getCurrentItem()).setSecgroupIdValue(pm_selectedGroups[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}

	public String[] getExcludedGroupsIds() {
		String[] excludedGroupsIds=null;
			try {
				if(this.getParent() !=null)
				{
					int size = this.getExcludedRolesDS().getPersistantObjectList().size();
					excludedGroupsIds = new String[size];
					for (int i = 0; i < size; i++) {
						excludedGroupsIds[i] = ((ExcludedRolesDetails) this.getExcludedRolesDS().getPersistantObjectList()
								.get(i)).getSecgroupIdValue();
					}
				}
			} catch (Exception e) {
				msc.getMessageCommnunicatorService().sendMessageToUser(
						"Unable to get table maintainance details groups due to "+ e.getMessage());
			}
		return excludedGroupsIds;
	}
	
	public void setAccessType(String pm_accessType) {
		try {
			String currentAccessType = this.getAccessType();
			if(currentAccessType != null && !currentAccessType.equals(pm_accessType))
				this.getExcludedRolesDS().markAllToBeDeleted();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultAccessTypeValue(pm_accessType);
	}
	public String getAccessType() {
		return this.getDefaultAccessTypeValue();
	}

	@Override
	public AttributeChangeValidator getAttributeChangeValidator(
			SecUsrDta pmSecUserData, PersistantObject pmPo, String pmKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isRenderCalcuSimple()
	{
		return this.getRenderBooleanImpl() instanceof SysObjTextBooleanSimpleImpl ;
	}
	
	public boolean isRenderCalcuUdv()
	{
		return this.getRenderBooleanImpl() instanceof SysObjTextBooleanUsingUserDefVar ;
	}
	
	public boolean isRenderCalcuUsers()
	{
		return this.getRenderBooleanImpl() instanceof SysObjTextBooleanUsingUsersAndGroups ;
	}
	
	public boolean isDisabledCalcuSimple()
	{
		return this.getDisabledBooleanImpl() instanceof SysObjTextBooleanSimpleImpl ;
	}
	
	public boolean isDisabledCalcuUdv()
	{
		return this.getDisabledBooleanImpl() instanceof SysObjTextBooleanUsingUserDefVar ;
	}
	
	public boolean isDisabledCalcuUsers()
	{
		return this.getDisabledBooleanImpl() instanceof SysObjTextBooleanUsingUsersAndGroups ;
	}
	
	private SysObjTextPropertyController disabledBooleanImpl = null ;	

	public void setDisabledBooleanImpl(SysObjTextPropertyController pm_disabledBooleanImpl) {
		this.disabledBooleanImpl = pm_disabledBooleanImpl;
	}

	public SysObjTextPropertyController getDisabledBooleanImpl() {
		if (disabledBooleanImpl == null)
		{
			disabledBooleanImpl =   this.calcSysObjTextBooleanType(this.getDisableProtectionTypeValue()) ;
		}
		return disabledBooleanImpl; 
	}
	
	public void setDisableProtectionTypeValue(java.lang.String   pm_DisabledProtectionType){
		super.setDisableProtectionTypeValue(pm_DisabledProtectionType);
		String implenCalssCode = this.getDisableProtectionTypeValue();
		SysObjTextPropertyController disabledBooleanImpl = this.calcSysObjTextBooleanType(implenCalssCode);
		this.setDisabledBooleanImpl(disabledBooleanImpl);
	}

	private SysObjTextPropertyController renderBooleanImpl = null ;

	public void setRenderBooleanImpl(SysObjTextPropertyController renderboolenImpl) {
		this.renderBooleanImpl = renderboolenImpl;
	}

	public SysObjTextPropertyController getRenderBooleanImpl() {
		if (renderBooleanImpl == null)
		{
			renderBooleanImpl =   this.calcSysObjTextBooleanType(this.getRenderProtectionTypeValue()) ;
		}
		return renderBooleanImpl; 
	}
	
	public void setRenderProtectionTypeValue(java.lang.String   pm_renderProtectionType){
		super.setRenderProtectionTypeValue(pm_renderProtectionType);
		String implenCalssCode = this.getRenderProtectionTypeValue();
		SysObjTextPropertyController renderBooleanImpl = this.calcSysObjTextBooleanType(implenCalssCode);
		this.setRenderBooleanImpl(renderBooleanImpl);
	}
	
	private SysObjTextPropertyController calcSysObjTextBooleanType(String implenCalssCode)
	{
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbService());
		SysObjTextPropertyController attBooleanType = new SysObjTextBooleanSimpleImpl();
		SysInterfaceImplementors sysImpl = 
		ims.getImplementorByCode(implenCalssCode,SysObjTextPropertyController.INTERFACE_NAME);
		attBooleanType = (sysImpl!=null)? (SysObjTextPropertyController)sysImpl.getImplInstance() : new SysObjTextBooleanSimpleImpl() ;
		return attBooleanType;
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