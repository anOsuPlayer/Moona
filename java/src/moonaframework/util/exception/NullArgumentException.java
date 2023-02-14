package moonaframework.util.exception;

public class NullArgumentException extends RuntimeException {

	private static final long serialVersionUID = -2;

	public NullArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NullArgumentException(String message) {
		super(message);
	}
	
	public NullArgumentException() {
		super("Null arguments are not allowed.");
	}
}
