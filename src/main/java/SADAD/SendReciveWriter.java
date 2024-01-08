package SADAD;
import SADAD.DATASTRCTURE.*;
import java.sql.*;
import java.text.SimpleDateFormat;



public class SendReciveWriter 
{
  private Connection con ;
  final static String tableName = "send_rec_messages";
  public SendReciveWriter(Connection m_con)
  {
   this.con =m_con;
  }
  public void storeRequestResponse(SADADService msg_recived , SADADService msg_sent , long responseTime , java.util.Date startTime) throws SADADException
  {
    
    try{
    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String startTimeString = sdf.format(startTime);

    String msg_recivedStr =msg_recived.toXMLString();
    String msg_sentStr =msg_sent.toXMLString();
    
    String  msg_recivedCode= "";
    String  msg_sentCode= "";
    String sent_msg_RUID = "";
    String rcvd_msg_RUID = "";
    if (msg_recived.signonRq != null)
    { msg_recivedCode = msg_recived.signonRq.getSignonProfile().getMsgCode(); 
    }
    else {msg_recivedCode = msg_recived.signonRs.signonProfile.getMsgCode(); }
    
    
    if (msg_sent.signonRq != null)
    { msg_sentCode = msg_sent.signonRq.getSignonProfile().getMsgCode(); }
    else {msg_sentCode = msg_sent.signonRs.signonProfile.getMsgCode(); }

      //--------Getting RUID of the msg_recived
      rcvd_msg_RUID = msg_recived.getRUID();
     
      //--------Getting RUID of the msg_sent
      sent_msg_RUID = msg_sent.getRUID();
    
 
    
    String insertStmt = " insert into "+tableName+ " (recvd_msg_Code, sent_msg_Code ,  msg_recived , msg_sent , response_time , startTime , recvd_msg_RUID , sent_msg_RUID ) values  "
             +" ('"+msg_recivedCode+"' , '"+ msg_sentCode +"', ? , ? ,'"+responseTime+"',to_date('"+startTimeString+"','dd-mm-yyyy hh24:mi:ss') , '"+rcvd_msg_RUID+"' ,'"+sent_msg_RUID+"' )";
             
    oracle.jdbc.OraclePreparedStatement pstmt = (oracle.jdbc.OraclePreparedStatement)con.prepareStatement(insertStmt);

    
    oracle.sql.CLOB  myClob = oracle.sql.CLOB.createTemporary(con,false,oracle.sql.CLOB.DURATION_SESSION);
    java.io.Writer writer = myClob.getCharacterOutputStream();   
    writer.write(msg_recivedStr,0,msg_recivedStr.length());
    writer.flush();
    writer.close();
    pstmt.setCLOB(1,myClob);
    
    myClob = oracle.sql.CLOB.createTemporary(con,false,oracle.sql.CLOB.DURATION_SESSION);
    writer = myClob.getCharacterOutputStream();   
    writer.write(msg_sentStr,0,msg_sentStr.length());
    writer.flush();
    writer.close();
    pstmt.setCLOB(2,myClob);
    
    pstmt.execute();
    
  //  con.commit();
    pstmt.close();
    } catch (Exception e) {throw new SADADException ("Unable to store message Sent and Recived in DB, Due to The following Exception"+e.getMessage() ,e,1);};
  }

  public static void main(String[] args) throws Exception
  {  
    SendReciveWriter clobWriter = new SendReciveWriter(null);
    clobWriter.storeRequestResponse (null , null ,100 , new java.util.Date());
  }
}