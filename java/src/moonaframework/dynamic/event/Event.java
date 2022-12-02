package moonaframework.dynamic.event;

import moonaframework.base.Moona;
import moonaframework.base.Satellite;
import moonaframework.base.Serial;
import moonaframework.util.annotations.Functional;
import moonaframework.util.functional.Snippet;

public @Functional interface Event extends Serial, Satellite<Snippet> {

	default @Override Snippet translate() {
		return () -> trigger();
	}
	
	@Override long id();
	
	default @Override int nature() {
		return Moona.EVENT;
	}
	
	void trigger();
}
