package moonaframework.design.bidimensional;

public sealed interface Dimensional2D<P extends Number> permits Dimensional2D.IntDimensional2D, Dimensional2D.LongDimensional2D, Dimensional2D.FloatDimensional2D, Dimensional2D.DoubleDimensional2D, Spatial2D<P>, Resizable2D<P> {

	P widthValue();
	
	P heightValue();
	
	public non-sealed interface IntDimensional2D extends Dimensional2D<Integer> {
		
		default @Override Integer widthValue() {
			return Integer.valueOf(getWidth());
		}
		
		int getWidth();
		
		default @Override Integer heightValue() {
			return Integer.valueOf(getHeight());
		}
		
		int getHeight();
	}
	
	public non-sealed interface LongDimensional2D extends Dimensional2D<Long> {
		
		default @Override Long widthValue() {
			return Long.valueOf(getWidth());
		}
		
		long getWidth();
		
		default @Override Long heightValue() {
			return Long.valueOf(getHeight());
		}
		
		long getHeight();
	}
	
	public non-sealed interface FloatDimensional2D extends Dimensional2D<Float> {
		
		default @Override Float widthValue() {
			return Float.valueOf(getWidth());
		}
		
		float getWidth();
		
		default @Override Float heightValue() {
			return Float.valueOf(getHeight());
		}
		
		float getHeight();
	}
	
	public non-sealed interface DoubleDimensional2D extends Dimensional2D<Double> {
		
		default @Override Double widthValue() {
			return Double.valueOf(getWidth());
		}
		
		double getWidth();
		
		default @Override Double heightValue() {
			return Double.valueOf(getHeight());
		}
		
		double getHeight();
	}
}
