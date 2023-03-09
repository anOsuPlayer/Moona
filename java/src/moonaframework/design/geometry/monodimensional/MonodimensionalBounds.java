package moonaframework.design.geometry.monodimensional;

import moonaframework.design.WatchDog;
import moonaframework.design.geometry.Size;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.Dimensional2D;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.DoubleDimensional2D;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.IntegralDimensional2D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;
import moonaframework.util.exception.NullArgumentException;

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
		public IntegralBounds1D(IntegralDimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public IntegralBounds1D(IntegralDimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
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
		public Bounds1D(Dimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public Bounds1D(Dimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
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
		public DoubleBounds1D(DoubleDimensional3D dim, Size width) throws NullPointerException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> this.width = (dim.getDepth() > 0) ? dim.getDepth() : dim.getDepth() * -1;
			}
		}
		public DoubleBounds1D(DoubleDimensional2D dim, Size width) throws NullPointerException, IllegalArgumentException {
			switch (width) {
				case WIDTH -> this.width = (dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1;
				case HEIGHT -> this.width = (dim.getHeight() > 0) ? dim.getHeight() : dim.getHeight() * -1;
				case DEPTH -> throw new IllegalArgumentException("Bidimensional Elements do not have any depth.");
			}
		}
		public DoubleBounds1D(DoubleDimensional1D dim) throws NullPointerException {
			this((dim.getWidth() > 0) ? dim.getWidth() : dim.getWidth() * -1);
		}
		public DoubleBounds1D(Dimensional1DType<?> dim) throws NullPointerException {
			this((dim.getWrappedWidth().doubleValue() > 0) ? dim.getWrappedWidth().doubleValue() :
				dim.getWrappedWidth().doubleValue() * -1);
		}
	}
}
