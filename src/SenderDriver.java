import Sender.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jdom2.*;

public class SenderDriver {
	public static void main(String[] args) {
		
		Sender s;
		try {
			Object a = new primitiveClass();
			primitiveArrayClass b = new primitiveArrayClass();
			collectionClass c = new collectionClass();
			c.add(b);
			objectClass o = new objectClass();
			o.setObj1(b);
			o.setObj2(a);
			Document doc = new Serializer().serialize(o);
			Sender.checker(doc);
		} 
		catch(Exception e){
			e.printStackTrace();
		}
	
		
		
	}
	
}
