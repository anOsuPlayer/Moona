package moonaframework.util.reflection.flare;

import java.lang.reflect.Executable;
import java.util.List;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class ExceptionProfile extends Flare<Type> {

	private final Reference<? extends Executable> target;
	
	public @Override Reference<? extends Executable> getTarget() {
		return this.target;
	}
	
	public List<Type> getExceptionTypes() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}

		return super.value;
	}
	public Type getExceptionType(int index) throws UndefinedReflectionException, IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " exception "
					+ "declarations, index " + index + " is out of range.");
		}
		
		return super.value.get(index);
	}
	
	public boolean doesThrow(Type ref) throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		return super.value.contains(ref);
	}
	public boolean doesThrow(Class<?> clazz) throws UndefinedReflectionException {
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
	
	public int exceptionTypesCount() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		return super.value.size();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof ExceptionProfile ep) ?
				this.target.equals(ep.getTarget())
				: false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "ExceptionProfile of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		java.lang.reflect.Executable exec = target.evaluate();
		
		for (Class<?> except : exec.getExceptionTypes()) {
			super.value.add(new Type(except));
		}
		
		super.reflect();
	}
	
	public ExceptionProfile(Reference<? extends Executable> target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("ExecutableProfiles cannot be extracted from a null Reference.");
		}
		this.target = target;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
