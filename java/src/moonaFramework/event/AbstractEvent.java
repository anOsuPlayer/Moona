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
		this.condition = c;
	}
	
	private int iterations = -1;
	@Override
	public int getIterations() {
		return this.iterations;
	}
	@Override
	public void setIterations(int i) throws UnsupportedOperationException {
		this.iterations = i;
	}
	
	@Override
	public abstract void onTrigger();
	
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
