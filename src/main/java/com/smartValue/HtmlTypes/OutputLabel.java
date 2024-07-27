package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;

public class OutputLabel extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		// TODO Auto-generated method stub
		return "/templates/include/controls/outputLabel.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		
		return new ArrayList<String>();
	}
	public ValidationResult  isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "14") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}

}
