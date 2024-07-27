package com.smartValue.database.map.auto;

 
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.validators.SysParamsChangeValidator;

public abstract class _SysParams extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_PARAMS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysParams(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public AttributeChangeValidator changeValidator ; 
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		if (changeValidator == null) 
		changeValidator = new SysParamsChangeValidator(pm_secUserData , pm_po  , pm_key); 
		return changeValidator; 
	}

	public static final String SYS_CODE ="SYS_CODE" ;

	public void setSysCodeValue(java.lang.String   pm_sysCode){
		this.getAttribute("SYS_CODE" ).setValue( pm_sysCode );
	}
 
	public java.lang.String getSysCodeValue(){
		return (java.lang.String) this.getAttribute ( "SYS_CODE").getValue()  ;
	}
 
	public Attribute getSysCode(){
		return this.getAttribute ( "SYS_CODE")  ;
	}

	public static final String MOD_CODE ="MOD_CODE" ;

	public void setModCodeValue(java.lang.String   pm_modCode){
		this.getAttribute("MOD_CODE" ).setValue( pm_modCode );
	}
 
	public java.lang.String getModCodeValue(){
		return (java.lang.String) this.getAttribute ( "MOD_CODE").getValue()  ;
	}
 
	public Attribute getModCode(){
		return this.getAttribute ( "MOD_CODE")  ;
	}

	public static final String USR_ID ="USR_ID" ;

	public void setUsrIdValue(java.lang.String   pm_usrId){
		this.getAttribute("USR_ID" ).setValue( pm_usrId );
	}
 
	public java.lang.String getUsrIdValue(){
		return (java.lang.String) this.getAttribute ( "USR_ID").getValue()  ;
	}
 
	public Attribute getUsrId(){
		return this.getAttribute ( "USR_ID")  ;
	}

	public static final String ID ="ID" ;

	public void setIdValue(java.math.BigDecimal   pm_id){
		this.getAttribute("ID" ).setValue( pm_id );
	}
 
	public java.math.BigDecimal getIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "ID").getValue()  ;
	}
 
	public Attribute getId(){
		return this.getAttribute ( "ID")  ;
	}

	public static final String TYP ="TYP" ;

	public void setTypValue(java.lang.String   pm_typ){
		this.getAttribute("TYP" ).setValue( pm_typ );
	}
 
	public java.lang.String getTypValue(){
		return (java.lang.String) this.getAttribute ( "TYP").getValue()  ;
	}
 
	public Attribute getTyp(){
		return this.getAttribute ( "TYP")  ;
	}

	public static final String SECT ="SECT" ;

	public void setSectValue(java.lang.String   pm_sect){
		this.getAttribute("SECT" ).setValue( pm_sect );
	}
 
	public java.lang.String getSectValue(){
		return (java.lang.String) this.getAttribute ( "SECT").getValue()  ;
	}
 
	public Attribute getSect(){
		return this.getAttribute ( "SECT")  ;
	}

	public static final String E_NAME ="E_NAME" ;

	public void setENameValue(java.lang.String   pm_eName){
		this.getAttribute("E_NAME" ).setValue( pm_eName );
	}
 
	public java.lang.String getENameValue(){
		return (java.lang.String) this.getAttribute ( "E_NAME").getValue()  ;
	}
 
	public Attribute getEName(){
		return this.getAttribute ( "E_NAME")  ;
	}

	public static final String A_NAME ="A_NAME" ;

	public void setANameValue(java.lang.String   pm_aName){
		this.getAttribute("A_NAME" ).setValue( pm_aName );
	}
 
	public java.lang.String getANameValue(){
		return (java.lang.String) this.getAttribute ( "A_NAME").getValue()  ;
	}
 
	public Attribute getAName(){
		return this.getAttribute ( "A_NAME")  ;
	}

	public static final String VAL ="VAL" ;

	public void setValValue(java.lang.String   pm_val){
		this.getAttribute("VAL" ).setValue( pm_val );
	}
 
	public java.lang.String getValValue(){
		return (java.lang.String) this.getAttribute ( "VAL").getValue()  ;
	}
 
	public Attribute getVal(){
		return this.getAttribute ( "VAL")  ;
	}

	public static final String E_DSC ="E_DSC" ;

	public void setEDscValue(java.lang.String   pm_eDsc){
		this.getAttribute("E_DSC" ).setValue( pm_eDsc );
	}
 
	public java.lang.String getEDscValue(){
		return (java.lang.String) this.getAttribute ( "E_DSC").getValue()  ;
	}
 
	public Attribute getEDsc(){
		return this.getAttribute ( "E_DSC")  ;
	}

	public static final String A_DSC ="A_DSC" ;

	public void setADscValue(java.lang.String   pm_aDsc){
		this.getAttribute("A_DSC" ).setValue( pm_aDsc );
	}
 
	public java.lang.String getADscValue(){
		return (java.lang.String) this.getAttribute ( "A_DSC").getValue()  ;
	}
 
	public Attribute getADsc(){
		return this.getAttribute ( "A_DSC")  ;
	}

	public static final String MODIFY_SEQ ="MODIFY_SEQ" ;

	public void setModifySeqValue(java.math.BigDecimal   pm_modifySeq){
		this.getAttribute("MODIFY_SEQ" ).setValue( pm_modifySeq );
	}
 
	public java.math.BigDecimal getModifySeqValue(){
		return (java.math.BigDecimal) this.getAttribute ( "MODIFY_SEQ").getValue()  ;
	}
 
	public Attribute getModifySeq(){
		return this.getAttribute ( "MODIFY_SEQ")  ;
	}

	public static final String UPDATE_STATUS ="UPDATE_STATUS" ;

	public void setUpdateStatusValue(java.math.BigDecimal   pm_updateStatus){
		this.getAttribute("UPDATE_STATUS" ).setValue( pm_updateStatus );
	}
 
	public java.math.BigDecimal getUpdateStatusValue(){
		return (java.math.BigDecimal) this.getAttribute ( "UPDATE_STATUS").getValue()  ;
	}
 
	public Attribute getUpdateStatus(){
		return this.getAttribute ( "UPDATE_STATUS")  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute("MODIFIED_DT" ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "MODIFIED_DT").getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( "MODIFIED_DT")  ;
	}
}