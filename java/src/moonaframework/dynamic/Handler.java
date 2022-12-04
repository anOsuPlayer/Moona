package moonaframework.dynamic;

import moonaframework.base.Agent;
import moonaframework.dynamic.event.AbstractEvent;
import moonaframework.dynamic.event.Action;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.process.AbstractProcess;
import moonaframework.dynamic.process.Daemon;
import moonaframework.dynamic.process.Process;
import moonaframework.dynamic.process.Task;
import moonaframework.dynamic.process.Worm;
import moonaframework.util.condition.Conditional;
import moonaframework.util.functional.Snippet;

public final class Handler {
	
	public static AbstractProcess cloneProcess(Process p) {
		Synthetized clone = new Synthetized(p::update, p::initialize, p::end, p::onPause, p::onUnpause);
		ProcessCondition.cloneCondition(clone, p);
		return switch (p.nature()) {
			case WORM: yield castWorm(clone);
			case DAEMON: yield castDaemon(clone);
			default: yield castTask(clone);
		};
	}
	
	public static Task castTask(Process p) {
		Task newTask = new Task() {
			public @Override void onPause() {
				p.onPause();
			}
			public @Override void onUnpause() {
				p.onUnpause();
			}
			
			public @Override void initialize() {
				p.initialize();
			}
			public @Override void update() {
				p.update();
			}
			public @Override void end() {
				p.end();
			}
		};
		ProcessCondition.cloneCondition(p, newTask);
		ProcessCondition.DEAD.set(p);
		Processor.addProcess(newTask);
		Processor.buildProcess(newTask);
		return newTask;
	}
	public static Task buildProcess(Snippet s) {
		return new Task() {
			public @Override void update() {
				s.code();
			}
		};
	}

	public static Daemon castDaemon(Process p) {
		Daemon newDaemon = new Daemon() {
			public @Override void onPause() {
				p.onPause();
			}
			public @Override void onUnpause() {
				p.onUnpause();
			}
			
			public @Override void initialize() {
				p.initialize();
			}
			public @Override void update() {
				p.update();
			}
			public @Override void end() {
				p.end();
			}
		};
		ProcessCondition.cloneCondition(p, newDaemon);
		ProcessCondition.DEAD.set(p);
		Processor.addProcess(newDaemon);
		Processor.buildProcess(newDaemon);
		return newDaemon;
	}
	public static Daemon buildDaemon(Snippet s) {
		return new Daemon() {
			public @Override void update() {
				s.code();
			}
		};
	}
	
	public static Worm castWorm(Process p) {
		Worm newWorm = new Worm() {
			public @Override void onPause() {
				p.onPause();
			}
			public @Override void onUnpause() {
				p.onUnpause();
			}
			
			public @Override void initialize() {
				p.initialize();
			}
			public @Override void update() {
				p.update();
			}
			public @Override void end() {
				p.end();
			}
		};
		ProcessCondition.cloneCondition(p, newWorm);
		if (p instanceof Worm w) { newWorm.setHost(w.getHost()); }
		ProcessCondition.DEAD.set(p);
		Processor.addProcess(newWorm);
		Processor.buildProcess(newWorm);
		return newWorm;
	}
	public static Worm buildWorm(Snippet s, Process host) {
		return new Worm(host) {
			public @Override void update() {
				s.code();
			}
		};
	}
	public static Worm buildWorm(Snippet s) {
		return buildWorm(s, null);
	}
	
	public static AbstractEvent buildEvent(Snippet s) {
		return new AbstractEvent() {
			public @Override void trigger() {
				s.code();
			}
		};
	}
	public static AbstractEvent castEvent(Event e) {
		AbstractEvent newEv = buildEvent(e.translate());
		if (Agent.has(e)) {
			Agent.exclude(e);
			Agent.include(newEv);
		}
		return newEv;
	}
	
	public static Action buildAction(Snippet s, EventMode e) {
		return new Action(e) {
			public @Override void trigger() {
				s.code();
			}
		};
	}
	public static Action castAction(Event e, EventMode em) {
		Action newAct = buildAction(e.translate(), em);
		if (Agent.has(e)) {
			Agent.exclude(e);
			Agent.include(newAct);
		}
		return newAct;
	}
	
	public static Action buildAction(Snippet s, int iterations) {
		return new Action(iterations) {
			public @Override void trigger() {
				s.code();
			}
		};
	}
	public static Action castAction(Event e, int iterations) {
		Action newAct = buildAction(e.translate(), iterations);
		if (Agent.has(e)) {
			Agent.exclude(e);
			Agent.include(newAct);
		}
		return newAct;
	}
	
	public static Action buildAction(Snippet s, Conditional c) {
		return new Action(c) {
			public @Override void trigger() {
				s.code();
			}
		};
	}
	public static Action castAction(Event e, Conditional c) {
		Action newAct = buildAction(e.translate(), c);
		if (Agent.has(e)) {
			Agent.exclude(e);
			Agent.include(newAct);
		}
		return newAct;
	}
	
	public static Action buildAction(Snippet s) {
		return buildAction(s, EventMode.ONCE);
	}
	public static Action castAction(Event e) {
		Action newAct = buildAction(e.translate());
		if (Agent.has(e)) {
			Agent.exclude(e);
			Agent.include(newAct);
		}
		return newAct;
	}
	
	private Handler() {
	}
}
