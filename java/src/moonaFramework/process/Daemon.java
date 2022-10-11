package moonaFramework.process;

import moonaFramework.Deadlined;
import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.ProcessCondition;
import moonaFramework.relation.Attached;

public abstract class Daemon extends AbstractProcess implements Attached<Moona> {

	@Override
	public int nature() {
		return Natural.DAEMON;
	}
	
	private final Moona host;
	public final Moona getHost() {
		return this.host;
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
				if (host.totalProcesses() == 0) {
					getClock().stasys();
					host.Interrupt(this);
				}
				getClock().pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	public Daemon(Moona host) {
		super();
		this.host = host;
	}
}
