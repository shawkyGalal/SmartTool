package SADAD;
import java.math.*;
import java.security.*;
import java.security.spec.*;


public class NcciKeyPairs 
{
  //----------The following Value have been created by executing the generateNewParms() method
  public static BigInteger p = new BigInteger("fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c7", 16);
  public static BigInteger q = new BigInteger("9760508f15230bccb292b982a2eb840bf0581cf5" , 16);
  public static BigInteger g = new BigInteger("f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a" , 16 );
  //---------The Private Key x ----------
  public static BigInteger x = new BigInteger("595f3f4b3341c771dcc6260f4cd00103d3d02a6" , 16);
  //---------The public Key y -----------
  public static BigInteger y = new BigInteger("bb9fbb5dc43f35eb35e19eb83716bf36912827a4f701afc2b540fe75d0b9c10977a03d0a9dec3bb289f70db1b21abc3e7631ea64e92d4cebb57445b4755b0065d59c536ba58d71eb6b31d071b81ba4b149c83162a2565f7a666edab600c14f6f754328ec255c3f84d74c31207e5f1672545df5d9ae7831d8aed87f0add70bfff", 16);

  public static PrivateKey getPrivateKey() throws Exception 
  {
     DSAPrivateKeySpec dsaPrivateKeySpec=  new DSAPrivateKeySpec(x,p,q,g);
     KeyFactory keyFactory = KeyFactory.getInstance("DSA");
     PrivateKey pk = keyFactory.generatePrivate(dsaPrivateKeySpec);
     return pk;
  }
  public static PublicKey getPublicKey() throws Exception
  {
     DSAPublicKeySpec dsaPublicKeySpec=  new DSAPublicKeySpec(y,p,q,g);
     KeyFactory keyFactory = KeyFactory.getInstance("DSA");
     PublicKey pk = keyFactory.generatePublic(dsaPublicKeySpec);
     return pk;
  }
  
  public NcciKeyPairs(BigInteger v_p ,BigInteger v_q, BigInteger v_g , BigInteger v_y , BigInteger v_x)
  {
    p = v_p;
    q= v_q;
    g=v_g;
    x= v_x;
    y = v_y;
  }
  public static void generateNewParms() throws Exception
  {
    DSAKeyParmsGenerator dsaParmGen = new DSAKeyParmsGenerator();
    p = dsaParmGen.getP();
    q = dsaParmGen.getQ();
    g = dsaParmGen.getG();
    x= dsaParmGen.getX();
    y =dsaParmGen.getY(); 
    
 
  }
 public static void main(String[] args) throws Exception
 {
   generateNewParms();
 }
}