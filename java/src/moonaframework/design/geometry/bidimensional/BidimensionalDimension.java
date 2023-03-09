package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.monodimensional.MonodimensionalDimension;

public interface BidimensionalDimension<T extends Number> extends BidimensionalSize<T>, Resizable2DType<T>, MonodimensionalDimension<T>  {

	public static class IntegralDimension2D extends IntegralSize2D implements BidimensionalDimension<Integer>, IntegralResizable2D {
		
		public @Override void setWidth(int width) {
			this.width = width;
		}
		
		public @Override void setHeight(int height) {
			this.height = height;
		}
		
		public @Override String toString() {
			return "( " + width + " x " + height + " )";
		}
		
		public @Override IntegralDimension2D clone() {
			return new IntegralDimension2D(width, height);
		}
		
		public IntegralDimension2D(int width, int height) {
			this.width = width; this.height = height;
		}
		public IntegralDimension2D(Integer width, Integer height) throws NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public IntegralDimension2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public IntegralDimension2D(Dimensional2DType<?> dim) throws NullPointerException {
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
		
		public @Override String toString() {
			return "( " + width + " x " + height + " )";
		}
		
		public @Override Dimension2D clone() {
			return new Dimension2D(width, height);
		}
		
		public Dimension2D(float width, float height) {
			this.width = width; this.height = height;
		}
		public Dimension2D(Float width, Float height) throws NullPointerException {
			this(width.floatValue(), height.floatValue());
		}
		public Dimension2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public Dimension2D(Dimensional2DType<?> dim) throws NullPointerException {
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
		
		public @Override String toString() {
			return "( " + width + " x " + height + " )";
		}
		
		public @Override DoubleDimension2D clone() {
			return new DoubleDimension2D(width, height);
		}
		
		public DoubleDimension2D(double width, double height) {
			this.width = width; this.height = height;
		}
		public DoubleDimension2D(Double width, Double height) throws NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public DoubleDimension2D(IntegralDimensional2D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight());
		}
		public DoubleDimension2D(Dimensional2DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue());
		}
		public DoubleDimension2D() {
			this(0, 0);
		}
	}
}
