import org.jdom2.Document;
import org.jdom2.Element;

import Receiver.*;


public class ReceiverDriver {
	public static void main(String[] args) {
		

			try {
				Receiver recv = new Receiver(9999);
				Document doc = recv.run();
				Deserializer deserial = new Deserializer();
				Object obj =deserial.deserialize(doc);
				Visualizer vis = new Visualizer();
				vis.visualize(obj);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		
	}
}