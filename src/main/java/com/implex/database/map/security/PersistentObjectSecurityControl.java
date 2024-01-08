package com.implex.database.map.security;


import com.implex.database.Attribute;
import com.implex.database.PersistantObject;


public interface  PersistentObjectSecurityControl
{
	/**
	 * Checks if the given user has the authority to see the given column in the given persistent object	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isSecured( Attribute att);
	
	/**
	 * Checks if the given user has the authority to see the given column in the given persistent object	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isRendered(Attribute att);
	
	/**
	 * Checks if the given user has the authority to delete the given persistent object	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isObjectCanBeDeleted(PersistantObject pm_persistentObject);
	
	/**
	 * Checks if the given user has the authority to delete the given persistent object	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isObjectCanBeSaved(PersistantObject pm_persistentObject);
	/**
	 * Checks if the given user has the authority to add the given persistent object	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isObjectCanBeAdded(PersistantObject pm_persistentObject);
	
	/**
	 * Check if the given column is mandatory in the given Persistent Object for the given user	
	 * @param secUserData
	 * @param pm_persistentObject
	 * @param pm_columnName
	 * @return
	 */
	public abstract boolean isRequired(Attribute att);
	public abstract String getOnChange( Attribute att);
	public abstract String getOnMouseOver( Attribute att);
	/**
	 * Return JSF Objects Id's to be Rerenderd when attribute changed
	 * @param secUserData
	 * @param persistantObject
	 * @param pm_key
	 * @return
	 */
	public abstract String getOnChangeRerender( Attribute att);
	
	public abstract boolean isDisabled( Attribute att);

	public abstract String getHint( Attribute att);


	public abstract boolean isStoredEncrypted( Attribute att);

}
