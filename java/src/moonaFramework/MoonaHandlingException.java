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
	 * ALL the exceptions return their {@link MoonaHandlingException#serialVersionUID} as ID.
	 */
	@Override
	public final long id() {
		return serialVersionUID;
	}
	
	/**
	 * ALL the exceptions return the {@link Moona#EXCEPTION} field to identify an exception's nature.
	 */
	@Override
	public final int nature() {
		return Moona.EXCEPTION;
	}
	
	/**
	 * The main constructor of this exceptions builds a RuntimeException on top of the given message.
	 * 
	 * @param message : The given string to output.
	 */
	public MoonaHandlingException(String message) {
		super(message);
	}
	
	/**
	 * The default constructor of this class generates a default instance of this exception which simply 
	 * states "Something went wrong with Moona...". This was created in order to prevent any empty 
	 * initializations to cause trouble.
	 */
	public MoonaHandlingException() {
		super("Something went wrong with Moona...");
	}
}