package moonaframework.util.superobject;

public interface AdversativeState<O> extends ContinuativeState<O> {

	SuperObject<O> orElse();
	
	ContinuativeState<O> orElseRun(Runnable r);
	
	<T extends Throwable> ContinuativeState<O> orElseThrow(T throwable) throws T;
}
