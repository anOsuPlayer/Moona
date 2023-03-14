package moonaframework.design.geometry;

public interface DimensionType<T extends Number> extends SizeType<T>, ResizableType<T> {

	public static class IntegralDimension extends IntegralSize implements DimensionType<Integer>, IntegralResizable {
		
		public @Override void setWidth(int width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(int height) throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.dim[1] = height;
		}
		
		public @Override void setDepth(int depth) throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " width " + getHeight() + " width " + getDepth() + " )";
		}
		
		public @Override IntegralDimension clone() {
			return new IntegralDimension(this);
		}
		
		public IntegralDimension(int width, int height, int depth) {
			super(width, height, depth);
		}
		public IntegralDimension(int width, int height) {
			super(width, height);
		}
		public IntegralDimension(int width) {
			super(width);
		}
		public IntegralDimension(IntegralDimensional dim) throws NullPointerException {
			super(dim);
		}
	}
	
	public static class Dimension extends Size implements DimensionType<Float>, Resizable {
		
		public @Override void setWidth(float width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(float height) throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.dim[1] = height;
		}
		
		public @Override void setDepth(float depth) throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " width " + getHeight() + " width " + getDepth() + " )";
		}
		
		public @Override Dimension clone() {
			return new Dimension(this);
		}
		
		public Dimension(float width, float height, float depth) {
			super(width, height, depth);
		}
		public Dimension(float width, float height) {
			super(width, height);
		}
		public Dimension(float width) {
			super(width);
		}
		public Dimension(Dimensional dim) throws NullPointerException {
			super(dim);
		}
	}

	public static class DoubleDimension extends DoubleSize implements DimensionType<Double>, DoubleResizable {
		
		public @Override void setWidth(double width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(double height) throws LowerDimensionalOrderException {
			if (dim.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.dim[1] = height;
		}
		
		public @Override void setDepth(double depth) throws LowerDimensionalOrderException {
			if (dim.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " width " + getHeight() + " width " + getDepth() + " )";
		}
		
		public @Override DoubleDimension clone() {
			return new DoubleDimension(this);
		}
		
		public DoubleDimension(double width, double height, double depth) {
			super(width, height, depth);
		}
		public DoubleDimension(double width, double height) {
			super(width, height);
		}
		public DoubleDimension(double width) {
			super(width);
		}
		public DoubleDimension(DoubleDimensional dim) throws NullPointerException {
			super(dim);
		}
	}
}
