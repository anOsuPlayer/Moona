package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.beacon.FieldProperty;

public final class Field extends Reference<java.lang.reflect.Field> {
	
	private final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return clazz;
	}
	
	private final String name;
	
	public String getName() {
		return this.name;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Field field) ?
				this.name.equals(field.name) && this.clazz.equals(field.clazz)
				: false;
	}
	
	public @Override String toString() {
		return (name == null) ? "Non-generated Reflection" : "Field " + name + " in class "
				+ clazz.getSimpleName();
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			super.value = clazz.getDeclaredField(name);
		}
		catch (NoSuchFieldException nsme) {
			throw new UndefinedReflectionException("No Method References could be generated from the given"
					+ " arguments.", nsme);
		}
	}
	
	private FieldProperty fp;
	
	public @Override FieldProperty derive() {
		if (fp == null) {
			fp = new FieldProperty(this);
		}
		return fp;
	}
	
	public Field(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Field Reference with a null class or a null"
					+ " field name.");
		}
		this.clazz = clazz; this.name = name;
	}
	
	public Field(java.lang.reflect.Field field) throws NullArgumentException {
		if (field == null) {
			throw new NullArgumentException("Cannot build a Method Reference over a null java.lang.reflect.Method.");
		}
		super.value = field;
		this.clazz = field.getDeclaringClass(); this.name = field.getName();
	}
}
