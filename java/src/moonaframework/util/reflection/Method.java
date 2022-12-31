package moonaframework.util.reflection;

import java.util.Arrays;

import moonaframework.base.Moona;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.MethodProperty;

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
	
	public @Override boolean equals(Object o) {
		return (o instanceof Method method) ?
				this.name.equals(method.name) && this.clazz.equals(method.clazz)
				&& Arrays.equals(this.args, method.args)
				: false;
	}
	
	public @Override String toString() {
		return (name == null) ? "Non-generated Reflection" : "Method " + name + " in class "
				+ clazz.getSimpleName() + ", "
				+ ((args.equals(Mirror.NO_ARGS)) ? "no parameters" : " parameters: " + Arrays.toString(args));
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			super.value = clazz.getDeclaredMethod(name, args);
		}
		catch (NoSuchMethodException nsme) {
			throw new UndefinedReflectionException("No Method References could be generated from the given"
					+ " arguments.", nsme);
		}
	}
	
	private MethodProperty mp;
	
	public @Override MethodProperty derive() {
		if (mp == null) {
			mp = new MethodProperty(this);
		}
		return mp;
	}
	
	public Method(Class<?> clazz, String name, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null class or a null"
					+ " method name.");
		}
		this.clazz = clazz; this.name = name; this.args = (args == null) ? Mirror.NO_ARGS : args;
		if (Moona.autoReflections.evaluate()) {
			derive();
		}
	}
	public Method(Class<?> clazz, String name) {
		this(clazz, name, Mirror.NO_ARGS);
	}
	
	public Method(java.lang.reflect.Method method) throws NullArgumentException {
		if (method == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Method.");
		}
		super.value = method;
		this.clazz = method.getDeclaringClass(); this.name = method.getName(); this.args = method.getParameterTypes();
	}
}
