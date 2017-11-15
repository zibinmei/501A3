import Sender.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jdom2.*;

public class SenderDriver {
	public static void main(String[] args) {
		
		
		try {
			Sender s = new Sender("localhost",9999);
			ObjectCreator creator = new ObjectCreator();
			creator.displayUI();
			Object obj = creator.getObject();
			Document doc = new Serializer().serialize(obj);
			s.send(doc);
		} 
		catch(Exception e){
			e.printStackTrace();
		}
	
		
		
	}
	
}
