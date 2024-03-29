package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

import demo.autocloseable.DemoAutoCloseable;
import demo.exceptions.Jep358;
import demo.functions.DemoFunctionalInterface;
import demo.interfaces.PostJDK9;
import demo.interfaces.PostJDK9Impl;
import demo.interfaces.PreJDK9;
import demo.interfaces.PreJDK9Impl;
import demo.records.DemoRecords;
import demo.records.Period;
import demo.sealed.Animal;
import demo.sealed.Antarctica;
import demo.sealed.IContinent;
import demo.sealed.IShape;
import demo.sealed.IShape.Rectangle;
import demo.string.multiline.Jep378;

public class JavaScratchPad {

	public static void main(String[] args) {
		jm20211201OperatorPrecedence();
		jeps101TypeInference();
		try {
		tryAutoCloseable();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		tryRecordsNested();
		tryRecordsAccessor();
		tryRecordsConstructor();
		
		tryPrivateInterfaceMethod();
		trySealedClass();
		trySealedInterface();
		trySealedRecord();
		
		tryGetPermittedSubclasses();
		
		tryJep378MultilineText();
		
		tryJep358BetterNullPointerException();
	
		tryRandomGeneratorFactory();
		tryCharStream();
		tryCharStreamUsingRecord();
		
		DemoFunctionalInterface.demo();
	}

	private static void jm20211201OperatorPrecedence() {
		var res = 54/5*5-4.0+4*8/10;
		System.out.println(res);
	}
	
	static class MyList {
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
	
	private static void jeps101TypeInference() {
		

		
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
	
	public static void tryRecordsNested() {
		System.out.println("*** TryRecordsNested");
		DemoRecords.Description d = new DemoRecords().new Description("ABC");
		System.out.println(d);
		DemoRecords.Price p = new DemoRecords.Price(0, 100);
		System.out.println(p);
		
	}

	public static void tryRecordsAccessor() {
		System.out.println("*** TryRecordsAccessor");
		Period period = new Period(56);
		System.out.println(period);
		System.out.println(period.aLength());
	}

	public static void tryRecordsConstructor() {
		System.out.println("*** TryRecordsConstructor");
		Period period = new Period();
		System.out.println(period);
		System.out.println(period.aLength());
	}
	
	public static void tryPrivateInterfaceMethod() {
		System.out.println("*** TryPrivateInterfaceMethod");
		new PreJDK9Impl().f();
		new PreJDK9Impl().fd();
		PreJDK9.g();
		PreJDK9.fs();
		new PostJDK9Impl().f();
		//new PostJDK9Impl().fd(); //private is not visible
		PostJDK9.g();
		//PostJDK9.fs(); //private is not visible
	}

	public static void trySealedClass() {
		System.out.println("*** TrySealedClass");
		Animal animal = new Animal.Lion();
		System.out.println(animal);
	}

	public static void trySealedInterface() {
		System.out.println("*** TrySealedInterface");
		IShape ishape = new Rectangle();
		System.out.println(ishape);
	}

	public static void trySealedRecord() {
		System.out.println("*** TrySealedRecord");
		IContinent icontinent = new Antarctica("Antarctica");
		System.out.println(icontinent);
	}
	
	public static void tryGetPermittedSubclasses() {
		System.out.println("*** TryGetPermittedSubclasses");
		System.out.println(Arrays.asList(Animal.class.getPermittedSubclasses()));
		System.out.println(Arrays.asList(IShape.class.getPermittedSubclasses()));
		System.out.println(Arrays.asList(IContinent.class.getPermittedSubclasses()));
	}
	
	public static void tryJep378MultilineText() {
		System.out.println("*** TryJep378MultilineText");
		System.out.println(Jep378.HTML);
		System.out.println(Jep378.SQL);
	}
	
	public static void tryJep358BetterNullPointerException() {
		System.out.println("*** TryJep358BetterNullPointerException");
		Jep358.demo();
	}
	
	public static void tryRandomGeneratorFactory() {
		System.out.println("*** TryRandomGeneratorFactory");
		RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("Random");
		RandomGenerator random = factory.create(100L);
		System.out.println(random.nextInt(75));
		System.out.println(random.nextDouble(75));
	}
	
	private static void tryCharStream() {
		String string = """
		Adfjkgop455oia5435 543u5i43u543 dPjBB SSsA D f GG HH pLK
		""";
		
		// Distinct Characters
		String distinctCharacters = string.codePoints()
			.filter(Character::isAlphabetic)
			.map(Character::toLowerCase)
			.distinct()
			.mapToObj(Character::toString)
			.collect(Collectors.joining());
		System.out.println(distinctCharacters);
		
		// Map of Characters and Occurrence
		Map<String, Long> frequencyMap = string.codePoints()
		.filter(Character::isAlphabetic)
		.map(Character::toLowerCase)
		.mapToObj(Character::toString)
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(frequencyMap);
		
		// Character with the most occurrence
		Map.Entry<String, Long> frequencyMapMax = frequencyMap.entrySet().stream()
				.max(Map.Entry.comparingByValue()).orElseThrow();
		System.out.println(frequencyMapMax);
		
		// Character list with the most occurence
		List<Map.Entry<String, Long>> frequencyMaxList = frequencyMap.entrySet().stream()
		.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
		.limit(3)
		.toList();
		System.out.println(frequencyMaxList);
		
		// Map of Occurrence and Characters 
		Map<Long, List<String>> frequencyCharacterMap = frequencyMap.entrySet().stream()
		.collect(Collectors.groupingBy(
				Map.Entry::getValue, 
				Collectors.mapping(
						Map.Entry::getKey, 
						Collectors.toList()
				)
		)
		);
		System.out.println(frequencyCharacterMap);
		
		List<Map.Entry<Long, List<String>>> frequencyCharacterMapDes = frequencyCharacterMap.entrySet().stream()
		.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
		.toList();
		System.out.println(frequencyCharacterMapDes);
	}
	
	static record Letter(int codePoint) {
		Letter(int codePoint) {
			this.codePoint = Character.toLowerCase(codePoint);
		}
	}
	
	static record LetterByCount(Letter letter, Long count) {
		LetterByCount(Map.Entry<Letter, Long> entry) {
			this(entry.getKey(), entry.getValue());
		}
	}
	
	private static void tryCharStreamUsingRecord() {
		String string = """
		Adfjkgop455oia5435 543u5i43u543 dPjBB SSsA D f GG HH pLK
		""";
		
		
		// Map of Characters and Occurrence
		Map<Letter, Long> frequencyMap = string.codePoints()
		.filter(Character::isAlphabetic)
		.mapToObj(Letter::new)
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(frequencyMap);
		
		// Character with the most occurrence
		Map.Entry<Letter, Long> frequencyMapMax = frequencyMap.entrySet().stream()
				.max(Map.Entry.comparingByValue()).orElseThrow();
		System.out.println(frequencyMapMax);
		
		// Character list with the most occurence
		List<Map.Entry<Letter, Long>> frequencyMaxList = frequencyMap.entrySet().stream()
		.sorted(Map.Entry.<Letter, Long>comparingByValue().reversed())
		.limit(3)
		.toList();
		System.out.println(frequencyMaxList);
		/*
		// Map of Characters and Occurrence
		Map<Long, Letter> frequencyLetterMap = string.codePoints()
		.filter(Character::isAlphabetic)
		.mapToObj(Letter::new)
		.collect(Collectors.groupingBy(Collectors.counting(), Function.identity()));
		System.out.println(frequencyLetterMap);
		*/
	}
}
