package demo.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DemoFunctionalInterface {

	public static void demo() {
		demoFunction();
		demoFunction2();
	}
	
	public static void demoFunction() {
		Map<String, Integer> historgram = new HashMap<>();
		Integer frequency = historgram.computeIfAbsent("A", e -> 5);
		System.out.println(frequency);
		frequency = historgram.computeIfAbsent("A", e -> 7);
		System.out.println(frequency);
		System.out.println(historgram);
	}
	
	public static void demoFunction2() {
		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";

		Function<Integer, String> quoteIntToString = quote.compose(intToString);

		System.out.println(quoteIntToString.apply(7));
	}
}
