package moonaFramework.event;

import moonaFramework.Serial;

public interface Event extends Serial {

	@Override
	long id();
	@Override
	int nature();
	
	void onTrigger();
}
