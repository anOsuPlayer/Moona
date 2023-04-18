package moonaframework.hallway;

public final class NativeLinker {

	private static long linkId;
	
	public static void linkObject(long linkId) {
		NativeLinker.linkId = linkId;
	}
	
	private static native long constructNative(long linkId);
	
	private static native long constructNativeArray(long linkId, int size);
	
	public static NativeAllocation construct() {
		return new NativeAllocation(constructNative(linkId));
	}
	
	public static NativeAllocation constructArray(int size) throws NegativeArraySizeException {
		if (size < 0) {
			throw new NegativeArraySizeException("Unable to allocate a negative size array.");
		}
		return new NativeArrayAllocation(constructNativeArray(linkId, size));
	}
	
	private static native void destroyNative(long address);
	
	private static native void destroyNativeArray(long address);
	
	public static void destroy(NativeAllocation address) throws IllegalArgumentException {
		if (address instanceof NativeArrayAllocation) {
			throw new IllegalArgumentException("A NativeArrayAllocation cannot be deleted like a regular NativeAllocation,"
					+ " use NativeLinker.destroyArray() instead.");
		}
		destroyNative(address.getRawAddress());
	}
	
	public static void destroyArray(NativeArrayAllocation address) {
		destroyNativeArray(address.getRawAddress());
	}
	
	private NativeLinker() {
		
	}
}
