package Support.Draw;
//------1---to store the data (chart bar parmeter-----
public class HotRecArea 
{
  public int x1;
  public int y1;
  public int hight;
  public int width;
  public String label;
  public HotRecArea(int m_x1 , int m_y1, int m_width , int m_hight  , String m_label)
  {
    this.x1 = m_x1;
    this.y1 = m_y1;
    this.hight = m_hight;
    this.width = m_width;
    label = m_label;
  }
}