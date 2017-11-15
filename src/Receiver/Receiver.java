package Receiver;

import java.io.DataInputStream;
import java.io.IOException;
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
		s_socket.setSoTimeout(10000);

	}
	
	public Document run() throws IOException, JDOMException {
		
		Socket conn = s_socket.accept();
		DataInputStream data = new DataInputStream(conn.getInputStream());
		SAXBuilder builder= new SAXBuilder();
		Document doc = builder.build(data);
		
		return doc;
	}
	
}
