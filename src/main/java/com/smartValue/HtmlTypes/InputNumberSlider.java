package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.TableMaintDetails;

public class InputNumberSlider extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/inputNumberSlider.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/inputNumberSliderSpecificProperties.xhtml";

	}
	
	ArrayList<String> specificTmdAttributes ; 
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		if (specificTmdAttributes == null)
			{
			specificTmdAttributes =  new ArrayList<String>();
			specificTmdAttributes.add(TableMaintDetails.INPUT_SLIDER_MIN);
			specificTmdAttributes.add(TableMaintDetails.INPUT_SLIDER_MAX);
			specificTmdAttributes.add(TableMaintDetails.INPUT_SLIDER_STEP);
			specificTmdAttributes.add(TableMaintDetails.SHOW_TOOL_TIP);
			}
		return specificTmdAttributes ; 
	}
	public ValidationResult isAllowedForDataType(String pmDataType , DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date")
			|| pmDataType.equalsIgnoreCase("NVARCHAR2")
			|| pmDataType.equalsIgnoreCase("NVARCHAR")
			|| pmDataType.equalsIgnoreCase("VARCHAR")
			|| pmDataType.equalsIgnoreCase("VARCHAR2"))
			{
				result.setValidResult(false);
				result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "13") );
				dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
			}
		return result;
	}

}
