package Support.Security;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;
import java.security.cert.Certificate;
public class NcciDataSigner 
{
  private String algorithmName = "RSA";
  private Signature signer = null;
  private PrivateKey privateKey = null;
  private PublicKey publicKey = null;
  
public NcciDataSigner(Certificate cert , char[] password ) throws Exception
{
  if (true)
  { throw new Exception("Sorry This Constructor is Not yet Implemented"); }
  this.publicKey = cert.getPublicKey();
}
public NcciDataSigner(PrivateKey m_privateKey ,PublicKey m_publicKey  ) throws Exception
{
  this.privateKey = m_privateKey;
  this.publicKey = m_publicKey;
}
public  NcciDataSigner(String  ksFilePath , String aliasName ,char[] password ) throws Exception
{
    FileInputStream fis = new FileInputStream(ksFilePath);
    KeyStore ks = KeyStore.getInstance("JKS");
    
    ks.load(fis,password);
    fis.close();
    setParms(ks,aliasName , password);
}
  public NcciDataSigner(KeyStore ks , String aliasName ,char[] password) throws Exception
  {
  setParms(ks,aliasName , password);
  }
  private void setParms(KeyStore ks , String aliasName ,char[] password) throws Exception
  {
    if (!ks.containsAlias(aliasName))
    {
      throw new Exception ("certificate Alias Not Found in the Key Store");
    }
    Certificate cer = ks.getCertificate(aliasName);
    publicKey= cer.getPublicKey();
    privateKey = (PrivateKey)ks.getKey(aliasName,password );
    signer = Signature.getInstance("SHA1with"+this.algorithmName.toUpperCase());
  }
    public  byte[]  sign (String data) throws Exception
    {
      byte[] signature ;
      signer.initSign(privateKey);  
      byte[] myData = data.getBytes("UTF-8");
      signer.update(myData);
      signature = signer.sign();
      return signature;
    }
  public  void validateSignature(String data , byte[] signature) throws Exception
  {
  //---------Removing first Zero if Found-----------
    if( signature[0] == 0 )
      {
        byte[] x = new byte[signature.length-1];
        for (int i=1; i< signature.length ; i++)
        {
          x[i-1]= signature[i];
        }
        signature = x;
      }
    //------End of Removing first Zero if Found-----------      
    boolean result= false;
    byte[] myData = data.getBytes("UTF-8");
    signer.initVerify(publicKey);
    signer.update(myData);
    result = signer.verify(signature);
    if (! result)
    {throw new Exception("Shawky : Invalid Signature - Data and the Signature Does Not Match");}
  }
 
 public static void main(String[] args)
 {
   try{
   NcciDataSigner ncciSigner = new NcciDataSigner ("C:\\jdev10g\\jdk\\bin\\NCCI_KS.jks", "NCCI_SERVER", "test2000".toCharArray());
   String data = "This is a test Text ";
   byte[] signaure =  ncciSigner.sign(data);
   ncciSigner.validateSignature(data, signaure);
   }
   catch (Exception e ){ e.printStackTrace(); }
 }
 
}