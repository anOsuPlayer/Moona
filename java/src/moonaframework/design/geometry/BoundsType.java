package moonaframework.design.geometry;

import moonaframework.design.WatchDog;
import moonaframework.design.geometry.DimensionalType.IntegralDimensional;
import moonaframework.design.geometry.SizeType.IntegralSize;

public interface BoundsType<T extends Number> extends SizeType<T> {
	
	public static class IntegralBounds extends IntegralSize implements BoundsType<Integer> {
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getWidth();
				case BIDIMENSIONAL: yield getWidth() + " x " + getHeight();
				case TRIDIMENSIONAL: yield getWidth() + " x " + getHeight() + " x " + getDepth();
			} + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof IntegralDimensional dim) {
				return getWidth() == dim.getWidth() && getHeight() == dim.getHeight() && getDepth() == dim.getDepth();
			}
			else if (o instanceof DimensionalType<?> dim) {
				return dim.getWrappedWidth().intValue() == getWidth() && dim.getWrappedHeight().intValue() == getHeight() &&
						dim.getWrappedDepth().intValue() == getDepth();
			}
			return false;
		}
		
		public @Override IntegralBounds clone() {
			return new IntegralBounds(this);
		}
		
		public IntegralBounds(int width, int height, int depth) throws IllegalArgumentException {
			super(width, height, depth);
			WatchDog.requiresPositive(width, height, depth);
		}
		public IntegralBounds(int width, int height) throws IllegalArgumentException {
			super(width, height);
			WatchDog.requiresPositive(width, height);
		}
		public IntegralBounds(int width) throws IllegalArgumentException {
			super(width);
			WatchDog.requiresPositive(width);
		}
		public IntegralBounds(IntegralDimensional dim) throws NullPointerException {
			super(dim);
			super.dim[0] = (super.dim[0] < 0) ? super.dim[0] * -1 : super.dim[0];
			if (dim.order() == DimensionalOrder.BIDIMENSIONAL) {
				super.dim[1] = (super.dim[1] < 0) ? super.dim[1] * -1 : super.dim[1];
			}
			if (dim.order() == DimensionalOrder.TRIDIMENSIONAL) {
				super.dim[2] = (super.dim[2] < 0) ? super.dim[2] * -1 : super.dim[2];
			}
		}
	}
	
	public static class Bounds extends Size implements BoundsType<Float> {
		
		public Bounds(float width, float height, float depth) throws IllegalArgumentException {
			super(width, height, depth);
			WatchDog.requiresPositive(width, height, depth);
		}
		public Bounds(float width, float height) throws IllegalArgumentException {
			super(width, height);
			WatchDog.requiresPositive(width, height);
		}
		public Bounds(float width) throws IllegalArgumentException {
			super(width);
			WatchDog.requiresPositive(width);
		}
		public Bounds(Dimensional dim) throws NullPointerException {
			super(dim);
			super.dim[0] = (super.dim[0] < 0) ? super.dim[0] * -1 : super.dim[0];
			if (dim.order() == DimensionalOrder.BIDIMENSIONAL) {
				super.dim[1] = (super.dim[1] < 0) ? super.dim[1] * -1 : super.dim[1];
			}
			if (dim.order() == DimensionalOrder.TRIDIMENSIONAL) {
				super.dim[2] = (super.dim[2] < 0) ? super.dim[2] * -1 : super.dim[2];
			}
		}
	}
	
	public static class DoubleBounds extends DoubleSize implements BoundsType<Double> {
		
		public DoubleBounds(double width, double height, double depth) throws IllegalArgumentException {
			super(width, height, depth);
			WatchDog.requiresPositive(width, height, depth);
		}
		public DoubleBounds(double width, double height) throws IllegalArgumentException {
			super(width, height);
			WatchDog.requiresPositive(width, height);
		}
		public DoubleBounds(double width) throws IllegalArgumentException {
			super(width);
			WatchDog.requiresPositive(width);
		}
		public DoubleBounds(DoubleDimensional dim) throws NullPointerException {
			super(dim);
			super.dim[0] = (super.dim[0] < 0) ? super.dim[0] * -1 : super.dim[0];
			if (dim.order() == DimensionalOrder.BIDIMENSIONAL) {
				super.dim[1] = (super.dim[1] < 0) ? super.dim[1] * -1 : super.dim[1];
			}
			if (dim.order() == DimensionalOrder.TRIDIMENSIONAL) {
				super.dim[2] = (super.dim[2] < 0) ? super.dim[2] * -1 : super.dim[2];
			}
		}
	}
}
