package moonaframework.dynamic.event;

import moonaframework.base.Nature;
import moonaframework.util.annotation.Functional;
import moonaframework.util.condition.Conditional;
import moonaframework.util.exceptions.NullArgumentException;

public @Functional interface ModalEvent extends Event {

	default @Override Nature nature() {
		return Nature.MODALEVENT;
	}
	
	@Override void trigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws NullArgumentException, UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
