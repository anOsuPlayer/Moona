package moonaframework.util.reflection;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.GenericBounds;

public non-sealed class Generic extends Reference<TypeVariable<?>> {

	private final Reference<? extends GenericDeclaration> source;
	
	public Reference<? extends GenericDeclaration> getSource() {
		return this.source;
	}
	
	private final String name;
	
	public String getName() {
		return this.name;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Generic gen) ?
				this.source.equals(gen.source) && this.name.equals(gen.name)
				: false;
	}
	
	public @Override String toString() {
		return (source == null) ? "Non-generated Reflection" : "Generic " + name + " of " + source;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		TypeVariable<?>[] vars = source.evaluate().getTypeParameters();
		for (TypeVariable<?> t : vars) {
			if (t.getName().equals(name)) {
				super.value = t;
				return;
			}
		}
		throw new UndefinedReflectionException("No Generic References could be generated from the given arguments.");
	}
	
	private GenericBounds gb;
	
	public @Override GenericBounds derive() {
		if (gb == null) {
			gb = new GenericBounds(this);
		}
		return gb;
	}
	
	public Generic(Reference<? extends GenericDeclaration> source, String name) throws NullArgumentException {
		if (source == null || name == null) {
			throw new NullArgumentException("Cannot generate a Generic Reference over a null Reference or"
					+ " null name.");
		}
		this.source = source; this.name = name;
	}
	public Generic(Class<?> clazz, String name) throws NullArgumentException {
		this (new Type(clazz), name);
	}
	
	protected Generic(Reference<? extends GenericDeclaration> source, String name, TypeVariable<?> typevar) throws NullArgumentException {
		this(source, name);
		if (typevar == null) {
			throw new NullArgumentException("A null java.lang.reflect.TypeVariable<?> cannot be accepted.");
		}
		super.value = typevar;
	}
}
