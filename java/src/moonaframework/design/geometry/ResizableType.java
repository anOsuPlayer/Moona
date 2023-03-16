package moonaframework.design.geometry;

public interface ResizableType<T extends Number> extends DimensionalType<T> {

	void setWidth(T width) throws NullPointerException;
	
	void resizeWidth(T dWidth) throws NullPointerException;
	
	void setHeight(T height) throws NullPointerException, LowerDimensionalOrderException;
	
	void resizeHeight(T dHeight) throws NullPointerException, LowerDimensionalOrderException;
	
	void setDepth(T depth) throws NullPointerException, LowerDimensionalOrderException;
	
	void resizeDepth(T dDepth) throws NullPointerException, LowerDimensionalOrderException;
	
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
	}
}
