package Receiver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
public class Receiver {

	private ServerSocket s_socket;
	
	public Receiver(int port) throws IOException {

		s_socket = new ServerSocket(port);
		s_socket.setSoTimeout(100000);

	}
	
	public Document run() throws IOException, JDOMException {
		
		Socket conn = s_socket.accept();
		System.out.println("Connected to: "+ conn);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		SAXBuilder builder= new SAXBuilder();
		Document doc = builder.build(in);
		return doc;
	}
	
}
