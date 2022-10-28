package moonaFramework.process;

import moonaFramework.Processor;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.essentials.Natural;
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
					synchronized (host.getClock()) {
						if (ProcessCondition.DEAD.check(host)) {
							Processor.interrupt(this);
						}
					}
				}
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
	
	public Worm(Process host) {
		this.host = host;
	}
	public Worm() {
		this(null);
	}
}
