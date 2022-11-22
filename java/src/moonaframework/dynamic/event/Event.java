package moonaframework.dynamic.event;

import moonaframework.base.Natural;
import moonaframework.base.Serial;
import moonaframework.util.function.Snippet;

public interface Event extends Serial {

	@Override
	long id();
	@Override
	default int nature() {
		return Natural.EVENT;
	}
	
	void trigger();
	
	default Snippet getSnippet() {
		return () -> trigger();
	}
}
