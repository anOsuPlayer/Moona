package moonaframework.design.geometry.tridimensional;

import moonaframework.design.WatchDog;
import moonaframework.design.geometry.bidimensional.BidimensionalBounds;

public interface TridimensionalBounds<T extends Number> extends TridimensionalSize<T>, BidimensionalBounds<T> {

	public static class IntegralBounds3D extends IntegralSize3D implements TridimensionalBounds<Integer> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " x " + depth + " |";
		}
		
		public @Override IntegralBounds3D clone() {
			return new IntegralBounds3D(width, height, depth);
		}
		
		public IntegralBounds3D(int width, int height, int depth) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height, depth);
			this.width = width; this.height = height; this.depth = depth;
		}
		public IntegralBounds3D(Integer width, Integer height, Integer depth) throws IllegalArgumentException, NullPointerException {
			this(width.intValue(), height.intValue(), depth.intValue());
		}
		public IntegralBounds3D(IntegralDimensional3D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1,
							(dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1);
		}
		public IntegralBounds3D(Dimensional3DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().intValue() > 0) ? dim.getWrappedWidth().intValue() :
				dim.getWrappedWidth().intValue() * -1, (dim.getWrappedWidth().intValue() > 0) ?
				dim.getWrappedWidth().intValue() : dim.getWrappedWidth().intValue() * -1,
				(dim.getWrappedWidth().intValue() > 0) ? dim.getWrappedWidth().intValue()
						: dim.getWrappedWidth().intValue() * -1);
		}
	}
	
	public static class Bounds3D extends Size3D implements TridimensionalBounds<Float> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " x " + depth + " |";
		}
		
		public @Override Bounds3D clone() {
			return new Bounds3D(width, height, depth);
		}
		
		public Bounds3D(float width, float height, float depth) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height, depth);
			this.width = width; this.height = height; this.depth = depth;
		}
		public Bounds3D(Float width, Float height, Float depth) throws IllegalArgumentException, NullPointerException {
			this(width.floatValue(), height.floatValue(), depth.floatValue());
		}
		public Bounds3D(Dimensional3D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1,
							(dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1);
		}
		public Bounds3D(Dimensional3DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().floatValue() > 0) ? dim.getWrappedWidth().floatValue() :
				dim.getWrappedWidth().floatValue() * -1, (dim.getWrappedWidth().floatValue() > 0) ?
				dim.getWrappedWidth().floatValue() : dim.getWrappedWidth().floatValue() * -1,
				(dim.getWrappedWidth().floatValue() > 0) ? dim.getWrappedWidth().floatValue()
						: dim.getWrappedWidth().floatValue() * -1);
		}
	}

	public static class DoubleBounds3D extends DoubleSize3D implements TridimensionalBounds<Double> {
		
		public @Override String toString() {
			return "| " + width + " x " + height + " x " + depth + " |";
		}
		
		public @Override DoubleBounds3D clone() {
			return new DoubleBounds3D(width, height, depth);
		}
		
		public DoubleBounds3D(double width, double height, double depth) throws IllegalArgumentException {
			WatchDog.requiresPositive(width, height, depth);
			this.width = width; this.height = height; this.depth = depth;
		}
		public DoubleBounds3D(Double width, Double height, Double depth) throws IllegalArgumentException, NullPointerException {
			this(width.doubleValue(), height.doubleValue(), depth.doubleValue());
		}
		public DoubleBounds3D(DoubleDimensional3D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1,
							(dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1);
		}
		public DoubleBounds3D(Dimensional3DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue() :
				dim.getWrappedWidth().doubleValue() * -1, (dim.getWrappedWidth().doubleValue() > 0) ?
				dim.getWrappedWidth().doubleValue() : dim.getWrappedWidth().doubleValue() * -1,
				(dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue()
						: dim.getWrappedWidth().doubleValue() * -1);
		}
	}
}
