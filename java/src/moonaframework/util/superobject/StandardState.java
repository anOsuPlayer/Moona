package moonaframework.util.superobject;

public non-sealed interface StandardState<O> extends ReadonlyState<O> {

	@Override O get();
	void set(O value);
	
	default StandardState<O> change(O value) {
		set(value);
		return this;
	}
}
