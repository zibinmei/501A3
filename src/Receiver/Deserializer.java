package Receiver;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.lang.reflect.Field;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserializer {
	private Object MainObject;
	private Element root;
	private List<Element> allObjects;
	
	
//use to deserialize
	public Object deserialize(Document doc) throws Exception{
		root = doc.getRootElement();
		allObjects = root.getChildren();
		Element node0 = allObjects.get(0);
		MainObject = objCreator(node0);

		return MainObject;
	}
	//create object from a object node element
	private Object objCreator(Element objectnode) throws Exception {
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
		//if the field is primitive set the field
			if (f.getType().isPrimitive()) {
				String value = fieldNode.getChildText("value");
				if (Modifier.isFinal(f.getModifiers())==false){
					if (value.equals("null")==false)
						f.set(obj, getPrimitive(f.getType(),value));
				}
			}
			//if the field is object or array, recursive created new object then set the reference to it 
			else {
				String id = fieldNode.getChildText("reference");
				Object fieldObj;

				if (id.equals("null")) {
					fieldObj = null;
				}
				else if(getNodebyID(id).getAttributes().size() == 2) {
					fieldObj = objCreator(getNodebyID(id));
				}
				else {
					fieldObj = arrCreator(getNodebyID(id));
						
				}
				if (Modifier.isFinal(f.getModifiers())==false)	
					f.set(obj,fieldObj);
			}
			
			
		}
		return obj;
	}
//use to deserialize array object
	private Object arrCreator(Element arraynode) throws Exception{
	//create object instance	
		String classname =arraynode.getAttributeValue("class");
		int arraysize =new Integer(arraynode.getAttributeValue("length"));
		Class<?> c= Class.forName(classname);
		Object arr =Array.newInstance(c.getComponentType() , arraysize);
		//check array type, different operation for primitive and object
		if (arr.getClass().getComponentType().isPrimitive()) {
			List<Element> valuenodes= arraynode.getChildren();
			for (int i =0;i<arraysize;i++) {
				Element v = valuenodes.get(i);
				String value = v.getText();
				if (value.equals("null")==false)
					Array.set(arr, i, getPrimitive(arr.getClass().getComponentType(),value));
			}
			
		}
//		check array type, if object, create it and pass in the reference
		else {
			List<Element> refnodes= arraynode.getChildren();
			for (int i =0;i<arraysize;i++) {
				Element r = refnodes.get(i);
				String value = r.getText();
				if (value.equals("null"))
					Array.set(arr, i, null);
				else
					Array.set(arr, i, objCreator(getNodebyID(value)));
			}
		}
	
		return arr;
	}
	//use to get the object node by its id
	private Element getNodebyID(String id) {
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
	//convert string into different primitive
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
		
		return value;

	}
	
	
	public Object getMainObject() {
		return MainObject;
	}

	public void setMainObject(Object mainObject) {
		MainObject = mainObject;
	}
}
