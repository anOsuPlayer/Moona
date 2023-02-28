package moonaframework.design.monodimensional.geometry;

import moonaframework.design.monodimensional.Movable1DType;

public interface MonodimensionalPoint<T extends Number> extends MonodimensionalPosition<T>, Movable1DType<T> {

	public static class IntegralPoint1D extends IntegralPosition1D implements MonodimensionalPosition<Integer>, IntegralMovable1D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public IntegralPoint1D(int x) {
			super(x);
		}
		public IntegralPoint1D(Integer x) {
			super(x);
		}
		public IntegralPoint1D() {
			super(0);
		}
	}
	
	public static class Point1D extends Position1D implements MonodimensionalPosition<Float>, Movable1D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public Point1D(float x) {
			super(x);
		}
		public Point1D(Float x) {
			super(x);
		}
		public Point1D() {
			super(0);
		}
	}
	
	public static class DoublePoint1D extends DoublePosition1D implements MonodimensionalPosition<Double>, DoubleMovable1D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public DoublePoint1D(double x) {
			super(x);
		}
		public DoublePoint1D(Double x) {
			super(x);
		}
		public DoublePoint1D() {
			super(0);
		}
	}
}
