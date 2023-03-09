package moonaframework.design.geometry;

public interface PositionalType<T extends Number> extends Cloneable {

	T getWrappedX();
	
	void applyX(MovableType<T> mov);
	
	T getWrappedY();
	
	void applyY(MovableType<T> mov);
	
	T getWrappedZ();
	
	void applyZ(MovableType<T> mov);
	
	public static interface IntegralPositional extends PositionalType<Integer> {
		
		int getX();
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getY();
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		
		int getZ();
		
		default @Override Integer getWrappedZ() {
			return Integer.valueOf(getZ());
		}
	}
	
	public static interface Positional extends PositionalType<Float> {
		
		float getX();
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getY();
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		
		float getZ();
		
		default @Override Float getWrappedZ() {
			return Float.valueOf(getZ());
		}
	}
	
	public static interface DoublePositional extends PositionalType<Double> {
		
		double getX();
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		
		double getY();
		
		default @Override Double getWrappedY() {
			return Double.valueOf(getY());
		}
		
		double getZ();
		
		default @Override Double getWrappedZ() {
			return Double.valueOf(getZ());
		}
	}
}
