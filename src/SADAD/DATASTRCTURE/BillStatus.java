package SADAD.DATASTRCTURE;

public class BillStatus 
{
  BillStausCode billStatusCode;
  BillShortDesc billShortDesc;
  public BillStatus()
  {
  }
   public String toXMLTag()
  {
  String xmlTag="<BillStatus>";
        xmlTag+=billStatusCode.toXMLTag();
        xmlTag+=billShortDesc.toXMLTag();
        xmlTag+="</BillStatus>";
  return xmlTag ;
  }
}