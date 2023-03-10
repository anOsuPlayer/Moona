package moonaframework.design.geometry;

public class LowerDimensionalOrderException extends RuntimeException {

	private static final long serialVersionUID = -7L;

	public LowerDimensionalOrderException() {
		super("The requested coordinate exceeds this Object's dimensional order.");
	}
	
	public LowerDimensionalOrderException(String message) {
		super(message);
	}
}
