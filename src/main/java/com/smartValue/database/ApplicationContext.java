package com.smartValue.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysFrm;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.services.MasSysCodeLookupsServices;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.event.logging.Console;

public abstract class ApplicationContext {
	protected static final Logger logger = LogManager.getLogger();

	private static 		Map<String , Pool> jdbcConnectionPools = null;
	private static 	  	List <SecUsrDta> listOfLoggedUsers = new ArrayList<SecUsrDta>();
	private static   	HashMap<String, SysMsg> listOfUsedSysMsgs = new HashMap<String, SysMsg>();
	private static    	HashMap<String, SysFrm> listOfUsedSysFrms = new HashMap<String, SysFrm>();
	private static    	MessagesCommnuicatorService messegeCommunicationServer ;
	//private static    	SecUsrDta loggedUserNotWeb;
	private static	  	HashMap<String, ArrayList<SelectItem>> environmentTableNames = new HashMap<String, ArrayList<SelectItem>>();
	
	public static void setPools(Map<String , Pool>  pools) {
		ApplicationContext.jdbcConnectionPools = pools;
	}

	public static Map<String , Pool>  getPools() {
		return jdbcConnectionPools;
	}
	
	private static SelectItem[]  environmentNames ; 
	public static SelectItem[] getEnvironmentNames()
	{
		if (environmentNames== null)
		{
			Map<String , Pool> pools = getPools();
			environmentNames = new SelectItem[pools.size()];
			Iterator<String>it = pools.keySet().iterator();
			int i = 0 ;
			while (it.hasNext())
			{
				String name =  it.next();
				environmentNames[i] = new SelectItem(name , name);
				i++;
			}
		}
		return environmentNames ; 
	}

	public static synchronized Connection popConnection(String pm_selectedEnv)
	{
		Pool p = (Pool) jdbcConnectionPools.get(pm_selectedEnv);
		Connection con = null;
		con = ( Connection ) p.pop();
		if (con == null )
		{
			try {
				Thread.currentThread().wait(2000);
				con = ( Connection ) p.pop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (con == null)
		{
			
		}

		return con;
	}

	public static void pushConnection(Connection pm_con, String pm_selectedEnv)
	{
		Pool px = (Pool) jdbcConnectionPools.get(pm_selectedEnv);
		px.push(pm_con);
	}
	
	public static void destroy() {
	
		Iterator keyIterator = jdbcConnectionPools.keySet().iterator();
		Console.log("Closing DB Connections....", ApplicationContext.class);
		logger.info("Closing DB Connections....") ; 
		while (keyIterator.hasNext())
		{
			try{
				Pool p = (Pool) jdbcConnectionPools.get(keyIterator.next());
				
				for (int i = 0 ; i < p.getStack().size() ; i++ )
				{
					Connection con = (Connection)(p.pop());
					try{
					if (con != null ) con.close();
					}
					catch (SQLException e) {e.printStackTrace();}
				}
			}
			catch (Exception e) {e.printStackTrace();}
		}
	}



	public static List <SecUsrDta> getListOfLoggedUsers()
	{
		return listOfLoggedUsers;
	}

	public static void addLoggedUser(SecUsrDta sud)
	{
		getListOfLoggedUsers().add(sud);
	}

	public static void removeLoggedUser(SecUsrDta sud)
	{
		getListOfLoggedUsers().remove(sud);
	}
	


	public static HashMap<String, SysMsg> getListOfUsedSysMsgs()
	{
		return listOfUsedSysMsgs;
	}
	


	


	public static HashMap<String, SysFrm> getListOfUsedSysFrms() {
		return listOfUsedSysFrms;
	}

	
	public  void setEnableConsoleLogging(boolean enable)
	{
		Console.setEnabled(enable);
	}


	/*
	public static SecUsrDta getCurrentLoggedUser()
	{
		SecUsrDta result = null ;
		try{
		result = (SecUsrDta) SWAF.getSession().getOperator();
		}
		catch (Exception e)
		{
			result = ApplicationContext.getLoggedUser();
		}
		return result;
	}
	*/

	//public static void setLoggedUser(SecUsrDta loggedUser) {
	//	ApplicationContext.loggedUserNotWeb = loggedUser;
	//}

	//private static SecUsrDta getLoggedUser() {
	//	return loggedUserNotWeb;
	//}

	//TODO Sakr Are we need this Method
	public static void setEnvironmentTableNames(HashMap<String, ArrayList<SelectItem>> environmentTableNames) {
		ApplicationContext.environmentTableNames = environmentTableNames;
	}

	public static HashMap<String, ArrayList<SelectItem>> getEnvironmentTableNames() {
		return environmentTableNames;
	}

	
	private static MasSysCodeLookupsServices masSysCodeLookups;

	public static boolean isWebApp() {
		try{
			SWAF.getManagedBean("moduleServicesContainer") ; 
		}
		catch (Exception e){
			return false;
		}
		return true;
	}

	//public  static HashMap <String, ModuleServicesContainer >    clientModuleServicesContainers = new HashMap <String , ModuleServicesContainer > ();

	
	//public static  void setClientModuleServicesContainer(ModuleServicesContainer pm_moduleServicesContainer)
	//{
	//	clientModuleServicesContainer = pm_moduleServicesContainer ; 
	//}
	public static ModuleServicesContainer getClientModuleServicesContainerx(DbServices m_dbs)
	{
		return m_dbs.getModuleServiceContainer() ;  
	}
	
	
	public static ModuleServicesContainer generateModuleServicesContainer(String environment_name , int userLang)
	{
		ModuleServicesContainer    clientModuleServicesContainer =  null; //clientModuleServicesContainers.get(environment_name) ;
		try {
				clientModuleServicesContainer = new ModuleServicesContainer(environment_name , userLang) {
					private static final long serialVersionUID = 1L;

					@Override
					public void loadAllModules() {
						super.loadBasicModules();
					}
				};

			} catch (Exception e) {
				e.printStackTrace();
		}

		return clientModuleServicesContainer ; 
	}
	
	
	public  static ModuleServicesContainer    clientUwModuleServicesContainer ;
	
	public static ModuleServicesContainer getUwModuleServicesContainer()
	{
		
		ModuleServicesContainer result ; 
		try{
			result = ((ModuleServicesContainer) SWAF.getManagedBean("uwModuleServicesContainer"));
		}
		catch (Exception e)
		{
			result = clientUwModuleServicesContainer ; 
		}
		
		return   result ;
		
	}

	private static HashMap<String, ArrayList<SelectItem>>   sysLanguages = new HashMap<String, ArrayList<SelectItem>>();
	public static ArrayList<SelectItem> getSysLanguages(String environment) {
		return sysLanguages.get(environment);
	}

	public static void setSysLanguages(ArrayList<SelectItem> languages ,String environment ) {
		sysLanguages.put(environment, languages)  ; 
		
	}

	public  static ModuleServicesContainer    clientHrModuleServicesContainer ;
	public static ModuleServicesContainer getHrModuleServicesContainer() {
		ModuleServicesContainer result ; 
		try{
			result = ((ModuleServicesContainer) SWAF.getManagedBean("hrModuleServicesContainer"));
		}
		catch (Exception e)
		{
			result = clientHrModuleServicesContainer ; 
		}
		
		return   result ;
	}
	

public static void sendMessageToUser(SecUsrDta m_usser ,  String msg)
{
	try{
		m_usser.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msg);
	}catch(Exception e)
	{
		Console.log(msg, ApplicationContext.class);
	}
	
}
	
}
