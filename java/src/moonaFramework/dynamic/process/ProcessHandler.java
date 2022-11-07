package moonaFramework.dynamic.process;

import moonaFramework.util.function.Snippet;

public interface ProcessHandler {

	static Task buildTask(Snippet s) {
		return new Task() {
			@Override
			public void update() {
				s.code();
			}
		};
	}

	static Daemon buildDaemon(Snippet s) {
		return new Daemon() {
			@Override
			public void update() {
				s.code();
			}
		};
	}
	
	static Worm buildWomr(Snippet s) {
		return new Worm() {
			@Override
			public void update() {
				s.code();
			}
		};
	}
}
