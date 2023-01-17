package moonaframework.util.reflection;

import java.lang.reflect.Executable;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class ExistingParameter extends Parameter {

	private boolean trusted = false;
	
	public @Override final java.lang.reflect.Parameter evaluate() throws UndefinedReflectionException {
		if (trusted) {
			return super.evaluate();
		}
		var previous = super.value;
		reflect();
		if (!super.value.equals(previous)) {
			throw new UndefinedReflectionException("The provided index for this Parameter Reference does not"
					+ " refer to the given java.lang.reflect.Parameter.");
		}
		return super.value;
	}
	
	public ExistingParameter(Reference<? extends Executable> target, int index, java.lang.reflect.Parameter param) throws NullArgumentException {
		super(target, index);
		if (param == null) {
			throw new NullArgumentException("A null java.lang.reflect.Parameter cannot be accepted.");
		}
		super.value = param;
		
		if (Reflection.strictContext.evaluate()) {
			trusted = true;
		}
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
