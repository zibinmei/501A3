package Sender;
import java.util.*;
public class ObjectCreator {
	Scanner reader = new Scanner(System.in);
	
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
	private int getInput() {
		System.out.print("Select:  ");
		int n = reader.nextInt(); 
		return n;
	}
	
	public void displayUI() {
		MainMenu();
		getInput();
	}
	
	private void createPrimitives() {
		
	}
	private void createReference() {
		
	}
	private void createArrayPrimitives() {
		
	}
	private void createArrayReferences() {
		
	}
	private void createCollection() {
		
	}
	
	
}

