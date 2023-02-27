package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalPoint;
import moonaframework.design.monodimensional.Movable1DType;

public interface Movable2DType<T extends Number> extends Positional2DType<T>, Movable1DType<T> {
	
	void setY(T y);
	
	void moveY(T dy);
	
	public static interface IntegralMovable2D extends Movable2DType<Integer>, IntegralPositional2D, IntegralMovable1D {
		
		default @Override void setY(Integer y) {
			setY(y.intValue());
		}
		
		void setY(int y);
		
		default @Override void moveY(Integer dy) {
			moveY(dy.intValue());
		}
		default void moveY(int dy) {
			setY(getY() + dy);
		}
	}
	
	public static interface Movable2D extends Movable2DType<Float>, Positional2D, Movable1D {
		
		default @Override void setY(Float y) {
			setY(y.floatValue());
		}
		
		void setY(float y);
		
		default @Override void moveY(Float dy) {
			moveX(dy.floatValue());
		}
		default void moveY(float dy) {
			setY(getY() + dy);
		}
	}
	
	public static interface DoubleMovable2D extends Movable2DType<Double>, DoublePositional2D, DoubleMovable1D {
		
		default @Override void setY(Double y) {
			setY(y.doubleValue());
		}
		
		void setY(double y);
		
		default @Override void moveY(Double dy) {
			moveY(dy.doubleValue());
		}
		default void moveY(double dy) {
			setY(getY() + dy);
		}
	}
}
