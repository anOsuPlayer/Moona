package moonaframework.design.bidimensional;

import moonaframework.design.monodimensional.Movable1DType;

public interface Movable2DType<T extends Number> extends Positional2DType<T>, Movable1DType<T> {
	
	void setY(T y) throws NullPointerException;
	
	void moveY(T dy) throws NullPointerException;
	
	public static interface IntegralMovable2D extends Movable2DType<Integer>, IntegralPositional2D, IntegralMovable1D {
		
		default @Override void setY(Integer y) throws NullPointerException {
			setY(y.intValue());
		}
		
		void setY(int y);
		
		default @Override void moveY(Integer dy) throws NullPointerException {
			moveY(dy.intValue());
		}
		default void moveY(int dy) {
			setY(getY() + dy);
		}
	}
	
	public static interface Movable2D extends Movable2DType<Float>, Positional2D, Movable1D {
		
		default @Override void setY(Float y) throws NullPointerException {
			setY(y.floatValue());
		}
		
		void setY(float y);
		
		default @Override void moveY(Float dy) throws NullPointerException {
			moveX(dy.floatValue());
		}
		default void moveY(float dy) {
			setY(getY() + dy);
		}
	}
	
	public static interface DoubleMovable2D extends Movable2DType<Double>, DoublePositional2D, DoubleMovable1D {
		
		default @Override void setY(Double y) throws NullPointerException {
			setY(y.doubleValue());
		}
		
		void setY(double y);
		
		default @Override void moveY(Double dy) throws NullPointerException {
			moveY(dy.doubleValue());
		}
		default void moveY(double dy) {
			setY(getY() + dy);
		}
	}
}
