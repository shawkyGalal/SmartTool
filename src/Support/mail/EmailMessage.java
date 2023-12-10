package Support.mail;

public class EmailMessage {
	
	private String[] to;
	private String from;
	private String[] cc = null;
	private String body;
	private String subject;
	private String[] attFileNames = {};
	private String[] bcc;
  public EmailMessage()
  {
    
  }
   public EmailMessage(String m_mailFrom,String[] m_mailRecepiants,  String[] m_ccRecepiants,String m_subject, String m_mailBody)
  {
    this.from = m_mailFrom;
    this.to = m_mailRecepiants;
    this.cc = m_ccRecepiants;
    this.subject = m_subject;
    this.body = m_mailBody;
  }
  
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	
	public void setBcc(String[] m_bcc) {
		this.bcc = m_bcc;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getAttFileNames() {
		return attFileNames;
	}
	public void setAttFileNames(String[] attFileNames) {
		this.attFileNames = attFileNames;
	}
	public String[] getBcc() {
		// TODO Auto-generated method stub
		return this.bcc;
	}

}
