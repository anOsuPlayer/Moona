package moonaframework.design.bidimensional;

import moonaframework.design.monodimensional.Positional1DType;

public interface Positional2DType<T extends Number> extends Positional1DType<T> {
	
	T getWrappedY();
	
	public static interface IntegralPositional2D extends Positional2DType<Integer>, IntegralPositional1D {
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		
		int getY();
	}
	
	public static interface Positional2D extends Positional2DType<Float>, Positional1D {
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		
		float getY();
	}
	
	public static interface DoublePositional2D extends Positional2DType<Double>, DoublePositional1D {
		
		default @Override Double getWrappedY() {
			return Double.valueOf(getY());
		}
		
		double getY();
	}
}
