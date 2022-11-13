package moonaFramework.util.reflection;

import moonaFramework.base.Moona;
import moonaFramework.base.Natural;
import moonaFramework.base.Serial;

public abstract class Reflection<T> implements Serial {

	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public int nature() {
		return Natural.REFLECTION;
	}
	
	public abstract Object getTarget();
	
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
		this.id = Moona.generateID();
	}
}
