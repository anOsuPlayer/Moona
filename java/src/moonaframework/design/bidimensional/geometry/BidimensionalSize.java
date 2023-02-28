package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.Bounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.DoubleBounds2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds.IntegralBounds2D;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.*;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize.IntegralSize1D;

public interface BidimensionalSize<T extends Number> extends MonodimensionalSize<T>, Dimensional2DType<T> {

	public static class IntegralSize2D implements BidimensionalSize<Integer>, IntegralDimensional2D, IntegralDimensional1D {
		
		protected int width;
		
		public @Override int getWidth() {
			return this.width;
		}
		
		protected int height;
		
		public @Override int getHeight() {
			return this.height;
		}

		public IntegralSize2D(int width, int height) {
			this.width = width; this.height = height;
		}
		public IntegralSize2D(Integer width, Integer height) throws NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public IntegralSize2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public IntegralSize2D(Dimensional2DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().intValue(), dim.getWrappedHeight().intValue());
		}
		public IntegralSize2D() {
			this(0, 0);
		}
	}
	
	public static class Size2D implements BidimensionalSize<Float>, Dimensional2D, Dimensional1D {
		
		protected float width;
		
		public @Override float getWidth() {
			return this.width;
		}
		
		protected float height;
		
		public @Override float getHeight() {
			return this.height;
		}
		
		public Size2D(float width, float height) {
			this.width = width; this.height = height;
		}
		public Size2D(Float width, Float height) throws NullPointerException {
			this(width.floatValue(), height.floatValue());
		}
		public Size2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public Size2D(Dimensional2DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().floatValue(), dim.getWrappedHeight().floatValue());
		}
		public Size2D() {
			this(0, 0);
		}
	}

	public static class DoubleSize2D implements BidimensionalSize<Double>, DoubleDimensional2D, DoubleDimensional1D {
		
		protected double width;
		
		public @Override double getWidth() {
			return this.width;
		}
		
		protected double height;
		
		public @Override double getHeight() {
			return this.height;
		}
		
		public DoubleSize2D(double width, double height) {
			this.width = width; this.height = height;
		}
		public DoubleSize2D(Double width, Double height) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue());
		}
		public DoubleSize2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public DoubleSize2D(Dimensional2DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue());
		}
		public DoubleSize2D() {
			this(0, 0);
		}
	}
}
