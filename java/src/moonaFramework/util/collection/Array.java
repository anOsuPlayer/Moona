package moonaFramework.util.collection;

public class Array<E> {
	
	private int length;
	
	private native void generate(int lenght);
	
	public Array(int lenght) {
		this.length = lenght;
		generate(lenght);
	}
	public Array() {
		this(0);
	}
}
