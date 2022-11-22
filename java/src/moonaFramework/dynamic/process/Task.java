package moonaFramework.dynamic.process;

import moonaFramework.util.annotations.Deadlined;

public abstract class Task extends AbstractProcess {

	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	@Override
	public abstract void update();
	
	public Task() {
		super();
	}
}
