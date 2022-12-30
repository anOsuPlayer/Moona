package moonaframework.util.exception;

import moonaframework.base.Nature;
import moonaframework.base.Serial;

public class ReflectionNotFoundException extends Exception implements Serial {

	private static final long serialVersionUID = -5L;
	
	public @Override long id() {
		return serialVersionUID;
	}
	public @Override Nature nature() {
		return Nature.EXCEPTION;
	}

	public ReflectionNotFoundException(String message) {
		super(message);
	}
	public ReflectionNotFoundException() {
		super("No Reflection matches the given arguments.");
	}
}
