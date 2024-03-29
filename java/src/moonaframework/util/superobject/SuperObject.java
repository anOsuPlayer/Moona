package moonaframework.util.superobject;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import moonaframework.util.condition.Conditional;

public interface SuperObject<O> extends OperativeState<O>, StandardState<O> {
	
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
	
	static <O> SuperObject<O> of() {
		return new BaseObject<>();
	}
	static <O> SuperObject<O> of(O value) {
		return new BaseObject<>(value);
	}
	static <O> SuperObject<O> of(Optional<O> opt) {
		try {
			return new BaseObject<>(opt.get());
		}
		catch (NoSuchElementException e) {
			return new BaseObject<>();
		}
	}
}
