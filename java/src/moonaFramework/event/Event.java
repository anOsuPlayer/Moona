package moonaFramework.event;

import moonaFramework.basics.Serial;

public interface Event extends Serial {

	@Override
	long id();
	
	void trigger();
	
	default Runnable asRunnable() {
		return () -> trigger();
	}
}
