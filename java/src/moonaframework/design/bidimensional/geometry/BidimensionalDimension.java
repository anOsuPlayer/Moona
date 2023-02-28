package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.bidimensional.Resizable2DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalDimension;

public interface BidimensionalDimension<T extends Number> extends BidimensionalSize<T>, Resizable2DType<T>, MonodimensionalDimension<T>  {

	public static class IntegralDimension2D extends IntegralSize2D implements BidimensionalDimension<Integer>, IntegralResizable2D {
		
		public @Override void setWidth(int width) {
			this.width = width;
		}
		
		public @Override void setHeight(int height) {
			this.height = height;
		}
		
		public IntegralDimension2D(int width, int height) {
			this.width = width; this.height = height;
		}
		public IntegralDimension2D(Integer width, Integer height) {
			this(width.intValue(), height.intValue());
		}
		public IntegralDimension2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public IntegralDimension2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().intValue(), dim.getWrappedHeight().intValue());
		}
		public IntegralDimension2D() {
			this(0, 0);
		}
	}
	
	public static class Dimension2D extends Size2D implements BidimensionalDimension<Float>, Resizable2D {
		
		public @Override void setWidth(float width) {
			this.width = width;
		}
		
		public @Override void setHeight(float height) {
			this.height = height;
		}
		
		public Dimension2D(float width, float height) {
			this.width = width; this.height = height;
		}
		public Dimension2D(Float width, Float height) {
			this(width.floatValue(), height.floatValue());
		}
		public Dimension2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public Dimension2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().floatValue(), dim.getWrappedHeight().floatValue());
		}
		public Dimension2D() {
			this(0, 0);
		}
	}


	public static class DoubleDimension2D extends DoubleSize2D implements BidimensionalDimension<Double>, DoubleResizable2D {
		
		public @Override void setWidth(double width) {
			this.width = width;
		}
		
		public @Override void setHeight(double height) {
			this.height = height;
		}
		
		public DoubleDimension2D(double width, double height) {
			this.width = width; this.height = height;
		}
		public DoubleDimension2D(Double width, Double height) {
			this(width.intValue(), height.intValue());
		}
		public DoubleDimension2D(IntegralDimensional2D dim) {
			this(dim.getWidth(), dim.getHeight());
		}
		public DoubleDimension2D(Dimensional2DType<?> dim) {
			this(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue());
		}
		public DoubleDimension2D() {
			this(0, 0);
		}
	}
}
