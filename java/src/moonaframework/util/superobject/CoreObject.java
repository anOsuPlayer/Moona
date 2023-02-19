package moonaframework.util.superobject;

class CoreObject<O> implements SuperObject<O> {

	private O value;
	
	public @Override O get() {
		return this.value;
	}
	public @Override void set(O value) {
		this.value = value;
	}
	
	CoreObject() {
		this.value = null;
	}
	CoreObject(O value) {
		this.value = value;
	}
}
