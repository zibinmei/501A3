package Sender;
import java.util.*;
public class ObjectCreator {
	Scanner reader = new Scanner(System.in);
	private Object Obj;
	public ObjectCreator() {
		
	}
	
	private void MainMenu() {
		System.out.println("================================================");
		System.out.println("1) Create object with only primitives");
		System.out.println("2) Create object with reference to other objects");
		System.out.println("3) Create object with array of primitive");
		System.out.println("4) Create object with array of references");
		System.out.println("5) Create object with java's collection classes");
		System.out.println("================================================");

	}
	private String getInput() {
		System.out.print("> ");
		String n = reader.nextLine();
		return n.trim();
	}
	
	public void displayUI() {
		while (true) {
			MainMenu();
			String userin = getInput();
			if (userin.equals("1")) {
				Obj = createPrimitives();
				break;
			}
			else if(userin.equals("2")) {
				Obj = createReference();
				break;
			}		
			else if(userin.equals("3")) {
				Obj = createArrayPrimitives();
				break;
			}		
			else if(userin.equals("4")) {
				Obj = createArrayReferences();
				break;
			}		
			else if(userin.equals("5")) {
				Obj=createCollection();
				break;
			}
			else {
				System.out.println("!!!invalid selection!!!");
			}
		}
	}
	
	private Object createPrimitives() {
		primitiveClass pObj=new primitiveClass();
		System.out.println(">>>Object with only primitive created");
		System.out.println(">>>Fields: int, char, float");
		System.out.println(">>>Default: 0, a, 0.0");
		System.out.println(">>>Do you want to set them? (y/n)");
		String userinput = getInput();
		if (userinput.equals("y")) {
			while(true) {
				System.out.println(">>>enter new values in following format");
				System.out.println(">>>int,char,float");
				String[] values = getInput().split(",");
				if (values.length == 3) {
					try {
						int i = Integer.parseInt(values[0]);
						char c =values[1].charAt(0);
						float f =Float.parseFloat(values[2]); 
						pObj.setInt1(i);
						pObj.setChar1(c);
						pObj.setFloat1(f);
						break;
					}
					catch(Exception e) {
						System.out.print("!!!wrong format!!!");
					}
				}
				
			}
		}
		else;
		return pObj;
	}
	private Object createReference() {
		objectClass rObj = new objectClass();
		System.out.println(">>>Object with references created");
		System.out.println(">>>Fields: Object,Object");
		System.out.println(">>>Default: Object with 3 primitives");
		System.out.println(">>>Do you want to set them? (y/n)");
		String userinput = getInput();
		if (userinput.equals("y")) {
			while(true) {
				System.out.println(">>>setting Object 1");
				System.out.println(">>>enter new values in following format");
				System.out.println(">>>int,char,float");
				String[] values = getInput().split(",");
				if (values.length == 3) {
					try {
						int i = Integer.parseInt(values[0]);
						char c =values[1].charAt(0);
						float f =Float.parseFloat(values[2]); 
						rObj.getObj1().setInt1(i);
						rObj.getObj1().setChar1(c);
						rObj.getObj1().setFloat1(f);
						break;
					}
					catch(Exception e) {
						System.out.print("!!!wrong format!!!");
					}
				}
			}
			while(true) {
				System.out.println(">>>setting Object 2");
				System.out.println(">>>enter new values in following format");
				System.out.println(">>>int,char,float");
				String[] values = getInput().split(",");
				if (values.length == 3) {
					try {
						int i = Integer.parseInt(values[0]);
						char c =values[1].charAt(0);
						float f =Float.parseFloat(values[2]); 
						rObj.getObj2().setInt1(i);
						rObj.getObj2().setChar1(c);
						rObj.getObj2().setFloat1(f);
						break;
					}
					catch(Exception e) {
						System.out.print("!!!wrong format!!!");
					}
				}
			}
			
		}else;
			
		return rObj;
	}
	private Object createArrayPrimitives() {
		primitiveArrayClass aObj = new primitiveArrayClass();
		System.out.println(">>>Object with int array created");
		System.out.println(">>>Fields: int[]");
		System.out.println(">>>Default: {1,2,3}");
		System.out.println(">>>Do you want to set them? (y/n)");
		String userinput = getInput();
		if (userinput.equals("y")) {
			while(true) {
				System.out.println(">>>setting int[]");
				System.out.println(">>>enter new values in following format");
				System.out.println(">>>int,int,int,....,int");
				String[] values = getInput().split(",");
				try {
					
					int[] intArr = new int[values.length];
					for (int i = 0; i< values.length;i++) {
						intArr[i] = Integer.parseInt(values[i]);
					}
					aObj.setInArray(intArr);
					break;
				}
				catch(Exception e) {
					System.out.print("!!!wrong format!!!");
				}
			
			}
		}else;
		return aObj;
	}
	private Object createArrayReferences() {
		objectArrayClass raObj = new objectArrayClass();
		return raObj;
	}
	private Object createCollection() {
		collectionClass cObj = new collectionClass();
		
		return cObj;
	}

	public Object getObject() {
		return Obj;
	}

	
}

