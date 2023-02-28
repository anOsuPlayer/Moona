package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalSize;

public interface BidimensionalSize<T extends Number> extends MonodimensionalSize<T>, Dimensional2DType<T> {

	public static class IntegralSize2D extends IntegralSize1D implements BidimensionalSize<Integer>, IntegralDimensional2D {
		
		protected int height;
		
		public @Override int getHeight() {
			return this.height;
		}

		public IntegralSize2D(int width, int height) {
			this.width = width; this.height = height;
		}
		public IntegralSize2D(Integer width, Integer height) {
			this(width.intValue(), height.intValue());
		}
		public IntegralSize2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public IntegralSize2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().intValue(), dim.getWrappedHeight().intValue());
		}
		public IntegralSize2D() {
			this(0, 0);
		}
	}
	
	public static class Size2D extends Size1D implements BidimensionalSize<Float>, Dimensional2D {
		
		protected float height;
		
		public @Override float getHeight() {
			return this.height;
		}
		
		public Size2D(float width, float height) {
			this.width = width; this.height = height;
		}
		public Size2D(Float width, Float height) {
			this(width.floatValue(), height.floatValue());
		}
		public Size2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public Size2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().floatValue(), dim.getWrappedHeight().floatValue());
		}
		public Size2D() {
			this(0, 0);
		}
	}

	public static class DoubleSize2D extends DoubleSize1D implements BidimensionalSize<Double>, DoubleDimensional2D {
		
		protected double height;
		
		public @Override double getHeight() {
			return this.height;
		}
		
		public DoubleSize2D(double width, double height) {
			this.width = width; this.height = height;
		}
		public DoubleSize2D(Double width, Double height) {
			this(width.doubleValue(), height.doubleValue());
		}
		public DoubleSize2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public DoubleSize2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue());
		}
		public DoubleSize2D() {
			this(0, 0);
		}
	}
}
