package moonaframework.util.superobject;

import java.util.NoSuchElementException;
import java.util.Optional;

public sealed interface ReadonlyState<O> permits StandardState<O>, ConclusiveState<O> {

	O get();
	
	default O evaluate() throws NoSuchElementException {
		if (get() == null) {
			throw new NoSuchElementException("No Object is present.");
		}
		return get();
	}
	
	default Optional<O> asOptional() throws NoSuchElementException {
		return Optional.of(evaluate());
	}
	
	default SuperObject<O> clone() {
		return SuperObject.of(get());
	}
}
