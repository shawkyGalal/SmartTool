package com.smartValue.database.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.sideinternational.sas.BaseException;
import com.smartValue.components.model.UserResponse;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SysMsg;
import com.smartValue.database.map.msgAction.MsgAction;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SysMsgSecurityControlHandlerImpl;
import com.smartValue.database.map.services.InterfaceImplServices;
import com.smartValue.database.map.validators.SysMsgChangeValidator;
import com.smartValue.database.trigger.TriggerHandler;


public class SysMsg extends _SysMsg {
	
	public static final int CONFIRM_DELETE_ID = 416; //need to be adjusted..
	
	public static final int CONFIRM_IGNORE_CHANGES_ID = 414; //need to be adjusted..
	
	public static final int CONFIRMATION_TYPE = 1;
	public static final int INFORMATION_TYPE = 2;
	
	private String okButtonRerender;
	private String okButtonOncomplete;
	private UserResponse userResponse = new UserResponse(); 
	private PersistantObject poForAction ;
	private DataSet dsForAction ;
	private ArrayList<String> params = new ArrayList<String>();
	private MsgAction msgAction ; 

	
	public void setUserResponse(UserResponse userResponse)
	{
		this.userResponse = userResponse;
	}
	public UserResponse getUserResponse()
	{
		return userResponse;
	}
	public String getLangDirection()
	{
		return (this.getMsgLngIdValue()!= null &&  this.getMsgLngIdValue().intValue()==1)? "rtl" :"ltr";
	}
	public String getTitleCode()
	{
		return (this.getMsgLngIdValue() !=null && this.getMsgLngIdValue().intValue()==2)? "Message Code": "رسالة رقم";
	}
	public String getMsgUniqueKey()
	{
		return this.getMsgIdValue()+"-"+this.getMsgTypeValue();
	}

	private static PersistentObjectSecurityControl sch = new SysMsgSecurityControlHandlerImpl() ; 
	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		return sch ; // SecurityControlHandlerFactory.getTmdImpl();
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
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		return new SysMsgChangeValidator(pm_secUserData , pm_po  , pm_key);
	}


	public void setOkButtonRerender(String okButtonRerender) {
		this.okButtonRerender = okButtonRerender;
	}

	public String getOkButtonRerender() {
		return okButtonRerender;
	}

	public void setOkButtonOncomplete(String pm_okButtonOncomplete) {
		this.okButtonOncomplete = "";
		StringTokenizer tok = new StringTokenizer(pm_okButtonOncomplete, ",");
		while (tok.hasMoreTokens()) {
			this.okButtonOncomplete += 
				"document.getElementById('" + tok.nextToken().trim() + "').component.hide();";
		}
	}

	public String getOkButtonOncomplete() {
		return okButtonOncomplete;
	}

	
	/**
	 * save the object in the persistent layer and notify the corresponding service to recalculate all its fields 
	 */
	public void afterSave()
	{
		this.getDbService().getModuleServiceContainer().getSysMsgServices().setAllFieldsToNull();
	}

	public static final String MSG_ACTIONS_INTERFACE = "MsgActions";

	private MsgAction calcMsgAction()
	{
		MsgAction ma = null  ;  
		String implClassId = String.valueOf (this.getImplClassIdValue()) ; 
		if (implClassId != null )
		{
			try {
			InterfaceImplServices ims = new InterfaceImplServices(this.getDbService());
			SysInterfaceImplementors sii =  ims.getImplementorByCode(implClassId, MSG_ACTIONS_INTERFACE);
			ma =  (MsgAction) sii.getImplInstance();
			}
			catch (Exception e)
			{
				
			}
		}
		return ma ; 
	}
	
	public void refreshMsgAction()
	{
		this.msgAction = this.calcMsgAction() ;
	}
	
	
	public void doOKAction()
	{
		this.doAction(MsgAction.OK_ACTION_TYPE);
	}
	
	/**
	 *  
	 * Execute the doAction(Persistent , DataSet , MsgAction.CANCEL_ACTION_TYPE) method in this object specified MsgAction Interface
	 */
	public void doCancelAction()
	{
		this.doAction(MsgAction.CANCEL_ACTION_TYPE);
	}
	
	public void doAction(String actionType)
	{
		MsgAction msgActionImpl = this.getMsgAction();
		
		if (msgActionImpl!=null)
		{
			if (this.getPoForAction()!= null)
			{
				msgActionImpl.doAction(this.getPoForAction()  , actionType);
			}
			
			else if (this.getDsForAction()!= null)
			{
				msgActionImpl.doAction(this.getDsForAction()  , actionType);
			}
			
			this.setPoForAction(null);
			this.setDsForAction(null);
		}
		else
		{
			String pm_sysMsg = "No Impl Class defined/ properly defined for this System Message " + this;
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(pm_sysMsg);
		}
	}
	
	public void setPoForAction(PersistantObject poForAction) {
		this.poForAction = poForAction;
	}
	public PersistantObject getPoForAction() {
		return poForAction;
	}
	public void setDsForAction(DataSet dsForAction) {
		this.dsForAction = dsForAction;
	}
	public DataSet getDsForAction() {
		return dsForAction;
	}

	/**
	 * Replaces tokens {0} , {1} , .... with the given params in sequence
	 * @param params
	 * @return
	 */
	public String getMsgDescWithParam(ArrayList<String> params)
	{
		return SysMsg.substituteParams(this.getMsgDescValue(), params);
	}
	
	public String getMsgDescWithParam()
	{
		return getMsgDescWithParam(this.getParams());
	}
	public static String substituteParams (String msgWithParam , ArrayList<String> params)
	{
		String result = msgWithParam;
		if (result == null){return result;}
		int i = 0 ;
		for (String param : params)
		{
			if (param != null)
			{
			result = result.replace("{"+i+"}", param);
			}
			i++;
		}
		
		return result; 
	}
	public void setParams(ArrayList<String> param) {
		this.params = param;
	}
	public ArrayList<String> getParams() {
		return params;
	}
	
	public String toString()
	{
		return this.getMsgIdValue() +":" + this.getMsgDesc().getValue();
	}
	public void setMsgAction(MsgAction msgAction) {
		this.msgAction = msgAction;
	}
	public MsgAction getMsgAction() {
		return msgAction;
	}
	
	public void initialize()
	{
		this.refreshMsgAction() ;
	}

	@Override
	public void beforeAttributeChange(Attribute pm_att) throws BaseException {
	}
	@Override
	public void afterAttributeChange(Attribute pm_att) {
		String key = pm_att.getKey() ;
		SysMsg sm = (SysMsg) pm_att.getParentPersistantObject() ;
		if (key.equals(SysMsg.IMPL_CLASS_ID)) 
		{
			sm.refreshMsgAction();
		}
		if (key.equals(SysMsg.MSG_ID)) 
		{
			sm.reCalcAllUsrDefValue();
		}
	}

}