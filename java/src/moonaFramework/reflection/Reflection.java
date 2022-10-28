package moonaFramework.reflection;

import moonaFramework.Moona;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;

public abstract class Reflection<T> implements Serial {

	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public final int nature() {
		return Natural.REFLECTION;
	}
	
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
