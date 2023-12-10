package com.implex.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Batch {
	
	private List<Command> commandList ;

	private boolean exitWhenFirstError = true ;
	private ArrayList<Attribute>  allInvalidAttributes = new ArrayList<Attribute>() ;

	public Batch(List<Command> pm_commandList) throws Exception
	{
		this.commandList = pm_commandList ; 
		for (Command cmd : commandList )
		{
			PersistantObject po =  cmd.getPersistantObject() ;
			if (! po.isContainsObjectKey())
			{
				po.beforeInsert() ;
				po.applyParentKeys();
				po.refreshInvalidAttList(); 
			}
			else
			{
				po.beforeUpdate() ;
			}
			allInvalidAttributes.addAll( po.getInvalidModifiedAttributes() ) ; 
		}
	}
	
	public ArrayList<Attribute> getAllInvalidAttributes() {
		return allInvalidAttributes;
	}
	
	public ArrayList<ValidationResult> getInvalidResults()
	{
		ArrayList<ValidationResult> result = new ArrayList<ValidationResult>() ;
		ArrayList<Attribute> allInvalidAttributes = this.getAllInvalidAttributes() ; 
		for (Attribute att : allInvalidAttributes)
		{
			result.add(att.getValidationResult());
		}
		
		return result ; 
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	public BatchExecuteResult executeForDirectJdbc(DirectJdbcServiceImpl dbs) throws SQLException {
		BatchExecuteResult result = new BatchExecuteResult() ; 
		List<Command> commandList = this.getCommandList() ;
		Connection con = dbs.getConnection();
		con.setAutoCommit(false);
			
		for ( Command command :  commandList )
		{
			try 
				{
					PersistantObject po = command.getPersistantObject();
					if (command.getType().equals(CommandType.DELETE_COMMAND))
					{
						dbs.delete(po, con);
					}
					
					else if (command.getType().equals(CommandType.UPDATE_COMMAND))
					{
						dbs.update(po , con);		
						po.afterSaveInitialization();
					}
					
					else 
					{
						String msg = "Command Type Not Yet Implemented"; 
						throw new Exception((msg));
					}
				}
				catch (Exception e)
				{
					result.getExceptions().add(e);
					e.printStackTrace();
					if (exitWhenFirstError)
					{
						break;
					}
				}
		}
			
		
		dbs.returnConnection(con);
		
		if (result.getExceptions().isEmpty())
		{
			con.commit(); 
			con.setAutoCommit(true);
		}
		else
		{
			try {con.rollback();	} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		return result ; 
	}

}
