package moonaFramework.event;

import moonaFramework.Phase;
import moonaFramework.essentials.Dynamic;
import moonaFramework.process.Task;

public abstract class AutoEvent extends Task implements Event, Dynamic {
	
	@Override
	public abstract void trigger();
	
	@Override
	public void update() {
		trigger();
		Phase.interrupt(this);
	}
	
	public AutoEvent() {
		super();
	}
}
