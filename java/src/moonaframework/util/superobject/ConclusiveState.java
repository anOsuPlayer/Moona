package moonaframework.util.superobject;

public non-sealed interface ConclusiveState<O> extends ReadonlyState<O> {

	@Override O get();
	
	boolean verify();
	
	AdversativeState<O> run(Runnable r);
	
	<T extends Throwable> AdversativeState<O> throwException(T throwable) throws T;
}
