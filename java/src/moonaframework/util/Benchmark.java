package moonaframework.util;

import moonaframework.util.functional.Snippet;

public final class Benchmark {
	
	public static long time(Snippet s) {
		long beg = System.nanoTime();
		s.run();
		return System.nanoTime()-beg;
	}
	public static void showTime(Snippet s) {
		System.out.println(Benchmark.time(s) + " ns");
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
