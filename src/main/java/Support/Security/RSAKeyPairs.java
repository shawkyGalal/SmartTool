package Support.Security;

import java.math.*;
import java.security.*;
import java.security.spec.*;


public class RSAKeyPairs 
{
  //----------The following Value have been created by executing the generateNewParms() method
  public  BigInteger modulus = new BigInteger("d460642b469e24efeb101d9f469a8fb5f21042bb9c88080d762af6b88cb3c549a166f7a9a5cd48ff1a4a19ed5ffdf5ae7f5d8f2781e2e25a4635385f38c68b78746345a5a71461d7fdf651be907896acc48633b1e7c0b9ff965868b59c9a32037551a6f2d8b3582f63994b1a7eb96990bc4ef610eb515342ea2524d3eff9b59f", 16);
  public  BigInteger privateExponent = new BigInteger("24589acc0d39e3786bf2461f8c25c7928beadc80df3928a7a5219537e05ca3d70618692a4f85e2884627886867953f06ae2584ea6e00365cdcc71eeeacf8cdc56f7d4409eaf95cde5ccf66e3aafc42eae18a81e2ae6bffc4a6dc7a4ee21565f26374ca78d3e0a7e0351e66216155fb427cbaf563e650360218ee7812772a1101" , 16);
  public  BigInteger publicExponent = new BigInteger("10001" , 16);

  public PrivateKey getPrivateKey() throws Exception 
  {
     RSAPrivateKeySpec rsaPrivateKeySpec=  new RSAPrivateKeySpec(modulus,privateExponent);
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     PrivateKey pk = keyFactory.generatePrivate(rsaPrivateKeySpec);
     return pk;
  }
  public PublicKey getPublicKey() throws Exception
  {
     RSAPublicKeySpec rsaPublicKeySpec=  new RSAPublicKeySpec(this.modulus, this.publicExponent);
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     PublicKey pk = keyFactory.generatePublic(rsaPublicKeySpec);
     return pk;
  }
  
  public RSAKeyPairs(boolean withNewParms) throws Exception
  {
    Support.Validator1.checkExpiry();
    if (withNewParms)
    {
      generateNewParms();
    }
    
  }
  public void generateNewParms() throws Exception
  {
    RSAKeyParmsGenerator rsaParmGen = new RSAKeyParmsGenerator();
    this.modulus = rsaParmGen.getModuls();
    this.privateExponent = rsaParmGen.getPrivateExponent();
    this.publicExponent = rsaParmGen.getPublicExponent();
    
    String modulusSt = modulus.toString(16);
    String privateExponentSt = privateExponent.toString(16);
    String publicExponentSt = publicExponent.toString(16);
  }
 public static void main(String[] args) throws Exception
 {
   RSAKeyPairs rsaKeyPairs = new RSAKeyPairs(false);
   rsaKeyPairs.generateNewParms();
 }
}