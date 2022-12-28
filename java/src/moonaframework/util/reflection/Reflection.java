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
	
	public abstract Object getTarget();
	
	public abstract void reflect();
	
	public T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	protected Reflection() {
		this.id = Moona.generateID();
		if (Moona.hasAutoReflections()) {
			Mirror.queue(this);
		}
	}
}
