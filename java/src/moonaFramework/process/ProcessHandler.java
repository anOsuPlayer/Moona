package moonaFramework.process;

import moonaFramework.basics.Snippet;

public interface ProcessHandler {

	static Task buildTask(Snippet r) {
		return new Task() {
			@Override
			public void update() {
				r.run();
			}
		};
	}

	static Daemon buildDaemon(Snippet r) {
		return new Daemon() {
			@Override
			public void update() {
				r.run();
			}
		};
	}
	
	static Worm buildWomr(Snippet r) {
		return new Worm() {
			@Override
			public void update() {
				r.run();
			}
		};
	}
}
