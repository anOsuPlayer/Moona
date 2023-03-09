package moonaframework.design.geometry;

public interface DimensionalType<T extends Number> extends Cloneable {

	T getWrappedWidth();
	
	void applyWidth(ResizableType<T> res);
	
	T getWrappedHeight();
	
	void applyHeight(ResizableType<T> res);
	
	T getWrappedDepth();
	
	void applyDepth(ResizableType<T> res);
	
	public static interface IntegralDimensional extends DimensionalType<Integer> {
		
		int getWidth();
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getHeight();
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		
		int getDepth();
		
		default @Override Integer getWrappedDepth() {
			return Integer.valueOf(getDepth());
		}
	}
	
	public static interface Dimensional extends DimensionalType<Float> {
		
		float getWidth();
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getHeight();
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		
		float getDepth();
		
		default @Override Float getWrappedDepth() {
			return Float.valueOf(getDepth());
		}
	}
	
	public static interface DoubleDimensional extends DimensionalType<Double> {
		
		double getWidth();
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getHeight();
		
		default @Override Double getWrappedHeight() {
			return Double.valueOf(getHeight());
		}
		
		double getDepth();
		
		default @Override Double getWrappedDepth() {
			return Double.valueOf(getDepth());
		}
	}
}
