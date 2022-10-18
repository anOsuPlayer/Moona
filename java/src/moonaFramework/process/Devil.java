package moonaFramework.process;

import moonaFramework.Phase;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.relation.Attached;

public abstract class Devil extends Daemon implements Attached<Phase> {

	private final Phase host;
	@Override
	public final Phase getHost() {
		return host;
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
				if (host.processCount() == 0) {
					host.interrupt(this);
				}
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	public Devil(Phase host) {
		this.host = host;
	}
	private Devil() {
		this(null);
	}
}
