package moonaframework.dynamic.event;

import moonaframework.util.annotation.Functional;
import moonaframework.util.condition.Conditional;
import moonaframework.util.exception.NullArgumentException;

public @Functional interface ModalEvent extends Event {
	
	@Override void trigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws NullArgumentException, UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
