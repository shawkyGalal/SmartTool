package Support;

public class SmsMessage {
	
	private String toMobile ;
	private String fromAddress ;
	private String message;
	
	public  SmsMessage(String m_message , String m_toMobile , String m_fromAddress)
	{
		this.message = m_message ; this.toMobile = m_toMobile ; this.fromAddress = m_fromAddress ;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	} 

}
