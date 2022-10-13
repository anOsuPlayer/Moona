package moonaFramework.process;

import moonaFramework.Phase;
import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.ProcessCondition;
import moonaFramework.annotations.Deadlined;
import moonaFramework.relation.Attached;

public abstract class Daemon extends AbstractProcess implements Attached<Phase> {

	@Override
	public int nature() {
		return Natural.DAEMON;
	}
	
	private final Phase host;
	public final Phase getHost() {
		return this.host;
	}
	
	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	public abstract void update();
	
	@Override
	public void run() {
		while (!ProcessCondition.DEAD.check(this) && Moona.IsOn()) {
			synchronized (getClock()) {
				if (host.processCount() == 0) {
					host.Interrupt(this);
				}
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	public Daemon(Phase host) {
		super();
		this.host = host;
	}
}
