package demo.autocloseable;

public class DemoAutoCloseable implements AutoCloseable {

	private boolean throwOnOpen = false;
	private boolean throwOnClose = false;
	
	public DemoAutoCloseable(boolean throwOnOpen, boolean throwOnClose) {
		this.throwOnOpen = throwOnOpen;
		this.throwOnClose = throwOnClose;
	}

	public void open() {
		if (throwOnOpen) {
			throw new IllegalArgumentException("Error on Open!");
		}
		System.out.println("Open");	
	}
	
	@Override
	public void close() throws RuntimeException { //throws Exception { //Narrow Exception to RuntimeException
		if (throwOnClose) {
			throw new IllegalArgumentException("Error on Close!");
		}
		System.out.println("Closing");
	}

}
