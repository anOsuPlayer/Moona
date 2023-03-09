package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.bidimensional.BidimensionalBounds.Bounds2D;
import moonaframework.design.geometry.bidimensional.BidimensionalBounds.DoubleBounds2D;
import moonaframework.design.geometry.bidimensional.BidimensionalBounds.IntegralBounds2D;
import moonaframework.design.geometry.monodimensional.MonodimensionalSize;

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
		
		public @Override IntegralSize2D getSize() {
			return new IntegralSize2D(width, height);
		}
		public @Override IntegralBounds2D getBounds() {
			return new IntegralBounds2D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof IntegralDimensional2D dim) ? dim.getWidth() == width && dim.getHeight() == height :
				(o instanceof Dimensional2DType<?> dimtype) ? dimtype.getWrappedWidth().intValue() == width &&
				dimtype.getWrappedWidth().intValue() == height : false;
		}
		
		public @Override IntegralSize2D clone() {
			return new IntegralSize2D(width, height);
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
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof Dimensional2D dim) ? dim.getWidth() == width && dim.getHeight() == height :
				(o instanceof Dimensional2DType<?> dimtype) ? dimtype.getWrappedWidth().floatValue() == width &&
				dimtype.getWrappedWidth().floatValue() == height : false;
		}
		
		public @Override Size2D clone() {
			return new Size2D(width, height);
		}
		
		public @Override Size2D getSize() {
			return new Size2D(width, height);
		}
		public @Override Bounds2D getBounds() {
			return new Bounds2D(this);
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
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof DoubleDimensional2D dim) ? dim.getWidth() == width && dim.getHeight() == height :
				(o instanceof Dimensional2DType<?> dimtype) ? dimtype.getWrappedWidth().doubleValue() == width &&
				dimtype.getWrappedWidth().doubleValue() == height : false;
		}
		
		public @Override DoubleSize2D clone() {
			return new DoubleSize2D(width, height);
		}
		
		public @Override DoubleSize2D getSize() {
			return new DoubleSize2D(width, height);
		}
		public @Override DoubleBounds2D getBounds() {
			return new DoubleBounds2D(this);
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
