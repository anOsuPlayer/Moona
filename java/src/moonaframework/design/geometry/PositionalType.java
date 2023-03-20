package moonaframework.design.geometry;

public interface PositionalType<T extends Number> {

	T getWrappedX();
	
	public static interface IntegralPositional extends PositionalType<Integer> {
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getX();
	}
	public static interface Positional extends PositionalType<Float> {
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getX();
	}
	public static interface DoublePositional extends PositionalType<Double> {
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		
		double getX();
	}
	
	public static interface PositionalType2D<T extends Number> extends PositionalType<T> {
		
		T getWrappedY();
		
		public static interface IntegralPositional2D extends PositionalType2D<Integer>, IntegralPositional {
			
			default @Override Integer getWrappedY() {
				return Integer.valueOf(getY());
			}
			
			int getY();
		}
		public static interface Positional2D extends PositionalType2D<Float>, Positional {
			
			default @Override Float getWrappedY() {
				return Float.valueOf(getY());
			}
			
			float getY();
		}
		public static interface DoublePositional2D extends PositionalType2D<Double>, DoublePositional {
			
			default @Override Double getWrappedY() {
				return Double.valueOf(getY());
			}
			
			double getY();
		}
	}
	
	public static interface PositionalType3D<T extends Number> extends PositionalType2D<T> {
		
		T getWrappedZ();
		
		public static interface IntegralPositional3D extends PositionalType3D<Integer>, IntegralPositional2D {
			
			default @Override Integer getWrappedZ() {
				return Integer.valueOf(getZ());
			}
			
			int getZ();
		}
		public static interface Positional3D extends PositionalType3D<Float>, Positional2D {
			
			default @Override Float getWrappedZ() {
				return Float.valueOf(getZ());
			}
			
			float getZ();
		}
		public static interface DoublePositional3D extends PositionalType3D<Double>, DoublePositional2D {
			
			default @Override Double getWrappedZ() {
				return Double.valueOf(getZ());
			}
			
			double getZ();
		}
	}
}
