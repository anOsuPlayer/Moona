package moonaframework.design.monodimensional.geometry;

import moonaframework.design.monodimensional.Dimensional1DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.Bounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.DoubleBounds1D;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds.IntegralBounds1D;

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
		
		public IntegralSize1D(int width) {
			this.width = width;
		}
		public IntegralSize1D(Integer width) throws NullPointerException {
			this(width.intValue());
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
		
		public Size1D(float width) {
			this.width = width;
		}
		public Size1D(Float width) throws NullPointerException {
			this(width.floatValue());
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
		
		public DoubleSize1D(double width) {
			this.width = width;
		}
		public DoubleSize1D(Double width) throws NullPointerException {
			this(width.doubleValue());
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
