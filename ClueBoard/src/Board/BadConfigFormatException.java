package Board;

public class BadConfigFormatException extends Exception {
	public enum errorType {blah;}
	
	private errorType type;
	private BadConfigFormatException(errorType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return null;
	}
}
