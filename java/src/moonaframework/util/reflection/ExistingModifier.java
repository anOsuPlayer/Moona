package moonaframework.util.reflection;

import java.lang.reflect.Member;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class ExistingModifier extends Modifier {

	private boolean trusted = false;
	
	public @Override final Integer evaluate() throws UndefinedReflectionException {
		if (trusted) {
			return super.evaluate();
		}
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
		
		if (Reflection.strictContext.evaluate()) {
			trusted = true;
		}
		
		super.mirrorInteraction();
	}
}
