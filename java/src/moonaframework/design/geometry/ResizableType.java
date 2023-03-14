package moonaframework.design.geometry;

public interface ResizableType<T extends Number> extends DimensionalType<T> {

	void setWidth(T width) throws NullPointerException;
	
	void resizeWidth(T dWidth) throws NullPointerException;
	
	void setHeight(T height) throws NullPointerException, LowerDimensionalOrderException;
	
	void resizeHeight(T dHeight) throws NullPointerException, LowerDimensionalOrderException;
	
	void setDepth(T depth) throws NullPointerException, LowerDimensionalOrderException;
	
	void resizeDepth(T dDepth) throws NullPointerException, LowerDimensionalOrderException;
	
	default void resize(T dWidth, T dHeight, T dDepth) throws NullPointerException, LowerDimensionalOrderException {
		resizeWidth(dWidth); resizeHeight(dHeight); resizeDepth(dDepth);
	}
	default void resize(T dWdith, T dHeight) throws NullPointerException, LowerDimensionalOrderException {
		resizeWidth(dWdith); resizeHeight(dHeight);
	}
	
	public static interface IntegralResizable extends ResizableType<Integer>, IntegralDimensional {
		
		void setWidth(int width);
		
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Integer width) throws NullPointerException {
			setWidth(width.intValue());
		}
		default @Override void resizeWidth(Integer dWidth) throws NullPointerException {
			resizeWidth(dWidth.intValue());
		}
		
		void setHeight(int height) throws LowerDimensionalOrderException;
		
		default void resizeHeight(int dHeight) throws LowerDimensionalOrderException {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Integer height) throws NullPointerException, LowerDimensionalOrderException {
			setHeight(height.intValue());
		}
		default @Override void resizeHeight(Integer dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeHeight(dHeight.intValue());
		}
		
		void setDepth(int depth) throws LowerDimensionalOrderException;
		
		default void resizeDepth(int dDepth) throws LowerDimensionalOrderException {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Integer depth) throws NullPointerException, LowerDimensionalOrderException {
			setDepth(depth.intValue());
		}
		default @Override void resizeDepth(Integer dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeDepth(dDepth.intValue());
		}
		
		default @Override void resize(Integer dWidth, Integer dHeight, Integer dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue()); resizeDepth(dDepth.intValue());
		}
		default void resize(int dWidth, int dHeight, int dDepth) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight); resizeDepth(dDepth);
		}
		default @Override void resize(Integer dWidth, Integer dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue());
		}
		default void resize(int dWidth, int dHeight) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight);
		}
	}
	
	public static interface Resizable extends ResizableType<Float>, Dimensional {
		
		void setWidth(float width);
		
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Float width) throws NullPointerException {
			setWidth(width.floatValue());
		}
		default @Override void resizeWidth(Float dWidth) throws NullPointerException {
			resizeWidth(dWidth.floatValue());
		}
		
		void setHeight(float height) throws LowerDimensionalOrderException;
		
		default void resizeHeight(float dHeight) throws LowerDimensionalOrderException {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Float height) throws NullPointerException, LowerDimensionalOrderException {
			setHeight(height.floatValue());
		}
		default @Override void resizeHeight(Float dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeHeight(dHeight.floatValue());
		}
		
		void setDepth(float depth) throws LowerDimensionalOrderException;
		
		default void resizeDepth(float dDepth) throws LowerDimensionalOrderException {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Float depth) throws NullPointerException, LowerDimensionalOrderException {
			setDepth(depth.floatValue());
		}
		default @Override void resizeDepth(Float dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeDepth(dDepth.floatValue());
		}
		
		default @Override void resize(Float dWidth, Float dHeight, Float dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue()); resizeDepth(dDepth.intValue());
		}
		default void resize(float dWidth, float dHeight, float dDepth) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight); resizeDepth(dDepth);
		}
		default @Override void resize(Float dWidth, Float dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue());
		}
		default void resize(float dWidth, float dHeight) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight);
		}
	}
	
	public static interface DoubleResizable extends ResizableType<Double>, DoubleDimensional {
		
		void setWidth(double width);
		
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Double width) throws NullPointerException {
			setWidth(width.doubleValue());
		}
		default @Override void resizeWidth(Double dWidth) throws NullPointerException {
			resizeWidth(dWidth.doubleValue());
		}
		
		void setHeight(double height) throws LowerDimensionalOrderException;
		
		default void resizeHeight(double dHeight) throws LowerDimensionalOrderException {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Double height) throws NullPointerException, LowerDimensionalOrderException {
			setHeight(height.doubleValue());
		}
		default @Override void resizeHeight(Double dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeHeight(dHeight.doubleValue());
		}
		
		void setDepth(double depth) throws LowerDimensionalOrderException;
		
		default void resizeDepth(double dDepth) throws LowerDimensionalOrderException {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Double depth) throws NullPointerException, LowerDimensionalOrderException {
			setDepth(depth.doubleValue());
		}
		default @Override void resizeDepth(Double dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeDepth(dDepth.doubleValue());
		}
		
		default @Override void resize(Double dWidth, Double dHeight, Double dDepth) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue()); resizeDepth(dDepth.intValue());
		}
		default void resize(double dWidth, double dHeight, double dDepth) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight); resizeDepth(dDepth);
		}
		default @Override void resize(Double dWidth, Double dHeight) throws NullPointerException, LowerDimensionalOrderException {
			resizeWidth(dWidth.intValue()); resizeHeight(dHeight.intValue());
		}
		default void resize(double dWidth, double dHeight) throws LowerDimensionalOrderException {
			resizeWidth(dWidth); resizeHeight(dHeight);
		}
	}
}
