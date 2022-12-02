package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Serial;
import moonaframework.util.functional.Property;

public abstract class Reflection<T> implements Property<T>, Serial {

	private final long id;
	public @Override final long id() {
		return this.id;
	}
	public @Override int nature() {
		return Moona.REFLECTION;
	}
	
	protected T value;
	
	public abstract Object getTarget();
	
	public abstract void reflect();
	
	public @Override final T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	public Reflection() {
		this.value = null;
		this.id = Moona.generateID();
	}
}
