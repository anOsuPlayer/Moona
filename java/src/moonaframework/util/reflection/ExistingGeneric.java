package moonaframework.util.reflection;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class ExistingGeneric extends Generic {

	private boolean trusted = false;
	
	public @Override final java.lang.reflect.TypeVariable<?> evaluate() throws UndefinedReflectionException {
		if (trusted) {
			return super.evaluate();
		}
		var previous = super.value;
		reflect();
		if (!super.value.equals(previous)) {
			throw new UndefinedReflectionException("The provided name does not either match the given"
					+ " TypeVariable<?> or cannot be evaluated from the given Reference.");
		}
		return super.value;
	}
	
	public ExistingGeneric(Reference<? extends GenericDeclaration> target, String name, TypeVariable<?> typevar) throws NullArgumentException {
		super(target, name);
		if (typevar == null) {
			throw new NullArgumentException("A null java.lang.reflect.TypeVariable<?> cannot be accepted.");
		}
		super.value = typevar;
		
		if (Reflection.strictContext.evaluate()) {
			trusted = true;
		}
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
