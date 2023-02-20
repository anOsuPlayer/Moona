package moonaframework.util.superobject;

public interface ConditionalState<O> {
	
	SuperObject<O> and();
	
	SuperObject<O> or();
	
	ConclusiveState<O> then();
}
