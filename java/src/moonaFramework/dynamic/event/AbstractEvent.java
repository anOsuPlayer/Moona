package moonaFramework.dynamic.event;

import moonaFramework.base.Moona;

public abstract class AbstractEvent implements Event {

	private final long id;
	@Override
	public final long id() {
		return id;
	}
	
	@Override
	public abstract void trigger();
	
	public AbstractEvent() {
		this.id = Moona.generateID();
	}
}
