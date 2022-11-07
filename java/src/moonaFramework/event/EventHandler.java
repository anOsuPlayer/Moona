package moonaFramework.event;

import moonaFramework.util.Conditional;

public interface EventHandler {

	static AbstractEvent buildAbstractEvent(Runnable r) {
		return new AbstractEvent() {			
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	
	static Action buildAction(Runnable r, EventMode e) {
		return new Action(e) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Runnable r, int iterations) {
		return new Action(iterations) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Runnable r, Conditional c) {
		return new Action(c) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Runnable r) {
		return buildAction(r, EventMode.ONCE);
	}
}
