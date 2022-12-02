package moonaframework.util.functional;

import moonaframework.base.Satellite;

public @FunctionalInterface interface Snippet extends Satellite<Runnable> {

	default @Override Runnable translate() {
		return () -> code();
	}
	
	void code();
}
