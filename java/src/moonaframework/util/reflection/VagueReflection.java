package moonaframework.util.reflection;

import moonaframework.base.Moona;
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
	
	protected @Override void mirrorInteraction() {
		if (!Moona.isOn()) {
			if (Moona.autoReflections.evaluate()) {
				Mirror.dequeue(refl);
				Mirror.queue(this);
			}
			
			if (Moona.autoDeriveReferences.evaluate() || (strictContext.evaluate() && Moona.autoDeriveReferences.evaluate())) {
				if (this instanceof Derivable d) {
					d.derive();
					d.getAnnotated();
				}
			}
		}
		else {
			if (Moona.autoReflections.evaluate()) {
				Mirror.removeReflection(refl);
				Mirror.addReflection(this);
			}
			
			if (Moona.deriveWhenInitialized.evaluate() || (strictContext.evaluate() && Moona.deriveWhenInitialized.evaluate())) {
				if (this instanceof Derivable d) {
					d.derive();
					d.getAnnotated();
				}
			}
		}
	}
	
	public VagueReflection(Reflection<R> refl) throws NullArgumentException {
		super();
		if (refl == null) {
			throw new NullArgumentException("Cannot build a VagueReflection over a null Reflection.");
		}
		this.refl = refl;
	}
}
