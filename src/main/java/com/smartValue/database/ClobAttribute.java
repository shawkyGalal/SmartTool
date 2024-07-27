package com.smartValue.database;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.sql.SQLException;

import com.smartValue.database.map.services.ModuleServicesContainer;

import oracle.sql.CLOB;



public class ClobAttribute extends Attribute  implements Serializable
{
	final static int DisplayThrashold =100 ;
	private boolean truncated = false ; 
	public ClobAttribute(String pmKey, Object pmAttributeValue, PersistantObject pmParentPersistantObject) 
	{
		super(pmKey, pmAttributeValue, pmParentPersistantObject);
	}
	
	public String getStringValue()
	{
		String result = null ; 
		String clobStrValue = this.getClobStrValue() ;
		if ( clobStrValue != null)
		{
			truncated  = clobStrValue.length() > DisplayThrashold ; 
			result = (truncated) ?  clobStrValue.substring(0, DisplayThrashold) + "..." :  clobStrValue ; 
		}
		return result ;
	}
	
	
	private String clobStrValue ; 
	
	public String getClobStrValue( )
	{	
		if (clobStrValue == null || clobStrValue.equals("") || this.isChanged())
		{
		clobStrValue = this.calcClobStrValue() ; 
		}
		return clobStrValue ; 
	}
	
	private String calcClobStrValue( )
	{
		String result = null ;
		try {
			
			if (this.currentValue instanceof oracle.sql.CLOB)
			{	
				Reader isr = ((oracle.sql.CLOB)this.currentValue).getCharacterStream();
		        
		        StringBuffer clobResult =  new StringBuffer();
		        if (isr != null)
		        { 
		          java.io.BufferedReader br  = new java.io.BufferedReader(isr);
		          String line  ="";
		          while ((line = br.readLine())!=null) 
		          {
		        	  clobResult.append(line +"\n");
		          }
		          result = clobResult.toString();
		        }
			}
			
			else 
			{
				result =  (this.currentValue  != null )? this.currentValue .toString() : "";
			}
			
		} catch (SQLException e) {
			DbServices dbs = this.getParentPersistantObject().getDbService();
			ModuleServicesContainer msc = dbs.getModuleServiceContainer() ;
			msc.getMessageCommnunicatorService().sendMessageToUser("Error During reading Clob.") ;
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ; 
	}
	
	protected  boolean isValuesDiffrent( Object pm_newValue)
	{
		boolean result = false ;
		
		
		if (this.currentValue == null && pm_newValue == null ) return false;
		else if (this.currentValue == null && pm_newValue != null ) return true;
		else if (this.currentValue != null && pm_newValue == null ) return true;

		else if (this.currentValue instanceof CLOB && pm_newValue instanceof String)
			{
				String orignalValueAsString = null ; 
				orignalValueAsString = this.getClobStrValue();
				result =  ! pm_newValue.equals(orignalValueAsString);
			}
		else 
		{
			result   =  ( !this.currentValue.equals(pm_newValue) );		
		}
		return result ;
	}
	
	public Object getValue()
	{
		return getClobStrValue() ; 
	}

	
	public boolean isTruncated() {
		return truncated;
	}


}