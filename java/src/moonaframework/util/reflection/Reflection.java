package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.base.Setting;
import moonaframework.util.exception.UndefinedReflectionException;

public abstract class Reflection<T> implements Serial {

	private final long id;
	
	public @Override final long id() {
		return this.id;
	}
	public @Override final Nature nature() {
		return Nature.REFLECTION;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Reflection<?> refl) ?
				this.value.equals(refl.value) && this.getTarget().equals(refl.getTarget())
				: false;
	}
	
	protected T value;
	
	public abstract Object getTarget();
	
	public abstract void reflect() throws UndefinedReflectionException;
	
	public T evaluate() throws UndefinedReflectionException {
		if (value == null) {
			reflect();
		}
		return value;
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
		this.id = Moona.generateID();
	}
}
