package moonaframework.util.reflection;

import moonaframework.base.Nature;
import moonaframework.base.Serial;

public class UnresolvedReflectionException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -4;

	public @Override long id() {
		return serialVersionUID;
	}
	public @Override Nature nature() {
		return Nature.EXCEPTION;
	}

	public UnresolvedReflectionException(String message) {
		super(message);
	}
	public UnresolvedReflectionException() {
		super("The given arguments could not generate any Reflection.");
	}	
}