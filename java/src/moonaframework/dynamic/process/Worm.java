package moonaframework.dynamic.process;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Processor;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.relation.Bindable;

public abstract class Worm extends Daemon implements Bindable<Process> {
	
	public @Override Nature nature() {
		return Nature.WORM;
	}
	
	public @Override Worm clone() {
		return (Worm) Handler.cloneProcess(this);
	}
	
	private Process host;
	
	public @Override Process getHost() {
		return host;
	}
	
	public @Override void setHost(Process newHost) {
		this.host = newHost;
	}
	public @Override void unbind() {
		setHost(null);
	}
	
	public @Deadlined void initialize() {
		
	}
	public @Deadlined void end() {
		
	}
	
	public @Override void run() {
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
