package csye6200;

public class StrExample {

	// Constructor
	public StrExample()  {
		
//		String quote = "The universe is full of stars";
		String quote = "Mars";
		
		int len = quote.length();
		
		int starIndex = quote.indexOf("star");
		
		if (quote.equalsIgnoreCase("Mars"))
			System.out.println("We have a planet");
	}
	
	public static void main(String[] args) {
		StrExample strEx = new StrExample();
	}
}
