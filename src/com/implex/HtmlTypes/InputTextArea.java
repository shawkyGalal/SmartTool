package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;

public class InputTextArea extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		// TODO Auto-generated method stub
		return 	 "/templates/include/controls/inputTextArea.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/dummySpecificProperties.xhtml";

	}
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		return new ArrayList<String>();
		
	}
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "7") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
}
