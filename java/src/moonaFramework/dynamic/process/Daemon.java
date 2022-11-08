package moonaFramework.dynamic.process;

import moonaFramework.base.ProcessCondition;
import moonaFramework.base.Processor;
import moonaFramework.util.annotations.Deadlined;

public abstract class Daemon extends AbstractProcess {
	
	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	@Override
	public abstract void update();
	
	@Override
	public void run() {
		while (!ProcessCondition.DEAD.check(this)) {
			synchronized (getClock()) {
				if (Processor.processCount() == 0) {
					Processor.interrupt(this);
				}
				getClock().pauseHolder();
				if (ProcessCondition.RUNNING.check(this)) {
					update();
				}
			}
		}
	}
	
	public Daemon() {
	}
}
