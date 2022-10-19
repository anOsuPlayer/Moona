package moonaFramework.event;

import moonaFramework.Serial;
import moonaFramework.util.Conditional;

public interface Event extends Serial {

	@Override
	long id();
	@Override
	int nature();
	
	void onTrigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
