package com.smartValue.database;

public class CommandType {

	String m_type;
	public static final CommandType UPDATE_COMMAND = new CommandType("Update_Command");
	public static final CommandType DELETE_COMMAND = new CommandType("Delete_Command");
	public CommandType(String pm_type) {
		this.m_type = pm_type;
	}
	public String toString()
	{
		return this.m_type ; 
	}
	

}
