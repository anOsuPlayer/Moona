package moonaframework.design.geometry;

import moonaframework.design.geometry.MovableType.DoubleMovable;
import moonaframework.design.geometry.MovableType.IntegralMovable;
import moonaframework.design.geometry.MovableType.Movable;

public interface PositionalType<T extends Number> extends Cloneable {

	T getWrappedX();
	
	void applyX(MovableType<T> mov);
	
	T getWrappedY();
	
	void applyY(MovableType<T> mov);
	
	T getWrappedZ();
	
	void applyZ(MovableType<T> mov);
	
	public static interface IntegralPositional extends PositionalType<Integer> {
		
		int getX();
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		default @Override void applyX(MovableType<Integer> mov) {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		int getY();
		
		default @Override Integer getWrappedY() {
			return Integer.valueOf(getY());
		}
		default @Override void applyY(MovableType<Integer> mov) {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		int getZ();
		
		default @Override Integer getWrappedZ() {
			return Integer.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Integer> mov) {
			if (mov instanceof IntegralMovable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
	}
	
	public static interface Positional extends PositionalType<Float> {
		
		float getX();
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		default @Override void applyX(MovableType<Float> mov) {
			if (mov instanceof Movable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		float getY();
		
		default @Override Float getWrappedY() {
			return Float.valueOf(getY());
		}
		default @Override void applyY(MovableType<Float> mov) {
			if (mov instanceof Movable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		float getZ();
		
		default @Override Float getWrappedZ() {
			return Float.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Float> mov) {
			if (mov instanceof Movable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
	}
	
	public static interface DoublePositional extends PositionalType<Double> {
		
		double getX();
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		default @Override void applyX(MovableType<Double> mov) {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setX(getX());
			}
			else {
				mov.setX(getWrappedX());
			}
		}
		
		double getY();
		
		default @Override Double getWrappedY() {
			return Double.valueOf(getY());
		}
		default @Override void applyY(MovableType<Double> mov) {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setY(getY());
			}
			else {
				mov.setY(getWrappedY());
			}
		}
		
		double getZ();
		
		default @Override Double getWrappedZ() {
			return Double.valueOf(getZ());
		}
		default @Override void applyZ(MovableType<Double> mov) {
			if (mov instanceof DoubleMovable realmov) {
				realmov.setZ(getZ());
			}
			else {
				mov.setZ(getWrappedZ());
			}
		}
	}
}
