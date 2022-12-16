package moonaframework.util.reflection;

import moonaframework.base.Mirror;

public class Method extends AbstractReflection<java.lang.reflect.Method> {
	
	public @Override java.lang.reflect.Method getTarget() {
		return super.value;
	}
	
	private final Class<?> clazz;
	
	private final String name;
	
	private final Class<?>[] args;
	
	public final void reflect() {
		for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : args.equals(m.getParameterTypes())) {
				super.value = m;
				return;
			}
		}
	}
	
	public Method(Class<?> clazz, String name, Class<?>...args) {
		this.clazz = clazz; this.name = name; this.args = args;
	}
	public Method(Class<?> clazz, String name) {
		this(clazz, name, Mirror.NO_ARGS);
	}
}
