package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalPoint;

public interface Movable2DType<T extends Number> extends Positional2DType<T> {

	void setX(T x);
	
	void moveX(T dx);
	
	void setY(T y);
	
	void moveY(T dy);
	
	public static interface IntegralMovable2D extends Movable2DType<Integer>, IntegralPositional2D {
		
		default @Override void setX(Integer x) {
			setX(x.intValue());
		}
		
		default @Override void moveX(Integer dx) {
			setX(getX() + dx.intValue());
		}
		default void moveX(int dx) {
			setX(getX() + dx);
		}
		
		void setX(int x);
		
		default @Override void setY(Integer y) {
			setY(y.intValue());
		}
		
		default @Override void moveY(Integer dy) {
			setY(getY() + dy.intValue());
		}
		default void moveY(int dy) {
			setY(getY() + dy);
		}
		
		void setY(int y);
	}
	
	public static interface Movable2D extends Movable2DType<Float>, Positional2D {
		
		default @Override void setX(Float x) {
			setX(x.floatValue());
		}
		
		default @Override void moveX(Float dx) {
			setX(getX() + dx.floatValue());
		}
		default void moveX(float dx) {
			setX(getX() + dx);
		}
		
		void setX(float x);
		
		default @Override void setY(Float y) {
			setY(y.floatValue());
		}
		
		default @Override void moveY(Float dy) {
			setY(getY() + dy.floatValue());
		}
		default void moveY(float dy) {
			setY(getY() + dy);
		}
		
		void setY(float y);
	}
	
	public static interface DoubleMovable2D extends Movable2DType<Double>, DoublePositional2D {
		
		default @Override void setX(Double x) {
			setX(x.doubleValue());
		}
		
		default @Override void moveX(Double dx) {
			setX(getX() + dx.doubleValue());
		}
		default void moveX(double dx) {
			setX(getX() + dx);
		}
		
		void setX(double x);
		
		default @Override void setY(Double y) {
			setY(y.doubleValue());
		}
		
		default @Override void moveY(Double dy) {
			setY(getY() + dy.doubleValue());
		}
		default void moveY(double dy) {
			setY(getY() + dy);
		}
		
		void setY(double y);
	}
}
