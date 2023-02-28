package moonaframework.design.monodimensional;

public interface Resizable1DType<T extends Number> extends Dimensional1DType<T> {

	void setWidth(T width) throws NullPointerException;
	
	void resizeWidth(T dWidth) throws NullPointerException;
	
	public static interface IntegralResizable1D extends Resizable1DType<Integer>, IntegralDimensional1D {
		
		default @Override void setWidth(Integer width) throws NullPointerException {
			setWidth(width.intValue());
		}
		
		void setWidth(int width);
		
		default @Override void resizeWidth(Integer dWidth) throws NullPointerException {
			resizeWidth(dWidth.intValue());
		}
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
	
	public static interface Resizable1D extends Resizable1DType<Float>, Dimensional1D {
		
		default @Override void setWidth(Float width) throws NullPointerException {
			setWidth(width.floatValue());
		}
		
		void setWidth(float width);
		
		default @Override void resizeWidth(Float dWidth) throws NullPointerException {
			resizeWidth(dWidth.floatValue());
		}
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
	
	public static interface DoubleResizable1D extends Resizable1DType<Double>, DoubleDimensional1D {
		
		default @Override void setWidth(Double width) throws NullPointerException {
			setWidth(width.doubleValue());
		}
		
		void setWidth(double dWidth);
		
		default @Override void resizeWidth(Double dWidth) throws NullPointerException {
			resizeWidth(dWidth.doubleValue());
		}
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
}
