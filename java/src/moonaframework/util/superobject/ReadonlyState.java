package moonaframework.util.superobject;

import java.util.NoSuchElementException;

public sealed interface ReadonlyState<O> permits StandardState<O>, ConclusiveState<O> {

	O get();
	
	default O evaluate() throws NoSuchElementException {
		if (get() == null) {
			throw new NoSuchElementException("No Object is present.");
		}
		return get();
	}
}
