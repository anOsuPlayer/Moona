package moonaframework.dynamic.process;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.relation.Attached;
import moonaframework.util.time.Chrono;

public class ProcessClock extends Chrono implements Attached<Process> {

	private Process host;
	@Override
	public Process getHost() {
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
		if (host.isPaused().verify()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	public ProcessClock(Process host) throws NullArgumentException {
		this.beginning = System.nanoTime();
		if (host == null) {
			throw new NullArgumentException("A ProcessClock's host cannot be null.");
		}
		this.host = host;
	}
}
