package moonaframework.dynamic.event;

import moonaframework.base.Moona;

public abstract class AbstractEvent implements Event {

	private final long id;
	public @Override final long id() {
		return id;
	}
	
	public @Override abstract void trigger();
	
	public AbstractEvent() {
		this.id = Moona.generateID();
	}
}
