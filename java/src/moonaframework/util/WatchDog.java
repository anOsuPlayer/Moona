package moonaframework.util;

import moonaframework.design.CoordinateOutOfRangeException;

public final class WatchDog {

	public static void requiresInRange(double value, double upperBound, double lowerBound) throws CoordinateOutOfRangeException {
		if (value < lowerBound || value > upperBound) {
			throw new CoordinateOutOfRangeException(value, upperBound, lowerBound);
		}
	}
	public static void requiresInStrictRange(double value, double upperBound, double lowerBound) throws CoordinateOutOfRangeException {
		if (value <= lowerBound || value >= upperBound) {
			throw new CoordinateOutOfRangeException(value, upperBound, lowerBound);
		}
	}
	
	public static void requiresPositive(double...values) throws IllegalArgumentException {
		for (double d : values) {
			if (d < 0) {
				throw new IllegalArgumentException(d + ": negative values are not allowed.");
			}
		}
	}
	
	private WatchDog() {
		
	}
}
