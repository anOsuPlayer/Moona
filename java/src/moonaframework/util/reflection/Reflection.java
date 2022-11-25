package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Natural;
import moonaframework.base.Serial;
import moonaframework.util.function.Property;

public abstract class Reflection<T> implements Property<T>, Serial {

	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public int nature() {
		return Natural.REFLECTION;
	}
	
	protected T value;
	
	public abstract Object getTarget();
	
	public abstract void reflect();
	
	public final T evaluate() {
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
