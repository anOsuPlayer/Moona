package moonaFramework.time;

public class Timer extends Clock {

	private long mills;
	private int nanos;
	
	public long getMills() {
		return mills;
	}
	public void setMills(long mills) {
		this.mills = mills;
	}
	
	public int getNanos() {
		return nanos;
	}
	public void setNanos(int nanos) {
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
	
	public Timer(long mills, int nanos) {
		this.mills = mills + (nanos / 1000000);
		this.nanos = nanos % 1000000;
	}
	public Timer(long mills) {
		this(mills, 0);
	}
	public Timer(int nanos) {
		this(nanos / 1000000, nanos % 1000000);
	}
	public Timer(double seconds) {
		this((long) seconds, ((int) ((seconds - ((int) seconds)) * 1000000)));
	}
	public Timer() {
		this(0, 0);
	}
}
