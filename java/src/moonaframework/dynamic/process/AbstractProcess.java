package moonaframework.dynamic.process;

import moonaframework.base.Moona;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Mirror;

public abstract class AbstractProcess implements Process {
	
	public @Override AbstractProcess clone() {
		return Handler.cloneProcess(this);
	}
	
	private final Nature nature;
	
	public @Override final Nature nature() {
		return this.nature;
	}
	
	public @Deadlined void onPause() {
		
	}
	public @Deadlined void onUnpause() {
		
	}
	
	public @Override abstract void initialize();
	
	public @Override abstract void update();
	
	public @Override abstract void end();

	public @Override void run() {
		while (!ProcessCondition.DEAD.check(this) && Moona.isOn()) {
			synchronized (getClock()) {
				getClock().pauseHolder();
				if (ProcessCondition.RUNNING.check(this)) {
					update();
				}
			}
		}
	}
	
	public AbstractProcess() {
		this.nature = new Nature(new ProcessClock(this), new ProcessStatus(ProcessCondition.DEAD));
		
		Mirror.add(new Method(this.getClass(), "initialize"));
		Mirror.add(new Method(this.getClass(), "end"));
	}
}
