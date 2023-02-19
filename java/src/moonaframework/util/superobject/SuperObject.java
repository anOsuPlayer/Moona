package moonaframework.util.superobject;

public interface SuperObject<O> {
	
	

	static <O> SuperObject<O> of() {
		return new CoreObject<O>();
	}
	static <O> SuperObject<O> of(O object) {
		return new CoreObject<O>();
	}
}
