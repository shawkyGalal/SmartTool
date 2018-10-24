package com.smartValue.tableControllers;

import java.sql.Connection;
import java.sql.Statement;

import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUserGroups;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.services.ModuleServicesContainer;

import Support.Misc;

/** This Class Is Used To Reflect the actual updates in DB to the an equivalent update in the persistent layer if applicable 
 * for Example : Direct update in DB in the loggedUser information should be reflected in the corresponding loggedUser persistent object
 * Also It Includes any action needed to be taken after committing the update and insert transactions 
 * */
public class secUserGroupsTableTrigger extends defaultTableTrigger {
	
	@Override
	public void afterUpdate(Connection mCon, String mRowId, String mColumnName, Object m_newValue)	throws Exception 
	{
		super.afterUpdate( mCon,  mRowId,  mColumnName,  m_newValue);
		TableMaintMaster tmm =  this.getTableMaintMaster() ; 
		ModuleServicesContainer msc = tmm.getModuleServiceContainer() ;
		
		SecUserGroups secGroup = (SecUserGroups) Misc.getPersObjectByRowId(msc, tmm.getOwnerValue(), tmm.getTableNameValue(), mRowId , SecUserGroups.class) ;
		SecUsrDta userUpdated =  msc.getSecUserDataService().getUserByUserName(secGroup.getUserNameValue()) ; 
		SecUsrDta loggedUser =   tmm.getDbService().getLoggedUser();

		// this boolean check if i'm updating info that has a persistent image in java.
		// if true then the this persistent image should updated also 
		boolean updatingLoggedUser = userUpdated.getAttributeValue("ROWID").equals(loggedUser.getAttributeValue("ROWID")) ;  
				  
		if (updatingLoggedUser)
		{
			userUpdated = loggedUser ;
			DataSet userUpdatedGroup = loggedUser.getUserGroupsDS();
			secGroup = (SecUserGroups) userUpdatedGroup.getFirstFilteredPO("ROWID", mRowId) ; 
			
			// Apply the db change to the persistent layer
			secGroup.setAttributeValue(mColumnName, m_newValue);
			
			// By the Way, Implement Business Logic - In Case of setting as the USER_DEFAULT_ROLE , rest others role if exist  
			if (mColumnName.equals(SecUserGroups.USER_DEFAULT_ROLE) && m_newValue!= null && m_newValue.toString().equalsIgnoreCase("Y") )
			{	
				for ( PersistantObject po : userUpdatedGroup.getPersistantObjectList())
				{
					SecUserGroups sug = (SecUserGroups) po ;  
					if ( sug.getUserDefaultRole().getBooleanValue()
						&& ! sug.getAttributeValue("ROWID").toString().equalsIgnoreCase(mRowId) )
					{
						String qs = "Update icdb."+ SecUserGroups.DB_TABLE_NAME + " Set " + SecUserGroups.USER_DEFAULT_ROLE +" = 'N' "
							+" Where ROWID "  + " = '" + po.getAttributeValue("ROWID") + "'"
							+ "and ROWID <> '"+mRowId+"' " ;
						Statement stmt = null; 
						try{
							stmt = mCon.createStatement() ;
							stmt.execute(qs) ; 
						}
						catch(Exception e ) { if (stmt != null) stmt.close(); }
					}
				}
			}	
		
		}
	}

}
