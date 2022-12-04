package moonaframework.dynamic.process;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Processor;
import moonaframework.util.annotations.Deadlined;

public abstract class Daemon extends AbstractProcess {
	
	public @Override Nature nature() {
		return Nature.DAEMON;
	}
	
	public @Override Daemon clone() {
		return (Daemon) Handler.cloneProcess(this);
	}
	
	public @Deadlined void initialize() {
	
	}
	public @Deadlined void end() {
	
	}
	
	public @Override abstract void update();
	
	public @Override void run() {
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
