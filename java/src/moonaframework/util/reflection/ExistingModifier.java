package moonaframework.util.reflection;

import java.lang.reflect.Member;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class ExistingModifier extends Modifier {

	public @Override final Integer evaluate() throws UndefinedReflectionException {
		Integer previous = super.value;
		reflect();
		if (!super.value.equals(previous)) {
			throw new UndefinedReflectionException("The provided Integer does not represent the given"
					+ " Reference's modifiers.");
		}
		return super.value;
	}
	
	public ExistingModifier(Reference<? extends Member> source, int modifiers) throws NullArgumentException {
		super(source);
		if (modifiers < 0) {
			throw new NullArgumentException("The value which states the modifiers cannot be less than zero.");
		}
		super.value = modifiers;
	}
}
