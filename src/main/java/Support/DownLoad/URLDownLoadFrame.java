package Support.DownLoad;
import java.text.*;
import java.util.*;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;

public class URLDownLoadFrame extends JFrame 
{
  //private ImageIcon imageHelp = new ImageIcon(URLDownLoadFrame.class.getResource("help.gif"));
  //private ImageIcon imageClose = new ImageIcon(URLDownLoadFrame.class.getResource("closefile.gif"));
  //private ImageIcon imageOpen = new ImageIcon(URLDownLoadFrame.class.getResource("openfile.gif"));
  private JToolBar toolBar = new JToolBar();
  private JLabel statusBar = new JLabel();
  private JMenuItem menuHelpAbout = new JMenuItem();
  private JMenu menuHelp = new JMenu();
  private JMenuItem menuFileExit = new JMenuItem();
  private JMenu menuFile = new JMenu();
  private JMenuBar menuBar = new JMenuBar();
  private JPanel panelCenter = new JPanel();
  private BorderLayout layoutMain = new BorderLayout();
  private JTextField urlAddress = new JTextField();
  private JTextField downLoadTo = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton downLoad = new JButton();
  private JTextArea progress = new JTextArea();
  private JCheckBox useProxy = new JCheckBox();
  private JTextField proxyServer = new JTextField();
  private JTextField proxyUserName = new JTextField();
  private JPasswordField proxyPassword = new JPasswordField();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField proxyPort = new JTextField();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JButton jButton1 = new JButton();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel10 = new JLabel();

