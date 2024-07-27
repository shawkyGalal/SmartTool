package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;

public class RichInputText extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/richInputText.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/dummySpecificProperties.xhtml";

	}
	ArrayList<String> specificTmdAttributes ; 
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		if (specificTmdAttributes == null)
		{
			InputText cb = new InputText();
			specificTmdAttributes=  cb.getSpecificTmdAttributes();
		}
		//Add Sphecific Rich Attributes...
		return specificTmdAttributes;
	}
	public ValidationResult  isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "11") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
}
