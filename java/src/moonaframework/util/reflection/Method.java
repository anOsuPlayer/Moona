package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;

public final class Method extends Reference<java.lang.reflect.Method> {
	
	private final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return this.clazz;
	}
	
	private final String name;
	
	public String getName() {
		return this.name;
	}
	
	private final Class<?>[] args;
	
	public Class<?>[] getParameterTypes() {
		return this.args;
	}
	
	public @Override final String toString() {
		return evaluate().getName();
	}
	
	public @Override final void reflect() throws UnresolvedReflectionException {
		for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : m.getParameterTypes().equals(args)) {
				super.value = m;
				return;
			}
		}
		throw new UnresolvedReflectionException("No Method References could be generated from the given arguments.");
	}
	
	public Method(Class<?> clazz, String name, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Method Reference with a null class or a null"
					+ " method name.");
		}
		this.clazz = clazz; this.name = name; this.args = args;
	}
	public Method(Class<?> clazz, String name) {
		this(clazz, name, Mirror.NO_ARGS);
	}
	
	protected Method(java.lang.reflect.Method method) throws NullArgumentException {
		if (method == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Method.");
		}
		super.value = method;
		this.clazz = method.getDeclaringClass(); this.name = method.getName(); this.args = method.getParameterTypes();
	}
}
