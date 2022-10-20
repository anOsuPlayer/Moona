package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.relation.Synced;
import moonaFramework.time.Timer;

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
			p.onPause();
			if (cooldown != null) {
				cooldown.elapse();
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
	
	public Synchronizer(Timer t, Process...synced) {
		this.cooldown = t;
		this.synced = synced;
		for (Process p : synced) {
			if (!ProcessCondition.DEAD.check(p)) {
				Moona.terminate(p);
			}
		}
	}
	public Synchronizer(Process...synced) {
		this(null, synced);
	}
}
