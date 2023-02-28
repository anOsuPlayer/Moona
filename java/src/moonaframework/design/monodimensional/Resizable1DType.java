package moonaframework.design.monodimensional;

public interface Resizable1DType<T extends Number> extends Dimensional1DType<T> {

	void setWidth(T width);
	
	void resizeWidth(T dWidth);
	
	public static interface IntegralResizable1D extends Resizable1DType<Integer>, IntegralDimensional1D {
		
		default @Override void setWidth(Integer width) {
			setWidth(width.intValue());
		}
		
		void setWidth(int width);
		
		default @Override void resizeWidth(Integer dWidth) {
			resizeWidth(dWidth.intValue());
		}
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
	
	public static interface Resizable1D extends Resizable1DType<Float>, Dimensional1D {
		
		default @Override void setWidth(Float width) {
			setWidth(width.floatValue());
		}
		
		void setWidth(float width);
		
		default @Override void resizeWidth(Float dWidth) {
			resizeWidth(dWidth.floatValue());
		}
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
	
	public static interface DoubleResizable1D extends Resizable1DType<Double>, DoubleDimensional1D {
		
		default @Override void setWidth(Double width) {
			setWidth(width.doubleValue());
		}
		
		void setWidth(double dWidth);
		
		default @Override void resizeWidth(Double dWidth) {
			resizeWidth(dWidth.doubleValue());
		}
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
	}
}
