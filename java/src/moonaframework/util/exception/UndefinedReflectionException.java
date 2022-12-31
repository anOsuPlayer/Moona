package moonaframework.util.exception;

import moonaframework.base.Nature;
import moonaframework.base.Serial;

public class UndefinedReflectionException extends Exception implements Serial {

	private static final long serialVersionUID = -4;

	public @Override long id() {
		return serialVersionUID;
	}
	public @Override Nature nature() {
		return Nature.EXCEPTION;
	}

	public UndefinedReflectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UndefinedReflectionException(String message) {
		super(message);
	}
	
	public UndefinedReflectionException() {
		super("The given arguments could not generate any Reflection.");
	}
}