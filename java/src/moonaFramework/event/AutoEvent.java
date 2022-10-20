package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.process.Task;
import moonaFramework.util.Conditional;

public abstract class AutoEvent extends Task implements ModalEvent {

	@Override
	public int nature() {
		return Natural.EVENT;
	}
	
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
	public void setCondition(Conditional c) throws UnsupportedOperationException {
		if (this.eventMode != EventMode.UNTIL) {
			throw new UnsupportedOperationException("You're not allowed to set iterations if the EventMode "
					+ "is not UNTIL.");
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
	
	@Override
	public void update() {
		trigger();
		if (eventMode == EventMode.ONCE) {
			Moona.interrupt(this);
		}
		if (eventMode == EventMode.REPEAT) {
			iterations--;
			if (iterations == 0) {
				Moona.interrupt(this);
			}
		}
		if (eventMode == EventMode.UNTIL) {
			if (condition.verify()) {
				Moona.interrupt(this);
			}
		}
	}
	
	public AutoEvent(EventMode em) {
		super();
		this.eventMode = (em == EventMode.UNTIL) ? EventMode.REPEAT : em;
		this.iterations = (em == EventMode.ONCE) ? 1 : -1;
	}
	public AutoEvent(int iterations) {
		super();
		this.iterations = (iterations <= 0) ? -1 : iterations;
		this.eventMode = (iterations == 1) ? EventMode.ONCE : EventMode.REPEAT;
	}
	public AutoEvent(Conditional c) throws NullPointerException {
		super();
		setCondition(c);
		this.eventMode = EventMode.UNTIL;
	}
	public AutoEvent() {
		this(1);
	}
}
