package com.smartValue.tableControllers;

import java.sql.Connection;

import com.smartValue.database.map.SecUserGroups;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.services.ModuleServicesContainer;

import Support.Misc;

/** This Class Is Used To Reflect the actual updates in DB to the an equivalent update in the persistent layer if applicable 
 * for Example : Direct update in DB in the loggedUser information should be reflected in the corresponding loggedUser persistent object
 * Also It Includes any action needed to be taken after committing the update and insert transactions 
 * */
public class secUserDtaTableTrigger extends defaultTableTrigger {
	
	@Override
	public void afterUpdate(Connection mCon, String mRowId, String mColumnName, Object m_newValue)		throws Exception 
	{
		super.afterUpdate( mCon,  mRowId,  mColumnName,  m_newValue);
		SecUsrDta loggedUser =   this.getTableMaintMaster().getDbService().getLoggedUser();

		if (loggedUser.getRowIdString().equalsIgnoreCase(mRowId)) // if Updating My imfo 
		{
			loggedUser.setAttributeValue(mColumnName, m_newValue);
		}
		System.out.print(" This Call from secUser"); 
	
	}
	
	public void afterInsert(Connection mCon, String mRowId) throws Exception
	{
		super.afterInsert(mCon, mRowId);
		TableMaintMaster tmm =  this.getTableMaintMaster() ; 
		ModuleServicesContainer msc = tmm.getModuleServiceContainer() ;
		SecUsrDta userUpdated = (SecUsrDta) Misc.getPersObjectByRowId(msc, tmm.getOwnerValue(), tmm.getTableNameValue(), mRowId , SecUsrDta.class) ;
		if (userUpdated.getUsrEmail()!= null)
		{
			try {userUpdated.sendResetPasswordEmail();}
			catch ( Exception e ) {
				msc.getMessageCommnunicatorService().sendMessageToUser("Unable to Send Email To User With His Account Details Due To " + e.getMessage() + " Please try again Later");
				e.printStackTrace();}
		}
	}
	
	@Override
	public  void upponUpdateCommit(Connection m_con , SecUsrDta m_logged_user , String m_rowId , String m_columnName , Object m_oldValue , Object m_newValue) throws Exception 
	{
		
	}
	@Override
	public void upponInserCommit(Connection mCon , SecUsrDta m_logged_user , String mRowId) throws Exception 
	{
		
	}
	

}
