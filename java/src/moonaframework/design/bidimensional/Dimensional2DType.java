package moonaframework.design.bidimensional;

public sealed interface Dimensional2DType<T extends Number> permits Spatial2DType<T>, Resizable2DType<T>, Dimensional2DType.IntegralDimensional2D, Dimensional2DType.Dimensional2D, Dimensional2DType.DoubleDimensional2D {

	T getWrappedWidth();
	
	T getWrappedHeight();
	
	public non-sealed static interface IntegralDimensional2D extends Dimensional2DType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		
		int getHeight();
	}
	
	public non-sealed static interface Dimensional2D extends Dimensional2DType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		
		float getHeight();
	}
	
	public non-sealed static interface DoubleDimensional2D extends Dimensional2DType<Double> {
		
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
