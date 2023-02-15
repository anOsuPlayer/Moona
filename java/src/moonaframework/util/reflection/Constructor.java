package moonaframework.util.reflection;

import java.util.Arrays;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.ConstructorProperty;

public final class Constructor extends Reference<java.lang.reflect.Constructor<?>> {
	
	private final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return this.clazz;
	}
	public @Override Class<?> getTarget() {
		return this.clazz;
	}
	
	private final Class<?>[] args;
	
	public Class<?>[] getParameterTypes() {
		return this.args;
	}
	
	private final boolean isDeclared;
	
	public final boolean isDeclared() {
		return this.isDeclared;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Constructor con) ?
				this.clazz.equals(con.clazz) && Arrays.equals(this.args, con.args)
				: false;
	}
	
	public @Override String toString() {
		String params = "";
		for (Class<?> param : args) {
			params += param.getSimpleName() + ", ";
		}
		params = (params.length() != 0) ? params.substring(0, params.length()-2) : params;
		
		return (clazz == null) ? "Non-generated Reflection" : "Constructor " + clazz.getSimpleName() + "("
				+ params + ")" + " in class " + clazz.getName();
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			if (isDeclared) {
				super.value = clazz.getDeclaredConstructor(args);
			}
			else {
				super.value = clazz.getConstructor(args);
			}
		}
		catch (NoSuchMethodException nsme) {
			throw new UndefinedReflectionException("No Method References could be generated from the given"
					+ " arguments.", nsme);
		}
	}
	
	private ConstructorProperty cp;
	
	public @Override ConstructorProperty derive() {
		if (cp == null) {
			cp = new ConstructorProperty(this);
		}
		return cp;
	}
	
	public Constructor(Class<?> clazz, boolean isDeclared, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot build a Constructor Reference over a null class.");
		}
		this.isDeclared = isDeclared;
		this.clazz = clazz; this.args = (args == null) ? Mirror.NO_ARGS : args;
		
		super.mirrorInteraction();
	}
	public Constructor(Class<?> clazz, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		this(clazz, false, args);
	}
	public Constructor(Class<?> clazz) {
		this(clazz, false, Mirror.NO_ARGS);
	}
	public Constructor(Class<?> clazz, boolean isDeclared) {
		this(clazz, isDeclared, Mirror.NO_ARGS);
	}
	
	public Constructor(java.lang.reflect.Constructor<?> constr) throws NullArgumentException {
		if (constr == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Constructor.");
		}
		super.value = constr;
		this.isDeclared = false;
		this.clazz = constr.getDeclaringClass();
		this.args = (constr.getParameterTypes().length == 0) ? Mirror.NO_ARGS : constr.getParameterTypes();
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}