package moonaFramework.time;

public class Timer extends Clock {

	private long mills;
	private int nanos;
	
	public long getMills() {
		return mills;
	}
	public void setMills(long mills) throws IllegalArgumentException {
		if (mills < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		this.mills = mills;
	}
	
	public int getNanos() {
		return nanos;
	}
	public void setNanos(int nanos) throws IllegalArgumentException {
		if (nanos < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		this.mills += nanos / 1000000;
		this.nanos = nanos % 1000000;
	}
	
	public void elapse() {
		sleep(mills, nanos);
	}
	public void consume() {
		elapse();
		setMills(0); setNanos(0);
	}
	
	public Timer(long mills, int nanos) throws IllegalArgumentException {
		if (mills < 0 || nanos < 0) {
			throw new IllegalArgumentException("Time values cannot be negative.");
		}
		this.mills = mills + (nanos / 1000000);
		this.nanos = nanos % 1000000;
	}
	public Timer(long mills) throws IllegalArgumentException {
		this(mills, 0);
	}
	public Timer(int micros) throws IllegalArgumentException {
		this(micros / 1000, ((micros * 1000) % 1000000));
	}
	public Timer(double seconds) throws IllegalArgumentException {
		this((long) seconds * 1000 + ((int) ((seconds - ((int) seconds)) * 1000)),
				(int) (((seconds - ((int) seconds)) * 1000000000)) % 1000000);
	}
	public Timer() {
		this(0, 0);
	}
}
