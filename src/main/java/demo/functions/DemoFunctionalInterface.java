package demo.functions;

import java.util.HashMap;
import java.util.Map;

public class DemoFunctionalInterface {

	public static void demoFunction() {
		Map<String, Integer> historgram = new HashMap<>();
		historgram.computeIfAbsent("A", e -> 5);
		historgram.computeIfAbsent("A", e -> 7);
		System.out.println(historgram);
	}
	
}
