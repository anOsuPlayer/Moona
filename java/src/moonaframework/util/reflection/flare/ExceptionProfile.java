package moonaframework.util.reflection.flare;

import java.lang.reflect.Executable;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class ExceptionProfile extends Flare<Type> {

	private final Reference<? extends Executable> target;
	
	public @Override Reference<? extends Executable> getTarget() {
		return this.target;
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
	}
}
