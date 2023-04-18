package moonaframework.hallway;

public sealed class NativeAllocation extends MemoryAddress permits NativeArrayAllocation {

	public NativeAllocation(long address) {
		super(address);
	}
}
