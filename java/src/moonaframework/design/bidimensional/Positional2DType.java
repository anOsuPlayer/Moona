package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalFixedPoint;

public sealed interface Positional2DType<T extends Number> permits Spatial2DType<T>, Movable2DType<T>, BidimensionalFixedPoint<T>, Positional2DType.IntegralPositional2D, Positional2DType.Positional2D, Positional2DType.DoublePositional2D {

	T getWrappedX();
	
	T getWrappedY();
	
	public non-sealed static interface IntegralPositional2D extends Positional2DType<Integer> {
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getX();
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		
		int getY();
	}
	
	public non-sealed static interface Positional2D extends Positional2DType<Float> {
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getX();
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		
		float getY();
	}
	
	public non-sealed static interface DoublePositional2D extends Positional2DType<Double> {
		
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
