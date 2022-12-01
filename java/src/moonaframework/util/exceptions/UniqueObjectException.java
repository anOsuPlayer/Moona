package moonaframework.util.exceptions;

import moonaframework.base.Moona;
import moonaframework.base.Serial;

public class UniqueObjectException extends RuntimeException implements Serial {

	private static final long serialVersionUID = -3L;
	public @Override final int nature() {
		return Moona.EXCEPTION;
	}
	public @Override final long id() {
		return serialVersionUID;
	}
	
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
