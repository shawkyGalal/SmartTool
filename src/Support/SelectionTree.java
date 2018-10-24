package Support;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;

public class SelectionTree extends LookupTreeV10 {

	
	public static final int QUERY_FROM_TMD = 1;  
	public static final int QUERY_FROM_SQL_BOUND_VAR = 2; 
	public static final int QUERY_FROM_LU_QUERY = 3;
	
	private String displayTitle ;  
	private String queryMaintainLink ; 
	private HttpSession session ; 
	private HttpServletRequest request ; 
	public SelectionTree(String treeIdInSession , int m_querySource , Connection m_con , HttpSession m_session , HttpServletRequest m_request  ) throws Exception {
		super(treeIdInSession);
		this.setQuerySource(m_querySource);
		this.con = m_con ;
		this.session = m_session ; 
		this.setRequest( m_request) ; 
	}
	
	public void setRequest(HttpServletRequest m_request )
	{
		this.request = m_request ;
	}
	
	public SelectionTree( String treeIdInSession) throws Exception
	{
		super(treeIdInSession) ; 
	}
	private String treeQuery ;
	
	/** This method Returns the Query Used to build this tree based on different query source*/ 
	public String getTreeQuery(boolean m_refreshAll) throws Exception
	{
		if (treeQuery == null || m_refreshAll)
		{
			if ( this.querySource == SelectionTree.QUERY_FROM_TMD )
		  	{
			  	TableMaintDetails tmd = null; 
			  	String tmdTableOwner = null ; 
			  	String tmdTableName = null ; 
			  	String tmdColumnName = null ; 
				tmdTableOwner = this.treeIdInSession.substring(0 , treeIdInSession.indexOf("/" , 0)) ; 
				tmdTableName  = treeIdInSession.substring(tmdTableOwner.length() +1 , treeIdInSession.indexOf("/" , tmdTableOwner.length()+1)) ;
				tmdColumnName = treeIdInSession.substring((tmdTableOwner+"/"+tmdTableName).length() +1 )  ;
					
				TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tmdTableOwner , tmdTableName ) ;
				tmd =  tmm.getTmdByColumnName(tmdColumnName) ; 
				setDisplayTitle(tmd.getDisplayNameValue()) ; 
					
				treeQuery = tmd.getSelectListQueryValue();
			 	if (treeQuery == null ) { throw new Exception ("No Query Found in the tmd ("+tmdTableOwner+"."+tmdTableName+"."+tmdColumnName+")  SELECT_LIST_QUERY") ; }
			  	
	        	// Check if it is a refrenced query
			 	int nestedQIndex = treeQuery.indexOf("@@") ; 
	        	if ( nestedQIndex > -1)
	        	{
	        		int endOfQueryId = treeQuery.indexOf("[",  nestedQIndex) ;
	        		int endOfQueryIndex = treeQuery.indexOf("]",  nestedQIndex) ;
	        		if (endOfQueryId < 0 || endOfQueryIndex < 0 )
	        		{
	        			throw new Exception("Please Use @@2345[2] format") ; 
	        		}
	        		int queryIndex = Integer.valueOf(treeQuery.substring(endOfQueryId+1 , endOfQueryIndex )) ;
	        		String nestedId = treeQuery.substring(nestedQIndex+2 , endOfQueryId ) ;
	    			Support.SqlReader nestedQueryReader = new Support.SqlReader(con , "LU_QUERIES" , "Query_body" , nestedId , session , request) ;
	    			treeQuery  = nestedQueryReader.getQueryStatments()[queryIndex] ;
	        	}
	        	
	        	queryMaintainLink = "tableEditor.jsp?tableName="+TableMaintDetails.DB_TABLE_OWNER+"."+TableMaintDetails.DB_TABLE_NAME+"&ROWID="+java.net.URLEncoder.encode(tmd.getRowIdString()) ; 
		  	} 
			
			else if (querySource == SelectionTree.QUERY_FROM_SQL_BOUND_VAR)
		  	{
		  		String boundVarName ; 
		  		if (m_refreshAll)
		  		{	boundVarName = this.getSqlBoundVarName(); 
		  		}
		  		else 
		  		{
		  			boundVarName = "$$"+request.getParameter("_boundVarName") ;
		  			this.setSqlBoundVarName(boundVarName); 
		  		}
		  		String sqlQuery = "Select TITLE , QUERY_FOR_LIST , ROWID from support.sql_bound_vars t where upper(BOUND_VAR_NAME) = upper('"+boundVarName+"') "
		  				+" and ( company_id = icdb.security.get_user_company  "
		  				      +" or "
		  				      +" ( system_id =  (Select system_id from ICDB.MAS_COMPANY_DATA mcd where mcd.CMP_ID = ICDB.SECURITY.GET_USER_COMPANY) and  company_id <> icdb.security.get_user_company ) " 
		  				      +")";
		  		java.sql.Statement stmt = con.createStatement() ; 
		  		java.sql.ResultSet rs = stmt.executeQuery(sqlQuery) ; 
		  		String rowIdValue = null ; 
		  		while( rs.next())
		  		{
		  			treeQuery = rs.getString("QUERY_FOR_LIST") ; 
		  			setDisplayTitle(rs.getString("TITLE")); 
		  			rowIdValue = rs.getString("ROWID") ; 
		  		}
		  		try { 	rs.close() ;  stmt.close(); } catch ( Exception e ) {}
		  		queryMaintainLink = "tableEditor.jsp?tableName=SUPPORT.SQL_BOUND_VARS&ROWID="+java.net.URLEncoder.encode(rowIdValue)  ;
		  	}
		  	else if (querySource == SelectionTree.QUERY_FROM_LU_QUERY)
		  	{
		  		int underScoreIndex = treeIdInSession.indexOf("_") ;  // the formate of the treeIdINSession = QueryId_queryIndex
		  		String queryId = treeIdInSession.substring(0, underScoreIndex) ; 
		  		int queryIndex = Integer.parseInt( treeIdInSession.substring(underScoreIndex+1)) ; 
		  		Support.SqlReader queryReader = new Support.SqlReader(con , "LU_QUERIES" , "Query_body" , queryId , session , request) ;
		  		treeQuery  = queryReader.getQueryStatments()[queryIndex] ;
		  		queryMaintainLink = "yyyyyyy"  ;
		  	}
		}
		
		return treeQuery ; 
	}
	
	public void setTreeDataByQuery(boolean m_refreshAll) throws Exception
	{
		this.setTreeDataByQuery(this.getTreeQuery(m_refreshAll));
	}
	public void setQuerySource(int querySource) {
		this.querySource = querySource;
	}
	public int getQuerySource() {
		return querySource;
	}

	public String getDisplayTitle() {
		return displayTitle;
	}

	private void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}
	
	
	public String getQueryMaintainLink() {
		return queryMaintainLink;
	}

	

	private int querySource = 1; 

}
