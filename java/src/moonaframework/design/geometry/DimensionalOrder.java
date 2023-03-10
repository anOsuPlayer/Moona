package moonaframework.design.geometry;

public enum DimensionalOrder {

	MONODIMENSIONAL(1, "Monodimensional Element"),
	
	BIDIMENSIONAL(2, "Bidimensional Element"),
	
	TRIDIMENSIONAL(3, "Tridimensional Element");
	
	static DimensionalOrder fromNumber(int number) {
		return switch (number) {
			case 1: yield MONODIMENSIONAL;
			case 2: yield BIDIMENSIONAL;
			case 3: yield TRIDIMENSIONAL;
			default: yield null;
		};
	}
	
	public @Override String toString() {
		return this.message;
	}
	
	private final int order;
	
	private final String message;
	
	private DimensionalOrder(int order, String message) {
		this.order = order;
		this.message = message;
	}
}
