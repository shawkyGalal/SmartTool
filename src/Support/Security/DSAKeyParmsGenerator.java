package Support.Security;

import java.math.*;
import java.security.*;
import java.security.spec.*;

public class DSAKeyParmsGenerator 
{
  //----------The following Value have been created by executing the generateNewParms() method
  public BigInteger p ;
  public BigInteger q ;
  public BigInteger g ;
  //---------The Private Key x ----------
  public BigInteger x ;
  //---------The public Key y -----------
  public BigInteger y ;
  
  public DSAKeyParmsGenerator()throws Exception
  {  
    generate();
  }
  
  private void generate() throws Exception
  {
    KeyPairGenerator  keyGen = KeyPairGenerator.getInstance("DSA");
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
    byte[] mySeed = random.generateSeed(80); 
    random.setSeed(mySeed);
    keyGen.initialize(1024,random);
    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey pk = pair.getPrivate();
    
    //-------To Get the Parameter--
    KeyFactory kf =  KeyFactory.getInstance("DSA");
    DSAPrivateKeySpec dsaPrivateKeySpec = (DSAPrivateKeySpec)kf.getKeySpec(pk,DSAPrivateKeySpec.class);
    g = dsaPrivateKeySpec.getG();
    p = dsaPrivateKeySpec.getP();
    q = dsaPrivateKeySpec.getQ();
    x = dsaPrivateKeySpec.getX();
    PublicKey pubKey = pair.getPublic();
    DSAPublicKeySpec dsaPublicKeySpec = (DSAPublicKeySpec)kf.getKeySpec(pubKey,DSAPublicKeySpec.class);
    y = dsaPublicKeySpec.getY();  

  }
  public BigInteger getP()
  {
    return p;
  }

  public BigInteger getQ()
  {
    return q;
  }

  public BigInteger getG()
  {
    return g;
  }
    public BigInteger getX()
  {
    return x;
  }
  public BigInteger getY()
  {
    return y;
  }

}