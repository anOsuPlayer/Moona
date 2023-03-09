package moonaframework.design.geometry;

public interface ResizableType<T extends Number> extends DimensionalType<T> {

	void setWidth(T width);
	
	void resizeWidth(T dWidth);
	
	void setHeight(T height);
	
	void resizeHeight(T dHeight);
	
	void setDepth(T depth);
	
	void resizeDepth(T dDepth);
	
	public static interface IntegralResizable extends ResizableType<Integer>, IntegralDimensional {
		
		void setWidth(int width);
		
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Integer width) {
			setWidth(width.intValue());
		}
		default @Override void resizeWidth(Integer dWidth) {
			resizeWidth(dWidth.intValue());
		}
		
		void setHeight(int height);
		
		default void resizeHeight(int dHeight) {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Integer height) {
			setHeight(height.intValue());
		}
		default @Override void resizeHeight(Integer dHeight) {
			resizeHeight(dHeight.intValue());
		}
		
		void setDepth(int depth);
		
		default void resizeDepth(int dDepth) {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Integer depth) {
			setDepth(depth.intValue());
		}
		default @Override void resizeDepth(Integer dDepth) {
			resizeDepth(dDepth.intValue());
		}
	}
	
	public static interface Resizable extends ResizableType<Float>, Dimensional {
		
		void setWidth(float width);
		
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Float width) {
			setWidth(width.floatValue());
		}
		default @Override void resizeWidth(Float dWidth) {
			resizeWidth(dWidth.floatValue());
		}
		
		void setHeight(float height);
		
		default void resizeHeight(float dHeight) {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Float height) {
			setHeight(height.floatValue());
		}
		default @Override void resizeHeight(Float dHeight) {
			resizeHeight(dHeight.floatValue());
		}
		
		void setDepth(float depth);
		
		default void resizeDepth(float dDepth) {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Float depth) {
			setDepth(depth.floatValue());
		}
		default @Override void resizeDepth(Float dDepth) {
			resizeDepth(dDepth.floatValue());
		}
	}
	
	public static interface DoubleResizable extends ResizableType<Double>, DoubleDimensional {
		
		void setWidth(double width);
		
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
		default @Override void setWidth(Double width) {
			setWidth(width.doubleValue());
		}
		default @Override void resizeWidth(Double dWidth) {
			resizeWidth(dWidth.doubleValue());
		}
		
		void setHeight(double height);
		
		default void resizeHeight(double dHeight) {
			setHeight(getHeight() + dHeight);
		}
		default @Override void setHeight(Double height) {
			setHeight(height.doubleValue());
		}
		default @Override void resizeHeight(Double dHeight) {
			resizeHeight(dHeight.doubleValue());
		}
		
		void setDepth(double depth);
		
		default void resizeDepth(double dDepth) {
			setDepth(getDepth() + dDepth);
		}
		default @Override void setDepth(Double depth) {
			setDepth(depth.doubleValue());
		}
		default @Override void resizeDepth(Double dDepth) {
			resizeDepth(dDepth.doubleValue());
		}
	}
}
