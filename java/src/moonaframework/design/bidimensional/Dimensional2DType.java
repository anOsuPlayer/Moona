package moonaframework.design.bidimensional;

import moonaframework.design.monodimensional.Dimensional1DType;

public interface Dimensional2DType<T extends Number> extends Dimensional1DType<T> {
	
	T getWrappedHeight();
	
	public static interface IntegralDimensional2D extends Dimensional2DType<Integer>, IntegralDimensional1D {
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		
		int getHeight();
	}
	
	public static interface Dimensional2D extends Dimensional2DType<Float>, Dimensional1D {
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		
		float getHeight();
	}
	
	public static interface DoubleDimensional2D extends Dimensional2DType<Double>, DoubleDimensional1D {
		
		default @Override Double getWrappedHeight() {
			return Double.valueOf(getHeight());
		}
		
		double getHeight();
	}
}
