package moonaframework.hallway;

import moonaframework.util.functional.Satellite;

public class MemoryAddress implements Satellite<java.lang.foreign.MemoryAddress> {

	protected final long address;
	
	public long rawAddress() {
		return this.address;
	}
	
	public @Override java.lang.foreign.MemoryAddress translate() {
		return java.lang.foreign.MemoryAddress.ofLong(this.address);
	}
	
	public @Override MemoryAddress build(java.lang.foreign.MemoryAddress ma) {
		return new MemoryAddress(ma.toRawLongValue());
	}
	
	public MemoryAddress(long address) {
		this.address = address;
	}
}
