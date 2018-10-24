package Support.Draw;
import Support.*;
import Support.HTML.*;
import java.util.*;
import java.awt.*;
import java.sql.*;
public class chartDrawer 
{
int width = 500;
int hight = 500;
int axisMagin = 20;
int barHight  = 20 ;
int startAt = 20;
int maxXdata = 0;
int minXdata = 0;
int[] sumOfXData ;
String[] Ydata ;
Vector[] data  = null; 
String filePath = "C\\temp\\abc.jpg";
java.util.Vector[] hotRecAreas ;
Vector hotArcAreas;
Color[] barColor ;
Color backGroundColor = new Color(200,200,200);
Connection con ;
Vector[] anchorXTags;
AnchorTag[] anchorYTags;
  public void setDBConnection( String m_DBServerIP, String m_DBServiceName , String m_DBUserName ,String m_DBpassword ) throws Exception
  {
  String ConnectionString="jdbc:oracle:thin:@"+m_DBServerIP +":1521:"+m_DBServiceName ;
  con =  java.sql.DriverManager.getConnection(ConnectionString,m_DBUserName,m_DBpassword);
  }
  public void setDBConnection(Connection m_con)
  {
    this.con = m_con;
  }
  public Vector[] getAnchorXTags()
  {
    return this.anchorXTags;
  }
    public AnchorTag[] getAnchorYTags()
  {
    return this.anchorYTags;
  }

  public void setDataFromDBQuery(String queryStatment) throws Exception
  {
  HTMLDbTransactionBeanWOPooling db = new HTMLDbTransactionBeanWOPooling();
  db.setDBConnection(con);
  db.setQueryStatment(queryStatment);
  data = db.getResultSetAsArrayofVectors();
  if( data[0].size() == 0 )
  {
    throw new Exception("No Data Found");
  }
  anchorXTags = new Vector[data.length];
  
  anchorYTags = new AnchorTag[data[0].size()]; 
  Ydata = new String[data[0].size()];
  for (int i = 0; i< data[0].size() ; i++)
  {
	  AnchorTag at = new AnchorTag(data[0].elementAt(i).toString() , "a"); 
	  if ( at.isHtmlTag("a") )
      {
        anchorYTags[i] = new AnchorTag(data[0].elementAt(i).toString() , "a");
        data[0].setElementAt(anchorYTags[i].getValue(),i)  ;
      }
    this.Ydata[i] = data[0].elementAt(i).toString();
    
  }
  //-------------Loop for all Data sets to find max and min -----------
  sumOfXData = new int[data.length];
  for (int g = 1 ; g < data.length ; g++)
  {
    anchorXTags[g] = new Vector();
    AnchorTag tag = new AnchorTag(data[g].elementAt(0).toString() , "a"); 
    if ( tag.isHtmlTag("a"))
         {minXdata = Integer.parseInt(new AnchorTag(data[g].elementAt(0).toString() , "a").getValue() );} //-- as a starting value to find the min      
     else {minXdata = Integer.parseInt(data[1].elementAt(0).toString());} //-- as a starting value to find the min 
    for (int i = 0; i< data[g].size() ; i++)
    {
      //-----------If x data contains Anchors hyperlinks remove it
    	AnchorTag tagi = new AnchorTag(data[g].elementAt(i).toString() , "a") ; 
    	if ( tagi.isHtmlTag("a") )
        {
          anchorXTags[g].addElement( new AnchorTag(data[g].elementAt(i).toString() , "a"));
          data[g].setElementAt(new AnchorTag(data[g].elementAt(i).toString() , "a").getValue(),i)  ;
        }
      //Xdata[i] = data[g].elementAt(i).toString();
      sumOfXData[g] += Integer.parseInt(data[g].elementAt(i).toString());
      //---------setting Min & Max Values----
      if (Integer.parseInt(data[g].elementAt(i).toString()) > maxXdata)
        { 
          maxXdata = Integer.parseInt(data[g].elementAt(i).toString());
        }
      if (Integer.parseInt(data[g].elementAt(i).toString()) < minXdata)
        { 
          minXdata = Integer.parseInt(data[g].elementAt(i).toString());
        }  
        
    }
  }
  }
  private int mapXvalueToXAxis(int xValue)
  { 
    this.minXdata = 0; 
    int a = (this.width-this.axisMagin - 0);
    int b = (this.maxXdata-this.minXdata);
    float slop = (float)a /b ; 
    slop = slop * (xValue - this.minXdata);
    int x= Math.round( slop );
    return x;
  }
  private int mapXvalueToRelativeAngle(int xValue , int dataSet)
  {
    return  Math.round((float)(xValue * 360)/this.sumOfXData[dataSet]);
  }
    
  public void setfilePath( String m_filePath)
  {
    filePath = m_filePath; 
  }

  public void setWidth( int m_width)
  {
    width = m_width; 
  }
  public void setHight( int m_hight)
  {
    hight = m_hight; 
  }
 public void setAxisMagin( int m_axisMagin)
  {
    axisMagin = m_axisMagin; 
  }
 public void setBarHight( int m_barHight)
  {
    barHight = m_barHight; 
  }

