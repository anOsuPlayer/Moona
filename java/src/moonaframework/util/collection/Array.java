package moonaframework.util.collection;

public class Array<E> {
	
	private int length;
	public int length() {
		return length;
	}
	
	private static int generator = 0;
	
	private final int id;
	
	private native void generate(int lenght);
	
	private native void set(E element, int at, int arr, int lenght);
	
	public void set(int at, E element) throws ArrayIndexOutOfBoundsException {
		if (at >= length || at < 0) {
			throw new ArrayIndexOutOfBoundsException("Index " + at + " out of bounds for length " + length + ".");
		}
		set(element, at, id, length);
	}
	
	private native E get(int at, int arr, int length);
	
	public E get(int at) throws ArrayIndexOutOfBoundsException {
		if (at >= length || at < 0) {
			throw new ArrayIndexOutOfBoundsException("Index " + at + " out of bounds for length " + length + ".");
		}
		return get(at, id, length);
	}
	
	public static native void clearAll();
	
	public Array(int lenght) {
		this.length = lenght;
		this.id = generator++;
		generate(lenght);
	}
	public Array() {
		this(0);
	}
}
