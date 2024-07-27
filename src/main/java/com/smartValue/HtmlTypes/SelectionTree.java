package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;

public class SelectionTree extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/inputTree.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/listSpecificProperties.xhtml";

	}
	
	ArrayList<String> specificTmdAttributes ; 
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		if (specificTmdAttributes == null)
		{
		ComboBox cb = new ComboBox();
		specificTmdAttributes=  cb.getSpecificTmdAttributes();
		}
		//TODO Add Specifc Tree Attributes...
		return specificTmdAttributes;
	}
	@Override
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult();
		if(pmDataType.equalsIgnoreCase("Date"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "10") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
}
