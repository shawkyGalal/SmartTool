package SADAD;
import java.security.cert.*;

public class NcciCertificate 
{
  X509Certificate cert;
  public NcciCertificate() throws Exception 
  {
   CertificateFactory certFac =  CertificateFactory.getInstance("X.509");
  }

  public static void main(String[] args)
  {
    try{
    NcciCertificate ncciCertificate = new NcciCertificate();
    }
    catch (Exception e){}
  }
}