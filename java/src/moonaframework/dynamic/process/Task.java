package moonaframework.dynamic.process;

import moonaframework.util.annotations.Deadlined;

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
