package demo.interfaces;

public interface PostJDK9 {

	private void fd() { // Automatically default
		System.out.println("JDK9::fd()");
	}

	private static void fs() {
		System.out.println("JDK9::fs()");
	}

	default void f() {
		fd();
	}

	static void g() {
		fs();
	}

}
