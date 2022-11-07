package moonaFramework.util;

import moonaFramework.util.function.Snippet;

/**
 * The Benchmark class is just a simple toolkit: it contains some useful tool for you developers to test
 * aspects of Moona from a debug perspective. This class cannot be instantiated, it uses a series of static
 * methods to make you access its features (instantiating it would be ratherly useless, for how it's built).
 * 
 * @author MasterZEr0
 */
public class Benchmark {

	/**
	 * This method returns the time that took the Runnable's .run() method to execute and returns it
	 * in nanoseconds.
	 * 
	 * @param r : The Runnable to time.
	 * @return the ammount of time elapsed.
	 */
	public static final long TIME(Snippet r) {
		long beginning = System.nanoTime();
		r.code();
		return (System.nanoTime() - beginning);
	}
	
	public static final void WAIT(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Private constructor due to forbidden instantiability.
	 */
	private Benchmark() {
		
	}
}
