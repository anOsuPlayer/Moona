package moonaFramework;

public class Benchmark {

	public static final long TIME(Runnable r) {
		long beginning = System.nanoTime();
		r.run();
		return (System.nanoTime() - beginning);
	}
	
	private Benchmark() {
		
	}
}
