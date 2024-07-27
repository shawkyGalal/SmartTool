package com.smartValue.database.map;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.auto._SysFrm;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SysFrmSecurityControlHandlerImpl;
import com.smartValue.database.trigger.TriggerHandler;

public class SysFrm extends _SysFrm {
	private static HashMap<String, DbForignKeyArray> childrenForignKeys ;
	private SysFrmSecurityControlHandlerImpl securityControlHandlerImpl = new SysFrmSecurityControlHandlerImpl();
	private static final String SYS_OBJ_TXT_TABLE_NAME = "SYS_OBJ_TEXT";
	
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
	private static DbForignKeyArray getForignKeyForSysObjText()
	{
		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SysFrm.FRM_NAME ,SysObjText.FRM_NAME),
				new DbForignKey(SysFrm.FRM_LNG_ID ,SysObjText.FRM_OBJ_LNG)} , false );
	}
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null)
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(SYS_OBJ_TXT_TABLE_NAME, SysFrm.getForignKeyForSysObjText());
		}
		return childrenForignKeys;
	}

	public DataSet getSysObjTextsDS() throws Exception
	{
		return getChildrenDataSet(SYS_OBJ_TXT_TABLE_NAME , SysObjText.class , false);
	}
	
	HashMap<String , SysObjText> sysObjectTextHashMap = null ;
	public HashMap<String , SysObjText> getSysObjectTextHashMap()
	{
		if (sysObjectTextHashMap == null)
		{
			sysObjectTextHashMap = new  HashMap<String , SysObjText>();
			List<PersistantObject> list = null;
			try {
				list = this.getSysObjTextsDS().getPersistantObjectList();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			for(PersistantObject po : list)
			{
				SysObjText sysObjText = (SysObjText) po;
				sysObjectTextHashMap.put(sysObjText.getFrmObjNameValue(), sysObjText);
			}
		}
		return  sysObjectTextHashMap; 
	}
	public SysObjText getSysObjTxt( String pm_objName)
	 {
		return getSysObjectTextHashMap().get(pm_objName);
		
	 } 
	
	public void addSysObjectText(){
		try {
			this.getSysObjTextsDS().addNew();
			((SysObjText)this.getSysObjTextsDS().getCurrentItem()).setFrmNameValue(this.getFrmNameValue());
			((SysObjText)this.getSysObjTextsDS().getCurrentItem()).setFrmObjNameValue("-");
			int loggedUserLang = this.getDbService().getLoggedUserLang() ; //ApplicationContext.getLoggedUserSession().getUserLang() ; 
			((SysObjText)this.getSysObjTextsDS().getCurrentItem()).setFrmObjLngValue(new BigDecimal(loggedUserLang));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteSysObjText(int deletedIndex)
	{
		try {
			this.getSysObjTextsDS().remove(deletedIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
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