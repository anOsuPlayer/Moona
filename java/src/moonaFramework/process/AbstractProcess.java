package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.ProcessCondition;
import moonaFramework.Status;
import moonaFramework.annotations.Deadlined;

/**
 * Introducing itself as the simplest process of them all, I am proud to present to you the Abstract Process!
 * This abstract class directly implements the primitive Process Interface, without adding any new methods.
 * <br><br>
 * This particular abstract class was introduced to restrict even more the usage of Processes inside of
 * Moona: being them very hard to handle properly, when creating new process-like structures it's better to
 * start from a solid base, like an abstract class such as this (these are much more flexible than
 * interfaces: extending this class to create new processes already gives you a good head start).
 * <br><br>
 * It was made to, mostly, give more possibilities on the inheritance side: other than simply implementing
 * the process interface, this class already has all the necessary fields for the methods to return
 * (like the isPaused and isRunning Status and more).
 * 
 * @author MasterZEr0
 */
public abstract class AbstractProcess implements Process {
	
	/**
	 * Stores the ID of this process.
	 */
	private final long id;
	/**
	 * Returns {@link #id}.
	 */
	@Override
	public final long id() {
		return this.id;
	}
	/**
	 * By default, return the Moona.PROCESS field, which is 0. This method might change for some subclass.
	 */
	@Override
	public int nature() {
		return Natural.PROCESS;
	}
	
	/**
	 * Stores the ProcessClock relative to this object.
	 */
	private final ProcessClock clock;
	/**
	 * Returns {@link #clock} field.
	 */
	@Override
	public final ProcessClock getClock() {
		return clock;
	}
	
	/**
	 * Status representing whether this process is running or not.
	 */
	private final Status isRunning;
	/**
	 * Returns {@link #isRunning} field.
	 */
	@Override
	public final Status isRunning() {
		return isRunning;
	}
	
	/**
	 * Status representing whether this process is paused or not.
	 */
	private final Status isPaused;
	/**
	 * Returns {@link #isPaused} field.
	 */
	@Override
	public final Status isPaused() {
		return isPaused;
	}
	
	/**
	 * Initially not declared (to not force overriding when not needed), it executes a certain action when
	 * the process is paused.
	 */
	@Deadlined
	public void onPause() {
	}
	/**
	 * Initially not declared (to not force overriding when not needed), it executes a certain action when
	 * the process is not paused anymore.
	 */
	@Deadlined
	public void onUnpause() {
	}
	
	@Override
	public abstract void initialize();
	@Override
	public abstract void update();
	@Override
	public abstract void end();
	
	/**
	 * Indirectly implemented from the Runnable interface, links Java Threads to Moona Processes. In the
	 * simplest case (this class), this method only features a while loop, executing until the process does
	 * not die, monitoring the {@link #isPaused} field to know when to pause and calling the {@link #update()}
	 * method. Those actions are synchronized to the class' {@link #clock} field.
	 */
	@Override
	public void run() {
		while (!ProcessCondition.DEAD.check(this) && Moona.IsOn()) {
			synchronized (clock) {
				clock.pauseHolder();
				if (!ProcessCondition.DEAD.check(this)) {
					update();
				}
			}
		}
	}
	
	/**
	 * Standard constructor which initializes the four private fields.
	 */
	public AbstractProcess() {
		this.id = Moona.GenerateID();
		this.clock = new ProcessClock(this);
		this.isRunning = new Status(false);
		this.isPaused = new Status(false);
	}
}
