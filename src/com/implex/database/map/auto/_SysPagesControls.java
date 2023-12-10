package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;


public abstract class _SysPagesControls extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	public _SysPagesControls(){
	}

	 public DbTable getTable() 

	 { 

			return new DbTable("ICDB" , "SYS_PAGES_CONTROLS" , this.getDbService()); 

	 } 

	public void setIdValue(java.math.BigDecimal   pm_id){
		this.getAttribute("ID" ).setValue( pm_id );
	}
 
	public java.math.BigDecimal getIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "ID").getValue()  ;
	}
 
	public Attribute getId(){
		return this.getAttribute ( "ID")  ;
	}

	public void setPgNameValue(java.lang.String   pm_pgName){
		this.getAttribute("PG_NAME" ).setValue( pm_pgName );
	}
 
	public java.lang.String getPgNameValue(){
		return (java.lang.String) this.getAttribute ( "PG_NAME").getValue()  ;
	}
 
	public Attribute getPgName(){
		return this.getAttribute ( "PG_NAME")  ;
	}

	public void setObjTableValue(java.lang.String   pm_objTable){
		this.getAttribute("OBJ_TABLE" ).setValue( pm_objTable );
	}
 
	public java.lang.String getObjTableValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_TABLE").getValue()  ;
	}
 
	public Attribute getObjTable(){
		return this.getAttribute ( "OBJ_TABLE")  ;
	}

	public void setObjNameValue(java.lang.String   pm_objName){
		this.getAttribute("OBJ_NAME" ).setValue( pm_objName );
	}
 
	public java.lang.String getObjNameValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_NAME").getValue()  ;
	}
 
	public Attribute getObjName(){
		return this.getAttribute ( "OBJ_NAME")  ;
	}

	public void setObjFldValue(java.lang.String   pm_objFld){
		this.getAttribute("OBJ_FLD" ).setValue( pm_objFld );
	}
 
	public java.lang.String getObjFldValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_FLD").getValue()  ;
	}
 
	public Attribute getObjFld(){
		return this.getAttribute ( "OBJ_FLD")  ;
	}

	public void setObjTypeValue(java.lang.String   pm_objType){
		this.getAttribute("OBJ_TYPE" ).setValue( pm_objType );
	}
 
	public java.lang.String getObjTypeValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_TYPE").getValue()  ;
	}
 
	public Attribute getObjType(){
		return this.getAttribute ( "OBJ_TYPE")  ;
	}

	public void setObjDisplaytableValue(java.lang.String   pm_objDisplaytable){
		this.getAttribute("OBJ_DISPLAYTABLE" ).setValue( pm_objDisplaytable );
	}
 
	public java.lang.String getObjDisplaytableValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_DISPLAYTABLE").getValue()  ;
	}
 
	public Attribute getObjDisplaytable(){
		return this.getAttribute ( "OBJ_DISPLAYTABLE")  ;
	}

	public void setObjDisplaytextValue(java.lang.String   pm_objDisplaytext){
		this.getAttribute("OBJ_DISPLAYTEXT" ).setValue( pm_objDisplaytext );
	}
 
	public java.lang.String getObjDisplaytextValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_DISPLAYTEXT").getValue()  ;
	}
 
	public Attribute getObjDisplaytext(){
		return this.getAttribute ( "OBJ_DISPLAYTEXT")  ;
	}

	public void setObjDisplayvalueValue(java.lang.String   pm_objDisplayvalue){
		this.getAttribute("OBJ_DISPLAYVALUE" ).setValue( pm_objDisplayvalue );
	}
 
	public java.lang.String getObjDisplayvalueValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_DISPLAYVALUE").getValue()  ;
	}
 
	public Attribute getObjDisplayvalue(){
		return this.getAttribute ( "OBJ_DISPLAYVALUE")  ;
	}

	public void setObjWidthValue(java.math.BigDecimal   pm_objWidth){
		this.getAttribute("OBJ_WIDTH" ).setValue( pm_objWidth );
	}
 
	public java.math.BigDecimal getObjWidthValue(){
		return (java.math.BigDecimal) this.getAttribute ( "OBJ_WIDTH").getValue()  ;
	}
 
	public Attribute getObjWidth(){
		return this.getAttribute ( "OBJ_WIDTH")  ;
	}

	public void setObjHeightValue(java.math.BigDecimal   pm_objHeight){
		this.getAttribute("OBJ_HEIGHT" ).setValue( pm_objHeight );
	}
 
	public java.math.BigDecimal getObjHeightValue(){
		return (java.math.BigDecimal) this.getAttribute ( "OBJ_HEIGHT").getValue()  ;
	}
 
	public Attribute getObjHeight(){
		return this.getAttribute ( "OBJ_HEIGHT")  ;
	}

	public void setObjIndexValue(java.math.BigDecimal   pm_objIndex){
		this.getAttribute("OBJ_INDEX" ).setValue( pm_objIndex );
	}
 
	public java.math.BigDecimal getObjIndexValue(){
		return (java.math.BigDecimal) this.getAttribute ( "OBJ_INDEX").getValue()  ;
	}
 
	public Attribute getObjIndex(){
		return this.getAttribute ( "OBJ_INDEX")  ;
	}

	public void setObjTextValue(java.lang.String   pm_objText){
		this.getAttribute("OBJ_TEXT" ).setValue( pm_objText );
	}
 
	public java.lang.String getObjTextValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_TEXT").getValue()  ;
	}
 
	public Attribute getObjText(){
		return this.getAttribute ( "OBJ_TEXT")  ;
	}

	public void setObjOrderValue(java.lang.String   pm_objOrder){
		this.getAttribute("OBJ_ORDER" ).setValue( pm_objOrder );
	}
 
	public java.lang.String getObjOrderValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_ORDER").getValue()  ;
	}
 
	public Attribute getObjOrder(){
		return this.getAttribute ( "OBJ_ORDER")  ;
	}

	public void setObjPasswordValue(java.lang.String   pm_objPassword){
		this.getAttribute("OBJ_PASSWORD" ).setValue( pm_objPassword );
	}
 
	public java.lang.String getObjPasswordValue(){
		return (java.lang.String) this.getAttribute ( "OBJ_PASSWORD").getValue()  ;
	}
 
	public Attribute getObjPassword(){
		return this.getAttribute ( "OBJ_PASSWORD")  ;
	}

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