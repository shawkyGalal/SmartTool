package SADAD.SAMA_SIMULATOR;
import SADAD.DATASTRCTURE.*;
import Support.HttpPoster;

public class BillerReconSender 
{
  public BillerReconSender() throws Exception
  {
  
  HttpPoster http = new HttpPoster("http://localhost:8988/SADAD/servlet/SADAD.BillerReconReciver", "","userName","pass");
  
  SADADService ss = new  SADADService("file:\\\\localhost\\Mywork\\BillerRecon.xml",null,true);
  String billerReconAsString = ss.toXMLString();
  http.postRequest(billerReconAsString);
  String response = http.readResponse();
  System.out.print(response);
  }

  public static void main(String[] args) throws Exception
  {
    BillerReconSender billerReconSender = new BillerReconSender();
  }
}