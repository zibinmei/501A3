package Receiver;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.lang.reflect.Field;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserializer {
	private Object MainObject;
	private Element root;
	private List<Element> allObjects;
	
	
	
	public Object deserialize(Document doc) throws Exception{
		root = doc.getRootElement();
		allObjects = root.getChildren();
		Element node0 = allObjects.get(0);
		MainObject = objCreator(node0);

		return MainObject;
	}
	private Object objCreator(Element objectnode) throws Exception {
		System.out.println("obj maker");
//create object instance		
		String classname =objectnode.getAttributeValue("class");
		Class<?> c= Class.forName(classname);
		Constructor<?> cons = c.getConstructor();
		Object obj = cons.newInstance();
		List<Element> allfields= objectnode.getChildren();
		for (int i = 0; i< allfields.size();i++) {
			//get the data from xml
			Element fieldNode = allfields.get(i);
			Field[] fields =obj.getClass().getDeclaredFields();
			Field f = fields[i];
			f.setAccessible(true);
			if (f.getType().isPrimitive()) {
				String value = fieldNode.getChildText("value");
				f.set(obj, getPrimitive(f.getType(),value));

			}
			else {
				String id = fieldNode.getChildText("reference");
				Element node = getNodebyID(id);
				Object fieldObj;
				if (node.getAttributes().size() == 2) {
					fieldObj = objCreator(node);
				}
				else {
					fieldObj = arrCreator(node);
						
				}
				f.set(obj,fieldObj);
			}
			
			
		}
		return obj;
	}
	
	private Object[] arrCreator(Element arraynode) throws Exception{
	//create object instance	
		System.out.println("array maker");
		String classname =arraynode.getAttributeValue("class");
		int arraysize =new Integer(arraynode.getAttributeValue("length"));
		Class<?> c= Class.forName(classname);
		Object arr =Array.newInstance(c.getComponentType() , arraysize);
		if (arr.getClass().getComponentType().isPrimitive()) {
			List<Element> valuenodes= arraynode.getChildren();
			for (int i =0;i<arraysize;i++) {
				Element v = valuenodes.get(i);
				String value = v.getText();
				Array.set(arr, i, getPrimitive(arr.getClass().getComponentType(),value));
			}
			
		}
		else {
			List<Element> refnodes= arraynode.getChildren();
			for (int i =0;i<arraysize;i++) {
				Element r = refnodes.get(i);
				String value = r.getText();
				Array.set(arr, i, objCreator(getNodebyID(value)));
			}
		}
	
	
		
		return null;
	}
	private Element getNodebyID(String id) {
		System.out.println("id: "+id);
		int index = Integer.valueOf(id);
		Element objectNode = allObjects.get(index);
		if (objectNode.getAttributeValue("id") != id) {
			for (int i = 0 ; i < allObjects.size(); i++) {
				if (allObjects.get(i).getAttributeValue("id") == id)
						objectNode = allObjects.get(i);
			}
			
		}
		return objectNode;
	}

	private Object getPrimitive(Class c,String value) {
		if (c.equals(int.class)) {
			return new Integer(value);
		}
		else if (c.equals(boolean.class)) {
			return new Boolean(value);
		}
		else if (c.equals(char.class)) {
			return new Character(value.charAt(0));
		}
		else if (c.equals(float.class)) {
			return new Float(value);
		}
		else if (c.equals(double.class)) {
			return new Double(value);
		}
		else if(c.equals(long.class)) {
			return new Long(value);			
		}
		else if(c.equals(byte.class)) {
			return new Byte(value);			
		}
		else if (value == "null") {
			return "null";
		}
		return value;

	}
	public Object getMainObject() {
		return MainObject;
	}



	public void setMainObject(Object mainObject) {
		MainObject = mainObject;
	}
}
