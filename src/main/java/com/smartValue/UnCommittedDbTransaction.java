package com.smartValue;

import java.util.Date;

import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.TableMaintMaster;

public class UnCommittedDbTransaction {
private TableMaintMaster tableMaintMaster ; 
private TableMaintDetails tableMaintdetail ; 
private Object oldValue ; 
private Object newValue ;
private Date  operateDate ; 
private String rowId ; 
public static final int UPDATE_OPERATION = 0 ; 
public static final int INSERT_OPERATION = 1 ; 
public static final int DELETE_OPERATION = 2 ;
public static final int EXECUTE_OPERATION = 3 ; // Not Yet Used ...
private  int operationType = UPDATE_OPERATION;

public UnCommittedDbTransaction (TableMaintDetails m_tableMaintdetail , String m_rowid, Object m_oldValue , Object m_newValue )
{
	this.setTableMaintdetail(m_tableMaintdetail) ;
	this.setRowId(m_rowid) ; 
	this.setOldValue(m_oldValue) ; 
	this.setNewValue(m_newValue) ; 
	this.setOperationType(UPDATE_OPERATION); 
	this.operateDate = new Date(); 
}

public UnCommittedDbTransaction (TableMaintMaster m_tableMaintMaster , String m_rowid , int m_dbOperationType)
{
	this.setTableMaintMaster(m_tableMaintMaster) ; 
	this.setRowId(m_rowid) ; 
	this.operateDate = new Date();
	this.setOperationType(m_dbOperationType); 
}



public void setTableMaintMaster(TableMaintMaster tableMaintMaster) {
	this.tableMaintMaster = tableMaintMaster;
}

public TableMaintMaster getTableMaintMaster() {
	return tableMaintMaster;
}

public void setTableMaintdetail(TableMaintDetails tableMaintdetail) {
	this.tableMaintdetail = tableMaintdetail;
}

public TableMaintDetails getTableMaintdetail() {
	return tableMaintdetail;
}

public void setOldValue(Object oldValue) {
	this.oldValue = oldValue;
}

public Object getOldValue() {
	return oldValue;
}

public void setNewValue(Object newValue) {
	this.newValue = newValue;
}

public Object getNewValue() {
	return newValue;
}

public void setOperateDate(Date operateDate) {
	this.operateDate = operateDate;
}

public Date getOperateDate() {
	return operateDate;
}

public void setRowId(String rowId) {
	this.rowId = rowId;
}

public String getRowId() {
	return rowId;
}

public void setOperationType(int operationType) {
	this.operationType = operationType;
}

public int getOperationType() {
	return operationType;
}

public boolean equals (Object m_oper)
{
	boolean m_result = false ; 
	if (m_oper instanceof UnCommittedDbTransaction)
	{
		UnCommittedDbTransaction xxx = (UnCommittedDbTransaction) m_oper ; 
		if ( this.getOperationType()== UnCommittedDbTransaction.UPDATE_OPERATION && xxx.getOperationType() == UnCommittedDbTransaction.UPDATE_OPERATION)
		{
			m_result = this.getTableMaintdetail().getOwnerValue().equals( xxx.getTableMaintdetail().getOwnerValue() )
						&& this.getTableMaintdetail().getTableNameValue() .equals( xxx.getTableMaintdetail().getTableNameValue() )
						&& this.getTableMaintdetail().getColumnNameValue() .equals( xxx.getTableMaintdetail().getColumnNameValue() ) 
						&& this.getRowId() .equals( xxx.getRowId() ) ;
		}
		else if (this.getOperationType()== UnCommittedDbTransaction.INSERT_OPERATION && xxx.getOperationType() == UnCommittedDbTransaction.INSERT_OPERATION  )
		{
			m_result = false ;  
				//this.getTableMaintMaster().getOwnerValue() == m_oper.getTableMaintMaster().getOwnerValue()
			    //     && this.getRowId() == m_oper.getRowId() ;
		}
	}
	else m_result = false ; 
		
	
	return m_result ;  
}
}
