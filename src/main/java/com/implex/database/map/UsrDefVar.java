package com.implex.database.map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.ClobAttribute;
import com.implex.database.DataSet;
import com.implex.database.DbForignKeyArray;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.Query;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._UsrDefVar;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.security.UsrDefVarSecurityControlHandlerImpl;
import com.implex.database.map.services.SqlBoundVarsServices;
import com.implex.database.map.services.SysMsgServices;
import com.implex.database.map.services.TableMaintMasterServices;
import com.implex.database.map.services.UsrDefVarServices;
import com.implex.database.map.validators.UsrDefVarChangeValidator;
import com.implex.database.trigger.TriggerHandler;

public class UsrDefVar extends _UsrDefVar {
	private UsrDefVarSecurityControlHandlerImpl securityControlHandlerImpl = new UsrDefVarSecurityControlHandlerImpl();
	public ArrayList<SelectItem> allUserDefVarsExceptMe = null;
	private String tempString ;
	
	
	public AttributeChangeValidator changeValidator ; 
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		if (changeValidator == null) 
		changeValidator = new UsrDefVarChangeValidator(pm_secUserData , pm_po  , pm_key); 
		return changeValidator; 
	}
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
		 return childrenForignKeys; 
	}
	
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return securityControlHandlerImpl; 
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
	private PersistantObject persistantObject ;
	
	public void setPersistantObject(PersistantObject persistantObject) {
		this.persistantObject = persistantObject;
	}
	public PersistantObject getPersistantObject() {
		return persistantObject;
	}
	public String calculateValue(PersistantObject pm_po) {
		
		return calculateValue(pm_po ,false);
		
	}
	
	public boolean checkFormulaSyntax(){
		boolean result = false;
		try {
			ClobAttribute udvVarFiled =  (ClobAttribute) this.getUdvVarField() ;
			String exp = udvVarFiled.getClobStrValue();
			result = this.checkFormulaSyntax(exp);
			this.setValidFormulaSyntax(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
public String getUdvVarFieldAfterSubstitution(PersistantObject po , boolean asTest)
{
	String query = ((ClobAttribute)this.getUdvVarField()).getClobStrValue();
	 //1- Substitute System Defined Variables
	 
	  String attValue;
	  ArrayList<String> list = PersistantObject.getListOfVariables(query, PersistantObject.SYS_DEFINED_DELIMTER);
	
	  for (String key : list) 
	  {		
		  	Object value =  po.getAttributeValue(key.toUpperCase(), false) ;
		   if (po!=null &&  value != null)
			   {
			   		attValue = value.toString();
			   }
		   else 
		   		{
			   	attValue =  "test value";
		   		}
		   query = query.replace(PersistantObject.SYS_DEFINED_DELIMTER + key, "'"+attValue+"'");
		  }
	  
	//2- Substitute User Defined Variables
	  ArrayList<String> listOfOtherUserDefVars = PersistantObject.getListOfVariables(query, PersistantObject.USER_DEFINED_DELIMTER);
	
	  UsrDefVarServices udvs =  this.getDbService().getModuleServiceContainer().getUsrDefVarServices();
	  for (String usrDefVarUniqueName : listOfOtherUserDefVars) 
	  {
		  
		  UsrDefVar udv = udvs.getUserDefinedVariable(usrDefVarUniqueName) ;
		  
		   if (udv != null)
			   {
				   String udvValue = udv.calculateValue(po);
				   query = query.replace(PersistantObject.USER_DEFINED_DELIMTER + usrDefVarUniqueName, "'"+udvValue+"'");
			   }
	  }
	  
	//3- Substitute Logged User Properties
	  query = this.subLoggedUserProperties(query) ; 
	  
	//4- Substitute User Input For Sql Bound Variables
	  ArrayList<String> listOfSqlBoundVars = PersistantObject.getListOfVariables(query, PersistantObject.INPUT_PARAMETER_DELIMTER);
	 
	  for (String sqlBoundVarName : listOfSqlBoundVars) 
	  {
		  SqlBoundVars sbv = this.getSqlboundVarByName (sqlBoundVarName) ; 
		  if ( sbv != null)
		   {
			  Object objVal = sbv.getCurrentValue();
			  String value = sbv.getDefaultValValue(); 
			  if (objVal != null)
				 value = ( objVal instanceof Date )? Attribute.convertDateToSql((Date)objVal) :  "'"+objVal.toString()+"'";
			  query = query.replace(PersistantObject.INPUT_PARAMETER_DELIMTER + sqlBoundVarName, value);
		   }
		  
	  }
  return query ;
}
	public DataSet calcDataSetValue(PersistantObject po , boolean asTest)
	{
	
		DataSet result = null ; 
	
		String query = this.getUdvVarFieldAfterSubstitution(po, asTest) ;
		  try{
			  TableMaintMasterServices tmms = new TableMaintMasterServices(this.getDbService());
			  Class cls = null; 
			  try{
				  Query qu = new Query(query) ; 
				  String estimatedTableName = qu.getEstimatedTableName() ;
				  String estimatedTableOwner = qu.getEstimatedTableOwner() ;
				  
				  TableMaintMaster tmm =  tmms.getTableMaintMaster(new DbTable( estimatedTableOwner , estimatedTableName , this.getDbService() )) ;
				  cls = Class.forName(tmm.getMapClassNameValue()) ;  
			  }
			  catch(Exception e){}
			  
			  result =  this.getDbService().queryForDataSet(query, cls);
			  
		  }
		  catch(Exception be)
		  {
			  //SysMsg sm =  msc.getSysMsgServices().getSysMsg(msgId, msgType);
			  //ApplicationContext.getMessageCommnunicatorService().sendMessageToUser("Unable to Calculate User Defined valiable " + this.getDisplayTxt() + " For Object Instance " + po.getDisplayTxt() + "Due To :" + be.getMessage()  );
			  SysMsgServices sysmsgservices=this.getDbService().getModuleServiceContainer().getSysMsgServices();
			  SysMsg sysMsg=sysmsgservices.getMsgUnableCalculateUserDefinedVariable();
			  ArrayList<String>param=new ArrayList<String>();
			  param.add(this.getDisplayTxt() );
			  param.add(po.getDisplayTxt());
			  param.add(be.getMessage() );
			  sysMsg.setParams(param);
			  this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
		  }
		  
		  return result ; 

	}
	public String calculateValue(PersistantObject po , boolean asTest)
	 {		 
		 String result = "";
		 if (po == null && ! asTest )
		 {
			 return "Will Be calculated for Specific object";
		 }
		 
		 try{
			 DataSet ds = this.calcDataSetValue(po, asTest) ; 
			 if (ds != null)
			  { 
				 result =  ds.getPersistantObjectList().get(0).getAttribute("RESULT").getAttributeSQLValue(true);
			  }
		  	}
		  catch(Exception be)
		  {
			  //SysMsg sm =  msc.getSysMsgServices().getSysMsg(msgId, msgType);
			  //ApplicationContext.getMessageCommnunicatorService().sendMessageToUser("Unable to Calculate User Defined valiable " + this.getDisplayTxt() + " For Object Instance " + po.getDisplayTxt() + "Due To :" + be.getMessage()  );
			  SysMsgServices sysmsgservices=this.getDbService().getModuleServiceContainer().getSysMsgServices();
			  SysMsg sysMsg=sysmsgservices.getMsgUnableCalculateUserDefinedVariable();
			  ArrayList<String>param=new ArrayList<String>();
			  param.add(this.getDisplayTxt() );
			  param.add(po.getDisplayTxt());
			  param.add(be.getMessage() );
			  sysMsg.setParams(param);
			  this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			  result = "Error ! ";
		  }
		  
		  return result;
		 
	 }
	
	private SqlBoundVars getSqlboundVarByName(String name )
	{
		SqlBoundVars result = null;
		for (PersistantObject sqbv : this.getSqlBondVars())
		{
			SqlBoundVars item =(SqlBoundVars)sqbv ; 
			if (item.getBoundVarNameValue().equalsIgnoreCase(PersistantObject.INPUT_PARAMETER_DELIMTER+name) )
			{
				result = item ; 
			}
		}
		return result;
	}
	public void appendSysVarToFormula(String value){
		appendToFormula(UsrDefVar.SYS_DEFINED_DELIMTER + value);
	}
	public void appendToFormula(String value){
		ClobAttribute udvVarFiled =  (ClobAttribute) this.getUdvVarField() ; 
		String clobVal = udvVarFiled.getClobStrValue() ;
		value = clobVal == null? value : clobVal +" "+value+" ";  
		this.getUdvVarField().setValue(value);
	}
	
	boolean reEvaluateExpression = true ;
	/**
	 * save the object in the persistent layer and notify the corresponding service to recalculate all its dependant fields 
	 */
	public void save() throws Exception
	{
		reEvaluateExpression = this.getUdvVarField().isChanged();
		super.save();
		// Notify the corresponding TableMaintMaster
		this.getTableMaintMasterFromTableName().setAllFieldsToNull();
		
	}
	public TableMaintMaster getTableMaintMasterFromTableName()
	{
		String tn = this.getTableNameValue() ;
		TableMaintMasterServices tms = this.getDbService().getModuleServiceContainer().getTableMaintServices();
		//TableMaintMasterServices tms =  new TableMaintMasterServices(this.getDbService()); 
		tms.setDbService(this.getDbService());
		tms.setTableName(tn);
		return  tms.getCurrentTable() ; 
	}

	public ArrayList<SelectItem> getAllUserDefVarsExceptMe() throws Exception
	{
		if (allUserDefVarsExceptMe == null)
		{
			String equalString = this.getUniqeKey().getEqualSqlString();
			String query = "Select '"+ PersistantObject.USER_DEFINED_DELIMTER + "' ||" + UsrDefVar.UDV_VAR +" , " +UsrDefVar.UDV_VAR_DESC_ +" , " +UsrDefVar.UDV_VAR_DESC 
				+" from "+ TableMaintMaster.CDB_SCHEMA_OWNER+"."+ this.getTable().getTableName()
				+" where " + UsrDefVar.TABLE_NAME + "= '" + this.getTableNameValue() + "'"
				+" And " + UsrDefVar.TABLE_OWNER + "= '" + this.getTableOwnerValue() + "'" 
				+" And not ( " +  equalString + ")"; 
			
			allUserDefVarsExceptMe =this.getDbService().getSelectItems(query,false);
		}
		return allUserDefVarsExceptMe  ;
	}
	public void setTempString(String tempString) {
		this.tempString = tempString;
	}
	public String getTempString() {
		return tempString;
	}


	public boolean isSystemGenerated()
	{
		return this.getSystemGenerated().getBooleanValue();
	}
	
	private List<PersistantObject> sqlBondVars ; 
	
	public List<PersistantObject> getSqlBondVars()
	{
		if (reEvaluateExpression )
		{
		SqlBoundVarsServices sbvs =  this.getDbService().getModuleServiceContainer().getSqlBoundVarsServices();
		ClobAttribute udvVarFiled =  (ClobAttribute) this.getUdvVarField() ;
		sqlBondVars = sbvs.getSqlBoundVarByNames( PersistantObject.getListOfVariables(udvVarFiled.getClobStrValue(), PersistantObject.INPUT_PARAMETER_DELIMTER) );
		reEvaluateExpression = false;
		}
		return sqlBondVars ; 
		
	}
	public void setValidFormulaSyntax(boolean validFormulaSyntax) {
		this.validFormulaSyntax = validFormulaSyntax;
	}
	public boolean isValidFormulaSyntax() {
		return validFormulaSyntax;
	}
	private boolean validFormulaSyntax;
	
	public void beforeInsert()
	{
		BigDecimal nextValue = this.getTableMaintMaster().getNextSequanceNumber(UsrDefVar.UDV_CODE);
		this.setUdvCodeValue(nextValue);
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
