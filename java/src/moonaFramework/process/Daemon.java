package moonaFramework.process;

import moonaFramework.Deadlined;
import moonaFramework.Moona;
import moonaFramework.ProcessCondition;

public abstract class Daemon extends AbstractProcess {

	@Override
	public int nature() {
		return Moona.DAEMON;
	}
	
	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	public abstract void update();
	
	public void run() {
		while (!ProcessCondition.DEAD.check(this) && Moona.IsOn()) {
			synchronized (getClock()) {
				if (Moona.totalProcesses() == 0) {
					getClock().stasys();
					Moona.Interrupt(this);
				}
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	public Daemon() {
		super();
	}
}
