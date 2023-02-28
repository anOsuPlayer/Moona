package moonaframework.design.bidimensional;

import moonaframework.design.monodimensional.Resizable1DType;

public interface Resizable2DType<T extends Number> extends Dimensional2DType<T>, Resizable1DType<T> {
	
	void setHeight(T height) throws NullPointerException;
	
	void resizeHeight(T dHeight) throws NullPointerException;
	
	public static interface IntegralResizable2D extends Resizable2DType<Integer>, IntegralDimensional2D, IntegralResizable1D {
		
		default @Override void setHeight(Integer height) throws NullPointerException {
			setHeight(height.intValue());
		}
		
		void setHeight(int height);
		
		default @Override void resizeHeight(Integer dHeight) throws NullPointerException {
			resizeHeight(dHeight.intValue());
		}
		default void resizeHeight(int dHeight) {
			setHeight(getHeight() + dHeight);
		}
	}
	
	public static interface Resizable2D extends Resizable2DType<Float>, Dimensional2D, Resizable1D {
		
		default @Override void setHeight(Float height) throws NullPointerException {
			setHeight(height.floatValue());
		}
		
		void setHeight(float height);
		
		default @Override void resizeHeight(Float dHeight) throws NullPointerException {
			resizeHeight(dHeight.floatValue());
		}
		default void resizeHeight(float dHeight) {
			setHeight(getHeight() + dHeight);
		}
	}
	
	public static interface DoubleResizable2D extends Resizable2DType<Double>, DoubleDimensional2D, DoubleResizable1D {
		
		default @Override void setHeight(Double height) throws NullPointerException {
			setHeight(height.doubleValue());
		}
		
		void setHeight(double height);
		
		default @Override void resizeHeight(Double dHeight) throws NullPointerException {
			resizeHeight(dHeight.doubleValue());
		}
		default void resizeHeight(double dHeight) {
			setHeight(getHeight() + dHeight);
		}
	}
}