package moonaframework.design.geometry;

public interface SizeType<T extends Number> extends DimensionalType<T> {

	public static class IntegralSize implements SizeType<Integer>, IntegralDimensional {
		
		protected int[] dim;
		
		public @Override DimensionalOrder getOrder() {
			return DimensionalOrder.fromNumber(dim.length);
		}
		
		public @Override int getWidth() {
			return dim[0];
		}
		
		public @Override int getHeight() {
			return dim[1];
		}
		
		public @Override int getDepth() {
			return dim[2];
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
		public IntegralSize(Integer width, Integer height, Integer depth) throws NullPointerException {
			this(width.intValue(), height.intValue(), depth.intValue());
		}
		public IntegralSize(Integer width, Integer height) throws NullPointerException {
			this(width.intValue(), height.intValue());
		}
		public IntegralSize(Integer width) throws NullPointerException {
			this(width.intValue());
		}
		public IntegralSize(IntegralDimensional dim) throws NullPointerException {
			this.dim = dim.toArray();
		}
		public IntegralSize(DimensionalOrder ord) throws NullPointerException {
			this.dim = switch (ord) {
				case MONODIMENSIONAL: yield new int[] {0};
				case BIDIMENSIONAL: yield new int[] {0, 0};
				case TRIDIMENSIONAL: yield new int[] {0, 0, 0};
			};
		}
	}
	
	public static class Size implements SizeType<Float>, Dimensional {
		
		protected float[] dim;
		
		public @Override DimensionalOrder getOrder() {
			return DimensionalOrder.fromNumber(dim.length);
		}
		
		public @Override float getWidth() {
			return dim[0];
		}
		
		public @Override float getHeight() {
			return dim[1];
		}
		
		public @Override float getDepth() {
			return dim[2];
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
		public Size(Float width, Float height, Float depth) throws NullPointerException {
			this(width.floatValue(), height.floatValue(), depth.floatValue());
		}
		public Size(Float width, Float height) throws NullPointerException {
			this(width.floatValue(), height.floatValue());
		}
		public Size(Float width) throws NullPointerException {
			this(width.floatValue());
		}
		public Size(Dimensional dim) throws NullPointerException {
			this.dim = dim.toArray();
		}
		public Size(DimensionalOrder ord) throws NullPointerException {
			this.dim = switch (ord) {
				case MONODIMENSIONAL: yield new float[] {0};
				case BIDIMENSIONAL: yield new float[] {0, 0};
				case TRIDIMENSIONAL: yield new float[] {0, 0, 0};
			};
		}
	}

	public static class DoubleSize implements SizeType<Double>, DoubleDimensional {
		
		protected double[] dim;
		
		public @Override DimensionalOrder getOrder() {
			return DimensionalOrder.fromNumber(dim.length);
		}
		
		public @Override double getWidth() {
			return dim[0];
		}
		
		public @Override double getHeight() {
			return dim[1];
		}
		
		public @Override double getDepth() {
			return dim[2];
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
		public DoubleSize(Double width, Double height, Double depth) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue(), depth.doubleValue());
		}
		public DoubleSize(Double width, Double height) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue());
		}
		public DoubleSize(Double width) throws NullPointerException {
			this(width.doubleValue());
		}
		public DoubleSize(DoubleDimensional dim) throws NullPointerException {
			this.dim = dim.toArray();
		}
		public DoubleSize(DimensionalOrder ord) throws NullPointerException {
			this.dim = switch (ord) {
				case MONODIMENSIONAL: yield new double[] {0};
				case BIDIMENSIONAL: yield new double[] {0, 0};
				case TRIDIMENSIONAL: yield new double[] {0, 0, 0};
			};
		}
	}
}
