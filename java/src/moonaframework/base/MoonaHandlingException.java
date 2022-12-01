package moonaframework.base;

public class MoonaHandlingException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -1;
	
	public @Override final long id() {
		return serialVersionUID;
	}
	public @Override final int nature() {
		return Natural.EXCEPTION;
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
