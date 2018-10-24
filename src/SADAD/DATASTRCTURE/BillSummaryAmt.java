package SADAD.DATASTRCTURE;

public class BillSummaryAmt 
{
  Currency curAmt;
  BillShortDesc billShortDesc;
  public BillSummaryAmt()
  {
  }
  
   public String toXMLTag()
   {
    String xmlTag="<BillSummaryAmt>";
    xmlTag+=curAmt.toXMLString();
   
    xmlTag+=billShortDesc.toXMLTag();
    xmlTag+="</BillSummaryAmt>";
    return xmlTag;
   }
  
}

