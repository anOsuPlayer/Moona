package moonaFramework.process;

import moonaFramework.Moona;
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
				if (Moona.totalProcesses() == 0) {
					Moona.interrupt(this);
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
