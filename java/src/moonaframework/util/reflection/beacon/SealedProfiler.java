package moonaframework.util.reflection.beacon;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Type;

public final class SealedProfiler extends Beacon<Type> {

	private final Type source;
	
	public @Override Type getTarget() {
		return this.source;
	}
	
	public Type getPermittedSubclass(int index) throws IndexOutOfBoundsException {
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " permitted "
					+ "subclasses, index " + index + " is out of range.");
		}
		if (!super.hasGenerated) {
			reflect();
		}
		return super.value.get(index);
	}
	
	public boolean isPermitted(Type ref) {
		if (!super.hasGenerated) {
			reflect();
		}
		return super.value.contains(ref);
	}
	public boolean isPermitted(Class<?> clazz) {
		if (!super.hasGenerated) {
			reflect();
		}
		for (Type t : super.value) {
			if (t.evaluate().equals(clazz)) {
				return true;
			}
		}
		return false;
	}
	
	public int permittedCount() {
		if (!super.hasGenerated) {
			reflect();
		}
		return super.value.size();
	}
	
	public @Override void reflect() {
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
		if (!source.evaluate().isSealed()) {
			throw new IllegalArgumentException("SealedProfilers can only be built from Type References that"
					+ " target sealed classes.");
		}
		this.source = source;
	}
	public SealedProfiler(Class<?> source) throws IllegalArgumentException, NullArgumentException {
		this(new Type(source));
	}
}
