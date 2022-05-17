package demo.sealed;

public record Antarctica(String name) implements IContinent {

	@Override
	public String name() {
		return this.name;
	}

}
