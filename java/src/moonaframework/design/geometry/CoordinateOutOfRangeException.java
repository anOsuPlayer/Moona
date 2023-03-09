package moonaframework.design.geometry;

public class CoordinateOutOfRangeException extends RuntimeException {

	private static final long serialVersionUID = -6L;
	
	public CoordinateOutOfRangeException() {
		this("One of the given coordinates is out of range.");
	}
	
	public CoordinateOutOfRangeException(String message) {
		super(message);
	}
	
	public CoordinateOutOfRangeException(double coordinate) {
		super(coordinate + " : coordinate out of range.");
	}
	
	public CoordinateOutOfRangeException(double coordinate, double upperBound, double lowerBound) {
		super(coordinate + " : coordinate out of range [ " + lowerBound + " - " + upperBound + " ].");
	}
}
