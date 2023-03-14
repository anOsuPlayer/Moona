package moonaframework.design.geometry;

public class LowerDimensionalOrderException extends RuntimeException {

	private static final long serialVersionUID = -7L;
	
	public LowerDimensionalOrderException() {
		super("The requested dimension or coordinate lives outside of this GeometricElement's space.");
	}
	
	public LowerDimensionalOrderException(String message) {
		super(message);
	}
}
