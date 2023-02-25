package moonaframework.design.bidimensional;

public interface Resizable2D<T extends Number> extends Dimensional2D<T> {

	void setWidth(T width);
	
	void setHeight(T height);
}
