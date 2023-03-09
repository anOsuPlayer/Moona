package moonaframework.design.geometry.bidimensional;

import moonaframework.design.WatchDog;
import moonaframework.design.geometry.Coordinate;
import moonaframework.design.geometry.Size;
import moonaframework.design.geometry.monodimensional.MonodimensionalBounds;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;

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
		public IntegralBounds2D(IntegralDimensional3D dim, Size width, Size height) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
			switch (height) {
				case WIDTH -> this.height = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.height = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.height = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public IntegralBounds2D(IntegralDimensional2D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public IntegralBounds2D(Dimensional2DType<?> dim) throws NullPointerException {
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
		public Bounds2D(Dimensional3D dim, Size width, Size height) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
			switch (height) {
				case WIDTH -> this.height = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.height = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.height = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public Bounds2D(Dimensional2D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public Bounds2D(Dimensional2DType<?> dim) throws NullPointerException {
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
		public DoubleBounds2D(DoubleDimensional3D dim, Size width, Size height) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
			switch (height) {
				case WIDTH -> this.height = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.height = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.height = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public DoubleBounds2D(DoubleDimensional2D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1,
					(dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1);
		}
		public DoubleBounds2D(Dimensional2DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue() :
				dim.getWrappedWidth().doubleValue() * -1, (dim.getWrappedWidth().doubleValue() > 0) ?
				dim.getWrappedWidth().doubleValue() : dim.getWrappedWidth().doubleValue() * -1);
		}
	}
}
