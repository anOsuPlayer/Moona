package moonaFramework.util.collection;

public class Array<E> {
	
	private int length;
	public int length() {
		return length;
	}
	
	private static int generator = 0;
	private final int id;
	
	private native void generate(int lenght);
	
	private native void erase(int arr);
	
	protected void finalize() {
		this.erase(id);
	}
	
	private native void set(E element, int at, int arr);
	
	public void set(int at, E element) {
		set(element, at, id);
	}
	
	private native E get(int at, int arr);
	
	public E get(int at) {
		return get(at, id);
	}
	
	public static native void clearAll();
	
	public Array(int lenght) {
		this.length = lenght;
		this.id = ++generator;
		generate(lenght);
	}
	public Array() {
		this(0);
	}
}
