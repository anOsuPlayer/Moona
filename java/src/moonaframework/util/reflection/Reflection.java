package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.base.Serial;

public abstract class Reflection<T> implements Serial {

	private final long id;
	
	public @Override final long id() {
		return this.id;
	}
	public @Override final Nature nature() {
		return Nature.REFLECTION;
	}
	
	protected T value;
	
	protected abstract Object getTarget();
	
	protected abstract void reflect();
	
	protected T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	protected Reflection() {
		this.id = Moona.generateID();
	}
}
