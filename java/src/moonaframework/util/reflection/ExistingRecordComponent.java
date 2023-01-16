package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class ExistingRecordComponent extends RecordComponent {

	private boolean trusted = false;
	
	public @Override final java.lang.reflect.RecordComponent evaluate() throws UndefinedReflectionException {
		if (trusted) {
			return super.evaluate();
		}
		if (!getTarget().isRecord()) {
			throw new UndefinedReflectionException("Cannot build a RecordComponent Reference over a Type"
					+ " Reference which points to a non-record type.");
		}
		
		var previous = super.value;
		reflect();
		if (!super.value.equals(previous)) {
			throw new UndefinedReflectionException("The provided name does not either match the given"
					+ " java.lang.reflect.RecordComponent or cannot be evaluated from the given Reference.");
		}
		return super.value;
	}
	
	public ExistingRecordComponent(Class<? extends Record> clazz, String name, java.lang.reflect.RecordComponent rc) throws NullArgumentException, IllegalArgumentException {
		super(clazz, name);
		if (rc == null) {
			throw new NullArgumentException("A null java.lang.reflect.RecordComponent cannot be accepted.");
		}
		super.value = rc;
		
		if (Reflection.strictContext.evaluate()) {
			trusted = true;
		}
	}
	public ExistingRecordComponent(Class<? extends Record> clazz, int index, java.lang.reflect.RecordComponent rc) throws NullArgumentException, IllegalArgumentException {
		super(clazz, index);
		if (rc == null) {
			throw new NullArgumentException("A null java.lang.reflect.RecordComponent cannot be accepted.");
		}
		super.value = rc;
		
		if (Reflection.strictContext.evaluate()) {
			trusted = true;
		}
		
		super.mirrorInteraction();
	}
}
