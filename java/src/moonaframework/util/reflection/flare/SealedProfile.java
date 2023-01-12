package moonaframework.util.reflection.flare;

import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Type;

public final class SealedProfile extends Flare<Type> {

	private final Type target;
	
	public @Override Type getTarget() {
		return this.target;
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
		
		return (!super.hasGenerated) ? "Non-generated Flare" : "SealedProfile of " + target + ", "
				+ "permitted subtypes : " + permit;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		Class<?> clazz = target.evaluate();
		
		for (Class<?> allowed : clazz.getPermittedSubclasses()) {
			super.value.add(new Type(allowed));
		}
		
		super.reflect();
	}
	
	public SealedProfile(Type target) throws IllegalArgumentException, NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("SealedProfilers cannot be extracted from a null Type Reference.");
		}
		if (!target.evaluate().isSealed()) {
			throw new IllegalArgumentException("SealedProfiles can only be built from Type References that"
					+ " target sealed classes.");
		}
		this.target = target;
	}
}
