package moonaframework.util.superobject;

class BaseObject<O> implements SuperObject<O>, StandardState<O> {

	private CoreObject<O> value;
	
	public @Override O get() {
		return this.value.get();
	}
	public @Override void set(O value) {
		this.value.set(value);
	}
	
	public @Override ConditionalState<O> ifPresent() {
		value.reworkCondition(value.get() != null);
		return value;
	}
	public @Override ConditionalState<O> ifEmpty() {
		value.reworkCondition(value.get() == null);
		return value;
	}
	
	public @Override ConditionalState<O> ifEquals(SuperObject<O> so) {
		value.reworkCondition(value.get().equals(so.get()));
		return value;
	}
	public @Override ConditionalState<O> ifEquals(O obj) {
		value.reworkCondition(value.get().equals(obj));
		return value;
	}
	public @Override ConditionalState<O> ifDifferent(SuperObject<O> so) {
		value.reworkCondition(!value.get().equals(so.get()));
		return value;
	}
	public @Override ConditionalState<O> ifDifferent(O obj) {
		value.reworkCondition(!value.get().equals(obj));
		return value;
	}
	
	public @Override ConditionalState<O> ifTrue(boolean condition) {
		value.reworkCondition(condition);
		return value;
	}
	public @Override ConditionalState<O> ifFalse(boolean condition) {
		value.reworkCondition(!condition);
		return value;
	}
	
	public @Override String toString() {
		return this.value.toString();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof ReadonlyState<?> ros) ? ros.get().equals(this.value.get()) : false;
	}
	
	BaseObject() {
		this.value = new CoreObject<>();
	}
	BaseObject(O value) {
		this.value = new CoreObject<>(value);
	}
}
