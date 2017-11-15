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
		Element objectnode= new Element("object");
		this.root.addContent(objectnode);
		objectnode.setAttribute("class" , obj.getClass().getName());
		objectnode.setAttribute("id",map.get(obj).toString());
		Field[] fields= classObject.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			Element fieldnode= new Element("field");
			objectnode.addContent(fieldnode);
			fieldnode.setAttribute("name",f.getName().toString());
			fieldnode.setAttribute("declaringclass", f.getDeclaringClass().getName());
			Object fieldObj = f.get(obj);
//			check the type of field
			if (f.getType().isPrimitive()) {
				Element value = new Element("value");
				value.setText(fieldObj.toString());
				fieldnode.addContent(value);
			}
			else if(f.getType().isArray()) {
				if (map.containsKey(fieldObj)) { 
					Element ref = new Element("reference");
					ref.setText(map.get(fieldObj).toString());
					fieldnode.addContent(ref);
				}
				else {
					Element ref = new Element("reference");
					String id = addIdentity(fieldObj);
					ref.setText(id);
					fieldnode.addContent(ref);
					addArrayObject(fieldObj);
				}
			}
			else{
				if (map.containsKey(fieldObj)) {
					Element ref = new Element("reference");
					ref.setText(map.get(fieldObj).toString());
					fieldnode.addContent(ref);
				}
				else {
					Element ref = new Element("reference");
					String id = addIdentity(fieldObj);
					ref.setText(id);
					fieldnode.addContent(ref);
					addObject(fieldObj);
				}
			}
		}

		
	}
//	use to add node that is array object
	private void addArrayObject(Object arr) throws IllegalArgumentException, IllegalAccessException {
		Element objectnode = new Element("object");
		this.root.addContent(objectnode);
//		attributes
		objectnode.setAttribute("class",arr.getClass().getName());
		objectnode.setAttribute("id",map.get(arr).toString());
		objectnode.setAttribute("length",Integer.toString(Array.getLength(arr)));
		
//		contents
		if(arr.getClass().getComponentType().isPrimitive()){
			for (int i = 0; i< Array.getLength(arr);i++) {
				Object arr_ele= Array.get(arr, i);
				Element value = new Element("value");
				value.setText(arr_ele.toString());
				objectnode.addContent(value);
			}
			
		}
		
		else {
			for (int i = 0; i< Array.getLength(arr);i++) {
				Object arr_ele= Array.get(arr, i);
				if (map.containsKey(arr_ele)) {
					String objid = map.get(arr_ele).toString();
					Element ref = new Element("ref");
					objectnode.addContent(ref);
					ref.setText(objid);
				}
				else {
					String id = addIdentity(arr_ele);
					Element ref = new Element("reference");
					objectnode.addContent(ref);
					ref.setText(id);
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
