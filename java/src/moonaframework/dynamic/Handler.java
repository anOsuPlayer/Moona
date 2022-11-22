package moonaframework.dynamic;

import moonaframework.dynamic.event.AbstractEvent;
import moonaframework.dynamic.event.Action;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.process.Daemon;
import moonaframework.dynamic.process.Process;
import moonaframework.dynamic.process.Task;
import moonaframework.dynamic.process.Worm;
import moonaframework.util.condition.Conditional;
import moonaframework.util.function.Snippet;

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
		ProcessCondition.DEAD.set(p);
		Processor.add(newTask);
		Processor.buildProcess(newTask);
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

	public static Daemon castDaemon(Process p) {
		Daemon newDaemon = new Daemon() {
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
		ProcessCondition.cloneCondition(p, newDaemon);
		ProcessCondition.DEAD.set(p);
		Processor.add(newDaemon);
		Processor.buildProcess(newDaemon);
		return newDaemon;
	}
	public static Daemon buildDaemon(Snippet s) {
		return new Daemon() {
			@Override
			public void update() {
				s.code();
			}
		};
	}
	
	public static Worm castWorm(Process p) {
		Worm newWorm = new Worm() {
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
		ProcessCondition.cloneCondition(p, newWorm);
		if (p instanceof Worm w) { newWorm.setHost(w.getHost()); }
		ProcessCondition.DEAD.set(p);
		Processor.add(newWorm);
		Processor.buildProcess(newWorm);
		return newWorm;
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
