package com.smartValue.database;

import java.util.ArrayList;

public class ValidationResult
{
	private boolean validResult = true;
	private ArrayList <String> invalidMessages = new ArrayList <String> ();
	
	public ValidationResult()
	{
		
	}
	public ValidationResult(String msg) 
	{
		validResult =  (msg == null) ; 
		if (msg!= null) invalidMessages.add(msg) ; 
	}
	public void setValidResult(boolean validationResult)
	{
		this.validResult = validationResult;
	}
	public boolean isValidResult()
	{
		return validResult;
	}
	public void addInvalidMessage(String invalidMessage)
	{
		this.invalidMessages.add( invalidMessage );
	}
	public void setInvalidMessage(String invalidMessage)
	{
		if (this.invalidMessages.isEmpty()) this.invalidMessages.add(null);
		this.invalidMessages.set(0, invalidMessage );
	}
	
	public String getInvalidMessage()
	{
		return (invalidMessages.isEmpty())? null : invalidMessages.get(0);
	}
	
	public ArrayList<String> getInvalidMessages()
	{
		return invalidMessages ;
	}

}
