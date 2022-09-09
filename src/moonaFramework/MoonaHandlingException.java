package moonaFramework;

/**
 * The MoonaHandlingException was created with the intent of warning the user of some problem happening in 
 * relation to the Moona Class. When it comes to some incorrect argument or to some wrong usage of a Moona 
 * method, this exception gets thrown to warn of those kind of issues. There is no such case in which a 
 * MoonaHandlingException can be thrown by some other class than the Moona Class itself.
 * 
 * @author MasterZEr0
 */
public class MoonaHandlingException extends RuntimeException implements Serial {

	/**
	 * this long value stores an unique ID to identify this exception. This number is unique for each 
	 * exception class, in this case, it assumes the value of -1.
	 */
	private static final long serialVersionUID = -1;
	
	/**
	 * ALL the exceptions return their #serialVersionUID as ID.
	 */
	@Override
	public final long id() {
		return serialVersionUID;
	}
	
	@Override
	public final int nature() {
		return Moona.EXCEPTION;
	}
	
	public MoonaHandlingException(String message) {
		super(message);
	}
	public MoonaHandlingException() {
		super("Something went wrong with Moona...");
	}
}
