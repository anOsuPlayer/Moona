package moonaframework.dynamic.process;

import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Processor;
import moonaframework.util.Packable;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.time.Timer;

public class Synchronizer extends Task implements Packable<Process> {
	
	private final Process[] synced;
	
	public @Override final Process[] pack() {
		return synced;
	}
	
	private Timer cooldown;
	
	public Timer getTimer() {
		return cooldown;
	}
	public void setTimer(Timer t) {
		this.cooldown = t;
	}
	
	public @Override void onPause() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.onPause();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	public @Override void onUnpause() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.onUnpause();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	public @Override void initialize() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.initialize();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	public @Override void update() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.update();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	public @Override void end() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.end();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	public Synchronizer(Timer t, Process...synced) throws NullArgumentException {
		this.cooldown = t;
		for (Process p : synced) {
			if (p == null) {
				throw new NullArgumentException("You cannot sync null Processes.");
			}
			if (!ProcessCondition.DEAD.check(p)) {
				Processor.terminate(p);
			}
		}
		this.synced = synced;
	}
	public Synchronizer(Process...synced) throws NullArgumentException {
		this(null, synced);
	}
}
