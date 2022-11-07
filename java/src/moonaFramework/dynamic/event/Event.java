package moonaFramework.dynamic.event;

import moonaFramework.base.Serial;
import moonaFramework.util.function.Snippet;

public interface Event extends Serial {

	@Override
	long id();
	
	void trigger();
	
	default Snippet getSnippet() {
		return () -> trigger();
	}
}
