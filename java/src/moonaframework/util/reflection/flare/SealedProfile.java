package moonaframework.util.reflection.flare;

import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Type;

public final class SealedProfile extends Flare<Type> {

	private final Type source;
	
	public @Override Type getTarget() {
		return this.source;
	}
	
	public List<Type> getPermittedSubclasses() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}

		return super.value;
	}
	public Type getPermittedSubclass(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
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
		if (index >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " permitted "
					+ "subclasses, index " + index + " is out of range.");
		}
		
		return super.value.get(index);
	}
	
	public boolean isPermitted(Type ref) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.contains(ref);
	}
	public boolean isPermitted(Class<?> clazz) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		for (Type t : super.value) {
			if (t.evaluate().equals(clazz)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int permittedCount() throws MoonaHandlingException {
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
	
	public @Override boolean equals(Object o) {
		return (o instanceof SealedProfile sp) ? this.getTarget().equals(sp.getTarget()) : false;
	}
	
	public @Override String toString() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		String permit = "";
		for (Type t : super.value) {
			permit += t.evaluate().getSimpleName() + ", ";
		}
		
		permit = permit.substring(0, permit.length()-2);
		
		return (source == null) ? "Non-generated Flare" : "SealedProfile of " + source + ", "
				+ "permitted subtypes : " + permit;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		Class<?> clazz = source.evaluate();
		
		strictContext.enable();
		
		for (Class<?> allowed : clazz.getPermittedSubclasses()) {
			super.value.add(new Type(allowed));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public SealedProfile(Type source) throws IllegalArgumentException, NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("SealedProfilers cannot be extracted from a null Type Reference.");
		}
		if (!source.evaluate().isSealed()) {
			throw new IllegalArgumentException("SealedProfilers can only be built from Type References that"
					+ " target sealed classes.");
		}
		this.source = source;
	}
	public SealedProfile(Class<?> source) throws IllegalArgumentException, NullArgumentException {
		this(new Type(source));
	}
}
