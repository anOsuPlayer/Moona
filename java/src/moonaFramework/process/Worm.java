package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.relation.Bindable;

public abstract class Worm extends Daemon implements Bindable<Process> {
	
	@Override
	public final int nature() {
		return Natural.WORM;
	}
	
	private Process host;
	@Override
	public Process getHost() {
		return host;
	}
	
	@Override
	public void setHost(Process newHost) {
		this.host = newHost;
	}
	@Override
	public void unbind() {
		setHost(null);
	}
	
	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	@Override
	public void run() {
		while (!ProcessCondition.DEAD.check(this)) {
			synchronized (getClock()) {
				if (host != null) {
					if (!ProcessCondition.DEAD.check(host)) {
						Moona.interrupt(this);
					}
				}
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
}
