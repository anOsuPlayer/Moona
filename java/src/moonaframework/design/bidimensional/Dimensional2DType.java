package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalBounds;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.Bounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.DoubleBounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.IntegralBounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalDimension.IntegralDimension2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.DoubleSize2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.IntegralSize2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize.Size2D;
import moonaframework.design.bidimensional.geometry.BidimensionalSize;
import moonaframework.design.monodimensional.Dimensional1DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.IntegralBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.IntegralSize1D;

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
