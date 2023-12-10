package com.implex.database;




public  class Command {
	
	private PersistantObject m_pos;
	
	private CommandType m_type;
		

	public CommandType getType() {
		return m_type;
	}
	public PersistantObject getPersistantObject()
	{
		return m_pos;
	}

	public Command(PersistantObject pm_pos, CommandType pm_commandType ) {
		this.m_pos = pm_pos;
		this.m_type = pm_commandType;
		
	}
	

	
}
