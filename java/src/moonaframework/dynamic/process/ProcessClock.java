package moonaframework.dynamic.process;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.relation.Attached;
import moonaframework.util.time.Chrono;

public class ProcessClock extends Chrono implements Attached<Process> {

	private Process host;
	
	public @Override Process getHost() {
		return host;
	}
	
	private final long beginning;
	
	public final long getBeginning() {
		return beginning;
	}
	
	public final long lifeTime() {
		return System.nanoTime() - beginning;
	}
	
	public void pauseHolder() {
		if (host.isPaused()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	ProcessClock(Process host) throws NullArgumentException {
		this.beginning = System.nanoTime();
		this.host = host;
	}
}
