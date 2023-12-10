package Support;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.net.ssl.internal.www.protocol.https.BASE64Encoder;

/**
 * @author Feras Nabulsi
 */
public class CompressedFilePoster{
	
	private URL url;
	private String username;
	private String password;
	
	public void init(String urlStr, String username, String password) throws MalformedURLException{
		url = new URL(urlStr);
		this.username = username;
		this.password = password;
	}

	public String doPost(String compressedFilePath) throws IOException {
		HttpURLConnection http;
		String response = "";
		http = (HttpURLConnection)url.openConnection();
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setRequestMethod("POST");
		http.setRequestProperty("CONTENT-TYPE","text/plain");
		http.setRequestProperty("Content-Encoding","gzip");
		
		String authString = username+":"+password;  
		String auth = "Basic " + new BASE64Encoder().encode(authString.getBytes()); 
		http.setRequestProperty("Authorization",auth);

		InputStream input = new FileInputStream(compressedFilePath);
		
		OutputStream out = http.getOutputStream();
		new BASE64Encoder().encode(input,out);
		http.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String line = "";
		while((line = in.readLine()) != null)
			response += line;
		in.close();
		input.close();
		out.close();
		return response;
	}
	
	public static void main(String[] args){
		CompressedFilePoster post = new CompressedFilePoster();
		try {
			post.init("http://10.16.17.55:5355/invoke/SADADEX02BillUpload.Accept/receiveBillData","Maulik","Two");
			System.out.println(post.doPost("D:\\sfs\\biller\\001-BillUpload3.xml.gz"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
