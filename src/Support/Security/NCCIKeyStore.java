package Support.Security;
import java.security.*;
import java.io.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;
public class NCCIKeyStore 
{
  KeyStore ks;
  public NCCIKeyStore(InputStream is , char[] pass ) throws Exception
  {
    ks = KeyStore.getInstance("JKS");
    ks.load(is,pass);
  }
  
  public void writeToJKSFile( String  filePath , char[] pass ) throws Exception    //"C:\\jdev10g\\jdk\\bin\\cacerts.jks"
  {
    FileOutputStream fos = new FileOutputStream(filePath);
    this.ks.store(fos,pass);
    fos.close();
    
  } 
  public void addCertificate(X509Certificate cert2BeAdded , String alias) throws Exception
  {
    ks.setCertificateEntry(alias,cert2BeAdded);
  }
  public void addX509Certificate(String certFilePath , String alias) throws Exception
  {
    CertificateFactory cf =  CertificateFactory.getInstance("X.509");
    FileInputStream fis = new FileInputStream(certFilePath);    //"C:\\jdev10g\\jdk\\bin\\alrajhi.cer"
    X509Certificate cert2BeAdded =  (X509Certificate)cf.generateCertificate(fis);
    ks.setCertificateEntry(alias,cert2BeAdded);
  }
  public static void generateEmptyKSFile(String filePath , char[] password) throws Exception
  {
    //----------Read the template one ---
    char[] emptyPass = "abc".toCharArray();
    NCCIKeyStore temp = new NCCIKeyStore(new FileInputStream("C:\\jdev10g\\jdk\\bin\\empty.jks"),emptyPass);
    temp.clearContents();
    temp.writeToJKSFile(filePath , password);
    //----------
  }
  public void clearContents() throws Exception
  {
    Enumeration aliases=  this.ks.aliases();
    String name = "";
   
    while (aliases.hasMoreElements())
    {
      name = aliases.nextElement().toString();
      ks.deleteEntry(name);
    }
  }
  public static void main(String[] args) throws Exception
  {
    try{
    
    //char[] newPass = "newPass".toCharArray();
    //NCCIKeyStore.generateEmptyKSFile("C:\\jdev10g\\jdk\\bin\\newEmpty.jks" , newPass);
    
    FileInputStream x = new FileInputStream("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMATrustedCAs.jks");
    
    char[] pass = new String("changeit").toCharArray(); //new String("changeit").toCharArray();
    NCCIKeyStore ncciKeyStore = new NCCIKeyStore(x,pass );
    ncciKeyStore.clearContents();
    // ncciKeyStore.addX509Certificate("C:\\jdev10g\\jdk\\bin\\SADAD\\unit-test.sadad.com.sa_Consolidated.crt" ,"unit-test.sadad.com.sa_Consolidated" );
    // ncciKeyStore.addX509Certificate("C:\\temp\\NCCI_Int_Test.cer" ,"NCCI9IASP" );
    //ncciKeyStore.addX509Certificate("C:\\temp\\SAMA Staging Shared CA.cer" ,"SAMA Staging Shared CA" );
    //ncciKeyStore.addX509Certificate("C:\\jdev10g\\jdk\\bin\\SADAD\\integration-test.sadad.com.sa-Shared.crt" ,"integration-test.sadad.com.sa" );
    ncciKeyStore.addX509Certificate("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMA Staging Shared CA.crt" ,"SAMA Staging Shared CA" ); 
    ncciKeyStore.addX509Certificate("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMA Test Root CA.crt" ,"SAMA_Test_Root" );
    ncciKeyStore.addX509Certificate("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMA Staging Infrastructure CA.crt" ,"SAMA_Staging_Infrastructure" );
    
    ncciKeyStore.writeToJKSFile("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMATrustedCAs.jks",pass);
    
    Enumeration aliases=  ncciKeyStore.ks.aliases();
    String type = ncciKeyStore.ks.getType();
    String name = "";
    boolean isKeyEntry = false;
    boolean isCertEntry = false;
    //-----------adding a new trusted certificate to the keystore----
    // ncciKeyStore.addCertificate("C:\\jdev10g\\jdk\\bin\\alrajhi.cer","tabadul.alrajhibank.com" );
    
    // ncciKeyStore.writeToJKSFile("C:\\jdev10g\\jdk\\bin\\cacertsXXXX.jks",pass);
   
    while (aliases.hasMoreElements())
    {
      name = aliases.nextElement().toString();
      //ncciKeyStore.ks.deleteEntry(name);
      isKeyEntry= ncciKeyStore.ks.isKeyEntry(name);
      isCertEntry= ncciKeyStore.ks.isCertificateEntry(name);
      PrivateKey key = (PrivateKey)ncciKeyStore.ks.getKey(name,pass);
      Certificate cert = ncciKeyStore.ks.getCertificate(name);
      String certType = cert.getType();
      cert.getPublicKey();
    }
    //char[] emptyPass = "abc".toCharArray();
    
    }
    catch (Exception e){e.printStackTrace(); throw e; }
  }
}