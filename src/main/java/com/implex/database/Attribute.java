package com.implex.database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.model.SelectItem;

import oracle.sql.CLOB;

import com.implex.components.model.tree.TreeBeanGeneric;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysFrmServices;
import com.implex.database.map.services.TableMaintMasterServices;
import com.implex.database.notifications.AttributeChangeNotifier;
import com.sideinternational.sas.event.logging.Console;

public class Attribute implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private PersistantObject parentPersistantObject;
	private Object m_OriginalValue;
	protected Object currentValue ;
	private Jsfproperties jsfProperties = null;
	private ValidationResult validationResult ;
	private boolean valueChanged ; 
	private TableMaintDetails tableMaintDetails ; 
	
	public boolean isStoredEncrypted()
	{
		boolean result = false;
		/** 
		 * postpone calcauilation
		 
		Jsfproperties jsfp = this.getJsfProperties();
		if ( jsfp!= null)
			result =  jsfp.isStoredEncrypted() ;
		*/
		return result;
		
	}

	
	public Attribute(String pm_key , Object pm_attributeValue , PersistantObject pm_parentPersistantObject )
	{
		this.m_OriginalValue = pm_attributeValue ;
		this.currentValue = pm_attributeValue;
		//this.setCurrentValue(pm_attributeValue) ; 
		this.parentPersistantObject = pm_parentPersistantObject;
		this.key = pm_key;
	}

	private void initialize()
	{
		this.refreshListOfValues() ;
		this.refreshDataType();
	}

	public boolean isRendered()
	{
		boolean result = true ; 
		
		TableMaintDetails tmd = this.getTableMaintDetail() ; 
		if (tmd!= null && tmd.getRenderBooleanImpl() != null ) 
		{
			result =  tmd.getRenderBooleanImpl().isRendered(this );
		}

		return result ;
	}
	
	public boolean isHaveSubList()
	{
		boolean result = false ; 
		
		TableMaintDetails tmd = this.getTableMaintDetail() ; 
		if (tmd!= null && tmd.getRenderBooleanImpl() != null ) 
		{
			result =  tmd.getRenderBooleanImpl().isHaveSubList(this);
		}

		return result ;
	}
	
	public boolean isSecured()
	{
		boolean result = false;
		return result;
	}

	public boolean isDisabled()
	{
		boolean result = false;
		TableMaintDetails tmd = this.getTableMaintDetail() ; 
		if (tmd!= null && tmd.getDisabledBooleanImpl() != null ) 
		{
			result =  tmd.getDisabledBooleanImpl().isDisabled(this );
		}
		return result ;
	
	}

	public static boolean isNullOrEmptyString(Object pm_obj)
	{
		return (pm_obj == null || ( pm_obj instanceof String && pm_obj.equals("")));
	}
	private Object properCastingWithBasicValidation (Object attributeValue)	
	{	
		Object castedNewValue = attributeValue;
		ValidationResult vr =  new ValidationResult();
		if (isNullOrEmptyString(attributeValue))
		{
			if (this.getTableMaintDetail() != null && ! this.getTableMaintDetail().getNullable().getBooleanValue())
			{
				vr.setInvalidMessage(this.getTableMaintDetail().getDisplayNameAutoLang() + " Is Required");
				vr.setValidResult(false);
			}
		}
		else if (attributeValue != null ) 
		{
			if ( this.isNumber() )//m_OriginalValue instanceof BigDecimal)
			{
				if (attributeValue instanceof BigDecimal)
				{
					castedNewValue = attributeValue;	
				}
				if (attributeValue instanceof String)
				{
					String strValue = (String) attributeValue ;
					if (strValue != null && strValue.equals(""))
					{
						castedNewValue =  null;
					}
					else 
					{
						try 
						{
							castedNewValue = new BigDecimal(strValue);
							this.setValidationResult(null);
						} catch (NumberFormatException  e1) 
						{
							
							vr.setInvalidMessage(this.getTableMaintDetail().getDisplayNameAutoLang() + " Should Be Number");
							vr.setValidResult(false);
							//this.setValidationResult(vr);
						}
					}
				}
			}
		}
		this.setValidationResult(vr);
		return castedNewValue; 
	}
	String dataType = null ; 
	public String getDataType()
	{
		if (dataType == null)
		{
			this.refreshDataType() ;
		}
		return dataType ; 
	}
	
	private void refreshDataType()
	{
		this.dataType = this.calsDataType() ; 
	}
	private String calsDataType()
	{
		if (dataType == null && this.getTableMaintDetail() != null)
		{
			dataType =  this.getTableMaintDetail().getDataTypeValue();
			
		}
		return dataType ;
	}
	
	
	public boolean isNumber()
	{
		return this.getTableMaintDetail() != null && this.getTableMaintDetail().isNumric() ;
	}
	
	public boolean isDate()
	{
		
		return this.getTableMaintDetail() != null && this.getTableMaintDetail().isDate() ;
		
	}
	
	private boolean setCurrentValue(Object obj)
	{
		boolean valueChanged = true ;
		ArrayList<AttChangeListner>  changeListners = this.getChangeListners() ;
		Exception e = null; 
		boolean changListnersExist =   changeListners != null && !changeListners.isEmpty() ; 
		if (changListnersExist)
		{
			for (AttChangeListner cl : changeListners)
			{
				try 
				{
					cl.beforeChange(this) ;
				} 
				catch (Exception e1) 
				{
					e= e1 ; 
					break ; 
				} 
			}
		}
		
		if (e == null )
		{
			this.currentValue = obj ;
			if (changListnersExist)
			{
				for (AttChangeListner cl : changeListners)
				{
					cl.afterChange(this) ;
				}
			}
		}
		else
		{
			valueChanged = false ;
			
			this.getMessageCommnunicatorService().sendExceptionToUser(e);
		}
		
		return valueChanged ; 
	
		
	}
	
	ArrayList<AttChangeListner>  changeListners ; 
	private ArrayList<AttChangeListner> getChangeListners() {
		if (changeListners == null)
		{
			changeListners = new ArrayList<AttChangeListner> () ; 
		}
		return changeListners ;
		
	}
	
	public MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return this.getParentPersistantObject().getModuleServiceContainer().getMessageCommnunicatorService() ; 
	}
	public void addChangeListner(AttChangeListner pm_attChangeListner)
	{
		this.getChangeListners().add(pm_attChangeListner) ;
	}

	public void removeChangeListner(AttChangeListner pm_attChangeListner)
	{
		this.getChangeListners().remove(pm_attChangeListner) ;
	}

	private long timeToSet ;
	
	public long getTimeToSet()
	{
		return timeToSet ;
	}
	private void constructDefaultValues()
	{
		//Construct default values A-K
		Object newValue = null;
		TableMaintMaster tmm = this.getParentPersistantObject().getTableMaintMaster();
		String loogedUserFilter = tmm.loggedUserFilter();
		if(loogedUserFilter != null  && !loogedUserFilter.isEmpty() && loogedUserFilter.contains(this.getKey()) )
		{
			String[] valueSplitter1= loogedUserFilter.split(this.getKey()); 
			String value = "";
			String newValueAsString = "";
			if(valueSplitter1.length>1)
			{
				value = valueSplitter1[1];
				for(int i = 0; i < value.length(); i++)
				{
					if(value.charAt(i)!='a')
					{
						newValueAsString += value.charAt(i);
					}else{
						String[] valueSplitter2 = newValueAsString.split("=");
						if(valueSplitter2[1].contains("'")) valueSplitter2[1].replace("'", " ");
						newValue = valueSplitter2[1].trim();
						break;
					}
					
				}
			}else{
				value = valueSplitter1[0];
				for(int i = 0; i < value.length(); i++)
				{
					if(value.charAt(i)!='a')
					{
						newValueAsString += value.charAt(i);
					}else{
						String[] valueSplitter2 = newValueAsString.split("=");
						if(valueSplitter2[1].contains("'")) valueSplitter2[1].replace("'", " ");
						newValue = valueSplitter2[1].trim();
						break;
					}
					
				}
			}
			
			if(this.getDataType().equals("NUMBER"))
			{
				this.currentValue = new BigDecimal( String.valueOf(newValue) );
			}else{
				this.currentValue = String.valueOf(newValue);
			}
			valueChanged = true;
		}
	}
	public void setValue(Object newValue)
	{
		{
			Date startDate = new Date();
			Object castedNewValue = properCastingWithBasicValidation(newValue);
			boolean newValueEqualsCurrent = (castedNewValue == null && this.currentValue == null) 
			   								|| 
			   								(castedNewValue != null && castedNewValue.equals(this.currentValue)) ;  
			
			if (  newValueEqualsCurrent )
			  {
			   return ; 
		    }
			ValidationResult vr = this.getValidationResult()  ;
			boolean newValueEqualsOriginal =  castedNewValue!= null && castedNewValue.equals(m_OriginalValue) 
											||
											// Also consider empty string as a null value -
											// Assuming that jsf will send empty string from jsf controls (h:inputText , ....)
											( this.getOrginalValue()==null && (newValue == null  || newValue instanceof String && newValue.equals("")) )  ;
			PersistentObjectSecurityControl sch = this.getParentPersistantObject().getSecurityController();
			if (newValueEqualsOriginal)
			{
				try {
					this.beforeChange();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setCurrentValue (this.getOrginalValue()) ;
				this.afterChange();
				this.setValueChanged(false);
				if (this.getTableMaintDetail() !=null && this.getTableMaintDetail().isListExccedsLimit() )
				{
					refreshValueDescFormTmdList();
				}
				return;
			}

			boolean valueNeed2BeChanged = this.isValuesDiffrent(castedNewValue);
			if (valueNeed2BeChanged)
			{
				if ( vr.isValidResult()) // if No basic invalidation .. check user validation 
				{
					PersistantObject parent = this.getParentPersistantObject(); 
					SecUsrDta secUserData = (parent.getDbService()!= null )? parent.getDbService().getLoggedUser() : null;
					
					AttributeChangeValidator attributeChangeValidator = (secUserData != null)? parent.getAttributeChangeValidator(secUserData, parent, key) : null ; //(this.getParentPersistantObject().getChangeValidator()!=null)? this.getParentPersistantObject().getChangeValidator().getAttributeValidator(this) : null;
					if (attributeChangeValidator != null)
						{
							this.setValidationResult(attributeChangeValidator.validate(castedNewValue) );
							if (   ! this.getValidationResult().isValidResult())
							{
								Console.log("Invalid New Value (" +castedNewValue +") for Key = " + this.getKey()+ " in Object " + this.getParentPersistantObject() , this.getClass());
								//return;
							}
						}
				}
				try {
					this.beforeChange();
				} catch (Exception e) {
					this.getMessageCommnunicatorService().sendExceptionToUser(e);
					e.printStackTrace();
					return ;
				}
				valueChanged =  this.setCurrentValue(castedNewValue) ;
				this.setValueChanged(valueChanged);
				this.afterChange();
				if (this.getTableMaintDetail() !=null && this.getTableMaintDetail().isListExccedsLimit() )
				{
					refreshValueDescFormTmdList();
				}
				//Calculate subList if have
				if(valueChanged && this.isHaveSubList())
				{
					this.calculateSubList();
				}
			}
			
			this.timeToSet = new Date().getTime() - startDate.getTime() ;
			this.title = null;
		}
		
		
	}
	
	private void beforeChange() throws Exception
	{
		 this.getParentPersistantObject().beforeAttributeChange(this) ;
	}
	
	private void afterChange()
	{

		 this.getParentPersistantObject().afterAttributeChange(this) ;
	}
	
	public boolean isChanged()
	{
		return this.valueChanged;
	}
	
	protected  boolean isValuesDiffrent( Object pm_newValue)
	{
		boolean result = false ;
		
		
		if (this.currentValue == null && pm_newValue == null ) return false;
		else if (this.currentValue == null && pm_newValue != null ) return true;
		else if (this.currentValue != null && pm_newValue == null ) return true;
		else if (this.currentValue instanceof String || this.currentValue instanceof BigDecimal)
			{
			result =  !this.currentValue.equals(pm_newValue);
			}
		else if (this.currentValue instanceof Date)
			{
				Date date1 = (Date)this.currentValue;
				result = ! date1.equals(pm_newValue);

			}
	
		else 
		{
			result   =  ( !this.currentValue.equals(pm_newValue) );		
		}
		return result ;
	}
	
	public Object getValue()
	{
		//do not apply encryption on rowid
		// if (this.key.equalsIgnoreCase(PersistantObject.OBJECT_KEY_NAME))
			return currentValue ; 
		//else
		//	return (this.isStoredEncrypted())? deCrypt(currentValue) : currentValue;
	}

	private Object deCrypt(Object obj)
	{
		if (obj==null)
		{
			return obj; 
		}
		if (obj instanceof String)
		{
		byte[] encryptedBytes = ((String) obj).getBytes();
		byte[] decryptedBytes = decrypt(encryptedBytes);
		return new String(decryptedBytes);
		}
		else if (obj instanceof BigDecimal)
		{
		byte[] encryptedBytes = ((BigDecimal) obj).toString().getBytes();
		byte[] decryptedBytes = decrypt(encryptedBytes);
		return new BigDecimal(new String(decryptedBytes));
		}
		else if (obj instanceof Date || obj instanceof Timestamp)
		{
			// Date can not be encrypted... till now...
			return obj;
		}
		else 
		{
			this.getMessageCommnunicatorService().sendMessageToUser("Sorry ...Encryption Not yet Implemented For This Type of Data");
			return obj;
		}
	}
	
	private Object enCrypt(Object obj) 
	{	if (obj==null)
		{
			return obj; 
		}
		if (obj instanceof String)
		{
		byte[] encryptedBytes = ((String) obj).getBytes();
		byte[] decryptedBytes = encrypt(encryptedBytes);
		return new String(decryptedBytes);
		}
		else if (obj instanceof BigDecimal)
		{
		byte[] encryptedBytes = ((BigDecimal) obj).toString().getBytes();
		byte[] decryptedBytes = encrypt(encryptedBytes);
		return new BigDecimal(new String(decryptedBytes));
		}
		else if (obj instanceof Date || obj instanceof Timestamp)
		{
			// Date can not be encrypted... till now...
			return obj;
		}
		else 
		{
			this.getMessageCommnunicatorService().sendMessageToUser("Sorry ...Encryption Not yet Implemented For This Type of Data");
			return obj;
		}
		
	}
	
	private byte[] encrypt(byte[] bytesToBeEncrypted)
	{
		return bytesToBeEncrypted ; //CryptographyFactory.create(CryptographyMode.DESede).encrypt(bytesToBeEncrypted);
	}
	
	private byte[] decrypt(byte[] bytesToBeDecrypted)
	{
		return bytesToBeDecrypted; //CryptographyFactory.create(CryptographyMode.DESede).decrypt(bytesToBeDecrypted);
	}
	
	public Object getOrginalValue()
	{
		return m_OriginalValue;
	}

//	public void setRequired(boolean required)
//	{
//		this.getJsfProperties().setRequired( required);
//	}

	public boolean isRequired()
	{
		boolean result = false;
		boolean requiredFromSch = (this.getJsfProperties() != null) && this.getJsfProperties().isRequired() ;
		boolean requiredTmd = this.getTableMaintDetail() != null && ! this.getTableMaintDetail().getNullable().getBooleanValue() ;
		TableMaintMaster tmm = this.getParentPersistantObject().getTableMaintMaster() ; 
//		boolean requiredDbMandatory = (tmm!= null )&& tmm.getMandatoryFields().contains(this.getKey());
		result = requiredFromSch || requiredTmd  ; 
		
		return result;
	}

	public void setJsfProperties(Jsfproperties jsfProperties)
	{
		this.jsfProperties = jsfProperties;
	}

	public Jsfproperties getJsfProperties()
	{
		PersistentObjectSecurityControl sch = this.getParentPersistantObject().getSecurityController();		 
		PersistantObject pop = this.getParentPersistantObject();
		if (sch != null  && pop !=null 	&& pop.getDbService() !=null)
		{	
			this.jsfProperties =  new Jsfproperties(sch , this );
		}
		return jsfProperties ; 
	}
		
	

	public void setParentPersistantObject(PersistantObject parentPersistantObject)
	{
		this.parentPersistantObject = parentPersistantObject;
	}

	public PersistantObject getParentPersistantObject()
	{
		return parentPersistantObject;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}
	
	public void commit()
	{
		this.m_OriginalValue = this.currentValue;
		this.setValueChanged(false);
	}

	public String getChangeDetails()
	{
		return (this.isChanged())? "Value Has Been Changed From <" + this.getOrginalValue() + "> To <" + this.getValue() +">": "";
	}

	private void setValidationResult(ValidationResult validationResult)
	{
		if (validationResult == null)
		{	this.validationResult = new ValidationResult() ;}
		else
		{	this.validationResult = validationResult; }
	}

	public ValidationResult getValidationResult()
	{
		if (validationResult == null )
		{
			String msg = null ; 
			if (this.isRequired() && isValueEmpty()  )
			{
				msg = " Implex: Value is Required for " + this.getTableMaintDetail().getDisplayNameAutoLang();
		 
			}
			validationResult = new ValidationResult(msg) ;
		}
		return validationResult;
	}

	public String getTemplateFile()
	{	
		String result = TableMaintDetails.DEFAULT_JSF_File ; 
		TableMaintDetails tmd = this.getTableMaintDetail() ;
		if ( tmd != null)
			result =  tmd.getJsfTemplateFile();
		return result;
	}
	
	public String getJsfTemplateFile()
	{	
		return "/templates/include/controls/attTemplateInput.xhtml" ; 
	}
	public TableMaintDetails getTableMaintDetail()
	{ 
		if (tableMaintDetails == null   )
		 {
			TableMaintMaster tmm = this.getParentPersistantObject().getTableMaintMaster();
			if (tmm != null)		
			tableMaintDetails = tmm.getTmdByColumnName(this.getKey()); 
		 }
		return tableMaintDetails;
	}

	public void revert()
	{
		this.currentValue = this.getOrginalValue() ; 
		this.setValueChanged(false);
		this.poToGetValueFrom = null ; 
		
	}

	public String getDisplayText()
	{
		String result = null;
		if (this.getTableMaintDetail() == null)
		{
			SysFrmServices sfs = new SysFrmServices(this.getParentPersistantObject().getDbService());
			//sfs.setDbService(this.getParentPersistantObject().getDbService());
			result = sfs.getFrmObjTxt("all.xhtml",this.getKey()) ;
		}
		else
		{
			result  = (String) this.getTableMaintDetail().getDisplayNameAutoLang().getValue();
		}
		
		return result;
	}
	
	
	public boolean isValueEmpty()
	{
		return this.getValue() == null ||  isEmptyString() ; 
	}
	
	private boolean isEmptyString()
	{
		return ( this.getValue() instanceof  String  && ((String)this.getValue()).equals(""));
	}
	
	@Override
	public String toString()
	{
		Object val = this.getValue() ; 
		return   String.valueOf((val!=null) ? val : "") ;
	}
	 public boolean getBooleanValue()
	 {
		 String value =  (getValue()!= null)? getValue().toString(): null;
		 return Attribute.getBooleanvalue(value);
	}
	 
	 public static boolean getBooleanvalue(String value)
	 {
		 
		 return value !=null && (value.equalsIgnoreCase("Y") 
				 					||value.equalsIgnoreCase("'Y'")
				 					|| value.equals("1"));

	 }
	 
	 public void setBooleanValue(boolean booleanValue)
	 {
		 
		 if (this.isNumber()) {
			 this.setValue( new BigDecimal(booleanValue? 1 : 0));			
		 }
		 else 
			 this.setValue(booleanValue? "Y" : "N");
	 }
	 
	 public TreeBeanGeneric getQueryTreeBean() throws Exception
	 {
		 TreeBeanGeneric tbg = this.getTableMaintDetail().getQueryTreeBean();
		 tbg.setAssociatedAttribute(this);
		 return tbg;
	 }
	 
	 public static String convertDateToSql(Date attributeValue)
	 {		
		 String result;
		 Date date = attributeValue;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		result = "to_Date( '"+sdf.format(date)+"', 'dd-mm-yyyy hh24:mi:ss')";
		return result;
	 }
	 
	 public String getAttributeSQLValue( boolean pm_forModifiedValues )
	 {
		 return this.getAttributeSQLValue(pm_forModifiedValues, true);
	 }
	 public String getAttributeSQLValue( boolean pm_forModifiedValues , boolean withQuot)
		{
			Object attributeValue = (pm_forModifiedValues)? this.getValue() : this.getOrginalValue();
			if (attributeValue== null) return "null";
			String result = "";
			if (attributeValue instanceof String)
			{
				result = ((withQuot) ? "'" : "") +attributeValue.toString().replace("'", "''")+ ( (withQuot) ? "'" : "" );
			}
			else if (attributeValue instanceof Timestamp ) 
			{
				
				Timestamp date = (Timestamp)attributeValue;
				result = convertDateToSql (date) ;
			}
			else if (attributeValue instanceof Date) 
			{
				
				Date date = (Date)attributeValue;
				result = convertDateToSql (date) ;
			}
			else if (attributeValue instanceof CLOB) 
			{
				String clobStr = ((ClobAttribute)this).getClobStrValue(); 
				result = ((withQuot) ? "'" : "") +clobStr.replace("'", "''")+ ( (withQuot) ? "'" : "" );
			}
			else
			{
				result = attributeValue.toString();
			}
			
			
			return result;
		}
	 
	 public String getWhereClause()
	 {
		 return this.key + " = " + this.getAttributeSQLValue(true);
	 }
	 public String getAlteredLabel(){
		 
		 SysFrmServices sfs = new SysFrmServices(this.getParentPersistantObject().getDbService());
		 //sfs.setDbService(this.getParentPersistantObject().getDbService());
		 return this.isChanged() ? "("+sfs.getFrmObjTxt("all.xhtml","lblAlter")+")" : "" ;
	 }
	 
	 public boolean equalData(Object ob)
	 {
		 boolean result = false;
		 if (ob instanceof Attribute )
		 {
			
			 Attribute att = (Attribute) ob;
			 if (this.getValue() !=null && att.getValue() != null)
			 {
				 result = this.getKey() == att.getKey();
				 result = result &&  this.getValue().equals(att.getValue());
			 }
		 }
		 return result; 
	 }

	 public boolean equalKey(Object ob)
	 {
		 boolean result = false;
		 if (ob instanceof Attribute )
		 {
			
			 Attribute att = (Attribute) ob;
			 if (this.getValue() !=null && att.getValue() != null)
			 {
				 result = this.getKey().equalsIgnoreCase(att.getKey());
				 //result = result && this.getParentPersistantObject() .equals(att.getParentPersistantObject()) ;
			 }
		 }
		 return result; 
	 }
	 @Override
	public boolean equals(Object ob)
	 {
		 return this.equalKey( ob) ; 
	 }
	 
	public void searchByMe()
	{
		try {
			this.getParentPersistantObject().getParentDataSet().setCurrrentItemByAttribute(this);
		} catch (Exception e) {
			this.getMessageCommnunicatorService().
				sendMessageToUser("can't Search by "+this.getKey()+" due to " + e.getMessage());
		}
	}
	public boolean isHasInputText()
	{
		return this.getTableMaintDetail() != null ? this.getTableMaintDetail().getHasInputtext().getBooleanValue() : false;
	}
	public String getToolTip(){
		return this.getTableMaintDetail() != null ? this.getTableMaintDetail().getTooltipDescAutoLang().getValue()+"" : "";  
	}

	public String getStyle(){
		String value = this.getTableMaintDetail() != null ? this.getTableMaintDetail().getCssStyleValue() : "";
		value = this.getValidationResult().isValidResult() == false  ? "border: 2px solid red;"+value : value ;
		return value;  
	}
	
	/**
	 * used for richFaces Calendar Object
	 */
	public Date getDateValue()
	{
		Object value = 	this.getValue();
		
		if  (value instanceof Timestamp)
		{
			return new Date(((Timestamp)value).getTime() );
		}
		if  (value instanceof java.sql.Date)
		{
			return new Date(((java.sql.Date)value).getTime() );
		}
		else 
		{
			return (Date) value ;
		}
		
	}
	
	public void setDateValue(Date date)
	{
		Object orignalVal = this.getOrginalValue();
		if  (date != null && orignalVal instanceof Date)
		{
		 	this.setValue(new Timestamp(date.getTime()));	
		}
		else
			this.setValue(date);
	}


	private void setValueChanged(boolean valueChanged) {
		this.valueChanged = valueChanged;
		this.informParentPo(valueChanged);
		
	}

	private void informParentPo(boolean isChanged)
	{
		PersistantObject parentPo = this.getParentPersistantObject() ;
		if (! parentPo.isContainsObjectKey()) {return ;}
		if (isChanged )
		{
			parentPo.addToModifiedAttList(this);
			
		}
		else 
		{
			parentPo.removeFromModifiedAttList(this);
			
		}
	}

	public boolean isValueChanged() {
		return valueChanged;
	}

	
	ArrayList<AttributeChangeNotifier> changeNotifiers ; 

	public ArrayList<AttributeChangeNotifier> getChangeNotifiers() {
		if (changeNotifiers == null)
		{
			TableMaintDetails tmd = this.getTableMaintDetail();
			changeNotifiers  =		tmd.getChangeNotifiers();	
		}
		return changeNotifiers ; 
		
	}
	

	

	public boolean isShowLabel()
	{
		return this.getTableMaintDetail() != null && this.getTableMaintDetail().getShowLabel() !=null
			&& this.getTableMaintDetail().getShowLabel().getBooleanValue();
	}

	private ArrayList<SelectItem> listOfValues ; 
	private String queryForLOV ; 
//	private boolean lovFromTmd = true ;
	public void setQueryForLOV(String queryForLOV) {
		this.queryForLOV = queryForLOV;
		this.refreshListOfValues() ;
	}

	public String getQueryForLOV() {
		return queryForLOV;
	}
		
	public void refreshListOfValues()
	{
		this.setListOfValues(this.calcListOfValues());
		
	}

	protected void setListOfValues(ArrayList<SelectItem> listOfValues) {
		this.listOfValues = listOfValues;
	}

	public ArrayList<SelectItem> getListOfValues() {
		if (listOfValues == null)
		{
			this.refreshListOfValues() ;
		}
		return listOfValues;
	}

	private ArrayList<SelectItem> calcListOfValues()
	{
		ArrayList<SelectItem> result ; 
	
		if (queryForLOV == null) 
		{
			result =  this.getTableMaintDetail().getQuerySelectItems() ;
		}
		else 
		{
			boolean nullAllowed = this.getTableMaintDetail().getNullable().getBooleanValue() ;
			result = this.getParentPersistantObject().getDbService().getSelectItems(this.getQueryForLOV(), nullAllowed);
		}
		//Set Default values
//		if(!this.getParentPersistantObject().isContainsObjectKey() && this.getValue() == null)
//		{
//			this.constructDefaultValues();
//		}
		//Calculate SubList
		if(this.isHaveSubList())
		{
			this.calculateSubList();
		}
		//End calculate SubList
		return result ;
	} 
	
	private void calculateSubList()
	{
		
		DataSet tmds = this.getParentPersistantObject().getTableMaintMaster().getTableMaintDetailss();
		TableMaintDetails currentTmd = null;
		PersistantObject poSearchResult=null;
		PersistantObject poCurrentItem=null;
		DbServices dbs = this.getParentPersistantObject().getDbService(); 
		ModuleServicesContainer msc = this.getParentPersistantObject().getDbService().getModuleServiceContainer() ; 
		TableMaintMasterServices tmmServices =  msc.getCurrentActiveModule().getTableMaintMasterServices();
		for(PersistantObject po : tmds.getPersistantObjectList())
		{
			currentTmd = (TableMaintDetails) po;
			
			String subQueryEnd = this.getTableMaintDetail().getColumnNameValue();
			
			if( currentTmd.getSelectListQueryValue() != null && currentTmd.getParentListColumnNameValue() != null && currentTmd.getParentListColumnNameValue().contains(subQueryEnd))
			{
				//TODO Please Asmaa Handle this section to be more generic
				String query = currentTmd.getSelectListQueryValue();
				String[] querySplitter = query.split("=");
				String subQuery =  querySplitter[0] + "=" + this.getValue();
				currentTmd.setSelectListQueryValue(subQuery);
				
			   poSearchResult =	tmmServices.getCurrentTable().getSearchResult().getCurrentItem();
			   if(poSearchResult != null)		
			   {
				   poSearchResult.getAttribute(currentTmd.getColumnNameValue()).refreshListOfValues();
			   }
			   
			   poCurrentItem =  tmmServices.getDataSetWithCondition() != null?tmmServices.getDataSetWithCondition().getCurrentItem() : null;
			   if(poCurrentItem != null)
			   {
				   poCurrentItem.getAttribute(currentTmd.getColumnNameValue()).refreshListOfValues();
			   }
			}
		}
	}
	
	public String getStringValue()
	{
		return this.toString() ;
	}

	
	private PersistantObject poToGetValueFrom ;
	private Query listQuery ;
	private TableMaintMaster searchTableMaintMaster   ;

	public Query getListQuery() {
		if (listQuery == null)
		{
			String q = this.getTableMaintDetail()!= null && this.getQueryForLOV() == null ?  this.getTableMaintDetail().getSelectListQueryValue() : this.getQueryForLOV();   
			listQuery = new Query(q) ;
		}
		return listQuery;
	}

	private String getPoKeyToGetValueFrom()
	{
		String key = this.getListQuery().getColumns()[0].trim() ;  
		int lastDotIndex = key.lastIndexOf(".") ;
		key = key.substring(lastDotIndex + 1) ; 
		return key ;
	}

	public PersistantObject getPoToGetValueFrom()
	{
		return poToGetValueFrom ;
	}

	private void setValueFromPo()
	{
		String  poKeyToGetValueFrom = getPoKeyToGetValueFrom() ; 
		if (poToGetValueFrom!= null && poKeyToGetValueFrom != null)
		{
			Object newValue = poToGetValueFrom.getAttributeValue(poKeyToGetValueFrom) ; 
			this.setValue(newValue);			
		}
		DbServices dbs = this.getParentPersistantObject().getDbService();
		ModuleServicesContainer msc = this.getParentPersistantObject().getDbService().getModuleServiceContainer() ;
		msc.setSearchFormAtt(null);
	}
	
	public void setPoToGetValueFrom(PersistantObject poToGetValueFrom) {
		this.poToGetValueFrom = poToGetValueFrom;
		this.setValueFromPo() ; 
	}

	 public TableMaintMaster getSearchTableMaintMaster() 
	 {
		 if (searchTableMaintMaster == null)
		 {
			 try{
			 String searchTableName = this.getListQuery().getEstimatedTableName() ;
			 
			 if (searchTableName != null )
			 {
				 DbServices dbs = this.getParentPersistantObject().getDbService() ; 
				 DbTable dbTable = new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER  , searchTableName , dbs) ;
				 ModuleServicesContainer msc = this.getParentPersistantObject().getDbService().getModuleServiceContainer() ;
				 searchTableMaintMaster = msc.getTableMaintServices().getTableMaintMaster(dbTable ) ;
			 }
			 }
			 catch (Exception e)
			 {
				 
			 }
			 
		 }
	 return searchTableMaintMaster;
	}
	 
	 public boolean isCLOB()
	 { 
		 boolean result = this instanceof ClobAttribute ; 
		 return result ; 
	 }

	 private String valueDescFormTmdList ;
	 private String calcValueDescFormTmdList()
	 {
		 String result = null ; 
			 {
				 Query query = this.getListQuery();
				 String idColumnName ;
				 String[] columnNames = query.getColumns() ;  
				 if ( columnNames != null && this.getValue() != null) 
				 {
					idColumnName = columnNames[0] ;
					String cond = this.getValue() != null ? Query.WHERE + " " + idColumnName + " = '" + this.getValue() + "'" : "";
					String valueFromListQuery =  "Select * From ( " + query.getQueryStr()  + " ) " +    cond ;
					DataSet ds = this.getParentPersistantObject().getDbService().queryForDataSet(valueFromListQuery, null);
		 
					if (ds != null && ! ds.getPersistantObjectList().isEmpty())
					{
						DbServices dbs = this.getParentPersistantObject().getDbService() ;
					 ModuleServicesContainer msc = dbs.getModuleServiceContainer() ;
					 int userlang = msc.getUserSession().getUserLang() ;
					 result = String.valueOf( ds.getPersistantObjectList().get(0).getAttributeValue(columnNames[userlang]) ) ;
					}
				 }
			 }
		 return result ;  
	 }

	public String getValueDescFormTmdList() 
	{
		if (valueDescFormTmdList == null)
		{
			refreshValueDescFormTmdList(); 
		}
		return valueDescFormTmdList;
	}
	public void refreshValueDescFormTmdList()
	{
		valueDescFormTmdList = calcValueDescFormTmdList() ; 
	}
	public boolean isBlob()
	{
		boolean result = this instanceof BlobAttribute ; 
		 return result ;   
	}
	public boolean isLob()
	{
		return this.isCLOB() || isBlob(); 
	}
	private String attributeId;
	public String getAttributeId()
	{
		// used because it may exist two tables have same column names
		if(attributeId == null)
			attributeId = this.getKey()+this.getParentPersistantObject().hashCode();
		return attributeId;
	}
	
	public void applyText()
	{
		this.setValue(text);
	}
	
	private String text = null;
	public void setText(String text) {
		this.text = text;
	}


	public String getText() {
		return text;
	}

	private String title = null;

	 public String getTitle()
	 {
		 if(title == null)
		 {
			 this.calcTitle();
		 }
		 return title;
	 }

	private void calcTitle()
	{
		 String value = this.isCLOB() ? ((ClobAttribute)this).getClobStrValue() : (String)this.getValue();
		 title = (value != null && value.length() > 8) ? value.substring(0,8)+"..." : value;
	}
	
	private boolean isLongerUsed = true;
	public void setLongerUsed(boolean isLongerUsed) {
		this.isLongerUsed = isLongerUsed;
	}


	public boolean isLongerUsed() {
		return isLongerUsed;
	}
	
	public void clear()
	{
		 this.key = null;
		 this.parentPersistantObject = null;
		 this.m_OriginalValue = null;
		 this.currentValue = null;
		 this.jsfProperties = null;
		 this.validationResult  = null;
		 this.tableMaintDetails = null; 
		 changeListners = null;
		 poToGetValueFrom = null;
		 listQuery = null;
		 searchTableMaintMaster = null;
		 valueDescFormTmdList = null;
		 attributeId = null;
		 text = null;
		 title = null;
	}
public String getInvalidMessage()
{
	String result = null;; 
	ValidationResult vr = this.getValidationResult();
	if (vr != null)
		result = this.getValidationResult().getInvalidMessage();
	return result ;  
}
}