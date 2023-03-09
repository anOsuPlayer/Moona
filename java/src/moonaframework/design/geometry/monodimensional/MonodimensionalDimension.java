package moonaframework.design.geometry.monodimensional;

public interface MonodimensionalDimension<T extends Number> extends MonodimensionalSize<T>, Resizable1DType<T> {
	
	public static class IntegralDimension1D extends IntegralSize1D implements MonodimensionalDimension<Integer>, IntegralResizable1D {
		
		public @Override void setWidth(int width) {
			this.width = width;
		}
		
		public @Override String toString() {
			return "( " + width + " )";
		}
		
		public @Override IntegralDimension1D clone() {
			return new IntegralDimension1D(width);
		}
		
		public IntegralDimension1D(int width) {
			super(width);
		}
		public IntegralDimension1D(Integer width) throws NullPointerException {
			super(width);
		}
		public IntegralDimension1D(IntegralDimensional1D dim) throws NullPointerException {
			super(dim);
		}
		public IntegralDimension1D(Dimensional1DType<?> dim) throws NullPointerException {
			super(dim);
		}
		public IntegralDimension1D() {
			this(0);
		}
	}
	
	public static class Dimension1D extends Size1D implements MonodimensionalDimension<Float>, Resizable1D {
		
		public @Override void setWidth(float width) {
			this.width = width;
		}
		
		public @Override String toString() {
			return "( " + width + " )";
		}
		
		public @Override Dimension1D clone() {
			return new Dimension1D(width);
		}
		
		public Dimension1D(float width) {
			super(width);
		}
		public Dimension1D(Float width) throws NullPointerException {
			super(width);
		}
		public Dimension1D(Dimensional1D dim) throws NullPointerException {
			super(dim);
		}
		public Dimension1D(Dimensional1DType<?> dim) throws NullPointerException {
			super(dim);
		}
		public Dimension1D() {
			this(0);
		}
	}

	public static class DoubleDimension1D extends DoubleSize1D implements MonodimensionalDimension<Double>, DoubleResizable1D {
		
		public @Override void setWidth(double width) {
			this.width = width;
		}
		
		public @Override String toString() {
			return "( " + width + " )";
		}
		
		public @Override DoubleDimension1D clone() {
			return new DoubleDimension1D(width);
		}
		
		public DoubleDimension1D(double width) {
			super(width);
		}
		public DoubleDimension1D(Double width) throws NullPointerException {
			super(width);
		}
		public DoubleDimension1D(DoubleDimensional1D dim) throws NullPointerException {
			super(dim);
		}
		public DoubleDimension1D(Dimensional1DType<?> dim) throws NullPointerException {
			super(dim);
		}
		public DoubleDimension1D() {
			this(0);
		}
	}
}
