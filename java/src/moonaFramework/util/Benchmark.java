package moonaFramework.util;

import moonaFramework.util.function.Snippet;

public class Benchmark {

	public static final native long time(Snippet s);
	
	public static final native double stress(Snippet s, int iterations);
	
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
