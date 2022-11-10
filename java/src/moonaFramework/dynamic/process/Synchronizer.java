package moonaFramework.dynamic.process;

import moonaFramework.dynamic.ProcessCondition;
import moonaFramework.dynamic.Processor;
import moonaFramework.util.relation.Synced;
import moonaFramework.util.time.Timer;

public class Synchronizer extends Task implements Synced<Process> {

	private final Process[] synced;
	@Override
	public final Process[] getSynced() {
		return synced;
	}
	
	private Timer cooldown;
	public Timer getTimer() {
		return cooldown;
	}
	public void setTimer(Timer t) {
		this.cooldown = t;
	}
	
	@Override
	public void onPause() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.onPause();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	@Override
	public void onUnpause() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.onUnpause();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	@Override
	public void initialize() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.initialize();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	@Override
	public void update() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.update();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	@Override
	public void end() {
		for (Process p : synced) {
			synchronized (getClock()) {
				p.end();
				if (cooldown != null) {
					cooldown.elapse();
				}
			}
		}
	}
	
	public Synchronizer(Timer t, Process...synced) throws NullPointerException {
		this.cooldown = t;
		for (Process p : synced) {
			if (p == null) {
				throw new NullPointerException("You cannot sync null Processes.");
			}
			if (!ProcessCondition.DEAD.check(p)) {
				Processor.terminate(p);
			}
		}
		this.synced = synced;
	}
	public Synchronizer(Process...synced) throws NullPointerException {
		this(null, synced);
	}
}
