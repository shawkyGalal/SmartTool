package SADAD.SAMA_SIMULATOR;
import SADAD.DATASTRCTURE.*;
import Support.HttpPoster;

public class BillConfSender 
{
  public BillConfSender() throws Exception
  {
  HttpPoster http = new HttpPoster("http://localhost:8988/SADAD/servlet/SADAD.BillConfReciver","", "userName","pass");
  
  SADADService ss = new  SADADService("file:\\\\localhost\\Mywork\\BCONRQ.xml",null,true);
  String billerReconAsString = ss.toXMLString();
  http.postRequest(billerReconAsString);
  String response = http.readResponse();
  System.out.print(response);
  }

  public static void main(String[] args) throws Exception
  {
    BillConfSender billConfSender = new BillConfSender();
  }
}