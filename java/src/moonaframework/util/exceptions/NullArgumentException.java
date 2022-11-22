package moonaframework.util.exceptions;

import moonaframework.base.Natural;
import moonaframework.base.Serial;

public class NullArgumentException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -2;

	@Override
	public long id() {
		return serialVersionUID;
	}
	@Override
	public int nature() {
		return Natural.EXCEPTION;
	}

	public NullArgumentException(String message) {
		super(message);
	}
}
