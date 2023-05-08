package moonaframework.hallway;

import moonaframework.util.functional.Satellite;

public class Address implements Satellite<java.lang.foreign.MemoryAddress> {

	protected final long address;
	
	public long getRawAddress() {
		return this.address;
	}
	
	public @Override java.lang.foreign.MemoryAddress translate() {
		return java.lang.foreign.MemoryAddress.ofLong(this.address);
	}
	public @Override Address build(java.lang.foreign.MemoryAddress ma) {
		return new Address(ma.toRawLongValue());
	}
	
	public @Override String toString() {
		return Long.toHexString(this.address);
	}
	public @Override boolean equals(Object o) {
		return (o instanceof Address ma) ? this.address == ma.address : false;
	}
	
	public Address(long address) {
		this.address = address;
	}
}
