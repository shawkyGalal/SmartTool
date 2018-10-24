package Support.transactions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.mapping.Array;

public class UserSessionUpdates {

HashMap<String , ArrayList<String>  > userSessionsTransactions = new HashMap<String , ArrayList<String> > ();

private String constructQueryID(int queryId , int querySeq)
{
	return queryId +"_"+ querySeq ; 
}

public void addStatement(int queryId , int querySeq , String updateStatement)
{
	String queryIdSeq = this.constructQueryID(queryId, querySeq) ; 
	if (userSessionsTransactions.get(queryIdSeq) == null)
	{
		userSessionsTransactions.put(queryIdSeq, new ArrayList<String>()) ;
	}
	userSessionsTransactions.get(queryIdSeq).add(updateStatement);
}
public ArrayList<String> getStoredStatments(int queryId , int querySeq)
{
	String queryIdSeq = this.constructQueryID(queryId, querySeq) ;
	return this.userSessionsTransactions.get(queryIdSeq);
}

public void executeUpdates(int queryId, int querySeq , java.sql.Connection con) throws SQLException 
{
 ArrayList<String> ss = this.getStoredStatments(queryId, querySeq) ;
 	
	 java.sql.Statement stmt = con.createStatement(); 
	 for (String stmtStr : ss)
	 {
		 try {
		 stmt.execute(stmtStr);
		 }
		 catch (SQLException sqle)
		 {
		}
	 }
 
}
}