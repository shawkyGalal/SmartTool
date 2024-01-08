package com.implex.web.swaf.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.sas.util.Util;
import com.sideinternational.web.swaf.AbstractModuleFactory;
import com.sideinternational.web.swaf.SWAF;
import com.sideinternational.web.swaf.WebModule;

public class CustomModuleFactory extends AbstractModuleFactory
{
	/** List of all defined modules. */
	private HashMap m_modules;
	
	/**
	 * Constructor.
	 */
	public CustomModuleFactory()
	{
		m_modules = new HashMap();
		// TODO get All modules from DB
		
	
	}
	
	/**
	 * Instantiates and returns the module implementation for the specified key.
	 * @param pm_moduleName The specified module name.
	 * @param pm_applicationName The specified application name.
	 * @return The module implementation.
	 */
	public WebModule createModule(String pm_moduleName, String pm_applicationName)
	{
		Class moduleClass = (Class)m_modules.get(pm_moduleName);
		if (moduleClass == null)
		{
			Console.log("Error, class for module [" + pm_moduleName + "] not found", 					getClass());
			return null;
		}
		
		try 
		{
			// Instantiates and loads the module.
			WebModule module = (WebModule)Util.instantiateClass(m_moduleClass);
			module.load(pm_moduleName, pm_applicationName);
			
			return module;
		} 
		catch (BaseException e) 
		{ 
			// The error has been logged by SAS. Ignore it at this level.
			return null;
		} 
	}

	/**
	 * Instantiates and returns the modules defined in the system.
	 * @return The modules defined in the system.
	 */
	public WebModule[] createModules()
	{
		ArrayList list = new ArrayList();

		Iterator iter = m_modules.keySet().iterator();
		while (iter.hasNext())
		{
			try 
			{
				String moduleId = (String)iter.next();
				Class moduleClass = (Class)m_modules.get(moduleId);
				
				WebModule module = (WebModule)Util.instantiateClass(moduleClass);
				module.load(moduleId, SWAF.getApplicationId());
				
				list.add(module);
			}
			catch (BaseException e) 
			{ 
				// The error has been logged by SAS. Ignore it at this level.
				e.printStackTrace();
			} 
		}
		
		WebModule[] modules = new WebModule[list.size()];
		return (WebModule[])list.toArray(modules);
	}
}
