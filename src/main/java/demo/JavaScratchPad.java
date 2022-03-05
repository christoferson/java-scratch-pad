package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import demo.autocloseable.DemoAutoCloseable;

public class JavaScratchPad {

	public static void main(String[] args) {
		jm20211201OperatorPrecedence();
		jeps101TypeInference();
		try {
		tryAutoCloseable();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	private static void jm20211201OperatorPrecedence() {
		var res = 54/5*5-4.0+4*8/10;
		System.out.println(res);
	}
	
	private static void jeps101TypeInference() {
		
		class MyList {
			static <Z> List<Z> nil() {
				return new ArrayList<Z>();
			}
			
			static List<Number> process(List<String> list) {
				List<Number> nlist = new ArrayList<>();
				for (String e : list) {
					nlist.add(new BigDecimal(e).add(BigDecimal.valueOf(2)));
				}
				return nlist;
			}
			
		}
		
		List<String> list = MyList.nil(); // Infer type String
		list.add("35");
		//list.add(24); // Error: The method add(int, String) in the type List<String> is not applicable for the arguments (int)
		System.out.printf("MyList.nil() : %s %n", list);
		System.out.printf("MyList.process(list) : %s %n", MyList.process(list)); // Infer type String
		
	}
	
	private static void tryAutoCloseable() {
		
		System.out.println("Throw on Open = false, Throw on Close = false");
		try (DemoAutoCloseable resource = new DemoAutoCloseable(false, false)) {
			resource.open();
		} catch (RuntimeException e) {
			print(e);
		} finally {
			System.out.println("Finally");
		}
		
		System.out.println("Throw on Open = true, Throw on Close = false");
		try (DemoAutoCloseable resource = new DemoAutoCloseable(true, false)) {
			resource.open();
		} catch (RuntimeException e) {
			print(e);
		} finally {
			System.out.println("Finally");
		}
		
		System.out.println("Throw on Open = false, Throw on Close = true");
		try (DemoAutoCloseable resource = new DemoAutoCloseable(false, true)) {
			resource.open();
		} catch (RuntimeException e) {
			print(e);
		} finally {
			System.out.println("Finally");
		}
		
		System.out.println("Throw on Open = true, Throw on Close = true");
		try (DemoAutoCloseable resource = new DemoAutoCloseable(true, true)) {
			resource.open();
		} catch (RuntimeException e) {
			print(e);
		} finally {
			System.out.println("Finally");
		}
	}
	
	private static void print(RuntimeException e) {
		StringBuilder builder = new StringBuilder();
		builder.append("Catch: ").append(e.getClass().getCanonicalName()).append("/").append(e.getMessage());
		builder.append("  Suppresed: ").append(e.getSuppressed().getClass().getCanonicalName()).append("/");
		System.out.println(builder.toString());
	}

}
