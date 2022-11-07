package moonaFramework.process;

public interface ProcessHandler {

	static Task buildTask(Runnable r) {
		return new Task() {
			@Override
			public void update() {
				r.run();
			}
		};
	}

	static Daemon buildDaemon(Runnable r) {
		return new Daemon() {
			@Override
			public void update() {
				r.run();
			}
		};
	}
	
	static Worm buildWomr(Runnable r) {
		return new Worm() {
			@Override
			public void update() {
				r.run();
			}
		};
	}
}
