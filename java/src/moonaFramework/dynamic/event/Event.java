package moonaFramework.dynamic.event;

import moonaFramework.base.Natural;
import moonaFramework.base.Serial;
import moonaFramework.util.function.Snippet;

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
