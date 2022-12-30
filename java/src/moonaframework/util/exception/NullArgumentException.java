package moonaframework.util.exception;

import moonaframework.base.Nature;
import moonaframework.base.Serial;

public class NullArgumentException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -2;

	public @Override long id() {
		return serialVersionUID;
	}
	public @Override Nature nature() {
		return Nature.EXCEPTION;
	}

	public NullArgumentException(String message) {
		super(message);
	}
	public NullArgumentException() {
		super("Null arguments are not allowed.");
	}
}
