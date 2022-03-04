package demo.autocloseable;

public class DemoAutoCloseable implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("Closing");
	}

}
