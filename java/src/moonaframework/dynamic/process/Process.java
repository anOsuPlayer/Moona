package moonaframework.dynamic.process;

import moonaframework.base.Serial;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.Status;

/**
 * The Process Interface is the base of all process-like types, it features all the basic methods to make a
 * process work alongside Moona.
 * <br><br>
 * All the process-like structures depend from this interface. It inherits both the Serial and
 * java.lang.Runnable interface: this makes the Process Interface both suitable to be initialized into a Java
 * Thread and also to interact with Moona seamlessly. It also features many different methods other than the
 * .run() method in the Runnable Interface that contribute to expand the flexibility of this type.
 * <br><br>
 * Processes behave almost like threads, they need to be started and, from there, they can be paused or
 * interrupted. Even though the basics are the same, the practical side is not exactly equal: while threads
 * depend on their instances (meaning you have to call methods from an instance in order to work with a
 * thread), Moona processes are interacted with via static methods inside the Moona Class. This was made in
 * order to achieve properly structured procedures to enhance efficiency and to expand the concept of regular
 * Java Runnables.
 * 
 * @author MasterZEr0
 */
public interface Process extends Runnable, Dynamic, Serial {
	
	@Override
	long id();
	
	/**
	 * Each class implementing this interface must return a ProcessClock, usually stored in a field.
	 * @return the hypothetical ProcessClock field.
	 */
	ProcessClock getClock();
	
	/**
	 * Each class implementing this interface must return a Status which tells whether or not this process
	 * is running.
	 * @return the hypothetical isRunning status.
	 */
	Status isRunning();
	/**
	 * Each class implementing this interface must return a Status telling whether or not this process
	 * is running.
	 * @return a status telling whether or not this process is paused.
	 */
	Status isPaused();
	
	/**
	 * It executes a certain action when the process is paused. It's called by the Sparking methods inside
	 * the Moona Class when needed. In the majority of this interface's subtypes, this method is declared
	 * with an empty body, to not force any override if not needed.
	 */
	void onPause();
	/**
	 * It executes a certain action when the process exits the pause state. It's called by the Sparking
	 * methods inside the Moona Class when needed. In the majority of this interface's subtypes, this method
	 * is declared with an empty body, to not force any override if not needed.
	 */
	void onUnpause();
	
	/**
	 * The first method executed when a Process is Started inside the Moona Class. Moona executes this method
	 * before entering the Thread phase.
	 */
	void initialize();
	/**
	 * This method gets executed according to the run() method. Moona never invokes this method directly;
	 * this is because it's mostly used inside the run() method to make the process do something. By
	 * convention, raw instructions are never written inside the run() method (for an organization matter)
	 * but instead they're all written inside here in the update() method. This method also improves
	 * polymorphism: in order to avoid to override the run() method (which, in some sub-processes, is already
	 * set up to work in a certain way) we use this one instead.
	 */
	void update();
	/**
	 * It's the last method getting called when Moona ends the process' Thread. In the Interruption phase.
	 * This gets called when the Thread ends, so after the process is declared dead.
	 */
	void end();
	
	/**
	 * The method which governs the Process throughout time. Directly implemented from the Runnable Interface,
	 * this method is also needed to initialize the Thread where the process will live. In each one of the
	 * processes I've created, this method is always already overridden and it features all the adequate
	 * instructions to make the process work correctly. The run() method is likely to cooperate with the
	 * update() method.
	 */
	@Override
	void run();
}
