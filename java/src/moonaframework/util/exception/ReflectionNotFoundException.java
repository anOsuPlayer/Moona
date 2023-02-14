package moonaframework.util.exception;

public class ReflectionNotFoundException extends Exception {

	private static final long serialVersionUID = -5L;

	public ReflectionNotFoundException(String message) {
		super(message);
	}
	
	public ReflectionNotFoundException() {
		super("No Reflection matches the given arguments.");
	}
}
