package Support;
import java.util.*;
import java.text.*;
public  class Validator1
{
  public Validator1()
  {
  }
  public static void checkExpiry() throws Exception
  {
         
    Date today = new Date();
    long x= 1213935272109L; //1135924172109L;   // 1138600000000L = 30-Jan-2006 // 1138600000000
    Date expireDate = new Date(x);
    if (today.after(expireDate))
    {
    //  throw new Exception("System out of Service : Please Contact ISD (Shawky Foda)");
    }
  }
  public static void main(String[] args) throws Exception
  {
    Validator1.checkExpiry();
   
  }
}