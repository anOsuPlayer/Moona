package moonaframework.base;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.functional.Snippet;

public class Endexpr implements MoonaObject {

	final Snippet code;
	
	public static void evaluate(Snippet code) throws NullArgumentException {
		new Endexpr(code);
	}
	
	private Endexpr(Snippet code) throws NullArgumentException {
		if (code == null) {
			throw new NullArgumentException("A null Endexpr cannot be initialized.");
		}
		this.code = code;
		Moona.add(this);
	}
}
