package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;

public final class Type extends Reference<Class<?>> {
	
	private final Class<?> clazz;
	
	public @Override final void reflect() {
		super.value = this.clazz;
	}
	
	public Type(Class<?> clazz) throws NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot generate a Type Reference over a null class.");
		}
		this.clazz = clazz;
	}
}