package moonaFramework.event;

import moonaFramework.basics.Snippet;
import moonaFramework.util.Conditional;

public interface EventHandler {

	static AbstractEvent buildAbstractEvent(Snippet r) {
		return new AbstractEvent() {			
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	
	static Action buildAction(Snippet r, EventMode e) {
		return new Action(e) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Snippet r, int iterations) {
		return new Action(iterations) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Snippet r, Conditional c) {
		return new Action(c) {
			@Override
			public void trigger() {
				r.run();
			}
		};
	}
	static Action buildAction(Snippet r) {
		return buildAction(r, EventMode.ONCE);
	}
}
