package moonaframework.dynamic.process;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Processor;
import moonaframework.util.annotations.Deadlined;

public abstract class Daemon extends AbstractProcess {
	
	@Override
	public Nature nature() {
		return Nature.DAEMON;
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
		while (!ProcessCondition.DEAD.check(this) && Moona.isOn()) {
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
