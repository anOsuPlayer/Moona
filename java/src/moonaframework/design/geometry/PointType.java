package moonaframework.design.geometry;

public interface PointType<T extends Number> extends PositionType<T>, MovableType<T> {

	public static class IntegralPoint extends IntegralPosition implements PointType<Integer>, IntegralMovable {
		
		public @Override void setX(int x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(int y) {
			super.pos[1] = y;
		}
		
		public @Override void setZ(int z) {
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + getX() + "; " + getY() + "; " + getZ() + " )";
		}
		
		public @Override IntegralPoint clone() {
			return new IntegralPoint(this);
		}
		
		public IntegralPoint(int x, int y, int z) {
			super(x, y, z);
		}
		public IntegralPoint(Integer x, Integer y, Integer z) throws NullPointerException {
			super(x, y, z);
		}
		public IntegralPoint(IntegralPositional pos) throws NullPointerException {
			super(pos);
		}
	}
	
	public static class Point extends Position implements PointType<Float>, Movable {
		
		public @Override void setX(float x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(float y) {
			super.pos[1] = y;
		}
		
		public @Override void setZ(float z) {
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + getX() + "; " + getY() + "; " + getZ() + " )";
		}
		
		public @Override Point clone() {
			return new Point(this);
		}
		
		public Point(float x, float y, float z) {
			super(x, y, z);
		}
		public Point(Float x, Float y, Float z) throws NullPointerException {
			super(x, y, z);
		}
		public Point(Positional pos) throws NullPointerException {
			super(pos);
		}
	}

	public static class DoublePoint extends DoublePosition implements PointType<Double>, DoubleMovable {
		
		public @Override void setX(double x) {
			super.pos[0] = x;
		}
		
		public @Override void setY(double y) {
			super.pos[1] = y;
		}
		
		public @Override void setZ(double z) {
			super.pos[2] = z;
		}
		
		public @Override String toString() {
			return "( " + getX() + "; " + getY() + "; " + getZ() + " )";
		}
		
		public @Override DoublePoint clone() {
			return new DoublePoint(this);
		}
		
		public DoublePoint(int x, int y, int z) {
			super(x, y, z);
		}
		public DoublePoint(Double x, Double y, Double z) throws NullPointerException {
			super(x, y, z);
		}
		public DoublePoint(DoublePositional pos) throws NullPointerException {
			super(pos);
		}
	}
}
