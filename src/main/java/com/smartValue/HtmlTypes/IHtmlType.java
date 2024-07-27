package com.smartValue.HtmlTypes;


import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.services.ModuleServicesContainer;

public abstract class IHtmlType {

	public final String DummyProperyPage = "templates/include/htmlTypesSpecificProperties/dummySpecificProperties.xhtml‬";
	public abstract String getJsfTemplateFile();
	/**
	 * 
	 * @return
	 */
	public abstract String getSpecificPropertiesPage();
	/**
	 * 
	 * @return {@link TableMaintDetails} attributes which need to be set if user select this type entry 
	 */
	public abstract ArrayList<String> getSpecificTmdAttributes();
	public abstract ValidationResult isAllowedForDataType(String pm_DataType , DbServices dbs);
	
	protected String getInvalidMsg(String pmDataType , DbServices dbs , String htmlTypeCode)
	{
		ArrayList<String> param = new ArrayList<String>();
		param.add(TableMaintDetails.getHtmlTypeDesc(htmlTypeCode , dbs));
		param.add(pmDataType);
		ModuleServicesContainer msc = dbs.getModuleServiceContainer() ; 
		SysMsg sysMsg = msc.getSysMsgServices().getInvalidHtmlTypeMsg();
		sysMsg.setParams(param);
		msc.getSysMsgServices().setCurrentActiveMessage(sysMsg);

		return sysMsg.getMsgDescWithParam() ;
	}
}
