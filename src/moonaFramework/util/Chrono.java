package moonaFramework.util;

public class Chrono {

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
	
	
}
