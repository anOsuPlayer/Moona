package moonaframework.design.monodimensional;

public interface Movable1DType<T extends Number> extends Positional1DType<T> {

	void setX(T x) throws NullPointerException;
	
	void moveX(T dx) throws NullPointerException;
	
	void setPosition(Positional1DType<T> pos) throws NullPointerException;
	
	public static interface IntegralMovable1D extends Movable1DType<Integer>, IntegralPositional1D {
		
		default @Override void setX(Integer x) throws NullPointerException {
			setX(x.intValue());
		}
		
		void setX(int x);
		
		default @Override void moveX(Integer dx) throws NullPointerException {
			moveX(dx.intValue());
		}
		default void moveX(int dx) {
			setX(getX() + dx);
		}
		
		default @Override void setPosition(Positional1DType<Integer> pos) {
			if (pos instanceof IntegralPositional1D actual) {
				setX((int) actual.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
	}
	
	public static interface Movable1D extends Movable1DType<Float>, Positional1D {
		
		default @Override void setX(Float x) throws NullPointerException {
			setX(x.floatValue());
		}
		
		void setX(float x);
		
		default @Override void moveX(Float dx) throws NullPointerException {
			moveX(dx.floatValue());
		}
		default void moveX(float dx) {
			setX(getX() + dx);
		}
		
		default @Override void setPosition(Positional1DType<Float> pos) {
			if (pos instanceof Positional1D actual) {
				setX((float) actual.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
	}
	
	public static interface DoubleMovable1D extends Movable1DType<Double>, DoublePositional1D {
		
		default @Override void setX(Double x) throws NullPointerException {
			setX(x.doubleValue());
		}
		
		void setX(double dx);
		
		default @Override void moveX(Double dx) throws NullPointerException {
			moveX(dx.doubleValue());
		}
		default void moveX(double dx) {
			setX(getX() + dx);
		}
		
		default @Override void setPosition(Positional1DType<Double> pos) {
			if (pos instanceof DoublePositional1D actual) {
				setX((double) actual.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
	}
}
