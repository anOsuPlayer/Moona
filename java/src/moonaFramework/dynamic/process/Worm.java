package moonaFramework.dynamic.process;

import moonaFramework.base.Moona;
import moonaFramework.base.Natural;
import moonaFramework.dynamic.ProcessCondition;
import moonaFramework.dynamic.Processor;
import moonaFramework.util.annotations.Deadlined;
import moonaFramework.util.relation.Bindable;

public abstract class Worm extends Daemon implements Bindable<Process> {
	
	@Override
	public int nature() {
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
		while (!ProcessCondition.DEAD.check(this) && Moona.isOn()) {
			synchronized (getClock()) {
				if (host != null) {
					if (ProcessCondition.DEAD.check(host)) {
						Processor.interrupt(this);
						break;
					}
				}
				if (Processor.processCount() == 0) {
					Processor.interrupt(this);
					break;
				}
				getClock().pauseHolder();
				if (ProcessCondition.RUNNING.check(this)) {
					update();
				}
			}
		}
	}
	
	public Worm(Process host) {
		super();
		this.host = host;
	}
	public Worm() {
		this(null);
	}
}
