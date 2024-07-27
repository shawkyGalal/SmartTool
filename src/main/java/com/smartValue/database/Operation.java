package com.smartValue.database;

import javax.faces.model.SelectItem;

public class Operation {


	public static final String EQUAL = new String ("=");
	public static final String NOT_EQUAL = new String ("<>");
	
	public static final String GREATER = new String(">");
	public static final String GREATER_OR_EQUAL = new String(">=");
	
	public static final String LESS = new String("<");
	public static final String LESS_OR_EQUAL = new String("<=");
	public static final String IN = new String("IN");
	public static final String CONTAINS = new String("CONTAINS");
	
	

	public static final String AND = new String("AND");
	public static final String OR = new String("OR");
	
	public static SelectItem[] allAvailableOperations ;
	public static SelectItem[] allAvailableLogicalOperations ;


	public static SelectItem[] getAllAvailableOperations()
	{
		if (allAvailableOperations == null)
		{
		allAvailableOperations = new SelectItem[] 
		            {	new SelectItem(null,"<Select>") 
					, 	new SelectItem(EQUAL,EQUAL) 
		            , 	new SelectItem(NOT_EQUAL,NOT_EQUAL) 
		            , 	new SelectItem(GREATER,GREATER)
		            , 	new SelectItem(GREATER_OR_EQUAL,GREATER_OR_EQUAL)
		            , 	new SelectItem(LESS,LESS)
		            , 	new SelectItem(LESS_OR_EQUAL,LESS_OR_EQUAL)
		            , 	new SelectItem(IN,IN)
		            , 	new SelectItem(CONTAINS,CONTAINS)
		            };
		}
		return   allAvailableOperations ;
	}
	
	
	public static SelectItem[] getAllAvailableLogicalOperations()
	{
		if (allAvailableLogicalOperations == null)
		{
		allAvailableLogicalOperations = new SelectItem[] 
		            {	new SelectItem(AND,AND) 
		            	,new SelectItem(OR,OR)		            
		            };
		}
		return   allAvailableLogicalOperations ;
	}
	
}
