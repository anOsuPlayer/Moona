package moonaFramework;

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
	 * @return
	 */
	public static final long TIME(Runnable r) {
		long beginning = System.nanoTime();
		r.run();
		return (System.nanoTime() - beginning);
	}
	
	/**
	 * Private constructor due to forbidden instantiability.
	 */
	private Benchmark() {
		
	}
}
