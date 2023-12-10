package com.implex.database.map.validators;
import java.math.BigDecimal;

import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._SysMsgChangeValidator;

public class SysMsgChangeValidator extends _SysMsgChangeValidator 
{ 
	 public SysMsgChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}

	@Override
	public ValidationResult validateModifiedDt(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgDesc(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgId(Object newValue)
	{
		
		ValidationResult vr = new ValidationResult();
		if (newValue == null)
		{
			vr.setValidResult(false);
			vr.setInvalidMessage("Should Not Be Null");
			return vr;
		}
		int x = 0;
		if (newValue instanceof String)
		{
			x= new BigDecimal((String)newValue).intValue();
		}
		else {x = ((BigDecimal)newValue).intValue();}
		if (x > 2000) 
		{
			vr.setValidResult(false);
			vr.setInvalidMessage("IT SHOULD Be Less Than 2000");
		}
		return vr;

	}

	@Override
	public ValidationResult validateMsgImgId(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgLngId(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgTitle(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgTooltip(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMsgType(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	} 
} 