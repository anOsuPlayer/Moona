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
		CompositeProcess clone = new CompositeProcess(p::update, p::initialize, p::end, p::onPause,
				p::onUnpause) {
			@Override
			public int nature() {
				return p.nature();
			}
			
			@Override
			public void run() {
				p.run();
			}
		};
		ProcessCondition.cloneCondition(p, clone);
		return clone;
	}
	
	public static Task castTask(Process p) {
		if (Processor.has(p)) { Processor.terminate(p); }
		Task newTask = new Task() {
			@Override
			public void onPause() {
				p.onPause();
			}
			@Override
			public void onUnpause() {
				p.onUnpause();
			}
			
			@Override
			public void initialize() {
				p.initialize();
			}
			@Override
			public void update() {
				p.update();
			}
			@Override
			public void end() {
				p.end();
			}
		};
		ProcessCondition.cloneCondition(p, newTask);
		Processor.add(newTask);
		return newTask;
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
