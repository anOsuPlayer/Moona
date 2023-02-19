package moonaframework.util.superobject;

public interface SuperObject<O> {
	
	O get();
	void set(O value);
	
	default SuperObject<O> setAndGet(O value) {
		set(value);
		return this;
	}

	static <O> CoreObject<O> of() {
		return new CoreObject<>();
	}
	static <O> CoreObject<O> of(O value) {
		return new CoreObject<>(value);
	}
}
