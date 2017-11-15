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
	
	public org.jdom2.Document serialize(Object obj) throws IllegalArgumentException, IllegalAccessException{
		root = new Element("serialized");
		addIdentity(obj);
		addObject(obj);
		Document doc = new Document(root);
		return doc;
	}
		
//use to add a node that is object	
	private void addObject(Object obj) throws IllegalArgumentException, IllegalAccessException {
//		add new object 
		Class classObject = obj.getClass();
		Element objectnode=root.addContent("object");
		objectnode.setAttribute("class" , obj.getClass().getName());
		objectnode.setAttribute("id",map.get(obj).toString());
		Field[] fields= classObject.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			Element fieldnode=objectnode.addContent("field");
			fieldnode.setAttribute("name",f.getName().toString());
			fieldnode.setAttribute("declaringclass", f.getDeclaringClass().toString());
			Object fieldObj = f.get(obj);
//			check the type of field
			if (fieldObj.getClass().isPrimitive()) {
				fieldnode.addContent("value").setText(fieldObj.toString());
			}
			else if(fieldObj.getClass().isArray()) {
				if (map.containsKey(fieldObj)) 
					fieldnode.addContent("reference").setText(map.get(fieldObj).toString());
		
				else {
					String id = addIdentity(fieldObj);
					fieldnode.addContent("reference").setText(id);
					addArrayObject(fieldObj);
					
				}
			}
			else if (fieldObj.getClass().is){
				if (map.containsKey(fieldObj))
					fieldnode.addContent("reference").setText(map.get(fieldObj).toString());
				else {
					String id = addIdentity(fieldObj);
					fieldnode.addContent("reference").setText(id);
					addObject(fieldObj);
				}
			}
		}

		
	}
//	use to add node that is array object
	private void addArrayObject(Object arr) throws IllegalArgumentException, IllegalAccessException {
		Element objectnode =root.addContent("object");
		
//		attributes
		objectnode.setAttribute("class",arr.getClass().getName());
		objectnode.setAttribute("id",map.get(arr).toString());
		objectnode.setAttribute("length",Integer.toString(Array.getLength(arr)));
		
//		contents
		if(arr.getClass().getComponentType().isPrimitive()){
			for (int i = 0; i< Array.getLength(arr);i++) {
				Object arr_ele= Array.get(arr, i);
				objectnode.addContent("value").setText(arr_ele.toString());
			}
			
		}
		
		else {
			for (int i = 0; i< Array.getLength(arr);i++) {
				Object arr_ele= Array.get(arr, i);
				if (map.containsKey(arr_ele)) {
					String objid = map.get(arr_ele).toString();
					objectnode.addContent("reference").setText(objid);
				}
				else {
					String id = addIdentity(arr_ele);
					objectnode.addContent("reference").setText(id);
					addObject(arr_ele);
				}
				
				
			}
			
			
		}
	}
//	a helper for adding id to object
	private String addIdentity(Object obj) {
		int id =newID; 
		map.put(obj, id);
		newID++;
		return Integer.toString(id);	
	}

}
