package moonaframework.util.exception;

import moonaframework.base.MoonaObject;

public class UniqueObjectException extends RuntimeException {

	private static final long serialVersionUID = -3L;
	
	public UniqueObjectException(String message) {
		super(message);
	}
	
	public UniqueObjectException(MoonaObject obj) {
		this(obj + ": this MoonaObject cannot be processed more than once.");
	}
	
	public UniqueObjectException() {
		this("Elements marked as Unique can be processed just once.");
	}
}
