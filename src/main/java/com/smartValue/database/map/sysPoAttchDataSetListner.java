package com.smartValue.database.map;

import com.smartValue.database.DataSet;
import com.smartValue.database.DatasetEventListener;
import com.smartValue.database.PersistantObject;

public class sysPoAttchDataSetListner implements DatasetEventListener {

	
	public void afterAddNew(DataSet ds) {
		try {
		DataSet  sysPoAttachGroupDs =  ds.getParent().getPersistantObject().getParentDataSet();
		PersistantObject ownerPO =  (PersistantObject) sysPoAttachGroupDs.getProperties().get(SysPoAttachmentGroup.ATTACHMENT_OWNER_KEY) ;
		SysPoAttachments  sysPoAttachments = (SysPoAttachments) ds.getCurrentItem() ;
		sysPoAttachments.setPoKeyValue(ownerPO.getDisplayTxt());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	
	public void afterMarkObjectToBeDeleted(PersistantObject pmPo) {
		// TODO Auto-generated method stub

	}

	
	public void afterRefresh(DataSet ds) {
		// TODO Auto-generated method stub

	}

	
	public void afterSaveAll(DataSet ds) {
		// TODO Auto-generated method stub

	}

	
	public void beforeAddNew(DataSet ds) throws Exception {
		// TODO Auto-generated method stub

	}

	
	public void beforeMarkObjectToBeDeleted(PersistantObject pmPo) {
		// TODO Auto-generated method stub

	}

	
	public void beforeRefresh(DataSet ds) throws Exception {
		// TODO Auto-generated method stub

	}

	
	public void beforeSaveAll(DataSet ds) throws Exception {
		// TODO Auto-generated method stub

	}

}
