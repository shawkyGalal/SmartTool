package SADAD.DATASTRCTURE;
/**
   * Bill hjgfhjg  hjg hg h
   */
public class BillStausCode 
{
  
  static final int PAID = 0;
  static final int PARTIALLY_PAID = 1;
  static final int OVER_PAID = 2;
  static final int UNPAID = 3;
  static final int NEW_BILL = 4;
  static final int BILL_UPDATE = 5;
  static final int BILL_EXPIRED = 6;
  static final int BILL_CANCELED = 7;
  static final int ACCOUNT_DEACTIVATED = 8;
  static final String[] StatusDesc = {  "PAID " 
                                      , "PARTIALLY_PAID "
                                      , "OVER_PAID "
                                      , "UNPAID "
                                      , "NEW_BILL "
                                      , "BILL_UPDATE "
                                      , "BILL_EXPIRED "
                                      , "BILL_CANCELED "
                                      , "ACCOUNT_DEACTIVATED"};
  int billStatusCode = 4; //---------Default Value is a new Bill--
  public BillStausCode()
  {
  }
   public String toXMLTag()
   {
    String xmlTag="\n<BillStatusCode>" +StatusDesc[billStatusCode] + "</BillStatusCode>";
    return xmlTag;
   }
   
   public void setCode(int m_billStatusCode)
   {
    billStatusCode = m_billStatusCode;
   }
}