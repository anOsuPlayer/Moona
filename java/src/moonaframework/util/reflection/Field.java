package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;

public final class Field extends Reference<java.lang.reflect.Field> {
	
	private final Class<?> clazz;
	
	private final String name;
	
	public @Override final void reflect() throws UnresolvedReflectionException {
		for (java.lang.reflect.Field m : clazz.getDeclaredFields()) {
			if (m.getName().equals(name)) {
				super.value = m;
				return;
			}
		}
		throw new UnresolvedReflectionException("No Field References could be generated from the given arguments.");
	}
	
	public Field(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Field Reference with a null class or a null"
					+ " field name.");
		}
		this.clazz = clazz; this.name = name;
	}
}
