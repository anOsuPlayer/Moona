package moonaframework.util.function;

import moonaframework.base.Satellite;

public @FunctionalInterface interface Snippet extends Satellite<Runnable> {

	default @Override Runnable translate() {
		return () -> code();
	}
	
	void code();
}
