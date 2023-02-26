package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.Dimensional2D.IntDimensional2D;

public sealed interface Movable2D<P extends Number> extends Positional2D<P> permits Movable2D.IntMovable2D, Movable2D.LongMovable2D, Movable2D.FloatMovable2D, Movable2D.DoubleMovable2D, Adjustable2D<P>, BidimensionalPoint<P> {

	void changeX(Number x);
	
	void changeY(Number y);
	
	public non-sealed interface IntMovable2D extends Movable2D<Integer>, IntPositional2D {
		
		default @Override void changeX(Number x) {
			setX(x.intValue());
		}
		
		void setX(int x);
		
		default @Override void changeY(Number y) {
			setY(y.intValue());
		}
		
		void setY(int y);
	}
	
	public non-sealed interface LongMovable2D extends Movable2D<Long>, LongPositional2D {
		
		default @Override void changeX(Number x) {
			setX(x.longValue());
		}
		
		void setX(long x);
		
		default @Override void changeY(Number y) {
			setY(y.longValue());
		}
		
		void setY(long y);
	}
	
	public non-sealed interface FloatMovable2D extends Movable2D<Float>, FloatPositional2D {
		
		default @Override void changeX(Number x) {
			setX(x.floatValue());
		}
		
		void setX(float x);
		
		default @Override void changeY(Number y) {
			setY(y.floatValue());
		}
		
		void setY(float y);
	}
	
	public non-sealed interface DoubleMovable2D extends Movable2D<Double>, DoublePositional2D {
		
		default @Override void changeX(Number x) {
			setX(x.doubleValue());
		}
		
		void setX(double x);
		
		default @Override void changeY(Number y) {
			setY(y.doubleValue());
		}
		
		void setY(double y);
	}
}
