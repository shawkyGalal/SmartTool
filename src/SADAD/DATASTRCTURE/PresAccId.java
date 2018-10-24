package SADAD.DATASTRCTURE;

public class PresAccId 
{
  String billingAcct="";
  static final  String billerId="";
  public PresAccId()
  {
  }
  
   public String toXMLTag()
  {
    String xMLTag = "/n" + "<PresAccId>" ;
    xMLTag +="/n/t" +"<BillerId>";
    xMLTag +="/n/t" + billerId;
    xMLTag +="/n/t" +"<BillerId>";
    
    xMLTag +="/n/t" +"<BillingAcct>";
    xMLTag +="/n/t" + billingAcct;
    xMLTag +="/n/t" +"<BillingAcct>";
    
    xMLTag = "/n/t" + "</PresAccId>";
    return xMLTag;
    
  }
}