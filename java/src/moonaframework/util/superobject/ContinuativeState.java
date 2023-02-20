package moonaframework.util.superobject;

public interface ContinuativeState<O> {

	ConclusiveState<O> andThen();
}
