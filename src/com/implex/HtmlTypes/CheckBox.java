package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;

public class CheckBox extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
	
		return  "/templates/include/controls/selectBooleanCheckBox.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		
		return "/templates/include/htmlTypesSpecificProperties/dummySpecificProperties.xhtml";
	}

	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		return new ArrayList<String>();
		
	}

	@Override
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult();
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result .setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "9") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
}
