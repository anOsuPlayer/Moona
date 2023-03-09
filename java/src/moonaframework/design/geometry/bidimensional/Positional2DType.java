package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.bidimensional.BidimensionalPosition.DoublePosition2D;
import moonaframework.design.geometry.bidimensional.BidimensionalPosition.IntegralPosition2D;
import moonaframework.design.geometry.bidimensional.BidimensionalPosition.Position2D;
import moonaframework.design.geometry.monodimensional.Positional1DType;

public interface Positional2DType<T extends Number> extends Positional1DType<T> {
	
	T getWrappedY();
	
	@Override BidimensionalPosition<T> getPosition();
	
	public static interface IntegralPositional2D extends Positional2DType<Integer>, IntegralPositional1D {
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		
		int getY();
		
		default @Override BidimensionalPosition<Integer> getPosition() {
			return new IntegralPosition2D(getX(), getY());
		}
	}
	
	public static interface Positional2D extends Positional2DType<Float>, Positional1D {
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		
		float getY();
		
		default @Override BidimensionalPosition<Float> getPosition() {
			return new Position2D(getX(), getY());
		}
	}
	
	public static interface DoublePositional2D extends Positional2DType<Double>, DoublePositional1D {
		
		default @Override Double getWrappedY() {
			return Double.valueOf(getY());
		}
		
		double getY();
		
		default @Override BidimensionalPosition<Double> getPosition() {
			return new DoublePosition2D(getX(), getY());
		}
	}
}
