package moonaframework.dynamic.event;

import moonaframework.base.Moona;
import moonaframework.util.annotations.Functional;
import moonaframework.util.condition.Conditional;
import moonaframework.util.exceptions.NullArgumentException;

public @Functional interface ModalEvent extends Event {

	default @Override int nature() {
		return Moona.MODALEVENT;
	}
	
	@Override void trigger();
	
	EventMode getMode();
	
	Conditional getCondition();
	void setCondition(Conditional c) throws NullArgumentException, UnsupportedOperationException;
	
	int getIterations();
	void setIterations(int i) throws UnsupportedOperationException;
}
