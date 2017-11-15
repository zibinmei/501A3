package Sender;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
		
		XMLOutputter x = new XMLOutputter();
		x.setFormat(Format.getPrettyFormat());
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		x.output(doc, out);
		s.close();
	}
	public static void checker(Document doc) throws IOException {
		XMLOutputter x = new XMLOutputter();
		x.setFormat(Format.getPrettyFormat());
		x.output(doc,new FileWriter("test.XML"));
	}
		
	
}
