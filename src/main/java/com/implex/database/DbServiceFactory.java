package com.implex.database;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.util.Util;

public class DbServiceFactory {

	private static String  DB_SERVICE_IMPL_CLASS = "databaseService-impl-Class";

	public static DbServices create() throws BaseException
	{
		String dbServiceImplClassName = Configuration.getImplementationClass(DB_SERVICE_IMPL_CLASS);
		DbServices dbs =  (DbServices) Util.instantiateClass(dbServiceImplClassName);	
		return  dbs;

	}
}
