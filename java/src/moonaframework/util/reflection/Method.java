package moonaframework.util.reflection;

import moonaframework.base.Mirror;
import moonaframework.util.exceptions.NullArgumentException;

public class Method extends AbstractReflection<java.lang.reflect.Method> {
	
	public @Override java.lang.reflect.Method getTarget() {
		return super.value;
	}
	
	private final Class<?> clazz;
	
	private final String name;
	
	private final Class<?>[] args;
	
	public final void reflect() throws UnresolvedReflectionException {
		for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : args.equals(m.getParameterTypes())) {
				super.value = m;
				return;
			}
		}
		throw new UnresolvedReflectionException("There is no method " + name + " in class " + clazz.getName() + ".");
	}
	
	public Method(Class<?> clazz, String name, Class<?>[] args) throws NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Declaring class and method's name cannot be null.");
		}
		this.clazz = clazz; this.name = name; this.args = (args == null) ? Mirror.NO_ARGS : args;
	}
	public Method(Class<?> clazz, String name) throws NullArgumentException {
		this(clazz, name, Mirror.NO_ARGS);
	}
}
