package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.MoonaObject;
import moonaframework.base.Setting;
import moonaframework.util.exception.UndefinedReflectionException;

public abstract class Reflection<R> implements MoonaObject {
	
	public @Override boolean equals(Object o) {
		return (o instanceof Reflection<?> refl) ?
				this.value.equals(refl.value) && this.getTarget().equals(refl.getTarget())
				: false;
	}
	
	protected R value;
	
	public abstract Object getTarget();
	
	public abstract void reflect() throws UndefinedReflectionException;
	
	public final void safeReflect() {
		try {
			reflect();
		}
		catch (UndefinedReflectionException ure) {
			
		}
	}
	
	public R evaluate() throws UndefinedReflectionException {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	public final R safeEval() {
		try {
			return evaluate();
		}
		catch (UndefinedReflectionException ure) {
			return null;
		}
	}
	
	public final boolean isUndefined() {
		try {
			evaluate();
			return false;
		}
		catch (UndefinedReflectionException ure) {
			return true;
		}
	}
	
	protected static transient final Setting strictContext = new Setting(false);
	
	protected void valueExtraction() {
		Mirror.askMirror(this);
	}
	
	protected void mirrorInteraction() {
		if (!Moona.isOn()) {
			if (Moona.autoReflections.evaluate()) {
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
	
	protected Reflection() {
		
	}
}
