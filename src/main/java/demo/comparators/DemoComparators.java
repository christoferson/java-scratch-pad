package demo.comparators;

import java.util.Arrays;
import java.util.Comparator;

public class DemoComparators {
	
	public record Person(String firstName, String lastName, int age) {
	}

	public static void main(String[] args) {
		sortStringArray();
		sortStringArrayReversed();
		sortIntegerArray();
		sortRecordArrayByField();
	}
	
	public static void sortStringArray() {
		
		// Strings implement Comparable
		var strings = Arrays.asList("c", "d", "a", "b");
		
		// Using Streams
		var sorted = strings.stream().sorted().toList();
		System.out.println(sorted);
		
		// Using List Interface
		strings.sort(null);
		System.out.println(strings);
		
	}
	
	public static void sortStringArrayReversed() {
		
		// Strings implement Comparable
		var strings = Arrays.asList("c", "d", "a", "b", null);
		
		Comparator<String> comparator = Comparator.naturalOrder();
		comparator = comparator.reversed();
		// Using Streams
		var sorted = strings.stream()
				.sorted(Comparator.nullsFirst(comparator))
				.toList();
		System.out.println(sorted);
		
	}
	
	public static void sortStringArrayWithComparator() {
		
		// Strings implement Comparable which sorts alphabetically by default
		// Suppose we want to sort by length of string
		var strings = Arrays.asList("one", "three", "two", "four", "five");
		
		// Using Streams
		var sorted = strings.stream().sorted().toList();
		System.out.println(sorted);
		
		// Using List Interface
		strings.sort(null);
		System.out.println(strings);
		
	}
	
	// When comparing numbers do not implement Comparators, use built in library.
	// You can encounter nasty errors like overflow for custom implementations.
	public static void sortIntegerArray() {
		
		var numbers = Arrays.asList(32, 76, 8, 100, 50);
		
		// Using Streams
		var sorted = numbers.stream().sorted(Integer::compare).toList();
		System.out.println(sorted);
		
	}
	
	public static void sortRecordArrayByField() {
		
		var records = Arrays.asList(new Person("Foo", "Bar", 32), new Person("Foo", "Bar", 24));
		
		// Using Streams
		var sorted = records.stream()
				.sorted(Comparator.comparingInt(Person::age).thenComparing(Person::firstName).reversed())
				.toList();
		System.out.println(sorted);
		
	}
}
