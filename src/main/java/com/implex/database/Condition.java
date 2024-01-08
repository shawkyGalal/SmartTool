package com.implex.database;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.implex.database.map.DateExpression;

public class Condition {
	
	
	private Attribute attribute =new Attribute(null, null, null);
	private String operation = Operation.EQUAL;
	
	private BigDecimal dateExpressionId ;
	private boolean disabled = false ;
	public void setDateExpressionId(BigDecimal dateExpressionId) {
		this.dateExpressionId = dateExpressionId;
		
	}

	public BigDecimal getDateExpressionId() {
		return dateExpressionId;
	}
	

	public Attribute getAttribute() {
		if (this.dateExpressionId != null)
		{
			try{
				DateExpression de = new DateExpression();
				de.setId(dateExpressionId);
				this.setAttribute(de.getDateValue());	
			}
			catch (Exception e) {
				
			}
		}
		return attribute;
	}

	public void setAttribute(Attribute attributeValue) {
		this.attribute = attributeValue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String op) {
		this.operation = op;
	}
	

	
	public Condition( String pm_oper, Attribute pm_attValue) {
		
		this.operation = pm_oper;
		this.attribute = pm_attValue;
	}

	public String toString()
	{
		Object attributeValueObject =  this.attribute.getValue();
		boolean isDate = attributeValueObject instanceof java.util.Date ; 
		boolean isNumber = attributeValueObject instanceof java.math.BigDecimal ;
		String result = ""; 
		String attributeValueWithoutQuta = this.attribute.getAttributeSQLValue(true , false);
		attributeValueWithoutQuta = attributeValueWithoutQuta.replace("null", "");
		String attributeValueWithQuat =  "'" + attributeValueWithoutQuta +  "'" ; 
		String value = (isDate || isNumber)? attributeValueWithoutQuta : attributeValueWithQuat ; 
		if (this.operation.equals(Operation.EQUAL)
			|| 	this.operation.equals( Operation.NOT_EQUAL)
			||	this.operation.equals(Operation.GREATER)
			|| 	this.operation.equals( Operation.GREATER_OR_EQUAL)
			|| 	this.operation.equals( Operation.LESS)
			|| 	this.operation.equals( Operation.LESS_OR_EQUAL)
			
			)
		{
			result = this.ignoreCaseValue && ! isNumber ? "lower(" + this.attribute.getKey() + ") " + this.operation + " lower(" + value + ")" 
													 : this.attribute.getKey() + " " + this.operation  + " " + value ;
		}
		
		else if (this.operation.equals(Operation.CONTAINS))
		{
			result =  this.ignoreCaseValue ? "lower(" + this.attribute.getKey() + ")" + " Like "  + "lower(" + "'%" + attributeValueWithoutQuta + "%'" + ")" 
														: this.attribute.getKey() + " " + " Like "   + "'%" + attributeValueWithoutQuta + "%'";
		}
		
		else if (this.operation.equals(Operation.IN))
		{
			
			if (attributeValueWithoutQuta.toString().contains(","))
			{
				String[] attrValue = attributeValueWithoutQuta.toString().split(",");
				if(this.ignoreCaseValue)
				{
					result =  "lower(" + this.attribute.getKey() + ")" + " " + Operation.IN + "( ";
					for(int i = 0; i < attrValue.length; i++ )
					{
						result += "lower('" + attrValue[i] + "')" + ",";
					}
				}
				else{
					result = attributeValueWithoutQuta + " " + Operation.IN + "( ";
					for(int i = 0; i < attrValue.length; i++ )
					{
						result +=  "'" + attrValue[i] + "'" + ",";
					}
				}
				 result = result.substring(0, result.length() -1);
				 result += " )";
			}
			else{
				String lowerValue = this.ignoreCaseValue ? "lower" : "";
				result = lowerValue + "(" +  this.attribute.getKey() + ")" + " " + Operation.IN + " " + lowerValue +"( " + "'" + attributeValueWithoutQuta + "'" +")";
			}
		}
	
		return  " " + result + " ";
		
	}
	
	public boolean isActive()
	{
		return ( attribute != null);
	}
	
	private String getSqlStringValue()
	{
		String result = "";
		Attribute attr = this.getAttribute() ;
		if ( attr == null || attr.getValue() == null)
		{
			return result ;
		}
		Object o = this.getAttribute().getValue();
		if (o instanceof java.lang.String)
		{
			return "'"+o+"'";
		}
		else if (o instanceof java.util.Date )
		{
			Date date = (Date)o;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			result = "to_Date( '"+sdf.format(date)+"', 'dd-mm-yyyy hh24:mi:ss')";
		}
		
		if (o != null)
		{
			 if(  o.equals(true))
				result = "1"; //TODO : Sakr is it better be "Y" ?? 
			 if( o.equals(false))
				result = "0";
			else
			{
				result = o.toString();
			}
		}
		return result;
		
		
	}

	private boolean ignoreCaseValue = true;
	public void setIgnoreCaseValue(boolean ignoreCaseValue) {
		this.ignoreCaseValue = ignoreCaseValue;
	}
	public boolean isIgnoreCaseValue() {
		return this.ignoreCaseValue;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return disabled;
	}
}
