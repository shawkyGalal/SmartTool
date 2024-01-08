package com.implex.jsf.converters;

import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.implex.database.Header;
import com.implex.database.map.DataTableDisplayProperities;

public class ConverterForUserSelectedHeaders implements Converter{

	
	public Object getAsObject(FacesContext arg0, UIComponent component , String displaytext) 
	{   
		ArrayList<SelectItem> availableHeaders = null;
		DataTableDisplayProperities dataTableDisplayProp = (DataTableDisplayProperities)component.getAttributes().get("dataTableDisplayProperities");
		if(dataTableDisplayProp==null)     
			{
				return null;	
			}
			availableHeaders = dataTableDisplayProp.getAllHeadersAsSelectItem();
	                        
		for (SelectItem selectitem:availableHeaders )
		{ 
			String headerDisplayName=selectitem.getLabel();   
			if(headerDisplayName.equals(displaytext)  )
				return selectitem.getValue() ;
			
		}
	
		return null;
	}

	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) 
	{
		if (obj==null ) 	return null;
		if(! (obj instanceof Header))
		{
			return null;
		}
		
		Header headerdata = (Header) obj;
		String  displaytext =(String)headerdata.getDisplayText();  
		return displaytext;
			
		
	}
	
	
	
	

}
