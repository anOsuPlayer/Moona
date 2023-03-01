package moonaframework.design.bidimensional.geometry;

import moonaframework.design.WatchDog;
import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds;

public interface BidimensionalBounds<T extends Number> extends BidimensionalSize<T>, MonodimensionalBounds<T> {

	public static class IntegralBounds2D extends IntegralSize2D implements BidimensionalBounds<Integer> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " |";
		}
		
		public @Override IntegralBounds2D clone() {
			return new IntegralBounds2D(width, height);
		}
		
		public IntegralBounds2D(int width, int height) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height);
			this.width = width; this.height = height;
		}
		public IntegralBounds2D(Integer width, Integer height) throws IllegalArgumentException, NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public IntegralBounds2D(IntegralDimensional2D dim) {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public IntegralBounds2D(Dimensional2DType<?> dim) {
			this((dim.getWrappedWidth().intValue() > 0) ? dim.getWrappedWidth().intValue() :
				dim.getWrappedWidth().intValue() * -1, (dim.getWrappedWidth().intValue() > 0) ?
				dim.getWrappedWidth().intValue() : dim.getWrappedWidth().intValue() * -1);
		}
	}
	
	public static class Bounds2D extends Size2D implements BidimensionalBounds<Float> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " |";
		}
		
		public @Override Bounds2D clone() {
			return new Bounds2D(width, height);
		}
		
		public Bounds2D(float width, float height) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height);
			this.width = width; this.height = height;
		}
		public Bounds2D(Float width, Float height) throws IllegalArgumentException, NullPointerException {
			this(width.floatValue(), height.floatValue());
		}
		public Bounds2D(Dimensional2D dim) {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public Bounds2D(Dimensional2DType<?> dim) {
			this((dim.getWrappedWidth().floatValue() > 0) ? dim.getWrappedWidth().floatValue() :
				dim.getWrappedWidth().floatValue() * -1, (dim.getWrappedWidth().floatValue() > 0) ?
				dim.getWrappedWidth().floatValue() : dim.getWrappedWidth().floatValue() * -1);
		}
	}
	
	public static class DoubleBounds2D extends DoubleSize2D implements BidimensionalBounds<Double> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " |";
		}
		
		public @Override DoubleBounds2D clone() {
			return new DoubleBounds2D(width, height);
		}
		
		public DoubleBounds2D(double width, double height) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height);
			this.width = width; this.height = height;
		}
		public DoubleBounds2D(Double width, Double height) throws IllegalArgumentException, NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public DoubleBounds2D(DoubleDimensional2D dim) {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public DoubleBounds2D(Dimensional2DType<?> dim) {
			this((dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue() :
				dim.getWrappedWidth().doubleValue() * -1, (dim.getWrappedWidth().doubleValue() > 0) ?
				dim.getWrappedWidth().doubleValue() : dim.getWrappedWidth().doubleValue() * -1);
		}
	}
}