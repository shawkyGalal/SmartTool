package Support.JAS;
import Support.*;
import jas.hist.JASHist;
import java.sql.Connection;
import java.awt.Color;
import java.sql.DriverManager;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class TestJAS extends JApplet
{
   private JASHist plot;
   private Connection con;
   
   public TestJAS()
   {
      plot = new JASHist();
      plot.setBackground(Color.white);
      plot.setTitle("");
      plot.getYAxis().setLabel("");
      plot.getXAxis().setLabel("Time");
      plot.setAllowUserInteraction(true);
      getContentPane().add(plot);
   }
   
   	
 
/*
    public Connection getConnection()
		{
			Connection conn = null;
      try
			{								
				DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
      	conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1251:orcl", "shawky", "redsea11");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
			return conn;
		}

  */  
   public void start()
   {
      
      String queryString = this.getParameter("queryString"); 
      String queryTitle = this.getParameter("queryTitle");
         
      String dbUrl = this.getParameter("dbUrl");
      String dbDriver = this.getParameter("dbDriver");
      String userName = this.getParameter("userName");
      String password = this.getParameter("password");
      // Generate the Db Conection

      //con = this.getConnection();      
	
    	try
			{	
               DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        this.con= DriverManager.getConnection(dbUrl,userName,password);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Unable to Establish xxx DB Connection Due To \n"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}

      plot.addData(new RecordDataSource(plot , con, queryString , queryTitle)).show(true);
      // plot.addData(new RecordDataSourceSkipped(plot)).show(true);
      super.start();
   }
   public void stop()
   {
      super.stop();
      
      if (this.con != null)
      {
        try{
        this.con.close();
        }
        catch(Exception e){}
      }

      plot.removeAllData();
   }
}






