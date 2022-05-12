package demo.records;

public class DemoRecords {

	public record Price(int min, int max) { }

	public class Description {
		public Description(String d) { }
	}

}
