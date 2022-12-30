package moonaframework.util.reflection.beacon;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;
import moonaframework.util.reflection.Type;

public final class SealedProfiler extends Beacon<Type> {

	private final Type source;
	
	public @Override Type getTarget() {
		return this.source;
	}
	
	public Type getPermittedSubclass(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " permitted "
					+ "subclasses, index " + index + " is out of range.");
		}
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		return super.value.get(index);
	}
	
	public boolean isPermitted(Type ref) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		return super.value.contains(ref);
	}
	public boolean isPermitted(Class<?> clazz) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		for (Type t : super.value) {
			try {
				if (t.evaluate().equals(clazz)) {
					return true;
				}
			}
			catch (UnresolvedReflectionException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int permittedCount() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		return super.value.size();
	}
	
	public @Override void reflect() throws UnresolvedReflectionException {
		Class<?> clazz = source.evaluate();
		
		strictContext.enable();
		
		for (Class<?> allowed : clazz.getPermittedSubclasses()) {
			super.value.add(new Type(allowed));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public SealedProfiler(Type source) throws IllegalArgumentException, NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("SealedProfilers cannot be extracted from a null Type Reference.");
		}
		try {
			if (!source.evaluate().isSealed()) {
				throw new IllegalArgumentException("SealedProfilers can only be built from Type References that"
						+ " target sealed classes.");
			}
		}
		catch (UnresolvedReflectionException e) {
			e.printStackTrace();
		}
		this.source = source;
	}
	public SealedProfiler(Class<?> source) throws IllegalArgumentException, NullArgumentException {
		this(new Type(source));
	}
}
