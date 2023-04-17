package moonaframework.hallway;

public interface NativeObject {

	long nativeID();
	
	default long constructNative() {
		if (NativeLinker.linkedObject() != nativeID()) {
			NativeLinker.linkTo(nativeID());
		}
		return NativeLinker.construct();
	}
	
	default long constructNativeArray(int elements) {
		if (NativeLinker.linkedObject() != nativeID()) {
			NativeLinker.linkTo(this.nativeID());
		}
		return NativeLinker.constructArray(elements);
	}
	
	default void destroy(long at) {
		if (NativeLinker.linkedObject() != nativeID()) {
			NativeLinker.linkTo(nativeID());
		}
		NativeLinker.destroy(at);
	}
	
	default void destroyArray(long at) {
		if (NativeLinker.linkedObject() != nativeID()) {
			NativeLinker.linkTo(nativeID());
		}
		NativeLinker.destroyArray(at);
	}
}
