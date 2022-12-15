package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.util.functional.Property;

public abstract class AbstractReflection<T> implements Reflection<T> {

	private final long id;
	public @Override final long id() {
		return this.id;
	}
	
	protected T value;
	
	public @Override abstract void reflect();
	
	public @Override final T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	public AbstractReflection() {
		this.value = null;
		this.id = Moona.generateID();
	}
}
