package moonaFramework.dynamic.event;

import moonaFramework.base.Natural;
import moonaFramework.util.condition.Conditional;

public interface ModalEvent extends Event {

	@Override
	default int nature() {
		return Natural.MODALEVENT;
	}
	
	@Override
	void trigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws NullPointerException, UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
