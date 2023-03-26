package moonaframework.dynamic.event;

import moonaframework.base.MoonaObject;
import moonaframework.util.annotation.Functional;
import moonaframework.util.functional.Snippet;
import moonaframework.util.functional.Translatable;

public @Functional interface Event extends MoonaObject, Translatable<Snippet> {

	default @Override Snippet translate() {
		return () -> trigger();
	}
	
	void trigger();
}
