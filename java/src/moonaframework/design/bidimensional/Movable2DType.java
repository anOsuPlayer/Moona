package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.geometry.BidimensionalPoint;

public sealed interface Movable2DType<T extends Number> extends Positional2DType<T> permits Adjustable2DType<T>, BidimensionalPoint<T>, Movable2DType.IntegralMovable2D, Movable2DType.Movable2D, Movable2DType.DoubleMovable2D {

	void setWrappedX(T x);
	
	void setWrappedY(T y);
	
	public non-sealed static interface IntegralMovable2D extends Movable2DType<Integer>, IntegralPositional2D {
		
		default @Override void setWrappedX(Integer x) {
			setX(x.intValue());
		}
		
		void setX(int x);
		
		default @Override void setWrappedY(Integer y) {
			setY(y.intValue());
		}
		
		void setY(int y);
	}
	
	public non-sealed static interface Movable2D extends Movable2DType<Float>, Positional2D {
		
		default @Override void setWrappedX(Float x) {
			setX(x.floatValue());
		}
		
		void setX(float x);
		
		default @Override void setWrappedY(Float y) {
			setY(y.floatValue());
		}
		
		void setY(float y);
	}
	
	public non-sealed static interface DoubleMovable2D extends Movable2DType<Double>, DoublePositional2D {
		
		default @Override void setWrappedX(Double x) {
			setX(x.doubleValue());
		}
		
		void setX(double x);
		
		default @Override void setWrappedY(Double y) {
			setY(y.doubleValue());
		}
		
		void setY(double y);
	}
}
