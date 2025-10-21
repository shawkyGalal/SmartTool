package com.smartValue.httpClient.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _HttpRequest extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "HTTP_REQUEST"; 
 public static final String DB_TABLE_OWNER = "APIGEE"; 

	public _HttpRequest(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String CATEGORY ="CATEGORY" ;

	public void setCategoryValue(java.lang.String   pm_category){
		this.getAttribute(CATEGORY ).setValue( pm_category );
	}
 
	public java.lang.String getCategoryValue(){
		return (java.lang.String) this.getAttribute ( CATEGORY).getValue()  ;
	}
 
	public Attribute getCategory(){
		return this.getAttribute ( CATEGORY)  ;
	}

	public static final String METHOD ="METHOD" ;

	public void setMethodValue(java.lang.String   pm_method){
		this.getAttribute(METHOD ).setValue( pm_method );
	}
 
	public java.lang.String getMethodValue(){
		return (java.lang.String) this.getAttribute ( METHOD).getValue()  ;
	}
 
	public Attribute getMethod(){
		return this.getAttribute ( METHOD)  ;
	}

	public static final String URL ="URL" ;

	public void setUrlValue(java.lang.String   pm_url){
		this.getAttribute(URL ).setValue( pm_url );
	}
 
	public java.lang.String getUrlValue(){
		return (java.lang.String) this.getAttribute ( URL).getValue()  ;
	}
 
	public Attribute getUrl(){
		return this.getAttribute ( URL)  ;
	}

	public static final String BODY_TYPE ="BODY_TYPE" ;

	public void setBodyTypeValue(java.lang.String   pm_bodyType){
		this.getAttribute(BODY_TYPE ).setValue( pm_bodyType );
	}
 
	public java.lang.String getBodyTypeValue(){
		return (java.lang.String) this.getAttribute ( BODY_TYPE).getValue()  ;
	}
 
	public Attribute getBodyType(){
		return this.getAttribute ( BODY_TYPE)  ;
	}

	public static final String PRE_REQUEST_SCRIPT ="PRE_REQUEST_SCRIPT" ;

	public void setPreRequestScriptValue(java.lang.String   pm_preRequestScript){
		this.getAttribute(PRE_REQUEST_SCRIPT ).setValue( pm_preRequestScript );
	}
 
	public java.lang.String getPreRequestScriptValue(){
		return (java.lang.String) this.getAttribute ( PRE_REQUEST_SCRIPT).getValue()  ;
	}
 
	public Attribute getPreRequestScript(){
		return this.getAttribute ( PRE_REQUEST_SCRIPT)  ;
	}

	public static final String CONTENT_TYPE ="CONTENT_TYPE" ;

	public void setContentTypeValue(java.lang.String   pm_contentType){
		this.getAttribute(CONTENT_TYPE ).setValue( pm_contentType );
	}
 
	public java.lang.String getContentTypeValue(){
		return (java.lang.String) this.getAttribute ( CONTENT_TYPE).getValue()  ;
	}
 
	public Attribute getContentType(){
		return this.getAttribute ( CONTENT_TYPE)  ;
	}

	public static final String BODY ="BODY" ;

	public void setBodyValue(java.lang.String   pm_body){
		this.getAttribute(BODY ).setValue( pm_body );
	}
 
	public java.lang.String getBodyValue(){
		return (java.lang.String) this.getAttribute ( BODY).getValue()  ;
	}
 
	public Attribute getBody(){
		return this.getAttribute ( BODY)  ;
	}

	public static final String REQUEST_ID ="REQUEST_ID" ;

	public void setRequestIdValue(java.math.BigDecimal   pm_requestId){
		this.getAttribute(REQUEST_ID ).setValue( pm_requestId );
	}
 
	public java.math.BigDecimal getRequestIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( REQUEST_ID).getValue()  ;
	}
 
	public Attribute getRequestId(){
		return this.getAttribute ( REQUEST_ID)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	public DataSet getDatasetHttpRequestHeaderFk1()
	{
		 return this.geChildDsByForignKeyName("HTTP_REQUEST_HEADER_FK1");
	}
	// *******End of Child DataSets getter methods******** 
}