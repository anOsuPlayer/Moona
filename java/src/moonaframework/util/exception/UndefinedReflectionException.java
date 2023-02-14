package moonaframework.util.exception;

public class UndefinedReflectionException extends Exception {

	private static final long serialVersionUID = -4;

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