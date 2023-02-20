package moonaframework.util.superobject;

public interface ConditionalState<O> {
	
	OperativeState<O> and();
	
	OperativeState<O> or();
	
	ConclusiveState<O> then();
}
