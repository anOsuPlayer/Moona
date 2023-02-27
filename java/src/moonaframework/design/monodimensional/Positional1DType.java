package moonaframework.design.monodimensional;

public interface Positional1DType<T extends Number> {

	T getWrappedX();
	
	public static interface IntegralPositional1D extends Positional1DType<Integer> {
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getX();
	}
	
	public static interface Positional1D extends Positional1DType<Float> {
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getX();
	}

	public static interface DoublePositional1D extends Positional1DType<Double> {
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		
		double getX();
	}
}
