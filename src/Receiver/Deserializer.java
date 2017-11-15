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
//create object instance		
		String classname =objectnode.getAttribute("class").getValue();
		Class<?> c= Class.forName(classname);
		Constructor<?> cons = c.getConstructor();
		Object obj = cons.newInstance();
//		System.out.println(obj.getClass().getName());
		List<Element> allfields= objectnode.getChildren();
		for (int i = 0; i< allfields.size();i++) {
			//get the data from xml
			Element fieldNode = allfields.get(i);
			Field[] fields =obj.getClass().getDeclaredFields();
			Field f = fields[i];
			f.setAccessible(true);
			String name =fieldNode.getAttribute("name").toString();
			String declaringclass = fieldNode.getAttribute("declaringclass").toString();
			String value = fieldNode.getChildText("value");
			System.out.println(value);
			f.set(obj, f.getType().cast(value));
			System.out.println(f.get(obj).toString());

			
			
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
