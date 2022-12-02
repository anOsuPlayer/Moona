package moonaframework.util;

import moonaframework.util.functional.Snippet;

public class Benchmark {

	public static final long time(Snippet s) {
		long beg = System.nanoTime();
		s.code();
		return System.nanoTime()-beg;
	}
	public static final void showTime(Snippet s) {
		System.out.println(Benchmark.time(s));
	}
	
	public static final double stress(Snippet s, int iterations) {
		double total = 0;
		for (int i = 0; i < iterations; i++) {
			total += time(s);
		}
		return total / iterations;
	}
	public static final void showStress(Snippet s, int iterations) {
		System.out.println(Benchmark.stress(s, iterations));
	}
	
	public static final void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Benchmark() {
		
	}
}
