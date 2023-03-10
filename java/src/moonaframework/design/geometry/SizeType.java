package moonaframework.design.geometry;

public interface SizeType<T extends Number> extends DimensionalType<T> {

	public static class IntegralSize implements SizeType<Integer>, IntegralDimensional {
		
		protected int[] pos;
		
		public @Override int getWidth() {
			return pos[0];
		}
		
		public @Override int getHeight() {
			return pos[1];
		}
		
		public @Override int getDepth() {
			return pos[2];
		}
		
		public IntegralSize(int width, int height, int depth) {
			this.pos = new int[] {width, height, depth};
		}
		public IntegralSize(Integer width, Integer height, Integer depth) throws NullPointerException {
			this(width.intValue(), height.intValue(), depth.intValue());
		}
		public IntegralSize(IntegralDimensional pos) throws NullPointerException {
			this(pos.getWidth(), pos.getHeight(), pos.getDepth());
		}
		public IntegralSize(DimensionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedWidth().intValue(), pos.getWrappedHeight().intValue(), pos.getWrappedDepth().intValue());
		}
		public IntegralSize() {
			this(0, 0, 0);
		}
	}
	
	public static class Size implements SizeType<Float>, Dimensional {
		
		protected float[] pos;
		
		public @Override float getWidth() {
			return pos[0];
		}
		
		public @Override float getHeight() {
			return pos[1];
		}
		
		public @Override float getDepth() {
			return pos[2];
		}
		
		public Size(float width, float height, float depth) {
			this.pos = new float[] {width, height, depth};
		}
		public Size(Float width, Float height, Float depth) throws NullPointerException {
			this(width.floatValue(), height.floatValue(), depth.floatValue());
		}
		public Size(Dimensional pos) throws NullPointerException {
			this(pos.getWidth(), pos.getHeight(), pos.getDepth());
		}
		public Size(DimensionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedWidth().floatValue(), pos.getWrappedHeight().floatValue(), pos.getWrappedDepth().floatValue());
		}
		public Size() {
			this(0, 0, 0);
		}
	}

	public static class DoubleSize implements SizeType<Double>, DoubleDimensional {
		
		protected double[] pos;
		
		public @Override double getWidth() {
			return pos[0];
		}
		
		public @Override double getHeight() {
			return pos[1];
		}
		
		public @Override double getDepth() {
			return pos[2];
		}
		
		public DoubleSize(double width, double height, double depth) {
			this.pos = new double[] {width, height, depth};
		}
		public DoubleSize(Double width, Double height, Double depth) throws NullPointerException {
			this(width.doubleValue(), height.doubleValue(), depth.doubleValue());
		}
		public DoubleSize(DoubleDimensional pos) throws NullPointerException {
			this(pos.getWidth(), pos.getHeight(), pos.getDepth());
		}
		public DoubleSize(DimensionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedWidth().doubleValue(), pos.getWrappedHeight().doubleValue(), pos.getWrappedDepth().doubleValue());
		}
		public DoubleSize() {
			this(0, 0, 0);
		}
	}
}
