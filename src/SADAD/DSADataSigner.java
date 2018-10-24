package SADAD;
import java.security.*;
import java.security.spec.*;
import java.math.*;
public class DSADataSigner 
{
 
  public DSADataSigner()
  {
  }
  public static byte[]  sign (byte[] myData) throws Exception
  {
    byte[] signature={};
    try{
    //KeyPairGenerator  keyGen = KeyPairGenerator.getInstance("DSA");
    //SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
    //byte[] mySeed = random.generateSeed(20); 
    //random.setSeed(mySeed);
    //keyGen.initialize(1024,random);
    //pair = keyGen.generateKeyPair();
    //PrivateKey pk = pair.getPrivate();
     PrivateKey pk = NcciKeyPairs.getPrivateKey();
     /*
      //-------To Get the Parameter--
      KeyFactory kf =  KeyFactory.getInstance("DSA");
      DSAPrivateKeySpec dsaPrivateKeySpec = (DSAPrivateKeySpec)kf.getKeySpec(pk,DSAPrivateKeySpec.class);
      String g = dsaPrivateKeySpec.getG().toString(16);
      String p = dsaPrivateKeySpec.getP().toString(16);
      String q = dsaPrivateKeySpec.getQ().toString(16);
      String x = dsaPrivateKeySpec.getX().toString(16);
      System.out.print( x);
      
      System.out.print( pk.toString()); */
    //----------Start signing my Data-------    
    Signature sig = Signature.getInstance("SHA1withDSA");
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
  public static boolean validateSignature(byte[] Data , byte[] signature) throws Exception
  {
    boolean result= false; 
    PublicKey pubKey = NcciKeyPairs.getPublicKey();
    Signature sig = Signature.getInstance("SHA1withDSA");
    sig.initVerify(pubKey);
    sig.update(Data);
    result = sig.verify(signature);
    return result;
  }
  public static String[] byteArray2StringArray(byte[] input)
  {
    String[] output= new String[input.length];
    for(int i =0 ; i<input.length ; i++)
    {
      int a = input[i];
      if (a<0 )
        {
         a = (127-a);
        }
      output[i] = Integer.toString(Integer.parseInt(Integer.toString(a)),16) ;
      System.out.print(output[i]+" ");
    }
   return output;
    
  }
  public static void main(String[] args)
  {
    byte[] myData = {1,2,3};
    byte[] signature = {};
    try{
    signature = DSADataSigner.sign(myData);
    DSADataSigner.validateSignature(myData, signature);
    }
    catch (Exception e ){e.printStackTrace();}
    
    byteArray2StringArray(signature);
    //-----------Validating The signature--------
  
    //System.out.print(pubKey);
   /* 
    byte[] encodedKey = {1,2,3};
    X509EncodedKeySpec x509KS = new X509EncodedKeySpec(encodedKey);
    KeyFactory kf = KeyFactory.getInstance("DSA");
    PrivateKey myPrivateKey = kf.generatePrivate(x509KS);
    
    Signature sig = Signature.getInstance("SHA1withRSA");
    sig.initSign(myPrivateKey);
    byte[] data = {1,2,3};
    sig.update(data);
    byte[] outBuf = {1,2,3};
    sig.sign(outBuf,0,2);
    */

  }
}