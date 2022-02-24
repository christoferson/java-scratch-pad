package demo.exceptions;

public class DemoFeatureExceptions {

	private static class MyResource implements AutoCloseable {

		public void execute() {
			System.out.println("Execute");	
		}
		@Override
		public void close() throws Exception {
			System.out.println("Closing");
		}
		
	}
	
	private static class MyResourceNoException implements AutoCloseable {

		public void execute() {
			System.out.println("Execute");	
		}
		@Override
		public void close() {
			System.out.println("Closing");
		}
		
	}
	
	private static class MyResourceWithRuntimeException implements AutoCloseable {

		public void execute() {
			System.out.println("Execute");	
		}
		@Override
		public void close() throws RuntimeException {
			System.out.println("Closing");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		demoTryWithResources1();
		demoTryWithResources2();
		demoTryWithResources3();
		demoTryWithResources4();

	}
	
	private static void demoTryWithResources1() throws Exception {
		
		MyResource resource = new MyResource();
		
		try (resource) {
			resource.execute();
		} finally {
			System.out.println("Finally");
		}
		
	}
	
	private static void demoTryWithResources2() {
		
		MyResource resource = new MyResource();
		
		try (resource) {
			resource.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		
	}
	
	private static void demoTryWithResources3() {
		
		MyResourceNoException resource = new MyResourceNoException();
		
		try (resource) {
			resource.execute();
		} finally {
			System.out.println("Finally");
		}
		
	}
	
	private static void demoTryWithResources4() {
		
		MyResourceWithRuntimeException resource = new MyResourceWithRuntimeException();
		
		try (resource) {
			resource.execute();
		} finally {
			System.out.println("Finally");
		}
		
	}
	

}
