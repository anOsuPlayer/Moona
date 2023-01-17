package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.FieldProperty;

public sealed class Field extends Reference<java.lang.reflect.Field> permits EnumField {
	
	protected final Class<?> clazz;
	
	public Class<?> getDeclaringClass() {
		return clazz;
	}
	
	protected final String name;
	
	public String getName() {
		return this.name;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Field field) ?
				this.name.equals(field.name) && this.clazz.equals(field.clazz)
				: false;
	}
	
	public @Override String toString() {
		return (name == null) ? "Non-generated Reflection" : "Field " + name + " in Class " + clazz.getName();
	}
	
	public @Override java.lang.reflect.Field getTarget() {
		return super.value;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			super.value = clazz.getDeclaredField(name);
		}
		catch (NoSuchFieldException nsfe) {
			throw new UndefinedReflectionException("No Field References could be generated from the given"
					+ " arguments.", nsfe);
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
		
		super.mirrorInteraction();
	}
	
	public Field(java.lang.reflect.Field field) throws NullArgumentException {
		if (field == null) {
			throw new NullArgumentException("Cannot build a Field Reference over a null java.lang.reflect.Method.");
		}
		super.value = field;
		this.clazz = field.getDeclaringClass(); this.name = field.getName();
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}

final class EnumField extends Field {
	
	public @Override String toString() {
		return (name == null) ? "Non-generated Reflection" : "Enum Const " + name + " in Enum " + clazz.getName();
	}
	
	public EnumField(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
		super(clazz, name);
	}
	
	public EnumField(java.lang.reflect.Field field) throws NullArgumentException {
		super(field);
	}
}