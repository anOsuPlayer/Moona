package moonaframework.design.geometry;

public interface DimensionType<T extends Number> extends SizeType<T>, ResizableType<T> {

	public static class IntegralDimension extends IntegralSize implements DimensionType<Integer>, IntegralResizable {
		
		public @Override void setWidth(int width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(int height) {
			super.dim[1] = height;
		}
		
		public @Override void setDepth(int depth) {
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " x " + getHeight() + " x " + getDepth() + " )";
		}
		
		public @Override IntegralDimension clone() {
			return new IntegralDimension(this);
		}
		
		public IntegralDimension(int width, int height, int depth) {
			super(width, height, depth);
		}
		public IntegralDimension(Integer width, Integer height, Integer depth) throws NullPointerException {
			super(width, height, depth);
		}
		public IntegralDimension(IntegralDimensional dim) throws NullPointerException {
			super(dim);
		}
	}
	
	public static class Dimension extends Size implements DimensionType<Float>, Resizable {
		
		public @Override void setWidth(float width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(float height) {
			super.dim[1] = height;
		}
		
		public @Override void setDepth(float depth) {
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " x " + getHeight() + " x " + getDepth() + " )";
		}
		
		public @Override Dimension clone() {
			return new Dimension(this);
		}
		
		public Dimension(float width, float height, float depth) {
			super(width, height, depth);
		}
		public Dimension(Float width, Float height, Float depth) throws NullPointerException {
			super(width, height, depth);
		}
		public Dimension(Dimensional dim) throws NullPointerException {
			super(dim);
		}
	}

	public static class DoubleDimension extends DoubleSize implements DimensionType<Double>, DoubleResizable {
		
		public @Override void setWidth(double width) {
			super.dim[0] = width;
		}
		
		public @Override void setHeight(double height) {
			super.dim[1] = height;
		}
		
		public @Override void setDepth(double depth) {
			super.dim[2] = depth;
		}
		
		public @Override String toString() {
			return "( " + getWidth() + " x " + getHeight() + " x " + getDepth() + " )";
		}
		
		public @Override DoubleDimension clone() {
			return new DoubleDimension(this);
		}
		
		public DoubleDimension(int width, int height, int depth) {
			super(width, height, depth);
		}
		public DoubleDimension(Double width, Double height, Double depth) throws NullPointerException {
			super(width, height, depth);
		}
		public DoubleDimension(DoubleDimensional dim) throws NullPointerException {
			super(dim);
		}
	}
}
