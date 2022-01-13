package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JavaScratchPad {

	public static void main(String[] args) {
		jm20211201OperatorPrecedence();
		rndTypeInference();
	}

	private static void jm20211201OperatorPrecedence() {
		var res = 54/5*5-4.0+4*8/10;
		System.out.println(res);
	}
	
	private static void rndTypeInference() {
		
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
	
}
