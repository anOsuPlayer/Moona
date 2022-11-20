package moonaFramework.util;

import moonaFramework.util.function.Snippet;

public class Benchmark {

	public static final long time(Snippet r) {
		long beginning = System.nanoTime();
		r.code();
		return (System.nanoTime() - beginning);
	}
	
	public static final void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static final double stress(Snippet r, long waitTime, int iterations) {
		double total = 0.0;
		for (int i = 0; i < iterations; i++) {
			total += 1.0 * time(r);
			sleep(waitTime);
		}
		return total / iterations;
	}
	
	private Benchmark() {
		
	}
}
