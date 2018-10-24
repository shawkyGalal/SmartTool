package Support.Draw;
import java.awt.*;
public class HotArcArea 
{
  public int x1;
  public int y1;
  public int width;
  public int hight;
  public int arcStartAngle;
  public int arcAngle;
  public Color co;
  public String label;
  public HotArcArea(int m_x1 , int m_y1, int m_width , int m_hight , int m_arcStartAngle , int m_arcAngle  , String m_label, Color m_co)
  {
    this.x1 = m_x1;
    this.y1 = m_y1;
    width = m_width;
    hight = m_hight;
    this.arcStartAngle = m_arcStartAngle;
    this.arcAngle = m_arcAngle;
    label = m_label;
    this.co = m_co;
  }
 
}