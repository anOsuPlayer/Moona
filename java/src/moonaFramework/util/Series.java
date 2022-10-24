package moonaFramework.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Series<T> implements Iterable<T> {
	
	@Override
	public String toString() {
		if (!series.isEmpty()) {
			String out = "[ ";
			for (T t : series) {
				out += String.valueOf(t) + ", ";
			}
			return (out.substring(0, out.length()-2) + " ]");
		}
		return "Series not generated yet!";
	}
	
	private final List<T> series;
	
	private T from;
	
	private long iterations;
	
	private int offset;
	
	public abstract T loop(T n);
	
	public final void generate() {
		series.add(from);
		for (int i = offset; i < iterations; ++i) {
		   	this.series.add(this.loop(this.series.get(i - offset)));
		}
	}

	@Override
	public Iterator<T> iterator() {
	   return this.series.iterator();
	}
	

	public T get(int index) throws IndexOutOfBoundsException {
		return this.series.get(index);
	}
	public List<T> asList() {
		return this.series;
	}
	
	public Series(T from, long until, int offset) throws NullPointerException, IllegalArgumentException {
		if (from == null) {
			throw new NullPointerException("A Series cannot be initialized starting from a null number.");
		}
		if (offset < 0) {
			throw new IllegalArgumentException("The offset cannot be negative.");
		}
		if (offset > until) {
			throw new IllegalArgumentException("The offset cannot be greater than the Series's length.");
		}
		offset += offset == 0 ? 1 : 0;
		this.series = new ArrayList<>();
		this.from = from; this.iterations = until;
		this.offset = offset;
	}
	public Series(T from, long until) throws NullPointerException {
		this(from, until, 0);
	}
}
