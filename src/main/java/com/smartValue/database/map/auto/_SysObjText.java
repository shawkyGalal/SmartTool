package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysObjText extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_OBJ_TEXT"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysObjText(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String FRM_NAME ="FRM_NAME" ;

	public void setFrmNameValue(java.lang.String   pm_frmName){
		this.getAttribute("FRM_NAME" ).setValue( pm_frmName );
	}
 
	public java.lang.String getFrmNameValue(){
		return (java.lang.String) this.getAttribute ( "FRM_NAME").getValue()  ;
	}
 
	public Attribute getFrmName(){
		return this.getAttribute ( "FRM_NAME")  ;
	}

	public static final String FRM_OBJ_NAME ="FRM_OBJ_NAME" ;

	public void setFrmObjNameValue(java.lang.String   pm_frmObjName){
		this.getAttribute("FRM_OBJ_NAME" ).setValue( pm_frmObjName );
	}
 
	public java.lang.String getFrmObjNameValue(){
		return (java.lang.String) this.getAttribute ( "FRM_OBJ_NAME").getValue()  ;
	}
 
	public Attribute getFrmObjName(){
		return this.getAttribute ( "FRM_OBJ_NAME")  ;
	}

	public static final String FRM_OBJ_LNG ="FRM_OBJ_LNG" ;

	public void setFrmObjLngValue(java.math.BigDecimal   pm_frmObjLng){
		this.getAttribute("FRM_OBJ_LNG" ).setValue( pm_frmObjLng );
	}
 
	public java.math.BigDecimal getFrmObjLngValue(){
		return (java.math.BigDecimal) this.getAttribute ( "FRM_OBJ_LNG").getValue()  ;
	}
 
	public Attribute getFrmObjLng(){
		return this.getAttribute ( "FRM_OBJ_LNG")  ;
	}

	public static final String FRM_OBJ_TXT ="FRM_OBJ_TXT" ;

	public void setFrmObjTxtValue(java.lang.String   pm_frmObjTxt){
		this.getAttribute("FRM_OBJ_TXT" ).setValue( pm_frmObjTxt );
	}
 
	public java.lang.String getFrmObjTxtValue(){
		return (java.lang.String) this.getAttribute ( "FRM_OBJ_TXT").getValue()  ;
	}
 
	public Attribute getFrmObjTxt(){
		return this.getAttribute ( "FRM_OBJ_TXT")  ;
	}

	public static final String FRM_OBJ_FONT ="FRM_OBJ_FONT" ;

	public void setFrmObjFontValue(java.lang.String   pm_frmObjFont){
		this.getAttribute("FRM_OBJ_FONT" ).setValue( pm_frmObjFont );
	}
 
	public java.lang.String getFrmObjFontValue(){
		return (java.lang.String) this.getAttribute ( "FRM_OBJ_FONT").getValue()  ;
	}
 
	public Attribute getFrmObjFont(){
		return this.getAttribute ( "FRM_OBJ_FONT")  ;
	}

	public static final String FRM_OBJ_FONT_SIZE ="FRM_OBJ_FONT_SIZE" ;

	public void setFrmObjFontSizeValue(java.math.BigDecimal   pm_frmObjFontSize){
		this.getAttribute("FRM_OBJ_FONT_SIZE" ).setValue( pm_frmObjFontSize );
	}
 
	public java.math.BigDecimal getFrmObjFontSizeValue(){
		return (java.math.BigDecimal) this.getAttribute ( "FRM_OBJ_FONT_SIZE").getValue()  ;
	}
 
	public Attribute getFrmObjFontSize(){
		return this.getAttribute ( "FRM_OBJ_FONT_SIZE")  ;
	}

	public static final String FRM_OBJ_FONT_BOLD ="FRM_OBJ_FONT_BOLD" ;

	public void setFrmObjFontBoldValue(java.math.BigDecimal   pm_frmObjFontBold){
		this.getAttribute("FRM_OBJ_FONT_BOLD" ).setValue( pm_frmObjFontBold );
	}
 
	public java.math.BigDecimal getFrmObjFontBoldValue(){
		return (java.math.BigDecimal) this.getAttribute ( "FRM_OBJ_FONT_BOLD").getValue()  ;
	}
 
	public Attribute getFrmObjFontBold(){
		return this.getAttribute ( "FRM_OBJ_FONT_BOLD")  ;
	}

	public static final String FRM_OBJ_FONT_COLOR ="FRM_OBJ_FONT_COLOR" ;

	public void setFrmObjFontColorValue(java.lang.String   pm_frmObjFontColor){
		this.getAttribute("FRM_OBJ_FONT_COLOR" ).setValue( pm_frmObjFontColor );
	}
 
	public java.lang.String getFrmObjFontColorValue(){
		return (java.lang.String) this.getAttribute ( "FRM_OBJ_FONT_COLOR").getValue()  ;
	}
 
	public Attribute getFrmObjFontColor(){
		return this.getAttribute ( "FRM_OBJ_FONT_COLOR")  ;
	}

	public static final String FRM_OBJ_FONT_BCOLOR ="FRM_OBJ_FONT_BCOLOR" ;

	public void setFrmObjFontBcolorValue(java.lang.String   pm_frmObjFontBcolor){
		this.getAttribute("FRM_OBJ_FONT_BCOLOR" ).setValue( pm_frmObjFontBcolor );
	}
 
	public java.lang.String getFrmObjFontBcolorValue(){
		return (java.lang.String) this.getAttribute ( "FRM_OBJ_FONT_BCOLOR").getValue()  ;
	}
 
	public Attribute getFrmObjFontBcolor(){
		return this.getAttribute ( "FRM_OBJ_FONT_BCOLOR")  ;
	}

	public static final String FRM_STYLE ="FRM_STYLE" ;

	public void setFrmStyleValue(java.lang.String   pm_frmStyle){
		this.getAttribute("FRM_STYLE" ).setValue( pm_frmStyle );
	}
 
	public java.lang.String getFrmStyleValue(){
		return (java.lang.String) this.getAttribute ( "FRM_STYLE").getValue()  ;
	}
 
	public Attribute getFrmStyle(){
		return this.getAttribute ( "FRM_STYLE")  ;
	}

	public static final String FRM_COMMON_OBJ ="FRM_COMMON_OBJ" ;

	public void setFrmCommonObjValue(java.lang.String   pm_frmCommonObj){
		this.getAttribute("FRM_COMMON_OBJ" ).setValue( pm_frmCommonObj );
	}
 
	public java.lang.String getFrmCommonObjValue(){
		return (java.lang.String) this.getAttribute ( "FRM_COMMON_OBJ").getValue()  ;
	}
 
	public Attribute getFrmCommonObj(){
		return this.getAttribute ( "FRM_COMMON_OBJ")  ;
	}

	public static final String SECURITY_QUERY ="SECURITY_QUERY" ;

	public void setSecurityQueryValue(java.lang.String   pm_securityQuery){
		this.getAttribute("SECURITY_QUERY" ).setValue( pm_securityQuery );
	}
 
	public java.lang.String getSecurityQueryValue(){
		return (java.lang.String) this.getAttribute ( "SECURITY_QUERY").getValue()  ;
	}
 
	public Attribute getSecurityQuery(){
		return this.getAttribute ( "SECURITY_QUERY")  ;
	}

	public static final String DEFAULT_ACCESS_TYPE ="DEFAULT_ACCESS_TYPE" ;

	public void setDefaultAccessTypeValue(java.lang.String   pm_defaultAccessType){
		this.getAttribute("DEFAULT_ACCESS_TYPE" ).setValue( pm_defaultAccessType );
	}
 
	public java.lang.String getDefaultAccessTypeValue(){
		return (java.lang.String) this.getAttribute ( "DEFAULT_ACCESS_TYPE").getValue()  ;
	}
 
	public Attribute getDefaultAccessType(){
		return this.getAttribute ( "DEFAULT_ACCESS_TYPE")  ;
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

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute("PREPARED_BY" ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( "PREPARED_BY").getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( "PREPARED_BY")  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute("PREPARED_DT" ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "PREPARED_DT").getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( "PREPARED_DT")  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute("MODIFIED_BY" ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( "MODIFIED_BY").getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( "MODIFIED_BY")  ;
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

	public static final String RENDERED ="RENDERED" ;

	public void setRenderedValue(java.lang.String   pm_rendered){
		this.getAttribute("RENDERED" ).setValue( pm_rendered );
	}
 
	public java.lang.String getRenderedValue(){
		return (java.lang.String) this.getAttribute ( "RENDERED").getValue()  ;
	}
 
	public Attribute getRendered(){
		return this.getAttribute ( "RENDERED")  ;
	}

	public static final String DISABLED ="DISABLED" ;

	public void setDisabledValue(java.lang.String   pm_disabled){
		this.getAttribute("DISABLED" ).setValue( pm_disabled );
	}
 
	public java.lang.String getDisabledValue(){
		return (java.lang.String) this.getAttribute ( "DISABLED").getValue()  ;
	}
 
	public Attribute getDisabled(){
		return this.getAttribute ( "DISABLED")  ;
	}

	public static final String RENDER_PROTECTION_TYPE ="RENDER_PROTECTION_TYPE" ;

	public void setRenderProtectionTypeValue(java.lang.String   pm_renderProtectionType){
		this.getAttribute("RENDER_PROTECTION_TYPE" ).setValue( pm_renderProtectionType );
	}
 
	public java.lang.String getRenderProtectionTypeValue(){
		return (java.lang.String) this.getAttribute ( "RENDER_PROTECTION_TYPE").getValue()  ;
	}
 
	public Attribute getRenderProtectionType(){
		return this.getAttribute ( "RENDER_PROTECTION_TYPE")  ;
	}

	public static final String DISABLE_PROTECTION_TYPE ="DISABLE_PROTECTION_TYPE" ;

	public void setDisableProtectionTypeValue(java.lang.String   pm_disableProtectionType){
		this.getAttribute("DISABLE_PROTECTION_TYPE" ).setValue( pm_disableProtectionType );
	}
 
	public java.lang.String getDisableProtectionTypeValue(){
		return (java.lang.String) this.getAttribute ( "DISABLE_PROTECTION_TYPE").getValue()  ;
	}
 
	public Attribute getDisableProtectionType(){
		return this.getAttribute ( "DISABLE_PROTECTION_TYPE")  ;
	}

	public static final String USR_DEF_VAR_ID_FOR_RENDER ="USR_DEF_VAR_ID_FOR_RENDER" ;

	public void setUsrDefVarIdForRenderValue(java.lang.String   pm_usrDefVarIdForRender){
		this.getAttribute("USR_DEF_VAR_ID_FOR_RENDER" ).setValue( pm_usrDefVarIdForRender );
	}
 
	public java.lang.String getUsrDefVarIdForRenderValue(){
		return (java.lang.String) this.getAttribute ( "USR_DEF_VAR_ID_FOR_RENDER").getValue()  ;
	}
 
	public Attribute getUsrDefVarIdForRender(){
		return this.getAttribute ( "USR_DEF_VAR_ID_FOR_RENDER")  ;
	}

	public static final String USR_DEF_VAR_ID_FOR_DISABLED ="USR_DEF_VAR_ID_FOR_DISABLED" ;

	public void setUsrDefVarIdForDisabledValue(java.lang.String   pm_usrDefVarIdForDisabled){
		this.getAttribute("USR_DEF_VAR_ID_FOR_DISABLED" ).setValue( pm_usrDefVarIdForDisabled );
	}
 
	public java.lang.String getUsrDefVarIdForDisabledValue(){
		return (java.lang.String) this.getAttribute ( "USR_DEF_VAR_ID_FOR_DISABLED").getValue()  ;
	}
 
	public Attribute getUsrDefVarIdForDisabled(){
		return this.getAttribute ( "USR_DEF_VAR_ID_FOR_DISABLED")  ;
	}
}