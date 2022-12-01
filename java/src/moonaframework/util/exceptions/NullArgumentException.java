package moonaframework.util.exceptions;

import moonaframework.base.Natural;
import moonaframework.base.Serial;

public class NullArgumentException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -2;

	public @Override long id() {
		return serialVersionUID;
	}
	public @Override int nature() {
		return Natural.EXCEPTION;
	}

	public NullArgumentException(String message) {
		super(message);
	}
	public NullArgumentException() {
		super("Null arguments are not allowed.");
	}
}
