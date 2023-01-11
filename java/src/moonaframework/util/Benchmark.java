package moonaframework.util;

import moonaframework.util.functional.Snippet;

public final class Benchmark {
	
	private static float benchmarkUnit;
	
	private static float computeBenchmarkUnit() {
		float bu = 0;
		for (int i = 0; i < 100; i++) {
			long now = System.nanoTime();
			for (int e = 0; e < 10000; e++);
			bu += System.nanoTime()-now;
		}
		return bu/100.0f;
	}
	
	public static float time(Snippet s) {
		long beg = System.nanoTime();
		s.run();
		return ((System.nanoTime()-beg)*1.0f)/(benchmarkUnit*1.0f);
	}
	public static void showTime(Snippet s) {
		System.out.println(Benchmark.time(s) + " bu");
	}
	
	public static void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static {
		benchmarkUnit = computeBenchmarkUnit();
	}
	
	private Benchmark() {
		
	}
}
