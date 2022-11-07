package moonaFramework.process;

public interface ProcessBuilder {

	public static Task fromRunnable(Runnable r) {
		return new Task() {
			@Override
			public void update() {
				r.run();
			}
		};
	}
}
