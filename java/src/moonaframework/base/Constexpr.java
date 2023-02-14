package moonaframework.base;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.functional.Snippet;

public final class Constexpr implements MoonaObject {
	
	final Snippet code;
	
	public static void evaluate(Snippet code) throws NullArgumentException {
		new Constexpr(code);
	}
	
	private Constexpr(Snippet code) throws NullArgumentException {
		if (code == null) {
			throw new NullArgumentException("A null Constexpr cannot be initialized.");
		}
		this.code = code;
		Moona.add(this);
	}
}
