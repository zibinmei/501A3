package Sender;
import java.util.ArrayList;
public class collectionClass {
	private ArrayList<Object> aList=new ArrayList<Object>();

	public ArrayList getaList() {
		return aList;
	}

	public void setaList(ArrayList a) {
		this.aList = a;
	}
	
	public void add(Object obj) {
		this.aList.add(obj);
		
	}
	public void remove(Object obj) {
		this.aList.remove(obj);
	}

}
