package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.MoonaObject;
import moonaframework.util.Setting;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.relation.Targetting;

public abstract class Reflection<R> implements MoonaObject, Targetting<Object> {
	
	public @Override boolean equals(Object o) {
		return (o instanceof Reflection<?> refl) ?
				this.value.equals(refl.value) && this.getTarget().equals(refl.getTarget())
				: false;
	}
	
	protected R value;
	
	public @Override abstract Object getTarget();
	
	public abstract void reflect() throws UndefinedReflectionException;
	
	public final boolean safeReflect() {
		try {
			reflect();
			return true;
		}
		catch (UndefinedReflectionException ure) {
			return false;
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
		return safeEval().equals(null);
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
