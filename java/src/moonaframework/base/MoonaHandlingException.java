package moonaframework.base;

public class MoonaHandlingException extends RuntimeException {

	private static final long serialVersionUID = -1L;
	
	public static final MoonaHandlingException CRITICAL = new MoonaHandlingException("This should not happen...");
	
	public MoonaHandlingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MoonaHandlingException(String message) {
		super(message);
	}
	
	public MoonaHandlingException() {
		super("Something went wrong with Moona...");
	}
}
