package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;

public class LargeObject extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		return "/templates/include/controls/attMediaOutput.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/fileUploaderProperties.xhtml";

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
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "5") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
}
