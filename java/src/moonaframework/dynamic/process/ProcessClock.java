package moonaframework.dynamic.process;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.relation.Attached;
import moonaframework.util.time.Chrono;

/**
 * A ProcessClock is an extended Chrono which helps Processes to handle waiting times, pauses and
 * synchronization.
 * 
 * Since these objects are required to operate in close contact with Processes, this class implements the
 * Attached interface. Being them attached to a Process, they're much more easy to work, since you're able to
 * both recall the Process from its ProcessClock and vice versa.
 * 
 * @author MasterZEr0
 */
public class ProcessClock extends Chrono implements Attached<Process> {

	/**
	 * The field containing this ProcessClock's host process.
	 */
	private Process host;
	/**
	 * Returns the host of this ProcessClock.
	 * returns {@link #host}.
	 */
	@Override
	public Process getHost() {
		return host;
	}
	
	/**
	 * This field contains a final long value which stores the time (in nanoseconds) which passed since the
	 * creation of this Clock. Another use of this field is to store the moment in which the process was
	 * started.
	 */
	private final long beginning;
	/**
	 * Returns (in nanoseconds) the time value in which this ProcessClock was created.
	 * @return {@link #beginning}
	 */
	public final long getBeginning() {
		return beginning;
	}
	
	/**
	 * Returns the ammount of time which passed between the creation of this object and the call of this
	 * method.
	 * @return Clock's lifetime in nanoseconds.
	 */
	public final long lifeTime() {
		return System.nanoTime() - beginning;
	}
	
	/**
	 * This method is perhaps the most important one in this class. The pauseHolding procedure is a way of
	 * pausing and resuming a Process according to its .isPaused() status' value. It directly checks that
	 * method from the host and, from there, it pauses or unpauses the Process accordingly.
	 */
	public void pauseHolder() {
		if (host.isPaused().verify()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	/**
	 * This constructor is the only way to initialize a new ProcessClock. Since these objects are made to
	 * be bound to an host, it would not make any sense to initialize one without it.
	 * @param host The host for the new ProcessClock.
	 * @throws IllegalArgumentException Thrown if the Process is null.
	 */
	public ProcessClock(Process host) throws NullArgumentException {
		this.beginning = System.nanoTime();
		if (host == null) {
			throw new NullArgumentException("A ProcessClock's host cannot be null.");
		}
		this.host = host;
	}
}
