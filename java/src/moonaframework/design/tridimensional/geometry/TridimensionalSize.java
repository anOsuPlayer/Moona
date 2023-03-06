package moonaframework.design.tridimensional.geometry;

import moonaframework.design.bidimensional.geometry.BidimensionalSize;
import moonaframework.design.tridimensional.Dimensional3DType;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.Bounds3D;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.DoubleBounds3D;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.IntegralBounds3D;

public interface TridimensionalSize<T extends Number> extends BidimensionalSize<T>, Dimensional3DType<T> {

	public static class IntegralSize3D implements TridimensionalSize<Integer>, IntegralDimensional3D, IntegralDimensional2D {
		
		protected int width;
		
		public @Override int getWidth() {
			return this.width;
		}
		
		protected int height;
		
		public @Override int getHeight() {
			return this.height;
		}
		
		protected int depth;
		
		public @Override int getDepth() {
			return this.depth;
		}
		
		public @Override IntegralSize3D getSize() {
			return new IntegralSize3D(width, height, depth);
		}
		public @Override IntegralBounds3D getBounds() {
			return new IntegralBounds3D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " x " + depth + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof IntegralDimensional3D dim) ? dim.getWidth() == width && dim.getHeight() == height && dim.getDepth() == depth :
				(o instanceof Dimensional3DType<?> dimtype) ? dimtype.getWrappedWidth().intValue() == width &&
				dimtype.getWrappedWidth().intValue() == height && dimtype.getWrappedDepth().intValue() == depth : false;
		}
		
		public @Override IntegralSize3D clone() {
			return new IntegralSize3D(width, height, depth);
		}

		public IntegralSize3D(int width, int height, int depth) {
			this.width = width; this.height = height; this.depth = depth;
		}
		public IntegralSize3D(Integer width, Integer height, Integer depth) throws NullPointerException {
			this(width.intValue(), height.intValue(), depth.intValue());
		}
		public IntegralSize3D(IntegralDimensional3D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
		public IntegralSize3D(Dimensional3DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().intValue(), dim.getWrappedHeight().intValue(), dim.getWrappedDepth().intValue());
		}
		public IntegralSize3D() {
			this(0, 0, 0);
		}
	}
	
	public static class Size3D implements TridimensionalSize<Float>, Dimensional3D, Dimensional2D {
		
		protected float width;
		
		public @Override float getWidth() {
			return this.width;
		}
		
		protected float height;
		
		public @Override float getHeight() {
			return this.height;
		}
		
		protected float depth;
		
		public @Override float getDepth() {
			return this.depth;
		}
		
		public @Override Size3D getSize() {
			return new Size3D(width, height, depth);
		}
		public @Override Bounds3D getBounds() {
			return new Bounds3D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " x " + depth + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof Dimensional3D dim) ? dim.getWidth() == width && dim.getHeight() == height && dim.getDepth() == depth :
				(o instanceof Dimensional3DType<?> dimtype) ? dimtype.getWrappedWidth().floatValue() == width &&
				dimtype.getWrappedWidth().floatValue() == height && dimtype.getWrappedDepth().floatValue() == depth : false;
		}
		
		public @Override Size3D clone() {
			return new Size3D(width, height, depth);
		}

		public Size3D(float width, float height, float depth) {
			this.width = width; this.height = height; this.depth = depth;
		}
		public Size3D(Float width, Float height, Float depth) throws NullPointerException {
			this(width.floatValue(), height.floatValue(), depth.floatValue());
		}
		public Size3D(IntegralDimensional3D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
		public Size3D(Dimensional3DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().floatValue(), dim.getWrappedHeight().floatValue(), dim.getWrappedDepth().floatValue());
		}
		public Size3D() {
			this(0, 0, 0);
		}
	}
	
	public static class DoubleSize3D implements TridimensionalSize<Double>, DoubleDimensional3D, DoubleDimensional2D {
		
		protected double width;
		
		public @Override double getWidth() {
			return this.width;
		}
		
		protected double height;
		
		public @Override double getHeight() {
			return this.height;
		}
		
		protected double depth;
		
		public @Override double getDepth() {
			return this.depth;
		}
		
		public @Override DoubleSize3D getSize() {
			return new DoubleSize3D(width, height, depth);
		}
		public @Override DoubleBounds3D getBounds() {
			return new DoubleBounds3D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " x " + height + " x " + depth + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof Dimensional3D dim) ? dim.getWidth() == width && dim.getHeight() == height && dim.getDepth() == depth :
				(o instanceof Dimensional3DType<?> dimtype) ? dimtype.getWrappedWidth().doubleValue() == width &&
				dimtype.getWrappedWidth().doubleValue() == height && dimtype.getWrappedDepth().doubleValue() == depth : false;
		}
		
		public @Override DoubleSize3D clone() {
			return new DoubleSize3D(width, height, depth);
		}

		public DoubleSize3D(double width, double height, double depth) {
			this.width = width; this.height = height; this.depth = depth;
		}
		public DoubleSize3D(Double width, Double height, Double depth) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue(), depth.doubleValue());
		}
		public DoubleSize3D(DoubleDimensional3D dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
		public DoubleSize3D(Dimensional3DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().doubleValue(), dim.getWrappedHeight().doubleValue(), dim.getWrappedDepth().doubleValue());
		}
		public DoubleSize3D() {
			this(0, 0, 0);
		}
	}
}
