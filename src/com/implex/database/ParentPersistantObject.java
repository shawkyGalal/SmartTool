package com.implex.database;

import java.io.Serializable;


public class ParentPersistantObject implements Serializable
{
	private PersistantObject persistantObject ;
	private DbForignKeyArray keys;

	public ParentPersistantObject(PersistantObject pm_persistantObject)
	{
		this.setPersistantObject(pm_persistantObject);
	}
	public void setPersistantObject(PersistantObject persistantObject)
	{
		this.persistantObject = persistantObject;
	}
	public PersistantObject getPersistantObject()
	{
		return persistantObject;
	}
	public void setForignKeys(DbForignKeyArray keys)
	{
		this.keys = keys;
	}
	public DbForignKeyArray getForignKeys()
	{
		return keys;
	}

}
