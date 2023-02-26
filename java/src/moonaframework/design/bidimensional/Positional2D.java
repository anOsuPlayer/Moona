package moonaframework.design.bidimensional;

public sealed interface Positional2D<P extends Number> permits Positional2D.IntPositional2D, Positional2D.LongPositional2D, Positional2D.FloatPositional2D, Positional2D.DoublePositional2D, Spatial2D<P>, Movable2D<P> {

	P xValue();
	
	P yValue();
	
	public non-sealed interface IntPositional2D extends Positional2D<Integer> {
		
		default @Override Integer xValue() {
			return Integer.valueOf(getX());
		}
		
		int getX();
		
		default @Override Integer yValue() {
			return Integer.valueOf(getY());
		}
		
		int getY();
	}
	
	public non-sealed interface LongPositional2D extends Positional2D<Long> {
		
		default @Override Long xValue() {
			return Long.valueOf(getX());
		}
		
		long getX();
		
		default @Override Long yValue() {
			return Long.valueOf(getY());
		}
		
		long getY();
	}
	
	public non-sealed interface FloatPositional2D extends Positional2D<Float> {
		
		default @Override Float xValue() {
			return Float.valueOf(getX());
		}
		
		float getX();
		
		default @Override Float yValue() {
			return Float.valueOf(getY());
		}
		
		float getY();
	}
	
	public non-sealed interface DoublePositional2D extends Positional2D<Double> {
		
		default @Override Double xValue() {
			return Double.valueOf(getX());
		}
		
		double getX();
		
		default @Override Double yValue() {
			return Double.valueOf(getY());
		}
		
		double getY();
	}
}
