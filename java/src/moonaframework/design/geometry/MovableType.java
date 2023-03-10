package moonaframework.design.geometry;

public interface MovableType<T extends Number> extends PositionalType<T> {

	void setX(T x);
	
	void moveX(T dx);
	
	void setY(T y);
	
	void moveY(T dy);
	
	void setZ(T z);
	
	void moveZ(T dz);
	
	public static interface IntegralMovable extends MovableType<Integer>, IntegralPositional {
		
		void setX(int x);
		
		default void moveX(int dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Integer x) {
			setX(x.intValue());
		}
		default @Override void moveX(Integer dx) {
			moveX(dx.intValue());
		}
		
		void setY(int y);
		
		default void moveY(int dy) {
			setY(getY() + dy);
		}
		default @Override void setY(Integer y) {
			setY(y.intValue());
		}
		default @Override void moveY(Integer dy) {
			moveY(dy.intValue());
		}
		
		void setZ(int z);
		
		default void moveZ(int dz) {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Integer z) {
			setZ(z.intValue());
		}
		default @Override void moveZ(Integer dz) {
			moveZ(dz.intValue());
		}
	}
	
	public static interface Movable extends MovableType<Float>, Positional {
		
		void setX(float x);
		
		default void moveX(float dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Float x) {
			setX(x.floatValue());
		}
		default @Override void moveX(Float dx) {
			moveX(dx.floatValue());
		}
		
		void setY(float y);
		
		default void moveY(float dy) {
			setY(getY() + dy);
		}
		default @Override void setY(Float y) {
			setY(y.floatValue());
		}
		default @Override void moveY(Float dy) {
			moveY(dy.floatValue());
		}
		
		void setZ(float z);
		
		default void moveZ(float dz) {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Float z) {
			setZ(z.floatValue());
		}
		default @Override void moveZ(Float dz) {
			moveZ(dz.floatValue());
		}
	}
	
	public static interface DoubleMovable extends MovableType<Double>, DoublePositional {
		
		void setX(double x);
		
		default void moveX(double dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Double x) {
			setX(x.doubleValue());
		}
		default @Override void moveX(Double dx) {
			moveX(dx.doubleValue());
		}
		
		void setY(double y);
		
		default void moveY(double dy) {
			setY(getY() + dy);
		}
		default @Override void setY(Double y) {
			setY(y.doubleValue());
		}
		default @Override void moveY(Double dy) {
			moveY(dy.doubleValue());
		}
		
		void setZ(double z);
		
		default void moveZ(double dz) {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Double z) {
			setZ(z.doubleValue());
		}
		default @Override void moveZ(Double dz) {
			moveZ(dz.doubleValue());
		}
	}
}
