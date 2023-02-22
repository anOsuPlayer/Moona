package moonaframework.util.superobject;

class CoreObject<O> implements SuperObject<O>, ConditionalState<O>, ConclusiveState<O>, AdversativeState<O> {

	private O value;
	
	public @Override O get() {
		return this.value;
	}
	public @Override void set(O value) {
		this.value = value;
	}
	
	private boolean currentCondition;
	
	private boolean pastCondition = false;
	
	public @Override boolean verify() {
		return this.currentCondition;
	}
	
	private static final int NONE = 0xffffffff;
	
	private static final int AND = 0x0;
	
	private static final int OR = 0x1;
	
	private int operator = NONE;
	
	void reworkCondition(boolean next) {
		switch (operator) {
			case AND: {
				this.currentCondition &= (next && !pastCondition);
				break;
			}
			case OR: {
				this.currentCondition |= (next && !pastCondition);
				break;
			}
			case NONE: {
				this.currentCondition = (next && !pastCondition);
				break;
			}
		}
	}
	
	public @Override ConditionalState<O> ifPresent() {
		reworkCondition(value != null);
		return this;
	}
	public @Override ConditionalState<O> ifEmpty() {
		reworkCondition(value == null);
		return this;
	}
	
	public @Override ConditionalState<O> ifEquals(SuperObject<O> so) {
		reworkCondition(value.equals(so.get()));
		return this;
	}
	public @Override ConditionalState<O> ifEquals(O obj) {
		reworkCondition(value.equals(obj));
		return this;
	}
	public @Override ConditionalState<O> ifDifferent(SuperObject<O> so) {
		reworkCondition(!value.equals(so.get()));
		return this;
	}
	public @Override ConditionalState<O> ifDifferent(O obj) {
		reworkCondition(!value.equals(obj));
		return this;
	}
	
	public @Override ConditionalState<O> ifTrue(boolean condition) {
		reworkCondition(condition);
		return this;
	}
	public @Override ConditionalState<O> ifFalse(boolean condition) {
		reworkCondition(!condition);
		return this;
	}
	
	public @Override OperativeState<O> and() {
		this.operator = AND;
		return this;
	}
	public @Override OperativeState<O> or() {
		this.operator = OR;
		return this;
	}
	
	public @Override ConclusiveState<O> then() {
		this.operator = NONE;
		return this;
	}
	public @Override ConclusiveState<O> andThen() {
		return this;
	}
	
	public @Override AdversativeState<O> run(Runnable r) {
		if (currentCondition) {
			r.run();
		}
		return this;
	}
	public @Override <T extends Throwable> AdversativeState<O> throwException(T throwable) throws T {
		if (currentCondition) {
			throw throwable;
		}
		return this;
	}
	
	public @Override OperativeState<O> orElse() {
		this.pastCondition |= currentCondition;
		return this;
	}
	
	public @Override ContinuativeState<O> orElseRun(Runnable r) {
		if (!currentCondition) {
			r.run();
		}
		return this;
	}
	public @Override <T extends Throwable> ContinuativeState<O> orElseThrow(T throwable) throws T {
		if (!currentCondition) {
			throw throwable;
		}
		return this;
	}
	
	public @Override String toString() {
		return this.value.toString();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof ReadonlyState<?> ros) ? ros.get().equals(this.value) : false;
	}
	
	public @Override SuperObject<O> clone() {
		return SuperObject.of(this.value);
	}
	
	CoreObject() {
		this.value = null;
	}
	CoreObject(O value) {
		this.value = value;
	}
}
