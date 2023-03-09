package moonaframework.design.geometry.monodimensional;

public interface MonodimensionalPoint<T extends Number> extends MonodimensionalPosition<T>, Movable1DType<T> {

	public static class IntegralPoint1D extends IntegralPosition1D implements MonodimensionalPosition<Integer>, IntegralMovable1D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override String toString() {
			return "( " + x + " )";
		}
		
		public @Override IntegralPoint1D clone() {
			return new IntegralPoint1D(x);
		}
		
		public IntegralPoint1D(int x) {
			super(x);
		}
		public IntegralPoint1D(Integer x) {
			super(x);
		}
		public IntegralPoint1D(IntegralPositional1D pos) {
			super(pos);
		}
		public IntegralPoint1D(Positional1DType<?> pos) {
			super(pos);
		}
		public IntegralPoint1D() {
			super(0);
		}
	}
	
	public static class Point1D extends Position1D implements MonodimensionalPosition<Float>, Movable1D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public @Override String toString() {
			return "( " + x + " )";
		}
		
		public @Override Point1D clone() {
			return new Point1D(x);
		}
		
		public Point1D(float x) {
			super(x);
		}
		public Point1D(Float x) {
			super(x);
		}
		public Point1D(Positional1D pos) {
			super(pos);
		}
		public Point1D(Positional1DType<?> pos) {
			super(pos);
		}
		public Point1D() {
			super(0);
		}
	}
	
	public static class DoublePoint1D extends DoublePosition1D implements MonodimensionalPosition<Double>, DoubleMovable1D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override String toString() {
			return "( " + x + " )";
		}
		
		public @Override DoublePoint1D clone() {
			return new DoublePoint1D(x);
		}
		
		public DoublePoint1D(double x) {
			super(x);
		}
		public DoublePoint1D(Double x) {
			super(x);
		}
		public DoublePoint1D(DoublePositional1D pos) {
			super(pos);
		}
		public DoublePoint1D(Positional1DType<?> pos) {
			super(pos);
		}
		public DoublePoint1D() {
			super(0);
		}
	}
}
