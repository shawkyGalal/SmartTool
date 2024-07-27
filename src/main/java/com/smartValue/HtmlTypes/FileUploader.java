package com.smartValue.HtmlTypes;

import java.util.ArrayList;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.ValidationResult;

public class FileUploader extends IHtmlType {

	@Override
	public String getJsfTemplateFile() {
		// TODO Auto-generated method stub
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

	@Override
	public ValidationResult isAllowedForDataType(String pmDataType,
			DbServices dbs) {
		ValidationResult result = new ValidationResult() ;
		if (! pmDataType.equalsIgnoreCase("BLOB"))
		{
			result.setValidResult(false);
			result.setInvalidMessage(this.getInvalidMsg(pmDataType, dbs , "17") );
			dbs.getMessageCommnunicatorService().sendValidationresultToUser(result);
		}
		return result;
	}

}
