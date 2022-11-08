package moonaFramework.dynamic;

import moonaFramework.dynamic.event.AbstractEvent;
import moonaFramework.dynamic.event.Action;
import moonaFramework.dynamic.event.EventMode;
import moonaFramework.dynamic.process.AbstractProcess;
import moonaFramework.dynamic.process.Daemon;
import moonaFramework.dynamic.process.Task;
import moonaFramework.dynamic.process.Worm;
import moonaFramework.util.annotations.Deadlined;
import moonaFramework.util.condition.Conditional;
import moonaFramework.util.function.Snippet;

public interface Handler {
	
	static AbstractProcess buildProcess(Snippet s) {
		return new AbstractProcess() {
			@Override
			public void update() {
				s.code();
			}

			@Deadlined
			public void initialize() {
			}
			@Deadlined
			public void end() {
			}
		};
	}
	
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
	
	static Worm buildWorm(Snippet s) {
		return new Worm() {
			@Override
			public void update() {
				s.code();
			}
		};
	}
	
	static AbstractEvent buildEvent(Snippet s) {
		return new AbstractEvent() {			
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	
	static Action buildAction(Snippet s, EventMode e) {
		return new Action(e) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	static Action buildAction(Snippet s, int iterations) {
		return new Action(iterations) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	static Action buildAction(Snippet s, Conditional c) {
		return new Action(c) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	static Action buildAction(Snippet s) {
		return buildAction(s, EventMode.ONCE);
	}
}
