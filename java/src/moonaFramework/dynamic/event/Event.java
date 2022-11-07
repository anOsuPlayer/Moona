package moonaFramework.dynamic.event;

import moonaFramework.base.Serial;

public interface Event extends Serial {

	@Override
	long id();
	
	void trigger();
	
	default Runnable asRunnable() {
		return () -> trigger();
	}
}
