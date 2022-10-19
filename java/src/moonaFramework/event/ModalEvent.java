package moonaFramework.event;

import moonaFramework.util.Conditional;

public interface ModalEvent extends Event {

	@Override
	void trigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws NullPointerException, UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
