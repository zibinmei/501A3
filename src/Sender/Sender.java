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

	Socket s;
	public Sender(String host,int port) throws UnknownHostException, IOException {
		s = new Socket(host ,port);

	}
	
	public void send(Document doc) throws IOException {
		OutputStream out = s.getOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);
		XMLOutputter x = new XMLOutputter();
		x.setFormat(Format.getPrettyFormat());
		x.output(doc, dataOut);
		dataOut.flush();
	}
	public static void checker(Document doc) throws IOException {
		XMLOutputter x = new XMLOutputter();
		x.setFormat(Format.getPrettyFormat());
		x.output(doc,new FileWriter("test.XML"));
	}
		
	
}
