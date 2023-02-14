package moonaframework.dynamic.event;

import moonaframework.base.MoonaObject;
import moonaframework.util.annotation.Functional;
import moonaframework.util.functional.Satellite;
import moonaframework.util.functional.Snippet;

public @Functional interface Event extends MoonaObject, Satellite<Snippet> {

	default @Override Snippet translate() {
		return () -> trigger();
	}
	
	void trigger();
}
