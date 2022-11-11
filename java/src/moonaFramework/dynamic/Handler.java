package moonaFramework.dynamic;

import moonaFramework.dynamic.event.AbstractEvent;
import moonaFramework.dynamic.event.Action;
import moonaFramework.dynamic.event.EventMode;
import moonaFramework.dynamic.process.Process;
import moonaFramework.dynamic.process.Daemon;
import moonaFramework.dynamic.process.Task;
import moonaFramework.dynamic.process.Worm;
import moonaFramework.util.condition.Conditional;
import moonaFramework.util.function.Snippet;

public final class Handler {
	
	public static CompositeProcess cloneProcess(Process p) {
		return new CompositeProcess(p::update, p::initialize, p::end, p::onPause, p::onUnpause) {
			@Override
			public int nature() {
				return p.nature();
			}
			
			{
				isRunning().imposeSet(p.isRunning().verify());
				isPaused().imposeSet(p.isPaused().verify());
			}
		};
	}
	
	public static Task buildProcess(Snippet s) {
		return new Task() {
			@Override
			public void update() {
				s.code();
			}
		};
	}

	public static Daemon buildDaemon(Snippet s) {
		return new Daemon() {
			@Override
			public void update() {
				s.code();
			}
		};
	}
	
	public static Worm buildWorm(Snippet s, Process host) {
		return new Worm(host) {
			@Override
			public void update() {
				s.code();
			}
		};
	}
	public static Worm buildWorm(Snippet s) {
		return buildWorm(s, null);
	}
	
	public static AbstractEvent buildEvent(Snippet s) {
		return new AbstractEvent() {			
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	
	public static Action buildAction(Snippet s, EventMode e) {
		return new Action(e) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	public static Action buildAction(Snippet s, int iterations) {
		return new Action(iterations) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	public static Action buildAction(Snippet s, Conditional c) {
		return new Action(c) {
			@Override
			public void trigger() {
				s.code();
			}
		};
	}
	public static Action buildAction(Snippet s) {
		return buildAction(s, EventMode.ONCE);
	}
	
	private Handler() {
	}
}