 public void setStartAt( int m_startAt)
  {
    startAt = m_startAt; 
  }
   public void setYdata( String[] m_Ydata)
  {
    Ydata = m_Ydata; 
  }
  /*
  public void setXdata( String[] m_Xdata)
  {
    Xdata = m_Xdata; 
  }
  */
  public void setBarColor( Color[] m_co)
  {
  barColor = m_co;
  }
  public void setBackGroundColor( Color m_co)
  {
  backGroundColor = m_co;
  }
  public chartDrawer()
  {
   
  }
public void drawRecChart() throws Exception
 {
    if (data == null || Ydata == null)
    {
      throw new Exception("Please Set Data First");
    }
    
    MyJPGFileWriter xx  = new MyJPGFileWriter(width,hight);
    Graphics g = xx.getImageGraphics();
    //------1---Set Back ground Color-----
    g.setColor(this.backGroundColor);
    g.fillRect(0,0,width,hight);
    
    //-----2- draw axis--------
    g.setColor(new Color(0,0,0));
    g.drawLine(axisMagin,0,axisMagin,hight);
    g.drawLine(0,hight-axisMagin,width,hight-axisMagin);
    //----- end of drawing axix
    //-----3- drawing axis labels---
    int yInterval = Math.round( (float)(hight-2*axisMagin) / (Ydata.length-1));
    int yLevel= 0;
    hotRecAreas = new java.util.Vector[data.length];
    HotRecArea hotRecArea ;
    for (int k = 1; k< data.length ; k++)
    {
        hotRecAreas[k] = new Vector();
        for (int i = 0; i< Ydata.length ; i++)
        {
         yLevel = hight- axisMagin - startAt- ((i)* yInterval)  ;
         yLevel= yLevel - (k-1)*barHight ;
          g.setColor(barColor[k]);
          g.drawString(Ydata[i],0, yLevel );
          
          int xWidth = Integer.parseInt(data[k].elementAt(i).toString());
          int barWidth = this.mapXvalueToXAxis(xWidth);
          //g.fillRect(this.hight -axisMagin -( yLevel-barHight/2) , axisMagin ,  barHight , barWidth  );          
          g.fillRect(axisMagin ,yLevel-barHight/2 , barWidth, barHight );
          //---------Labeling Balrs------
          g.setColor(new Color(255,255,255));
          g.drawString(data[k].elementAt(i).toString(), axisMagin + barWidth/2 ,yLevel+barHight/2 ); 
          g.setColor(barColor[k]);
          //--Storing Hot Areas-----
          hotRecArea = new HotRecArea(axisMagin,yLevel-barHight/2, barWidth, barHight,Ydata[i]);
          hotRecAreas[k].addElement(hotRecArea);
        }
    }
    xx.writeToFile(filePath);
  
  }
public void drawBieChart() throws Exception
{
   if (data == null || Ydata == null)
    {
      throw new Exception("Please Set Data First");
    }
    
    MyJPGFileWriter xx  = new MyJPGFileWriter(width,hight);
    Graphics g = xx.getImageGraphics();
    //------1---Set Back ground Color-----
    g.setColor(this.backGroundColor);
    g.fillRect(0,0,width,hight);
        
    HotArcArea hotArcArea ;
    HotRecArea hotRecArea;
    hotRecAreas = new Vector[1];
    hotArcAreas = new Vector();
    int startAngle= 0;
    int arcAngle = 0;
    hotRecAreas[0] = new Vector();
    for (int i = 0; i< Ydata.length ; i++)
    {
      Color   arcColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
      g.setColor(arcColor);
      arcAngle = mapXvalueToRelativeAngle(Integer.parseInt(data[1].elementAt(i).toString()),1);
      g.fillArc(width/4 ,hight/2,width/2,hight/2,startAngle,arcAngle);
      int legandX = 20 ;//Math.round((float)(3*width)/4);
      int legandY = i*30 + 20 ;
      int legandWidth = 50;
      int legandHight = 20;
      g.fillRect(legandX,legandY,legandWidth,legandHight);
      hotRecArea = new HotRecArea(legandX,legandY,legandWidth,legandHight,Ydata[i].toString());
      hotRecAreas[0].addElement(hotRecArea);
      g.drawString(Ydata[i].toString() +"  ("+data[1].elementAt(i).toString()+")",legandX+legandWidth ,legandY + (legandHight/2) );
      hotArcArea = new HotArcArea(width/4 ,hight/4 ,width/2,hight/2,  startAngle,arcAngle,Ydata[i].toString(), arcColor);
      hotArcAreas.addElement(hotArcArea);
      startAngle+= arcAngle;
    }
    xx.writeToFile(filePath);
}


public Vector[] getHotRecAreas()
{
  return hotRecAreas;
}
public static void main(String[]args )
{
  int a = 10 ;
  int b = 3;
  float c= (float)a/b;
  System.out.print(c);
}

}