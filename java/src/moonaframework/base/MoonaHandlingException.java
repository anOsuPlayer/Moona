package moonaframework.base;

public class MoonaHandlingException extends RuntimeException {

	private static final long serialVersionUID = -1L;
	
	public static final MoonaHandlingException CRITICAL = new MoonaHandlingException("This should not happen...");
	
	public MoonaHandlingException(String message, Throwable cause) {
		super(message, cause);
		Moona.isOn = false;
	}
	
	public MoonaHandlingException(String message) {
		super(message);
		Moona.isOn = false;
	}
	
	public MoonaHandlingException() {
		super("Something went wrong with Moona...");
		Moona.isOn = false;
	}
}
