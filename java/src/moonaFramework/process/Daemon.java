package moonaFramework.process;

import moonaFramework.Processor;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.essentials.Natural;

public abstract class Daemon extends AbstractProcess {
	
	@Override
	public int nature() {
		return Natural.DAEMON;
	}
	
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
				if (Processor.totalProcesses() == 0) {
					Processor.interrupt(this);
				}
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	public Daemon() {
	}
}
