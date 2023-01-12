package moonaframework.util.reflection;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.GenericBounds;

public sealed class Generic extends Reference<TypeVariable<?>> permits ExistingGeneric {

	private final Reference<? extends GenericDeclaration> target;
	
	public Reference<? extends GenericDeclaration> getTarget() {
		return this.target;
	}
	
	private final String name;
	
	public String getName() {
		return this.name;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Generic gen) ?
				this.target.equals(gen.target) && this.name.equals(gen.name)
				: false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Reflection" : "Generic " + name + " of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		TypeVariable<?>[] vars = target.evaluate().getTypeParameters();
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
	
	public Generic(Reference<? extends GenericDeclaration> target, String name) throws NullArgumentException {
		if (target == null || name == null) {
			throw new NullArgumentException("Cannot generate a Generic Reference over a null Reference or"
					+ " null name.");
		}
		this.target = target; this.name = name;
	}
	public Generic(Class<?> clazz, String name) throws NullArgumentException {
		this (new Type(clazz), name);
	}
}
