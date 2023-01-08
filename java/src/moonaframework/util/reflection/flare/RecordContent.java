package moonaframework.util.reflection.flare;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Record;

public final class RecordContent extends TypeContent {

	public @Override boolean equals(Object o) {
		return (o instanceof RecordContent rc) ? this.getTarget().equals(rc.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Flare" : "RecordContent of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		super.reflect();
		
		
	}
	
	public RecordContent(Record source) throws NullArgumentException {
		super(source);
	}
	public RecordContent(Class<? extends java.lang.Record> source) throws NullArgumentException {
		super(source);
	}
}
