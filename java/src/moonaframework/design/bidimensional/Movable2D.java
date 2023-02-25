package moonaframework.design.bidimensional;

public interface Movable2D<T extends Number> extends Positional2D<T> {

	void setX(T x);
	
	void setY(T y);
}
