package com.smartValue.database.map.validators;



import java.util.List;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysMnu;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.validators.auto._SysMnuChangeValidator;


public class SysMnuChangeValidator extends _SysMnuChangeValidator
{

	public SysMnuChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{
		super(pm_secUserData, pm_po, pm_key);
	}

	public ValidationResult validateMnuCode(Object newValue)
	{
		ValidationResult result = new ValidationResult();
		try
		{
			 ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
			 List<PersistantObject> list = msc.getDbServices().queryForList(
			       "select t.mnu_code from sys_mnu t where t.mnu_code = '"+newValue+"'", SysMnu.class);
			 if (list.size() > 0) {
			 SysMsg sysMsg = msc.getSysMsgServices().getSysMsg(1003, 1 , false);
			 result.setInvalidMessage(sysMsg.getMsgDescValue());
			 result.setValidResult(false);
			 }
			    
		} catch (Exception e)
		{
			result.setValidResult(false);
			result.setInvalidMessage("Unbale To Validate MnuCode Due To "+ e.getMessage());
		}
		
		return result;
	}
	
	public ValidationResult  validateMnuSql(Object newValue)
	{
		SysMnu sysMnu = (SysMnu)this.po;
		ValidationResult result = new ValidationResult();
		if (sysMnu.getMnuTypeValue().intValue()==SysMnu.Menu_SQL_TYPE  &&  (newValue == null || newValue.equals("") ))
		{
			result.setInvalidMessage("You Have Selected MnuType to be SQL Execution ....Please enter a valid query...");
			result.setValidResult(false);
			
		}
		return result;
		
		
	}

	@Override
	public ValidationResult validateMnuType(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDateCreated(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuArg1(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuArg2(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuArg3(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuArg4(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuArgTyp(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuCallId(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuDesc(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuDesc_(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontBold(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontColorB(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontColorG(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontColorR(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontName(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuFontSize(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuImg(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuParent(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuPrgAlias(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuPrgName(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuShortcut(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuStatus(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuStyl(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuTxt(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuTxt_(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateModifiedDt(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateServiceClass(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuRpt(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMnuUrl(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateVpdatabasetable(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
}
