package moonaframework.util.function;

import moonaframework.base.Satellite;

public interface Snippet extends Satellite<Runnable> {

	default Runnable translate() {
		return () -> code();
	}
	
	void code();
}
