package moonaframework.hallway;

abstract class NativeLinker {

	private static long linkedObject;
	
	static long linkedObject() {
		return NativeLinker.linkedObject;
	}
	static void linkTo(long objCode) {
		linkedObject = objCode;
	}
	
	private static native long constructNative(long objCode);
	
	static long construct() {
		return NativeLinker.constructNative(linkedObject);
	}
	
	private static native long constructNativeArray(long objCode, int quantity);
	
	static long constructArray(int elements) {
		return NativeLinker.constructNativeArray(linkedObject, elements);
	}
	
	private static native void destroyNative(long objCode, long address);
	
	static void destroy(long address) {
		NativeLinker.destroyNative(linkedObject, address);
	}
	
	private static native void destroyNativeArray(long objCode, long address);
	
	static void destroyArray(long address) {
		NativeLinker.destroyNativeArray(linkedObject, address);
	}
	
	private NativeLinker() {
		
	}
}
