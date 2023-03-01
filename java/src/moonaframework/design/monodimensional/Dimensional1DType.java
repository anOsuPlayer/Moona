package moonaframework.design.monodimensional;

import moonaframework.design.monodimensional.geometry.MonodimensionalBounds;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.Bounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.DoubleBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.IntegralBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.DoubleSize1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.IntegralSize1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.Size1D;

public interface Dimensional1DType<T extends Number> {

	T getWrappedWidth();
	
	MonodimensionalSize<T> getSize();
	
	MonodimensionalBounds<T> getBounds();
	
	public static interface IntegralDimensional1D extends Dimensional1DType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
		
		default @Override MonodimensionalSize<Integer> getSize() {
			return new IntegralSize1D(getWidth());
		}
		default @Override MonodimensionalBounds<Integer> getBounds() {
			return new IntegralBounds1D(this);
		}
	}
	
	public static interface Dimensional1D extends Dimensional1DType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
		
		default @Override MonodimensionalSize<Float> getSize() {
			return new Size1D(getWidth());
		}
		default @Override MonodimensionalBounds<Float> getBounds() {
			return new Bounds1D(this);
		}
	}
	
	public static interface DoubleDimensional1D extends Dimensional1DType<Double> {
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
		
		default @Override MonodimensionalSize<Double> getSize() {
			return new DoubleSize1D(getWidth());
		}
		default @Override MonodimensionalBounds<Double> getBounds() {
			return new DoubleBounds1D(this);
		}
	}
}
