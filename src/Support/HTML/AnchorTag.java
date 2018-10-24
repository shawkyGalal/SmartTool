package Support.HTML;

public class AnchorTag 
{
  String anchorStr = null;
  String hrefValue = "";
  String value= "";
//  private String tagName = "a" ; 
  public AnchorTag(String m_anchorStr , String m_tagName) throws Exception
  {
    validateAnchor(m_anchorStr , m_tagName);
    this.anchorStr = m_anchorStr.toLowerCase();
    setValue(m_tagName);
  }
  public void validateAnchor(String m_anchorStr , String m_tagName) throws Exception
  {
    if( m_anchorStr.indexOf("<"+m_tagName+" ") == -1
        || m_anchorStr.indexOf("</"+m_tagName+">") == -1)
        {
          throw new Exception("Anchor tages Does not Exist");
        }
  }
  public void setValue(String m_tagName)
  {
    int startIndex = this.anchorStr.indexOf(">");
    int endIndex = this.anchorStr.indexOf("</"+m_tagName+">");
    
     this.value = anchorStr.substring(startIndex+1, endIndex);
  }
 public String getValue()
  {
     return this.value;
  }

  public boolean isHtmlTag(String m_tagName)
  {
    boolean result = false;
    m_tagName = m_tagName.toLowerCase(); 
    if( anchorStr != null && anchorStr.indexOf("<"+m_tagName+" ") != -1
        && anchorStr.indexOf("</"+m_tagName+">") != -1)
        {
          result = true; 
          setValue(m_tagName); 
        }
    return result;
  }
  public String getAllContents(String m_tagName)
  {
    String allContents= "";
    int startIndex = this.anchorStr.indexOf("<"+m_tagName) + 2;
    int endIndex = this.anchorStr.indexOf(">");
    allContents = this.anchorStr.substring(startIndex, endIndex);
    return allContents;
  }
  public String getHrefValue()
  {
    String hrefValue="";
    String hrefString = "href";
    int hrefIndex  = this.anchorStr.indexOf(hrefString);
    int equalSignIndex = this.anchorStr.indexOf(hrefString)+ hrefString.length() +1;
    int startIndex = equalSignIndex +1  ;
    int endIndex = this.anchorStr.indexOf(" ", startIndex) ;
    hrefValue = anchorStr.substring(startIndex+1  , endIndex-1);
    return hrefValue;
  }
  public static void main(String[] args)
  {
    try{ 
      AnchorTag anchorTag = new AnchorTag("<a target = \"abc\" href =\"editAndExecute.jsp?id=3050&billerId=003\" alt = 'yrtyrt' title = 'sdfsdf' >118201</a>" , "a"); 
      System.out.print(anchorTag.getAllContents("ui"));
    }
    catch (Exception e) {System.out.print(e);}
  }
}