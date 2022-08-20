package moonaFramework;

public class MoonaHandlingException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -1;
	
	public final long id() {
		return serialVersionUID;
	}
	
	public final int nature() {
		return Natural.EXCEPTION;
	}
	
	public MoonaHandlingException(String message) {
		super(message);
	}
	public MoonaHandlingException() {
		super("Something went wrong with Moona...");
	}
}
