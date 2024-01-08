package com.implex.database;

import java.util.ArrayList;

public class CommandBatch {

	private ArrayList<String> commands ;
 
	public CommandBatch(ArrayList<String> pm_commands)
	{
		this.setCommands(pm_commands);
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

}
