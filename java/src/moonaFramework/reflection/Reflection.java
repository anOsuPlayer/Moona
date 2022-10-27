package moonaFramework.reflection;

import moonaFramework.Moona;
import moonaFramework.essentials.Natural;

public abstract non-sealed class Reflection<T> implements Property<T> {

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
	
	@Override
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
