package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.TableMaintDetails;

public class RichEditor   extends IHtmlType
{
	@Override
	public String getJsfTemplateFile() {
		
		return "/templates/include/controls/RichEditor.xhtml";
	}

	@Override
	public String getSpecificPropertiesPage() {
		return "/templates/include/htmlTypesSpecificProperties/richEditorProperties.xhtml";

	}
	
	ArrayList<String> specificTmdAttributes ; 
	@Override
	public ArrayList<String> getSpecificTmdAttributes() {
		if (specificTmdAttributes == null)
			{
			specificTmdAttributes =  new ArrayList<String>();
			specificTmdAttributes.add(TableMaintDetails.THEME);
			specificTmdAttributes.add(TableMaintDetails.VIEWMODE);
			}
		return specificTmdAttributes ; 
	}

	@Override
	public ValidationResult isAllowedForDataType(String pmDataType,
			DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if(pmDataType.equalsIgnoreCase("Date")||pmDataType.equalsIgnoreCase("NUMBER")||pmDataType.equalsIgnoreCase("INTEGER")||pmDataType.equalsIgnoreCase("FLOAT"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "16") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}
	
	
	
}
