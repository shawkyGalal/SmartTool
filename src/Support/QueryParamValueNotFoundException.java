package Support;

public class QueryParamValueNotFoundException extends Exception 
{
	private String missingParam = null ; 
	public QueryParamValueNotFoundException (String m_message , String m_missingPram)
	{
		super(m_message) ; 
		setMissingParam(m_missingPram);  
	}
	public void setMissingParam(String missingParam) {
		this.missingParam = missingParam;
	}
	public String getMissingParam() {
		return missingParam;
	}

}
