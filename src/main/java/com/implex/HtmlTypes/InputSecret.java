package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;

public class InputSecret extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/inputSecret.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {

		return DummyProperyPage;
	}

	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		return new ArrayList<String>();
	}

	@Override
	public ValidationResult isAllowedForDataType(String pmDataType,
			DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "15") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}

}
