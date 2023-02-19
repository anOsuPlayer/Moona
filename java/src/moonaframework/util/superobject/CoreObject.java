package moonaframework.util.superobject;

import java.util.Optional;

class CoreObject<O> implements SuperObject<O> {

	private O value;
	
	CoreObject() {
		this.value = null;
	}
	CoreObject(O object) {
		this.value = object;
	}
}
