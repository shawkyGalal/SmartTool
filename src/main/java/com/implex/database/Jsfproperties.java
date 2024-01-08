package com.implex.database;

import com.implex.database.map.security.PersistentObjectSecurityControl;

public class Jsfproperties
{
	private PersistentObjectSecurityControl sch ; 
	private PersistantObject pop ;
	private String key; 
	private Attribute att ; 

	public Jsfproperties(PersistentObjectSecurityControl pm_sch, Attribute pm_att ) //PersistantObject pm_pop, 	String pm_key) 
	{
		this.sch = pm_sch;
		this.att = pm_att ;
		
	}
	
	public boolean isRendered()
	{
		return this.sch.isRendered(att);
	}
	
	public boolean isRequired()
	{
		return this.sch.isRequired(att);
	}

	public boolean isStoredEncrypted()
	{
		return this.sch.isStoredEncrypted(att);
	}
	
	public String getOnMouseOver()
	{	
		return this.sch.getOnMouseOver(att);
	}
	
	
	public String getOnChangeRerenderIds()
	{
		return this.sch.getOnChangeRerender(att);
	}
	
	public boolean isDisabled()
	{
		return this.sch.isDisabled(att);
	}
	
	public String getOnClick()
	{
		String result = "";
		if (isDisabled()) 
			result = "alert ('You May Not Authorized to Update This Info. Please Contact System Administrator')";
		return result;
	}

	public String getHint()
	{
		if (isDisabled()) 
			return "alert ('You May Not Authorized to Update This Info. Please Contact System Administrator')";

		return  this.sch.getHint(att);
	}
	
	public String getOnChange()
	{

		return  this.sch.getOnChange(att);
	}

	public boolean isSecured()
	{
		return  this.sch.isSecured(att);
	}

}
