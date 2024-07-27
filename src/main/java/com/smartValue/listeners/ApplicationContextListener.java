package com.smartValue.listeners;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Support.XMLConfigFileReader;

import com.sideinternational.sas.configuration.ApplicationEnvironment;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.Pool;


public class ApplicationContextListener extends com.sideinternational.web.swaf.ApplicationContextListener{
	protected static final Logger logger = LogManager.getLogger();


	public void contextInitialized(ServletContextEvent pm_event)
	{
		ApplicationEnvironment.addInformation("SmartValue Core Library :", PersistantObject.class.getPackage());
		super.contextInitialized(pm_event);
		Map<String, Pool> allPools = ApplicationContextListener.initializeConnections();	
		ApplicationContext.setPools(allPools);
	}
	
	public static void main(String[] abc) throws MalformedURLException
	{
		Map<String, Pool> allPools = ApplicationContextListener.initializeConnections(ApplicationContextListener.getConnectionsFile());
	}

	public void contextDestroyed(ServletContextEvent pm_event)
	{
		super.contextDestroyed(pm_event);
		ApplicationContext.destroy();
	}
	static URL connectionsFile ;
	private static void setConnectionsFile(URL pm_connectionsFile)
	{
		connectionsFile = pm_connectionsFile ; 
	}
	
	public static URL getConnectionsFile() throws MalformedURLException {
		if (connectionsFile == null)
		{
			connectionsFile =   new URL ("file://" +((isWindowsOS())?"/":"") + Configuration.getConfigurationHome() + File.separator + SWAF.getConfigurationSubDirectory()+File.separator + "Connections_config.xml") ;	
		}
		return connectionsFile ; 
	}
	// Assuming Connection Configuration file located in the class path 
	public static URL getConnectionsFile_new() throws MalformedURLException {
		if (connectionsFile == null)
		{
		  String path = "config" + "/" + SWAF.getConfigurationSubDirectory()+"/Connections_config.xml" ; 
		  connectionsFile = ApplicationContextListener.class.getClassLoader().getResource(path);
		}
		return connectionsFile ; 
	}
	private static boolean isWindowsOS()
	{
		boolean result = false ; 
		String osName = System.getProperty("os.name").toLowerCase();
		//Console.log("Operating System Name :" +osName , ReadEnvironment.class);
		if (osName.indexOf("windows") > -1) result = true;
		else result = false;
		return result ; 
	}
	private static XMLConfigFileReader xmlConnectionsData = null ; 

	public static XMLConfigFileReader getXmlConnectionsData() throws MalformedURLException, Exception
	{
		if (xmlConnectionsData == null) 
		{
			xmlConnectionsData = new XMLConfigFileReader(getConnectionsFile() , false );
		}
		return xmlConnectionsData ; 
	}
	public static Map<String, Pool> initializeConnections(URL url)
	{
		setConnectionsFile(url) ; 
		Map<String, Pool> allPools= new HashMap<String, Pool>();
		
		try
		{
			Console.log("Connection Configuration File : " + url.getPath() , ApplicationContextListener.class);
			logger.info("Connection Configuration File : " + url.getPath());
			XMLConfigFileReader connectionsData = new XMLConfigFileReader(url , false );
			Vector<Support.ConnParms> conParms = connectionsData.connParms;
			
			for (int i = 0 ; i< conParms.size() ; i++ ) 
            {
				Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
				if (thisConParms.active.equals("Y"))
                { 
					Pool pool = new Pool(thisConParms.getMaxPoolSize()); 
					try{
						if (thisConParms.password != null)
						{
							for (int j = 0 ; j< thisConParms.getMaxPoolSize() ; j++)
							{
								pool.push( thisConParms.generateConnection());
							}
							if (thisConParms.getMaxPoolSize() > 0 ) Console.log("A new Pool (Size = "+thisConParms.getMaxPoolSize()+")  <" + thisConParms.name +"> of Connections Have been Created Successfuly ", ApplicationContextListener.class);
						}	
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Console.log("Unable to Connect To : " + thisConParms.name , ApplicationContext.class);
						logger.info("Unable to Connect To : " + thisConParms.name ) ; 
					}
					allPools.put(thisConParms.name, pool);	
                } 
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return allPools;
	}
	public static Map<String, Pool> initializeConnections()
	{
		Map<String, Pool> allPools =null;
		try{
			allPools =  ApplicationContextListener.initializeConnections(getConnectionsFile());
		}
		catch (Exception e){e.printStackTrace();}
		return allPools;

	}
	

}
