package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.Natural;

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
	
	private EventMode mode;
	@Override
	public EventMode getMode() {
		return this.mode;
	}
	
	@Override
	public abstract void onTrigger();
	
	public AbstractEvent(EventMode e) {
		this.mode = e;
		this.id = Moona.generateID();
	}
	public AbstractEvent() {
		this(EventMode.ONCE);
	}
}
