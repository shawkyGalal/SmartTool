package com.smartValue.database;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Set;


public class DBKey{
	
	/**
	 * represent the unique columns with its values 
	 */
	LinkedHashMap<String , Attribute> keys ;//= 
	public static final String UNIQUE_KEY = "U";
	public static final String PRIMARY_KEY = "P";
	public static final String FORIGN_KEY = "F";
	private String keyType ;
	
	public DBKey (String pm_keyType )
	{
		setKeyType(pm_keyType);
		this.keys = new LinkedHashMap<String , Attribute >();
	}
	
	public DBKey ( String[] columns)
	{
		this.keys = new LinkedHashMap<String , Attribute >();
		for (int i = 0 ; i< columns.length ; i++)
		{
			this.addColumn(columns[i]);
		}
		
	}
	
	public void addColumn ( String column)
	{
		this.keys.put(column , null);
	}
	
	public LinkedHashMap<String , Attribute > getUniqueMap()
	{
		return keys;
	}
	
	public boolean equal (Object uniquekey2)
	{
		boolean result = false;
		if (uniquekey2 instanceof DBKey)
		{
			DBKey uk2 = (DBKey) uniquekey2 ; 
			Set<String> s2 = uk2.getUniqueMap().keySet();
			Set<String> s1 = this.getUniqueMap().keySet() ;
			if (! s1.equals(s2 ) )
			{
				return false;
			}
			else 
			{
				for (String key : s1 )
				{ 
					String objStrValue  =   this.getUniqueMap().get(key).getAttributeSQLValue(true);
					String thisStrValue =   uk2.getUniqueMap().get(key).getAttributeSQLValue(true);
					if (objStrValue != null && !objStrValue.equalsIgnoreCase(thisStrValue) )
					{
						return false;
					}
				}
				
				result = true;
			}
			
		}
		return result; 
	}
	
	public String toString()
	{
		return this.getUniqueMap().toString();
	}

	public String[] getKeys() {
		// TODO Auto-generated method stub
		String[] result = new String[this.getUniqueMap().size()];
		return this.getUniqueMap().keySet().toArray(result);
	}

	public String getEqualSqlString() {
		return this.getSqlString("=");
	}
	
	
	public String getSqlString(String pm_relation) {
		String result =null;
		int counter = 0;
		for (String key : this.getUniqueMap().keySet() )
		{ 
			String objStrValue  =  this.getUniqueMap().get(key).getAttributeSQLValue(true);
			result += ((counter == 0)? "t." : " And t." )+ key + pm_relation +  objStrValue ; 
			counter++;
		}
		return (result!= null )? "And " : "" +  result;
	}

	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return getUniqueMap().containsKey(key);
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeyType() {
		return keyType;
	}
	
	public String toFoldersPath()
	{
		String result ="";
		int counter = 0;
		for (String key : this.getUniqueMap().keySet() )
		{ 
			String objStrValue  =  this.getUniqueMap().get(key).getAttributeSQLValue(true);
			result += ((counter == 0)? "" : File.separatorChar )+ key + "=" +  objStrValue ; 
			counter++;
		}
		return result;
	}
	
}
