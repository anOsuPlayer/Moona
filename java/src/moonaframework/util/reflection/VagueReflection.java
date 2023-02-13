package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public class VagueReflection<R> extends Reflection<R> {
	
	private final Reflection<R> refl;
	
	public @Override final Object getTarget() {
		return this.refl.getTarget();
	}
	public final Reflection<R> getBaseReflection() {
		return this.refl;
	}
	
	public @Override final void reflect() {
		try {
			refl.reflect();
		}
		catch (UndefinedReflectionException ure) { }
	}
	public final void safeReflect() throws UndefinedReflectionException {
		refl.reflect();
	}
	
	public @Override final R evaluate() {
		try {
			return refl.evaluate();
		}
		catch (UndefinedReflectionException ure) {
			return null;
		}
	}
	public final R safeEvaluate() throws UndefinedReflectionException {
		return refl.evaluate();
	}
	
	public VagueReflection(Reflection<R> refl) throws NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("Cannot build a VagueReflection over a null Reflection.");
		}
		this.refl = refl;
	}
}
