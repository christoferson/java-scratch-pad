package demo.enums;

import java.util.Arrays;

public class DemoFeatureEnums {

	private static enum Suit {
		Heart(12), Spade(25), Diamond(5), Clover(7);
		private int code;
		private Suit(int code) {
			if (code < 0) throw new IllegalArgumentException();
			this.code = code;
		}
		public int getCode() {
			return code;
		}
		@Override
		public String toString() {
			return String.format("%s(%d)", super.toString(), this.code);
		}
	}
	
	public static void main(String[] args) {

		demoEnumInCase();
		
		demoEnumEquality();
		
		demoEnumMethods();

	}
	
	private static void demoEnumInCase() {
		Suit suit = Suit.Diamond;
		switch(suit) {
		case Heart : System.out.println("1"); break;
		case Diamond : System.out.println("2"); break;
		default: System.out.println(suit);		
		}
	}
	
	private static void demoEnumEquality() {
		Suit suit1 = Suit.Diamond;
		Suit suit2 = Suit.Diamond;
		System.out.println(suit1 == suit2);
		System.out.println(suit1.equals(suit2));
		System.out.println(suit1.compareTo(suit2));

		System.out.println(suit1 == Suit.Spade);
		System.out.println(suit1.equals(Suit.Spade));
	}

	private static void demoEnumMethods() {
		
		System.out.println(Suit.Diamond.name());
		System.out.println("Suit.Heart.ordinal(): " + Suit.Heart.ordinal());
		System.out.println("Suit.Diamond.ordinal(): " + Suit.Diamond.ordinal());
		System.out.println(Arrays.asList(Suit.values()));
		//System.out.println(Suit.valueOf(null)); //NPE
		//System.out.println(Suit.valueOf("unknown")); // java.lang.IllegalArgumentException
		System.out.println(Suit.valueOf("Clover"));
		
		//System.out.println(Enum.valueOf(Suit.class, null)); //NPE
		//System.out.println(Enum.valueOf(Suit.class, "unknown")); // java.lang.IllegalArgumentException
		System.out.println(Enum.valueOf(Suit.class, "Clover"));
		
		System.out.println(Suit.valueOf("Clover").getCode());
		
	}

}
