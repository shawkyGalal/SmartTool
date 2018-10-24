package Support.Security;

import Support.*;
import java.math.*;
import java.security.*;
import java.security.spec.*;

public class RSAKeyParmsGenerator 
{
  //----------The following Value have been created by executing the generateNewParms() method
  //---------The Private Key x ----------
  public BigInteger moduls ;
  public BigInteger privateExponent ;
  //---------The public Key y -----------
  public BigInteger publicExponent ;
    
  public RSAKeyParmsGenerator()throws Exception
  {  
    Validator1.checkExpiry();
    generate();
  }
  
  private void generate() throws Exception
  {
    KeyPairGenerator  keyGen = KeyPairGenerator.getInstance("RSA");
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
    byte[] mySeed = random.generateSeed(80); 
    random.setSeed(mySeed);
    keyGen.initialize(1024,random);
    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey pk = pair.getPrivate();
    
    //-------To Get the Parameter--
    KeyFactory kf =  KeyFactory.getInstance("RSA");
    RSAPrivateKeySpec rsaPrivateKeySpec = (RSAPrivateKeySpec)kf.getKeySpec(pk,RSAPrivateKeySpec.class);
    moduls = rsaPrivateKeySpec.getModulus();
    privateExponent = rsaPrivateKeySpec.getPrivateExponent();
    
    PublicKey pubKey = pair.getPublic();
    RSAPublicKeySpec rsaPublicKeySpec = (RSAPublicKeySpec)kf.getKeySpec(pubKey,RSAPublicKeySpec.class);
    publicExponent = rsaPublicKeySpec.getPublicExponent(); 

  }
  public BigInteger getModuls()
  {
    return this.moduls;
  }

  public BigInteger getPrivateExponent()
  {
    return this.privateExponent;
  }

  public BigInteger getPublicExponent()
  {
    return publicExponent;
  }
  public static void main(String[] arg) throws Exception
  {
    try{
    RSAKeyParmsGenerator x = new RSAKeyParmsGenerator();
    }
    catch (Exception e){e.printStackTrace(); throw e;}
  }

}