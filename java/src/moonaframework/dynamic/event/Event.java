package moonaframework.dynamic.event;

import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.util.annotations.Functional;
import moonaframework.util.functional.Satellite;
import moonaframework.util.functional.Snippet;

public @Functional interface Event extends Serial, Satellite<Snippet> {

	default @Override Snippet translate() {
		return () -> trigger();
	}
	
	@Override long id();
	
	default @Override Nature nature() {
		return Nature.EVENT;
	}
	
	void trigger();
}
