package com.smartValue.database;

import java.io.Serializable;

import org.richfaces.model.Ordering;

public class Header  implements Serializable{
	private String columnName ;
	private String displayText ;
	private Ordering sortOrder;
	
	@Deprecated 
	public Header() {
		//this to avoid  InstantiationException  in using the listShuttle
	}
	public Header(String pm_columnName , String pm_displayText)
	{
		this.columnName = pm_columnName;
		this.displayText = pm_displayText ; 
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setSortOrder(Ordering sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Ordering getSortOrder() {
		return sortOrder;
	}
	  public boolean equals(Object obj) { 
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			final Header header = (Header) obj;
			if (columnName == null) 
			{
				if (header.columnName != null)
					return false;
				
			} 
			
			else if(!columnName.equals(header.columnName))
				return false;
			if (displayText == null) 
			{
				if (header.displayText != null)
					return false;
			}
			
			else if (!displayText.equals(header.displayText))
				return false;
			
			
	return true;
			
			 
	  }
	  public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((displayText  == null)  ? 0  : displayText.hashCode());
			
			
			return result;
		}
}
