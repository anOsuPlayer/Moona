package moonaframework.dynamic.event;

import moonaframework.util.condition.Conditional;
import moonaframework.util.exceptions.NullArgumentException;

public abstract class Action extends AbstractEvent implements ModalEvent {

	private final EventMode eventMode;
	@Override
	public final EventMode getMode() {
		return eventMode;
	}
	
	private Conditional condition;
	@Override
	public Conditional getCondition() {
		return this.condition;
	}
	@Override
	public void setCondition(Conditional c) throws UnsupportedOperationException, NullArgumentException {
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
	@Override
	public int getIterations() {
		return this.iterations;
	}
	@Override
	public void setIterations(int i) throws UnsupportedOperationException {
		if (this.eventMode != EventMode.REPEAT) {
			throw new UnsupportedOperationException("You're not allowed to set iterations if the EventMode "
					+ "is not REPEAT.");
		}
		this.iterations = i;
	}
	
	@Override
	public abstract void trigger();
	
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
