package com.implex.event.logging;

/**
 * Copyright (c) 2007 SIDE International
 * SIDE Resources & Development Belgium S.A.
 * Rue du Cerf, 200 B-1330 Rixensart, Belgium
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SIDE International ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with SIDE International. 
 */

//package com.sideinternational.sas.event.logging;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServiceFactory;
import com.implex.database.DbServices;
import com.implex.database.map.services.SysParamsServices;
import com.sideinternational.sas.util.Time;

/**
 * Logs messages in a formatted way in the standard system output.  
 */
public class Console 
{
	/** By default, the console output log is enabled. */

	
	
	public static final int ALL_LOGGING_LEVEL = 4;
	public static final int NOTIFICATION_LOGGING_LEVEL = 3;
	public static final int ERROR_LOGGING_LEVEL = 2;
	public static final int SEVER_LOGGING_LEVEL = 1;
	public static final int NO_LOGGING_LEVEL = 0;
	private static boolean enabled = true;
	
	private static DbServices dbs ; 
	//private static String sysParamLogLevel = "0";
	private static int  logLevel = 0 ;
	
	
	public static int getLogLevel()
	{
		if (dbs == null)
		{
			dbs = getDbService() ; 
		}
		if (dbs != null) 
		{
			try 
			{
				String sysParamLogLevel = "4" ;
				boolean alreadyLogged =   dbs.getLoggedUser() != null ;
				if ( alreadyLogged)
				{
					SysParamsServices sps =   dbs.getModuleServiceContainer().getSysParamsServices();
					sysParamLogLevel = sps.getConsoleLoggingLevel().getValValue() ; 
				}
					 
				//if (sysParamLogLevel != null)
				logLevel = Integer.parseInt(  sysParamLogLevel);
			} 
			catch (Exception e) {
				
			}
		}
		return logLevel ; 
	}
	/**
	 * Logs a message in the standard system output.  
	 * @param pm_message The message to log.
	 * @param pm_source The message source info.
	 */
	public synchronized static void log(String pm_message, Class pm_source , int pm_loglevel  )
	{
		if (! Console.enabled)
		{
			return;
		}
		if (pm_loglevel <= getLogLevel() )
		{
			if (pm_message == null || pm_source == null) throw new IllegalArgumentException("Arguments cannot be null.");
			System.err.println(formatMessage(pm_message, pm_source));
		}
	}
	
	public synchronized static void log(String pm_message, Class pm_source   )
	{
		Console.log(pm_message, pm_source, 4);
	}

	private static DbServices getDbService() 
	{
		 DbServices dbs = null;
		 try
		 {
			 dbs= DbServiceFactory.create();
			 dbs.initializeForWeb();
		 }
		 catch (Exception e){
			 
			 dbs.initialize("SmartTool" , 1);
		 }
		 return dbs;
	}
	
	
	
	/**
	 * Creates the message to log.
	 * @param pm_message The 'core' message.
	 * @param pm_source The message source.
	 * @return The message to log.
	 */
	private static String formatMessage(String pm_message, Class pm_source)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("[SMART-NO_EVENT] [");
		sb.append(new Time().getFormattedDateTime());
		sb.append("] ");
		sb.append(pm_message);
		sb.append(" [");
		sb.append(pm_source.getName());
		sb.append(']');
		
		return sb.toString();
	}
	public static void setEnabled(boolean pm_enable) {
		
		enabled = pm_enable ; 
	}

	

}
