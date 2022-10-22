package moonaFramework.reflection;

public non-sealed abstract class Reflection<T> implements Property<T> {

	private T value;
	
	public abstract void reflect();
	
	public final T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	public Reflection() {
		this.value = null;
	}
}
