package moonaframework.design.geometry.monodimensional;

import moonaframework.design.geometry.Size;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.Dimensional2D;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.DoubleDimensional2D;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.IntegralDimensional2D;
import moonaframework.design.geometry.monodimensional.MonodimensionalBounds.Bounds1D;
import moonaframework.design.geometry.monodimensional.MonodimensionalBounds.DoubleBounds1D;
import moonaframework.design.geometry.monodimensional.MonodimensionalBounds.IntegralBounds1D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;

public interface MonodimensionalSize<T extends Number> extends Dimensional1DType<T>, Cloneable {
	
	public static class IntegralSize1D implements MonodimensionalSize<Integer>, IntegralDimensional1D {
		
		protected int width;
		
		public @Override int getWidth() {
			return this.width;
		}
		
		public @Override IntegralSize1D getSize() {
			return new IntegralSize1D(width);
		}
		public @Override IntegralBounds1D getBounds() {
			return new IntegralBounds1D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof IntegralDimensional1D dim) ? dim.getWidth() == width :
				(o instanceof Dimensional1DType<?> dimtype) ? dimtype.getWrappedWidth().intValue() == width :
				false;
		}
		
		public @Override IntegralSize1D clone() {
			return new IntegralSize1D(width);
		}
		
		public IntegralSize1D(int width) {
			this.width = width;
		}
		public IntegralSize1D(Integer width) throws NullPointerException {
			this(width.intValue());
		}
		public IntegralSize1D(IntegralDimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> this.width = dim.getDepth();
			}
		}
		public IntegralSize1D(IntegralDimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
		}
		public IntegralSize1D(IntegralDimensional1D dim) throws NullPointerException {
			this(dim.getWidth());
		}
		public IntegralSize1D(Dimensional1DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().intValue());
		}
		public IntegralSize1D() {
			this(0);
		}
	}
	
	public static class Size1D implements MonodimensionalSize<Float>, Dimensional1D {
		
		protected float width;
		
		public @Override float getWidth() {
			return this.width;
		}
		
		public @Override Size1D getSize() {
			return new Size1D(width);
		}
		public @Override Bounds1D getBounds() {
			return new Bounds1D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof Dimensional1D dim) ? dim.getWidth() == width :
				(o instanceof Dimensional1DType<?> dimtype) ? dimtype.getWrappedWidth().floatValue() == width :
				false;
		}
		
		public @Override Size1D clone() {
			return new Size1D(width);
		}
		
		public Size1D(float width) {
			this.width = width;
		}
		public Size1D(Float width) throws NullPointerException {
			this(width.floatValue());
		}
		public Size1D(Dimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> this.width = dim.getDepth();
			}
		}
		public Size1D(Dimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
		}
		public Size1D(IntegralDimensional1D dim) throws NullPointerException {
			this(dim.getWidth());
		}
		public Size1D(Dimensional1DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().floatValue());
		}
		public Size1D() {
			this(0);
		}
	}

	public static class DoubleSize1D implements MonodimensionalSize<Double>, DoubleDimensional1D {
		
		protected double width;
		
		public @Override double getWidth() {
			return this.width;
		}
		
		public @Override DoubleSize1D getSize() {
			return new DoubleSize1D(width);
		}
		public @Override DoubleBounds1D getBounds() {
			return new DoubleBounds1D(this);
		}
		
		public @Override String toString() {
			return "[ " + width + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof DoubleDimensional1D dim) ? dim.getWidth() == width :
				(o instanceof Dimensional1DType<?> dimtype) ? dimtype.getWrappedWidth().doubleValue() == width :
				false;
		}
		
		public @Override DoubleSize1D clone() {
			return new DoubleSize1D(width);
		}
		
		public DoubleSize1D(double width) {
			this.width = width;
		}
		public DoubleSize1D(Double width) throws NullPointerException {
			this(width.doubleValue());
		}
		public DoubleSize1D(DoubleDimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> this.width = dim.getDepth();
			}
		}
		public DoubleSize1D(DoubleDimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = dim.getWidth();
				case HEIGHT -> this.width = dim.getHeight();
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
		}
		public DoubleSize1D(IntegralDimensional1D dim) throws NullPointerException {
			this(dim.getWidth());
		}
		public DoubleSize1D(Dimensional1DType<?> dim) throws NullPointerException {
			this(dim.getWrappedWidth().doubleValue());
		}
		public DoubleSize1D() {
			this(0);
		}
	}
}
