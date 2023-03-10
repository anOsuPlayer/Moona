package moonaframework.design.geometry;

public interface PositionType<T extends Number> extends PositionalType<T> {

	public static class IntegralPosition implements PositionType<Integer>, IntegralPositional {
		
		protected int[] pos;
		
		public @Override int getX() {
			return pos[0];
		}
		
		public @Override int getY() {
			return pos[1];
		}
		
		public @Override int getZ() {
			return pos[2];
		}
		
		public IntegralPosition(int x, int y, int z) {
			this.pos = new int[] {x, y, z};
		}
		public IntegralPosition(Integer x, Integer y, Integer z) throws NullPointerException {
			this(x.intValue(), y.intValue(), z.intValue());
		}
		public IntegralPosition(IntegralPositional pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public IntegralPosition(PositionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().intValue(), pos.getWrappedY().intValue(), pos.getWrappedZ().intValue());
		}
		public IntegralPosition() {
			this(0, 0, 0);
		}
	}
	
	public static class Position implements PositionType<Float>, Positional {
		
		protected float[] pos;
		
		public @Override float getX() {
			return pos[0];
		}
		
		public @Override float getY() {
			return pos[1];
		}
		
		public @Override float getZ() {
			return pos[2];
		}
		
		public Position(float x, float y, float z) {
			this.pos = new float[] {x, y, z};
		}
		public Position(Float x, Float y, Float z) throws NullPointerException {
			this(x.floatValue(), y.floatValue(), z.floatValue());
		}
		public Position(Positional pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public Position(PositionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().floatValue(), pos.getWrappedY().floatValue(), pos.getWrappedZ().floatValue());
		}
		public Position() {
			this(0, 0, 0);
		}
	}

	public static class DoublePosition implements PositionType<Double>, DoublePositional {
		
		protected double[] pos;
		
		public @Override double getX() {
			return pos[0];
		}
		
		public @Override double getY() {
			return pos[1];
		}
		
		public @Override double getZ() {
			return pos[2];
		}
		
		public DoublePosition(double x, double y, double z) {
			this.pos = new double[] {x, y, z};
		}
		public DoublePosition(Double x, Double y, Double z) throws NullPointerException {
			this(x.doubleValue(), y.doubleValue(), z.doubleValue());
		}
		public DoublePosition(DoublePositional pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public DoublePosition(PositionalType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().doubleValue(), pos.getWrappedY().doubleValue(), pos.getWrappedZ().doubleValue());
		}
		public DoublePosition() {
			this(0, 0, 0);
		}
	}
}
