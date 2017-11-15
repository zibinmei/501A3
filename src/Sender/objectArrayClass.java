package Sender;

public class objectArrayClass {
	private Object[] objArray =new Object[3];

	public objectArrayClass(){
		objArray[0]=new primitiveClass();
		objArray[1]=new objectClass();
		objArray[2]=new primitiveArrayClass();
	}
	
	public Object[] getObjArray() {
		return objArray;
	}

	public void setObjArray(Object[] objArray) {
		this.objArray = objArray;
	}
	
	
}
