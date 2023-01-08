package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;

public final class Enumeration extends Type {

	public @Override boolean equals(Object o) {
		return (o instanceof Enumeration e) ?
				super.value.equals(e.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "Enumeration " + value.getName();
	}
	
	public Enumeration(Class<? extends Enum<?>> clazz) throws NullArgumentException {
		super(clazz);
	}
}
