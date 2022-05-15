package demo.sealed;

public sealed interface IShape {

	non-sealed interface ITriangle extends IShape {
		
	}
	
	non-sealed interface IRectangle extends IShape {
		
	}
	
	class Rectangle implements IRectangle {
		
	}
	
}
