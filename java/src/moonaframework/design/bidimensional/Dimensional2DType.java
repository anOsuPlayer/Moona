package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalBounds;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.Bounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.DoubleBounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.IntegralBounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.DoubleSize2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.IntegralSize2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.Size2D;
import moonaframework.design.monodimensional.Dimensional1DType;

public interface Dimensional2DType<T extends Number> extends Dimensional1DType<T> {
	
	T getWrappedHeight();
	
	@Override BidimensionalSize<T> getSize();
	
	@Override BidimensionalBounds<T> getBounds();
	
	public static interface IntegralDimensional2D extends Dimensional2DType<Integer>, IntegralDimensional1D {
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		
		int getHeight();
		
		default @Override BidimensionalSize<Integer> getSize() {
			return new IntegralSize2D(getWidth(), getHeight());
		}
		default @Override BidimensionalBounds<Integer> getBounds() {
			return new IntegralBounds2D(this);
		}
	}
	
	public static interface Dimensional2D extends Dimensional2DType<Float>, Dimensional1D {
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		
		float getHeight();
		
		default @Override BidimensionalSize<Float> getSize() {
			return new Size2D(getWidth(), getHeight());
		}
		default @Override BidimensionalBounds<Float> getBounds() {
			return new Bounds2D(this);
		}
	}
	
	public static interface DoubleDimensional2D extends Dimensional2DType<Double>, DoubleDimensional1D {
		
		default @Override Double getWrappedHeight() {
			return Double.valueOf(getHeight());
		}
		
		double getHeight();
		
		default @Override BidimensionalSize<Double> getSize() {
			return new DoubleSize2D(getWidth(), getHeight());
		}
		default @Override BidimensionalBounds<Double> getBounds() {
			return new DoubleBounds2D(this);
		}
	}
}
