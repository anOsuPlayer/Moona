package moonaframework.util.exception;

public class UniqueObjectException extends RuntimeException {

	private static final long serialVersionUID = -3L;
	
	public UniqueObjectException(String message) {
		super(message);
	}
	
	public UniqueObjectException(Object obj) {
		this(obj + ": this Object cannot be processed more than once.");
	}
	
	public UniqueObjectException() {
		this("Elements marked as Unique can be processed just once.");
	}
}
