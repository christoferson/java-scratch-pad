package demo.exceptions;

public class Jep358 {

	record A(String s) {}
	record B(A a) {}
	record C(B b) {}
	
	  public static void demo() {
	    C[] ca = {
	      new C(new B(new A(null))),
	      new C(new B(null)),
	      new C(null),
	    };
	    for(C c: ca) {
	      try {
	        System.out.println(c.b().a().s());
	      } catch(NullPointerException npe) {
	        System.out.println(npe);
	      }
	    }
	  }
	
}
