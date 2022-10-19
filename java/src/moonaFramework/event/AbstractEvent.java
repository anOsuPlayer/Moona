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
	
	@Override
	public abstract void trigger();
	
	public AbstractEvent() {
		this.id = Moona.generateID();
	}
}
