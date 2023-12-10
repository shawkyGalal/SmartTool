package SADAD;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class SadadBillUploadFrame extends JFrame 
{

  private JLabel jLabel1 = new JLabel();
  private JTextField xmlFilePath = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JButton jButton1 = new JButton();
  private JLabel jLabel3 = new JLabel();
  private JButton jButton2 = new JButton();

  public SadadBillUploadFrame()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(669, 476));
    jLabel1.setText("SADAD UPLOAD REQUEST PROCESSING");
    jLabel1.setBounds(new Rectangle(145, 15, 355, 45));
    jLabel1.setFont(new Font("Times New Roman", 0, 18));
    xmlFilePath.setBounds(new Rectangle(295, 100, 245, 25));
    jLabel2.setText("Step 1- Generate XML Upload Request File ");
    jLabel2.setBounds(new Rectangle(20, 95, 300, 30));
    jLabel2.setFont(new Font("Tahoma", 1, 11));
    jButton1.setText("OK");
    jButton1.setBounds(new Rectangle(575, 100, 50, 25));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jLabel3.setText("Step 2- Send File To SADA ( Push File ).");
    jLabel3.setBounds(new Rectangle(20, 175, 300, 30));
    jLabel3.setFont(new Font("Tahoma", 1, 11));
    jButton2.setText("OK");
    jButton2.setBounds(new Rectangle(575, 170, 50, 25));
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(xmlFilePath, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton1_actionPerformed(ActionEvent e) 
  {
  Connection  con= null;
  try{
  con = Support.ConnectionFactory.createConnection_old("","","","");
  SADAD.DATASTRCTURE.BillUploadRq billUploadRq = new SADAD.DATASTRCTURE.BillUploadRq(null,con);
  billUploadRq.writeToFile(this.xmlFilePath.getText());
  con.close();
  
  }
  catch(Exception ex) {
      System.out.print(ex.getMessage());
      try{
      con.close();
      }
      catch (Exception ee) {}
}
  }
}