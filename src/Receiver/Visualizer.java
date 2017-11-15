package Receiver;

public class Visualizer {
	public void visualize(Object obj) throws IllegalArgumentException, IllegalAccessException, Exception {
		Inspector inspector =new Inspector();
		inspector.inspect(obj,true);
		
	}
}
