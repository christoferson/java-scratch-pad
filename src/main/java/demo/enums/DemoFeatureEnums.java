package demo.enums;

public class DemoFeatureEnums {

	private static enum Suit {
		Heart, Spade, Diamond, Cover
	}
	
	public static void main(String[] args) {

		demoEnumInCase();

	}
	
	private static void demoEnumInCase() {
		Suit suit = Suit.Diamond;
		switch(suit) {
		case Heart : System.out.println("1"); break;
		case Diamond : System.out.println("2"); break;
		default: System.out.println("3");		
		}
	}

}
