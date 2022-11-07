package moonaFramework.dynamic.process;

import moonaFramework.base.ProcessCondition;
import moonaFramework.base.Processor;
import moonaFramework.util.annotations.Deadlined;
import moonaFramework.util.relation.Bindable;

public abstract class Worm extends Daemon implements Bindable<Process> {
	
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
					if (ProcessCondition.DEAD.check(host)) {
						Processor.interrupt(this);
					}
				}
				if (Processor.totalProcesses() == 0) {
					Processor.interrupt(this);
				}
				getClock().pauseHolder();
				if (ProcessCondition.RUNNING.check(this)) {
					update();
				}
			}
		}
	}
	
	public Worm(Process host) {
		this.host = host;
	}
	public Worm() {
		this(null);
	}
}
