package Support.RemoteControl;
import java.io.*;
import java.util.*;

public class RuntimeCommander extends Commander
{
  
  public RuntimeCommander(String allCommands_m) throws Exception
  {
   this.AllCommands = allCommands_m;
   this.AllCommands = this.extractInfo("RunTime_Command");
   this.command = this.extractInfo("COMMAND");
   this.mailTo =this.extractInfo("MAILTO");
  }
 public void execAndSendMail() throws Exception 
 {
     try
        {            
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(this.command);
            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            commandOutput = "Result of Executing Command \""+this.command+"\"\n<Br>---------------------\n<Br><table  border= 1>";
            while ( (line = br.readLine()) != null)
                this.commandOutput += "\n <tr><td>" + line.replaceAll(">","&gt").replaceAll("<","&lt").replaceAll("\t","</td><td>") +"</td></tr>";
            commandOutput += "\n </table>";
            //System.out.println("</OUTPUT>");
            int exitVal = proc.waitFor();            
            commandOutput += "<Br> Process exitValue: " + exitVal;
            System.out.println(commandOutput);
            this.sendCommandOutByEmail();
            
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
   
 }
  public static void main(String[] args) throws Exception
  {
    Commander c = new RuntimeCommander("cmd /c edit D:\\MyWork\\Support\\public_html\\AJAX_Chart.jsp");
    c.execAndSendMail();

     
  }
}