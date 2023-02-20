package moonaframework.util.superobject;

import java.util.function.Predicate;

import moonaframework.util.condition.Conditional;

public interface SuperObject<O> extends StandardState<O> {
	
	@Override O get();
	@Override void set(O value);

	ConditionalState<O> ifPresent();
	ConditionalState<O> ifEmpty();
	
	ConditionalState<O> ifEquals(SuperObject<O> so);
	ConditionalState<O> ifEquals(O obj);
	
	ConditionalState<O> ifDifferent(SuperObject<O> so);
	ConditionalState<O> ifDifferent(O obj);
	
	default ConditionalState<O> ifTrue(Predicate<O> condition) {
		return ifTrue(condition.test(get()));
	}
	default ConditionalState<O> ifFalse(Predicate<O> condition) {
		return ifFalse(condition.test(get()));
	}
	
	default ConditionalState<O> ifTrue(Conditional condition) {
		return ifTrue(condition.verify());
	}
	default ConditionalState<O> ifFalse(Conditional condition) {
		return ifFalse(condition.verify());
	}
	
	ConditionalState<O> ifTrue(boolean condition);
	ConditionalState<O> ifFalse(boolean condition);
	
	static <O> SuperObject<O> of() {
		return new BaseObject<>();
	}
	static <O> SuperObject<O> of(O value) {
		return new BaseObject<>(value);
	}
}
