package Support.Draw;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.util.Iterator;
//import java.util.Iterator;
public class MyJPGFileWriter 
{
  private BufferedImage bi;
public MyJPGFileWriter(int hight , int width ) throws IOException
{
  //-------Start with a  new JPG File 
  bi = new BufferedImage(hight, width, BufferedImage.TYPE_INT_RGB);
}

public MyJPGFileWriter(String inFilePath) throws IOException
{
   File f1 = new File(inFilePath);
   bi = ImageIO.read(f1);
}

public Graphics getImageGraphics()
{
  return bi.getGraphics();
}
public void writeToFile (String outFilePath) throws IOException
{
   //-----Deleting
   File f2 = new File(outFilePath);
   f2.renameTo(new File("outFilePath"+"1"));
   boolean x= f2.delete();
   Iterator writers = ImageIO.getImageWritersByFormatName("jpg");
   ImageWriter writer = (ImageWriter)writers.next();
   //Once an ImageWriter has been obtained, its destination must be set to an ImageOutputStream:
   f2 = new File(outFilePath);
   ImageOutputStream ios = ImageIO.createImageOutputStream(f2);
   writer.setOutput(ios);
   //Finally, the image may be written to the output stream:
   writer.write(bi);
   ios.close();
   
   
}

    public static void main(String s[]) throws Exception
    {
    //MyJPGFileWriter gw = new  MyJPGFileWriter("C:\\temp\\optical_illusion_9.jpg");
    MyJPGFileWriter gw = new  MyJPGFileWriter(300,300);
    Graphics g = gw.getImageGraphics();
    //-----------Start Drawing ---------
    g.setColor(new Color(255,255,255));
    g.drawRect(0,0,300,300);
    g.fillRect(0,0,300,300);
    g.setColor(new Color(200,200,200));
    g.drawString("Shawky Galal Foda",50,50);
    g.drawArc(0,0,100,100,30,120);
    g.drawLine(0,0, gw.bi.getWidth(), gw.bi.getHeight());
    g.fillRect(100,100,100,100);
    //----------------End of Drawing-----------
    
    gw.writeToFile("D:\\weps\\Chart\\public_html\\images\\xxx.jpg");
     
    } 
}