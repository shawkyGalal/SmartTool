package com.masterWorks.httpClient.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _HttpRequestHeader extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "HTTP_REQUEST_HEADER"; 
 public static final String DB_TABLE_OWNER = "APIGEE"; 

	public _HttpRequestHeader(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String HEADER_ID ="HEADER_ID" ;

	public void setHeaderIdValue(java.math.BigDecimal   pm_headerId){
		this.getAttribute(HEADER_ID ).setValue( pm_headerId );
	}
 
	public java.math.BigDecimal getHeaderIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( HEADER_ID).getValue()  ;
	}
 
	public Attribute getHeaderId(){
		return this.getAttribute ( HEADER_ID)  ;
	}

	public static final String HEADER_NAME ="HEADER_NAME" ;

	public void setHeaderNameValue(java.lang.String   pm_headerName){
		this.getAttribute(HEADER_NAME ).setValue( pm_headerName );
	}
 
	public java.lang.String getHeaderNameValue(){
		return (java.lang.String) this.getAttribute ( HEADER_NAME).getValue()  ;
	}
 
	public Attribute getHeaderName(){
		return this.getAttribute ( HEADER_NAME)  ;
	}

	public static final String HEADER_VALUE ="HEADER_VALUE" ;

	public void setHeaderValueValue(java.lang.String   pm_headerValue){
		this.getAttribute(HEADER_VALUE ).setValue( pm_headerValue );
	}
 
	public java.lang.String getHeaderValueValue(){
		return (java.lang.String) this.getAttribute ( HEADER_VALUE).getValue()  ;
	}
 
	public Attribute getHeaderValue(){
		return this.getAttribute ( HEADER_VALUE)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}