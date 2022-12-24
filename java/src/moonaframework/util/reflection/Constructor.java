package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;

public final class Constructor extends Reference<java.lang.reflect.Constructor<?>> {
	
	private final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return this.clazz;
	}
	
	private final Class<?>[] args;
	
	public Class<?>[] getParameterTypes() {
		return this.args;
	}
	
	public @Override final String toString() {
		return evaluate().getName();
	}
	
	public @Override final void reflect() throws UnresolvedReflectionException {
		for (java.lang.reflect.Constructor<?> m : clazz.getDeclaredConstructors()) {
			if ((args.equals(Mirror.NO_ARGS)) ? m.getParameterTypes().length == 0 : m.getParameterTypes().equals(args)) {
				super.value = m;
				return;
			}
		}
		throw new UnresolvedReflectionException("No Constructor References could be generated from the given arguments.");
	}
	
	public Constructor(Class<?> clazz, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot build a Constructor Reference with a null class.");
		}
		this.clazz = clazz; this.args = args;
	}
	public Constructor(Class<?> clazz) {
		this(clazz, Mirror.NO_ARGS);
	}
}