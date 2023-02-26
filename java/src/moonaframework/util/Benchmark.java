package moonaframework.util;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.functional.Snippet;

public final class Benchmark {
	
	public static long time(Snippet s) throws NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("Cannot time a null Snippet.");	
		}
		
		long beg = System.nanoTime();
		s.run();
		return System.nanoTime()-beg;
	}
	public static void showTime(Snippet s) throws NullArgumentException {
		long time = Benchmark.time(s);
		System.out.println(time + " ns | " + ((double) time) * 10e-10 + " s");
	}
	
	public static double stress(int cycles, Snippet s) throws IllegalArgumentException, NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("Cannot stress a null Snippet.");
		}
		if (cycles <= 0) {
			throw new IllegalArgumentException("Must iterate at least one time.");
		}
		
		double avg = 0.0;
		for (int i = 0; i < cycles; i++) {
			avg += time(s);
		}
		return avg / cycles;
	}
	public static void showStress(int cycles, Snippet s) throws NullArgumentException {
		System.out.println("avg of: " + stress(cycles, s) + " ns");
	}
	
	public static void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Benchmark() {
		
	}
}
