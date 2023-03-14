package moonaframework.design.geometry;

public interface PointType<T extends Number> extends PositionType<T>, MovableType<T> {

	public static class IntegralPoint extends IntegralPosition implements PointType<Integer>, IntegralMovable {
		
		public @Override void setX(int x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(int y) throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.pos[1] = y;
		}
		
		public @Override void setZ(int z) throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " )";
		}
		
		public @Override IntegralPoint clone() {
			return new IntegralPoint(this);
		}
		
		public IntegralPoint(int x, int y, int z) {
			super(x, y, z);
		}
		public IntegralPoint(int x, int y) {
			super(x, y);
		}
		public IntegralPoint(int x) {
			super(x);
		}
		public IntegralPoint(IntegralPositional pos) throws NullPointerException {
			super(pos);
		}
	}
	
	public static class Point extends Position implements PointType<Float>, Movable {
		
		public @Override void setX(float x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(float y) throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.pos[1] = y;
		}
		
		public @Override void setZ(float z) throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " )";
		}
		
		public @Override Point clone() {
			return new Point(this);
		}
		
		public Point(float x, float y, float z) {
			super(x, y, z);
		}
		public Point(float x, float y) {
			super(x, y);
		}
		public Point(float x) {
			super(x);
		}
		public Point(Positional pos) throws NullPointerException {
			super(pos);
		}
	}

	public static class DoublePoint extends DoublePosition implements PointType<Double>, DoubleMovable {
		
		public @Override void setX(double x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(double y) throws LowerDimensionalOrderException {
			if (pos.length < 2) {
				throw new LowerDimensionalOrderException("This element is not Bidimensional or Tridimensionals.");
			}
			super.pos[1] = y;
		}
		
		public @Override void setZ(double z) throws LowerDimensionalOrderException {
			if (pos.length < 3) {
				throw new LowerDimensionalOrderException("This element is not Tridimensionals.");
			}
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + switch (order()) {
				case MONODIMENSIONAL: yield getX();
				case BIDIMENSIONAL: yield getX() + "; " + getY();
				case TRIDIMENSIONAL: yield getX() + "; " + getY() + "; " + getZ();
			} + " )";
		}
		
		public @Override DoublePoint clone() {
			return new DoublePoint(this);
		}
		
		public DoublePoint(double x, double y, double z) {
			super(x, y, z);
		}
		public DoublePoint(double x, double y) {
			super(x, y);
		}
		public DoublePoint(double x) {
			super(x);
		}
		public DoublePoint(DoublePositional pos) throws NullPointerException {
			super(pos);
		}
	}
}
