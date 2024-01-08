package com.implex.components.model;

import java.io.Serializable;

public class UserResponse implements Serializable
{
	private boolean okButtonPressed = false;
	private boolean cancelButtonPressed = false;
	private String userInputString = null;
	
	public void clickOK()
	{
		this.setOkButtonPressed(true);
	}
	
	public void clickCancel()
	{
		this.setCancelButtonPressed(true);
	}
	public boolean isOkButtonPressed()
	{
		return okButtonPressed;
	}
	public void setOkButtonPressed(boolean okButtonPressed)
	{
		this.okButtonPressed = okButtonPressed;
	}
	public boolean isCancelButtonPressed()
	{
		return cancelButtonPressed;
	}
	public void setCancelButtonPressed(boolean cancelButtonPressed)
	{
		this.cancelButtonPressed = cancelButtonPressed;
	}
	public String getUserInputString()
	{
		return userInputString;
	}
	public void setUserInputString(String userInputString)
	{
		this.userInputString = userInputString;
	}

}
