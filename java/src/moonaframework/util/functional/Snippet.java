package moonaframework.util.functional;

public @FunctionalInterface interface Snippet extends Satellite<Runnable> {

	default @Override Runnable translate() {
		return () -> execute();
	}
	
	void execute();
}
