package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.essentials.Dynamic;
import moonaFramework.process.Task;

public abstract class AutoEvent extends Task implements Event, Dynamic {
	
	@Override
	public abstract void trigger();
	
	@Override
	public void update() {
		trigger();
		Moona.interrupt(this);
	}
	
	public AutoEvent() {
		super();
	}
}
