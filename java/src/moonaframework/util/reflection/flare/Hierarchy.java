package moonaframework.util.reflection.flare;

import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Type;

public final class Hierarchy extends Flare<Type> {
	
	private final Type target;
	
	public @Override Type getTarget() {
		return this.target;
	}
	
	public Type getSuperclass() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.get(0);
	}
	
	public List<Type> getInterfaces() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.subList(1, super.value.size());
	}
	public Type getInterface(int index) throws IllegalArgumentException, MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index + 1 >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " Interface Type "
					+ "References, index " + index + " is out of range.");
		}
		
		return super.value.get(index+1);
	}
	
	public boolean isSuperType(Type t) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.contains(t);
	}
	public boolean isSuperType(Class<?> clazz) throws MoonaHandlingException {
		return isSuperType(new Type(clazz));
	}
	
	public int superTypesCount() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.size();
	}
	public int interfaceCount() throws MoonaHandlingException {
		return superTypesCount()-1;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Hierarchy h) ? this.getTarget().equals(h.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generate Flare." : "Hierarchy of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		Class<?> clazz = target.evaluate();
		
		strictContext.enable();
		
		super.value.add(new Type((clazz.getSuperclass() != null) ? clazz.getSuperclass() : Object.class));
		
		for (Class<?> interf : clazz.getInterfaces()) {
			super.value.add(new Type(interf));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public Hierarchy(Type target) {
		if (target == null) {
			throw new NullArgumentException("Hierarchy cannot be extracted from a null Type Reference.");
		}
		this.target = target;
	}
	public Hierarchy(Class<?> clazz) throws NullArgumentException {
		this(new Type(clazz));
	}
}
