package moonaframework.design.monodimensional.geometry;

import moonaframework.design.WatchDog;
import moonaframework.design.monodimensional.Dimensional1DType;

public interface MonodimensionalBounds<T extends Number> extends MonodimensionalSize<T> {
	
	public static class IntegralBounds1D extends IntegralSize1D implements MonodimensionalBounds<Integer> {
		
		public @Override String toString() {
			return "| " + width + " |";
		}
		
		public @Override IntegralBounds1D clone() {
			return new IntegralBounds1D(width);
		}
		
		public IntegralBounds1D(int width) throws IllegalArgumentException {
			WatchDog.requiresPositive(width);
			this.width = width;
		}
		public IntegralBounds1D(Integer width) throws IllegalArgumentException, NullPointerException {
			this(width.intValue());
		}
		public IntegralBounds1D(IntegralDimensional1D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1);
		}
		public IntegralBounds1D(Dimensional1DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().intValue() > 0) ? dim.getWrappedWidth().intValue() :
				dim.getWrappedWidth().intValue() * -1);
		}
	}
	
	public static class Bounds1D extends Size1D implements MonodimensionalBounds<Float> {
		
		public @Override String toString() {
			return "| " + width + " |";
		}
		
		public @Override Bounds1D clone() {
			return new Bounds1D(width);
		}
		
		public Bounds1D(float width) throws IllegalArgumentException {
			WatchDog.requiresPositive(width);
			this.width = width;
		}
		public Bounds1D(Float width) throws IllegalArgumentException, NullPointerException {
			this(width.floatValue());
		}
		public Bounds1D(Dimensional1D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1);
		}
		public Bounds1D(Dimensional1DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().floatValue() > 0) ? dim.getWrappedWidth().floatValue() :
				dim.getWrappedWidth().floatValue() * -1);
		}
	}
	
	public static class DoubleBounds1D extends DoubleSize1D implements MonodimensionalBounds<Double> {
		
		public @Override String toString() {
			return "| " + width + " |";
		}
		
		public @Override DoubleBounds1D clone() {
			return new DoubleBounds1D(width);
		}
		
		public DoubleBounds1D(double width) throws IllegalArgumentException {
			WatchDog.requiresPositive(width);
			this.width = width;
		}
		public DoubleBounds1D(Double width) throws IllegalArgumentException, NullPointerException {
			this(width.doubleValue());
		}
		public DoubleBounds1D(Dimensional1D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1);
		}
		public DoubleBounds1D(Dimensional1DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue() :
				dim.getWrappedWidth().doubleValue() * -1);
		}
	}
}
