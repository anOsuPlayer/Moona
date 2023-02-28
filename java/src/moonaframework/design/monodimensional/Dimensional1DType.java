package moonaframework.design.monodimensional;

import moonaframework.design.monodimensional.geometry.MonodimensionalBounds;
import moonaframework.design.monodimensional.geometry.MonodimensionalDimension.IntegralDimension1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalPosition.IntegralPosition1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.Bounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.DoubleBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.IntegralBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.DoubleSize1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.IntegralSize1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.Size1D;

public interface Dimensional1DType<T extends Number> {

	T getWrappedWidth();
	
	public static interface IntegralDimensional1D extends Dimensional1DType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
	}
	
	public static interface Dimensional1D extends Dimensional1DType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
	}
	
	public static interface DoubleDimensional1D extends Dimensional1DType<Double> {
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
	}
}
