package moonaframework.util.reflection;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.base.Setting;

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
	
	public abstract void reflect();
	
	public T evaluate() {
		if (value == null) {
			reflect();
		}
		return value;
	}
	
	protected static final Setting strictContext = new Setting(false);
	
	protected Reflection() {
		this.id = Moona.generateID();
		if (Moona.autoReflections.evaluate() || strictContext.evaluate()) {
			Mirror.queue(this);
		}
	}
}
