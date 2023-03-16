package moonaframework.design.geometry;

import moonaframework.design.geometry.MovableType.DoubleMovable;
import moonaframework.design.geometry.MovableType.IntegralMovable;
import moonaframework.design.geometry.MovableType.Movable;
import moonaframework.design.geometry.PositionType.DoublePosition;
import moonaframework.design.geometry.PositionType.IntegralPosition;
import moonaframework.design.geometry.PositionType.Position;

public interface PositionalType<T extends Number> extends Cloneable, GeometricElement<T> {

	T getWrappedX();
	
	void applyX(MovableType<T> mov) throws NullPointerException;
	
	T getWrappedY() throws LowerDimensionalOrderException;
	
	void applyY(MovableType<T> mov) throws NullPointerException, LowerDimensionalOrderException;
	
	T getWrappedZ() throws LowerDimensionalOrderException;
	
	void applyZ(MovableType<T> mov) throws NullPointerException, LowerDimensionalOrderException;
	
	PositionType<T> getPosition();
	
	public static interface IntegralPositional extends PositionalType<Integer>, IntegralGeometricElement {
		
		int getX();
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		default @Override void applyX(MovableType<Integer> mov) throws NullPointerException {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		int getY() throws LowerDimensionalOrderException;
		
		default @Override Integer getWrappedY() throws LowerDimensionalOrderException {
			return Integer.valueOf(getY());
		}
		default @Override void applyY(MovableType<Integer> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		int getZ() throws LowerDimensionalOrderException;
		
		default @Override Integer getWrappedZ() throws LowerDimensionalOrderException {
			return Integer.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Integer> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
		
		default @Override IntegralPosition getPosition() {
			return new IntegralPosition(this);
		}
		
		default @Override int[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new int[] {getX()};
				case BIDIMENSIONAL: yield new int[] {getX(), getY()};
				case TRIDIMENSIONAL: yield new int[] {getX(), getY(), getZ()};
			};
		}
	}
	
	public static interface Positional extends PositionalType<Float>, FloatGeometricElement {
		
		float getX();
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		default @Override void applyX(MovableType<Float> mov) throws NullPointerException {
			if (mov instanceof Movable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		float getY() throws LowerDimensionalOrderException;
		
		default @Override Float getWrappedY() throws LowerDimensionalOrderException {
			return Float.valueOf(getY());
		}
		default @Override void applyY(MovableType<Float> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof Movable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		float getZ() throws LowerDimensionalOrderException;
		
		default @Override Float getWrappedZ() throws LowerDimensionalOrderException {
			return Float.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Float> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof Movable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
		
		default @Override Position getPosition() {
			return new Position(this);
		}
		
		default @Override float[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new float[] {getX()};
				case BIDIMENSIONAL: yield new float[] {getX(), getY()};
				case TRIDIMENSIONAL: yield new float[] {getX(), getY(), getZ()};
			};
		}
	}
	
	public static interface DoublePositional extends PositionalType<Double>, DoubleGeometricElement {
		
		double getX();
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		default @Override void applyX(MovableType<Double> mov) throws NullPointerException {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		double getY() throws LowerDimensionalOrderException;
		
		default @Override Double getWrappedY() throws LowerDimensionalOrderException {
			return Double.valueOf(getY());
		}
		default @Override void applyY(MovableType<Double> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		double getZ() throws LowerDimensionalOrderException;
		
		default @Override Double getWrappedZ() throws LowerDimensionalOrderException {
			return Double.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Double> mov) throws NullPointerException, LowerDimensionalOrderException {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
		
		default @Override DoublePosition getPosition() {
			return new DoublePosition(this);
		}
		
		default @Override double[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new double[] {getX()};
				case BIDIMENSIONAL: yield new double[] {getX(), getY()};
				case TRIDIMENSIONAL: yield new double[] {getX(), getY(), getZ()};
			};
		}
	}
}
