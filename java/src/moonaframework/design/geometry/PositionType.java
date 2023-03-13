package moonaframework.design.geometry;

public interface PositionType<T extends Number> extends PositionalType<T> {
	
	public static class IntegralPosition implements PositionType<Integer>, IntegralPositional {
		
		protected int[] pos;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(pos.length);
		}
		
		public @Override int getX() {
			return pos[0];
		}
		
		public @Override int getY() {
			return pos[1];
		}
		
		public @Override int getZ() {
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + getX() + "; " + getY() + "; " + getZ() + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof IntegralPositional pos) {
				return getX() == pos.getX() && getY() == pos.getY() && getZ() == pos.getZ();
			}
			else if (o instanceof PositionalType<?> pos) {
				return pos.getWrappedX().intValue() == getX() && pos.getWrappedY().intValue() == getY() &&
						pos.getWrappedZ().intValue() == getZ();
			}
			return false;
		}
		
		public @Override IntegralPosition clone() {
			return new IntegralPosition(this);
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
	}
	
	public static class Position implements PositionType<Float>, Positional {
		
		protected float[] pos;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(pos.length);
		}
		
		public @Override float getX() {
			return pos[0];
		}
		
		public @Override float getY() {
			return pos[1];
		}
		
		public @Override float getZ() {
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + getX() + "; " + getY() + "; " + getZ() + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof Positional pos) {
				return getX() == pos.getX() && getY() == pos.getY() && getZ() == pos.getZ();
			}
			else if (o instanceof PositionalType<?> pos) {
				return pos.getWrappedX().floatValue() == getX() && pos.getWrappedY().floatValue() == getY() &&
						pos.getWrappedZ().floatValue() == getZ();
			}
			return false;
		}
		
		public @Override Position clone() {
			return new Position(this);
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
	}

	public static class DoublePosition implements PositionType<Double>, DoublePositional {
		
		protected double[] pos;
		
		public @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(pos.length);
		}
		
		public @Override double getX() {
			return pos[0];
		}
		
		public @Override double getY() {
			return pos[1];
		}
		
		public @Override double getZ() {
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + getX() + "; " + getY() + "; " + getZ() + " ]";
		}
		
		public @Override boolean equals(Object o) {
			if (o instanceof DoublePositional pos) {
				return getX() == pos.getX() && getY() == pos.getY() && getZ() == pos.getZ();
			}
			else if (o instanceof PositionalType<?> pos) {
				return pos.getWrappedX().doubleValue() == getX() && pos.getWrappedY().doubleValue() == getY() &&
						pos.getWrappedZ().doubleValue() == getZ();
			}
			return false;
		}
		
		public @Override DoublePosition clone() {
			return new DoublePosition(this);
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
	}
}
