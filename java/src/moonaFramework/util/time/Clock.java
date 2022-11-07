package moonaFramework.util.time;

/**
 * A Clock is a pretty simple object to operate with thread-related sleeping tasks. Other than that, it's
 * the base of many other time-related elements spread throughout the framework.
 * Currently, it just features a bunch of wrappers which handle in different ways the
 * Thread.sleep(long millis) method, from the Thread class. Those methods serve as the building blocks
 * for more complex objects such as the Chrono.
 * 
 * @author MasterZEr0
 */
public class Clock {

	/**
	 * Wraps the Thread.sleep(long millis, int nanos). It avoids you to write that gigantic try-catch
	 * block when you just want to give your poor threads a bit of rest... a very kind method, isnt't it?
	 * Jokes aside, in order to avoid potentially tragic issues, InterruptedExceptions are correctly handled,
	 * too.
	 * 
	 * @param mills : The ammount of milliseconds to sleep.
	 * @param nanos : The ammount of nanoseconds to sleep.
	 */
	public void sleep(long mills, int nanos) throws IllegalArgumentException {
		if (mills < 0 || nanos < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		try {
			Thread.sleep(mills, nanos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sleeps the given ammount of milliseconds by making a call to sleep(long mills, int nanos).
	 * 
	 * @param mills : The ammount of milliseconds to sleep.
	 */
	public void sleep(long mills) throws IllegalArgumentException {
		if (mills < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep(mills, 0);
	}
	/**
	 * Sleeps the given ammount of microseconds by making a call to sleep(long mills, int nanos). If the
	 * given integer is bigger than 1 thousand microseconds (1 ms), the adequate conversion is performed.
	 * This method was created because, sometimes, it's usefull to work with microseconds too.
	 * 
	 * @param micros : The ammount of microseconds to sleep.
	 */
	public void sleep(int micros) throws IllegalArgumentException {
		if (micros < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		long mills = micros / 1000;
		sleep(mills, ((micros * 1000) % 1000000));
	}
	
	/**
	 * Sleeps the given ammount of seconds by making a call to sleep(long mills, int nanos). In this case,
	 * the integer part of the double corresponds to the given quantity of seconds to sleep (the decimal
	 * part gets adequately converted, too).
	 * 
	 * @param seconds : The ammount of seconds to sleep.
	 */
	public void sleep(double seconds) throws IllegalArgumentException {
		if (seconds < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep((long) seconds * 1000 + ((int) ((seconds - ((int) seconds)) * 1000)),
				((int) (((seconds - ((int) seconds)) * 1000000000)) % 1000000));
	}
	
	/**
	 * Clock's class default constructor.
	 */
	public Clock() {
		
	}
}
