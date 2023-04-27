package moonaframework.hallway.dynamo;

import moonaframework.base.Moona;
import moonaframework.base.MoonaObject;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Method;

public final class NativeMethod implements MoonaObject {

	private final Method ref;
	
	public Method getSource() {
		return this.ref;
	}
	
	private String implementation;
	
	public long proof() {
		long inspCode = 0;
		byte[] impl = implementation.getBytes();
		for (byte b : impl) {
			inspCode += b;
		}
		return inspCode;
	}
	
	public String getImplementation() {
		return this.implementation;
	}
	public void compose(String code) throws UnsupportedOperationException, NullArgumentException {
		if (Moona.isOn()) {
			throw new UnsupportedOperationException("Unable to compose a NativeMethod after starting Moona.");
		}
		if (code == null) {
			throw new NullArgumentException("Unable to build a NativeMethod from a null code snippet.");
		}
		this.implementation = code;
	}
	
	public NativeMethod(Method ref) throws NullArgumentException, IllegalArgumentException, UnsupportedOperationException {
		if (ref == null) {
			throw new NullArgumentException("A link to a null Method Reference cannot be done.");
		}
		try {
			if (!ref.derive().getModifiers().isNative()) {
				throw new IllegalArgumentException("The given method must be native.");
			}
		}
		catch (UndefinedReflectionException ure) {
			ure.printStackTrace();
		}
		this.ref = ref;
		
	}
}
