package moonaframework.design.geometry;

public interface SizeType<T extends Number> extends DimensionalType<T> {

	public static class IntegralSize implements SizeType<Integer>, IntegralDimensional {
		
		protected int[] dim;
		
		public @Override int getWidth() {
			return dim[0];
		}
		
		public @Override int getHeight() {
			return dim[1];
		}
		
		public @Override int getDepth() {
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + getWidth() + " x " + getHeight() + " x " + getDepth() + " ]";
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
		
		public @Override IntegralSize clone() {
			return new IntegralSize(this);
		}
		
		public IntegralSize(int width, int height, int depth) {
			this.dim = new int[] {width, height, depth};
		}
		public IntegralSize(Integer width, Integer height, Integer depth) throws NullPointerException {
			this(width.intValue(), height.intValue(), depth.intValue());
		}
		public IntegralSize(IntegralDimensional dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
	}
	
	public static class Size implements SizeType<Float>, Dimensional {
		
		protected float[] dim;
		
		public @Override float getWidth() {
			return dim[0];
		}
		
		public @Override float getHeight() {
			return dim[1];
		}
		
		public @Override float getDepth() {
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + getWidth() + " x " + getHeight() + " x " + getDepth() + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof Dimensional dim) {
				return getWidth() == dim.getWidth() && getHeight() == dim.getHeight() && getDepth() == dim.getDepth();
			}
			else if (o instanceof DimensionalType<?> dim) {
				return dim.getWrappedWidth().floatValue() == getWidth() && dim.getWrappedHeight().floatValue() == getHeight() &&
						dim.getWrappedDepth().floatValue() == getDepth();
			}
			return false;
		}
		
		public @Override Size clone() {
			return new Size(this);
		}
		
		public Size(float width, float height, float depth) {
			this.dim = new float[] {width, height, depth};
		}
		public Size(Float width, Float height, Float depth) throws NullPointerException {
			this(width.floatValue(), height.floatValue(), depth.floatValue());
		}
		public Size(Dimensional dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
	}

	public static class DoubleSize implements SizeType<Double>, DoubleDimensional {
		
		protected double[] dim;
		
		public @Override double getWidth() {
			return dim[0];
		}
		
		public @Override double getHeight() {
			return dim[1];
		}
		
		public @Override double getDepth() {
			return dim[2];
		}
		
		public @Override String toString() {
			return "[ " + getWidth() + " x " + getHeight() + " x " + getDepth() + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof DoubleDimensional dim) {
				return getWidth() == dim.getWidth() && getHeight() == dim.getHeight() && getDepth() == dim.getDepth();
			}
			else if (o instanceof DimensionalType<?> dim) {
				return dim.getWrappedWidth().doubleValue() == getWidth() && dim.getWrappedHeight().doubleValue() == getHeight() &&
						dim.getWrappedDepth().doubleValue() == getDepth();
			}
			return false;
		}
		
		public @Override DoubleSize clone() {
			return new DoubleSize(this);
		}
		
		public DoubleSize(double width, double height, double depth) {
			this.dim = new double[] {width, height, depth};
		}
		public DoubleSize(Double width, Double height, Double depth) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue(), depth.doubleValue());
		}
		public DoubleSize(DoubleDimensional dim) throws NullPointerException {
			this(dim.getWidth(), dim.getHeight(), dim.getDepth());
		}
	}
}
