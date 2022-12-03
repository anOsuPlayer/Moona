package moonaframework.dynamic.event;

import moonaframework.util.annotations.Functional;
import moonaframework.util.condition.Conditional;
import moonaframework.util.exceptions.NullArgumentException;

public @Functional abstract class Action extends AbstractEvent implements ModalEvent {

	private final EventMode eventMode;
	
	public @Override final EventMode getMode() {
		return eventMode;
	}
	
	private Conditional condition;
	
	public @Override Conditional getCondition() {
		return this.condition;
	}
	public @Override void setCondition(Conditional c) throws UnsupportedOperationException, NullArgumentException {
		if (this.eventMode != EventMode.UNTIL) {
			throw new UnsupportedOperationException("You're not allowed to set iterations if the EventMode "
					+ "is not UNTIL.");
		}
		if (c == null) {
			throw new NullArgumentException("The given Conditional is null.");
		}
		this.condition = c;
	}
	
	private int iterations = -1;
	
	public @Override int getIterations() {
		return this.iterations;
	}
	public @Override void setIterations(int i) throws UnsupportedOperationException {
		if (this.eventMode != EventMode.REPEAT) {
			throw new UnsupportedOperationException("You're not allowed to set iterations if the EventMode "
					+ "is not REPEAT.");
		}
		this.iterations = i;
	}
	
	public @Override abstract void trigger();
	
	public Action(EventMode em) throws NullArgumentException {
		super();
		if (em == null) {
			throw new NullArgumentException("Non null EventMode is required.");
		}
		this.eventMode = em;
		this.iterations = (em == EventMode.ONCE) ? 1 : -1;
	}
	public Action(int iterations) {
		super();
		this.iterations = (iterations <= 0) ? -1 : iterations;
		this.eventMode = (iterations == 1) ? EventMode.ONCE : EventMode.REPEAT;
	}
	public Action(Conditional c) throws NullArgumentException {
		super();
		setCondition(c);
		this.eventMode = EventMode.UNTIL;
	}
	public Action() {
		this(1);
	}
}
