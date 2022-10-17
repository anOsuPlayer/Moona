package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.relation.Bindable;

public abstract class Worm extends AbstractProcess implements Bindable<Process> {
	
	private Process host;
	public Process getHost() {
		return this.host;
	}
	
	public void setHost(Process newHost) {
		this.host = newHost;
	}
	public void unbind() {
		this.host = null;
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
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
}
