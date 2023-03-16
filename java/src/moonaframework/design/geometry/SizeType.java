package moonaframework.design.geometry;

import java.util.Arrays;

public interface SizeType<T extends Number> extends DimensionalType<T> {

	public static class IntegralSize implements SizeType<Integer>, IntegralDimensional {
		
		protected int[] dim;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(dim.length);
		}
		
		public @Override int getWidth() {
			return dim[0];
		}
		
		public @Override int getHeight() throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			return dim[1];
		}
		
		public @Override int getDepth() throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensional.");
			}
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getWidth();
				case BIDIMENSIONAL: yield getWidth() + " x " + getHeight();
				case TRIDIMENSIONAL: yield getWidth() + " x " + getHeight() + " x " + getDepth();
			} + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof IntegralDimensional dim) {
				return Arrays.equals(dim.toArray(), this.dim);
			}
			else if (o instanceof DimensionalType<?> dim) {
				return Arrays.equals(this.toWrappedArray(), dim.toWrappedArray());
			}
			return false;
		}
		
		public @Override IntegralSize clone() {
			return new IntegralSize(this);
		}
		
		public IntegralSize(int width, int height, int depth) {
			this.dim = new int[] {width, height, depth};
		}
		public IntegralSize(int width, int height) {
			this.dim = new int[] {width, height};
		}
		public IntegralSize(int width) {
			this.dim = new int[] {width};
		}
		public IntegralSize(IntegralDimensional pos) throws NullPointerException {
			this.dim = pos.toArray();
		}
	}
	
	public static class Size implements SizeType<Float>, Dimensional {
		
		protected float[] dim;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(dim.length);
		}
		
		public @Override float getWidth() {
			return dim[0];
		}
		
		public @Override float getHeight() throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			return dim[1];
		}
		
		public @Override float getDepth() throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getWidth();
				case BIDIMENSIONAL: yield getWidth() + " x " + getHeight();
				case TRIDIMENSIONAL: yield getWidth() + " x " + getHeight() + " x " + getDepth();
			} + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof Dimensional dim) {
				return Arrays.equals(dim.toArray(), this.dim);
			}
			else if (o instanceof DimensionalType<?> dim) {
				return Arrays.equals(this.toWrappedArray(), dim.toWrappedArray());
			}
			return false;
		}
		
		public @Override Size clone() {
			return new Size(this);
		}
		
		public Size(float width, float height, float depth) {
			this.dim = new float[] {width, height, depth};
		}
		public Size(float width, float height) {
			this.dim = new float[] {width, height};
		}
		public Size(float width) {
			this.dim = new float[] {width};
		}
		public Size(Dimensional dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
	}

	public static class DoubleSize implements SizeType<Double>, DoubleDimensional {
		
		protected double[] dim;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(dim.length);
		}
		
		public @Override double getWidth() {
			return dim[0];
		}
		
		public @Override double getHeight() throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			return dim[1];
		}
		
		public @Override double getDepth() throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getWidth();
				case BIDIMENSIONAL: yield getWidth() + " x " + getHeight();
				case TRIDIMENSIONAL: yield getWidth() + " x " + getHeight() + " x " + getDepth();
			} + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof DoubleDimensional dim) {
				return Arrays.equals(dim.toArray(), this.dim);
			}
			else if (o instanceof DimensionalType<?> dim) {
				return Arrays.equals(this.toWrappedArray(), dim.toWrappedArray());
			}
			return false;
		}
		
		public @Override DoubleSize clone() {
			return new DoubleSize(this);
		}
		
		public DoubleSize(double width, double height, double depth) {
			this.dim = new double[] {width, height, depth};
		}
		public DoubleSize(double width, double height) {
			this.dim = new double[] {width, height};
		}
		public DoubleSize(double width) {
			this.dim = new double[] {width};
		}
		public DoubleSize(DoubleDimensional dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
	}
}
