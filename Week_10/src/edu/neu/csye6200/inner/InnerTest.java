package edu.neu.csye6200.inner;

public class InnerTest {
	// my secret key
	private int key = 20;
	
	public class InnerProc{
		public String encode(String input) {
			StringBuffer sb = new StringBuffer();
			for (char c : input.toCharArray()) {
				// XOR - Exclusive OR
				char c2 = (char) (c ^ key);
				// Add the encoded character to the buffer
				sb.append(c2);
			}
			// return the encoded buffer
			return sb.toString();
		}
	}
	
	/**
	 * Constructor - this is the constructor of my class
	 * @param key the secret encryption key
	 * @author Chen.JL
	 */
	public InnerTest(int key) {
		this.key = key;
		InnerProc iPoc = new InnerProc();
		
		String str0 = "My secret Message 123";
		System.out.println("Str0 " + str0);
		
		// Initial code
		String str1 = iPoc.encode(str0);
		System.out.println("Str1 " + str1);
		
		// Reverse the encoding due to XOR
		String str2 = iPoc.encode(str1);
		System.out.println("Str2 " + str2);
	}

	// args input arguments
	public static void main(String[] args) {
		InnerTest inTest = new InnerTest(30);
	}
}
