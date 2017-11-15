package Receiver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.lang.reflect.Field;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserializer {
	private Object MainObject;
	
	
	
	public Object deserialize(Document doc) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Element root = doc.getRootElement();
		List<Element> allObjects = root.getChildren();
		for (Element objectnode: allObjects) {
			String id = objectnode.getAttribute("id").toString();
			Object obj = objCreator(objectnode);
			if (id.equals("0")) {
				MainObject =obj;
			}

			
		}
		
		
		
		return MainObject;
	}
	private Object objCreator(Element objectnode) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String classname =objectnode.getAttribute("class").toString();
		Class<?> c= Class.forName(classname);
		Constructor<?> cons = c.getConstructor();
		Object obj = cons.newInstance();
		List<Element> allfields= objectnode.getChildren();
		for (Element fieldNode: allfields) {
			String name =fieldNode.getAttribute("name").toString();
			String declaringclass = fieldNode.getAttribute("declaringclass").toString();
			String value = fieldNode.getContent().toString();
			obj.getClass().getDeclaredField()
			
		}
		return obj;
	}



	public Object getMainObject() {
		return MainObject;
	}



	public void setMainObject(Object mainObject) {
		MainObject = mainObject;
	}
}
