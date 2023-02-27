package moonaframework.design.monodimensional;

public interface Dimensional1DType<T extends Number> {

	T getWrappedWidth();
	
	public static interface IntegralDimensional1D extends Dimensional1DType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
	}
	
	public static interface Dimensional1D extends Dimensional1DType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
	}
	
	public static interface DoubleDimensional1D extends Dimensional1DType<Double> {
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
	}
}