  public URLDownLoadFrame()
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
    this.setJMenuBar(menuBar);
    this.getContentPane().setLayout(layoutMain);
    panelCenter.setLayout(null);
    urlAddress.setBounds(new Rectangle(160, 165, 405, 25));
    urlAddress.setText("http://130.1.2.30:7777/SADAD/bill_Data_prepare.jsp");
    downLoadTo.setBounds(new Rectangle(235, 215, 310, 25));
    downLoadTo.setText("c:\\temp\\renewal_Data<Date>.xml");
    jLabel1.setText("Enter URL Address");
    jLabel1.setBounds(new Rectangle(30, 160, 110, 30));
    jLabel2.setText("1- Down Load Contents To:");
    jLabel2.setBounds(new Rectangle(25, 215, 200, 25));
    jLabel2.setFont(new Font("Tahoma", 1, 12));
    downLoad.setText("Start DownLoad");
    downLoad.setBounds(new Rectangle(245, 295, 120, 30));
    downLoad.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          downLoad_actionPerformed(e);
        }
      });
    progress.setBounds(new Rectangle(35, 330, 525, 45));
    progress.setAutoscrolls(true);
    useProxy.setText("Connect Through Proxy/HTTP Server");
    useProxy.setBounds(new Rectangle(25, 5, 210, 25));
    useProxy.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          useProxy_actionPerformed(e);
        }
      });
    proxyServer.setText("130.1.2.111");
    proxyServer.setBounds(new Rectangle(175, 40, 135, 25));
    proxyUserName.setText("foda_sh");
    proxyUserName.setBounds(new Rectangle(175, 75, 195, 25));
    proxyPassword.setText("1234");
    proxyPassword.setBounds(new Rectangle(175, 105, 195, 25));
    jLabel3.setText("Proxy/HTTP Server");
    jLabel3.setBounds(new Rectangle(65, 35, 105, 30));
    jLabel4.setText("User Name");
    jLabel4.setBounds(new Rectangle(65, 70, 90, 30));
    jLabel5.setText("Password");
    jLabel5.setBounds(new Rectangle(65, 100, 80, 30));
    proxyPort.setText("8080");
    proxyPort.setBounds(new Rectangle(470, 40, 40, 15));
    jLabel6.setText("Port");
    jLabel6.setBounds(new Rectangle(425, 40, 20, 15));
    jLabel7.setText("Note : File Ne = abc<Date>.xml will be translated to abc09-03-2004 " + "10-25.xml.");
    jLabel7.setBounds(new Rectangle(25, 245, 530, 30));
    jLabel7.setHorizontalTextPosition(SwingConstants.LEADING);
    jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel8.setText("where 09-03-2004 10-25 is the current Date and Time");
    jLabel8.setBounds(new Rectangle(60, 265, 360, 20));
    jLabel9.setText("2- Upload Generated XML To URL");
    jLabel9.setBounds(new Rectangle(30, 410, 215, 25));
    jLabel9.setFont(new Font("Tahoma", 1, 13));
    jButton1.setText("Start Upload");
    jButton1.setBounds(new Rectangle(250, 475, 115, 30));
    jTextField1.setBounds(new Rectangle(250, 410, 305, 25));
    jLabel10.setText("(Not Yet implemented)");
    jLabel10.setBounds(new Rectangle(75, 435, 130, 20));
    this.setSize(new Dimension(662, 582));
    this.setTitle("URL DownLoad Application");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          fileExit_ActionPerformed(ae);
        }
      });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          helpAbout_ActionPerformed(ae);
        }
      });
    statusBar.setText("");
    menuFile.add(menuFileExit);
    menuBar.add(menuFile);
    menuHelp.add(menuHelpAbout);
    menuBar.add(menuHelp);
    this.getContentPane().add(statusBar, BorderLayout.WEST);
    this.getContentPane().add(toolBar, BorderLayout.NORTH);
    this.getContentPane().add(panelCenter, BorderLayout.CENTER);
    panelCenter.add(jLabel10, null);
    panelCenter.add(jTextField1, null);
    panelCenter.add(jButton1, null);
    panelCenter.add(jLabel9, null);
    panelCenter.add(jLabel8, null);
    panelCenter.add(jLabel7, null);
    panelCenter.add(jLabel5, null);
    panelCenter.add(jLabel4, null);
    panelCenter.add(jLabel3, null);
    panelCenter.add(proxyPassword, null);
    panelCenter.add(proxyUserName, null);
    panelCenter.add(proxyServer, null);
    panelCenter.add(downLoad, null);
    panelCenter.add(jLabel2, null);
    panelCenter.add(jLabel1, null);
    panelCenter.add(downLoadTo, null);
    panelCenter.add(urlAddress, null);
    panelCenter.add(progress, null);
    panelCenter.add(useProxy, null);
    panelCenter.add(proxyPort, null);
    panelCenter.add(jLabel6, null);
  }

  void fileExit_ActionPerformed(ActionEvent e)
  {
    System.exit(0);
  }

  void helpAbout_ActionPerformed(ActionEvent e)
  {
    JOptionPane.showMessageDialog(this, new URLDownLoadFrame_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
  }

  private void downLoad_actionPerformed(ActionEvent e)
  {
    this.progress.setText("");  
    this.repaint();
    //----Replacing "<Date>" with the current Date & Time 
    SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy HH-mm");
    String dateTimeStamp = df.format(new Date());
    String filePath = this.downLoadTo.getText().replaceAll("<Date>",dateTimeStamp);
    //---End of Replacing "<Date>" with the current Date & Time 

    try
    {
        URLDownLoader urlDownloader = new URLDownLoader();
        if (this.useProxy.isSelected())
        {
        urlDownloader.downLoad(this.urlAddress.getText(),filePath
                                ,this.proxyServer.getText()
                                ,Integer.parseInt(this.proxyPort.getText())
                                , this.proxyUserName.getText()
                                ,this.proxyPassword.getText());  
        }
        else
        {
        urlDownloader.downLoad(this.urlAddress.getText(),filePath,"",8080,"","");  
        }
        
        progress.setText("Download Done Successully.");
    }
    catch (Exception ex) {this.progress.setText(this.progress.getText() +"\n" + ex.getMessage());}
  }

  private void useProxy_actionPerformed(ActionEvent e)
  {
    if (this.useProxy.isSelected())
    {
      this.proxyServer.enable();
      this.proxyUserName.enable();
      this.proxyPassword.enable();
      this.repaint();
    }
    else 
    {
      this.proxyServer.disable();
      this.proxyUserName.disable();
      this.proxyPassword.disable();
      repaint();
    }
    
  }

  
}