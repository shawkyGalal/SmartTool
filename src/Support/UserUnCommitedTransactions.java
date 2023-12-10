package Support;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.smartValue.UnCommittedDbTransaction;
import com.smartValue.tableControllers.ItableTriggerController;

public class UserUnCommitedTransactions {
	
  public static final  String dbOperationsNameInSession = "unCommitedTransactions" ;

  public static void rollBackConnection (Connection m_con , HttpSession m_session) throws Exception
  {
	  m_con.rollback() ;
	  // Clear stored operations ( UnCommited Transactions )  
	  m_session.setAttribute(dbOperationsNameInSession , new ArrayList<UnCommittedDbTransaction>()); 
  
  }

  // Returns All the uncommited transaction from user session    
	  @SuppressWarnings("unchecked")
  public static ArrayList<UnCommittedDbTransaction> getUnCommitedTransactions(HttpSession m_session)
  {
	 ArrayList<UnCommittedDbTransaction> result = ( ArrayList<UnCommittedDbTransaction> ) m_session.getAttribute(dbOperationsNameInSession) ;
	 if (result == null )
	 {
	  result = new ArrayList<UnCommittedDbTransaction>(); 
	 }
	 return result ; 
  }

	  // Commits the uncommited transactions after calling the UponUpdateCommit and UponInsertCommit actions of the ItableTriggerController  associated with the Table   
   @SuppressWarnings("unchecked")
   public static void commitConnection (Connection m_con , HttpSession m_session) throws Exception
   {
	   SecUsrDta loggedUser = (SecUsrDta)m_session.getAttribute("loggedUser") ; 
	  ArrayList<UnCommittedDbTransaction> dbOperations = getUnCommitedTransactions(m_session) ; 
	  //if (dbOperations.isEmpty()){return ;}
	  
	  for (UnCommittedDbTransaction dbOper : dbOperations)
	  {
	  	  boolean updateOperation = dbOper.getOperationType() == UnCommittedDbTransaction.UPDATE_OPERATION ;  
	  	  boolean insertOperation = dbOper.getOperationType() == UnCommittedDbTransaction.INSERT_OPERATION ; 
		  TableMaintMaster tmm =  updateOperation ? (TableMaintMaster)dbOper.getTableMaintdetail().getParent().getPersistantObject() : dbOper.getTableMaintMaster() ; 
		  TableMaintDetails tmd = dbOper.getTableMaintdetail() ; 
				  
		  String rowId = dbOper.getRowId() ; 
		  Object oldValue = dbOper.getOldValue() ; 
		  Object newValue = dbOper.getNewValue() ;
		  
		  ItableTriggerController tableTriggers = null; 
		  try { tableTriggers = tmm.getTableTriggers() ; } catch ( Exception e ){}  
		  if (tableTriggers != null )
		  {
			  if (updateOperation)
			  {
				  tableTriggers.upponUpdateCommit(m_con , loggedUser, rowId , tmd.getColumnNameValue() , oldValue , newValue) ;  
			  }
			  else if (insertOperation)
			  {
				  tableTriggers.upponInserCommit(m_con , loggedUser, rowId ) ; 
			  }
		  }
	  }
	  // Clear stored operations 
	  m_session.setAttribute(dbOperationsNameInSession , new ArrayList<UnCommittedDbTransaction>()); 
	  try{	  m_con.commit() ;} catch (Exception e){} 
  }


}
