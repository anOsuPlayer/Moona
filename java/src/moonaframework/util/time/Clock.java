package moonaframework.util.time;

public class Clock {

	public void sleep(long mills, int nanos) throws IllegalArgumentException {
		if (mills < 0 || nanos < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		try {
			Thread.sleep(mills, nanos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sleep(long mills) throws IllegalArgumentException {
		if (mills < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep(mills, 0);
	}
	
	public void sleep(double seconds) throws IllegalArgumentException {
		if (seconds < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		sleep((long) seconds * 1000 + ((int) ((seconds - ((int) seconds)) * 1000)),
				((int) (((seconds - ((int) seconds)) * 1000000000)) % 1000000));
	}
	
	public Clock() {
		
	}
}
