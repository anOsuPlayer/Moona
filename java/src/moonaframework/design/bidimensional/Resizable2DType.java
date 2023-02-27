package moonaframework.design.bidimensional;

public sealed interface Resizable2DType<T extends Number> extends Dimensional2DType<T> permits Adjustable2DType<T>, Resizable2DType.IntegralResizable2D, Resizable2DType.Resizable2D, Resizable2DType.DoubleResizable2D {

	void setWidth(T width);
	
	void resizeWidth(T dWdith);
	
	void setHeight(T height);
	
	void resizeHeight(T dHeight);
	
	public non-sealed static interface IntegralResizable2D extends Resizable2DType<Integer>, IntegralDimensional2D {
		
		default @Override void setWidth(Integer width) {
			setWidth(width.intValue());
		}
		
		default @Override void resizeWidth(Integer dWidth) {
			setWidth(getWidth() + dWidth.intValue());
		}
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		void setWidth(int width);
		
		default @Override void setHeight(Integer height) {
			setHeight(height.intValue());
		}
		
		default @Override void resizeHeight(Integer dHeight) {
			setHeight(getHeight() + dHeight.intValue());
		}
		default void resizeHeight(int dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		void setHeight(int height);
	}
	
	public non-sealed static interface Resizable2D extends Resizable2DType<Float>, Dimensional2D {
		
		default @Override void setWidth(Float width) {
			setWidth(width.floatValue());
		}
		
		default @Override void resizeWidth(Float dWidth) {
			setWidth(getWidth() + dWidth.floatValue());
		}
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		void setWidth(float width);
		
		default @Override void setHeight(Float height) {
			setHeight(height.floatValue());
		}
		
		default @Override void resizeHeight(Float dHeight) {
			setHeight(getHeight() + dHeight.floatValue());
		}
		default void resizeHeight(float dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		void setHeight(float height);
	}
	
	public non-sealed static interface DoubleResizable2D extends Resizable2DType<Double>, DoubleDimensional2D {
		
		default @Override void setWidth(Double width) {
			setWidth(width.doubleValue());
		}
		
		default @Override void resizeWidth(Double dWidth) {
			setWidth(getWidth() + dWidth.doubleValue());
		}
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		void setWidth(double width);
		
		default @Override void setHeight(Double height) {
			setHeight(height.doubleValue());
		}
		
		default @Override void resizeHeight(Double dHeight) {
			setHeight(getHeight() + dHeight.doubleValue());
		}
		default void resizeHeight(double dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		void setHeight(double height);
	}
}