package moonaframework.design.bidimensional;

public sealed interface Resizable2D<P extends Number> extends Dimensional2D<P> permits Resizable2D.IntResizable2D, Resizable2D.LongResizable2D, Resizable2D.FloatResizable2D, Resizable2D.DoubleResizable2D, Adjustable2D<P> {

	void changeWidth(Number width);
	
	void changeHeight(Number height);
	
	public non-sealed interface IntResizable2D extends Resizable2D<Integer>, IntDimensional2D {
		
		default @Override void changeWidth(Number width) {
			setX(width.intValue());
		}
		
		void setX(int x);
		
		default @Override void changeHeight(Number height) {
			setY(height.intValue());
		}
		
		void setY(int y);
	}
	
	public non-sealed interface LongResizable2D extends Resizable2D<Long>, LongDimensional2D {
		
		default @Override void changeWidth(Number width) {
			setX(width.longValue());
		}
		
		void setX(long x);
		
		default @Override void changeHeight(Number height) {
			setY(height.longValue());
		}
		
		void setY(long y);
	}
	
	public non-sealed interface FloatResizable2D extends Resizable2D<Float>, FloatDimensional2D {
		
		default @Override void changeWidth(Number width) {
			setX(width.floatValue());
		}
		
		void setX(float x);
		
		default @Override void changeHeight(Number height) {
			setY(height.floatValue());
		}
		
		void setY(float y);
	}
	
	public non-sealed interface DoubleResizable2D extends Resizable2D<Double>, DoubleDimensional2D {
		
		default @Override void changeWidth(Number width) {
			setX(width.doubleValue());
		}
		
		void setX(double x);
		
		default @Override void changeHeight(Number height) {
			setY(height.doubleValue());
		}
		
		void setY(double y);
	}
}