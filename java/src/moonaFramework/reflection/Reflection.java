package moonaFramework.reflection;

public abstract non-sealed class Reflection<T> implements Property<T> {

	protected T value;
	
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
