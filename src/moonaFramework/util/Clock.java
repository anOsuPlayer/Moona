package moonaFramework.util;

import moonaFramework.process.Process;

public class Clock implements Attached<Process> {

	private final long beginning;
	public long getBeginning() {
		return beginning;
	}
	
	private Process host;
	public Process getHost() {
		return host;
	}
	
	public void sleep(long mills, int nanos) {
		synchronized (this) {
			this.notify();
			try {
				this.wait(mills, nanos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void sleep(long mills) {
		sleep(mills);
	}
	
	public void pauseHolder() {
		if (host.isPaused().verify()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	public void stasys() {
		synchronized (this) {
			this.notify();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release() {
		synchronized (this) {
			this.notify();
		}
	}
	
	public Clock(Process host) {
		this.beginning = System.nanoTime();
		this.host = host;
	}
}
