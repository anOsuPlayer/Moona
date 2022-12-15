package moonaframework.util.reflection;

import moonaframework.base.Moona;

public abstract class AbstractReflection<T> implements Reflection<T> {

	private final long id;
	public @Override final long id() {
		return this.id;
	}
	
	protected T value;
	
	public @Override abstract Object getTarget();
	
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
