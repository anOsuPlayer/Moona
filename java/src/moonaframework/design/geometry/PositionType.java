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
		
		public @Override int getY() throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensional.");
			}
			return pos[1];
		}
		
		public @Override int getZ() throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensional.");
			}
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " ]";
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
		public IntegralPosition(int x, int y) {
			this.pos = new int[] {x, y};
		}
		public IntegralPosition(int x) {
			this.pos = new int[] {x};
		}
		public IntegralPosition(IntegralPositional pos) throws NullPointerException {
			this.pos = pos.toArray();
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
		
		public @Override float getY() throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensional.");
			}
			return pos[1];
		}
		
		public @Override float getZ() throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensional.");
			}
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " ]";
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
		public Position(float x, float y) {
			this.pos = new float[] {x, y};
		}
		public Position(float x) {
			this.pos = new float[] {x};
		}
		public Position(Positional pos) throws NullPointerException {
			this.pos = pos.toArray();
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
		
		public @Override double getY() throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensional.");
			}
			return pos[1];
		}
		
		public @Override double getZ() throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensional.");
			}
			return pos[2];
		}
		
		public @Override String toString() {
			return "[ " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " ]";
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
		public DoublePosition(double x, double y) {
			this.pos = new double[] {x, y};
		}
		public DoublePosition(double x) {
			this.pos = new double[] {x};
		}
		public DoublePosition(DoublePositional pos) throws NullPointerException {
			this.pos = pos.toArray();
		}
	}
}
