package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.util.Conditional;

public abstract class AbstractEvent implements Event {

	private final long id;
	@Override
	public final long id() {
		return id;
	}
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
	
	public AbstractEvent(int iterations) {
		this.iterations = (iterations <= 0) ? -1 : iterations;
		this.id = Moona.generateID();
		this.eventMode = (iterations == 1) ? EventMode.ONCE : EventMode.REPEAT;
	}
	public AbstractEvent(Conditional c) {
		this.condition = c;
		this.id = Moona.generateID();
		this.eventMode = EventMode.UNTIL;
	}
	public AbstractEvent() {
		this(1);
	}
}
