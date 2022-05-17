package demo.sealed;

public sealed interface IContinent permits Antarctica {
	String name();
}
