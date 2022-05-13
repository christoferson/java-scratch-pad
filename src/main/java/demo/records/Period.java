package demo.records;

public record Period(int aLength) {

	// compact form of canonical constructor
//	public Period {
//		if (aLength < 0) {
//			throw new RuntimeException();
//		}
//	}
	
	public Period (int aLength) { // canonical constructor
		this.aLength = aLength + 1;
	}
	
	public Period() {
		this(0);
	}
}
