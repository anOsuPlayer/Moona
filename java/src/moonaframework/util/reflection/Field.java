package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.filters.ClassElement;
import moonaframework.util.reflection.filters.Nominal;
import moonaframework.util.reflection.flare.FieldProperty;

public sealed class Field extends Reference<java.lang.reflect.Field> implements ClassElement, Nominal permits EnumField {
	
	protected final Class<?> clazz;
	
	public @Override Class<?> getDeclaringClass() {
		return clazz;
	}
	public @Override Class<?> getTarget() {
		return this.clazz;
	}
	
	protected final String name;
	
	public @Override String getName() {
		return this.name;
	}
	
	private final boolean isDeclared;
	
	public final boolean isDeclared() {
		return this.isDeclared;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Field field) ?
				this.name.equals(field.name) && this.clazz.equals(field.clazz)
				: false;
	}
	
	public @Override String toString() {
		return (name == null) ? "Non-generated Reflection" : "Field " + name + " in Class " + clazz.getName();
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		try {
			if (isDeclared) {
				super.value = clazz.getDeclaredField(name);
			}
			else {
				super.value = clazz.getField(name);
			}
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

	public Field(Class<?> clazz, String name, boolean isDeclared) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a Field Reference with a null class or a null"
					+ " field name.");
		}
		this.isDeclared = isDeclared;
		this.clazz = clazz; this.name = name;
		
		super.mirrorInteraction();
	}
	public Field(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
		this(clazz, name, false);
	}
	
	public Field(java.lang.reflect.Field field) throws NullArgumentException {
		if (field == null) {
			throw new NullArgumentException("Cannot build a Field Reference over a null java.lang.reflect.Method.");
		}
		super.value = field;
		this.isDeclared = false;
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