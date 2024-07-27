package com.smartValue.database;

import java.util.ArrayList;
import java.util.List;

public class BatchExecuteResult  {
	
	private ArrayList<Exception> exceptions = new ArrayList<Exception>();
	private ArrayList<String> warnnings = new ArrayList<String>();
	private ArrayList<String> notifications = new ArrayList<String>() ;
	private ArrayList<DataSet> dataSets ; 

	public void setExceptions(ArrayList<Exception> exceptions) {
		this.exceptions = exceptions;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void setWarnnings(ArrayList<String> warnnings) {
		this.warnnings = warnnings;
	}

	public ArrayList<String> getWarnnings() {
		return warnnings;
	}

	public void setNotifications(ArrayList<String> notifications) {
		this.notifications = notifications;
	}

	public ArrayList<String> getNotifications() {
		return notifications;
	}

	public ArrayList<DataSet> getDataSets() {
		return dataSets;
	}
	
	String defaultJsfRendererPage = "/templates/include/batchExecResult.xhtml" ; 
	public String getDefaultJsfRendererPage()
	{
		return defaultJsfRendererPage ;
	}

}
