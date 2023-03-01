package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalPosition;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.DoublePosition2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.IntegralPosition2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.Position2D;
import moonaframework.design.monodimensional.Positional1DType;

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
