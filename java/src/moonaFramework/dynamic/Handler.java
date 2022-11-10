package moonaFramework.dynamic;

import moonaFramework.dynamic.event.AbstractEvent;
import moonaFramework.dynamic.event.Action;
import moonaFramework.dynamic.event.EventMode;
import moonaFramework.dynamic.process.AbstractProcess;
import moonaFramework.dynamic.process.Process;
import moonaFramework.dynamic.process.Daemon;
import moonaFramework.dynamic.process.Task;
import moonaFramework.dynamic.process.Worm;
import moonaFramework.util.annotations.Deadlined;
import moonaFramework.util.condition.Conditional;
import moonaFramework.util.function.Snippet;

public final class Handler {
	
	public static AbstractProcess cloneProcess(Process p) {
		return new AbstractProcess() {
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
			
			@Override
			public void run() {
				p.run();
			}
			
			{
				isRunning().imposeSet(p.isRunning().verify());
				isPaused().imposeSet(p.isPaused().verify());
			}
		};
	}
	
	public static AbstractProcess buildProcess(Snippet s) {
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
	
	public static Task buildTask(Snippet s) {
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
