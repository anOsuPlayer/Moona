package moonaframework.util.superobject;

import java.util.function.Predicate;

import moonaframework.util.condition.Conditional;

public interface SuperObject<O> extends OperativeState<O>, StandardState<O> {
	
	@Override O get();
	@Override void set(O value);

	@Override ConditionalState<O> ifPresent();
	@Override ConditionalState<O> ifEmpty();
	
	@Override ConditionalState<O> ifEquals(SuperObject<O> so);
	@Override ConditionalState<O> ifEquals(O obj);
	
	@Override ConditionalState<O> ifDifferent(SuperObject<O> so);
	@Override ConditionalState<O> ifDifferent(O obj);
	
	default @Override ConditionalState<O> ifTrue(Predicate<O> condition) {
		return ifTrue(condition.test(get()));
	}
	default @Override ConditionalState<O> ifFalse(Predicate<O> condition) {
		return ifFalse(condition.test(get()));
	}
	
	default @Override ConditionalState<O> ifTrue(Conditional condition) {
		return ifTrue(condition.verify());
	}
	default @Override ConditionalState<O> ifFalse(Conditional condition) {
		return ifFalse(condition.verify());
	}
	
	@Override ConditionalState<O> ifTrue(boolean condition);
	@Override ConditionalState<O> ifFalse(boolean condition);
	
	static <O> SuperObject<O> of() {
		return new BaseObject<>();
	}
	static <O> SuperObject<O> of(O value) {
		return new BaseObject<>(value);
	}
}
