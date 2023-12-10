package SADAD.DATASTRCTURE;

 public class BillShortDesc
  {
    String text="";
    String language="";
    public  BillShortDesc(BillRec billRec)
    {
    
    }
    String toXMLTag()
    {
      String XMLTag= "<ShortDesc>";
      XMLTag+="\n\t<language>"+language+"</language>";
      XMLTag+="\n\t<Text>"+text+"</Text>";
      XMLTag+= "</ShortDesc>";
      return XMLTag;
    }
  }