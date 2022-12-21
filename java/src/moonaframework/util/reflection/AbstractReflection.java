package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Nature;

public abstract class AbstractReflection<T> implements Reflection<T> {

	private final long id;
	
	public @Override final long id() {
		return this.id;
	}
	public @Override final Nature nature() {
		return Nature.REFLECTION;
	}
	
	protected T value;
	
	public @Override abstract Object getTarget();
	
	public @Override abstract void reflect();
	
	public @Override T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	protected AbstractReflection() {
		this.id = Moona.generateID();
	}
}
