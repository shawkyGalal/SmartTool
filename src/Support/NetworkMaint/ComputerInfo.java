package Support.NetworkMaint;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ComputerInfo extends BasicDeviceInfo {
	private InetAddress inetAddress ;
	private String monitorSerialNumber ;
	private String ipAddress ;


	public InetAddress getInetAddress() {
		return inetAddress;
	}

	@Override
	protected void save() {
		// TODO Auto-generated method stub
		
	}

	public void setMonitorSerialNumber(String monitorSerialNumber) {
		this.monitorSerialNumber = monitorSerialNumber;
	}

	public String getMonitorSerialNumber() {
		return monitorSerialNumber;
	}
	
//	protected String getMacAddress()
//	{
//		return this.getInetAddress()
//	}

	public  String getAllHostINfo()
	{
		StringBuffer info = new StringBuffer();
 
		InetAddress add = this.getInetAddress() ;
		try {

            //add = InetAddress.getByName(ipAstring);
            
            info.append("          host: " + add.getHostName());
            info.append("         class: " + add.getClass().getSimpleName());
            info.append("            ip: " + add.getHostAddress());
            info.append("         chost: " + add.getCanonicalHostName());
            info.append("    sitelocal?: " + add.isSiteLocalAddress());
    
            String macAdd = this.getMacAddress();
            info.append(macAdd);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return info.toString();
	}

	public String getMacAddress() throws SocketException
	{

    	String macAdd = ("");

       NetworkInterface ni1 = NetworkInterface.getByInetAddress(this.getInetAddress());
         if (ni1 != null) 
         {
         	System.out.print("    Net Interface Name: " + ni1.getName());	
         	byte[] mac = ni1.getHardwareAddress();
             if (mac != null) {

             	for (int k = 0; k < mac.length; k++) {
             		macAdd = macAdd + String.format("%02X%s", mac[k], (k < mac.length - 1) ? "-" : "");
                 }
                
             } else {
                 System.out.println("Address doesn't exist ");
             }
         } 
         else {
             System.out.println("address is not found.");
         }
     return macAdd ; 
	}
	public void setIpAddress(String ipAddress) throws UnknownHostException {
		this.ipAddress = ipAddress;
		this.inetAddress = InetAddress.getByName(ipAddress);
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
}
