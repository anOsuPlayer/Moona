package moonaframework.design.geometry;

public interface DimensionalType<T extends Number> {

	T getWrappedWidth();
	
	public static interface IntegralDimensional extends DimensionalType<Integer> {
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
	}
	public static interface Dimensional extends DimensionalType<Float> {
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
	}
	public static interface DoubleDimensional extends DimensionalType<Double> {
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
	}
	
	public static interface DimensionalType2D<T extends Number> extends DimensionalType<T> {
		
		T getWrappedHeight();
		
		public static interface IntegralDimensional2D extends DimensionalType2D<Integer>, IntegralDimensional {
			
			default @Override Integer getWrappedHeight() {
				return Integer.valueOf(getHeight());
			}
			
			int getHeight();
		}
		public static interface Dimensional2D extends DimensionalType2D<Float>, Dimensional {
			
			default @Override Float getWrappedHeight() {
				return Float.valueOf(getHeight());
			}
			
			float getHeight();
		}
		public static interface DoubleDimensional2D extends DimensionalType2D<Double>, DoubleDimensional {
			
			default @Override Double getWrappedHeight() {
				return Double.valueOf(getHeight());
			}
			
			double getHeight();
		}
	}
	
	public static interface DimensionalType3D<T extends Number> extends DimensionalType2D<T> {
		
		T getWrappedDepth();
		
		public static interface IntegralDimensional3D extends DimensionalType3D<Integer>, IntegralDimensional2D {
			
			default @Override Integer getWrappedDepth() {
				return Integer.valueOf(getDepth());
			}
			
			int getDepth();
		}
		public static interface Dimensional3D extends DimensionalType3D<Float>, Dimensional2D {
			
			default @Override Float getWrappedDepth() {
				return Float.valueOf(getDepth());
			}
			
			float getDepth();
		}
		public static interface DoubleDimensional3D extends DimensionalType3D<Double>, DoubleDimensional2D {
			
			default @Override Double getWrappedDepth() {
				return Double.valueOf(getDepth());
			}
			
			double getDepth();
		}
	}
}
