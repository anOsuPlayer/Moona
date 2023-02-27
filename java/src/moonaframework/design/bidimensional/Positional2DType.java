package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalPosition;

public interface Positional2DType<T extends Number> {

	T getWrappedX();
	
	T getWrappedY();
	
	public static interface IntegralPositional2D extends Positional2DType<Integer> {
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getX();
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		
		int getY();
	}
	
	public static interface Positional2D extends Positional2DType<Float> {
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getX();
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		
		float getY();
	}
	
	public static interface DoublePositional2D extends Positional2DType<Double> {
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		
		double getX();
		
		default @Override Double getWrappedY() {
			return Double.valueOf(getY());
		}
		
		double getY();
	}
}
