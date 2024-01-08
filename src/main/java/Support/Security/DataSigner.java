package Support.Security;

import java.security.*;
import java.security.spec.*;
import java.math.*;
public class DataSigner 
{
 
  DSAKeyPairs dsaKeyPairs;
  RSAKeyPairs rsaKeyPairs;
  public static final String RSA_ALGORITHM_NAME = "RSA";
  public static final String DSA_ALGORITHM_NAME = "DSA";
  String algorithmName= "";


 
  
  public DataSigner(boolean withNewKeyPairs ,String m_algorithmName) throws Exception
  {
    if (m_algorithmName.toUpperCase().equals(RSA_ALGORITHM_NAME))
    {
    rsaKeyPairs = new RSAKeyPairs(withNewKeyPairs);
    }
    else if (m_algorithmName.toUpperCase().equals(DSA_ALGORITHM_NAME))
    {
    dsaKeyPairs = new DSAKeyPairs(withNewKeyPairs);
    }
    else { throw new Exception ("Shawky : Invalid Algorithm Name");}   
    algorithmName = m_algorithmName;
  }
  public  byte[]  sign (String data, char[] password) throws Exception
  {
    validatePassword(password);
    byte[] signature={};
    try{
    byte[] myData = data.getBytes("UTF-8");
    PrivateKey pk = null;
    if (this.algorithmName.equals(DSA_ALGORITHM_NAME ))
    { pk = dsaKeyPairs.getPrivateKey();}
    else if (this.algorithmName.equals(RSA_ALGORITHM_NAME ))
    { pk = rsaKeyPairs.getPrivateKey();}
    
    Signature sig = Signature.getInstance("SHA1with"+this.algorithmName.toUpperCase());
    sig.initSign(pk);
    sig.update(myData);
    signature = sig.sign();
     }
    catch (Exception e){
    e.printStackTrace();
    throw e;
    }
    return signature;
  }
  private void validatePassword(char[] password) throws Exception
  {
    //--------Not Yet Implemented-----
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
    PublicKey pubKey = null;
    if (this.algorithmName.equals(DSA_ALGORITHM_NAME ))
    {pubKey = dsaKeyPairs.getPublicKey();}
    else if (this.algorithmName.equals(RSA_ALGORITHM_NAME ))
    {pubKey = rsaKeyPairs.getPublicKey();}
    byte[] encodedPubKey = pubKey.getEncoded(); //---------This what should be sent to the recviving party
    String encodedPubKeyAsString = new BigInteger(encodedPubKey).toString(16);
    //-------receiving Party should perfom the follwing ----    
    byte[] retrevEncodedPubKey = new BigInteger(encodedPubKeyAsString,16).toByteArray();
    X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(retrevEncodedPubKey);
    KeyFactory kf =KeyFactory.getInstance(this.algorithmName);
    PublicKey retrivedPubKey = kf.generatePublic(pubKeySpec);
    Signature sig = Signature.getInstance("SHA1with"+this.algorithmName);
    sig.initVerify(pubKey);
    sig.update(myData);
    result = sig.verify(signature);
    if (! result)
    {throw new Exception("Shawky : Invalid Signature - Data and the Signature Does Not Match");}
  }
 

  public static void main(String[] args) throws Exception
  {
    String data = "Allah" ;
    byte[] signature = {};
    try{
    DataSigner dsaDataSigner = new DataSigner(false, "RSA");
    signature = dsaDataSigner.sign(data, new String("fasdf").toCharArray());
    dsaDataSigner.validateSignature(data, signature);
    
    }
    catch (Exception e ){e.printStackTrace(); }
 
  }
  
  
}