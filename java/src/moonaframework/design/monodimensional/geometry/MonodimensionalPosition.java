package moonaframework.design.monodimensional.geometry;

import moonaframework.design.monodimensional.Positional1DType;

public interface MonodimensionalPosition<T extends Number> extends Positional1DType<T> {

	public static class IntegralPosition1D implements MonodimensionalPosition<Integer>, IntegralPositional1D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		public IntegralPosition1D(int x) {
			this.x = x;
		}
		public IntegralPosition1D(Integer x) {
			this(x.intValue());
		}
		public IntegralPosition1D() {
			this.x = 0;
		}
	}
	
	public static class Position1D implements MonodimensionalPosition<Float>, Positional1D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		public Position1D(float x) {
			this.x = x;
		}
		public Position1D(Float x) {
			this(x.floatValue());
		}
		public Position1D() {
			this.x = 0;
		}
	}
	
	public static class DoublePosition1D implements MonodimensionalPosition<Double>, DoublePositional1D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		public DoublePosition1D(double x) {
			this.x = x;
		}
		public DoublePosition1D(Double x) {
			this(x.doubleValue());
		}
		public DoublePosition1D() {
			this.x = 0;
		}
	}
}
