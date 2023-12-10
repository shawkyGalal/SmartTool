package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;
import com.implex.database.map.TableMaintDetails;

public class ComboBox extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/selectOneMenu.xhtml";
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
			specificTmdAttributes =  new ArrayList<String>();
			specificTmdAttributes.add(TableMaintDetails.SELECT_LIST_QUERY);
			specificTmdAttributes.add(TableMaintDetails.HAS_INPUTTEXT);
			
			}
		return specificTmdAttributes ; 
	}
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result =new ValidationResult () ;
		if(pmDataType.equalsIgnoreCase("Date"))
			{
				result.setValidResult(false);
				result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "4") );
				dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
			}
		return result;
	}
}
