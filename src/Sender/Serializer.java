package Sender;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class Serializer {
	private Element root;
	private IdentityHashMap map = new IdentityHashMap();
	private int newID =0;
	public void Serializer() {
		
	}
	
	public org.jdom2.Document serialize(Object obj){
		root = new Element("serialized");
		try {
			addObject(obj);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = new Document(root);
		return doc;
	}
		
	
	private void addObject(Object obj) throws IllegalArgumentException, IllegalAccessException {

		Class classObject = obj.getClass();
		Element objectnode=root.addContent("object").setAttribute("class" , obj.getClass().getName());
		objectnode.setAttribute("id",Integer.toString(newID));
		map.put(newID, obj);
		newID++;
		Field[] fields= classObject.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			Element fieldnode=objectnode.addContent("field");
			fieldnode.setAttribute("name",f.getName().toString());
			fieldnode.setAttribute("declaringclass", f.getDeclaringClass().toString());
			if (f.get(obj).getClass().isPrimitive())
				fieldnode.addContent("value").setText(f.get(obj).toString());
			else if(f.get(obj).getClass().isArray()) {
				fieldnode.addContent("reference").setText(Integer.toString(newID));
				addArrayObject(f.get(obj),newID);
				map.put(newID, f.get(obj));
				newID++;
			}
			else {
				fieldnode.addContent("reference").setText(Integer.toString(newID));
				map.put(newID, f.get(obj));
				newID++;
				addObject(f.get(obj));
			}

		}

		
	}
	
	private void addArrayObject(Object arr,int id) {
		if(arr.getClass().getComponentType().isPrimitive()){
			Element objectnode =root.addContent("object");
			objectnode.setAttribute("class",arr.getClass().getName());
			objectnode.setAttribute("id",Integer.toString(id));
			objectnode.setAttribute("length",Integer.toString(Array.getLength(arr)));
			for (int i = 0; i< Array.getLength(arr);i++) {
				Object arr_ele= Array.get(arr, i);
				objectnode.addContent("value").setText(arr_ele.toString());
			}
			
		}
		else {
			
		}
	}
}
