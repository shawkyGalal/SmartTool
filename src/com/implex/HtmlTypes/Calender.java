package com.implex.HtmlTypes;

import java.util.ArrayList;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.ValidationResult;
import com.implex.database.map.TableMaintDetails;

public class Calender extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/calendar.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		// TODO Auto-generated method stub
		return "/templates/include/htmlTypesSpecificProperties/calenderSpecificProperties.xhtml";
	}
	ArrayList<String> specificTmdAttributes ; 
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		if (specificTmdAttributes == null)
			{
			
			specificTmdAttributes =  new ArrayList<String>();
			specificTmdAttributes.add(TableMaintDetails.POP_UP);
			specificTmdAttributes.add(TableMaintDetails.CALENDER_LOCALE );
			specificTmdAttributes.add(TableMaintDetails.PATTERN);
			specificTmdAttributes.add(TableMaintDetails .SHOW_APPLY_BUTTON);
			
			}
		return specificTmdAttributes ; 
	}

	@Override
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult();
		if(pmDataType.equalsIgnoreCase("DATE"))
			result.setValidResult(true); 
		else {
				
				result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "4") );
				result.setValidResult(false);
				dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
			}
		return result;
	}
	
	
}
