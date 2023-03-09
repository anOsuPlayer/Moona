package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.monodimensional.Movable1DType;
import moonaframework.design.geometry.monodimensional.Positional1DType;

public interface Movable2DType<T extends Number> extends Positional2DType<T>, Movable1DType<T> {
	
	void setY(T y) throws NullPointerException;
	
	void moveY(T dy) throws NullPointerException;
	
	default void asX(Positional1DType<T> pos) throws NullPointerException {
		setX(pos.getWrappedX());
	}
	
	default void asY(Positional1DType<T> pos) throws NullPointerException {
		setY(pos.getWrappedX());
	}
	
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
		
		default @Override void setPosition(Positional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralPositional2D actual) {
				setX(actual.getX()); setY(actual.getY());
			}
			else if (pos instanceof Positional2DType<Integer> general) {
				setX(general.getWrappedX()); setY(general.getWrappedY());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		
		default @Override void asX(Positional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralPositional1D specific) {
				setX(specific.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		default @Override void asY(Positional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralPositional1D specific) {
				setY(specific.getX());
			}
			else {
				setY(pos.getWrappedX());
			}
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
		
		default @Override void setPosition(Positional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Positional2D actual) {
				setX(actual.getX()); setY(actual.getY());
			}
			else if (pos instanceof Positional2DType<Float> general) {
				setX(general.getWrappedX()); setY(general.getWrappedY());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		
		default @Override void asX(Positional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Positional1D specific) {
				setX(specific.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		default @Override void asY(Positional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Positional1D specific) {
				setY(specific.getX());
			}
			else {
				setY(pos.getWrappedX());
			}
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
		
		default @Override void setPosition(Positional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoublePositional2D actual) {
				setX(actual.getX()); setY(actual.getY());
			}
			else if (pos instanceof Positional2DType<Double> general) {
				setX(general.getWrappedX()); setY(general.getWrappedY());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		
		default @Override void asX(Positional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoublePositional1D specific) {
				setX(specific.getX());
			}
			else {
				setX(pos.getWrappedX());
			}
		}
		default @Override void asY(Positional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoublePositional1D specific) {
				setY(specific.getX());
			}
			else {
				setY(pos.getWrappedX());
			}
		}
	}
}
