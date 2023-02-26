package moonaframework.design.bidimensional;

public interface Dimensional2DType<T extends Number> {

	T getWrappedWidth();
	
	T getWrappedHeight();
	
	public interface IntegralDimensional2D extends Dimensional2DType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		
		int getHeight();
	}
	
	public interface Dimensional2D extends Dimensional2DType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		
		float getHeight();
	}
	
	public interface DoubleDimensional2D extends Dimensional2DType<Double> {
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
		
		default @Override Double getWrappedHeight() {
			return Double.valueOf(getHeight());
		}
		
		double getHeight();
	}
}
