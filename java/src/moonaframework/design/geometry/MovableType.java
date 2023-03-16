package moonaframework.design.geometry;

public interface MovableType<T extends Number> extends PositionalType<T> {

	void setX(T x) throws NullPointerException;
	
	void moveX(T dx) throws NullPointerException;
	
	void setY(T y) throws NullPointerException, LowerDimensionalOrderException;
	
	void moveY(T dy) throws NullPointerException, LowerDimensionalOrderException;
	
	void setZ(T z) throws NullPointerException, LowerDimensionalOrderException;
	
	void moveZ(T dz) throws NullPointerException, LowerDimensionalOrderException;
	
	void setPosition(PositionalType<T> pos) throws NullPointerException;
	
	public static interface IntegralMovable extends MovableType<Integer>, IntegralPositional {
		
		void setX(int x);
		
		default void moveX(int dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Integer x) throws NullPointerException {
			setX(x.intValue());
		}
		default @Override void moveX(Integer dx) throws NullPointerException {
			moveX(dx.intValue());
		}
		
		void setY(int y) throws LowerDimensionalOrderException;
		
		default void moveY(int dy) throws LowerDimensionalOrderException {
			setY(getY() + dy);
		}
		default @Override void setY(Integer y) throws NullPointerException, LowerDimensionalOrderException {
			setY(y.intValue());
		}
		default @Override void moveY(Integer dy) throws NullPointerException, LowerDimensionalOrderException {
			moveY(dy.intValue());
		}
		
		void setZ(int z) throws LowerDimensionalOrderException;
		
		default void moveZ(int dz) throws LowerDimensionalOrderException {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Integer z) throws NullPointerException, LowerDimensionalOrderException {
			setZ(z.intValue());
		}
		default @Override void moveZ(Integer dz) throws NullPointerException, LowerDimensionalOrderException {
			moveZ(dz.intValue());
		}
		
		default @Override void setPosition(PositionalType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralPositional ipos) {
				ipos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					ipos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					ipos.applyZ(this);
				}
			}
			else {
				pos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					pos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					pos.applyZ(this);
				}
			}
		}
	}
	
	public static interface Movable extends MovableType<Float>, Positional {
		
		void setX(float x);
		
		default void moveX(float dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Float x) throws NullPointerException {
			setX(x.floatValue());
		}
		default @Override void moveX(Float dx) throws NullPointerException {
			moveX(dx.floatValue());
		}
		
		void setY(float y) throws LowerDimensionalOrderException;
		
		default void moveY(float dy) throws LowerDimensionalOrderException {
			setY(getY() + dy);
		}
		default @Override void setY(Float y) throws NullPointerException, LowerDimensionalOrderException {
			setY(y.floatValue());
		}
		default @Override void moveY(Float dy) throws NullPointerException, LowerDimensionalOrderException {
			moveY(dy.floatValue());
		}
		
		void setZ(float z) throws LowerDimensionalOrderException;
		
		default void moveZ(float dz) throws LowerDimensionalOrderException {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Float z) throws NullPointerException, LowerDimensionalOrderException {
			setZ(z.floatValue());
		}
		default @Override void moveZ(Float dz) throws NullPointerException, LowerDimensionalOrderException {
			moveZ(dz.floatValue());
		}
		
		default @Override void setPosition(PositionalType<Float> pos) throws NullPointerException {
			if (pos instanceof Positional fpos) {
				fpos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					fpos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					fpos.applyZ(this);
				}
			}
			else {
				pos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					pos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					pos.applyZ(this);
				}
			}
		}
	}
	
	public static interface DoubleMovable extends MovableType<Double>, DoublePositional {
		
		void setX(double x);
		
		default void moveX(double dx) {
			setX(getX() + dx);
		}
		default @Override void setX(Double x) throws NullPointerException {
			setX(x.doubleValue());
		}
		default @Override void moveX(Double dx) throws NullPointerException {
			moveX(dx.doubleValue());
		}
		
		void setY(double y) throws LowerDimensionalOrderException;
		
		default void moveY(double dy) throws LowerDimensionalOrderException {
			setY(getY() + dy);
		}
		default @Override void setY(Double y) throws NullPointerException, LowerDimensionalOrderException {
			setY(y.doubleValue());
		}
		default @Override void moveY(Double dy) throws NullPointerException, LowerDimensionalOrderException {
			moveY(dy.doubleValue());
		}
		
		void setZ(double z) throws LowerDimensionalOrderException;
		
		default void moveZ(double dz) throws LowerDimensionalOrderException {
			setZ(getZ() + dz);
		}
		default @Override void setZ(Double z) throws NullPointerException, LowerDimensionalOrderException {
			setZ(z.doubleValue());
		}
		default @Override void moveZ(Double dz) throws NullPointerException, LowerDimensionalOrderException {
			moveZ(dz.doubleValue());
		}
		
		default @Override void setPosition(PositionalType<Double> pos) throws NullPointerException {
			if (pos instanceof DoublePositional dpos) {
				dpos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					dpos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					dpos.applyZ(this);
				}
			}
			else {
				pos.applyX(this);
				if (pos.order().satisfies(DimensionalOrder.BIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.BIDIMENSIONAL)) {
					pos.applyY(this);
				}
				if (pos.order().satisfies(DimensionalOrder.TRIDIMENSIONAL) && this.order().satisfies(DimensionalOrder.TRIDIMENSIONAL)) {
					pos.applyZ(this);
				}
			}
		}
	}
}
