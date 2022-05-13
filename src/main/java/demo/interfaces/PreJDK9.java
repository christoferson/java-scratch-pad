package demo.interfaces;

public interface PreJDK9 {

	default void fd() {
		System.out.println("PreJDK9::fd()");
	}

	static void fs() {
		System.out.println("PreJDK9::fs()");
	}

	default void f() {
		fd();
	}

	static void g() {
		fs();
	}

}
