package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.Size;
import moonaframework.design.geometry.monodimensional.MonodimensionalDimension;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;

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
			super(width, height);
		}
		public IntegralDimension2D(Integer width, Integer height) throws NullPointerException {
			super(width.intValue(), height.intValue());
		}
		public IntegralDimension2D(IntegralDimensional3D dim, Size width, Size height) throws NullPointerException {
			super(dim, width, height);
		}
		public IntegralDimension2D(IntegralDimensional2D dim) throws NullPointerException {
			super(dim.getWidth(), dim.getHeight());
		}
		public IntegralDimension2D(Dimensional2DType<?> dim) throws NullPointerException {
			super(dim.getWrappedWidth().intValue(), dim.getWrappedHeight().intValue());
		}
		public IntegralDimension2D() {
			super(0, 0);
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
			super(width, height);
		}
		public Dimension2D(Float width, Float height) throws NullPointerException {
			super(width.floatValue(), height.floatValue());
		}
		public Dimension2D(Dimensional3D dim, Size width, Size height) throws NullPointerException {
			super(dim, width, height);
		}
		public Dimension2D(IntegralDimensional2D dim) throws NullPointerException {
			super(dim.getWidth(), dim.getHeight());
		}
		public Dimension2D(Dimensional2DType<?> dim) throws NullPointerException {
			super(dim.getWrappedWidth().floatValue(), dim.getWrappedHeight().floatValue());
		}
		public Dimension2D() {
			super(0, 0);
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
			super(width, height);
		}
		public DoubleDimension2D(Double width, Double height) throws NullPointerException {
			super(width.intValue(), height.intValue());
		}
		public DoubleDimension2D(DoubleDimensional3D dim, Size width, Size height) throws NullPointerException {
			super(dim, width, height);
		}
		public DoubleDimension2D(IntegralDimensional2D dim) throws NullPointerException {
			super(dim.getWidth(), dim.getHeight());
		}
		public DoubleDimension2D(Dimensional2DType<?> dim) throws NullPointerException {
			super(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue());
		}
		public DoubleDimension2D() {
			super(0, 0);
		}
	}
}
