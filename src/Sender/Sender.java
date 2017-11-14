package Sender;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sender {
	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 8000;
	
		try {
			
			Socket s = new Socket(server ,port);

			OutputStream out = s.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);
				
			s.close();
		
		}
		catch (Exception e) {}
		
		
	}
}
