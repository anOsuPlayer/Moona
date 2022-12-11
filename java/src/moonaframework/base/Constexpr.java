package moonaframework.base;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.functional.Snippet;

public final class Constexpr implements Serial {

	private final long id;
	public @Override final long id() {
		return this.id;
	}
	public @Override final Nature nature() {
		return Nature.OBJECT;
	}
	
	final Snippet instructions;
	
	{
		Moona.add(this);
	}
	
	public static void evaluate(Snippet code) throws NullArgumentException {
		new Constexpr(code);
	}
	
	private Constexpr(Snippet code) throws NullArgumentException {
		if (code == null) {
			throw new NullArgumentException("A null Constexpr cannot be initialized.");
		}
		this.instructions = code;
		this.id = Moona.generateID();
	}
}
