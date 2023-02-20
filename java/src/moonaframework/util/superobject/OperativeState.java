package moonaframework.util.superobject;

import java.util.function.Predicate;

import moonaframework.util.condition.Conditional;

public interface OperativeState<O> {

	ConditionalState<O> ifPresent();
	ConditionalState<O> ifEmpty();
	
	ConditionalState<O> ifEquals(SuperObject<O> so);
	ConditionalState<O> ifEquals(O obj);
	
	ConditionalState<O> ifDifferent(SuperObject<O> so);
	ConditionalState<O> ifDifferent(O obj);
	
	ConditionalState<O> ifTrue(Predicate<O> condition);
	ConditionalState<O> ifFalse(Predicate<O> condition);
	
	default ConditionalState<O> ifTrue(Conditional condition) {
		return ifTrue(condition.verify());
	}
	default ConditionalState<O> ifFalse(Conditional condition) {
		return ifFalse(condition.verify());
	}
	
	ConditionalState<O> ifTrue(boolean condition);
	ConditionalState<O> ifFalse(boolean condition);
}
