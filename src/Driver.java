import Sender.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jdom2.*;

public class Driver {
	public static void main(String[] args) {
		
		Sender s;
		try {
			primitiveClass a = new primitiveClass();
			Document doc = new Serializer().serialize(a);
			Sender.checker(doc);
		} 
		catch(Exception e){
			e.printStackTrace();
		}
	
		
		
	}
	
}
