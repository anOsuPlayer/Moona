package moonaframework.util.reflection;

public final class Record extends Type {

	public @Override boolean equals(Object o) {
		return (o instanceof Record r) ?
				super.value.equals(r.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "Record " + value.getName();
	}
	
	public Record(Class<? extends java.lang.Record> clazz) throws NullPointerException {
		super(clazz);
	}
}
