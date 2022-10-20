package moonaFramework.time;

/**
 * A Chrono is a very particular object. It was thought in order to achieve two things: a revamped use of
 * the .wait() and .notify() methods and a very good option for an object to put inside synchronized blocks.
 * 
 * It handles waiting and notifying processes in a pretty smart way: all of the following methods use the
 * Chrono itself as an argument for synchronized blocks that wrap .wait() and .notify(). Since their purpose
 * is only wrapping waiting and notifying operations, there are no losses in terms of thread safety:
 * InterruptedExceptions are properly handled in order to not have any issues.
 * 
 * They also are a good choice for synchronized blocks since they only require one method call to be either
 * set on a waiting state or notified. Forget the old and bulky try-catch method to use the .wait() method
 * on a normal object, just use one line and you'll do the same thing in an even clearer way!
 *	
 * The other (and more serius) motivation because of why they should be used there is that, since they
 * auto-refer themselves when waiting and notifying, they can also be used for synchronization... aren't
 * they cool?
 * 
 * @author MasterZEr0
 */
public class Chrono extends Clock {

	/**
	 * Synchronizing to the Chrono itself, this method awakens it with the .notify() method and then uses
	 * the wait(long mills, int nanos) method to make the calling thread wait for the given ammount of time.
	 */
	@Override
	public void sleep(long mills, int nanos) throws IllegalArgumentException {
		if (mills < 0 || nanos < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		synchronized (this) {
			this.notify();
			try {
				this.wait(mills, nanos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void sleep(long mills) throws IllegalArgumentException {
		if (mills < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep(mills, 0);
	}
	@Override
	public void sleep(int micros) throws IllegalArgumentException {
		if (micros < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		long mills = micros / 1000;
		sleep(mills, (micros * 1000) % 1000000);
	}
	
	@Override
	public void sleep(double seconds) throws IllegalArgumentException {
		if (seconds < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep((long) seconds * 1000 + ((int) ((seconds - ((int) seconds)) * 1000)),
				((int) (((seconds - ((int) seconds)) * 1000000000)) % 1000000));
	}
	
	/**
	 * By calling this method, you automatically put the Chrono in a waiting state, it's like you had called
	 * the .wait() method on the Chrono itself. It uses a synchronized block which is synced with the
	 * instance.
	 */
	public void stasys() {
		synchronized (this) {
			this.notify();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * It's the Chrono's analogue to the .notify() method. It calls the notification procedure inside of a
	 * synchronized block synchronized with, you guessed it, the Chrono itself.
	 */
	public void release() {
		synchronized (this) {
			this.notify();
		}
	}
	
	/**
	 * Default constructor which refers to its superclass' one.
	 */
	public Chrono() {
		super();
	}
}
