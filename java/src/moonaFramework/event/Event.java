package moonaFramework.event;

import moonaFramework.essentials.Serial;

public interface Event extends Serial {

	@Override
	long id();
	@Override
	int nature();
	
	void trigger();
}
