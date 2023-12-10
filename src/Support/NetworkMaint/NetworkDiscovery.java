package Support.NetworkMaint;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;




public class NetworkDiscovery {
	public static void main(String[] args) {
		for (int i=129 ; i<135 ; i++)
		{
			try {
				System.out.println(printHostINfo ("192.168.1." + i));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String printHostINfo(String ipAstring) throws UnknownHostException
	{
		String info ;
		ComputerInfo compInfo = new ComputerInfo(); 
		compInfo.setIpAddress(ipAstring) ;
		info =  compInfo.getAllHostINfo();
		return info;
	}
	
	public static String toMACAddrString(byte[] a)
	{
	  if (a == null)
	  {
	     return "null";
	  }
	  int iMax = a.length - 1;

	  if (iMax == -1)
	  {
	     return "[]";
	  }

	  StringBuilder b = new StringBuilder();
	  b.append('[');
	  for (int i = 0;; i++)
	  {
	     b.append(String.format("%1$02x", a[i]));

	     if (i == iMax)
	     {
	        return b.append(']').toString();
	     }
	     b.append(":");
	  }
	}
}
