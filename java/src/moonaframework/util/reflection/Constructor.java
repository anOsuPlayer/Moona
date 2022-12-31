package moonaframework.util.reflection;

import java.util.Arrays;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.beacon.ConstructorProperty;

public final class Constructor extends Reference<java.lang.reflect.Constructor<?>> {
	
	private final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return this.clazz;
	}
	
	private final Class<?>[] args;
	
	public Class<?>[] getParameterTypes() {
		return this.args;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Constructor con) ?
				this.clazz.equals(con.clazz) && Arrays.equals(this.args, con.args)
				: false;
	}
	
	public @Override String toString() {
		return (clazz == null) ? "Non-generated Reflection" : "Constructor of class " + clazz.getSimpleName() + ", "
				+ ((args.equals(Mirror.NO_ARGS)) ? "no parameters" : " parameters: " + Arrays.toString(args));
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			super.value = clazz.getDeclaredConstructor(args);
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
	
	public Constructor(Class<?> clazz, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot build a Constructor Reference over a null class.");
		}
		this.clazz = clazz; this.args = (args == null) ? Mirror.NO_ARGS : args;
	}
	public Constructor(Class<?> clazz) {
		this(clazz, Mirror.NO_ARGS);
	}
	
	public Constructor(java.lang.reflect.Constructor<?> constr) throws NullArgumentException {
		if (constr == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Constructor.");
		}
		super.value = constr;
		this.clazz = constr.getDeclaringClass();
		this.args = (constr.getParameterTypes().length == 0) ? Mirror.NO_ARGS : constr.getParameterTypes();
	}
}