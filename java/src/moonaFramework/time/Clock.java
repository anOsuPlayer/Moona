package moonaFramework.time;

public class Clock {

	public void sleep(long mills, int nanos) {
		try {
			Thread.sleep(mills, nanos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sleep(long mills) {
		sleep(mills, 0);
	}
	public void sleep(int nanos) {
		long mills = nanos / 1000000;
		sleep(mills, nanos % 1000000);
	}
	
	public void sleep(double seconds) {
		sleep((long) seconds, ((int) ((seconds - ((int) seconds)) * 1000000)));
	}
	
	public Clock() {
		
	}
}
