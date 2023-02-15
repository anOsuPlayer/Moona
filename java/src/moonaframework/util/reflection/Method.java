package moonaframework.util.reflection;

import java.util.Arrays;

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
	
	public Class<?> getReturnType() {
		return super.value.getReturnType();
	}
	
	private final boolean isDeclared;
	
	public final boolean isDeclared() {
		return this.isDeclared;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Method method) ?
				this.name.equals(method.name) && this.clazz.equals(method.clazz)
				&& Arrays.equals(this.args, method.args)
				: false;
	}
	
	public @Override String toString() {
		String params = "";
		for (Class<?> param : args) {
			params += param.getSimpleName() + ", ";
		}
		params = (params.length() != 0) ? params.substring(0, params.length()-2) : params;
		
		return (name == null) ? "Non-generated Reflection" : "Method " + name + "(" + params
				+ ")" + " in class " + clazz.getName();
	}
	
	public @Override Class<?> getTarget() {
		return this.clazz;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			if (isDeclared) {
				super.value = clazz.getDeclaredMethod(name, args);
			}
			else {
				super.value = clazz.getMethod(name, args);
			}
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
	
	public Method(Class<?> clazz, String name, boolean isDeclared, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null class or a null"
					+ " method name.");
		}
		this.isDeclared = isDeclared;
		this.clazz = clazz; this.name = name; this.args = (args == null) ? Mirror.NO_ARGS : args;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
	public Method(Class<?> clazz, String name, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		this(clazz, name, false, Mirror.NO_ARGS);
	}
	public Method(Class<?> clazz, String name) {
		this(clazz, name, false, Mirror.NO_ARGS);
	}
	public Method(Class<?> clazz, String name, boolean isDeclared) {
		this(clazz, name, isDeclared, Mirror.NO_ARGS);
	}
	
	public Method(java.lang.reflect.Method method) throws NullArgumentException {
		if (method == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Method.");
		}
		super.value = method;
		this.isDeclared = false;
		this.clazz = method.getDeclaringClass(); this.name = method.getName(); this.args = method.getParameterTypes();

		super.mirrorInteraction(); super.valueExtraction();
	}
}
