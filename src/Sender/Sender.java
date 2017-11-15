package Sender;

import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.jdom2.*;
import org.jdom2.output.*;


public class Sender {

	String server = "127.0.0.1";
	int port = 8000;
	Socket s;
	public Sender() throws UnknownHostException, IOException {
		s = new Socket(server ,port);

	}
	
	public void send() throws IOException {
		OutputStream out = s.getOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);
	}
	public static void checker(Document doc) throws IOException {
		XMLOutputter x = new XMLOutputter();
		x.setFormat(Format.getPrettyFormat());
		x.output(doc,new FileWriter("test.XML"));
	}
		
	
}
