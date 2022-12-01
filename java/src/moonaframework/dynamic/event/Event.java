package moonaframework.dynamic.event;

import moonaframework.base.Natural;
import moonaframework.base.Satellite;
import moonaframework.base.Serial;
import moonaframework.util.function.Snippet;

public interface Event extends Serial, Satellite<Snippet> {

	default @Override Snippet translate() {
		return () -> trigger();
	}
	
	@Override long id();
	
	default @Override int nature() {
		return Natural.EVENT;
	}
	
	void trigger();
}
